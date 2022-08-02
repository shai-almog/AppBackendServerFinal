package com.codename1.app.backend.webservices;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/key")
public class KeyService {
    private static final String GOOGLE_MAPS_KEY = "AIzaSyB6tFx4RS0BYngJOehhshYu6-Jn1IG0yc8";
    
    
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody String getKey(@RequestParam(value="type", required=true) String type,
            @RequestParam(value="appId", required=true) String appId) {
        if(type.equals("gmk") && appId.equals("rest")) {
            return GOOGLE_MAPS_KEY;
        }
        
        return null;
    }
}