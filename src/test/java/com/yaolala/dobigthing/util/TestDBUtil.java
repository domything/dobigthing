package com.yaolala.dobigthing.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.yaolala.dobigthing.entity.User;

import junit.framework.Assert;

public class TestDBUtil {
	private Logger logger = Logger.getLogger(TestDBUtil.class);  
    
	@Test
	public void testLog4j() {  
        logger.info("info...........");  
        logger.debug("debug...........");  
        logger.warn("warn...........");  
        logger.error("error...........");  
        logger.fatal("fatal...........");  
    }  
	
	@Test
    public void testGetConnection() {
    		Connection conn = DBUtil.getConnection();
    		String sql="insert into t_user values (?,?,?)";
    		PreparedStatement pstmt = null;
    		User u = new User(10, 20, "yaolala");
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, u.getId());
            pstmt.setInt(2, u.getAge());
            pstmt.setString(3, u.getName());
            pstmt.executeUpdate();
            logger.info("插入成功！");
        } catch (SQLException e) {
        		logger.error("失败", e);
            Assert.fail();
        }finally{
            DBUtil.closeAll(null, pstmt, conn);
        }
    		boolean b = (conn == null);
    		Assert.assertEquals(false, b);
    }
   

}
