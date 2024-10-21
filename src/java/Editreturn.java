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
import java.sql.ResultSet;
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




@WebServlet("/Editreturn")
public class Editreturn extends HttpServlet {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void doGet(HttpServletRequest req, HttpServletResponse rsp) throws IOException, ServletException {
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();

        String id = req.getParameter("id");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/apex_crud", "root", "");
            pst = con.prepareStatement("SELECT * FROM employee WHERE id = ?"); // Change to eid
            pst.setString(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                // HTML output starts here
                out.println("<!DOCTYPE html>");
                out.println("<html lang='en'>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<title>Edit Employee</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }");
                out.println("h1 { text-align: center; color: #333; }");
                out.println("form { max-width: 500px; margin: 0 auto; background: white; padding: 20px; border-radius: 5px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); }");
                out.println("table { width: 100%; }");
                out.println("td { padding: 10px; }");
                out.println("input[type='text'] { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; }");
                out.println("input[type='submit'] { background-color: #007bff; color: white; border: none; padding: 10px; border-radius: 4px; cursor: pointer; }");
                out.println("input[type='submit']:hover { background-color: #0056b3; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Edit Employee</h1>");
                out.println("<form action='EditServlet' method='POST'>");
                out.println("<table>");
                out.println("<input type='hidden' name='id' value='" + id + "' required />");
                out.println("<tr><td>EmpID</td><td><input type='text' name='empid' id='eid' value='" + rs.getString("eid") + "' required /></td></tr>");
                out.println("<tr><td>Firstname</td><td><input type='text' name='fname' id='fname' value='" + rs.getString("fname") + "' required /></td></tr>");
                out.println("<tr><td>Lastname</td><td><input type='text' name='lname' id='lname' value='" + rs.getString("lname") + "' required /></td></tr>");
                out.println("<tr><td>Address</td><td><input type='text' name='address' id='address' value='" + rs.getString("address") + "' required /></td></tr>"); // Added Address
                out.println("<tr><td>Designation</td><td><input type='text' name='designation' id='designation' value='" + rs.getString("designation") + "' required /></td></tr>"); // Added Designation
                out.println("<tr><td colspan='2'><input type='submit' value='Update' /></td></tr>");
                out.println("</table>");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<h3>No employee found with ID: " + id + "</h3>");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Editreturn.class.getName()).log(Level.SEVERE, null, ex);
            out.println("<div style='color: red;'>Database Driver Not Found</div>");
        } catch (SQLException ex) {
            out.println("<div style='color: red;'>Record retrieval failed: " + ex.getMessage() + "</div>");
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ignore) {}
        }
    }
}
