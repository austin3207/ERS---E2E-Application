package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.revature.model.Claim;
import com.revature.model.Employee;

public class DBConnection {
	
	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet result = null;
	
	public static void connect() throws Exception{
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://database-1.chvxwgszfvm3.us-east-2.rds.amazonaws.com:5432/postgres";
		String username = "postgres";
		String password = "password";
		connection = DriverManager.getConnection(url, username, password);
				
	}
	public static void closeResource() throws Exception {
		if (result != null) {
			result.close();
		}
		if (preparedStatement != null) {
			preparedStatement = null;
		}
		if (statement != null) {
			statement = null;
		}
		if (connection != null) {
			connection = null;
		}
	}
	public static List<Employee> findAllEmployees() throws Exception {
		DBConnection.connect();
		List<Employee> employees = new ArrayList<Employee>();
		Employee temp = new Employee("temp");
		//List<Claim> tempClaims = new ArrayList<Claim>();
		String query = "select * from project1.employee";
		statement= connection.createStatement();
		//store/process result
		result = statement.executeQuery(query);
		while(result.next()) {
			temp = new Employee("temp");
			temp.setUsername(result.getString(1));
			temp.setName(result.getString(2));
			temp.setPassword(result.getString(3));
			temp.setAdmin(result.getBoolean(4));
			//System.out.println(temp.getUsername());
			
			employees.add(temp);
			//System.out.println(result.getInt(1) + "\t" + result.getString(2) + "\t" + result.getString(3));
		}
		DBConnection.closeResource();
		
		return employees;
	}
	public static List<Claim> findAllClaims() throws Exception {
		DBConnection.connect();
		List<Claim> claims = new ArrayList<Claim>();
		Claim claim = new Claim();
		String query = "select * from project1.claim";
		statement= connection.createStatement();
		//store/process result
		result = statement.executeQuery(query);
		while(result.next()) {
			claim = new Claim();
			claim.setClaim_id(result.getInt(1));
			claim.setClaim_amount(result.getDouble(2));
			claim.setClaim_reason(result.getString(3));
			claim.setClaim_status(result.getString(4));
			claim.setUserName(result.getString(5));
			claims.add(claim);
			//System.out.println(result.getInt(1) + "\t" + result.getString(2) + "\t" + result.getString(3));
		}
		DBConnection.closeResource();
		return claims;
		
	}
	public static List<Claim> findClaimsByUsername(String userName) throws Exception {
		DBConnection.connect();
		String query = "select * from project1.claim WHERE `user_name`=" + userName;
		statement= connection.createStatement();
		//store/process result
		result = statement.executeQuery(query);
		List<Claim> claims = new ArrayList<Claim>();
		Claim claim = new Claim();
		while(result.next()) {
			claim = new Claim();
			claim.setClaim_id(result.getInt(1));
			claim.setClaim_amount(result.getDouble(2));
			claim.setClaim_reason(result.getString(3));
			claim.setClaim_status(result.getString(4));
			claim.setUserName(userName);
			claims.add(claim);
		}
		DBConnection.closeResource();
		return claims;
	}
	public static void insertNewClaim(Claim claim) throws Exception {
		DBConnection.connect();
		String insertQuery = "INSERT INTO project1.claim (claim_amount, claim_reason, user_name, claim_approved) VALUES (?,?,?,?);";
		preparedStatement = connection.prepareStatement(insertQuery);
		preparedStatement.setDouble(1, claim.getClaim_amount());
		preparedStatement.setString(2, claim.getClaim_reason());
		preparedStatement.setString(3, claim.getUserName());
		preparedStatement.setString(4, claim.getClaim_status());
		preparedStatement.executeUpdate();
		DBConnection.closeResource();

	}
	public static void updateClaim(Claim claim) {
		try {
			DBConnection.connect();
			String updateQuery = "UPDATE project1.claim SET claim_approved= ? WHERE claim_id = ?;";
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, claim.getClaim_status());
			preparedStatement.setInt(2, claim.getClaim_id());
			preparedStatement.executeUpdate();
			DBConnection.closeResource();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static boolean checkCredentials(String username, String pwd) throws Exception {
		DBConnection.connect();
		String query = "select exists(select *from employee where user_name= ? AND password= ?);";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, pwd);
		result = preparedStatement.executeQuery();
		result.next();
		if (result.getInt(1) == 1) {
			DBConnection.closeResource();
			return true;
		}
		DBConnection.closeResource();
		return false;
	}
	/*
	 * Updates employee object including claims
	 */
	public static void updateCurrentObject(Employee employee) throws Exception {
		DBConnection.connect();

		String query = "select * from employee WHERE `user_name`=" + employee.getUsername();
		statement = connection.createStatement();
		// store/process result
		result = statement.executeQuery(query);
		result.next();
		employee.setUsername(result.getString(1));
		employee.setName(result.getString(3));
		employee.setAdmin(result.getBoolean(4));
		
		List<Claim> claims = new ArrayList<Claim>();
		Claim tempClaim = new Claim();
		query = "select * from claim WHERE `user_name`=" + employee.getUsername();
		statement = connection.createStatement();
		// store/process result
		result = statement.executeQuery(query);
		while(result.next()) {
			tempClaim.setClaim_id(result.getInt(1));
			tempClaim.setClaim_amount(result.getDouble(2));
			tempClaim.setClaim_reason(result.getString(3));
			tempClaim.setClaim_status(result.getString(4));
			claims.add(tempClaim);
		}
		DBConnection.closeResource();
	}
}
