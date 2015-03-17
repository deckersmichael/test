<%-- 
    Document   : userRegistration
    Created on : Mar 14, 2015, 3:15:52 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Registration</title>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

    </head>
    <body>
        <div class="container">
            <div class="jumbotron">
                <h1>User listing</h1>
                <p>All users are listed below.</p>
            </div>
            <form action="./users" method="POST">
                <table>
                    <tr>
                        <td>First name</td>
                        <td><input type="text" name="firstName" value="${user.firstName}" /></td>
                    </tr>
                    <tr>
                        <td>Last name</td>
                        <td><input type="text" name="lastName" value="${user.lastName}" /></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" value="${user.email}" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="action" class="btn btn-default" value="Add" />
                            <input type="submit" name="action" value="Edit" class="btn btn-default" />
                            <input type="submit" name="action" value="Delete" class="btn btn-default" />
                            <input type="submit" name="action" value="Search" class="btn btn-default" />
                        </td>                
                    </tr>            
                </table>
            </form>        
            <br>
            <table border="1">
                <th>First name</th>
                <th>Last name</th>
                <th>Email</th>
                    <c:forEach items="${allUsers}" var="user">
                    <tr>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                    </tr>
                </c:forEach>
            </table>  
        </div>
    </body>
</html>