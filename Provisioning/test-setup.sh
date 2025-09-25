#!/bin/bash                                     
  
echo "Preparing for installation of packages"         

set -e                                                #exit on error
sudo yum update -y                                    #update linux
sudo yum install wget unzip httpd -y                  #install wget, unzip and apache
sudo systemctl start httpd                            #start apache
sudo systemctl enable httpd                           #enable apache to start on boot

echo "Creating a temp file"                          #create a temp file
sudo mkdir -p /tmp/webfiles                          #create a tmp/ directory
cd /tmp/webfiles                                     #navigate to the directory

echo "Downloading web files"                                             
wget https://www.tooplate.com/zip-templates/2137_barista_cafe.zip        #downloading the zip file
unzip 2137_barista_cafe.zip                                              #unzip the downloaded file
sudo cp -r 2137_barista_cafe/* /var/www/html/                            #copy the files to the html directory
sudo rm -rf 2137_barista_cafe 2137_barista_cafe.zip                      #remove the unzipped folder and zip file

echo "Restarting apache server"         
sudo systemctl restart httpd            #restarting apache server

echo "Installation complete"            #installation complete
