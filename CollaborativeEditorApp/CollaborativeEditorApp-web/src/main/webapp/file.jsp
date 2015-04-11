<%-- 
    Document   : editFile
    Created on : Apr 11, 2015, 4:48:17 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit</title>
    </head>
    <body>
        <div class="container">
            <div class="jumbotron">
                <h1>Edit</h1>
            </div>
            <form action="./file" method="POST">
                <table>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="name" value="${file.name}" /></td>
                    </tr>
                    <tr>
                        <td>Title</td>
                        <td><input type="text" name="title" value="${file.title}" /></td>
                    </tr>
                    <tr>
                        <td>Content</td>
                        <td><input type="text" name="content" value="${file.content}" /></td>
                    </tr>
                    <input type="hidden" name="id" value="${file.id}" />
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="action" class="btn btn-default" value="Update" />
                        </td>                
                    </tr>            
                </table>
            </form>        
        </div>
    </body>
</html>
