#Jenkins Setup on AWS

I created an EC2 instance on AWS and named it Jenkins_server.
The AMI used was Ubuntu, and the instance type was set to t2.small because t2.micro does not have enough memory to run Jenkins reliably. With a t2.micro, Jenkins would frequently crash, requiring me to stop and restart the instance often.
For secure access, I generated a key pair named Jenkins_key.
The security group was named Jenkins_sg, and I configured the inbound rules (screenshot attached) to allow the necessary traffic for Jenkins to function properly.
In the Advanced details section during instance creation, I provided a user data script named Jenkins.sh.
This script contains all the essential installation steps to set up Jenkins automatically on the instance at launch.

#Accessing Jenkins
Once the instance was running, I accessed Jenkins through a web browser by copying the public IP address of the instance and appending :8080.
Recommendation: when working with Jenkins, it's best practice to assign a static IP (Elastic IP) to ensure the IP address remains consistent, especially when the instance is stopped and restarted.

#Additional Tools Installed
Maven
I installed Maven and configured it in Jenkins under the name MAVEN3.9.
JDK 17
To install JDK 17, I connected to the Jenkins server using Git Bash terminal, switched to the root user, and ran:
apt install openjdk-17-jdk -y

After the installation, I confirmed the available JDK versions in Gitbash terminal by running: ls /usr/lib/jvm/
The output showed the installed JDK directory: /usr/lib/jvm/java-17-openjdk-amd64
I then copied this path into Jenkins and added it as a new tool configuration, allowing Jenkins to use JDK 17 for builds.
