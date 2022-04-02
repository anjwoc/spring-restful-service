package com.example.restfulwebservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {
    @Autowired
    private MessageSource messageSource;
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

    @GetMapping(path = "/internationalized")
    public String Internationalized(@RequestHeader(name="Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
