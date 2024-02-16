package com.admiral.base.db;

import com.admiral.base.utils.InitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;


public class ADConnection implements Serializable, Cloneable {
    private static Logger logger = LoggerFactory.getLogger(ADConnection.class);
    private volatile static ADConnection s_cc = null;

    private String m_name = "Standard";
    private String m_app_host = "localhost";
    private String m_db_host = "localhost";
    private String m_type = "";
    private int m_db_port = 0;
    private String m_db_name = "";
    private String m_db_uid = "adempiere";
    private String m_db_pwd = "adempiere";

    public ADConnection(String host){
        if(host != null){
            m_app_host = host;
            m_db_host = host;
        }
    }

    public String getName(){
        return m_name;
    }
    public void setName(String name){
        this.m_name = name;
    }
    public String getAppHost(){
        return m_app_host;
    }
    public void setAppHost(String appHost){
        this.m_app_host = appHost;
    }
    public String getDbHost(){
        return m_db_host;
    }
    public void setDbHost(String dbHost){
        this.m_db_host = dbHost;
    }
    public String getType(){
        return this.m_type;
    }
    public void setType(String type){
        this.m_type = type;
    }
    public int getDbPort(){
        return this.m_db_port;
    }
    public void setDbPort(int dbPort){
        this.m_db_port = dbPort;
    }
    public String getDbName(){
        return this.m_db_name;
    }
    public void setDbName(String dbName){
        this.m_db_name = dbName;
    }
    public String getDbUid(){
        return this.m_db_uid;
    }
    public void setDbUid(String dbUid){
        this.m_db_uid = dbUid;
    }
    public String getDbPwd(){
        return this.m_db_pwd;
    }
    public void setDbPwd(String dbPwd){
        this.m_db_pwd = dbPwd;
    }

    /**
     * appsHost,type,DBHost,DBPort,DBName,UID,PWD
     */
    private void setAttributes(String attributes){
        TODOHERE;
    }

    public synchronized static ADConnection get(){
        if(s_cc == null){
            String attributes = InitProperties.getProperty(InitProperties.AD_CONNECTION);
            s_cc = new ADConnection(null);
            s_cc.setAttributes(attributes);
        }
    }
}
