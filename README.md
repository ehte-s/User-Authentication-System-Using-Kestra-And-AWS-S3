# User-Authentication-System-Using-Kestra-And-AWS-S3
This project is a secure and scalable User Authentication System built in Java, enhanced with Kestra workflow orchestration for automated processes and AWS S3 for cloud-based data backup. It supports user registration, login, and password reset, ensuring security through SHA-256 password hashing and robust activity monitoring.

The project demonstrates the integration of traditional user management functionality with modern tools like Kestra and AWS, making it ideal for hackathons and production-ready applications.


Features
User Authentication:

Registration with unique usernames and hashed passwords.
Secure login functionality.
Password reset with automated workflows.
Kestra Integration:

Automates workflows for:
User registration validation and notifications.
Password reset token generation and monitoring.
Activity tracking (e.g., failed login attempts).
AWS S3 Backup:

Periodic backups of user data to an S3 bucket in the Asia Pacific (Mumbai) region (ap-south-1).
Security Enhancements:

Passwords hashed using SHA-256.
Suspicious activity detection (e.g., multiple failed login attempts).
Logs user actions for auditing.
Extensible Design:

Ready for future enhancements like:
Multi-Factor Authentication (MFA).
Role-Based Access Control (RBAC).
AI-driven anomaly detection.
Tech Stack
Java: Core application logic.
Kestra: Workflow orchestration for asynchronous task automation.
AWS S3: Cloud-based storage for data backup.
SHA-256: Password hashing algorithm for security.



How It Works
User Registration:

User enters a username and password.
A Kestra workflow validates the input, sends a welcome email, and logs the action.
Login:

System verifies credentials using stored hashed passwords.
Password Reset:

Kestra generates a secure token, emails it to the user, and tracks token expiration.
Data Backup:

Kestra periodically uploads the users.txt file to AWS S3.
