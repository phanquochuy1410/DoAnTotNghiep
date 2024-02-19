package com.example.demo.controller;

import com.example.demo.repositories.TheLoaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trang-chu")
@CrossOrigin({"*"})
public class TrangChuController {
    @Autowired
    private TheLoaiRepository theLoaiRepository;

    @GetMapping("/the-loai")
    public ResponseEntity hienThiTheLoai(){
        return ResponseEntity.ok(theLoaiRepository.findAll());
    }

}
