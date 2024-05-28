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
import java.sql.ResultSet;



@WebServlet("/login")
public class Login extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        RegisterDao rdao = new RegisterDao();
        Connection con = rdao.getConnection();

        String query = "select * from users where userName=? and password=?";

        try {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1,email);
            pst.setString(2,password);

            ResultSet rset = pst.executeQuery();

            if(rset.next()){
                out.println("<h1>You are logged In</h1>");

                out.println("<h2>");

                out.println("First Name:"+rset.getString("firstName"));
                out.println("<br>");

                out.println("Last Name:"+rset.getString("lastName"));
                out.println("<br>");

                out.println("Email:"+rset.getString("userName"));
                out.println("<br>");

                out.println("DOB:"+rset.getDate("dob"));
                out.println("<br>");

                out.println("</h2>");
            }else {
                out.println("<h2>Username or password is incorrect</h2>");
                RequestDispatcher rd = req.getRequestDispatcher("login.html");
                rd.include(req,res);
            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
