/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rhton
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



@WebServlet("/employee")
public class Employee extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse rsp) throws IOException, ServletException {
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();
        Connection con = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/apex_crud", "root", "");

            String empid = req.getParameter("empid");
            String empfname = req.getParameter("fname");
            String emplname = req.getParameter("lname");
            String empaddress = req.getParameter("address");
            String empdesignation = req.getParameter("designation");

            pst = con.prepareStatement("INSERT INTO employee (eid, fname, lname, address, designation) VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, empid);
            pst.setString(2, empfname);
            pst.setString(3, emplname);
            pst.setString(4, empaddress);
            pst.setString(5, empdesignation);
            int row = pst.executeUpdate();

            // Redirect to the root with a success or error message
            if (row > 0) {
                rsp.sendRedirect(req.getContextPath() + "/?message=Record Added Successfully");
            } else {
                rsp.sendRedirect(req.getContextPath() + "/?message=Failed to Add Record");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            rsp.sendRedirect(req.getContextPath() + "/?message=Database Driver Not Found");
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            rsp.sendRedirect(req.getContextPath() + "/?message=Error Executing SQL Query");
        } finally {
            // Close resources
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, "Error closing resources", e);
            }
        }
    }
}
