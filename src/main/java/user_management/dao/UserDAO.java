package user_management.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user_management.model.User;

// This DAO represents the data access object
// Provides CRUD db operations for the table users in the db
public class UserDAO {
  private String jdbcUsername = "root";
  private String jdbcPassword = "QWERTY@12";
  private String jdbcURL = "jdbc:mysql://localhost:3306/user_management?useSSL=false";
  
  // SQL Commands for user db crud
  private static final String INSERT_USER = "insert into users" + " (name,email,country) values " + "(?,?,?);";
  private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id = ?";
  private static final String SELECT_ALL_USERS = "select * from users";
  private static final String DELETE_USER = "delete from users where id = ?;"; 
  private static final String UPDATE_USER = "update users set name = ?, email = ?, country = ? where id = ?;";
  
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
  public void insertUser(User user) {
	  try {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2, user.getEmail());
		preparedStatement.setString(3, user.getCountry());
		preparedStatement.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
 }
	  
	  // Update user
	  public boolean updateUser(User user) {
		  boolean rowUpdated = false;
		  try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.setInt(4, user.getId());
			
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  
		return rowUpdated;
  }
  
  // Select user by id
	  public User selectUser(int id) {
		  User user = null;
		  try {
				Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				
				// process the ResultSet object
				while(rs.next()) {
					String name = rs.getString("name");
					String email = rs.getString("email");
					String country = rs.getString("country");
					user = new User(id, name, email, country);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return user;
			  
	  }
	  
  // Select All Users
	  public List<User> selectAllUsers() {
		  List<User> users = new ArrayList<>();
		  try {
				Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				
				// process the ResultSet object
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String country = rs.getString("country");
					users.add(new User(id, name, email, country));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return users;
			  
	  }
	  
  // Delete user
	  public boolean deleteUser(int id) {
		  boolean rowDeleted = false;
		  try {
				Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
				preparedStatement.setInt(1, id);
				rowDeleted = preparedStatement.executeUpdate() > 0;
	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return rowDeleted;
			  
	  }
}
