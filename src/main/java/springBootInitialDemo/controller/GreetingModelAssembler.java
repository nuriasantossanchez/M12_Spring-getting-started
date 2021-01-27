package springBootInitialDemo.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import springBootInitialDemo.dto.GreetingResponseDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Clase de la capa Controller
 * Implemente la interfaz RepresentationModelAssembler
 * (pertenece al modulo de Spring HATEOAS, org.springframework.hateoas.server)
 *
 * Convierte un objeto de dominio en un RepresentationModel, que es una clase base
 * para que los DTO recopilen enlaces, un EntityModel simple que envuelve un objeto
 * de dominio y le agrega enlaces.
 *
 * Anotaciones:
 *
 * @Component
 * Indica que una clase es un "componente".
 * Estas clases se consideran candidatas para la detección automática cuando se utiliza una configuración
 * basada en anotaciones y un escaneo de classpath.
 * También se pueden considerar otras anotaciones a nivel de clase como identificación de un componente,
 * normalmente un tipo especial de componente: por ejemplo, la anotación @Repository
 *
 */
@Component
public class GreetingModelAssembler implements RepresentationModelAssembler<GreetingResponseDto, EntityModel<GreetingResponseDto>> {

    /**
     * Metodo abstracto de la interfaz RepresentationModelAssembler
     * Convierte un objeto de tipo GreetingResponseDto en un EntityModel.
     * El objeto EntityModel envuelve a un objeto de dominio y le agrega enlaces
     *
     * @param greetingResponseDto, objeto de tipo GreetingResponse
     * @return objeto de tipo EntityModel que envuelve un objeto de tipo GreetingResponseDto
     * y le agrega enlaces
     */
    @Override
    public EntityModel<GreetingResponseDto> toModel(GreetingResponseDto greetingResponseDto) {

        String requestParamValue= greetingResponseDto.getContent().substring(greetingResponseDto.getContent().indexOf(" ") + 1);

        return EntityModel.of(greetingResponseDto,
                linkTo(methodOn(GreetingController.class).helloWorld(requestParamValue)).withSelfRel(),
                linkTo(methodOn(GreetingController.class).helloWorld()).withSelfRel(),
                linkTo(methodOn(GreetingController.class).helloWorld(null)).withSelfRel());
    }
}
