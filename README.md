# **Introducción a Rest y Spring**

## Descripción del repositorio:

El objetivo de este repositorio es repasar el framework Spring de Java para hacer una aplicación tipo API para consulta de una base de datos a través de los métodos HTTP.

## Ejecutar el proyecto

  ## Ejecutar el proyecto

1. Contar con las dependencias instaladas ejecutando:
   ```bash
   mvn install
2. Ejecutar el archivo SQL dentro de PostgreSQL ubicado en:
   ```bash
   src/main/resources/database.sql
3. Iniciar el servidor de Spring Boot con:
   ```bash
   mvn spring-boot:run
    
## Listado de algunas funciones de la clase controlador UsuarioRest utilizadas y su uso
  
  ### Solicita a la base de datos información de un usuario por medio de su id por medio de metodo GET
  ```bash
   @GetMapping("/usuarios/{id}")
    /*public ResponseEntity<Usuario> obtenUsuario (@PathVariable("id") Integer idUsuario){
        
        log.info("Buscando usuario con id:" + idUsuario);
        Optional <Usuario> usuario = UsuarioDAO.findById(idUsuario);

        //return ResponseEntity.ok(usuario);
        return ResponseEntity.of(usuario);
    }*/

    public ResponseEntity<Usuario> obtenUsuario (@PathVariable("id") Integer idUsuario, String cadena, Integer numero){
        
        log.info("Buscando usuario con id:" + idUsuario);
        log.info("Cadena" + cadena + ", numero" + numero);
        //Optional <Usuario> usuario = UsuarioDAO.findById(idUsuario);
        Usuario usuario = UsuarioDAO.findById(idUsuario).get();

        return ResponseEntity.ok(usuario);
        //return ResponseEntity.of(usuario);
    }
  ```
  ### Crea un nuevo usuario y lo guarda en la base de datos por medio del metodo POST
  ```bash
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> crearUsuario (@RequestBody UsuarioDTO usuario){

        Usuario usuarioACrear = new Usuario();
        usuarioACrear.setNombre(usuario.getNombre());
        usuarioACrear.setEmail(usuario.getEmail());
        usuarioACrear.setPassword(usuario.getPassword());
        Usuario usuarioNuevo = UsuarioDAO.save(usuarioACrear);

        log.info("Guardar usuario con la info:" + usuario);
        return ResponseEntity.ok(usuarioNuevo);

    }
  ```
  ### Actualiza un usuario y guarda los cambios en la base de datos por medio del metodo PUT
  ```bash
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario (@PathVariable ("id") Integer id, UsuarioDTO infoUsuario){
        Optional <Usuario> usuario = UsuarioDAO.findById(id);
        if(usuario.isPresent()){
            Usuario usuarioAActualizar = usuario.get();
            usuarioAActualizar.setNombre(infoUsuario.getNombre());
            usuarioAActualizar.setEmail(infoUsuario.getEmail());
            usuarioAActualizar.setPassword(infoUsuario.getPassword());
            UsuarioDAO.save(usuarioAActualizar);
            return ResponseEntity.ok(usuarioAActualizar);

        }else{
            return ResponseEntity.of(usuario);
        }
    }
```
   ### Elimina un usuario y guarda los cambios en la base de datos por medio del metodo DELETE
   ```bash
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map <String,String>> borrarUsuari (@PathVariable ("id") Integer id){
        compraDAO.borrarPorIdUsuario(id);

        UsuarioDAO.deleteById(id);

        Map <String,String> respuesta = Collections.singletonMap("respuesta", "Usuario eliminado");

        return ResponseEntity.ok(respuesta);
    }

```




  


