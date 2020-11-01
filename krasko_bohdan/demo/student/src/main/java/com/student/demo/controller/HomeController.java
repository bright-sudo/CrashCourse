package com.student.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    @RequestMapping(value={"/test", "/{studentId}/courses"} )
    public String index() {
        return "/";
    }
}