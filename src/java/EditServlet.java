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




@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {

    Connection con;
    PreparedStatement pst;
    int row;

    public void doPost(HttpServletRequest req, HttpServletResponse rsp) throws IOException, ServletException {
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/apex_crud", "root", "");

            String id = req.getParameter("id"); // The id used to identify the record
            String empid = req.getParameter("empid"); // The new employee ID
            String fname = req.getParameter("fname");
            String lname = req.getParameter("lname");
            String address = req.getParameter("address"); 
            String designation = req.getParameter("designation"); 

            // Updated SQL query to include address and designation
            pst = con.prepareStatement("UPDATE employee SET eid = ?, fname = ?, lname = ?, address = ?, designation = ? WHERE id = ?");
            pst.setString(1, empid); // Set new employee ID
            pst.setString(2, fname);
            pst.setString(3, lname);
            pst.setString(4, address);
            pst.setString(5, designation);
            pst.setString(6, id); // Use the id to identify which record to update
            row = pst.executeUpdate();

            // HTML output starts here
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Update Employee</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }");
            out.println("h1 { text-align: center; color: #333; }");
            out.println(".message { text-align: center; padding: 20px; border-radius: 5px; }");
            out.println(".success { background-color: #d4edda; color: #155724; }");
            out.println(".error { background-color: #f8d7da; color: #721c24; }");
            out.println("a { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 5px; }");
            out.println("a:hover { background-color: #0056b3; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            if (row > 0) {
                out.println("<div class='message success'>Record updated successfully!</div>");
            } else {
                out.println("<div class='message error'>Record update failed!</div>");
            }
            out.println("<a href='viewemployee'>Back to Employee List</a>");
            out.println("</body>");
            out.println("</html>");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditServlet.class.getName()).log(Level.SEVERE, null, ex);
            out.println("<div class='message error'>Database Driver Not Found</div>");
        } catch (SQLException ex) {
            out.println("<div class='message error'>Record update failed: " + ex.getMessage() + "</div>");
        } finally {
            // Close resources
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ignore) {}
        }
    }
}
