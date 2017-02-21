package com.desha.Controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DEJAN on 21/02/17 at 19:45.
 */
@RestController
public class ISA_ErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling done right! ~ Desha";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
