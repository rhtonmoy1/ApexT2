/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rhton
 */


@WebServlet("/Delete")
public class Delete extends HttpServlet {
    
    Connection con;
    PreparedStatement pst;
    int row;

    public void doGet(HttpServletRequest req, HttpServletResponse rsp) throws IOException, ServletException {
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();

        String empid = req.getParameter("id");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/apex_crud", "root", "");
            pst = con.prepareStatement("DELETE FROM employee WHERE id = ?");
            pst.setString(1, empid);
            row = pst.executeUpdate();

            // Check if the deletion was successful and set a message
            String message;
            if (row > 0) {
                message = "Record deleted successfully!";
            } else {
                message = "Record deletion failed!";
            }

            // Store message in request scope for redirection
            req.setAttribute("message", message);
            rsp.sendRedirect("viewemployee");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
            out.println("<font color='red'>Database Driver Not Found</font>");
        } catch (SQLException ex) {
            out.println("<font color='red'>Record deletion failed: " + ex.getMessage() + "</font>");
        } finally {
            // Close resources
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ignore) {}
        }
    }
}