package com.tuanzeebee.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    //new a controller method to show initial HTML form
    @GetMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }
    // need a controller method to process the HTML form
    @GetMapping("/processFrom")
    public String processForm(){
        return "helloworld";
    }

    //need a controller method to read form data and
    //add data to model
    @GetMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model){

        // read to the request parameter from the HTML form

        String theName = request.getParameter("studentName");

        //convert the data to all caps
        theName = theName.toUpperCase();
        //create the message
        String result = "Yo! "+ theName;

        //add message to the model
        model.addAttribute("message",result);

        return "helloworld";
    }
    @PostMapping("/processFormVersionThree")
    public String processFormVersionThree(
            @RequestParam("studentName") String theName, Model model){

        //convert the data to all caps
        theName = theName.toUpperCase();
        //create the message
        String result = "Hey My Friend! "+ theName;

        //add message to the model
        model.addAttribute("message",result);

        return "helloworld";
    }
}
