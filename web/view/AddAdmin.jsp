<%-- 
    Document   : AddAdmin
    Created on : Oct 19, 2021, 9:50:08 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <script>
        function Add(username){
            window.location.href = "admin?username=" + username;
        }
    </script>
    </head>
    <body>
        <table border="1px">
            <tr>
                <td>StudentID</td>
                <td>StudentName</td>
                <td>Username</td>
                <td></td>
            </tr>
            <c:forEach items="${requestScope.students}" var="s">
                <tr>
                    <td>${s.id}</td>
                    <td>${s.name}</td>
                    <td>${s.username}</td>
                    <td>
                        <input type="button" onclick="Add(this.value);" value="${s.username}">
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
