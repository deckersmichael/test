<%-- 
    Document   : login
    Created on : Apr 11, 2015, 12:51:46 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Login</title>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

    </head>
    <body>
        <div class="container">
            <div class="jumbotron">
                <h1>User login</h1>
                <p>You can login by giving email and password.</p>
            </div>
            <form action="./login" method="POST">
                <table>
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
                            <input type="submit" name="action" class="btn btn-default" value="Login" />
                        </td>                
                    </tr>            
                </table>
            </form>        
        </div>
    </body>
</html>