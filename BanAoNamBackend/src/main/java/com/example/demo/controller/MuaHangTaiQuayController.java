package com.example.demo.controller;

import com.example.demo.dto.HoaDonChiTiet.HoaDonChiTietRequest;
import com.example.demo.dto.cuaHang.SearchThuocTinh;
import com.example.demo.dto.hoadon.HoaDonRequest;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.service.MuaHangTaiQuayService;
import com.example.demo.service.impl.MuaHangTaiQuayServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mua-hang-tai-quay")
@CrossOrigin({"*"})
public class MuaHangTaiQuayController {

    @Autowired
    private MuaHangTaiQuayService service = new MuaHangTaiQuayServiceImpl();

    @GetMapping("/add-sp-hd")
    public ResponseEntity addSPintoHD(@RequestParam("idHoaDon") Integer idHoaDon, @RequestParam("idChiTietSanPham") Integer idChiTietSanPham) {
        return ResponseEntity.ok(service.addSanPhamIntoHoaDon(idHoaDon, idChiTietSanPham));
    }

    @GetMapping("/hien-thi")
    public ResponseEntity hienThi(@RequestParam(defaultValue = "0") Integer page, final SearchThuocTinh searchThuocTinh) {
        return ResponseEntity.ok(service.hienThiSanPham(searchThuocTinh, page));
    }

    @PostMapping("/add-hoa-don")
    public ResponseEntity addHoaDon(@RequestParam("email") String email) {
        return ResponseEntity.ok(service.addHoaDon(email));
    }

    @GetMapping("/hien-thi-ma")
    public ResponseEntity hienThiMa() {
        return ResponseEntity.ok(service.findMaHoaDon());
    }


    @PutMapping("/update-so-luong/{id}")
    public ResponseEntity updateSoLuong(@PathVariable("id") Integer id, @RequestBody @Valid HoaDonChiTiet request) {
        return ResponseEntity.ok(service.updateSoLuong(request, id));
    }

    @PutMapping("/thanh-toan")
    public ResponseEntity thanhToan( @RequestBody @Valid HoaDonRequest request) {
        return ResponseEntity.ok(service.thanhToan(request));
    }

    @GetMapping("/so-luong-ton")
    public ResponseEntity soLuongTon(@RequestParam Integer id) {
        return ResponseEntity.ok(service.soLuongTon(id));
    }

    @GetMapping("/so-luong-hoa-don")
    public ResponseEntity soLuongHoaDon() {
        return ResponseEntity.ok(service.soLuong());
    }
}
