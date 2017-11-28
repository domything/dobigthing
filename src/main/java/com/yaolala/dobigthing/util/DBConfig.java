package com.yaolala.dobigthing.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DBConfig {
	private static Logger logger = Logger.getLogger(DBConfig.class);
	private static Properties prop = new Properties();    
    static{        
        try {
            //加载jdbc.propertiess配置文件
            prop.load(DBConfig.class.getResourceAsStream("/jdbc.properties"));
        } catch (IOException e) {
        		logger.error("读取资源文件失败", e);
        }
    }
    
    //设置常量
    public static final String DRIVER_NAME = prop.getProperty("driver_name");
    public static final String DATABASE_URL = prop.getProperty("database_url");
    public static final String DATABASE = prop.getProperty("database");
    public static final String USER_NAME = prop.getProperty("user_name");
    public static final String PWD = prop.getProperty("pwd");
    

}
