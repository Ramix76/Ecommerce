# Variables
DC=docker-compose
PROJECT_NAME=ecommerce

# =================================
# Comandos principales
# =================================
.PHONY: up down build rebuild logs clean

# Levantar todos los servicios en background
up:
	$(DC) up --build -d

# Detener todos los servicios
down:
	$(DC) down

# Construir imágenes sin levantar contenedores
build:
	$(DC) build

# Reconstruir y levantar
rebuild: down build up

# Ver logs en tiempo real
logs:
	$(DC) logs -f

# Limpiar volúmenes y contenedores
clean:
	$(DC) down -v --rmi all --remove-orphans