package com.example.ecomm;
import java.sql.*;

public class DatabaseConnection {

    /* we need these three things url,username,password to connect with database*/
    String dbURL = "jdbc:mysql://localhost:3306/ecomm ";
    String userName = "root";
    String password = "#@incorrect";


    /*Now comes the connection part*/
    private Statement getStatement(){
        try{
            /* 2. ---> Driver manager return connection which we  catch in the object of connection class,,
            * and then with the help of that connection ,, we can create a statements to execute queries on database*/
            Connection conn = DriverManager.getConnection(dbURL,userName,password);  //1.---> DriverManager class helps us to create a connection with database
            return conn.createStatement(); //3.---> so from connection object  we create a statement object and that statement object is used to run select queries as well as insert queries
        } catch(Exception e){
            e.printStackTrace();
        }

        return null;  //if its fail to connect return null;
    }


    // executing the query using statement,,,,and getting the result of that query
    public ResultSet getQuery(String query){
        Statement statement = getStatement(); // calling getStatement method and string the statement

        try{
            return statement.executeQuery(query);
        } catch (SQLException exception){
            exception.printStackTrace();
        }

        return null;
    }

    public boolean insertUpdatesQuery(String query){
        Statement statement = getStatement(); // calling getStatement method and string the statement

        try{
            statement.executeUpdate(query);
            return  true;
        } catch (SQLException exception){
            exception.printStackTrace();
        }

        return false;
    }




//    public static void main(String[] args) {
//
//        // writing queries
//        String query = "SELECT * FROM products";
//
//        DatabaseConnection dbConn = new DatabaseConnection();  //created an object ob database class
//        ResultSet resultSet = dbConn.getQuery(query);
//
//        if(resultSet != null){
//            System.out.println("Connected to database");
//        }
//    }

}
