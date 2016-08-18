package com.nrhumla.mock;

import static java.util.Arrays.asList;

import org.mockito.Mockito;

import com.nrhumla.service.FruitService;

public class MockFruitService
{
    private FruitService fruitService;

    private MockFruitService(FruitService service)
    {
        this.fruitService = service;
    }

    public static MockFruitService stub(FruitService service)
    {
        return new MockFruitService(service);
    }

    public MockFruitService searchWithoutParam()
    {
        Mockito.when(fruitService.search(null)).thenReturn(asList("banana", "orange"));
        return this;
    }

    public MockFruitService searchWithParam()
    {
        Mockito.when(fruitService.search("ban")).thenReturn(asList("banana"));
        return this;
    }

}
