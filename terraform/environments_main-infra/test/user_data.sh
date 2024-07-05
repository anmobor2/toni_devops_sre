#!/bin/bash
yum update -y

amazon-linux-extras install docker -y
service docker start
systemctl enable docker

echo "${docker_hub_password}" | docker login -u "${docker_hub_username}" --password-stdin

docker pull ${docker_hub_username}/toni:latest

docker run -d -p 80:80 \
  -e DB_HOST=${rds_endpoint} \
  -e DB_USERNAME=${db_username} \
  -e DB_PASSWORD=${db_password} \
  ${docker_hub_username}/toni:latest

echo "@reboot root $(which docker) run -d -p 80:80 -e DB_HOST=${rds_endpoint} -e DB_USERNAME=${db_username} -e DB_PASSWORD=${db_password} ${docker_hub_username}/toni:latest" >> /etc/crontab