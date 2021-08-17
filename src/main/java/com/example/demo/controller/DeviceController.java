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
                    .save(new Devices(devices.getName(), devices.getPrice())));
            return new ResponseEntity<>(_devices,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/devices/{id}")
    public ResponseEntity<Devices> updateDevices(@PathVariable("id") long id, @RequestBody Devices devices){
        Optional<Devices> devicesData = DevicesRepository.findById(id);

        if (devicesData.isPresent()){
            Devices _devices = devicesData.get();
            _devices.setName(users.getName());
            _devices.setPrice(users.getPrice());;
            return new ResponseEntity<>(DeviceRepository.save(_devices),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/devices/{id}")
    public ResponseEntity<HttpStatus> deleteDevices(@PathVariable("id") long id){
        try {
            DeviceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}