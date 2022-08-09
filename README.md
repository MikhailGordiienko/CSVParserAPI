 <h1 align="center">CSVParserAPI</h1>

A server-side API that can download a .csv file in a .rar archive, parse it, 
save the data to a database, and return the data in json format
__________________________

<h4>Table of contents</h4>
<ol>
    <li>Technologies</li>
    <li>Server setup</li>
    <li>Queries</li>
</ol>

__________________________
<h4>Technologies</h4>
<ul>
    <li>Java 8.0</li>
    <li>Spring Boot</li>
    <li>Spring JPA</li>
    <li>MySQL</li>
    <li>Json</li>
    <li>Postman(For testing requests)</li>
    <li>WinRar</li>
</ul>
<b><u></u></b>

_____________________________

<h4>Server setup</h4>
<ul>
    <li>Install <b><u>"WinRar"</u></b>. Remember the path of the <b><u>"WinRar.exe"</u></b></li>
    <li>In file <b><u>src/main/resources/pathToWinRarExe.txt</u></b>, delete all text. 
Then write in it the full path to the file <b><u>WinRar.exe</u></b></li>
    <li>In file <b><u>src/main/resources/application.properties</u></b>, write the server settings
(IP,Port,AdminName,AdminPassword)</li>
    <li>Сreate a MySQL database named <b><u>"csvparserapi"</u></b></li>
    <li>API can be run</li>
</ul>

____________________
<h4>Queries</h4>
<ol>
    <h4>POST</h4>
    <li>http://localhost:8080/institutions/update?linkForDownloading=https://drive.google.com/u/0/uc?id=1EVDMMafPfDSVG03DqdLLs4W7A60Yi8fm%26export=download
<ul>
    <li><h5>Where</h5></li>
    <li>body of the query - "http://localhost:8080/institutions/update?linkForDownloading="</li>
    <li>localhost - your IP</li>
    <li>8080 - number of your Port</li>
    <li>"linkForDownloading= any link, for download a rar file(csv has to named only <b><u>"MSI-master.csv"!!!</u></b>)</li>
    <li>This request tells the server to download the file, unzip it, read the attached .csv file, and parse it. Enter all read entities into the database, and delete downloaded files.</li>
</ul>
    <h4>GET</h4>
    <li>Equals the names. Return a list of entities in json format</li>
    <li>http://localhost:8080/institutions/getAll</li>
    <li>http://localhost:8080/institutions/getAllSortByName</li>
    <li>http://localhost:8080/institutions/getAllSortByPhoneNumber</li>
    <li>http://localhost:8080/institutions/getAllSortByState</li> 
    <li>http://localhost:8080/institutions/getAllSortByInstitution</li>
    <li></li>
</ol>
