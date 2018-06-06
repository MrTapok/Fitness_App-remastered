package system.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.entities.Pass;
import system.service.PassService;

import java.util.List;

@RestController
public class PassRestController {
    private PassService passService;

    @Autowired
    public void setPassService(PassService passService) {
        this.passService = passService;
    }

    @RequestMapping(value = "/createPass", method = RequestMethod.POST)
    public boolean createPass(@RequestBody String name, int price, String description, int remain, int type_id) {
        return passService.createPass(name, price, description, remain, type_id);
    }

    @RequestMapping(value = "/getAllPasses", method = RequestMethod.GET)
    public List<Pass> getPasses(){
        return passService.getAllPasses();
    }



}
