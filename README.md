# SC2002_CampProject
The assignment for your group will be to design and develop a: Camp Application and Management System (CAMs). CAMs is an application for staff and students to manage, view and register for camps within NTU. The application will act as a centralized hub for all staff and  students. 

User:
- All users will need to login to this hub using their user account.
- User ID will be the NTU network user ID, that is the part before @ in email 
address.

o Assume all users use the default password, which is password. 

o A user can change password in the system. 

o A user will have faculty information. E.g, EEE, SCSE. 

• A student list and staff list can be initiated through files uploaded into the system at 
initialization. The sample student file and staff file are provided. 


Staff:

• A staff will be able to create, edit and delete camps.

• A staff can toggle the visibility of the camp to be “on” or “off”. This will be reflected 
in the camp list that will be visible to students. 

• A staff can view all camps.

• A staff can see list of camps that his/her created in a separate menu list so they can 
edit the camps they created. 

• A staff can view and reply to enquiries from students to the camp(s) his/her has 
created.

• A staff can view and approve suggestions to changes to camp details from camp 
committee.

• A staff can generate a report of the list of students attending each camp that his/her
has created. The list will include details of the camp as well as the roles of the 
participants. There should be filters for how the staff would want to generate the list. 
(attendee, camp committee, etc.) (generate in either txt or csv format). 

• A staff can also generate a performance report of the camp committee members.


###Camp information entered by the staff will include information like:

o Camp Name

o Dates

o Registration closing date

o User group this camp is open to own school or whole NTU

o Location

o Total Slots

o Camp Committee Slots (max 10)

o Description

o Staff in charge (automatically tied to the staff who created it)


Camp

• Each camp should have Camp information as above.

• Each camp will include a list of the students that have registered for it as camp

attendees and camp committee. 

o Staff and camp committee members will be able to access this list.


Student:

• A student can only view the list of camps that are open to his/her user group (SCSE, 
whole NTU etc.) and if their visibility has been toggled “on”.

• A student can view the remaining slots of each camp that is open to his/her.

• A student will be able to register for camps either as a camp attendee or camp 
committee.

• A student can submit enquiries regarding a camp. 

o Only staff and camp committees in charge of that camp can view it.

• A student can view, edit, and delete their enquiries before it is processed.

• The status of the student as a camp committee will be reflected in their profile.

• A student is only able to be in the camp committee for one camp but can attend 
multiple camps.

• A student is not allowed to register for multiple camps if there are clashes in the 
dates. 

• A student only can register a camp before it is full.

• A student only can register a camp before it’s registration deadline.

• A student can see the camps that his/her has already registered for and his/her roles 
(attendees OR camp committee)

• A student is allowed to withdraw from camps that his/her has already registered for. 
The remaining slot will be updated automatically. But the student is not allowed to 
register the same camp again.


###Camp committee member

• A camp committee member can view the details of the camp that he/she has
registered for.

• A camp committee member can submit suggestions for changes to camp details to 
staff.

• A camp committee member is not allowed to directly edit the camp details.

• A camp committee member can view and reply to enquiries from students to the 
camp they oversee. 

• A camp committee member can view, edit, and delete the details of his/her 
suggestions before being processed. 

• A camp committee member can generate a report of the list of students attending 
each camp that they have created. The list will include details of the camp as well as 
the roles of the participants. There should be filters for how the camp committee 
member would want to generate the list. (attendee, camp committee, etc.) (generate in 
either txt or csv format). 

• A camp committee member can get one point for each enquiry replied and each 
suggestion given. One extra point will be granted for each accepted suggestion. 

• A camp committee member cannot quit from camp. 


###Assumption:

o All users can use filters to view the camp list (date, location etc.) Assume that 
default is by alphabetical order. 

o We assume that registration of camp and camp committee is automatic as long 
as there is vacancy.

o We assume that the number of camp committee is counted into total slots.  
