# Foro Hub

El proyecto consiste en la creación de un foro.
Este fue creado para demostrar las habilidades en Spring.

--------------------------Herramientas utilizadas--------------------------
Se utilizo Spring Boot 3 y Maven 4.
A este se le agregaron las siguientes dependencias:
  -Lombok
  -Spring Web
  -Spring Boot DevTools
  -Spring Data JPA
  -Flyway Migration
  -MySQL Driver
  -Validation
  -Spring Security
Al igual se utilizo Java 17 y MySQL 8.


--------------------------Configuración de las variables de entorno--------------------------
Para mantener los datos protegidos se utilizó variables de entorno.
DB_URL_FH -> Url donde se encueentra la base de datos que deseamos utilizar.
DB_USER_FH -> El usuario de nuestra base de datos
DB_PASS_FH -> La contraseña de nuestra base de datos.
JWT_SECRET -> El clave de encriptación para nuestros TokensJWT, esta tiene una definida por defecto.
Debemos agragar estas variables a nuestra computadora (recordando que hay que reiniciarla), con nuestros respectivos valores.
Si deseamos cambiar los nombres de las variables lo podemos hacer en el archivo "src/main/resources/application.properties"


--------------------------Enviando datos--------------------------
Creando un Usuario
Cualquiera puede crear un usuario, no se nesecita de una autorizacion, esto seria en la url "/usuarios" con un método POST.
El JSON que se debe enviar es el siguiente:
{
    "nombre": "nombre",
    "correoElectronico": "correo",
    "contrasena": "pass"
}
Una vez creado ya puede iniciar sesión.

Creando un Curso
Solo los usuarios ADMIN y DEV pueden crear un curso, ya vienen un usuario de cada uno por defecto:
"correo":"admin@lsh.com"  "contrasena":"12345678"
"correo":"dev@lsh.com"  "contrasena":"12345678"
Por el momento no esta incluida la funcionalidad de crear más ADMINs y DEVs.
Para crear un Curso hay que mandar el Token de autentificación y un JSON con la seguiente estructura:
{
    "nombre":"nombre",
    "categoria":"CATEGORIA"
}
Las categorias se encuantran en el archivo "cursos

