<%-- 
    Document   : chat
    Created on : May 14, 2015, 11:37:50 AM
    Author     : Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:chat>
    <div class="container">
        <div id='chatArea' style='border:1px solid #aaa; width:480px; height:400px; overflow:auto;'></div>
        <input style='width:415px;' id='chatLine' type='text' value=''>  
        <button style='width:60px;'onclick='sendText()'>Send</button>  
    </div>
    <script>
        var Info = {
            fileId: "${fid}",
            token: "${session.token}"
        };
        
        document.getElementById("chatLine").addEventListener("keydown", function (e) {
            if (e.keyCode === 13) {
                sendText();
            }
        });
    </script>
    <script src="scripts/chat.js"></script>
</t:chat>

