package com.spring.echoserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class EchoServerController {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServerController.class);
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/**", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            method = {  RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
                        RequestMethod.OPTIONS,RequestMethod.HEAD,RequestMethod.PATCH,RequestMethod.TRACE })
    public ResponseEntity<Map<String, Object>> anyRequest(@RequestBody(required = false) byte[] body) throws IOException {

        Map<String, String> headers = new HashMap<String, String>();
        for (String headerName : Collections.list(request.getHeaderNames())) {
            headers.put(headerName, request.getHeader(headerName));
        }
        Map<String, Object> responseMap = new HashMap<String,Object>();
        responseMap.put("protocol", request.getProtocol());
        responseMap.put("method", request.getMethod());
        responseMap.put("headers", headers);
        responseMap.put("cookies", request.getCookies());
        responseMap.put("parameters", request.getParameterMap());
        responseMap.put("path", request.getServletPath());
        responseMap.put("query_string", request.getQueryString());
        responseMap.put("body", body != null ? new String(body) : null);
        LOG.info("REQUEST: " + request.getRequestedSessionId());

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }
}
