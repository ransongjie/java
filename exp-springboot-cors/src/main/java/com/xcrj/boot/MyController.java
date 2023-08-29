package com.xcrj.boot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/cors")
@RestController
public class MyController {
    @GetMapping("/jsonp/{id}")
    public String test(@PathVariable Integer id, @RequestParam("callback") String callback) {
        Map<String, Object> map = new HashMap();
        map.put("code", 200);
        map.put("data", "xcrj");
        ObjectMapper mapper = new ObjectMapper();

        String result = null;
        try {
            result = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return callback + "(" + result + ")";
    }

    @CrossOrigin("http://localhost:8081")
    @GetMapping("/cors/{id}")
    public String test2(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap();
        map.put("code", 200);
        map.put("data", "xcrj");
        ObjectMapper mapper = new ObjectMapper();

        String result = null;
        try {
            result = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
