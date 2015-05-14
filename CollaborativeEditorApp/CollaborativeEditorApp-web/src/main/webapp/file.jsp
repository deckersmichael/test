<%-- 
    Document   : editFile
    Created on : Apr 11, 2015, 4:48:17 PM
    Author     : matson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <link rel="stylesheet" media="all" type="text/css" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <link rel="stylesheet" media="all" type="text/css" href="jquery-ui-timepicker-addon.css" />
    <nav class="navbar">
            <div class="container">
                <div class="navbar-header">
                    <p class="navbar-brand">${file.name}</p>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="#" onclick="Download();">Download <small>(.txt)</small></a></li>
                        <li><a href="shares?id=${file.id}">Share</a></li>
                        <li><a href="#" onclick="OpenChat();">Chat</a></li>
                        <li><a href="#" onclick="OpenStat();">Stats</a></li>
                        <script>
                            function Download() {
                                var pom = document.createElement('a');
                                pom.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(window.editor.getValue()));
                                pom.setAttribute('download', '${file.name}' + '.txt');
                                pom.click();
                                //if (document.createEvent) {
                                //    var event = document.createEvent('MouseEvents');
                                //    event.initEvent('click', true, true);
                                //    pom.dispatchEvent(event);
                                //}
                                //else {
                                //    pom.click();
                                //}
                            }
                            function OpenChat() {
                                window.open("chat?id=${file.id}", "", "width=572, height=600");
                            }
                            
                            function OpenStat() {
                                window.open("Statistics?id=${file.id}", "", "width=800, height=600");
                            }
                        </script>
                        <li><input type="text" value="" id="basic_example_1" name="basic_example_1" /></li>
                        <li><button onclick="showHistory()">Show history</button></li>
                        <li><button onclick="revertToPrevious()">Revert to this time</button></li>
                        <li><button onclick="showCurrent()">Back to current</button></li>
                        <script>
                            var hm;
                            var mdy;
                            function showHistory () {
                                var dateString = document.getElementById("basic_example_1").value;
                                hm = dateString.split(" ")[1].split(":");
                                mdy = dateString.split(" ")[0].split("/");
                                mdy[0] = mdy[0]-1;
                                //mdy[1] = mdy[1]-10;
                                mdy[2] = mdy[2]-1900;
                                showHistoryText(mdy, hm);
                            }
                            
                        </script>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><span id="editor-notification">(Saved)</span></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
    <form action="./file" method="POST" class="form-horizontal">

        <input type="hidden" name="content" id="inputContent" value="">
        <div id="editor"></div>
        <script>
            var Info = {
                fileId: ${file.id},
                token: "${session.token}",
                browserID: "${browserID}"
            };
            
            var makeDay = "${creationDate}";
        </script>
 
        <script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.1.9/ace.js" type="text/javascript" charset="utf-8"></script>
        <script src="scripts/editor.js"></script>
        
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.min.js"></script>
	<script type="text/javascript" src="scripts/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript" src="scripts/jquery-ui-sliderAccess.js"></script>
	<script type="text/javascript" src="scripts/script.js"></script>
                
    </form>
</t:template>
