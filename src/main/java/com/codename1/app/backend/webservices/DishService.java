package com.codename1.app.backend.webservices;

import com.codename1.app.backend.data.CategoryEntity;
import com.codename1.app.backend.data.DishEntity;
import com.codename1.app.backend.data.DishRepository;
import com.codename1.app.backend.data.RestaurantEntity;
import com.codename1.app.backend.data.RestaurantRepository;
import com.codename1.app.backend.service.dao.AppVersion;
import com.codename1.app.backend.service.dao.Dish;
import com.codename1.app.backend.service.dao.DishContainer;
import java.util.Iterator;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dish")
public class DishService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody DishContainer fetchDishes(@RequestParam(value="stamp", required=true) long stamp,
            @RequestParam(value="appId", required=true) String appId) {
        RestaurantEntity r = restaurantRepository.findOne(appId);
        if(r.getDishListUpdateTimestamp() != stamp) {
            Set<DishEntity> dd = r.getDishes();
            Dish[] a = new Dish[dd.size()];
            int pos = 0;
            for(DishEntity e : dd) {
                a[pos] = e.toDish();
                pos++;
            }
            Set<CategoryEntity> cats = r.getCategories();
            Iterator<CategoryEntity> catIterator = cats.iterator();
            String[] categories = new String[cats.size()];
            for(int iter = 0 ; iter < categories.length ; iter++) {
                categories[iter] = catIterator.next().getName();
            }
            return new DishContainer("" + r.getDishListUpdateTimestamp(), a, categories);
        }
        return new DishContainer(null, new Dish[0], new String[0]);
    }
}