package com.example.demo.controller;


import com.example.demo.dto.khuyenmai.KhuyenMaiRequest;
import com.example.demo.dto.khuyenmai.SearchKhuyenMai;
import com.example.demo.service.KhuyenMaiService;
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
@RequestMapping ("/khuyen-mai")
@CrossOrigin ({"*"})
public class KhuyenMaiController {
    @Autowired
    private KhuyenMaiService service;

    @GetMapping ("/getAll")
    public ResponseEntity<?> getAll( @RequestParam (defaultValue = "0", name = "page") Integer page ){
        return ResponseEntity.ok(service.getAll(page));
    }

    @PutMapping ("/update-tt-km")
    public ResponseEntity<?> updateTTKM(){
        return ResponseEntity.ok(service.updateTrangThai());
    }

    @PostMapping ("/add")
    public ResponseEntity<?> add( @RequestBody @Valid KhuyenMaiRequest request, BindingResult result ){

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return ResponseEntity.ok(errors);
        } else {
            return ResponseEntity.ok(service.add(request));
        }
    }

    @PutMapping ( "/update/{id}" )
    public ResponseEntity<?> update ( @PathVariable ("id") int id, @RequestBody KhuyenMaiRequest request ) {
        return ResponseEntity.ok(service.update(request, id));
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> delete( @PathVariable("id") Integer id ){
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping ("/tim-kiem")
    public ResponseEntity timKiem(final SearchKhuyenMai searchKhuyenMai){
        return ResponseEntity.ok(service.timKiem(searchKhuyenMai));
    }
}
