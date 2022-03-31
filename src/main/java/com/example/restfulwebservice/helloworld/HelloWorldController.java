package com.example.restfulwebservice.helloworld;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {
    @GetMapping(path = "/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/bean/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/test/{msg}")
    public HelloWorldBean helloWorldBean2(@PathVariable String msg) {
        HelloWorldBean bean = HelloWorldBean.builder().message(msg).build();
        return bean;
    }

    @PostMapping(path = "/test2")
    public HelloWorldBean beanPostMethod(@RequestBody HelloWorldBean bean) {
        return bean;
    }
}
