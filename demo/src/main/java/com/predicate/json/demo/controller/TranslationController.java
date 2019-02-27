package com.predicate.json.demo.controller;

import com.weddini.throttling.Throttling;
import com.weddini.throttling.ThrottlingType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.predicate.json.demo.parser.antlr4.PredicateParser;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
public class TranslationController {

    private static final Log LOGGER = LogFactory.getLog(TranslationController.class);

    @Autowired
    private PredicateParser parser;

    @PostMapping("/toJson")
    @Throttling(type = ThrottlingType.RemoteAddr, limit = 1, timeUnit = TimeUnit.SECONDS)
    Translation toJson(
            @RequestParam("datalog") final String datalog
            , final HttpServletRequest request) {
        LOGGER.info("Remote Address " + request.getRemoteAddr() + " requested for toJson");
        return new Translation(parser.parse(datalog));
    }

}
