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
        <style>
            header{
                background-color: #99FFFF;
                display: block;
                padding-bottom: 5px;
                text-align: center;
            }
            .left{
                float: left;
                background-color: #DDDDDD;
                margin-top: 10px;
                margin-bottom: 10px;
                border: 1px solid black;
                border-radius: 5px 5px 5px 5px;
                width: 44%;
                padding-right: 140px;
                height: 400px;
                text-align: center;
                font-size: 19px;
            }
            .left table{
                width: 700px;
                height: 100px;
                text-align: center;
            }
            .right table{
                width: 600px;
                height: 100px;
                text-align: center;
            }
            .right{
                float: right;
                width: 44%;
                background-color: #DDDDDD;
                margin-top: 10px;
                margin-bottom: 10px;
                height: 400px;
                border: 1px solid black;
                border-radius: 5px 5px 5px 5px;
                text-align: center;
            }
            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #333;
            }

            li {
                float: left;
            }

            li a {
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }

            li a:hover {
                background-color: #111;
            }
            li{
                float:right;
            }
            li:first-child{
                float:left;
            }
            footer{
                clear:both;
                text-align: center;
                background-color: #99FFFF;
            }
        </style>
        <script>
            function Add(username) {
                window.location.href = "admin?username=" + username;
            }

            function Delete(username) {
                var c = "Are you sure?";
                if (c) {
                    window.location.href = "deleteadmin?username=" + username;
                }
            }
        </script>
    </head>
    <body>
        <header>
            <h1>Add Admin System</h1>
        </header>

        <div class="container">
            <ul>
                <li><a>DOM INFORMATION</a></li>
                <li><a href="logout">Logout</a></li>
                <li><a  href="perinfo">Hello ${sessionScope.acc.username}</a>
                <li><a href="about">About</a></li>    
                <li><a class="active" href="adminhome">Home</a></li>
            </ul>
            <section class = "left">
                <h1>List of Students</h1>
                <table border="1px">
                    <tr>
                        <td>StudentID</td>
                        <td>StudentName</td>
                        <td>Username</td>
                        <td>Add</td>
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
            </section>
            <section class="right">
                <h1>List of Admins</h1>
                <table border="1px">
                    <tr>
                        <td>AdminID</td>
                        <td>AdminName</td>
                        <td>Username</td>
                        <td>Delete</td>
                    </tr>
                    <c:forEach items="${requestScope.admins}" var="admin">
                        <tr>
                            <td>${admin.id}</td>
                            <td>${admin.name}</td>
                            <td>${admin.username}</td>
                            <td>
                                <input type="button" onclick="Delete(this.value);" value="${admin.username}">
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </section>
        </div>
        <footer>
            <h3>Information Contact</h3>
            <p>Facebook: Thái Thế Nguyễn</p>
            <p>Gmail: thaithenguyen2001@gmail.com</p>
            <p>Phone: 0964040501</p>
        </footer>
    </body>
</html>
