<%-- 
    Document   : editFile
    Created on : Apr 11, 2015, 4:48:17 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <form action="./file" method="POST" class="form-horizontal">
        <legend>${file.name}</legend>
        <input type="hidden" name="id" value="${file.id}">
        <textarea name="content" class="form-control" rows="20">${file.content}</textarea>
        <br>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</t:template>
