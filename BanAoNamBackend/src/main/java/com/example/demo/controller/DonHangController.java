package com.example.demo.controller;

import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.HoaDonService;
import com.example.demo.service.impl.KhachHangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/don-hang")
@CrossOrigin({"*"})
public class DonHangController {

    @Autowired
    private HoaDonService service;

    @Autowired
    private HoaDonChiTietService hdctService;

    @Autowired
    KhachHangServiceImpl khachHangService;

    @GetMapping("/get-all-hd")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.findAllHDByEmail(khachHangService.emailKhachHang));
    }

    @GetMapping("/get-hdct")
    public ResponseEntity<?> getHDCT(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(hdctService.findSPByIdHD(id));
    }

    @GetMapping("/cho-thanh-toan")
    public ResponseEntity<?> getChoThanhToan(){
        Integer trangThai = 0;
        return ResponseEntity.ok(service.findAllHDByEmailAndTrangThai(khachHangService.emailKhachHang, trangThai));
    }
    //
    @GetMapping("/van-chuyen")
    public ResponseEntity<?> getVanChuyen(){
        Integer trangThai = 2;
        return ResponseEntity.ok(service.findAllHDByEmailAndTrangThai(khachHangService.emailKhachHang, trangThai));
    }
    //
    @GetMapping("/dang-giao")
    public ResponseEntity<?> getDangGiao(){
        Integer trangThai = 3;
        return ResponseEntity.ok(service.findAllHDByEmailAndTrangThai(khachHangService.emailKhachHang, trangThai));
    }
    //
    @GetMapping("/hoan-thanh")
    public ResponseEntity<?> getHoanThanh(){
        Integer trangThai = 1;
        return ResponseEntity.ok(service.findAllHDByEmailAndTrangThai(khachHangService.emailKhachHang, trangThai));
    }
    //
    @GetMapping("/da-huy")
    public ResponseEntity<?> getDaHuy(){
        Integer trangThai = 4;
        return ResponseEntity.ok(service.findAllHDByEmailAndTrangThai(khachHangService.emailKhachHang, trangThai));
    }
    //
    @GetMapping("/tra-hang")
    public ResponseEntity<?> getTraHang(){
        Integer trangThai = 5;
        return ResponseEntity.ok(service.findAllHDByEmailAndTrangThai(khachHangService.emailKhachHang, trangThai));
    }

    @GetMapping("/thong-tin-hd/{id}")
    public ResponseEntity<?> getThongTinHD(@PathVariable("id")Integer id){
        return ResponseEntity.ok(service.findAllHDByEmailAndIdHoaDon(khachHangService.emailKhachHang, id));
    }
    //
    @GetMapping("/thong-tin-hdct/{id}")
    public ResponseEntity<?> getThongTinHDCT(@PathVariable("id")Integer id){
        return ResponseEntity.ok(hdctService.findSPByIdHD(id));
    }

    @PutMapping("/huy-don-hang/{id}")
    public ResponseEntity<?> huyDonHang(@PathVariable("id")Integer id,
                                        @RequestBody String lyDo){
        Integer trangThai = 4;
        return ResponseEntity.ok(service.updateDonHang(id, trangThai, lyDo));
    }

    @PutMapping("/tra-don-hang/{id}")
    public ResponseEntity<?> traDonHang(@PathVariable("id")Integer id,
                                        @RequestBody String lyDo){
        Integer trangThai = 5;
        return ResponseEntity.ok(service.updateDonHang(id, trangThai, lyDo));
    }
}