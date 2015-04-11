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
        <h1>Your first name: ${user.firstName}</h1>
        <h1>Your last name: ${user.lastName}</h1>
        <h2>File count in system: ${fileCount}</h2>
    </body>
</html>
