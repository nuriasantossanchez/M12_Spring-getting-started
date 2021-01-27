package springBootInitialDemo.service;

import springBootInitialDemo.dto.GreetingResponseDto;

/**
 * Interface de la capa Service
 *
 */
public interface IGreetingService {

    GreetingResponseDto getGreeting(String greeting);
    GreetingResponseDto getGreeting();
}
