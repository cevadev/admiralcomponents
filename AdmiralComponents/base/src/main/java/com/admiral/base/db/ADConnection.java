package com.admiral.base.db;

import com.admiral.base.utils.InitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Properties;


public class ADConnection implements Serializable, Cloneable {
    private static Logger logger = LoggerFactory.getLogger(ADConnection.class);
    private volatile static ADConnection s_cc = null;

    public synchronized static ADConnection get(){
        if(s_cc == null){
            String attributes = InitProperties.getProperty(InitProperties.AD_CONNECTION);
        }
    }
}
