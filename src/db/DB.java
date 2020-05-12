package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	private static Connection conn = null;
	
	private static Properties loadProperties() {
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}catch(Exception e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static Connection getConnection() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Properties prop = loadProperties();
			String url = prop.getProperty("url");
			conn = DriverManager.getConnection(url, prop);
			conn.setAutoCommit(false);
		}catch(Exception e) {
			throw new DbException(e.getMessage());
		}
		return conn;
	}
	
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
