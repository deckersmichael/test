/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {
    var APIURI = "http://127.0.0.1:8080/CollaborativeEditorApp-web/resources/";
    var time = 0;



    var updateSuccess = function (content) {
        var array = JSON.parse(content);
        for (var i = 0; i < array.length; i++) {
            var cur = array[i];
            var content = cur[0];
            var sender = cur[1];
            var time = cur[2];
            var date = new Date(time).toLocaleString();
            
            var ca = document.getElementById('chatArea');  
            var p = document.createElement( "div" );  
            if(sender!=null){  
              var span = document.createElement( "span" );  
              span.innerHTML = sender + ' (<i>' + date + '</i>):';  
              p.appendChild(span);  
            }  
            var htmlText = document.createElement ("span");  
            htmlText.innerHTML = "<br><b>&nbsp;&nbsp" + content + "</b>";  
            p.appendChild (htmlText);  
            ca.appendChild(p);  
            ca.scrollTop = 50000;  
        }
    }

    var sendUpdate = function (passvalue) {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: APIURI + "chats/update",
            data: JSON.stringify(passvalue),
            success: function (data) {
                updateSuccess(data);
            },
            error: function(data,status,er) {
                console.log(data + " "+ status + " " + er);
            },
            mediaType: "application/json",
            dataType: 'text'
        });
    };

    window.sendText = function () {
        var data = {fileid: window.Info.fileId, senderid: window.Info.token, content: document.getElementById("chatLine").value, option: "send", time: new Date().getTime()};
        document.getElementById("chatLine").value = "";
        sendUpdate(data);
    };
    
    var handleUpdate = function() {
        if (time === 0){
            var data = {fileid: window.Info.fileId, senderid: "", content: "", option: "findforfile", time: time};
            sendUpdate(data);
        } else {
            var data = {fileid: window.Info.fileId, senderid: "", content: "", option: "findrecentforfile", time: time};
            sendUpdate(data);
        }
        time = new Date().getTime();
    };
    
    setInterval(handleUpdate, 100);
});
