package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.ThreadLocalRandom;

@Controller
public class DiceController {
    @GetMapping("/roll-dice/{guess}")
    public String rollDice(@PathVariable int guess, Model model){

        String winOrLoseMessage;

        int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);

        if(guess == randomNum){
            winOrLoseMessage = "Hooray! You guessed correctly!";
        }else{
            winOrLoseMessage = "Boo, that's wrong. Try again.";
        }

        model.addAttribute("guess", guess);
        model.addAttribute("message", winOrLoseMessage);
        model.addAttribute("randomNumber", randomNum);

        return "roll-results";

    }


    @GetMapping("/roll-dice")
    public String showRollDicePage(){
        return "roll-dice";
    }

}
