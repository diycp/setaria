package com.weghst.setaria.client;

import org.testng.annotations.Test;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class JsonSetariaConfigTest {

    @Test
    public void testInit() throws Exception {
        SetariaBean setariaBean = new SetariaBean();
        setariaBean.addResource(new SetariaBean.Resource("classpath:com/weghst/setaria/client/test-config-1.json"));
        setariaBean.addResource(new SetariaBean.Resource("classpath:com/weghst/setaria/client/test-config-2.json"));
        setariaBean.addResource(new SetariaBean.Resource("classpath:com/weghst/setaria/client/test-config-3.json", true));

        JsonSetariaConfig setariaConfig = new JsonSetariaConfig(setariaBean);
        setariaConfig.init();
    }

    @Test
    public void testRefresh() throws Exception {
    }

}