<%-- 
    Document   : homepage
    Created on : Apr 3, 2015, 3:05:56 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <h1>Name: ${user.firstName} ${user.lastName}</h1>
        <h2>Total file count in system: ${fileCount}</h2>
        <br>
        <h2>Your files can be found <a href="files">here</a></h2>
    </body>
</html>
