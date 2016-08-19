package br.com.wt.poclaboratorio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{

   @RequestMapping("/")
   public String index()
   {
      return "home/index";
   }
}
