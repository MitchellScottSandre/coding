package com.controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

        String[] codes = {"TSLA", "F", "TM"};
        String baseUrl = "http://finance.yahoo.com/q?s=";
        String ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.33 (KHTML, like Gecko) Chrome/27.0.1438.7 Safari/537.33";

        for (String code : codes) {
            String url = baseUrl + code;
            Document doc  = Jsoup.connect(url).userAgent(ua).timeout(10*1000).get();
            String price = doc.select(".time_rtq_ticker").first().text();
            String name = doc.select(".title h2").first().text();

            System.out.println(String.format("%s [%s] is trading at %s", name, code, price));
        }


        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("pageTitle", "Home Page");
        modelAndView.addObject("message", "Hello World");
        return modelAndView;
    }
}
