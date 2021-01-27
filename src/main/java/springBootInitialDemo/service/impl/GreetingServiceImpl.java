package springBootInitialDemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springBootInitialDemo.dto.GreetingResponseDto;
import springBootInitialDemo.repository.GreetingRepository;
import springBootInitialDemo.service.IGreetingService;

/**
 * Clase de la capa Service, implementa la interface IGreetingService
 *
 * Anotaciones:
 * @Service
 * Indica que la clase es un "Servicio", esto es, una operación ofrecida como una interfaz que está solo en el modelo,
 * sin un estado encapsulado".
 * Sirve como una especialización de @Component, lo que permite que las clases de implementación se detecten
 * automáticamente a través del escaneo del classpath
 *
 * @Autowired
 * Marca un constructor, campo, método setter o método de configuración para ser detectado
 * automáticamente por la funcionalidad de inyección de dependencias de Spring
 *
 */
@Service
public class GreetingServiceImpl implements IGreetingService {

    private final GreetingRepository greetingRepository;

    /**
     * Constructor de la clase, parametrizado con la la clase GreetingRepository
     * Marcado con la anotacion @Autowired, la clase de servicio es automaticamente
     * detectada por Spring
     *
     * @param greetingRepository, objeto de tipo GreetingRepository
     */
    @Autowired
    public GreetingServiceImpl(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    /**
     * Delega en el objeto GreetingRepository, accediendo a uno de sus metodos
     * para retornar un objeto de tipo GreetingResponseDto
     *
     * @return objeto de tipo GreetingResponseDto devuelto por el GreetingRepository,
     * informado/seteado usando el valor por defecto "HELLO WORLD"
     */
    @Override
    public GreetingResponseDto getGreeting() {
        return greetingRepository.getGreeting();
    }

    /**
     * Delega en el objeto GreetingRepository, accediendo a uno de sus metodos
     * para retornar un objeto de tipo GreetingResponseDto
     *
     * @param greeting, valor de tipo String que sirve como parametro en la
     *                  invocacion al metodo del GreetingRepository
     * @return objeto de tipo GreetingResponseDto devuelto por el GreetingRepository,
     * informado/seteado usando el valor pasado como parametro
     */
    @Override
    public GreetingResponseDto getGreeting(String greeting) {
        return greetingRepository.getGreeting(greeting);
    }

}
