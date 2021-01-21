package springBootInitialDemo.repository;

import springBootInitialDemo.dto.GreetingResponse;

public interface IGreetingRepository {

    public GreetingResponse getGreeting(String greeting);

    public GreetingResponse getGreeting();
}
