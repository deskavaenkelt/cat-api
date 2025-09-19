# Cat API

En fullstack-applikation för att hantera katter, byggd med Spring Boot (backend) och React TypeScript (frontend).

## Teknisk stack

### Backend

- Java 21 (Amazon Corretto)
- Spring Boot 3.2.2
- Spring Security med JWT-autentisering
- H2 in-memory databas
- Swagger/OpenAPI dokumentation
- Maven

### Frontend

- React 19 med TypeScript
- Node 22
- Tailwind CSS
- React Router
- Nginx (för Docker)

## Docker-installation

### Förutsättningar

- Docker och Docker Compose installerade
- Portar 80 och 8080 lediga

### Starta applikationen

1. Klona repositoryt:

```bash
git clone <repository-url>
cd cat-api
```

2. Bygg och starta med Docker Compose:

```bash
docker-compose up -d --build
```

3. Applikationen är nu tillgänglig på:

- **Frontend**: <http://localhost>
- **Backend API**: <http://localhost:8080>
- **Swagger UI**: <http://localhost:8080/swagger-ui.html>
- **H2 Console**: <http://localhost:8080/h2-console>

### Docker-kommandon

```bash
# Starta containrar
docker-compose up -d

# Stoppa containrar
docker-compose down

# Visa loggar
docker-compose logs -f

# Bygga om efter kodändringar
docker-compose build
docker-compose up -d

# Visa status
docker-compose ps
```

## Azure VM Deployment

### Installera Docker på Azure VM

1. SSH:a in på din Azure VM:

```bash
ssh <användarnamn>@<vm-ip-adress>
```

2. Installera Docker:

```bash
# Uppdatera paket
sudo apt-get update

# Installera Docker
sudo apt-get install -y docker.io docker-compose

# Lägg till användaren i docker-gruppen
sudo usermod -aG docker $USER

# Logga ut och in igen för att aktivera gruppändringen
exit
```

3. SSH:a in igen och verifiera:

```bash
docker --version
docker-compose --version
```

### Deploya applikationen

1. Klona repositoryt på VM:n:

```bash
git clone <repository-url>
cd cat-api
```

2. Starta applikationen:

```bash
docker-compose up -d
```

3. Öppna portar i Azure Network Security Group:

- Port 80 (HTTP) för frontend
- Port 8080 för backend API

4. Accessa applikationen via:

- Frontend: `http://<vm-ip-adress>`
- Backend API: `http://<vm-ip-adress>:8080`

## Funktionalitet

### Autentisering

- Registrera ny användare
- Logga in med email och lösenord
- JWT-baserad autentisering
- Rollbaserad åtkomstkontroll (USER/ADMIN)

### Katthantering

- Lista alla katter
- Lägg till ny katt
- Uppdatera kattinformation
- Ta bort katt
- Admin: Ladda fördefinierade exempelkatter

## API Endpoints

### Autentisering

- `POST /auth/signup` - Registrera ny användare
- `POST /auth/login` - Logga in

### Katter (kräver autentisering)

- `GET /cats` - Hämta alla katter
- `GET /cats/{id}` - Hämta specifik katt
- `POST /cats` - Skapa ny katt
- `PUT /cats/{id}` - Uppdatera katt
- `DELETE /cats/{id}` - Ta bort katt
- `POST /cats/addPredefinedCats` - Lägg till exempelkatter (endast admin)

## Databas

H2 in-memory databas används med följande inställningar:

- URL: `jdbc:h2:mem:testdb`
- Användare: `sa`
- Lösenord: `password`
- Console: <http://localhost:8080/h2-console>

## Utveckling

### Backend (utan Docker)

```bash
cd cat_mvc_auth_swagger_lomboc
mvn spring-boot:run
```

### Frontend (utan Docker)

```bash
cd cat_mvc_frontend
npm install
npm start
```

## Felsökning

### Containrar startar inte

```bash
# Kontrollera loggar
docker-compose logs backend
docker-compose logs frontend

# Starta om containrar
docker-compose restart
```

### Portar upptagna

```bash
# Kontrollera vad som använder portarna
sudo lsof -i :80
sudo lsof -i :8080

# Eller ändra portar i docker-compose.yml
```

### Bygga om efter ändringar

```bash
docker-compose down
docker-compose build --no-cache
docker-compose up -d
```

## Licens

Detta projekt är skapat för undervisningsändamål.
