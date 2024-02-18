package com.example.demo.controller;

import com.example.demo.dto.diachi.DiaChiRequest;
import com.example.demo.service.impl.DiaChiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dia-chi")
@CrossOrigin({"*"})
public class DiaChiController {

    @Autowired
    private DiaChiServiceImpl service;

    @GetMapping("/load-dia-chi")
    public ResponseEntity<?> getListDC() {
        return ResponseEntity.ok(service.getListDC());
    }

    @PostMapping("/save-dia-chi")
    public ResponseEntity<?> save(@RequestBody DiaChiRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @PutMapping("/update-dia-chi")
    public ResponseEntity<?> update(@RequestBody DiaChiRequest request) {
        return ResponseEntity.ok(service.update(request));
    }

}
