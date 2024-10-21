<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Employee Registration</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #eef2f3;
            margin: 0;
            padding: 40px;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            max-width: 600px; /* Set a reasonable max-width */
            margin: 0 auto;
            border-collapse: collapse;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
        }

        td {
            padding: 12px;
            border: 1px solid #ddd;
            background-color: #f9f9f9;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }

        input[type="text"] {
            width: calc(100% - 20px); /* Adjusting width to account for padding */
            padding: 10px; /* Consistent padding */
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            transition: border 0.3s;
            box-sizing: border-box; /* Ensures padding is included in width */
        }

        input[type="text"]:focus {
            border: 1px solid #5cb85c;
            outline: none;
        }

        input[type="submit"] {
            background-color: #5cb85c;
            color: white;
            border: none;
            padding: 10px;
            width: 100%;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #4cae4c;
        }

        p {
            text-align: center;
            margin-top: 20px;
        }

        a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
            color: #0056b3;
        }

        /* Responsive design */
        @media (max-width: 600px) {
            table {
                width: 90%;
            }
        }
    </style>
    <script>
        // Function to display the alert message
        function showMessage(message) {
            if (message) {
                alert(message);
            }
        }
    </script>
</head>
<body>

<form method="POST" action="employee">
    <table>
        <tr>
            <td colspan="2"><h1>Employee Registration</h1></td>
        </tr>
        <tr>
            <td><label for="empid">Employee ID</label></td>
            <td><input type="text" name="empid" id="empid" required></td>
        </tr>
        <tr>
            <td><label for="fname">First Name</label></td>
            <td><input type="text" name="fname" id="fname" required></td>
        </tr>
        <tr>
            <td><label for="lname">Last Name</label></td>
            <td><input type="text" name="lname" id="lname" required></td>
        </tr>
        <tr>
            <td><label for="designation">Designation</label></td>
            <td><input type="text" name="designation" id="designation" required></td>
        </tr>
        <tr>
            <td><label for="address">Address</label></td>
            <td><input type="text" name="address" id="address" required></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Submit"></td>
        </tr>
    </table>
</form>

<p><a href="viewemployee">View Employee</a></p>

<%
    String message = request.getParameter("message");
    if (message != null) {
%>
    <script>
        // Call the function to show the message in a popup
        showMessage("<%= message %>");
    </script>
<% 
    } 
%>

</body>
</html>
