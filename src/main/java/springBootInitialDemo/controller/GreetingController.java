package springBootInitialDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springBootInitialDemo.dto.GreetingResponse;
import springBootInitialDemo.service.IGreetingService;

@RestController
public class GreetingController{

    private final IGreetingService greetingService;
    private final GreetingModelAssembler assembler;

    @Autowired
    public GreetingController(IGreetingService greetingService, GreetingModelAssembler assembler) {
        this.greetingService = greetingService;
        this.assembler = assembler;
    }

    /**
     *
     * The spring boot exception IllegalStateException:
     * Ambiguous mapping. Cannot map method occurs when two methods in the rest
     * controller class are configured with the same request mapping url.
     * Two methods in the rest controller class should not be configured
     * using the same url.
     *
     * @return
     */
    //@GetMapping(produces = { "application/hal+json" })
    @GetMapping(value = "/v1", produces = { "application/hal+json" })
    public ResponseEntity<?> helloWorld() {

        GreetingResponse greetingResponse = greetingService.getGreeting();

        System.out.println(greetingResponse.toString());

        EntityModel<GreetingResponse> entityModel = assembler.toModel(greetingResponse);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

    @GetMapping(produces = { "application/hal+json" })
    public ResponseEntity<?> helloWorld(
            @RequestParam(value = "name", defaultValue = "WORLD") String name) {

        GreetingResponse greetingResponse = greetingService.getGreeting(name);

        System.out.println(greetingResponse.toString());

        EntityModel<GreetingResponse> entityModel = assembler.toModel(greetingResponse);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
