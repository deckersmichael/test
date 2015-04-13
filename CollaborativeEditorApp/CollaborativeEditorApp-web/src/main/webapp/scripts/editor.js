/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var APIURI = "http://localhost:8080/CollaborativeEditorApp-web/resources/";
var editor = ace.edit("editor");
editor.setTheme("ace/theme/github");
editor.getSession().setMode("ace/mode/plain_text");

editor.getSession().on('change', function (e) {
    var fileId = $("#fileId").val();
    var userId = $("#userId").val(); // This will be probably replaced by a token
    sendUpdate(fileId, userId, editor.getValue());
});

var sendUpdate = function(fileId, userId, content) {
    var data = {fileId: fileId, userId: userId, content: content};
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: APIURI + "files/update",
        data: JSON.stringify(data),
        success: console.log("POST success"),
        mediaType: "application/json",
        dataType: "json"
    });
};