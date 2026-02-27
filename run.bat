@echo off
REM Build and run using Maven wrapper
call mvnw.cmd clean package
if %errorlevel% neq 0 exit /b %errorlevel%
java -jar target\deals-site-0.1.0.jar
