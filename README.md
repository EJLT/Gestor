# Gestor de Información

Este proyecto es un gestor de información que permite a los usuarios crear, editar, eliminar y filtrar publicaciones, comentarios, usuarios, categorías y etiquetas.

## Tecnologías utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Lombok
- PostgreSQL
- Maven

## Configuración

1. Clona este repositorio en tu máquina local.
2. Configura la base de datos PostgreSQL en tu entorno local.
3. Actualiza las configuraciones de la base de datos en el archivo `application.properties`.
4. Ejecuta la aplicación utilizando Maven o tu IDE preferido.

## Endpoints

### Usuarios

- `GET /users`: Obtiene todos los usuarios o filtra por nombre de usuario, nombre completo o correo electrónico.
- `GET /users/{id}`: Obtiene un usuario por su ID.
- `POST /users`: Crea un nuevo usuario.
- `PUT /users/{id}`: Actualiza un usuario existente.
- `DELETE /users/{id}`: Elimina un usuario por su ID.

### Publicaciones

- `GET /publications`: Obtiene todas las publicaciones o filtra por título, usuario o categoría.
- `GET /publications/{id}`: Obtiene una publicación por su ID.
- `POST /publications`: Crea una nueva publicación.
- `PUT /publications/{id}`: Actualiza una publicación existente.
- `DELETE /publications/{id}`: Elimina una publicación por su ID.

### Comentarios

- `GET /comments`: Obtiene todos los comentarios o filtra por texto o ID de publicación.
- `GET /comments/{id}`: Obtiene un comentario por su ID.
- `POST /comments`: Crea un nuevo comentario.
- `PUT /comments/{id}`: Actualiza un comentario existente.
- `DELETE /comments/{id}`: Elimina un comentario por su ID.

### Categorías

- `GET /categories`: Obtiene todas las categorías o filtra por nombre.
- `GET /categories/{id}`: Obtiene una categoría por su ID.
- `POST /categories`: Crea una nueva categoría.
- `PUT /categories/{id}`: Actualiza una categoría existente.
- `DELETE /categories/{id}`: Elimina una categoría por su ID.

- ### Tag

- `GET /tags`: Obtiene todas las etiquetas o filtra por nombre.
- `GET /tags/{id}`: Obtiene una etiqueta por su ID.
- `POST /tags`: Crea una nueva etiqueta.
- `PUT /tags/{id}`: Actualiza una etiqueta existente.
- `DELETE /tags/{id}`: Elimina una etiqueta por su ID.
- 
### Ejemplo de Creación de Usuario

```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"username": "usuario1", "fullName": "Usuario Uno", "email": "usuario1@example.com", "password": "password123"}' \
  http://localhost:8080/users

## Contribución

¡Las contribuciones son bienvenidas! Si deseas contribuir a este proyecto, no dudes en abrir un issue o enviar un pull request.

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.
