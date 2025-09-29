# Jenkins Ec2 on AWS

I launched an EC2 instance on AWS named Jenkins_server using the Ubuntu AMI and t2.small instance type, with a Jenkins_key for secure access, configured the Jenkins_sg security group, provided a user data script (Jenkins.sh) to automate Jenkins installation at launch, accessed Jenkins via the instance’s public IP on port 8080. 

After launching the SonarQube Ec2 instance I added in Jenkins security gorup, inbound rules from SonarQube_sg to allow communication between Jenkins and SonarQube. 

#Additional tools installed:
- JDK 17
- AWS CLI

After the installation, I confirmed the available JDK versions in Gitbash terminal by running: ls /usr/lib/jvm/
The output showed the installed JDK directory /usr/lib/jvm/java-17-openjdk-amd64 witch I copied into Jenkins and added it as a new tool configuration, allowing Jenkins to use JDK 17 for builds.

# SonarQube Ec2 on AWS

I launched an EC2 instance on AWS named SonarQube using the Ubuntu AMI and t2.medium instance type. A SonarQube key was created for secure access. The SonarQube_sg security group was configured, including inbound rules from Jenkins_sg to allow communication between SonarQube and Jenkins. A user data script (SonarQube.sh) was provided during instance creation to automate the installation and setup of SonarQube at launch.
Once the instance was running, SonarQube was accessed via a web browser using the instance’s public IP address on port 80.

# S3 bucket on AWS

I created an S3 bucket named "jenkinspipelines3bucket".

# IAM Role

I created an IAM policy with a JSON document that allows Jenkins to access the S3 bucket without requiring credentials, so that they wouldn’t be exposed in the pipeline, and named the policy "bucket policy" (S3_bucket.json).
I accessed the IAM Roles for the Jenkins EC2 instance and created a role named "s3BucketPolicy" with permissions for the "jenkinspipelines3bucket".
I then returned to the Jenkins instance and attached the "s3BucketPolicy".
This approach allows Jenkins to interact with the S3 bucket during pipeline execution without using AWS credentials.

# Accessing Jenkins through a web browser

#Additional tools installed:
 - JDK -> Set up JDK with the name jdk17. Log in to Git Bash as the root user, then copy the path /usr/lib/jvm/java-17-openjdk-amd64 into Jenkins configuration.
 - SonarQube Scanner, version 6.2.1.4610 named "sonar6.2".

#Additional plugins installed:
- SonarQube scanner
- Build timestamp
- Pipeline utility step
- Aws steps

In Jenkins, I navigated to SonarQube Servers, enabled Environment Variables, and gave the server the name "sonarserver" and then I entered the private IP of the SonarQube server with port :80.
Next, I logged into the SonarQube server, generated a token named "sonartoken", and added it in Jenkins as a Secret Text credential. After saving it I selected the credential for the SonarQube server configuration and saved the settings again.

# Accessing SonarQube through a web browser

In SonarQube, I created a New Project named "Jenkins_pipeline" and the I navigated to Project Settings → Quality Gates and created a new quality gate named "Jenkins_QA". I unlocked editing, added a condition for Overall Code, selected Bugs, and set the value to 10.
Then, I returned to the SonarQube dashboard, went to Project Settings → Quality Gate, and enabled "Always use a specific Quality Gate", selecting the one I just created. Next, to allow SonarQube to notify Jenkins, I created a webhook by navigating to Project Settings → Webhooks → Create. I named it "Jenkins-webhook" and set the URL to http://"private IP of Jenkins":8080/sonarqube-webhook
