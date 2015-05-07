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
        <form action="./loginDropbox" method="POST">
            <table class="table table-striped">
                <th>Name</th>
                <th>Edit</th>
                <th>Delete</th>
                <th>Export to Dropbox</th>
                    <c:forEach items="${files}" var="file">
                    <tr>
                        <td>${file.name}</td>
                        <td><a role="button" class="btn btn-default btn-xs" href="file?id=${file.id}">edit</a></td>
                        <td><a role="button" class="btn btn-danger btn-xs" href="deletefile?id=${file.id}">delete</a></td>
                        <td><button class="btn btn-default btn-xs" name="export" value="${file.id}" type="submit">Export to Dropbox</button></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
        <br>
        <a role="button" class="btn btn-primary" href="newfile">New file</a>
    </div>
</t:template>
