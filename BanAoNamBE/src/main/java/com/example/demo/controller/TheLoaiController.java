package com.example.demo.controller;


import com.example.demo.dto.theloai.TheLoaiRequest;
import com.example.demo.service.TheLoaiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

import java.util.List;

@RestController
@RequestMapping ( "/the-loai" )
@CrossOrigin ( {"*"} )
public class TheLoaiController {
    @Autowired
    private TheLoaiService service;

//    @GetMapping ()
//    public ResponseEntity<?> getData(){
//        return ResponseEntity.ok(service.getData());
//    }

    @GetMapping ( "/getAll" )
    public ResponseEntity<?> getAll ( @RequestParam ( defaultValue = "0", name = "page" ) Integer page ) {
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping ( "/get-cbb-the-loai" )
    public ResponseEntity<?> getCbb ( ) {
        return ResponseEntity.ok(service.getCbb());
    }

    @PostMapping ( "/add" )
    public ResponseEntity<?> add ( @RequestBody @Valid TheLoaiRequest request , BindingResult result ) {

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return ResponseEntity.ok(errors);
        } else {
            return ResponseEntity.ok(service.add(request));
        }
    }

    @PutMapping ( "/update/{id}" )
    public ResponseEntity<?> update ( @PathVariable ( "id" ) int id , @RequestBody TheLoaiRequest request ) {
        return ResponseEntity.ok(service.update(request , id));
    }

    @DeleteMapping ( "/delete/{id}" )
    public ResponseEntity<?> delete ( @PathVariable ( "id" ) Integer id ) {
        return ResponseEntity.ok(service.delete(id));
    }
}
