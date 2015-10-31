package com.nrhumla;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Controller
public class ApplicationController {

    @RequestMapping(value = "/fruits", method = RequestMethod.GET)
    public String getFruits(@RequestParam(value = "search", required = false) String search, final Model model) {
        model.addAttribute("fruitBowl", search(search));
        return "fruits";
    }

    private List<String> search(String search) {
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
