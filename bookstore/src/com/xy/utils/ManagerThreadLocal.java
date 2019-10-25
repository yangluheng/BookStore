package com.xy.utils;


import java.sql.Connection;
import java.sql.SQLException;

public class ManagerThreadLocal {

	private static ThreadLocal<Connection>  tl = new ThreadLocal<Connection>();

	public static Connection getConnection() throws SQLException {
		Connection conn = tl.get();
		if(conn == null){
			conn = JDBCUtils.getConnection();
			tl.set(conn);
		}
		return conn;
	}
	public static void beginTransaction(){
		try {
			getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void commitTransaction(){
		try {
			getConnection().commit();;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(){
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(){
		try {
			getConnection().close();
			tl.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
