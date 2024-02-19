package com.example.demo.controller;

import com.example.demo.dto.HoaDonChiTiet.HoaDonChiTietRequest;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.service.ChiTietSanPhamService;
import com.example.demo.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hoa-don-chi-tiet")
@CrossOrigin({"*"})
public class HoaDonChiTietController {
    @Autowired
    private HoaDonChiTietService service;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamSer;

    @PutMapping("/update")
    public ResponseEntity<?> updateHDCT(@RequestBody List<HoaDonChiTietRequest> requests, @RequestParam("sl") List<Integer> soLuongs) {
        return ResponseEntity.ok(service.update(requests, soLuongs));
    }

    @PutMapping("/update-tai-quay")
    public ResponseEntity<?> updateTaiQuay(@RequestBody HoaDonChiTietRequest requests, @RequestParam("sl") Integer soLuongs) {
        return ResponseEntity.ok(service.updateTaiQuay(requests, soLuongs));
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody HoaDonChiTietRequest request, @RequestParam Integer idHoaDon) {
        return ResponseEntity.ok(service.addToCart(request, idHoaDon));
    }

    @DeleteMapping("/delete/sp")
    public ResponseEntity<?> deleteSP(@RequestParam("id") Integer id){
        return ResponseEntity.ok(service.deleteSP(id));
    }

    @GetMapping("/detail")
    public ResponseEntity<?> detailHDCT(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(service.findAllByIdHoaDon(id));
    }

    @GetMapping("/details")
    public ResponseEntity<?> detailHDCTS(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(service.findAllByIdHoaDonS(id));
    }
}
