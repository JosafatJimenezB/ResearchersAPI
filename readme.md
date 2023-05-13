# Researchers API :man_student:

Para este reto se utlizo la API de Google Scholar que proporciona un conjunto de servicios y endpoints para acceder a la información y datos relacionados con los autores, publicaciones y citas en Google Scholar. Con esta API, los desarrolladores pueden realizar consultas y obtener resultados de búsqueda personalizados, como información de perfil de autor, citas de un artículo, estadísticas de citas y más.

## Características principales :red_envelope:

- **Búsqueda de autores:** Permite buscar autores por ID, nombre u otros parámetros.
- **Información de perfil de autor:** Proporciona detalles sobre un autor, como su nombre, afiliación, áreas de investigación, citas y más.
- **Búsqueda de publicaciones:** Permite buscar publicaciones por título, autor, palabras clave, etc.
- **Citas de un artículo:** Obtiene información sobre las citas recibidas por un artículo específico.
- **Estadísticas de citas:** Brinda estadísticas de citas para un autor o artículo, como el número total de citas y las citas por año.

## Iniciación del proyecto :rocket:
Haz un fork de este repositorio en tu propia cuenta de GitHub.

Clona el repositorio desde tu cuenta de GitHub a tu máquina local:

```bash
git clone https://github.com/JosafatJimenezB/ResearchersAPI.git
```
Accede al directorio del proyecto:

```bash
cd ResearchersAPI
```
Abre el proyecto en tu entorno de desarrollo preferido (por ejemplo, IntelliJ IDEA, Eclipse).

Configura la conexión a la base de datos MySQL siguiendo los pasos descritos a continuación.

### Configuración de MySQL
Asegúrate de tener un servidor MySQL en ejecución en tu máquina local o en algún otro host accesible.

Abre el archivo src/main/resources/application.properties en tu editor de texto preferido.

Actualiza las siguientes propiedades de conexión a la base de datos con los valores correspondientes:

**properties**
```java
    server.port=9091
    spring.application.name=researchers-api
    server.servlet.context-path=/researchers-api

    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url = jdbc:mysql://localhost:3306/researchers_db?autoReconnect=true&useSSL=false
    spring.datasource.username=<Your user>
    spring.datasource.password=<Your password>
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver spring.jpa.show-sql=true 
        
    com-researchesapi.serpApi.apiKey=<Your Token>
    com-researchesapi.serpApi.google.scholar.author.url=https://serpapi.com/search?engine=google_scholar_author&author_id={0}&api_key={1}    

```

Asegúrate de reemplazar <Your user>, <Your password> y <Your Token> con las credenciales correctas para acceder a tu servidor de MySQL.

Crea una base de datos en tu servidor MySQL llamada researchers_db:

```sql
CREATE DATABASE researchers_db;
```
Guarda los cambios en el archivo application.properties.

Ejecución de la aplicación
Ejecuta la clase principal Application.java para iniciar la aplicación Spring Boot.

La aplicación se ejecutará en el puerto predeterminado 8080 en tu máquina local.

Abre tu navegador web y accede a la siguiente URL para probar la aplicación:

```bash
http://localhost:9091/researchers-api/api/v1/researchers/{query}
```
Reemplaza {query} con el término de búsqueda que desees, como el ID, nombre o fecha de nacimiento del autor. La aplicación buscará en la API de Google Scholar y mostrará los resultados en el navegador.

###### Made with :heart: by JosafatJimenezB