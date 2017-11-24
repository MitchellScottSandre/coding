package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;

@Controller
public class HomeController {

    @RequestMapping(value = "/home")
    public ModelAndView home() throws IOException {


        Stock stock = YahooFinance.get("AAPL");
        System.out.println("TEST");
        System.out.println(stock.getQuote().getPrice());



        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("pageTitle", "Home Page");
        modelAndView.addObject("message", "Hello World");
        return modelAndView;
    }
}
