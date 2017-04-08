<%-- 
    Document   : validation
    Created on : Feb 21, 2017, 7:44:05 PM
    Author     : johnkmnguyen
--%>

<%@ page import ="java.sql.*" %>
<%
    try {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/morecookbook?zeroDateTimeBehavior=convertToNull", "root", "123456");
        PreparedStatement pst = conn.prepareStatement("Select userName,password from owner where username = ? and password = ?");
        pst.setString(1, userName);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            String redirectURL = "http://localhost:8080/CookBook_v7/faces/index.xhtml";
            response.sendRedirect(redirectURL);
        } else {
            // FIX THIS SHIT
            String redirectURL = "http://localhost:8080/CookBook_v7/faces/index.xhtml";
            response.sendRedirect(redirectURL);
        }
    } catch (Exception e) {
//        out.println("Error 404");
    }
%>