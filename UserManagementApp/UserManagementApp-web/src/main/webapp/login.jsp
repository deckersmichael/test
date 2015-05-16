<%-- 
    Document   : login
    Created on : Apr 11, 2015, 12:51:46 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link href="styles/signin.css" rel="stylesheet">
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <title>Sign in</title>
    </head>
    <body>
        <div class="container">
            <form class="form-signin" action="./login" method="POST">
                <h2 class="form-signin-heading">Please sign in</h2>
                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="text" name="email" id="inputEmail" class="form-control" placeholder="Email address or LDAB login" required autofocus>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
                <button class="btn btn-lg btn-primary btn-block" value="standard" type="submit">Sign in</button>
                <button class="btn btn-lg btn-primary btn-block" value="LDAB" onclick="form.action='./loginLDAB';" type="submit">Sign in with LDAB</button>
            </form>
            <form class="form-signin" action="./loginTwitter" method="POST">
                <button class="btn btn-lg btn-primary btn-block" value="Twitter" type="submit">Sign in with Twitter</button>
            </form>>
            <br>
            <p class="text-center">No account yet? <a href="registration">Register now!</a></p>
            <h4 class="text-center"><c:out value="${message}"/></h4>
            <c:remove var="message" scope="session"/>
        </div>
    </body>
</html>
