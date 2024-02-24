package com.admiral.db;

import com.admiral.utils.InitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;


public class ADConnection implements Serializable, Cloneable {
    private static Logger logger = LoggerFactory.getLogger(ADConnection.class);
    private volatile static ADConnection s_cc = null;

    private String m_name = "Standard";
    private String m_apps_host = "localhost";
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
    public String getAppsHost(){
        return m_apps_host;
    }
    public void setAppsHost(String appHost){
        this.m_apps_host = appHost;
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

    public void setDbPort(int db_port){
        m_db_port = db_port;
    }
    public void setDbPort(String dbPort){
        try{
            if(dbPort == null || dbPort.isEmpty()){
                ;
            }else{
                setDbPort(Integer.parseInt(dbPort));
            }
        }catch(Exception ex){
            logger.error(ex.toString());
        }
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
        try{
            attributes = attributes.substring(attributes.indexOf("[") + 1, attributes.length());
            String[] pairs = attributes.split(",");
            for(String pair : pairs){
                String[] pairComponents = pair.split("=");
                String key = pairComponents[0];
                String value = pairComponents.length == 2 ? pairComponents[1] : "";
                if("name".equalsIgnoreCase(key)){
                    setName(value);
                }
                else if("AppsHost".equalsIgnoreCase(key)){
                    setAppsHost(value);
                }
                else if("type".equalsIgnoreCase(key)){
                    setType(value);
                }
                else if("DBhost".equalsIgnoreCase(key)){
                    setDbHost(value);
                }
                else if("DBport".equalsIgnoreCase(key)){
                    setDbPort(value);
                }else if("DBname".equalsIgnoreCase(key)){
                    setDbName(value);
                }else if("UID".equalsIgnoreCase(key)){
                    setDbUid(value);
                }else if("PWD".equalsIgnoreCase(key)){
                    setDbPwd(value);
                }
            }
        }catch (Exception ex){
            logger.error(attributes + " - " + ex.toString());
        }
    }

    public synchronized static ADConnection get(){
        if(s_cc == null){
            String attributes = InitProperties.getProperty(InitProperties.AD_CONNECTION);
            s_cc = new ADConnection(null);
            s_cc.setAttributes(attributes);
        }
    }
}
