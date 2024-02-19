package com.example.demo.controller;

import com.example.demo.dto.cuaHang.CuaHangCustum;
import com.example.demo.dto.cuaHang.CuaHangRequest;
import com.example.demo.dto.cuaHang.SearchThuocTinh;
import com.example.demo.service.CuaHangService;
import com.example.demo.service.impl.CuaHangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cua-hang")
@CrossOrigin({"*"})
public class CuaHangConTroller {

    @Autowired
    private CuaHangService cuaHangService = new CuaHangServiceImpl();


    @GetMapping("/hien-thi")
    public ResponseEntity<List<CuaHangCustum>> hienThi( final SearchThuocTinh searchThuocTinh ,@RequestParam(defaultValue = "0") Integer page) {
        return ResponseEntity.ok(cuaHangService.hienThi(searchThuocTinh , page));
    }

    @GetMapping("/hien-thi-san-pham-km")
    public ResponseEntity<List<CuaHangCustum>> hienThiSanPhamKM( final SearchThuocTinh searchThuocTinh ,@RequestParam(defaultValue = "0") Integer page) {
        return ResponseEntity.ok(cuaHangService.hienThiKhuyenMai(searchThuocTinh , page));
    }

    @GetMapping("/search")
    public ResponseEntity searchSanPham(final CuaHangRequest cuaHangRequest) {
        return ResponseEntity.ok(cuaHangService.searchSanPham(cuaHangRequest));
    }

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(cuaHangService.getAll());
    }

    @GetMapping("/mau-sac")
    public ResponseEntity mauSac() {
        return ResponseEntity.ok(cuaHangService.getMauSac());
    }

    @GetMapping("/kich-co")
    public ResponseEntity kichCo() {
        return ResponseEntity.ok(cuaHangService.getKichCo());
    }

    @GetMapping("/the-loai")
    public ResponseEntity theLoai() {
        return ResponseEntity.ok(cuaHangService.getTheLoai());
    }

    @GetMapping("/chat-lieu")
    public ResponseEntity chatLieu() {
        return ResponseEntity.ok(cuaHangService.getChatLieu());
    }

}
