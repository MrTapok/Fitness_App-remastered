package system.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.entities.Pass;
import system.service.WorkoutTypeService;

import java.util.List;
@RestController
public class WorkoutTypeRestController {
    private WorkoutTypeService workoutTypeService;

    @Autowired
    public void setWorkoutTypeService(WorkoutTypeService workoutTypeService) {
        this.workoutTypeService = workoutTypeService;
    }

    @RequestMapping(value = "/createWorkoutType", method = RequestMethod.POST)
    public boolean fitnessRegistration(@RequestBody String name) {
        return workoutTypeService.createWorkoutType(name);
    }

    @RequestMapping(value = "/getAllWorkoutTypes", method = RequestMethod.GET)
    public List<Pass> getPasses(){
        return workoutTypeService.getAllWorkoutTypes();
    }
}
