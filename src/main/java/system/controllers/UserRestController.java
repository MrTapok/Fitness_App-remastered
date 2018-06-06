package system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.entities.Pass;
import system.entities.Workout;
import system.entities.User;
import system.service.UserService;

import java.util.List;

@RestController
public class UserRestController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/submitRegistration", method = RequestMethod.POST)
    public boolean fitnessRegistration(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public void deleteUser(@RequestBody int id) {
        userService.deleteUser(id);
    }

    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public void addAdmin(@RequestBody User user){
        userService.addAdmin(user);
    }

    @RequestMapping(value = "/addManager", method = RequestMethod.POST)
    public void addManager(@RequestBody User user){
        userService.addManager(user);
    }

    @RequestMapping(value = "/addTrainer", method = RequestMethod.POST)
    public void addTrainer(@RequestBody User user){
        userService.addTrainer(user);
    }

    @RequestMapping(value = "/getUsername", method = RequestMethod.GET)
    public String getUsername(){
        return userService.getUserName();
    }

    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    public User getCurrentUser(){
        return userService.getCurrentUser();
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET) //Вообще все
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/getAllVisitors", method = RequestMethod.GET) //Все ROLE_USER
    public List<User> getVisitors(){
        return userService.getAllVisitors();
    }

    @RequestMapping(value = "/getAllTrainers", method = RequestMethod.GET)
    public List<User> getTrainers(){
        return userService.getAllTrainers();
    }

    @RequestMapping(value = "/getAllManagers", method = RequestMethod.GET)
    public List<User> getManagers(){
        return userService.getAllManagers();
    }

    @RequestMapping(value = "/getAllAdmins", method = RequestMethod.GET)
    public List<User> getAdmins(){
        return userService.getAllAdmins();
    }

    @RequestMapping(value = "/addWorkoutToUser", method = RequestMethod.POST)
    public void addWorkoutToUser(@RequestBody int id){
        userService.addWorkoutToUser(id);
    }

    @RequestMapping(value = "/addPassToUser", method = RequestMethod.POST)
    public void addPassToUser(@RequestBody int id){
        userService.addPassToUser(id);
    }

    @RequestMapping(value = "/getUserWorkout", method = RequestMethod.GET)
    public List<Workout> getUserWorkout(){
        return userService.getUserWorkout();
    }

    @RequestMapping(value = "/getUserPass", method = RequestMethod.GET)
    public List<Pass> getUserPass(){
        return userService.getUserPass();
    }

}