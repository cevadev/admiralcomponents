package com.admiral.base.db;

import com.admiral.base.utils.InitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;


public class ADConnection implements Serializable, Cloneable {
    private static Logger logger = LoggerFactory.getLogger(ADConnection.class);

    public static synchronized boolean startup(boolean isClient){
        InitProperties.setClient(isClient);
        InitProperties.loadProperties(isClient);
    }
}
