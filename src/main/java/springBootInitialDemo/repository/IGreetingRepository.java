package springBootInitialDemo.repository;

import springBootInitialDemo.dto.GreetingResponseDto;

/**
 * Interface de la capa Repository
 *
 */
public interface IGreetingRepository {

    public GreetingResponseDto getGreeting(String greeting);

    public GreetingResponseDto getGreeting();
}
