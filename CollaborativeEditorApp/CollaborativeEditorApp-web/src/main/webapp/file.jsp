<%-- 
    Document   : editFile
    Created on : Apr 11, 2015, 4:48:17 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <form action="./file" method="POST" class="form-horizontal">
        <div id="editor-header">
            ${file.name} <span id="editor-notification">(Saved)</span>
        </div>
        <input type="hidden" id="fileId" value="${file.id}">
        <input type="hidden" id="userId" value="${user.id}">
        <input type="hidden" name="content" id="inputContent" value="${file.content}">
        <div id="editor">${file.content}</div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.1.9/ace.js" type="text/javascript" charset="utf-8"></script>
        <script src="scripts/editor.js"></script>
    </form>
</t:template>
