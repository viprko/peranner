package pet.peranner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {

    @GetMapping
    public String helloPage() {
        return "Hello, this is the main page of future best personal planner - \"Peranner\"";
    }
}
