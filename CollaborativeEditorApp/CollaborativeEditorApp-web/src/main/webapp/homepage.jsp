<%-- 
    Document   : homepage
    Created on : Apr 3, 2015, 3:05:56 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <h1>Name: ${user.firstName} ${user.lastName}</h1>
        <h2>File count in system: ${fileCount}</h2>
        <br>
        <h2>Your files:</h2>
        <div class="container">
            <table border="1">
                <th>Name</th>
                <th>Title</th>
                <th>Edit</th>
                <th>Delete</th>
                <c:forEach items="${files}" var="file">
                    <tr>
                        <td>${file.name}</td>
                        <td>${file.title}</td>
                        <td><a href="file?id=${file.id}">edit</a></td>
                        <td><a href="deletefile?id=${file.id}">delete</a></td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <a href="newfile">New file</a>
        </div>
    </body>
</html>
