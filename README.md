
# Smart Course Scheduler

Smart Course Scheduler generates course schedules. Users only need to type in the courses they are planning to take in the upcoming school years, and all valid course schedules would be output in timetable format. <br/><br/>
Technologies used in this project: Java, HTML, Jsoup, Junit testing, Constraint Networks 
<br/><br/>
## Inspirations
This application is inspired by the [constraint network model](http://www.cs.sjsu.edu/faculty/pearce/modules/patterns/events/ConstraintNetworks.htm) I learnt from an introductory AI course. I reduced the course scheduling problem into a constraint network problem. The courses are the variables, the sections are the possible values of the variables, and time conflicts are the constraints.<br/>
## Problems
This model works fine for small inputs (e.g. 2-3 courses) but soon encounters runtime problems when the number of courses grows large. Unfortunately, we failed to fix this issue, and the application can be used only as a toy prototype.
<br/><br/>
In another course we took later, we learnt that scheduling problem is NP-Hard. There exists no efficient algorithm to obtain all valid solutions. We will try to implement a heuristics algorithm to find some "good enough" timetables when we have a chance.
## Get Started
The setup is simple: Clone the code and run Main
(Note: This project scrapes UBC's course registeration website, and is up-to-date as of February 2020. It may no longer work if UBC changed the structure of its website.)
## Overview


https://user-images.githubusercontent.com/45861466/123415615-1ec09a80-d5e8-11eb-8cff-c624dfd8dd39.mp4

