package user_management.dao;

// This DAO represents the data access object
// Provides CRUD db operations for the table users in the db
public class UserDAO {
  private String jdbcUsername = "root";
  private String jdbcPassword = "QWERTY@12";
  private String jdbcURL = "jdbc:mysql://localhost:3306/user_management?useSSL=false";
  
  private static final String insertUser = "insert into users" + " (name,email,country) values " + "(?,?,?);";
}
