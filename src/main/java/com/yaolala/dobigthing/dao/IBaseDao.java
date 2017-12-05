package com.yaolala.dobigthing.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDao {

	/**
	 * 执行sql查询
	 * @param sql
	 * @param params
	 * @return
	 */
	List<Map<String, String>> executeQuery(String sql, Object[] params);
	
	List<Object[]> query(String sql, Object[] args);
	
	/**
	 * 执行sql更新操作
	 * @param sql
	 * @param params
	 * @return 受影响的行数
	 */
	int executeUpdate(String sql, Object[] params);
}
