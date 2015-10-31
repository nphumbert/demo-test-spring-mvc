package com.nrhumla;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoTestSpringMvcApplication.class)
@WebAppConfiguration
public class ApplicationControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        ApplicationController controller = new ApplicationController();
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver())
                .build();
    }

    @Test
    public void should_get_fruit_list() throws Exception {
        // when and then
        mockMvc.perform(get("/fruits"))
                .andExpect(status().isOk())
                .andExpect(view().name("fruits"))
                .andExpect(model().attribute("fruitBowl", contains("banana", "orange")));
    }

    @Test
    public void should_filter_fruits_by_name() throws Exception {
        // when and then
        mockMvc.perform(get("/fruits")
                .param("search", "ban"))
                .andExpect(status().isOk())
                .andExpect(view().name("fruits"))
                .andExpect(model().attribute("fruitBowl", contains("banana")));
    }

    private InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("WEB-INF/templates/");
        viewResolver.setSuffix("html");
        return viewResolver;
    }
}