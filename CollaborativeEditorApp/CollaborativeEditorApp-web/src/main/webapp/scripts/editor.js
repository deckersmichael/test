/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    var APIURI = "http://localhost:8080/CollaborativeEditorApp-web/resources/";
    var editor = ace.edit("editor");
    var shadowContent = editor.getValue();
    editor.setTheme("ace/theme/monokai");
    editor.getSession().setMode("ace/mode/plain_text");
    var changes = [];
    
    editor.getSession().on('change', function(e) {
        if (e.data.action === "insertText"){
            changes.push(["insert", e.data.text, [e.data.range.start.column, e.data.range.start.row], [e.data.range.end.column, e.data.range.end.row]])
        } else {
            changes.push(["delete", [e.data.range.start.column, e.data.range.start.row], [e.data.range.end.column, e.data.range.end.row]])
        }
        console.log(changes);
    });

    var setNotificationValue = function (value) {
        var notifId = "#editor-notification";
        $(notifId).text("(" + value + ")");
    };

    editor.getSession().on('change', function (e) {
        setNotificationValue("Unsaved changes");
    });

    var updateSuccess = function (msg, content) {
        setNotificationValue(msg);
        shadowContent = content;
    };

    var sendUpdate = function (data) {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: APIURI + "files/update",
            data: JSON.stringify(data),
            success: updateSuccess("Saved", data.content),
            mediaType: "application/json",
            dataType: "json"
        });
    };

    var handleUpdate = function () {
        var content = editor.getValue();
        var changesString = JSON.stringify(changes);
        if (content !== shadowContent) {
            var data = {fileId: Info.fileId, token: Info.token, email: Info.email, content: content, changes: changesString};
            sendUpdate(data);
        }
        changes = [];
    };

    setInterval(handleUpdate, 1000);
});