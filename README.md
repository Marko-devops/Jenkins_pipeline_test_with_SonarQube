# Jenkins Ec2 on AWS

I launched an EC2 instance on AWS named Jenkins_server using the Ubuntu AMI and t2.small instance type, with a Jenkins_key for secure access, configured the Jenkins_sg security group, provided a user data script (Jenkins.sh) to automate Jenkins installation at launch, accessed Jenkins via the instance’s public IP on port 8080. 

After launching the SonarQube Ec2 instance I added in Jenkins security gorup, inbound rules from SonarQube_sg to allow communication between Jenkins and SonarQube. 

#Additional Tools Installed
- JDK 17
- AWS CLI

After the installation, I confirmed the available JDK versions in Gitbash terminal by running: ls /usr/lib/jvm/
The output showed the installed JDK directory /usr/lib/jvm/java-17-openjdk-amd64 witch I copied into Jenkins and added it as a new tool configuration, allowing Jenkins to use JDK 17 for builds.

# SonarQube Ec2 on AWS

I launched an EC2 instance on AWS named SonarQube using the Ubuntu AMI and t2.medium instance type. A SonarQube key was created for secure access. The SonarQube_sg security group was configured, including inbound rules from Jenkins_sg to allow communication between SonarQube and Jenkins. A user data script (SonarQube.sh) was provided during instance creation to automate the installation and setup of SonarQube at launch.
Once the instance was running, SonarQube was accessed via a web browser using the instance’s public IP address on port 80.

# S3 bucket on AWS

I created an S3 bucket named "jenkinspipelines3bucket".
I created an IAM policy with a JSON document that allows Jenkins to access the S3 bucket without requiring credentials, so that they wouldn’t be exposed in the pipeline, and named the policy "bucket policy":
      {
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "Statement1",
            "Effect": "Allow",
            "Action": [
                "s3:PutObject",
                "s3:GetObject",
                "s3:ListBucket"
                    ],
            "Resource": [
                "arn:aws:s3:::jenkinstests3bucket",
                "arn:aws:s3:::jenkinstests3bucket/*"
                    ]           
        }
                 ]
      }
