package pl.hgawrys.nasaapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.hgawrys.nasaapi.models.services.NasaService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class MainController {
    private NasaService nasaService = NasaService.getInstance();

    Date today = Calendar.getInstance().getTime();
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);

    public void run(){
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("photoData", nasaService.getPictureOfTheDay());
        model.addAttribute("dataNasa", nasaService.getAsteroids(formatter.format(today)));
        model.addAttribute("day", today);
        return "index";
    }

    @PostMapping("/")
    public String indexpost(@RequestParam("dataInput") String dataInput,
            Model model) {
        model.addAttribute("photoData", nasaService.getPictureOfTheDay());
        model.addAttribute("dataNasa", nasaService.getAsteroids(dataInput));
        model.addAttribute("day", dataInput);
        System.out.println(nasaService.getAsteroids(dataInput).toString());
        return "index";

    }
}
