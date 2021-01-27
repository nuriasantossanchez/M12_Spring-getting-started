package springBootInitialDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springBootInitialDemo.dto.GreetingResponseDto;
import springBootInitialDemo.service.IGreetingService;

/**
 * Clase de la capa Controller.
 * La anotacion @RestController convierte a la aplicacion en un REST Service, basado en el intercambio
 * de recursos (elementos de información) entre componentes de la red, clientes y servidores, que
 * se comunican a través del protocolo HTTP
 *
 * Anotaciones:
 *
 * @RestController
 * Anotación que a su vez está anotada con @Controller y @ResponseBody.
 * Los tipos que llevan esta anotación se tratan como controladores donde los métodos @RequestMapping
 * asumen la semántica @ResponseBody por defecto
 *
 * @Autowired
 * Marca un constructor, campo, método setter o método de configuración para ser detectado
 * automáticamente por la funcionalidad de inyección de dependencias de Spring
 *
 * @GetMapping(produces = {"application/hal+json"})
 * Anotación para mapear solicitudes HTTP GET en métodos del controlador.
 * @GetMapping es una anotación compuesta que actúa como un atajo para @RequestMapping(method = RequestMethod.GET).
 * Hace uso del atributo produces para indicar que la respuesta en formato JSON sera de tipo
 * Hypertext Application Language
 * Al no especificar ni el atributo path ni su alias value, el punto de acceso a la peticion será
 * http://localhost:8080, en este caso
 *
 * @GetMapping(value = "/v1", produces = { "application/hal+json" })
 * Igual que la anterior pero añadiendo a la anotacion el atributo value="/v1", por lo que el punto de acceso
 * a la peticion será http://localhost:8080/v1
 *
 */
@RestController
public class GreetingController{

    private final IGreetingService greetingService;
    private final GreetingModelAssembler assembler;

    /**
     * Constructor de la clase, parametrizado con la la interface IGreetingService y la clase GreetingModelAssembler,
     * implementacion de la interface RepresentationModelAssembler
     * Marcado con la anotacion @Autowired, la clase controlador es automaticamente detectada por Spring
     *
     * @param greetingService, interfaz de tipo IGreetingService, implementada por la clase GreetingServiceImpl,
     *                        en la cual se exponen los servicios o funcionalidades accesibles via HTTP
     * @param assembler, instancia de tipo GreetingModelAssembler, convierte un objeto de dominio en un
     *                   RepresentationModel, un EntityModel que envuelve al objeto de dominio y le agrega enlaces
     *
     */
    @Autowired
    public GreetingController(IGreetingService greetingService, GreetingModelAssembler assembler) {
        this.greetingService = greetingService;
        this.assembler = assembler;
    }

    /**
     * Representa el mapeo de una peticion HTTP GET, a la URL http://localhost:8080/v1
     *
     * Accede a la capa de servicio, GreetingServiceImpl, a traves de su interfaz IGreetingService
     * y hace uso de uno de sus metodos para recuperar un objeto de tipo GreetingResponseDto
     * (que retorna el valor por defecto "HELLO WORLD"/"HELLO <NAME>"), en forma de ResponseEntity,
     * agregando enlaces al objeto de dominio GreetingResponseDto
     *
     * En un REST controller no se pueden mapear dos metodos con la misma configuracion url:
     * The spring boot exception IllegalStateException: Ambiguous mapping
     *
     * @return objeto de tipo ResponseEntity, objeto de dominio de tipo GreetingResponseDto al que
     * se le ha agregado enlaces
     */
    //@GetMapping(produces = { "application/hal+json" })
    @GetMapping(value = "/v1", produces = { "application/hal+json" })
    public ResponseEntity<?> helloWorld() {

        GreetingResponseDto greetingResponseDto = greetingService.getGreeting();

        System.out.println(greetingResponseDto.toString());

        EntityModel<GreetingResponseDto> entityModel = assembler.toModel(greetingResponseDto);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

    /**
     * Representa el mapeo de una peticion HTTP GET, a la URL http://localhost:8080
     *
     * Accede a la capa de servicio, GreetingServiceImpl, a traves de su interfaz IGreetingService
     * y hace uso de uno de sus metodos para recuperar un objeto de tipo GreetingResponseDto
     * (que retorna "HELLO WORLD"/"HELLO <NAME>"), en forma de ResponseEntity, agregando enlaces
     * al objeto de dominio GreetingResponseDto
     *
     * @param name, @RequestParam que indica que el parámetro del método debe estar vinculado a
     *              un parámetro de la peticion web. Es de tipo String y tiene un valor por defecto
     * @return objeto de tipo ResponseEntity, objeto de dominio de tipo GreetingResponseDto al que
     * se le ha agregado enlaces
     */
    @GetMapping(produces = { "application/hal+json" })
    public ResponseEntity<?> helloWorld(
            @RequestParam(value = "name", defaultValue = "WORLD") String name) {

        GreetingResponseDto greetingResponseDto = greetingService.getGreeting(name);

        System.out.println(greetingResponseDto.toString());

        EntityModel<GreetingResponseDto> entityModel = assembler.toModel(greetingResponseDto);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
