package com.aman.registeruser;

import java.sql.*;


public class RegisterDao {

    public Connection getConnection(){

        Connection connection = null;

        String url = "jdbc:mysql://localhost:3306/hotel";
        String user = "root";
        String pass = "tiger";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url,user,pass);

            if(connection.isClosed()){
                System.out.println("Disconnected to DB");
            }else{

                System.out.println("Connected to DB");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return connection;

    }

}
