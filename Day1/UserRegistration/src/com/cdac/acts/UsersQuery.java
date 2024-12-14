package com.cdac.acts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UsersQuery {
	
	public static void registerNewUser(){
		Connection connection = null;
		PreparedStatement pstSelect = null;
		Statement stSelect = null;
		ResultSet result = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "cdac");
			
			stSelect = connection.createStatement();
			pstSelect = connection.prepareStatement("INSERT INTO `users`.`registeredusers` (`userId`, `userName`, `psswd`, `email`, `city`) VALUES "
					+ "(?,?,?,?,?);");
			System.out.println("Enetr user ID : ");
			int id = sc.nextInt();
			
			sc.nextLine();
			System.out.println("Enetr user name : ");
			String name = sc.next();
			
			System.out.println("Enetr user password : ");
			String psswd = sc.next();
			
			System.out.println("Enetr user email : ");
			String email = sc.next();
			
			System.out.println("Enetr user city : ");
			String city = sc.next();
			
			pstSelect.setInt(1, id);
			pstSelect.setString(2, name);
			pstSelect.setString(3, psswd);
			pstSelect.setString(4, email);
			pstSelect.setString(5, city);
			
			pstSelect.executeUpdate(); 
			System.out.println("/****************New User Registered*****************/");

		}catch (SQLException e) {
			e.printStackTrace();
		}
		
//		displayAllUsers();
		
		sc.close();
	}
	
	public static void displayAllUsers() {
		Connection connection = null;
		Statement stSelect = null;
		ResultSet result = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "cdac");
			
			stSelect = connection.createStatement();
			
			result = stSelect.executeQuery("Select * from registeredusers");
			
			
			while ( result.next() ) {
				System.out.println(result.getInt(1)+  " || " + result.getString(2)+  " || " + result.getString(3)+  " || " + result.getString(4)+  " || " + result.getString(5));
				System.out.println("/*********************************/");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void filterByCity() {
		Connection connection = null;
		PreparedStatement pstSelect = null;
		Statement stSelect = null;
		ResultSet result = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "cdac");
			
			stSelect = connection.createStatement();
			pstSelect = connection.prepareStatement("Select * from registeredusers where city=?");
			System.out.println("Enetr city : ");
			String city = sc.next();
			
			pstSelect.setString(1, city);
			
			result = pstSelect.executeQuery(); 
	
			
			while ( result.next() ) {
				System.out.println(result.getInt(1)+  " || " + result.getString(2)+  " || " + result.getString(3)+  " || " + result.getString(4)+  " || " + result.getString(5));
				System.out.println("/*********************************/");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		sc.close();
	}
	

}
