package com.admiral.base.utils;

import com.admiral.base.db.ADConnection;

import java.util.Properties;

public class TestConnection {
    public static synchronized boolean startup(boolean isClient){
        InitProperties.setClient(isClient);
        InitProperties.loadProperties(isClient);
        Properties properties = InitProperties.getProperties();

        Database.setDatabaseTarget(ADConnection.get());
    }
}
