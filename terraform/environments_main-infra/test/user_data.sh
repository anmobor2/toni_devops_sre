yum update -y

# Install Docker
amazon-linux-extras install docker -y
service docker start
systemctl enable docker

# Log in to Docker Hub
echo "${docker_hub_password}" | docker login -u "${docker_hub_username}" --password-stdin

# Pull the Docker image
docker pull ${docker_hub_username}/toni:latest

# Run the Docker container with environment variables
docker run -d -p 80:8080 \
  -e DB_HOST=${rds_endpoint} \
  -e DB_USERNAME=${db_username} \
  -e DB_PASSWORD=${db_password} \
  ${docker_hub_username}/toni:latest

# Ensure the container starts on boot
echo "@reboot root $(which docker) run -d -p 80:8080 -e DB_HOST=${rds_endpoint} -e DB_USERNAME=${db_username} -e DB_PASSWORD=${db_password} ${docker_hub_username}/toni:latest" >> /etc/crontab

# Add logging
echo "User Data script executed successfully" >> /var/log/user-data.log 2>&1
#yum install -y httpd
#systemctl start httpd
#systemctl enable httpd
#echo "It works!" > /var/www/html/index.html