package com.predicate.json.demo.controller;

import com.weddini.throttling.Throttling;
import com.weddini.throttling.ThrottlingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.predicate.json.demo.parser.antlr4.DatalogJSONConverter;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class TranslationController {

    private final Logger logger  = Logger.getLogger(TranslationController.class.getName());

    @Autowired
    private DatalogJSONConverter parser;

    @PostMapping("/toJson")
    @Throttling(type = ThrottlingType.RemoteAddr, limit = 1, timeUnit = TimeUnit.SECONDS)
    Translation toJson(
            @RequestParam("datalog") final String datalog
            , final HttpServletRequest request) {
        logger.log(Level.INFO, "Remote Address {0} requested for toJson", request.getRemoteAddr());
        return new Translation(parser.convert(datalog));
    }

}
