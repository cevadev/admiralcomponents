package com.admiral.base.utils;

import com.admiral.base.db.Database_PostgreSQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * <b>Load Ini properties from properties file</b>
 */
public final class InitProperties implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(InitProperties.class);
    private volatile static Properties ad_prop = new Properties();
    /** IsClient mode           */
    private static boolean      ad_client = false;
    /** Property file name				*/
    public static final String	ADMIRAL_PROPERTY_FILE = "admiral.properties";

    /* Connection deatils*/
    private static final String AD_CONNECTION = "Connection";
    private static final String DEFAULT_CONNECTION = "";

    /* Init properties */
    private static final String[] PROPERTIES = new String[]{AD_CONNECTION};

    /* Init properties values*/
    private static final String[] VALUES = new String[]{DEFAULT_CONNECTION};

    public static boolean isClient()
    {
        return ad_client;
    }   //  isClient

    public static void setClient (boolean client)
    {
        ad_client = client;
    }   //  setClient

    public static String getFileName(boolean tryUserHome){
        String basePath = null;
        if (tryUserHome && ad_client)
            basePath = System.getProperty("user.home");
        if (basePath != null && !basePath.endsWith(File.separator))
            basePath += File.separator;
        if (basePath == null)
            basePath = "";
        //
        return basePath + ADMIRAL_PROPERTY_FILE;
    }

    private static String checkProperty(String key, String defaultValue){
        String result =  null;
        if(!isClient()){
            result = ad_prop.getProperty(key);
        }
        ad_prop.setProperty(key, result);
        return result;
    }

    private static void checkProperties(){
        for(int i = 0; i < PROPERTIES.length; i++){
            if(VALUES.length > 0){
                checkProperty(PROPERTIES[i],VALUES[i]);
            }
        }
    }

    public static boolean loadProperties(String fileName){
        boolean loadOk = true;
        boolean firstTime = false;
        ad_prop = new Properties();
        try(FileInputStream fis = new FileInputStream(fileName)){
            ad_prop.load(fis);
        }
        catch(IOException e){
            logger.warn(fileName + " file not found");
            loadOk = false;
        }
        catch(Exception e){
            logger.error("Error severo " + fileName + " - " + e.toString());
            loadOk = false;
        }
        catch (Throwable t){
            logger.error("Error severo " + fileName + " - " + t.toString());
            loadOk = false;
        }
        if(!loadOk){
            firstTime = true;
        }

        checkProperties();
        return firstTime;
    }

    /**
     *	Load INI parameters from disk
     *  @param reload reload
     */
    public static void loadProperties (boolean reload)
    {
        if (reload || ad_prop.size() == 0)
        {
            loadProperties(getFileName(ad_client));
        }
    }	//	loadProperties
}