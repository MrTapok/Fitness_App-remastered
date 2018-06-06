package system.service;

import org.springframework.security.access.annotation.Secured;
import system.entities.User;
import system.entities.WorkoutType;
import system.entities.Pass;
import system.repository.PassRepository;
import system.repository.WorkoutTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;

@Service
public class PassService {

    @Autowired
    private WorkoutTypeRepository workoutTypeRepository;

    @Autowired
    private PassRepository passRepository;

    @Secured("ROLE_MANAGER")
    public boolean createPass(String name, int price, String description, int remain, int type_id) {
        WorkoutType type = workoutTypeRepository.findOne(type_id);
        if (type!=null){
            Pass newPass = new Pass();
            newPass.setName(name);
            newPass.setPrice(price);
            newPass.setDescription(description);
            newPass.setRemain(remain);
            newPass.setType(type);
            passRepository.save(newPass);
            return true;
        }
        return false;
    }

    public List<Pass> getAllPasses(){  //check
        return passRepository.findAll();
    }

    public int getPassId(Pass pass){
        return pass.getId();
    }

}
