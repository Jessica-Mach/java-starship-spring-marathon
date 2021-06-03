package com.launchacademy.javastarships.controllers;

import com.launchacademy.javastarships.models.StarShip;
import com.launchacademy.javastarships.services.StarShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/starships")
public class StarShipsController {

  private StarShipService starShipService;

  @Autowired
  public StarShipsController(StarShipService starShipService) {
    this.starShipService = starShipService;
  }

  @GetMapping
  public String getList(Model model) {
    model.addAttribute("starShips", starShipService.findAll());
    return "starships/index";
  }

  @GetMapping("/{index}")
  public String getShow(@PathVariable Integer index, Model model) {
    try{
      model.addAttribute("starShip", starShipService.get(index));
    } catch (IndexOutOfBoundsException exception) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND
      );
    }
    return "starships/show";
  }

  @GetMapping("/new")
  public String getNew(@ModelAttribute StarShip starShip) {
    return "starships/new";
  }

  @PostMapping
  public String createNew(@ModelAttribute StarShip starShip) {
    starShip.setId(starShipService.findAll().size() + 1);
    starShipService.addToList(starShip);
    return "redirect:/starships/" + starShip.getId();
  }
}
