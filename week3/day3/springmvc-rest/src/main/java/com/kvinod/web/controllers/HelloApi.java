package com.kvinod.web.controllers;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kvinod.entity.Person;
import com.kvinod.entity.PersonList;

// import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController;

// @Controller
@RestController
public class HelloApi {

    @RequestMapping("/api/hello")
    // @ResponseBody
    public String hello() {
        return "/Hello.jsp";
    }

    @RequestMapping("/api/info")
    // @ResponseBody
    public Map<String, Object> info() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "Vinod");
        map.put("email", "vinod@vinod.co");
        map.put("timestamp", new Date());
        map.put("isIndian", true);
        return map;
    }

    @RequestMapping(path = "/api/persons", produces = { "application/json" })
    // @ResponseBody
    public List<Person> personsAsJson() {
        return Arrays.asList(new Person("Vinod Kumar", "vinod@vinod.co", "9731424784", "Bangalore"),
                new Person("Shyam", "shyam@xmpl.com", "9731424000", "Shivamogga"));
    }

    @RequestMapping(path = "/api/persons", produces = { "application/xml" })
    // @ResponseBody
    public PersonList personsAsXml() {
        return new PersonList(Arrays.asList(new Person("Vinod", "vinod@vinod.co", "9731424784", "Bangalore"),
                new Person("Shyam", "shyam@xmpl.com", "9731424000", "Shivamogga")));
    }

    @RequestMapping(path = "/api/persons/{id}", produces = { "application/json", "application/xml" })
    // @ResponseBody
    public Person person(@PathVariable Integer id) {
        return new Person("Vinod", "vinod@vinod.co", "9731424784", "Bangalore");
    }
}
