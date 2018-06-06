package system.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import system.entities.WorkoutType;
import system.repository.WorkoutTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class WorkoutTypeService {

    @Autowired
    WorkoutTypeRepository workoutTypeRepository;

    @Secured("ROLE_MANAGER")
    public boolean createWorkoutType(String name) {
        WorkoutType newWorkoutType = new WorkoutType();
        newWorkoutType.setName(name);
        workoutTypeRepository.save(newWorkoutType);
        return true;
    }

    public List getAllWorkoutTypes(){  //check
        return workoutTypeRepository.findAll();
    }

    public int getWorkoutTypeId(WorkoutType workoutType){
        return workoutType.getId();
    }
}
