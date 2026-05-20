# necesidades-service DTO + Liquibase

Microservicio de gestion de necesidades para Donaton.

## Ejecutar

```bash
mvn clean install
mvn spring-boot:run
```

Puerto:

```txt
8083
```

## Base de datos

Este proyecto usa Liquibase. Ya no usa `ddl-auto=update`.

```properties
spring.jpa.hibernate.ddl-auto=none
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml
```

Base de datos:

```txt
donaton_necesidades
```

## Crear necesidad

```http
POST http://localhost:8083/api/v1/necesidades
```

```json
{
  "recursoNecesitado": "Agua embotellada",
  "cantidad": 100,
  "ubicacion": "Valparaiso",
  "descripcion": "Familias afectadas necesitan agua potable",
  "prioridad": "URGENTE"
}
```
