<%-- 
    Document   : settings
    Created on : Apr 12, 2015, 4:50:34 PM
    Author     : matson
--%>

<%@page import="ua.group06.persistence.User"%>
<%@page import="ua.group06.persistence.ExternalUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
</t:template>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link href="styles/signup.css" rel="stylesheet">
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <title>Settings</title>
    </head>
    <body>
        <div class="container">
            <form class="form-signup" action="./settings" method="POST">
                <h2 class="form-signup-heading">Settings</h2>
                <label for="inputEmail" class="sr-only">Email</label>
                <input type="text" name="email" id="inputEmail" class="form-control" placeholder="Email" value="${user.email}" required autofocus>
                <br>
                <label for="inputFN" class="sr-only">First name</label>
                <input type="text" name="firstName" id="inputFN" class="form-control" placeholder="First name" value="${user.firstName}" required autofocus>
                <br>
                <label for="inputLN" class="sr-only">Last name</label>
                <input type="text" name="lastName" id="inputLN" class="form-control" placeholder="Last name" value="${user.lastName}" required>
                <br>
                <% 
                    if(session.getAttribute("user")instanceof User){ 
                %>
                <label for="newPassword" class="sr-only">New password</label>
                <input type="password" name="newPassword" id="newPassword" class="form-control" placeholder="New Password">
                <br>
                <label for="confirmPassword" class="sr-only">Confirm new password</label>
                <input type="password" name="confirmPassword" id="confirmPassword" class="form-control" placeholder="Confirm Password">
                <br>
                <label for="currentPassword" class="sr-only">Enter current password (to confirm)</label>
                <input type="password" name="currentPassword" id="currentPassword" class="form-control" placeholder="Current Password" required>
                <br>
                <%
                    } 
                %>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit changes</button>
                <text>
            </form>
            <h4><c:out value="${message}"/></h4>
            <c:remove var="message" scope="session"/>
        </div>
    </body>