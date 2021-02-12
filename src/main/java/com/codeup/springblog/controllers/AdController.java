package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdController {

    //AdRespository = adDao
    private final AdRepository adsDao;

    public AdController(AdRepository adsDao) {
        this.adsDao = adsDao;
    }

    @GetMapping("/ads/jpa")
    @ResponseBody
    public List<Ad> jpaIndex(){
        return adsDao.findAll();
    }

    @GetMapping("/ads/jpa/{id}")
    @ResponseBody  //@ResponseBody requires a String representation
    public String viewJpaAd(@PathVariable long id){
        return adsDao.getOne(id).toString();
    }

    @GetMapping("/ads/{id}")
    public String viewAd(@PathVariable long id, Model model){
        Ad myAd = adsDao.getOne(id);  //parameter for getOne MUST be the id
        model.addAttribute("ad", myAd);

        return "ads/show";  //haven't made this! just an example
    }

    @GetMapping("/ads/jpa/create")
    public void createAd(){
        Ad ad = new Ad();
        ad.setTitle("Whatevs.");
        ad.setDescription("Whateeeevvvvss.");

        adsDao.save(ad);
    }

    @GetMapping("/ads/jpa/delete") //don't run these yet, just an example VVVVVV
    public void deleteAd(){
        adsDao.deleteById(7L);
    }

    @GetMapping("/ads/search")
    @ResponseBody
    public Ad returnAdByTitle(){
        return adsDao.findByTitle("Whatevs.");
    }

    @GetMapping("/ads/order")
    @ResponseBody
    public List<Ad> returnAdsByTitle(){
        return adsDao.findByOrderByTitle(); //returns all ads sorted alphabetically by title
    }

}
