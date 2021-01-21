package springBootInitialDemo.dto;

import org.springframework.stereotype.Component;

@Component
public class GreetingResponse extends ResponseDto {

    private long id;
    private String content;

    public GreetingResponse() {
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
