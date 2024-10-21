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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewemployee")
public class ViewEmployee extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse rsp) throws IOException, ServletException {
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/apex_crud", "root", "");
            stmt = con.createStatement();
            String sql = "SELECT * FROM employee";
            rs = stmt.executeQuery(sql);

            // HTML output starts here
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Employee List</title>");
            out.println("<style>");
            out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #eef2f3; margin: 0; padding: 20px; }");
            out.println("h1 { text-align: center; color: #333; margin-bottom: 20px; }");
            out.println("table { width: 100%; max-width: 800px; margin: 20px auto; border-collapse: collapse; background: white; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); }");
            out.println("th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }");
            out.println("th { background-color: #4CAF50; color: white; }");
            out.println("tr:hover { background-color: #f1f1f1; }");
            out.println("button, a { background-color: #5cb85c; color: white; border: none; padding: 10px 15px; border-radius: 4px; text-decoration: none; cursor: pointer; margin: 5px; }");
            out.println("button:hover, a:hover { background-color: #4cae4c; }");
            out.println(".container { text-align: center; margin-bottom: 20px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Employee List</h1>");
            out.println("<div class='container'>");
            out.println("<button onclick=\"window.location.href='index.jsp'\">Add Employee</button>"); // Redirects to index.jsp
            out.println("<button onclick=\"window.open('printview.jsp', '_blank')\">Print View</button>");//Print
            out.println("</div>");
            out.println("</div>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>EmpID</th>");
            out.println("<th>Firstname</th>");
            out.println("<th>Lastname</th>");
            out.println("<th>Address</th>");
            out.println("<th>Designation</th>");
            out.println("<th>Actions</th>");
            out.println("</tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("eid") + "</td>"); // Changed to eid
                out.println("<td>" + rs.getString("fname") + "</td>");
                out.println("<td>" + rs.getString("lname") + "</td>");
                out.println("<td>" + rs.getString("address") + "</td>"); // Added address
                out.println("<td>" + rs.getString("designation") + "</td>"); // Added designation
                out.println("<td>");
                out.println("<a href='Editreturn?id=" + rs.getString("id") + "'>Edit</a> ");
                out.println("<a href='Delete?id=" + rs.getString("id") + "'>Delete</a>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewEmployee.class.getName()).log(Level.SEVERE, null, ex);
            out.println("<font color='red'>Database Driver Not Found</font>");
        } catch (SQLException ex) {
            out.println("<font color='red'>Record Failed: " + ex.getMessage() + "</font>");
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ignore) {
            }
        }
    }
}
