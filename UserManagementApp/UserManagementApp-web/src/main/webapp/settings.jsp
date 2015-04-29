<%-- 
    Document   : settings
    Created on : Apr 12, 2015, 4:50:34 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
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
                <label for="inputFN" class="sr-only">First name</label>
                <input type="text" name="firstName" id="inputFN" class="form-control" placeholder="First name" value="${user.firstName}" required autofocus>
                <br>
                <label for="inputLN" class="sr-only">Last name</label>
                <input type="text" name="lastName" id="inputLN" class="form-control" placeholder="Last name" value="${user.lastName}" required>
                <br>
                <label for="newPassword" class="sr-only">New password</label>
                <input type="password" name="New Password" id="newPassword" class="form-control" placeholder="newPassword">
                <br>
                <label for="confirmPassword" class="sr-only">Confirm new password</label>
                <input type="password" name="Confirm Password" id="confirmPassword" class="form-control" placeholder="confirmPassword">
                <br>
                <label for="currentPassword" class="sr-only">Enter current password (to confirm)</label>
                <input type="password" name="Current Password" id="currentPassword" class="form-control" placeholder="currentPassword" required>
                <br>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit changes</button>
            </form>
        </div>
    </body>
</t:template>