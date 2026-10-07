package com.workintech.s17d2.rest;

import com.workintech.s17d2.dto.DeveloperResponse;
import jakarta.annotation.PostConstruct;
import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.DeveloperFactory;
import com.workintech.s17d2.model.SeniorDeveloper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.workintech.s17d2.tax.Taxable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    public Map<Integer, Developer> developers ;
    private Taxable taxable;

    @PostConstruct
    public void init(){
        developers = new HashMap<>();
        developers.put(1,new SeniorDeveloper(1,"Mustafa",10000d));

    }
    @Autowired
    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeveloperResponse addDeveloper(@RequestBody Developer developer){
        Developer createdDeveloper = DeveloperFactory.createDeveloper(developer,taxable);
        if(Objects.nonNull(createdDeveloper)){
            developers.put(createdDeveloper.getId(),createdDeveloper);
        }
        return  new DeveloperResponse(HttpStatus.CREATED.value(),"Developer created",createdDeveloper);

    }
    @GetMapping
    public List<Developer> getAllDevelopers(){
        return  developers.values().stream().toList();
    }
    @GetMapping("/{id}")
    public  DeveloperResponse getDeveloperById(@PathVariable int id){
       Developer foundDeveloper = this.developers.get(id);
       if(foundDeveloper==null){
           return new DeveloperResponse(HttpStatus.NOT_FOUND.value(),"Developer not found",null);
       }
       return new DeveloperResponse(HttpStatus.OK.value(),"Developer found",foundDeveloper);
    }
    @PutMapping("/{id}")
    public DeveloperResponse updateDeveloper(@PathVariable("id") int id, @RequestBody Developer developer){
        developer.setId(id);
        Developer newDeveloper = DeveloperFactory.createDeveloper(developer,taxable);
        this.developers.put(id,newDeveloper);
        return new DeveloperResponse(HttpStatus.OK.value(),"Developer updated",newDeveloper);
    }

    @DeleteMapping("/{id}")
    public DeveloperResponse deleteDeveloper(@PathVariable("id") int id){
        Developer foundDeveloper = this.developers.get(id);
        if(foundDeveloper!=null){
            this.developers.remove(id);
        }
        return new DeveloperResponse(HttpStatus.OK.value(),"Developer deleted",foundDeveloper);
    }
}
