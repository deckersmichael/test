<%-- 
    Document   : login
    Created on : Apr 9, 2015, 9:22:51 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
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
</t:template>>
