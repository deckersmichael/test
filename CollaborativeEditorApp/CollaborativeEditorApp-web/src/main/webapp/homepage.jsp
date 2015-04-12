<%-- 
    Document   : homepage
    Created on : Apr 3, 2015, 3:05:56 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <div class="container">
        <h1>Name: ${user.firstName} ${user.lastName}</h1>
        <h2>Total file count in system: ${fileCount}</h2>
        <h2>Your files can be found <a href="files">here</a></h2>
        <p><a href="logout">logout</a></p>
    </div>
</t:template>
