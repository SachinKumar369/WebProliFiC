package com.prologic.util.data;

import java.sql.*;

/**
 * Created by USER on 4/11/2024.
 */
public class DataBaseDriver {

	String url;
	String userName;
	String password;
	private Connection connection;

	public DataBaseDriver(String url, String userName, String password) {
		this.url = url;
		this.userName = userName;
		this.password = password;
	}

	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		connection = DriverManager.getConnection(url, userName, password);
	}

	public ResultSet executeQuery(String query) throws SQLException {
		Statement s1 = connection.createStatement();
		return s1.executeQuery(query);
	}

}
