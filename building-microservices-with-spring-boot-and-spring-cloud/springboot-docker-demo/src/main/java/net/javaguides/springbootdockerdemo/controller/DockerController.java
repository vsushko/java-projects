package net.javaguides.springbootdockerdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vsushko
 */
@RestController
public class DockerController {

    @GetMapping("/docker")
    public String dockerDemo() {
        return "Dockerizing Spring Boot Application";
    }
}
