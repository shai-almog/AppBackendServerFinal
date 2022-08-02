package com.codename1.app.backend.webservices;

import com.codename1.app.backend.data.RestaurantEntity;
import com.codename1.app.backend.data.RestaurantRepository;
import com.codename1.app.backend.service.dao.AppVersion;
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
@RequestMapping("/ver")
public class VersionService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody AppVersion getKey(@RequestParam(value="os", required=true) String os,
            @RequestParam(value="appId", required=true) String appId) {
        RestaurantEntity r = restaurantRepository.findOne(appId);
        switch(os) {
            case "ios":
                return new AppVersion(r.getCurrentVersionIOS(), r.getLastSupportedVersionIOS());
            case "and":
                return new AppVersion(r.getCurrentVersionAnd(), r.getLastSupportedVersionAnd());
            case "win":
                return new AppVersion(r.getCurrentVersionUWP(), r.getLastSupportedVersionUWP());
        } 
        
        return null;
    }
}