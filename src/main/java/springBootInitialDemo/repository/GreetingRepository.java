package springBootInitialDemo.repository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springBootInitialDemo.dto.GreetingResponse;

import java.util.concurrent.atomic.AtomicLong;

@Repository
@Transactional
public class GreetingRepository implements IGreetingRepository {

    @Autowired
    private GreetingResponse greetingResponse;

    private AtomicLong id = new AtomicLong();

    private static final String TEMPLATE = "HELLO %s";
    private String defaultValue="WORLD";

    @Override
    public GreetingResponse getGreeting() {
        greetingResponse.setContent(String.format(TEMPLATE,defaultValue));
        greetingResponse.setId(id.incrementAndGet());
        return greetingResponse;
    }

    @Override
    public GreetingResponse getGreeting(String greeting) {
        greetingResponse.setContent(String.format(TEMPLATE, greeting));
        greetingResponse.setId(id.incrementAndGet());
        return greetingResponse;
    }

}
