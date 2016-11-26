BEGIN;
USE Team19_db;
/* Users*/
INSERT INTO User (Username,Password,Name,Type) VALUES ('user1','user','Bob','U');
INSERT INTO User (Username,Password,Name,Type) VALUES ('user2','user','Coast Guard','U');
INSERT INTO User (Username,Password,Name,Type) VALUES ('user3','user','Environmental Research','U');
INSERT INTO User (Username,Password,Name,Type) VALUES ('user4','user','Pounce City Hall','U');

INSERT INTO User (Username,Password,Name,Type) VALUES ('user5','user','Jim','U');
INSERT INTO User (Username,Password,Name,Type) VALUES ('user6','user','Access Board','U');
INSERT INTO User (Username,Password,Name,Type) VALUES ('user7','user','Environmental Labs','U');
INSERT INTO User (Username,Password,Name,Type) VALUES ('user8','user','Virginia Beach City Hall','U');

/* Individual*/
INSERT INTO Individual (Username,JobTitle,DateHired) VALUES ('user1',' Environmental Engineer','2013-08-08');
INSERT INTO Individual (Username,JobTitle,DateHired) VALUES ('user5','Resource Manger','2014-07-08');

/*Goverment Agency */
INSERT INTO Government_Agency (Username,Jurisdiction) VALUES ('user2','Subject Matter Jurisdiction');
INSERT INTO Government_Agency (Username,Jurisdiction) VALUES ('user6','Courts of General Jurisdiction');

/*Company*/
INSERT INTO Company (Username,HQLocation) VALUES ('user3','Atlanta');
INSERT INTO Company (Username,HQLocation) VALUES ('user7','Virgina');

/*Municipalities*/
INSERT INTO Municipalities (Username,Population) VALUES ('user4','10000');
INSERT INTO Municipalities (Username,Population) VALUES ('user8','1000000');

/*ESF*/
INSERT INTO ESF(Number,Description) VALUES (1, 'Transportation');
INSERT INTO ESF(Number,Description) VALUES (2, 'Communications');
INSERT INTO ESF(Number,Description) VALUES (3, 'Public Works and Engineering');
INSERT INTO ESF(Number,Description) VALUES (4, 'Firefighting');
INSERT INTO ESF(Number,Description) VALUES (5, 'Emergency Management');
INSERT INTO ESF(Number,Description) VALUES (6, 'Mass Care, Emergency Assistance, Housing, and Human Services');
INSERT INTO ESF(Number,Description) VALUES (7, 'Logistics Management and Resource Support');
INSERT INTO ESF(Number,Description) VALUES (8, 'Public Health and Medical Services');
INSERT INTO ESF(Number,Description) VALUES (9, 'Search and Rescue');
INSERT INTO ESF(Number,Description) VALUES (10, 'Oil and Hazardous Materials Response');
INSERT INTO ESF(Number,Description) VALUES (11, 'Agriculture and Natural Resources');
INSERT INTO ESF(Number,Description) VALUES (12, 'External Affairs');
INSERT INTO ESF(Number,Description) VALUES (13, 'Energy');
INSERT INTO ESF(Number,Description) VALUES (14, 'Public Safety and Security');
INSERT INTO ESF(Number,Description) VALUES (15, 'Long-Term Community Recovery');

/*Cost Time Unit*/
INSERT INTO Cost_Time_Unit(Unit) VALUES('Day');
INSERT INTO Cost_Time_Unit(Unit) VALUES('Week');
INSERT INTO Cost_Time_Unit(Unit) VALUES('Month');
INSERT INTO Cost_Time_Unit(Unit) VALUES('Year');


/*Resource*/
INSERT INTO Resource (Username,name,NextAvailableDate,Status,Model,Latitude,Longitude,Amount,CostTimeUnit) VALUES ('user1','Resource1',CURDATE(),'Available', 'JKJDFD', '37.923122', '30.392', '1123.20', 'Day');
INSERT INTO Resource (Username,name,NextAvailableDate,Status,Model,Latitude,Longitude,Amount,CostTimeUnit) VALUES ('user1','Resource2',CURDATE(),'Available', 'DFDSFDS', '25.328233', '69.234122', '113.20', 'Week');
INSERT INTO Resource (Username,name,NextAvailableDate,Status,Model,Latitude,Longitude,Amount,CostTimeUnit) VALUES ('user1','Resource3',CURDATE(),'Available', 'DFSDFSD', '17.92522', '18.923442', '1123.20', 'Month');
INSERT INTO Resource (Username,name,NextAvailableDate,Status,Model,Latitude,Longitude,Amount,CostTimeUnit) VALUES ('user2','Resource3',CURDATE(),'Not Available', 'DSFSDF', '24.34', '5.245622', '1123.20', 'Month');
INSERT INTO Resource (Username,name,NextAvailableDate,Status,Model,Latitude,Longitude,Amount,CostTimeUnit) VALUES ('user2','Resource4',CURDATE(),'Not Available', 'DSFSDF', '23.923122', '37.923122', '1123.20', 'Week');
INSERT INTO Resource (Username,name,NextAvailableDate,Status,Model,Latitude,Longitude,Amount,CostTimeUnit) VALUES ('user2','Resource4',CURDATE(),'Not Available', 'DSFSDF', '37.923122', '37.923122', '1123.20', 'Week');
INSERT INTO Resource (Username,name,NextAvailableDate,Status,Model,Latitude,Longitude,Amount,CostTimeUnit) VALUES ('user3','Resource4',CURDATE(),'Not Available', 'DSFSDF', '37.923122', '37.923122', '1123.20', 'Week');
INSERT INTO Resource (Username,name,NextAvailableDate,Status,Model,Latitude,Longitude,Amount,CostTimeUnit) VALUES ('user4','Resource4',CURDATE(),'Not Available', 'DSFSDF', '37.923122', '37.923122', '1123.20', 'Week');
INSERT INTO Resource (Username,name,NextAvailableDate,Status,Model,Latitude,Longitude,Amount,CostTimeUnit, ID) VALUES ('user5','Resource4',CURDATE(),'Not Available', 'DSFSDF', '10.124423', '80.234312', '1123.20', 'Week', 100);
INSERT INTO Resource (Username,name,NextAvailableDate,Status,Model,Latitude,Longitude,Amount,CostTimeUnit, ID) VALUES ('user5','Resource4',CURDATE(),'Not Available', 'DSFSDF', '-40.923122', '21.025856', '1123.20', 'Week', 101);
INSERT INTO Resource (Username,name,NextAvailableDate,Status,Model,Latitude,Longitude,Amount,CostTimeUnit, ID) VALUES ('user5','Resource4',CURDATE(),'Not Available', 'DSFSDF', '-53.923122', '37.923122', '1123.20', 'Week', 102);

/*Capabilities*/
INSERT INTO Capabilities (ID,Capabilities ) VALUES (100, 'Patrolling');
INSERT INTO Capabilities (ID,Capabilities ) VALUES (100, 'OnBoard Computer');
INSERT INTO Capabilities (ID,Capabilities ) VALUES (100, 'GPS');


/*Incident*/
INSERT INTO Incident (Username,Date, Description, Latitude,Longitude) VALUES ('user1', CURDATE(), 'Test Incident 1', '25.59392','38.2223');
INSERT INTO Incident (Username,Date, Description, Latitude,Longitude) VALUES ('user1', CURDATE(), 'Test Incident 2', '-32.12323','-14.2123');
INSERT INTO Incident (Username,Date, Description, Latitude,Longitude) VALUES ('user1', CURDATE(), 'Test Incident 3', '30.1583','25.2432');
INSERT INTO Incident (Username,Date, Description, Latitude,Longitude) VALUES ('user7', CURDATE(), 'Test Incident 1', '96.12323','-23.2123');
INSERT INTO Incident (Username,Date, Description, Latitude,Longitude) VALUES ('user8', CURDATE(), 'Test Incident 2', '43.12323','12.2123');
INSERT INTO Incident (Username,Date, Description, Latitude,Longitude) VALUES ('user5', CURDATE(), 'Test Incident 3', '12.12323','-74.2123');
INSERT INTO Incident (ID, Username,Date, Description, Latitude,Longitude) VALUES (102,'user1', CURDATE(), 'Test Incident 3', '16.24739','2.2123');
INSERT INTO Incident (ID, Username,Date, Description, Latitude,Longitude) VALUES (103,'user1', CURDATE(), 'Test Incident 3', '56.1','8.23');

/*Requests*/
INSERT INTO Requests(IncidentID, ResourceID, RequestDate, ReturnDate, ResourceOwner, Submitter) VALUES (102, 100,CURDATE(), DATE_ADD(CURDATE(),INTERVAL 6 MONTH),'user5', 'user1' );
INSERT INTO Requests(IncidentID, ResourceID, RequestDate, ReturnDate, ResourceOwner, Submitter) VALUES (103, 101, CURDATE(), DATE_ADD(CURDATE(),INTERVAL 6 MONTH),'user5', 'user1' );

/*Schedules_Repair*/
INSERT INTO Schedules_Repair (Username,ResourceID, RepairStartDate, DaysInRepair) VALUES ('user1', 1, CURDATE(), '50');
INSERT INTO Schedules_Repair (Username,ResourceID, RepairStartDate, DaysInRepair) VALUES ('user5', 102, CURDATE(), '50');

/*Deployed*/
INSERT INTO Deployed(IncidentID, ResourceID, StartDate) VALUES (102, 100, CURDATE());

/*Primary ESF*/
INSERT INTO Primary_ESF(Number, ResourceID) VALUES (4, 100);
INSERT INTO Primary_ESF(Number, ResourceID) VALUES (4, 101);

COMMIT;
