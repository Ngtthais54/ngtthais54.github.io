<%-- 
    Document   : AdminHome
    Created on : Oct 18, 2021, 10:48:32 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="Model.Account"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <style>
            header{
                background-color: #FF7F50;
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
                background-color: #FF7F50;
            }
            .left{
                background-color: #DDDDDD;
                margin-top: 10px;
                margin-bottom: 10px;
                border: 1px solid black;
                border-radius: 5px 5px 5px 5px;
                
                padding-right: 140px;
                height: 400px;
                text-align: center;
                font-size: 30px;
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
        </style>
        <script>
           
        </script>
    </head>
    <body>
        <header>
            <h1>Welcome to DOM system</h1>
            <h3>Admin</h3>
        </header>
        <div class="container">
            <ul>
                <li><a>DOM INFORMATION</a></li>
                <li><a href="logout">Logout</a></li>
                <li><a  href="">Hello ${sessionScope.acc.username}</a>
                <li><a href="about">About</a></li>    
                <li><a class="active" href="adminhome">Home</a></li>
            </ul>
            <section class = "left">
                <h2>FUNCTION</h2>

                <button><a href="addadmin">Add admin</a></button><br>
                <button><a href="viewbookbed">View Book Bed Request of Student</a></button><br>
                <button><a href="admindomdetail">Dom detail</a></button><br>
                <button><a href="viewrequest">View Request Of Students</a></button><br>

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
