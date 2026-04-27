# Employees API - Spring Boot

API REST para la gestión de empleados, desarrollada con Spring Boot 3, Java 17 y PostgreSQL en Docker.

---

## Tecnologías utilizadas

| Tecnología | Versión |
|------------|---------|
| Java | 17 |
| Spring Boot | 3.5.14 |
| PostgreSQL | 42.7.10 |
| Docker | 4.70.0 |

---
## Estructura del proyecto

```
src/
└── main/
    └── java/
        └── com/tuempresa/employees/
            ├── web/
            │   ├── controller/    # Endpoints REST
            │   └── dto/           # Objetos de transferencia
            ├── service/           # Lógica de negocio
            ├── repository/        # Acceso a datos
            ├── model/             # Entidades JPA
            └── exception/         # Manejo de errores
```
---

## Requisitos previos

- Java 17 instalado
- Maven instalado o usar el wrapper incluido
- Docker instalado y corriendo

---

## Levantar base de datos en Docker

Ejecutar el siguiente comando:

```bash
docker run -d \
  --name postgres-employees \
  -e POSTGRES_DB=employees_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres
```

---

## Configuración

Verificar que `application.properties` tenga los siguientes valores:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/employees_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```

---

## Ejecutar la aplicación

Desde la raíz del proyecto:

### Opción 1: Maven Wrapper

#### Windows (PowerShell)
```bash
.\mvnw.cmd spring-boot:run
```

#### Windows (CMD)
```bash
mvnw.cmd spring-boot:run
```

#### Linux / Mac
```bash
./mvnw spring-boot:run
```

### Opción 2: Maven global
```bash
mvn spring-boot:run
```

---

## Documentación API (Swagger)
Una vez levantado el proyecto, acceder a: http://localhost:8080/swagger-ui.html

---

## Endpoints principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/employees` | Listar todos los empleados |
| GET | `/api/employees/{id}` | Obtener empleado por ID |
| GET | `/api/employees/search?position=` | Buscar empleados por posición |
| POST | `/api/employees` | Crear nuevo empleado |

### Ejemplo de request POST

```json
{
  "name": "Juan Pérez",
  "position": "Contador",
  "salary": 1500.00
}
```

### Ejemplo de response exitoso

```json
{
  "status": 201,
  "message": "Empleado creado exitosamente.",
  "success": true,
  "data": {
    "id": 1,
    "name": "Juan Pérez",
    "position": "Contador",
    "salary": 1500.00
  }
}
```

### Ejemplo de response error

```json
{
  "status": 404,
  "message": "No se encontró el empleado solicitado.",
  "success": false,
  "code": "EMPLOYEE_NOT_FOUND"
}
```

---

## Datos iniciales

Al iniciar la aplicación se cargan automáticamente 3 empleados de prueba si la base de datos está vacía.

---

## Notas

- Si Docker no está corriendo, la aplicación no podrá conectarse a la base de datos.
- El proyecto utiliza `ddl-auto=update`, las tablas se crean automáticamente al iniciar.
- Las búsquedas por posición no distinguen entre mayúsculas y minúsculas.
