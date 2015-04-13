<%-- 
    Document   : editFile
    Created on : Apr 11, 2015, 4:48:17 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <form action="./file" method="POST" class="form-horizontal">
        <div class ="editor-header">
            ${file.name}
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
        <input type="hidden" name="id" value="${file.id}">
        <input type="hidden" name="content" id="inputContent" value="${file.content}">
        <div id="editor">${file.content}</div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.1.9/ace.js" type="text/javascript" charset="utf-8"></script>
        <script>
            var editor = ace.edit("editor");
            editor.setTheme("ace/theme/github");
            editor.getSession().setMode("ace/mode/plain_text");
            editor.getSession().on('change', function(e) {
                $("#inputContent").val(editor.getValue());
            });
        </script>
    </form>
</t:template>
