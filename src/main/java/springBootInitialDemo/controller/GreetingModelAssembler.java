package springBootInitialDemo.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import springBootInitialDemo.dto.GreetingResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GreetingModelAssembler implements RepresentationModelAssembler<GreetingResponse, EntityModel<GreetingResponse>> {

    @Override
    public EntityModel<GreetingResponse> toModel(GreetingResponse greetingResponse) {

        String requestParamValue= greetingResponse.getContent().substring(greetingResponse.getContent().indexOf(" ") + 1);

        return EntityModel.of(greetingResponse,
                linkTo(methodOn(GreetingController.class).helloWorld(requestParamValue)).withSelfRel(),
                linkTo(methodOn(GreetingController.class).helloWorld()).withSelfRel());
    }
}
