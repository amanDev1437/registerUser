package com.aman.registeruser;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;



@WebServlet("/register")
public class Register extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {



        res.setContentType("text/html");

        PrintWriter out = res.getWriter();

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String dob = req.getParameter("dob");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        RegisterDao rdao = new RegisterDao();
        Connection con = rdao.getConnection();

        String query = "insert into users values(?,?,?,?,?)";

        try {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1,email);
            pst.setString(2,password);
            pst.setString(3,firstName);
            pst.setString(4,lastName);
            pst.setString(5,dob);

            int rowsAffected = pst.executeUpdate();
            if(rowsAffected>0){
                out.println("You are registered successfully");
                RequestDispatcher rd = req.getRequestDispatcher("login.html");
                rd.include(req,res);

            }else{
                out.println("Registration failed");
                RequestDispatcher rd = req.getRequestDispatcher("index.html");
                rd.include(req,res);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
