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

/* Script to populate tables (adjust if needed)*/

INSERT INTO COUNSELORS (NAME, SPECIALIZATION, AVAILABILITY)
VALUES 
('Dr. Sarah Adams', 'Anxiety Management', 'Available'),
('Mr. John Roberts', 'Career Counseling', 'Unavailable'),
('Ms. Priya Nair', 'Stress Management', 'Available'),
('Dr. Carlos Gomez', 'Trauma Therapy', 'Available');

INSERT INTO APPOINTMENTS (STUDENTNAME, COUNSELORNAME, APPOINTMENTDATE, APPOINTMENTTIME, STATUS)
VALUES 
('Alice Brown', 'Dr. Sarah Adams', '2025-07-19', '10:00', 'Scheduled'),
('David Smith', 'Ms. Priya Nair', '2025-07-20', '14:00', 'Scheduled'),
('Emily Jones', 'Dr. Carlos Gomez', '2025-07-21', '09:30', 'Cancelled'),
('Michael Johnson', 'Mr. John Roberts', '2025-07-22', '11:15', 'Scheduled');


INSERT INTO FEEDBACK (STUDENTNAME, RATING, COMMENTS)
VALUES 
('Alice Brown', 5, 'Excellent support and understanding.'),
('David Smith', 4, 'Helpful but could be more punctual.'),
('Emily Jones', 3, 'Session was average.'),
('Michael Johnson', 5, 'Very professional counselor.');
