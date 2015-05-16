<%-- 
    Document   : fileshares
    Created on : Apr 29, 2015, 10:01:53 PM
    Author     : michaeldeckers
--%>


        
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <div class="container">
        <h1>Shares for file: ${file.name}</h1>
        <table class="table table-striped">
            <th>Email</th>
            <th>Permission</th>
            <th>Delete</th>
                <c:forEach items="${file.collaborators}" var="collaborator">
                <tr>
                    <td>${collaborator}</td>
                    <td>Collaborator</td>
                    <td><form method="POST">
                        <input type="hidden" name="action" value="remove" />
                        <input type="hidden" name="name" value="${collaborator}" />
                        <input type="hidden" name="id" value="${file.id}" />
                        <input type="hidden" name="type" value="collab" />
                        <button class="btn btn-danger btn-xs" type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
                </c:forEach>
        </table>
        <br>
        <form method="POST" class="form-horizontal">
            <legend>Add share</legend>
            <label for="inputName">Email</label>
            <input type="text" name="name" id="inputName"/><br>
            <!--<input type="radio" name="type" value="collab" checked> Collaborator
            <input type="radio" name="type" value="spec"> Spectator-->
            <input type="hidden" name="action" value="add" />
            <input type="hidden" name="id" value="${file.id}" />
            <br>
            <button type="submit">Share file</button>
            <textfield id="status">
        </form>
    </div>
</t:template>