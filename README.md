# Libreria Javier Arreaza

### Entidades
- Se han creado las entidades Libro y Autor, con una relacion entre ellos de @ManyToOne.
- Se ha creado la entidad User que implementa UserDetails para poder utilizarlo junto con Spring Security y utilizar tokens para validar/registrar usuarios

Las entidades Libro y Autor se encuentran en el paquete Models / Entity.
La entidad Usuario se encuentra en el mismo paquete de Models / User.

Empleo H2 como BBDD, con una directiva Create-Drop.
La carga inicial de datos se encuentra en el paquete Resources en el archivo import.sql.

### Controladores
En el paquete Controllers encontramos 2 clases controladoras:
- AuthController: Gestiona el login y el registro de usuarios.
- LibroRestController: Gestiona los endpoints para las operaciones CRUD de los libros.

### DTO
En el paquete Models se encuentra el paquete Dto donde se desarrollan las clases para los dto de Libro y Autor.
Ambas implementan la interfaz Serializable para que puedan ser enviados a traves de la red.
- LibroDto: Se sustituye el Objeto Autor por un campo Long que identifica el Id del Autor.
- AutorDto: Omite la lista de Libros que posee el Autor para simplificar su visualizacion.
Se aplican Validaciones a ciertos campos para asegurar la consistencia de datos.
Se ha implementado un Mapper propio en el paquete Models/Mapper para transformar un Dto a Entity y viceversa.

### Servicios
Los services se encuentran en el paquete Models/Service, haciendo uso de DAOs se realizan las operaciones CRUD Transaccionales.
Estos servicios manejan directamente los Dtos para un codigo mejor encapsulado.

### JWT
En el paquete Security encontramos la configuracion de Spring Security en la clase SecurityConfig, aqui cambiamos la seguridad de nuestra aplicacion para que en vez de usar Login implemente autorizacion y autenticacion por Tokens.
Para generar estos Tokens usamos JWT implementando los filtros y el servicio de esta en la carpeta jwt dentro de Security.
Tanto las Solicitudes como Respuestas se gestionan desde las clases dentro de Auth.

Todo esto proporciona tokens cuando se registra/loggea un usuario y dependiendo del rol de este podrá usar X endpoints (configurado en SecurityConfig)

### CORSS
Para permitir todas las llamadas desde cualquier origen (o restringirlo) se ha creado la clase CorsConfig dentro del paquete Security.

### Excepciones Centralizadas
Dentro del paquete Exception se han creado 2 clases para resolver posibles excepciones en tienpo de ejecucion en los controladores REST.
Estas excepciones personalizadas se gestionan a su vez en una clase Global que permite enviar una respuesta personalizada para las excepciones propias.

### Swagger
En el archivo Propperties se ha habilitado la documentacion Swagger, ademas en la seguridad se ha permitido acceder a esta ruta sin la necesidad de estar autenticado por token.
Para poder acceder a la documentacion entrar en la URL = http://localhost:8080/swagger-ui/index.html

### DAO
Para realizar las consultas se ha incluido DAOs o Repositorios en el paquete Models. Estos implementan una interfaz propia que hereda de JPA, permitiendo usar consultas derivadas para operaciones CRUD.

