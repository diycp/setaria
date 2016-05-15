package com.weghst.setaria.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public abstract class AbstractWatchedSetariaConfig extends AbstractSetariaConfig {

    private SetariaBean setariaBean;
    private Thread fileWatchThread;

    protected AbstractWatchedSetariaConfig(SetariaBean setariaBean) {
        Assert.notNull(setariaBean);
        this.setariaBean = setariaBean;
    }

    @Override
    protected void doInit() {
        refresh();

        // watch file change
        fileWatchThread = new Thread(new ConfigFileWatch(this, setariaBean), "setaria-config-file-watch");
        fileWatchThread.setDaemon(true);
        fileWatchThread.start();
    }

    /**
     * @return
     */
    protected SetariaBean getSetariaBean() {
        return setariaBean;
    }

    private class ConfigFileWatch implements Runnable {

        private SetariaConfig setariaConfig;
        private SetariaBean setariaBean;

        ConfigFileWatch(SetariaConfig setariaConfig, SetariaBean setariaBean) {
            this.setariaConfig = setariaConfig;
            this.setariaBean = setariaBean;
        }

        @Override
        public void run() {
            WatchService watchService;
            try {
                watchService = FileSystems.getDefault().newWatchService();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }

            List<String> fileNames = new ArrayList<>();
            for (SetariaBean.Resource resource : setariaBean.getResources()) {
                try {
                    URL url = ResourceUtils.getURL(resource.getLocation());
                    if (ResourceUtils.isJarURL(url)) {
                        url = ResourceUtils.extractJarFileURL(url);
                    }

                    Path path = Paths.get(url.toURI());
                    File file = path.toFile();
                    fileNames.add(file.getName());
                    if (file.isFile()) {
                        path = path.getParent();
                    }

                    path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                            StandardWatchEventKinds.ENTRY_MODIFY);
                } catch (FileNotFoundException e) {
                    if (!resource.isIgnoreNotFound()) {
                        throw new SetariaConfigException(e);
                    }
                } catch (Exception e) {
                    throw new SetariaConfigException(e);
                }
            }

            for (; ; ) {
                WatchKey watchKey = null;
                try {
                    watchKey = watchService.take();
                } catch (InterruptedException e) {
                    // ignore
                }

                if (watchKey == null) {
                    continue;
                }

                List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                for (WatchEvent<?> watchEvent : watchEvents) {
                    if (fileNames.contains(watchEvent.context().toString())) {
                        setariaConfig.refresh();
                        break;
                    }
                }
                watchKey.reset();
            }
        }

    }
}
