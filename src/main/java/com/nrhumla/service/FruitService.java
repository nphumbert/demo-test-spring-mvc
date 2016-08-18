package com.nrhumla.service;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class FruitService
{
    public List<String> search(String search)
    {
        if (StringUtils.isEmpty(search)) {
            return fruitBowl();
        }

        return fruitBowl().stream()
                .filter(fruit -> fruit.startsWith(search))
                .collect(toList());
    }

    private List<String> fruitBowl()
    {
        return asList("banana", "orange");
    }
}
