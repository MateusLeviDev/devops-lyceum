# AWS Identity and Access Management (IAM):

- **Create a user** with appropriate permissions instead of using the root user.
- **Reasons for using IAM**:
  - **Principle of least privilege**: Grant each user only the permissions necessary for their work, reducing the potential damage if their credentials are compromised.
  - **Traceability**: Assign actions to specific users for auditing and investigation purposes.
  - **Role separation**: Allow different teams or departments to have control over their own permissions and resources.
  - **Simplified management**: Easily add, remove, or modify users without affecting other accounts or services.
  - **Compliance**: Implement strict access controls to meet compliance requirements.
  - **Enhanced security and monitoring**: Apply custom security policies, such as requiring two-factor authentication, to strengthen account security.

# Deploying Resources on AWS:

- Utilize IAM to create and manage users, groups, and roles for secure infrastructure management and scalability.

# Creating a User:

- Create a user with a username and password.
- Assign appropriate permissions based on the user's role (e.g., AdministratorAccess, EC2 full access, etc.).

# IAM Tags:

- Use tags to identify resources associated with specific users in IAM.

# Network Settings:

- Configure network settings to restrict access to known IP addresses for security purposes.

# SSH Access:

- Use SSH to access the EC2 instance.
- Set permissions for the .pem file using `chmod 400 <pem file>`.
- Connect to the instance using `ssh -i <path to .pem> ubuntu@<public ip>`.

# Environment Configuration:

- Configure the environment within the terminal, such as installing Go.

# Cloning the Repository:

- Clone the repository using HTTPS.

# Running the Application:

- Execute `go run main.go` to run the Go application.

# Exposing the Application:

- Update security group inbound rules to allow traffic on port 8080 from `0.0.0.0/0` (anywhere).
- Adjust the `router.Run("localhost:8080")` to `router.Run("0.0.0.0:8080")` to allow external connections.

By following these steps, you can securely deploy your Go application on AWS while ensuring proper access control and network security measures.

