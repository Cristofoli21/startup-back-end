package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;

@CrossOrigin(origins = "ec2-18-230-154-138.sa-east-1.compute.amazonaws.com:9090")
@RestController
@RequestMapping("/api")

public class UsersController {
    @Autowired
    UsersRepository UsersRepository;

    @GetMapping("/")
    public ResponseEntity<String> getHello(){
        System.out.println("Hello World tutorials");
        
        
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }
 

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false)String name){
        try{
            List<User> users = new ArrayList<Users>();

            System.out.println(name);
            
            if(name == null)
            UsersTutorial.findAll().forEach(users::add);
            else
                UsersTutorial.findByNameContaining(name).forEach(users::add);
            
            if(users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUsersById(@PathVariable("id") long id){
        Optional<Users> usersData = TutorialRepository.findById(id);

        if(usersData.isPresent()){
            return new ResponseEntity<>(usersData.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Users> createUsers(@RequestBody Users users){
        try{
            Users _users = UsersRepository
                    .save(new Users(users.getName(), tutorial.getAge(), false));
            return new ResponseEntity<>(_users,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUsers(@PathVariable("id") long id, @RequestBody Users users){
        Optional<Users> usersData = UsersRepository.findById(id);

        if (usersData.isPresent()){
            Users _users = usersData.get();
            _users.setTitle(users.getTitle());
            _users.setDescription(users.getDescription());
            _users.setPublished(users.isPublished());
            return new ResponseEntity<>(UsersRepository.save(_users),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUsers(@PathVariable("id") long id){
        try {
            UsersRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@GetMapping("/users/verified")
	public ResponseEntity<List<Users>> findByVerified() {
		try {
			List<Users> users = UsersRepository.findByVerified(true);

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}