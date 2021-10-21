<%-- 
    Document   : ViewRequest
    Created on : Oct 21, 2021, 10:48:33 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            h1{
                padding-top: 50px;
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
            .left{
                float: left;
                background-color: #DDDDDD;
                margin-top: 10px;
                margin-bottom: 10px;
                border: 1px solid black;
                border-radius: 5px 5px 5px 5px;
                width: 69%;
                padding-right: 140px;
                height: 400px;
                text-align: center;
                font-size: 30px;
            }
            .right{
                float: right;
                width: 20%;
                background-color: #DDDDDD;
                margin-top: 10px;
                margin-bottom: 10px;
                height: 400px;
                border: 1px solid black;
                border-radius: 5px 5px 5px 5px;
                text-align: center;
            }
            button{
                width: 200px;
                margin-bottom: 10px;
            }
            a{
                text-decoration: none;
            }
            button{
                width: 500px;
                height: 50px;
            }
            .right .money{
                border: 1px solid black;
                width: 70%;
                margin-left: auto;
                margin-right: auto;
                margin-top: 100px;
                text-align: center;
                padding-bottom: 10px;
            }
            .right button{
                width: 50%;
                margin-top: 10px;
            }
            table{
                width: 100%;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <header>
            <h1>Student Requests</h1>
        </header>
        <div class="container">
            <ul>
                <li><a>DOM INFORMATION</a></li>
                <li><a href="logout">Logout</a></li>
                <li><a  href="perinfo">Hello ${sessionScope.acc.username}</a>
                <li><a href="about">About</a></li>    
                <li><a class="active" href="home">Home</a></li>
            </ul>
            <form action="viewrequest" method="POST">
                <table border="1px">
                    <tr>
                        <th>ID</th>
                        <th>StudentID</th>
                        <th>Title</th>
                        <th>Note</th>
                        <th>Status</th>
                    </tr>
                    <c:forEach items="${requestScope.requests}" var="r">
                        <tr>
                            <td>${r.id}</td>
                            <td>${r.student.id}</td>
                            <td>${r.title}</td>
                            <td>${r.note}</td>
                            <td>
                                <div class="custom-control custom-radio">
                                    <input type="radio" value="1" id="${r.id}" name="${r.id}" class="custom-control-input">
                                    <label class="custom-control-label" for="${r.id}">Approve</label>
                                </div>
                                <div class="custom-control custom-radio">
                                    <input type="radio" value="-1" id="${r.id}" name="${r.id}"  checked="checked" class="custom-control-input">
                                    <label class="custom-control-label" for="${r.id}">Reject</label>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <input type="submit" value="Submit">
            </form>
        </div>
        <footer>
            <h3>Information Contact</h3>
            <p>Facebook: Thái Thế Nguyễn</p>
            <p>Gmail: thaithenguyen2001@gmail.com</p>
            <p>Phone: 0964040501</p>
        </footer>
    </body>
</html>
