# Documentación de Proyecto Gym

## Descripción
Este proyecto **tiene como objetivo mostrar cómo se evaluan las habilidades de un desarrollador la creacion de APIs RESTful y en el manejo de bases de datos** A continuación, se proporciona una descripción general de las principales componentes y funcionalidades del proyecto.


## Características
El proyecto consiste en el desarrollo de una API **con tecnología Java utilizando el framework Spring Boot**. Este proyecto tiene como objetivo crear rutas e implementar acciones que permitan gestionar distintas operaciones y consultas a bases de datos. A continuación, se detallan las principales características de este proyecto:

1. **API**: La aplicación desarrollada se encarga de consumir la API  obteniendo información relevante sobre distintos clientes y disciplinas. Se crea una API que ofrece diversas funcionalidades incluyendo:
    - Búsqueda de clientes y disciplinas por id.
    - Obtención del listado de clientes, disciplinas y facturas.
    - Acceso a los datos de un cliente y disciplina por id.
    - Listado completo de facturas realizadas.
    - Creacion de disciplinas nuevas.
    - Guardado de clientes en base de datos.
    - Eliminar y modificar clientes, disciplinas y facturas por id.
    - Actualizar y modificar clientes y disciplinas.

2. **Almacenamiento en Base de Datos**: Se implementa un esquema de base de datos, preferiblemente MySQL, para almacenar toda la información necesaria.


## Configuración
Antes de ejecutar la aplicación, es necesario configurar las siguientes propiedades en el archivo `application.properties`:

Se debe de configurar en el archivo application.properties las propiedades correspondientes para MySQL:
```properties
spring.datasource.url=jdbc:mysql://<Tu host>:<Tu puerto>/<Tu base de datos>
spring.datasource.username=<Tu username>
spring.datasource.password=<Tu password>
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
```

Ademas se configurar las propiedades de Swagger para visualizar las operaciones en la ruta '**/swagger-ui.html**'
```properties
springdoc.swagger-ui.enabled=true
springdoc.api-docs.enabled=true
springdoc.swagger-ui.path=/swagger-ui.html
```


## Paquetes del Proyecto
El proyecto está organizado en los siguientes paquetes:

- `com/app/gym_management/dto`: Contiene clases DTO.
- `com/app/gym_management/mapper`: Contiene mapeadores para convertir entre entidades y DTO.
- `com/app/gym_management/persistance/models`: Define entidades y modelos de la base de datos.
- `com/app/gym_management/persistance/repository`: Define repositorios para acceder a la base de datos.
- `com/app/gym_management/services`: Define interfaces de servicio.
- `com/app/gym_management/service/impl`: Implementaciones de servicios.
- `com/app/gym_management/controllers`: Controladores web para manejar las solicitudes de la API.
- `com/app/gym_management/config`: Clases de configuracion.

## Clases Principales
A continuación, se describen algunas de las clases principales del proyecto:

### `ClientRepository` , `DisciplineRepository ` y ` InvoiceRepository`
- Repositorio que interactúa con la base de datos para obtener información sobre los clientes, las disciplinas y las facturas. Proporciona métodos para operar sobre los elementos en la base de datos.


### `IInvoiceService` , `IDisciplineService` y `IClientService`
- Interfaces de servicio que definen operaciones relacionadas con clientes, disciplinas y facturas. Sus implementaciones se encuentran en `IInvoiceServiceImpl` , `DisciplineServiceImpl`y`ClientServiceImpl` respectivamente.

### Controladores
- Los controladores, como `InvoiceController`, `ClientController`, y `DisciplineController`, gestionan las solicitudes HTTP y responden con datos a los clientes.


## Manejo de Excepciones
El proyecto incluye un sistema de manejo de excepciones que garantiza una respuesta adecuada a diferentes tipos de errores, como errores de autenticación y errores en las solicitudes. El manejo de excepciones se realiza a través de la libreria Validator.

## Scripts SQL
El proyecto incluye la configuración necesaria para que las tablas se creen automaticamente al ejecutar el proyecto.
Por defecto esta configuración se encuentra comentada pero basta con descomentar las siguientes propiedades para que se creen las tablas:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
```

## Swagger
Si se desea, se puede utilizar Swagger para visualizar las operaciones disponibles dentro de la app y operar sobre ellas. [Click aquí para ir a interfaz de Swagger](http://localhost:8080/swagger-ui/index.html)

## Resumen
Este documento proporciona una visión general de la estructura del proyecto y sus componentes clave. Asegúrate de configurar correctamente la clase application.properties antes de ejecutar la aplicación para que funcione correctamente.