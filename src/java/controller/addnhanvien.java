/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ADDNHANVIEN;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class addnhanvien extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addnhanvien</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addnhanvien at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addnhanvien.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String number = request.getParameter("number");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int roleid = 2;

        ADDNHANVIEN add = new ADDNHANVIEN();
        if (add.isUsernameExists(username)) {

            request.setAttribute("name", name);
            request.setAttribute("number", number);
            request.setAttribute("email", email);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("error", "Tên tài khoản đã tồn tại. Vui lòng chọn tên khác.");
            request.getRequestDispatcher("addnhanvien.jsp").forward(request, response);
            return;
        }
        boolean t = add.getNhanVien(name, number, email, roleid, username, password);
        if (t) {
            request.setAttribute("name", name);
            request.setAttribute("number", number);
            request.setAttribute("email", email);
            request.setAttribute("username", username);
            request.setAttribute("password", password);

            request.setAttribute("success", "Tuyển Nhân Viên Thành Công!");
            response.sendRedirect("admin");
        } else {
            request.setAttribute("name", name);
            request.setAttribute("number", number);
            request.setAttribute("email", email);
            request.setAttribute("username", username);
            request.setAttribute("password", password);

            request.setAttribute("error", "Không Thể Tuyển");
            request.getRequestDispatcher("addnhanvien.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
