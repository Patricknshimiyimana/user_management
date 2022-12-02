package user_management.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This DAO represents the data access object
// Provides CRUD db operations for the table users in the db
public class UserDAO {
  private String jdbcUsername = "root";
  private String jdbcPassword = "QWERTY@12";
  private String jdbcURL = "jdbc:mysql://localhost:3306/user_management?useSSL=false";
  
  // SQL Commands for user db crud
  private static final String insertUser = "insert into users" + " (name,email,country) values " + "(?,?,?);";
  private static final String selectUserById = "select id,name,email,country from users where id = ?";
  private static final String selectAllUsers = "select * from users";
  private static final String deleteUser = "delete from users where id = ?;"; 
  private static final String updateUser = "update users set name = ?, email = ?, country = ? where id = ?;";
  
  protected Connection getConnection() {
	  Connection connection = null;
	  try {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return connection;
  }
  // Create user
  // Update user
  // Select user by id
  // Select All
  // Delete user
}
