package com.yaolala.dobigthing.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

public class DBUtil {
	private static Logger logger = Logger.getLogger(DBUtil.class);
	/**private static final String USER_NAME = "root";
	private static final String PWD = "1";
	private static final String DBURL = "jdbc:mysql://localhost:3306/java";**/
	private Connection conn = null;
    private PreparedStatement preparedStatment = null;
    private ResultSet rs = null;
    
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
    
    public List<Object[]> query(String sql, Object[] args) {
    	List<Object[]> list = new ArrayList<Object[]>();
        try {
            Class.forName(DBConfig.DRIVER_NAME);
            conn = DriverManager.getConnection(DBConfig.DATABASE_URL + DBConfig.DATABASE, DBConfig.USER_NAME, DBConfig.PWD);
            preparedStatment = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
				preparedStatment.setObject(i, args[i]);
			}
            rs = preparedStatment.executeQuery();
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据  
            int columnCount = md.getColumnCount();   //获得查询结果的列数 
            while (rs.next()) { //循环所有的行
            	//Map<String, Object> rowData = new HashMap<String,Object>();
            	Object[] rowData = new Object[columnCount];
                for (int i = 0; i <columnCount; i++) {  
                    //rowData.put(md.getColumnName(i), rs.getObject(i));
                	rowData[i]  = rs.getObject(i+1);
                }  
                list.add(rowData); 
			}
        } catch (ClassNotFoundException e) {
            logger.error("找不到数据库驱动", e);
        } catch (SQLException e) {
        	logger.error("获取数据库连接异常", e);
        } finally {
        	this.close();
        }
        return list;
    }
    
    public void close() {
    	try {
            if(rs != null) {
	           rs.close();
            }
            if(preparedStatment != null) {
            	preparedStatment.close();
            }
            if(conn != null) {
	           conn.close();
            }
        } catch (SQLException e) {
           logger.error("关闭数据库资源对象失败", e);
        }
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
