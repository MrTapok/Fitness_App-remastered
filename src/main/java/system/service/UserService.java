package system.service;

import system.entities.User;
import system.entities.Pass;
import system.entities.Workout;
import system.repository.PassRepository;
import system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import system.repository.WorkoutRepository;

import java.security.MessageDigest;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private PassRepository passRepository;

    public boolean createUser(User user) {
        User user1 = userRepository.findByName(user.getName());
        if ((user1==null) && (!user.getPassword().equals("")) && (!user.getName().equals("")))
        {
            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setPassword(UserService.toMD5(user.getPassword()));
            newUser.setRole("ROLE_USER");
            userRepository.save(newUser);
            return true;
        }
        return false;
    }

    public String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public User getCurrentUser() {
        User user = userRepository.findByName(getUserName());
        return user;
    }

    static public String toMD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    @Secured("ROLE_USER")  //check
    public boolean addWorkoutToUser(int id) {
        User user = getCurrentUser();
        Workout work = workoutRepository.findOne(id);
        List<Workout> workouts = user.getWorkouts();
        if((work!=null) && (work.getRemain()>0)) {
            workouts.add(work);
            work.setRemain(work.getRemain() - 1);
            user.setWorkouts(workouts);
            userRepository.save(user);
            workoutRepository.save(work);
            return true;
        }
        return false;
    }

    @Secured("ROLE_USER")  //check
    public boolean addPassToUser(int id) {
        User user = getCurrentUser();
        Pass pass = passRepository.findOne(id);
        List<Pass> passes = user.getPasses();
        if((pass!=null) && (pass.getRemain()>0)) {
            passes.add(pass);
            pass.setRemain(pass.getRemain() - 1);
            user.setPasses(passes);
            userRepository.save(user);
            passRepository.save(pass);
            return true;
        }
        return false;
    }

    public List<Workout> getUserWorkout() {  //check
        User user = getCurrentUser();
        List<Workout> workouts = user.getWorkouts();
        return workouts;
    }

    public List<Pass> getUserPass() {  //check
        User user = getCurrentUser();
        List<Pass> passes = user.getPasses();
        return passes;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllVisitors() {
        return userRepository.findByRole("ROLE_USER");
    }

    public List<User> getAllAdmins() {
        return userRepository.findByRole("ROLE_ADMIN");
    }

    public List<User> getAllManagers() {
        return userRepository.findByRole("ROLE_TRAINER");
    }

    public List<User> getAllTrainers() {
        return userRepository.findByRole("ROLE_MANAGER");
    }

    public void addAdmin(User user)  //check
    {
        if (user.getName()!=null && user.getPassword()!=null){
            user.setRole("ROLE_ADMIN");
            userRepository.save(user);
        }
    }

    public void addManager(User user)  //check
    {
        if (user.getName()!=null && user.getPassword()!=null){
            user.setRole("ROLE_MANAGER");
            userRepository.save(user);
        }
    }

    public void addTrainer(User user)  //check
    {
        if (user.getName()!=null && user.getPassword()!=null){
            user.setRole("ROLE_MANAGER");
            userRepository.save(user);
        }
    }

    public void deleteUser(int id)
    {
        User user = userRepository.findOne(id);
        if(user!=null){
            userRepository.delete(user);
        }
    }
}
