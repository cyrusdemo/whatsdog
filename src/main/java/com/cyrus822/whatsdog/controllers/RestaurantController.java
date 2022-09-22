package com.cyrus822.whatsdog.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.cyrus822.whatsdog.models.Restaurants;
import com.cyrus822.whatsdog.repos.RestaurantRepo;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantRepo repo;

    @GetMapping("/test")
    @Transactional
    public String test(ModelMap m, @RequestParam(name = "name") String name, @RequestParam(name = "addr") String addr){
        List<Restaurants> all = repo.getRestaurantsJPA(name, addr);
        m.addAttribute("allRest", all);
        return "list";
    }

    @GetMapping({
        "",
        "/",
        "/index",
        "/draw"
    })
    public String draw(ModelMap m){
        List<Restaurants> allRestaurants = repo.findAll();
        if(allRestaurants.isEmpty()){
            return "redirect:/create";
        }
        Random rand = new Random(System.currentTimeMillis());
        int idx = rand.nextInt(0, allRestaurants.size());
        Restaurants rObj = allRestaurants.get(idx);
        m.addAttribute("drawObject", rObj);
        return "index";
    }

    //Create
    @GetMapping("/create")
    public String create(ModelMap m){
        m.addAttribute("rForm", new Restaurants());
        return "create";
    }   

    @PostMapping("/create")
    public String create(ModelMap m, @Valid @ModelAttribute("rForm") Restaurants rForm, BindingResult results){
        if(results.hasErrors()){
            m.addAttribute("rForm", rForm);
            return "create";
        }

        repo.save(rForm);

        return "redirect:/list";
    }       

    //Retrieve
    @GetMapping("/list")
    public String list(ModelMap m){
        List<Restaurants> allRestaurants = repo.findAll();
        m.addAttribute("allRest", allRestaurants);
        return "list";
    }        

    @GetMapping("/detail/{rId}")
    public String detail(ModelMap m, @PathVariable("rId") Integer rId){
        Optional<Restaurants> rObj = repo.findById(rId);
        if(!rObj.isPresent()){
            return "redirect:/list";
        }

        m.addAttribute("drawObject", rObj.get());
        return "detail";
    }      

    //Update
    @GetMapping("/update/{rId}")
    public String update(ModelMap m, @PathVariable("rId") Integer rId){
        Optional<Restaurants> rObj = repo.findById(rId);
        if(!rObj.isPresent()){
            return "redirect:/list";
        }

        m.addAttribute("rForm", rObj.get());
        return "update";
    }   

    @PostMapping("/update/{rId}")
    public String update(ModelMap m, @PathVariable("rId") Integer rId, @Valid @ModelAttribute("rForm") Restaurants rForm, BindingResult results){
        if(results.hasErrors()){
            m.addAttribute("rForm", rForm);
            return "update";
        }   

        Optional<Restaurants> rObj = repo.findById(rId);
        if(!rObj.isPresent()){
            return "redirect:/list";
        }
        
        Restaurants updObj = rObj.get();
        updObj.setRAddr(rForm.getRAddr());
        updObj.setRName(rForm.getRName());
        updObj.setRPhone(rForm.getRPhone());
        updObj.setRWeb(rForm.getRWeb());

        repo.save(updObj);

        return "redirect:/list";
    }         

    //Delete
    @GetMapping("/delete/{rId}")
    public String delete(ModelMap m, @PathVariable("rId") Integer rId){
        Optional<Restaurants> rObj = repo.findById(rId);
        if(!rObj.isPresent()){
            return "redirect:/list";
        }

        repo.deleteById(rId);
        return "redirect:/list";
    }   
}
