package springBootInitialDemo.service;

import springBootInitialDemo.dto.GreetingResponse;

public interface IGreetingService {

    GreetingResponse getGreeting(String greeting);
    GreetingResponse getGreeting();
}
