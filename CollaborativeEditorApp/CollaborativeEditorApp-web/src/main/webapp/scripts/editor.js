/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    var APIURI = "http://localhost:8080/CollaborativeEditorApp-web/resources/";
    var editor = ace.edit("editor");
    var shadowContent = editor.getValue();
    editor.setTheme("ace/theme/github");
    editor.getSession().setMode("ace/mode/plain_text");

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
        if (content !== shadowContent) {
            var fileId = $("#fileId").val();
            var userId = $("#userId").val(); // This will be probably replaced by a token
            var data = {fileId: fileId, userId: userId, content: content};
            sendUpdate(data);
        }
    };

    setInterval(handleUpdate, 5000);
});