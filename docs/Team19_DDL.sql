BEGIN;
CREATE DATABASE IF NOT EXISTS Team19_db;
USE Team19_db;


DROP TABLE IF EXISTS Cost_Time_Unit;
DROP TABLE IF EXISTS Individual;
DROP TABLE IF EXISTS Government_Agency;
DROP TABLE IF EXISTS Company;
DROP TABLE IF EXISTS Municipalities;
DROP TABLE IF EXISTS Capabilities;
DROP TABLE IF EXISTS Requests;
DROP TABLE IF EXISTS Deployed;
DROP TABLE IF EXISTS Schedules_Repair;
DROP TABLE IF EXISTS ESF;
DROP TABLE IF EXISTS Primary_ESF;
DROP TABLE IF EXISTS Additional_ESF;
DROP TABLE IF EXISTS Resource;
DROP TABLE IF EXISTS Incident;
DROP TABLE IF EXISTS User;

CREATE TABLE User(
	Username varchar(50) NOT NULL,
	Password varchar(50) NOT NULL,
	Name varchar(50) NOT NULL,
	Type varchar(50) NOT NULL,
	PRIMARY KEY (Username));


CREATE TABLE Individual(
	Username varchar(50) NOT NULL,
	JobTitle varchar(50) NOT NULL,
	DateHired datetime  NOT NULL,
PRIMARY KEY (username),
	FOREIGN KEY (username) REFERENCES User(Username));


CREATE TABLE Government_Agency(
	Username varchar(50) NOT NULL,
	Jurisdiction varchar(50) NOT NULL,
	PRIMARY KEY (Username),
	FOREIGN KEY (Username) REFERENCES User (Username));

CREATE TABLE Company(
	Username varchar(50) NOT NULL,
	HQLocation varchar(50) NOT NULL,
	PRIMARY KEY (Username),
FOREIGN KEY (Username) REFERENCES User (Username));

CREATE TABLE Municipalities(
	Username varchar(50) NOT NULL,
	Population varchar(50) NOT NULL,
	PRIMARY KEY (Username),
FOREIGN KEY (Username) REFERENCES User (Username));

CREATE TABLE Incident(
	Username varchar(50) NOT NULL,
	ID integer(10) NOT NULL auto_increment,
	Date datetime NOT NULL,
	Description varchar(255) NOT NULL,
	Latitude decimal(10,6) NOT NULL,
	Longitude decimal(10,6) NOT NULL,
	PRIMARY KEY (ID),
FOREIGN KEY (Username) REFERENCES User (Username));

ALTER TABLE Incident ADD CONSTRAINT uc_Incident UNIQUE (ID,Username);

CREATE TABLE Resource(	
	Username varchar(50) NOT NULL,
	ID integer(10) NOT NULL auto_increment,
	name varchar(50) NOT NULL,
	NextAvailableDate datetime NOT NULL,
	Status varchar(50) NOT NULL,
	Model varchar(50) NOT NULL,
	Latitude decimal(10,6) NOT NULL,
	Longitude decimal(10,6) NOT NULL,
	Amount decimal(9,2) NOT NULL,
	CostTimeUnit varchar(50) NOT NULL,
PRIMARY KEY (ID),
FOREIGN KEY (username) REFERENCES User(Username));

ALTER TABLE Resource ADD CONSTRAINT uc_Resource UNIQUE (ID,Username);

CREATE TABLE Capabilities(
	ID integer(10) NOT NULL,
	Capabilities varchar(50) NOT NULL,
	PRIMARY KEY (ID, Capabilities),
FOREIGN KEY (ID) REFERENCES Resource (ID));

CREATE TABLE Requests(
	IncidentID integer(10) NOT NULL,
	ResourceID integer(10) NOT NULL,
	RequestDate datetime NOT NULL,
	ReturnDate datetime NOT NULL,
	ResourceOwner varchar(50) NOT NULL,
	Submitter varchar(50) NOT NULL,
	Status varchar(50) NOT NULL,
	PRIMARY KEY (IncidentID, ResourceID),
FOREIGN KEY (IncidentID) REFERENCES Incident (ID),
FOREIGN KEY (ResourceID) REFERENCES Resource (ID),
FOREIGN KEY (Submitter) REFERENCES User(Username),
FOREIGN KEY (ResourceOwner) REFERENCES User(Username)
);


CREATE TABLE Deployed(
	IncidentID integer(10) NOT NULL,
	ResourceID integer(10) NOT NULL,
	StartDate datetime NOT NULL,
	PRIMARY KEY (IncidentId, ResourceId),
FOREIGN KEY (IncidentID) REFERENCES Incident (ID),
FOREIGN KEY (ResourceID) REFERENCES Resource (ID));

CREATE TABLE Schedules_Repair(
	Username varchar(50) NOT NULL,
	ResourceID integer(10) NOT NULL,
	RepairStartDate datetime NOT NULL,
	DaysInRepair integer(10) NOT NULL,
	PRIMARY KEY (Username, ResourceId),
FOREIGN KEY (Username) REFERENCES User (Username),
FOREIGN KEY (ResourceID) REFERENCES Resource (ID));


CREATE TABLE ESF(
	Number integer (10) NOT NULL,
	Description varchar(200) NOT NULL,
	PRIMARY KEY (Number));

CREATE TABLE Primary_ESF(
	Number varchar(50) NOT NULL,
	ResourceId integer(10) NOT NULL,
	PRIMARY KEY (Number, ResourceId),
FOREIGN KEY (ResourceID) REFERENCES Resource (ID));

CREATE TABLE Additional_ESF(
	Number varchar(50) NOT NULL,
	ResourceId integer(10) NOT NULL,
	PRIMARY KEY (Number, ResourceId),
FOREIGN KEY (ResourceID) REFERENCES Resource (ID));

CREATE TABLE Cost_Time_Unit (
	Unit varchar(50) NOT NULL,
	PRIMARY KEY (Unit)	
);

COMMIT;
