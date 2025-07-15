/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  marku
 * Created: Jul 15, 2025
 */



/* Use the following sql when executing command on database*/

CREATE TABLE Appointments (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    studentName VARCHAR(100) NOT NULL,
    counselorName VARCHAR(100) NOT NULL,
    appointmentDate DATE NOT NULL,
    appointmentTime TIME NOT NULL,
    status VARCHAR(20) DEFAULT 'Scheduled'
);

CREATE TABLE Counselors (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    availability VARCHAR(100) NOT NULL
);

CREATE TABLE Feedback (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    studentName VARCHAR(100) NOT NULL,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    comments VARCHAR(255)
);

