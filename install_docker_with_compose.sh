#!/bin/bash

# Docker installation script för Azure Ubuntu VM
# Detta script installerar Docker och Docker Compose på en Ubuntu-baserad Azure VM

echo "=== Docker Installation Script för Azure VM ==="
echo ""

# Uppdatera paketlistan
echo "Steg 1: Uppdaterar paketlistan..."
sudo apt-get update -y

# Installera nödvändiga paket
echo "Steg 2: Installerar nödvändiga paket..."
sudo apt-get install -y \
    ca-certificates \
    curl \
    gnupg \
    lsb-release

# Lägg till Dockers officiella GPG-nyckel
echo "Steg 3: Lägger till Dockers GPG-nyckel..."
sudo mkdir -m 0755 -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg

# Lägg till Docker repository
echo "Steg 4: Lägger till Docker repository..."
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

# Uppdatera paketlistan igen
echo "Steg 5: Uppdaterar paketlistan med Docker repository..."
sudo apt-get update -y

# Installera Docker Engine
echo "Steg 6: Installerar Docker Engine..."
sudo apt-get install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

# Installera standalone Docker Compose (för docker-compose kommandot)
echo "Steg 7: Installerar Docker Compose..."
DOCKER_COMPOSE_VERSION="2.23.3"
sudo curl -L "https://github.com/docker/compose/releases/download/v${DOCKER_COMPOSE_VERSION}/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# Lägg till användaren i docker-gruppen
echo "Steg 8: Lägger till användaren i docker-gruppen..."
sudo usermod -aG docker $USER

# Verifiera installation
echo ""
echo "=== Verifierar installation ==="
docker --version
docker-compose --version

echo ""
echo "=== Installation klar! ==="
echo ""
echo "VIKTIGT: Du måste logga ut och in igen för att gruppändringen ska träda i kraft."
echo ""
echo "Efter utloggning, kör följande kommandon för att starta Cat API:"
echo "  git clone <repository-url>"
echo "  cd cat-api"
echo "  docker-compose up -d --build"
echo ""
echo "För att logga ut, kör: exit"