package springBootInitialDemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springBootInitialDemo.dto.GreetingResponseDto;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Clase de la capa Repository, implementa la interface IGreetingRepository
 *
 * Anotaciones:
 * @Repository
 * Indica que la clase es un "Repositorio", es decir, un mecanismo para encapsular el comportamiento
 * de almacenamiento, recuperación y búsqueda que emula una colección de objetos.
 * Esta anotación también sirve como una especialización de @Component, lo que permite que
 * la clase se detecte automáticamente a través del escaneo del classpath.
 * Implica un enfoque de tipo Domain-Driven Design, que es el concepto de que la estructura y el lenguaje
 * del código (nombres de clase, métodos de clase, variables de clase) deben coincidir con el dominio empresarial.
 * DDD (Domain-Driven Design) conecta la implementación con el dominio empresarial.
 *
 * @Transactional
 * Describe un atributo de transacción en la clase, es decir, da soporte a la transacionalidad
 *
 * @Autowired
 * Marca un constructor, campo, método setter o método de configuración para ser detectado
 * automáticamente por la funcionalidad de inyección de dependencias de Spring
 *
 */
@Repository
@Transactional
public class GreetingRepository implements IGreetingRepository {

    @Autowired
    private GreetingResponseDto greetingResponseDto;

    private AtomicLong id = new AtomicLong();

    /**
     * Variable de tipo String utilizada como plantilla para dar valor a
     * una de las propiedades del objeto GreetingResponseDto, utilizando
     * el formato "HELLO <NAME>", donde <NAME> es un String parametrizable
     */
    private static final String TEMPLATE = "HELLO %s";
    private String defaultValue="WORLD";

    /**
     * Metodo de la interface IGreetingRepository, implementada por la clase.
     * Retorna un objeto de tipo GreetingResponseDto informando/seteando con
     * un valor sus atributos o propiedades.
     * Metodo sin parametros
     *
     * @return objeto de tipo GreetingResponseDto informado/seteado con un valor
     * por defecto de "HELLO WORLD"
     */
    @Override
    public GreetingResponseDto getGreeting() {
        greetingResponseDto.setContent(String.format(TEMPLATE,defaultValue));
        greetingResponseDto.setId(id.incrementAndGet());
        return greetingResponseDto;
    }

    /**
     * Metodo de la interface IGreetingRepository, implementada por la clase.
     * Retorna un objeto de tipo GreetingResponseDto informando/seteando con
     * un valor sus atributos o propiedades.
     * Hace uso de la variable String pasada como parametro, para retornar
     * la cadena "HELLO <String param>", encapsulada en uno de los atributos
     * del DTO
     *
     * @param greeting, tipo String utilizado para setear el valor de uno de
     *                 los atributos del objeto GreetingResponseDto
     * @return objeto de tipo GreetingResponseDto informado/seteado con el valor
     * que parametriza al metodo
     */
    @Override
    public GreetingResponseDto getGreeting(String greeting) {
        greetingResponseDto.setContent(String.format(TEMPLATE, greeting));
        greetingResponseDto.setId(id.incrementAndGet());
        return greetingResponseDto;
    }

}
