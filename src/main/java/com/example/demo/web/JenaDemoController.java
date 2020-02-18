package com.example.demo.web;

import com.example.demo.jena.QueryDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>demo controller</p>
 *
 * @author SageZhang
 * @version 2018/7/25
 */
@RestController
@RequestMapping("/demo")
public class JenaDemoController {
    @Autowired
    private QueryDemo queryDemo;

    @RequestMapping(value = "/search/{param}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> search(@PathVariable("param") String param) {
        return queryDemo.query(param);
    }
}