package com.codename1.app.backend.webservices;

import com.codename1.app.backend.data.CategoryEntity;
import com.codename1.app.backend.data.CategoryRepository;
import com.codename1.app.backend.data.DishEntity;
import com.codename1.app.backend.data.DishRepository;
import com.codename1.app.backend.data.RestaurantEntity;
import com.codename1.app.backend.data.RestaurantRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/initDb-UYGhjjfhs658537543hjag")
public class InitDbService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private DishRepository dishRepository;
        
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody String initDb() {
        Set<CategoryEntity> entries = new HashSet<>();
        entries.add(new CategoryEntity("Entr\u00e9e"));
        entries.add(new CategoryEntity("Mains"));
        entries.add(new CategoryEntity("Deserts"));
        entries.add(new CategoryEntity("Wines"));
        entries.add(new CategoryEntity("Beverages"));
        categoryRepository.save(entries);
        
        RestaurantEntity rest = new RestaurantEntity();
        rest.setName("Test Restaurant");
        rest.setMerchantId("6hc6rd4s5g4qpbr7");
        rest.setPublicKey("2jkpz7z2tm542mrm");
        rest.setPrivateKey("e687d6bf23d376c7d1599ca03cb99112");
        rest.setCategories(entries);
        
        List<CategoryEntity> l = new ArrayList<>();
        for(CategoryEntity e : entries) {
            l.add(e);
        }
        
        Set<DishEntity> dishes = new HashSet<>();
        for(int iter = 0 ; iter < l.size() ; iter++) {
            DishEntity di = new DishEntity();
            di.setCategory(l.get(0));
            di.setDescription("My delicious dish " + iter);
            di.setName("Dish Name " + iter);
            di.setPrice(iter + 2);
            dishRepository.save(di);
            dishes.add(di);
        }
        
        rest.setDishes(dishes);

        restaurantRepository.save(rest);
        
        return "Success!";
    }
}