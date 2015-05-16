<%-- 
    Document   : registration
    Created on : Apr 11, 2015, 11:03:51 AM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link href="styles/signup.css" rel="stylesheet">
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <title>Sign up</title>
    </head>
    <body>
        <div class="container">
            <form class="form-signup" action="./registration" method="POST">
                <h2 class="form-signup-heading">Sign up</h2>
                <label for="inputFN" class="sr-only">First name</label>
                <input type="text" name="firstName" id="inputFN" class="form-control" placeholder="First name" required autofocus>
                <br>
                <label for="inputLN" class="sr-only">Last name</label>
                <input type="text" name="lastName" id="inputLN" class="form-control" placeholder="Last name" required>
                <br>
                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required>
                <br>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
            </form>
            <h4 class="text-center"><c:out value="${message}"/></h4>
            <c:remove var="message" scope="session"/>
        </div>
    </body>
</html>
