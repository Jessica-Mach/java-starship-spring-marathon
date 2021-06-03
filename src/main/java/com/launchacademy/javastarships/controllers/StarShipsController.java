package com.launchacademy.javastarships.controllers;

import com.launchacademy.javastarships.services.StarShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/starships")
public class StarShipsController {

  private StarShipService starShipService;

  @Autowired
  public StarShipsController(StarShipService starShipService) {
    this.starShipService = starShipService;
  }

  @GetMapping
  public String getIndex(Model model) {
    model.addAttribute("starShips", starShipService.findAll());
    return "starships/index";
  }

}
