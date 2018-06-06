package system.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.entities.Workout;
import system.service.WorkoutService;

import java.util.List;
@RestController
public class WorkoutRestService {
    private WorkoutService workoutService;

    @Autowired
    public void setWorkoutService(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @RequestMapping(value = "/createWorkout", method = RequestMethod.POST)
    public boolean createWorkout(@RequestBody String name, int price, String description, int remain, int type_id, int trainer_id) {
        return workoutService.createWorkout(name, price, description, remain, type_id, trainer_id);
    }

    @RequestMapping(value = "/getAllWorkouts", method = RequestMethod.GET)
    public List<Workout> getWorkouts(){
        return workoutService.getAllWorkouts();
    }
}
