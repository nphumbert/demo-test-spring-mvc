package com.nrhumla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nrhumla.service.FruitService;

@Controller
public class FruitController
{
    @Autowired
    private FruitService fruitService;

    @RequestMapping(value = "/fruits", method = RequestMethod.GET)
    public String getFruits(@RequestParam(value = "search", required = false) String search, final Model model)
    {
        model.addAttribute("fruitBowl", fruitService.search(search));
        return "fruits";
    }
}
