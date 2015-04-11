<%-- 
    Document   : registration
    Created on : Apr 11, 2015, 11:03:51 AM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Registration</title>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

    </head>
    <body>
        <div class="container">
            <div class="jumbotron">
                <h1>User registration</h1>
                <p>You can create an user by filling the form below.</p>
            </div>
            <form action="./registration" method="POST">
                <table>
                    <tr>
                        <td>First name</td>
                        <td><input type="text" name="firstName" /></td>
                    </tr>
                    <tr>
                        <td>Last name</td>
                        <td><input type="text" name="lastName" /></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" /></td>
                    </tr>
                                        <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="action" class="btn btn-default" value="Register" />
                        </td>                
                    </tr>            
                </table>
            </form>        
        </div>
    </body>
</html>