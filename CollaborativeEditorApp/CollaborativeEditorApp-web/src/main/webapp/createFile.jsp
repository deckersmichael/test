<%-- 
    Document   : createFile
    Created on : Apr 11, 2015, 3:01:01 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new file</title>
    </head>
    <body>
        <div class="container">
            <div class="jumbotron">
                <h1>File creation</h1>
                <p>Create new file.</p>
            </div>
            <form action="./newfile" method="POST">
                <table>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="name" /></td>
                    </tr>
                    <tr>
                        <td>Title</td>
                        <td><input type="text" name="title" /></td>
                    </tr>
                    <tr>
                        <td>content</td>
                        <td><input type="text" name="content" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="action" class="btn btn-default" value="Create" />
                        </td>                
                    </tr>            
                </table>
            </form>        
        </div>
    </body>
</html>
