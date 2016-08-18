package com.nrhumla.controller;

import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nrhumla.mock.MockFruitService;
import com.nrhumla.service.FruitService;

@RunWith(SpringRunner.class)
@WebMvcTest(FruitController.class)
public class FruitControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FruitService fruitService;

    @Before
    public void setUp()
    {
        MockFruitService.stub(fruitService)
                .searchWithParam()
                .searchWithoutParam();
    }

    @Test
    public void shouldGetFruitList() throws Exception
    {
        // when and then
        mockMvc.perform(get("/fruits"))
                .andExpect(status().isOk())
                .andExpect(view().name("fruits"))
                .andExpect(model().attribute("fruitBowl", contains("banana", "orange")));
    }

    @Test
    public void shouldFilterFruitsByName() throws Exception
    {
        // when and then
        mockMvc.perform(get("/fruits")
                .param("search", "ban"))
                .andExpect(status().isOk())
                .andExpect(view().name("fruits"))
                .andExpect(model().attribute("fruitBowl", contains("banana")));
    }
}