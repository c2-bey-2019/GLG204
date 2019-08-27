# CNAM Attendance

## Quick Description

A Java web application that allows CNAM administration to create events such as classes, information meetings, presentations of projects etc. assign them to a Teacher, add students to these events and generate attendance reports.

The students, using a light mobile application, can see the classes and check-in using their mobile phones. The system will save the student's location along with his information for the purpose of reporting and verification.

The admin must be able to see the check-ins on a map in the application.

## Technologies Used
* JAVA EE
* JSF
* MySQL
* Netbeans IDE 8.2

## Extra Feature: CNAM People 
### (To be linked to CNAM Attendance in the future, we welcome your contributions and suggestions)

Watch the Video!

[![Watch the video](https://i9.ytimg.com/vi/l4e7GhhKLfQ/mq2.jpg?sqp=COT1lOsF&rs=AOn4CLD1CLB5ZErERXhYRdOAS87_tg6y7Q)](https://youtu.be/l4e7GhhKLfQ)

CNAM People is a Web Application that allows CNAM Administration to consolidate their employee information in one directory where they can easily add, edit, search and remove employees to always reflect the real number of people in the organization along with their current information.

CNAM People est une application Web qui permet à l’administration CNAM de regrouper les informations relatives à leurs employés dans un seul répertoire, leur permettant d’ajouter, d’éditer, de rechercher et de supprimer facilement des employés, afin de refléter en permanence le nombre réel de personnes dans l’organisation et leurs informations actuelles.

Technologies Used:
Framework: Java Server Faces (JSF) 2.0 (MVC design pattern)
IDE: Eclipse Eclipse (Oxygen) for Java EE Developers
Java JDK :1.8
Application Server: Apache Tomcat 9

Needs 1 more fine tuning related to the dashboard.

For testing purpose, use the following credentials: Username: admin/Password: admin

Currently image upload is moving the image to the images directory in the app server using java.io File features.
Currently we are initializing the list of employees from a text file using java.io File features.

Future development tasks: 
Link the sign in to a back end database.
Save the directory to a back end database including large objects for the photos.
To add the teacher created in CNAM People to CNAM Attendance and vice versa.

## Developed By (listed in an alphabetical order)
* Mazen Kabalan
* Mohamed Sabra
* Raymond Khawand


## Developers corner
* Generating SSH Key [click here](https://help.github.com/en/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent)
* To Solve the Authentication Required problem with Apache Tomcat in NetBeans IDE [click here](http://ohmjavaclasses.blogspot.com/2011/12/netbeans-problem-for-apache-tomcat.html)
* Tomcat Installation Guide (Video Tutorial) [click here](https://youtu.be/pKMgr8uNvGM)
* Adding JSF Facet to Existing Project in Eclipse Oxygen click here (https://help.eclipse.org/kepler/topic/org.eclipse.jst.jsf.doc.user/html/tasks/add_jsf_facet.html)
* How to solve ‘Starting Tomcat Server at localhost’ has encountered a problem [click here](https://www.youtube.com/watch?v=qB3ETbAU1NY)
* Install GlassFish Tools in Eclipse Oxygen [click here](https://download.eclipse.org/glassfish-tools/1.0.0/repository/)
