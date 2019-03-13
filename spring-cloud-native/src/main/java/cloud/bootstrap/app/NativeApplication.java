package cloud.bootstrap.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NativeApplication {

    public static void main(String[] args) {

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.setId("booxj");
//        context.registerBean("message", String.class, new String());
//        context.refresh();

//        new SpringApplicationBuilder(NativeApplication.class).parent(context).run(args);
        new SpringApplicationBuilder(NativeApplication.class).run(args);
    }


    @Autowired
    @Qualifier("message")
    private String message;

    @Value("${spring.application.name}")
    private String name;

    @Bean("message")
    public String Message() {
        return "Hello World!";
    }

    @GetMapping("hi")
    public String hi() {
        return message;
    }

    @GetMapping("name")
    public String name() {
        return name;
    }
}
