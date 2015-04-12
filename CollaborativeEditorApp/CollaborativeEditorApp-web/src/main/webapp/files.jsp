<%-- 
    Document   : files
    Created on : Apr 11, 2015, 9:06:12 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <div class="container">
        <h1>Your files:</h1>
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
</t:template>
