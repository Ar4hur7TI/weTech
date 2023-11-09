package com.hex.wetech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ViewController
 *
 * @author Guofeng Lin
 * @since 2023/11/9
 */
@Controller
public class ViewController {

    @RequestMapping(value = "/")
    public String index() {
        System.out.println("index");
        return "index";
    }
}
