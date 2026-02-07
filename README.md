# Hostel Management Backend

This is the **backend** of the Hostel Management system built with **Spring Boot** and **H2 database**.  
The backend APIs are deployed on **Render (Free Tier)** and connected with a React frontend deployed on **Vercel**.

---

## API Base URL

Backend deployed URL (Render):  
[https://hostel-management-backend-rc9d.onrender.com](https://hostel-management-backend-rc9d.onrender.com)

---

## Available APIs

### Students
- `GET /api/students` - List all students  
- `POST /api/students` - Add a new student  
- `PUT /api/students/{id}` - Update a student  
- `DELETE /api/students/{id}` - Delete a student  

### Hostels
- `GET /api/hostels` - List all hostels  
- `POST /api/hostels` - Add a new hostel  

### Rooms
- `GET /api/rooms` - List all rooms  
- `POST /api/rooms` - Add a new room  

### Allocations
- `GET /api/allocations` - List all allocations  
- `POST /api/allocations` - Allocate a room to a student  

> All endpoints are accessible by the frontend only from:  
> `https://hostel-management-frontend-beta.vercel.app`

---

## Frontend Repo

The frontend for this backend is hosted on Vercel:  
[https://hostel-management-frontend-beta.vercel.app/](https://hostel-management-frontend-beta.vercel.app/)

---

## Running Locally

1. Clone the repository:

```bash
git clone https://github.com/sandy13226/hostel-management-backend.git
cd hostel-management-backend
Build with Maven:

mvn clean package


Run the JAR:

java -jar target/demo-0.0.1-SNAPSHOT.jar


Access APIs locally at: http://localhost:8080/api/

Technology Stack

Spring Boot

H2 Database (in-memory)

Maven

Render Free Tier (for deployment)

REST APIs

PPT Link

Add your backend PPT link here: [Your PPT Link Here]
