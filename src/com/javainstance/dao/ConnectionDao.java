package com.javainstance.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author javainstance
 *
 */
public class ConnectionDao {

	private static Connection con = null;

	public static final String DB_URL = "jdbc:mysql://localhost:3306/uploadfile";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "Welcome123";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @return Connection
	 */
	public static Connection getConnection() {

		return con;

	}

	public void closeConnection(Connection con) {

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
