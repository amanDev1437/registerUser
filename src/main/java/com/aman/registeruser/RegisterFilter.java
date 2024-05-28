package com.aman.registeruser;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebFilter("/register")
public class RegisterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String email = req.getParameter("email");

        RegisterDao rdao = new RegisterDao();
        Connection con = rdao.getConnection();

        String query = "select userName from users where userName=?";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,email);

            ResultSet rset = pst.executeQuery();

            if(rset.next()){
                out.println("<h2>This email is already registered!!</h2>");
                RequestDispatcher rd = req.getRequestDispatcher("index.html");
                rd.include(req,res);

            }else {
                chain.doFilter(req,res);
                System.out.println("inside else filter");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
