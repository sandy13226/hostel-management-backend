# Navigate to backend directory
Set-Location -Path "c:\Users\ELCOT\Downloads\demo\demo\backend"

# Start the backend
Write-Host "Starting Spring Boot Backend..." -ForegroundColor Green
.\mvnw.cmd spring-boot:run
