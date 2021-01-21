package springBootInitialDemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springBootInitialDemo.dto.GreetingResponse;
import springBootInitialDemo.repository.GreetingRepository;
import springBootInitialDemo.service.IGreetingService;

@Service
public class GreetingServiceImpl implements IGreetingService {

    private final GreetingRepository greetingRepository;

    @Autowired
    public GreetingServiceImpl(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }


    @Override
    public GreetingResponse getGreeting() {
        return greetingRepository.getGreeting();
    }

    @Override
    public GreetingResponse getGreeting(String greeting) {
        return greetingRepository.getGreeting(greeting);
    }

}
