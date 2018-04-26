package pl.hgawrys.nasaapi.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.hgawrys.nasaapi.models.NasaService;

public class MainController {
    private NasaService nasaService = NasaService.getInstance();

    public void run(){
        System.out.println(nasaService.getAsteroids("2018-04-20", "2018-04-21"));
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("dataNasa", nasaService.getAsteroids("2018-04-20", "2018-04-21"));
        return "index";
    }
}
