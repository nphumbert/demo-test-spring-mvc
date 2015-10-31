package com.nrhumla;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Controller
public class ApplicationController {

    @RequestMapping(value = "/fruits", method = RequestMethod.GET)
    public ModelAndView getFruits(@RequestParam(value = "search", required = false) String search) {
        ModelAndView modelAndView = new ModelAndView("fruits");
        modelAndView.addObject("fruitBowl", search(search));
        return modelAndView;
    }

    private List<String> search(@RequestParam(value = "search", required = false) String search) {
        if (StringUtils.isEmpty(search)) {
            return fruitBowl();
        }

        return fruitBowl().stream()
                .filter(fruit -> fruit.startsWith(search))
                .collect(toList());
    }

    private List<String> fruitBowl() {
        return asList("banana", "orange");
    }
}
