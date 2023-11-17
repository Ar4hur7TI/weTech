package com.hex.wetech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ViewController
 *
 * @author Guofeng Lin
 * @since 2023/11/9
 */
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class ViewController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
    @RequestMapping(value = "/show")
    public String show() {
        return "show";
    }
}
