package net.javaguides.springbootrestfulwebservices;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot REST API Documentation",
                description = "Spring Boot REST API Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Vasilii",
                        email = "com.vsushko@gmail.com",
                        url = "https://www.vsprog.net"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.vsprog.com/license"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot User Management Documentation",
                url = "https://www.vsprog.com/user_management.html"
        )
)
@SpringBootApplication
public class SpringbootRestfulWebservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
    }
}
