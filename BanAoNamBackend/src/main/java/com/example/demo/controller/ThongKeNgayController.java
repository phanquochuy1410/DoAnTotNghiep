package com.example.demo.controller;

import com.example.demo.dto.HoaDonChiTiet.DanhSachHoaDonCustom;
import com.example.demo.dto.HoaDonChiTiet.SearchHoaDon;
import com.example.demo.service.ThongKeService;
import com.example.demo.service.impl.ThongKeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/thong-ke-ngay")
@CrossOrigin({"*"})
public class ThongKeNgayController {

    @Autowired
    private ThongKeService thongKeService = new ThongKeServiceImpl();

    @GetMapping("/hien-thi")
    public ResponseEntity thongKeSanPham() {
        return ResponseEntity.ok(thongKeService.thongKeTheoNgay());
    }

    @GetMapping("/tim-ngay")
    public ResponseEntity searchNgay(final SearchHoaDon searchHoaDon) {
        List<DanhSachHoaDonCustom> searchNgay = thongKeService.searchNgayInThongKeNgay(searchHoaDon);
        return ResponseEntity.ok(searchNgay);
    }

    @GetMapping("/tim-khoang-ngay")
    public ResponseEntity searchKhoangNgay(final SearchHoaDon searchHoaDon) {
        List<DanhSachHoaDonCustom> searchKhoangNgay = thongKeService.searchKhoangNgayInThongKeNgay(searchHoaDon);
        return ResponseEntity.ok(searchKhoangNgay);
    }

    @GetMapping("/7-ngay-truoc")
    public ResponseEntity load7NgayTruoc() {
        return ResponseEntity.ok(thongKeService.load7NgayInNgay());
    }

    @GetMapping("/1-thang-truoc")
    public ResponseEntity load1ThangTruoc() {
        return ResponseEntity.ok(thongKeService.load1ThangInNgay());
    }

    @GetMapping("/1-nam-truoc")
    public ResponseEntity load1NamTruoc() {
        return ResponseEntity.ok(thongKeService.load1NamInNgay());
    }

    @GetMapping("/thang-nay")
    public ResponseEntity thangNay() {
        return ResponseEntity.ok(thongKeService.loadThangNayInNgay());
    }
}
