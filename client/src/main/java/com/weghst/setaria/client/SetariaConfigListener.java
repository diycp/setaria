package com.weghst.setaria.client;

import java.util.EventListener;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface SetariaConfigListener extends EventListener {

    /**
     *
     */
    enum Event {
        /**
         *
         */
        BEFORE_INIT,
        /**
         *
         */
        AFTER_INIT,
        /**
         *
         */
        BEFORE_REFRESH,
        /**
         *
         */
        AFTER_REFRESH,
        /**
         *
         */
        BEFORE_DESTROY,
        /**
         *
         */
        AFTER_DESTROY
    }

    /**
     * @param event
     */
    void execute(Event event);
}
