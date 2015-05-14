<%-- 
    Document   : statistics
    Created on : May 14, 2015, 6:46:53 PM
    Author     : Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/Tabs.css">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script type="text/javascript" src="scripts/Tabs.js"></script>
        
        <title>Statistics for ${file.name}</title>
        <meta name="description" content="chart created using amCharts live editor" />
    <!-- amCharts javascript sources -->
        <script type="text/javascript" src="http://cdn.amcharts.com/lib/3/amcharts.js"></script>
        <script type="text/javascript" src="http://www.amcharts.com/lib/3/pie.js"></script>
        <script type="text/javascript" src="http://cdn.amcharts.com/lib/3/serial.js"></script>
        <script type="text/javascript" src="http://cdn.amcharts.com/lib/3/themes/light.js"></script>
        
        <script>
            window.collabJSON = ${collabJSON};
            window.changesJSON = ${changesJSON};
        </script>
        <script type="text/javascript" src="scripts/Charts/Collaboration.js"></script>
        <script type="text/javascript" src="scripts/Charts/Changes.js"></script>
    </head>
    <body>
        <div class="tabs">
            <ul class="tab-links">
                <li class="active"><a href="#tab1">Information</a></li>
                <li><a href="#tab3">Changes</a></li>
                <li><a href="#tab4">Collaboration</a></li>
            </ul>

            <div class="tab-content">
                <div id="tab1" class="tab active">
                    
                    <table cellspacing="10">
                        <tr>
                          <td><b>File name: </b></td>
                          <td>${name}</td>
                        </tr>
                        <tr>
                          <td><b>Owner name: </b></td>
                          <td>${ownername}</td>
                        </tr>
                        <tr>
                          <td><b>Owner email: </b></td>
                          <td>${owneremail}</td>
                        </tr>
                        <tr>
                          <td><b>Created on: </b></td>
                          <td>${creationdate}</td>
                        </tr>
                        <tr>
                          <td><b>Last modified: </b></td>
                          <td>${lastmodified}</td>
                        </tr>
                        <tr>
                          <td><b>Size: </b></td>
                          <td>${contentsize} (bytes)</td>
                        </tr>
                    </table>
                    
                </div>
                <div id="tab3" class="tab">   
                    <div id="changesChart" style="width: 100%; height: 400px; background-color: #FFFFFF;" ></div>
                </div>
                <div id="tab4" class="tab">
                    
                    <div id="collabChart" style="width: 100%; height: 400px; background-color: #FFFFFF;" ></div>
                </div>
            </div>
        </div>
        
    </body>            
</html>
