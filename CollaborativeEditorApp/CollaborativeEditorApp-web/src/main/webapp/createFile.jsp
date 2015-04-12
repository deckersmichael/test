<%-- 
    Document   : createFile
    Created on : Apr 11, 2015, 3:01:01 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template alert="${alert}">
    <form action="./newfile" method="POST" class="form-horizontal">
        <legend>New file</legend>
        <label for="inputName">Name</label>
        <input type="text" name="name" id="inputName"/>
        <br>
        <button type="submit" class="btn btn-primary">Create</button>
    </form>
</t:template>
