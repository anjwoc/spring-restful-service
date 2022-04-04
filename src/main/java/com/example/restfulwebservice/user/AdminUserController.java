package com.example.restfulwebservice.user;

import com.example.restfulwebservice.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    private UserDaoService service;

    public AdminUserController(UserDaoService service) {
        this.service = service;
    }

    // test
    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = service.findAll();

    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
            .filterOutAllExcept("id", "name", "joinDate", "ssn");

    FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
    MappingJacksonValue mappingValue = new MappingJacksonValue(users);
        mappingValue.setFilters(filters);

        return mappingValue;
}

    // GET /admin/users/1 - /admin/v1/users/1
//    @GetMapping(value = "/v1/users/{id}")
//    @GetMapping(value = "/users/{id}", params = "version=1")
    //    @GetMapping(value="/users/{id}", headers = "X-API-VERSION=1")
    @GetMapping(value="/users/{id}", produces = "application/lec.company.app.v1+json")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn");

        MappingJacksonValue mappingValue = new MappingJacksonValue(user);
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        mappingValue.setFilters(filters);

        return mappingValue;
    }

//    @GetMapping(value = "/v2/users/{id}")
//    @GetMapping(value = "/users/{id}", params = "version=2")
//    @GetMapping(value="/users/{id}", headers = "X-API-VERSION=2")
    @GetMapping(value="/users/{id}", produces = "application/lec.company.app.v2+json")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // User -> User2
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2);
        userV2.setGrade("VIP");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "grade");

        MappingJacksonValue mappingValue = new MappingJacksonValue(userV2);
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);
        mappingValue.setFilters(filters);

        return mappingValue;
    }

}
