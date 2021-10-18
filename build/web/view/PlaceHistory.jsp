<%-- 
    Document   : PlaceHistory
    Created on : Oct 8, 2021, 12:05:07 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table{
                width: 100%;
                height:100%;
                margin-left: auto;
                margin-right: auto;
            }
        </style>
    </head>
    <body>
        <table border="1px">
            <tr>
                <td>Student ID</td>
                <td>Room</td>
                <td>Dom</td>
                <td></td>
            </tr>
            <c:forEach items="${requestScope.details}" var="d">
                <tr>
                    <td>${d.student.name}</td>
                    <td>${d.room.room_code}</td>
                    <td>${d.dom.name}</td>
                    <td><a href="historydetail?id=${d.id}">Detail</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
