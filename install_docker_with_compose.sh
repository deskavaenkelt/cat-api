#!/bin/bash

echo
echo "1. Uninstall anyting docker"
sudo apt-get remove docker docker-engine docker.io containerd runc -y
for pkg in docker.io docker-doc docker-compose docker-compose-v2 podman-docker containerd runc; do sudo apt-get remove $pkg; done

echo
echo "2. Update apt"
sudo apt-get update

echo
echo "3. Install docker from offical docker site"
echo "3.1. Add Docker's official GPG key:"
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

echo
echo "3.2. Add the repository to Apt sources:"
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update

echo
echo "4. Install docker"
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y

echo
echo "5. Add user to docker group"
sudo usermod -aG docker $USER

echo
echo "6. Check if docker is running"
docker run hello-world

echo
echo "7. Check if docker compose is running"
docker compose version

