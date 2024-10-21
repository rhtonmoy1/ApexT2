<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Print Employee List</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #4CAF50; color: white; }
    </style>
    <script>
        window.onload = function() {
            window.print();
            setTimeout(function() {
                window.location.href = 'viewemployee'; // Redirect after printing
            }, 1000); // Adjust the delay as necessary
        };
    </script>
</head>
<body>
    <h1>Employee List</h1>
    <table>
        <tr>
            <th>EmpID</th>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>Address</th>
            <th>Designation</th>
        </tr>
        <%
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/apex_crud", "root", "");
                stmt = con.createStatement();
                String sql = "SELECT * FROM employee";
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getString("eid") + "</td>");
                    out.println("<td>" + rs.getString("fname") + "</td>");
                    out.println("<td>" + rs.getString("lname") + "</td>");
                    out.println("<td>" + rs.getString("address") + "</td>");
                    out.println("<td>" + rs.getString("designation") + "</td>");
                    out.println("</tr>");
                }
            } catch (Exception e) {
                out.println("<tr><td colspan='5'>Error fetching data</td></tr>");
            } finally {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            }
        %>
    </table>
</body>
</html>
