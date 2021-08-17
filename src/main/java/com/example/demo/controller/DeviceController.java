package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Devices;
import com.example.demo.repository.DeviceRepository;

@RestController
@RequestMapping("/api")

public class DeviceController {
    @Autowired
    DeviceRepository DeviceRepository;
 

    @GetMapping("/devices")
    public ResponseEntity<List<Devices>> getAllDevices(@RequestParam(required = false)String name){
        try{
            List<Devices> devices = new ArrayList<Devices>();

            System.out.println(name);
            
            if(name == null)
            DevicesRepository.findAll().forEach(Devices::add);
            else
            	DevicesRepository.findByName(name).forEach(Devices::add);
            
            if(Devices.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(devices, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/devices/{id}")
    public ResponseEntity<Devices> getDevicesById(@PathVariable("id") long id){
        Optional<Devices> devicesData = DeviceRepository.findById(id);

        if(devicesData.isPresent()){
            return new ResponseEntity<>(devicesData.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/devices")
    public ResponseEntity<Devices> createDevice(@RequestBody Devices devices){
        try{
            Devices _devices = DeviceRepository
                    .save(new Users(users.getName(), users.getAge(), false));
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
            _users.setName(users.getName());
            _users.setAge(users.getAge());
            _users.setVerified(users.isVerified());
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

    @GetMapping("/users/verified2")
	public ResponseEntity<List<Users>> findByVerified2() {
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