package com.ecommerce.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ecommerce.entities.User;
import com.ecommerce.myfactory.FactoryProvider;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response, String user_name) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("message", "Registration is Succesful " + user_name);
        response.sendRedirect("register.jsp");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user_name = request.getParameter("username");
        String user_email = request.getParameter("email");
        String user_password = request.getParameter("password");
        String user_phone = request.getParameter("phone");
        String user_address = request.getParameter("address");

        User user = new User(user_name, user_email, user_password, user_phone, "default.jpg", user_address, "normal");

        try (Session session = FactoryProvider.getFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            session.save(user);

            tx.commit();
        }

        System.out.println("succesfull " + user_name);

        doGet(request, response, user_name);
    }

}
