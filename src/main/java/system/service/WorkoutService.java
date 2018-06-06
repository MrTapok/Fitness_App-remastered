package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.access.annotation.Secured;
import system.entities.User;
import system.entities.Workout;
import system.entities.WorkoutType;
import system.repository.WorkoutRepository;
import system.repository.WorkoutTypeRepository;
import system.repository.UserRepository;

import java.util.List;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutTypeRepository workoutTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Secured("ROLE_MANAGER")
    public boolean createWorkout(String name, int price, String description, int remain, int type_id, int trainer_id) {
        WorkoutType type = workoutTypeRepository.findOne(type_id);
        User trainer = userRepository.findOne(trainer_id);

        if ((type!=null) && (trainer!=null) && (trainer.getRole().equals("ROLE_TRAINER")))
        {
            Workout newWorkout = new Workout();
            newWorkout.setName(name);
            newWorkout.setPrice(price);
            newWorkout.setDescription(description);
            newWorkout.setRemain(remain);
            newWorkout.setType(type);
            newWorkout.setTrainer(trainer);
            workoutRepository.save(newWorkout);
        }
        return false;
    }

    public List<Workout> getAllWorkouts(){  //check
        return workoutRepository.findAll();
    }

    public int getWorkoutId(Workout workout){
        return workout.getId();
    }
}
