package com.example.demo.controller;

import com.example.demo.dto.khachhang.KhachHangRequest;
import com.example.demo.dto.nhanvien.NhanVienRequest;
import com.example.demo.service.KhachHangService;
import com.example.demo.service.SendMailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/khach-hang")
@CrossOrigin({"*"})
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private SendMailService sendMailService;

    @PutMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) {
        return new ResponseEntity<>(sendMailService.forgotPass(email), HttpStatus.OK);
    }
//
//    @PutMapping("/set-pass")
//    public ResponseEntity<String> setPass(@RequestParam("email") String email, @RequestHeader String newPass){
//        return new ResponseEntity<>(sendMailService.setPass(email , newPass), HttpStatus.OK);
//    }

    @GetMapping("/hien-thi")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        return ResponseEntity.ok(khachHangService.getAll(page));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(khachHangService.detail(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid KhachHangRequest request, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return ResponseEntity.ok(errors);
        } else {
            return ResponseEntity.ok(khachHangService.add(request));
        }
    }

    @PostMapping("/add-regis")
    public ResponseEntity<?> addRegis(@RequestBody @Valid KhachHangRequest request, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return ResponseEntity.ok(errors);
        } else {
            return ResponseEntity.ok(khachHangService.addRegis(request));
        }
    }


    @GetMapping("/load-khach-hang")
    public ResponseEntity<?> loadKhachHang() {
        return ResponseEntity.ok(khachHangService.loadKhachHang());
    }

    @GetMapping("/so-luong")
    public ResponseEntity<?> loadSoLuongSanPham() {
        return ResponseEntity.ok(khachHangService.getSoLuongSanPham());
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginKhachHang(@RequestParam("email") String email, @RequestParam("password") String passWord) {
        return ResponseEntity.ok(khachHangService.loGinKhachHang(email, passWord));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody @Valid KhachHangRequest request, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return ResponseEntity.ok(errors);
        } else {
            return ResponseEntity.ok(khachHangService.update(request, Integer.parseInt(id)));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(khachHangService.delete(id));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("sdtSearch")String sdt){
        return ResponseEntity.ok(khachHangService.search(sdt));
    }

}
