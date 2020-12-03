USE Airline;

CREATE TABLE AirlineCompany
(
AirlineCode int primary key NOT NULL,
AirlineName varchar(255)
);
CREATE TABLE Flight
(
FlightID int primary key,
PlaneType varchar (255),
AirlineCode int,
FOREIGN KEY (AirlineCode) REFERENCES AirlineCompany(AirlineCode)
);
CREATE TABLE Country
(
CountryID int primary key,
CountryName varchar(255),
FlightID int,
FOREIGN KEY (FlightID) REFERENCES Flight(FlightID)
);
CREATE TABLE Airport
(
AirportID int primary key,
AirportName varchar(255),
CountryID int,
FOREIGN KEY (CountryID) REFERENCES Country(CountryID)
);
CREATE TABLE Customer
(
CustomerID int primary key,
CustomerFirstName varchar(255),
CustomerLastName varchar(255),
Username varchar(255),
CustomerPassword varchar(255),
Email varchar(255)
);
CREATE TABLE Payment

(
PaymentID int primary key,
CardNo varchar(255),
CVV int(3),
Expiry int,
CustomerID int,
FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);
CREATE TABLE Booking
(
BookingID int primary key,
CustomerID int,
FlightID int,
AirlineCode int,
PaymentID int,
FOREIGN KEY (PaymentID) REFERENCES Payment(PaymentID),
FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
FOREIGN KEY (FlightID) REFERENCES Flight(FlightID),
FOREIGN KEY (AirlineCode) REFERENCES AirlineCompany(AirlineCode)
);
insert into AirlineCompany(AirlineCode, AirlineName) values(1, "Etihad");
insert into AirlineCompany(AirlineCode, AirlineName) values(2, "Lufthansa");
insert into Flight (FlightID, PlaneType, AirlineCode) values(1, "Boeing", 1);
insert into Country (CountryID, CountryName, FlightID) values(1, "India", 1);
insert into Airport(AirportID, AirportName, CountryID) values(1, "Mumbai Airport", "1");
insert into Customer(CustomerID, CustomerFirstName,CustomerLastName,Username,
CustomerPassword, Email) values(1, "Saurabh","Gaglani","sgaglani","abcd", "sg@sg.com");
insert into Payment (PaymentID, CardNo, CVV, Expiry, CustomerID) values (1, 1234, 345,
1213, 1);
insert into Booking (BookingID, CustomerID, FlightID, AirlineCode) values (1, 1, 1, 2);
Update Booking set PaymentID = 1 where BookingID = 1;

insert into Flight (FlightID, PlaneType, AirlineCode) values(3, "Airbus A338", 1);

select * from Flight;

select * from Customer;

select Flight.FlightID, Flight.PlaneType,AirlineCompany.AirlineName from Flight inner join AirlineCompany on Flight.AirlineCode = AirlineCompany.AirlineCode where FlightID = 1;

select MAX(CustomerID) from Customer;

select * from Flight;

select * from Booking;

insert into Booking(BookingID,PaymentID) values (2,2);
insert into Booking(FlightID, AirlineCode) select Flight.FlightID,AirlineCompany.AirlineCode from Flight inner join AirlineCompany on Flight.AirlineCode = AirlineCompany.AirlineCode where FlightID = 1 and BookingID = 2;

select * from Payment;




