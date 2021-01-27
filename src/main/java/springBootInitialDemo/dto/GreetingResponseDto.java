package springBootInitialDemo.dto;

import org.springframework.stereotype.Component;

/**
 * Clase de la capa de dominio, implementa el patron Data Transfer Object (DTO Pattern) mediante la
 * creacion de un objeto plano (POJO) con una serie de atributos que puedan ser enviados o recuperados
 * del servidor en una sola invocación (de tal forma que un DTO puede contener información de múltiples
 * fuentes o tablas y concentrarlas en una única clase simple, esto es, crear estructuras de datos
 * independientes del modelo de datos, para transmitir información entre un cliente y un servidor)
 *
 * Anotaciones:
 * @Component
 * Indica que una clase es un "componente".
 * Estas clases se consideran candidatas para la detección automática cuando se utiliza una configuración
 * basada en anotaciones y un escaneo de classpath.
 * También se pueden considerar otras anotaciones a nivel de clase como identificación de un componente,
 * normalmente un tipo especial de componente: por ejemplo, la anotación @Repository
 *
 */
@Component
public class GreetingResponseDto extends ResponseDto {

    private long id;
    private String content;

    public GreetingResponseDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "\nGreetingResponse {" +
                "id=" + id +
                ", '" + content + '\'' +
                '}';
    }
}
