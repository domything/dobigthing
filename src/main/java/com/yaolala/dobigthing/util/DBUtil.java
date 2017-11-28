package com.yaolala.dobigthing.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DBUtil {
	private static Logger logger = Logger.getLogger(DBUtil.class);
	/**private static final String USER_NAME = "root";
	private static final String PWD = "1";
	private static final String DBURL = "jdbc:mysql://localhost:3306/java";**/
	
	private DBUtil() {
	}

	/**
	 * 获得数据库连接
	 * @return
	 */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(DBConfig.DRIVER_NAME);
            conn = DriverManager.getConnection(DBConfig.DATABASE_URL + DBConfig.DATABASE, DBConfig.USER_NAME, DBConfig.PWD);
        } catch (ClassNotFoundException e) {
        		logger.error("找不到数据库驱动", e);
        } catch (SQLException e) {
        		logger.error("获取数据库连接异常", e);
        }
        return conn;
    }
    
    /**
     * 关闭数据库资源
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void closeAll(ResultSet rs, Statement stmt, Connection conn){
        try {
            if(rs!=null) {
	            	rs.close();
            }
            if(stmt!=null) {
	            	stmt.close();
            }
            if(conn!=null) {
	            	conn.close();
            }
        } catch (SQLException e) {
           logger.error("关闭数据库资源对象失败", e);
        }
    }
}
