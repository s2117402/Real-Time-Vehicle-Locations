# Real-Time-Vehicle-Locations

---

###Breif Introduction
we use two terminal to achive our tracking vehicle location purpose.
One is the mobile terminal,we develop a android application and use the GPS sensor to collect the
location information and send it to the server by socket.We use the java socket and web socket to build
the connection for transmitting information.
The other terminal is the server terminal,we use js to finish this part,when web socket of our js project
accept the location from our android app,it will process it and display it on the google map(we use google map api).

---
####PCClient folder
In the PCClient folder,this is the android application.It in charge of collecting location data and transmitting it 
to our js project which we deploy in server.The app will collect the GPS locations per fixed time,and send it to the server
by socket connection.(we need to set sepcific server IP before we use)

---
####GPSServer folder
In the GPSServer folder,that's a JS project we use to accept data from android application,and display the locations on
google map and form them as a track.

----
###Future Work
we only achive the basic functionality of requirement,and we need restart the web server each time,and the server only can
process the information from one mobile terminal.GUI is too rough.The most important problem is we need to set a begining
point each time we start our js project in server.

---
####Team members
This is a team project,I'm really grateful to my partner Kazi Amanul Islam Siddiqui,he contributed a lot to our project.
By the way,We learned much from some other projects,we refered much useful from internet,we really appreciate it,I'm sorry I
can't remerber them and list them here.

