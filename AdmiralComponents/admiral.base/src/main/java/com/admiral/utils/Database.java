package com.admiral.utils;

import com.admiral.db.ADConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Database {
    private static Logger logger = LoggerFactory.getLogger(Database.class);
    private static ADConnection s_cc = null;
    private static Object s_ccLock = new Object();

    public static void closeTarget(){
        boolean closed = false;
        if(s_cc != null){
            closed = true;
            s_cc.setDataSource(null);
        }
        s_cc = null;
        if(closed)
            logger.info("closed");
    }
    public static void setDatabaseTarget(ADConnection cc){
        if(cc == null){
            throw new IllegalArgumentException("Connection is null");
        }

        if(s_cc != null && s_cc.equals(cc)){
            return;
        }

        Database.closeTarget();
    }
}
