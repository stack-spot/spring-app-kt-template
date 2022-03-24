./gradlew run

---

### Release via GitHub Actions Pipeline


The application can be deployed on the AWS cloud infrastructure through the GitHub Actions tool.

Setup configuration to GitHub Actions and AWS integration:


#### 1 - Perform GitHub Action OIDC integration configurations with AWS

References:
  https://docs.github.com/en/actions/deployment/security-hardening-your-deployments/configuring-openid-connect-in-amazon-web-services
  https://docs.aws.amazon.com/IAM/latest/UserGuide/id_roles_providers_create_oidc.html#manage-oidc-provider-console

![GitHub Action OIDC integration configurations with AWS](docs/IdentityProvider.png?raw=true "GitHub Action OIDC integration configurations with AWS")


#### 2 - Run application code versioning in a GitHub repository.

If you wanted to associate the generated application with a remote GitHub repository via the STK CLI application creation command, you can do it with the parameter: "--remote"
Otherwise, the user must version the remote GitHub repository manually

References:
  https://docs.stackspot.com/v3.1.0/stk-cli/commands/commands-list/stk-create-app/#como-criar-uma-aplica%C3%A7%C3%A3o


#### 3 - Creation of AWS IAM Role to allow access for pipeline execution via GitHub Actions

Create policy: IAMWritePassRolePolicy

![AWS create IAM Policy](docs/IAMWritePassRolePolicy1.png?raw=true "AWS create IAM Policy")

![AWS create IAM Policy](docs/IAMWritePassRolePolicy2.png?raw=true "AWS create IAM Policy")

![AWS create IAM Policy](docs/IAMWritePassRolePolicy3.png?raw=true "AWS create IAM Policy")

Create a new IAM Role and associate the following Policies:

    IAMWritePassRolePolicy
    AmazonRDSFullAccess
    AmazonEC2FullAccess
    AmazonSQSFullAccess
    AmazonEC2ContainerRegistryFullAccess
    AmazonS3FullAccess
    CloudWatchFullAccess
    AmazonSSMFullAccess
    AWSXrayFullAccess
    AWSCloudFormationFullAccess

![AWS create IAM Role](docs/RoleCreation1.png?raw=true "AWS create IAM Role")

![AWS create IAM Role](docs/RoleCreation2.png?raw=true "AWS create IAM Role")

![AWS create IAM Role](docs/RoleCreation3.png?raw=true "AWS create IAM Role")

![AWS create IAM Role](docs/RoleCreation4.png?raw=true "AWS create IAM Role")


#### 4 - Register Action Secrets (Settings > Security - Secrets > Actions menu) in the application repository on GitHub, with the information associated with the AWS IAM role of pipeline execution created in step 3.

     PIPELINE_RELEASE_ROLE : The secret value must be the ARN of the Role created in step 3

![GitHub Configuration Action Secret](docs/GitHubActionsSecrets1.png?raw=true "GitHub Configuration Action Secret")

![GitHub Configuration Action Secret](docs/GitHubActionsSecrets2.png?raw=true "GitHub Configuration Action Secret")


#### 5 - Pipeline execution triggers: There are 2 distinct pipelines for the application that are triggered at different times:


  5.1 - Build and Test Pipeline: performs the build, tests and verification of security aspects through the HorusSec tool in the application, being triggered when performing a pull request to the main branch of the application


  5.2 - Release Pipeline: deploy the application in the cloud environment, being triggered when generating a new tag/release with the following naming structure: 
        
        "<stage>-v<version>", for example: "sandbox-v1.0.0"

  ![GitHub Action Pipeline Execution](docs/Pipeline.png?raw=true "GitHub Action Pipeline Execution")