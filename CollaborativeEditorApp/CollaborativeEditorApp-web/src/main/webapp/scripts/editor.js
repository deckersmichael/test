/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    var APIURI = "http://127.0.0.1:8080/CollaborativeEditorApp-web/resources/";
    var editor = ace.edit("editor");
    var shadowContent = editor.getValue();
    editor.setTheme("ace/theme/monokai");
    editor.getSession().setMode("ace/mode/plain_text");
    var changes = [];
    editor.$blockScrolling = Infinity;
    var typing = true;
    var Range = require('ace/range').Range;
    
    editor.getSession().on('change', function(e) {
        if (typing === true){
            setNotificationValue('Unsaved changes...');
            if (e.data.action === "insertText"){
                changes.push(["insert", e.data.text, [e.data.range.start.column, e.data.range.start.row], [e.data.range.end.column, e.data.range.end.row]]);
            } else {
                changes.push(["delete", [e.data.range.start.column, e.data.range.start.row], [e.data.range.end.column, e.data.range.end.row]]);
            }
        }
    });

    var setNotificationValue = function (value) {
        var notifId = "#editor-notification";
        $(notifId).text("(" + value + ")");
    };

    var updateSuccess = function (msg, content) {
        setNotificationValue(msg);
        var array = JSON.parse(content);
        for (var i = 0; i < array.length; i++) {
            var cur = array[i];
            //console.log(cur);
            typing = false;
            if (cur[0] === "clear"){
                editor.setValue("");
            }
            else if (cur[0] === "addition"){
                editor.session.insert({row:cur[2][0], column: cur[2][1]}, cur[1]);
            } else {
                //editor.moveCursorTo(cur[1][0], cur[1][1]);
                //editor.selection.setSelectionRange(new Range(cur[1][0], cur[1][1], cur[2][0], cur[2][1]));
                editor.session.replace(new Range(cur[1][0], cur[1][1], cur[2][0], cur[2][1]), "");
            }
            typing = true;
        }
    };

    var sendUpdate = function (passvalue) {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: APIURI + "files/update",
            data: JSON.stringify(passvalue),
            success: function (data) {
                updateSuccess("Saved" , data);
            },
            error: function(data,status,er) {
                console.log(data + " "+ status + " " + er);
            },
            mediaType: "application/json",
            dataType: 'text'
        });
    };

    var pauseUpdate = false;
    
    var handleUpdate = function () {
        if (pauseUpdate === false){
            var content = editor.getValue();
            var changesString = JSON.stringify(changes);
            if (content !== shadowContent) {
                var data = {fileId: Info.fileId, token: Info.token, browserID: Info.broserID, email: Info.email, content: content, changes: changesString, timeDate: ""};
                sendUpdate(data);
            } else {
                var data = {fileId: Info.fileId, token: Info.token, browserID: Info.broserID, email: Info.email, content: content, changes: "", timeDate: ""};
                sendUpdate(data);
            }
            changes = [];
        }
    };
    
    window.showHistoryText = function (date, time) {
        pauseUpdate = true;
        editor.setReadOnly(true);
        
        editor.setValue("");
        
        date.push.apply(date, time);
        
        var data = {fileId: Info.fileId, token: Info.token, browserID: Info.broserID, email: Info.email, content: "", changes: "", timeDate: JSON.stringify(date)};
        sendUpdate(data);
    }

    setInterval(handleUpdate, 2000);
});