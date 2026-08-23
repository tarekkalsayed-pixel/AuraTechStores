# Security Policy

AuraTechStores is a portfolio and educational Spring Boot project. Security issues are taken seriously, especially authentication, authorization, CSRF protection, credential handling, and admin-only actions.

## Reporting a Vulnerability

If you discover a security issue, please do not publish working exploit details in a public issue. Contact the repository owner privately with:

- A short description of the issue
- The affected route or component
- Reproduction steps
- The expected security behavior
- Any suggested remediation

## Security Practices

The project uses the following protections:

- Spring Security role-based authorization
- BCrypt-backed password encoding for newly saved accounts
- CSRF protection for application forms
- POST-only destructive product deletion
- Environment variables for SMTP and database credentials
- Admin-only catalog management and operation history
- Repository rules that exclude `.env`, local database files, IDE metadata, and logs

## Local Demo Accounts

The seeded `admin` and `user` accounts are intended only for local demonstration. Their documented default credentials must not be reused for a public deployment.

For any deployed environment, replace demo credentials and provide secrets through environment variables or an external secret manager.

## Exposed Credentials

If a credential is ever committed to Git history, removing it from the latest file is not enough. Revoke or rotate the credential immediately because previous commits may still contain it.
