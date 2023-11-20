package UdeA.zerohunger.controller;

import UdeA.zerohunger.model.Home;
import UdeA.zerohunger.service.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/homes/")
@CrossOrigin(origins = "*")
public class HomeController {

    private final HomeService homeService;
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping
    public List<Home> listHomes() {
        logger.info("Listing homes");
        return homeService.listAllHomes();
    }

    @PostMapping
    public ResponseEntity<Home> addHome(@RequestBody Home newHome) {
        logger.info("Adding home");
        Home home = homeService.addHome(newHome);
        return ResponseEntity.status(HttpStatus.OK).body(home);
    }
}

