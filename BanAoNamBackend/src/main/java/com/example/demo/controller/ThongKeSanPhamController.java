package com.example.demo.controller;


import com.example.demo.dto.HoaDonChiTiet.SearchHoaDon;
import com.example.demo.service.ThongKeService;
import com.example.demo.service.impl.ThongKeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thong-ke-san-pham")
@CrossOrigin({"*"})
public class ThongKeSanPhamController {

    @Autowired
    private ThongKeService thongKeService = new ThongKeServiceImpl();

    @GetMapping("/san-pham")
    public ResponseEntity thongKeSanPham() {
        return ResponseEntity.ok(thongKeService.thongKeSanPham());
    }

    @GetMapping("/7-ngay-truoc")
    public ResponseEntity load7NgayTruoc() {
        return ResponseEntity.ok(thongKeService.load7NgaySanPham());
    }

    @GetMapping("/1-thang-truoc")
    public ResponseEntity load1ThangTruoc() {
        return ResponseEntity.ok(thongKeService.load1ThangSanPham());
    }

    @GetMapping("/1-nam-truoc")
    public ResponseEntity load1NamTruoc() {
        return ResponseEntity.ok(thongKeService.load1NamSanPham());
    }

    @GetMapping("/thang-nay")
    public ResponseEntity thangNay() {
        return ResponseEntity.ok(thongKeService.loadThangNaySanPham());
    }

    @GetMapping("/tim-kiem-ngay")
    public ResponseEntity timKiemTheoNgay(final SearchHoaDon searchHoaDon) {
        return ResponseEntity.ok(thongKeService.loadSanPhamBanTheoNgay(searchHoaDon));
    }

    @GetMapping("/tim-kiem-khoang-ngay")
    public ResponseEntity timKiemTheoKhoangNgay(final SearchHoaDon searchHoaDon) {
        return ResponseEntity.ok(thongKeService.loadSanPhamBanTheoKhoangNgay(searchHoaDon));
    }
}
