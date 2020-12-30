# London Subway Navigator

Introduction
 - This program was developed in the Fall of 2020 in collaboration with other students in class at the University of Wisconsin-Madison.
 - The purpose of this program is to simulate a modern GPS app at a high level, such as this one:
 https://www.rome2rio.com/map/West-Ruislip-Station/Epping-Station-England
 - This project was developed almost entirely using Java based programming (including JUnit testing) with the exception of Make (which was used for construction).
 
How it Works
 - The program uses a simple loader file to harvest data from CSV file (which can be found within this repo) containing all of the stations and connections between stations in the London "tube."
 - From the loader, stations are added as vertices and connections between stations are added as edges (the weight of each edge is the travel time between stations).
 - For the navigation function, we implemented Dijkstra's algorithm for the shortest path which accurately finds the shortest path between any two stations, providing a list of stations which are passed and the estimated travel time to the user.

How to Use
 - The London Subway Navigator is fully executable within Linux via Make, and operates within the command line. Simply type "make" into the command line (while being within the project directory) to run the program.
 - As long as you are operating within the project directory using a Linux machine, the program should launch shortly after the Java files are compiled, and JUnit tests are run.
 - Please note that these instructions assume that the user already has Make installed.

 
