package com.aman.registeruser;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;


@WebFilter("/login")
public class LoginFilter implements Filter {



    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String email = req.getParameter("email");
        String password = req.getParameter("password");



        if(Objects.equals(email, "") || Objects.equals(password, "")){

            out.println("<h2>Please fill all the details");
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.include(req,res);
        }else{
            chain.doFilter(req,res);
        }
    }


}
