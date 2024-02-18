package com.example.demo.controller;

import com.example.demo.service.ThongKeService;
import com.example.demo.service.impl.ThongKeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thong-ke-ban-chay")
@CrossOrigin({"*"})
public class ThongKeBanChayController {
    @Autowired
    private ThongKeService thongKeService = new ThongKeServiceImpl();

    @GetMapping("/load-tong-tien-ngay")
    public ResponseEntity loadTongTienNgay() {
        return ResponseEntity.ok(thongKeService.loadTongTienTrongNgay());
    }

    @GetMapping("/load-tong-tien-ngay-tai-quay")
    public ResponseEntity loadTongTienNgayTaiQuay() {
        return ResponseEntity.ok(thongKeService.loadTongTienTrongNgayTaiQuay());
    }

    @GetMapping("/load-san-pham-ban-chay")
    public ResponseEntity loadSanPhamBanChayTrongNgay() {
        return ResponseEntity.ok(thongKeService.loadSanPhamBanChay());
    }

    @GetMapping("/load-trang-thai")
    public ResponseEntity loadTrangThai() {
        return ResponseEntity.ok(thongKeService.loadTrangThai());
    }
}
