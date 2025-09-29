# Jenkins Ec2 on AWS

I created an Ec2 instance on AWS and named it Jenkins_server.
The AMI used was Ubuntu, and the instance type was set to t2.small because t2.micro does not have enough memory to run Jenkins reliably. With a t2.micro, Jenkins would frequently crash, requiring me to stop and restart the instance often.
For secure access, I generated a key pair named Jenkins_key.
The security group was named Jenkins_sg, and I configured the inbound rules to allow the necessary traffic for Jenkins to function properly.
In the Advanced details section during instance creation, I provided a user data script named Jenkins.sh. 
This script contains all the essential installation steps to set up Jenkins automatically on the instance at launch.
Once the instance was running, I accessed Jenkins through a web browser by copying the public IP address of the instance and appending :8080.

#Additional Tools Installed
- JDK 17
- AWS CLI

After the installation, I confirmed the available JDK versions in Gitbash terminal by running: ls /usr/lib/jvm/
The output showed the installed JDK directory /usr/lib/jvm/java-17-openjdk-amd64 witch I copied into Jenkins and added it as a new tool configuration, allowing Jenkins to use JDK 17 for builds.

# SonarQube Ec2 on AWS

I created an EC2 instance on AWS and named it SonarQube. The AMI used was Ubuntu, and the instance type was set to t2.medium. For secure access, I created a SonarQube key. The security group was named SonarQube_sg, and I configured the inbound rules, including rules from Jenkins_sg, so that SonarQube and Jenkins could communicate.

In the Advanced Details section during instance creation, I provided a user data script named SonarQube.sh. This script contains all the essential installation steps to set up SonarQube automatically on the instance at launch.

Once the instance was running, I accessed SonarQube through a web browser by copying the public IP address of the instance and appending :80.
