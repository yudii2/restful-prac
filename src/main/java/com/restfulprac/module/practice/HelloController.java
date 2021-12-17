package com.restfulprac.module.practice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller //boot가 라이프사이클을 가지는데, component scanner가 어떤 bean들을 생명주기에 올릴 것인지를 결정하는데, 해당 어노테이션을 통해 bean으로 등록해줌
@RestController //Controller + ResponseBody(모든 메서드에) : api를 만들 것 -> 우리 서버에는 html이 존재하지 않을 것.
public class HelloController {

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello world";
    }

    @GetMapping(path = "/hello-world-list")
    public List<String> helloWorldList() {
        List<String> messages = new ArrayList<>();
        messages.add("hi");
        messages.add("hello");
        messages.add("welcome");
        return messages;
    }

    @GetMapping(path = "/hello-world-map")
    public Map<String, String> helloWorldMap() {
        Map<String, String> messages = new HashMap<>();
        messages.put("1", "hello");
        messages.put("2", "hi");
        messages.put("3", "welcome");
        return messages;
    }

    @GetMapping(path = "/hello-world-object")
    public HelloObject helloWorldObject() {
        return new HelloObject("1", "HelloWorld!");
    }

    @GetMapping(path = "/hello-world-object-list")
    public List<HelloObject> helloWorldObjectList() {
        List<HelloObject> response = new ArrayList<>();
        response.add(new HelloObject("1", "Hello World"));
        response.add(new HelloObject("2", "Hi"));
        response.add(new HelloObject("3", "Welcome"));

        return response;
    }

    @GetMapping(path = "/hello-world/{name}")
    public ResponseEntity helloWorldName(@PathVariable(value = "name") String name) {    //파라미터명을 다르게 하고 싶다면 value를 지정해주면 됨
        return ResponseEntity.status(HttpStatus.OK).body(String.format("Hello, %s", name)); //파라미터 name값을 s에 담아라

    }

    @GetMapping(path = "/hello-world-param")
    public ResponseEntity helloWorldParam(@RequestParam(required = false, defaultValue = "My name") String name) {   //value를 지정하지 않으면 변수명과 일치하는 파라미터값을 찾는다.
        return ResponseEntity.status(HttpStatus.OK).body(String.format("Hello, %s", name)); //파라미터 name값을 s에 담아라
    }
}

class HelloObject {
    private String id;
    private String message;

    public HelloObject(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }
}
