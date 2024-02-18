package com.example.demo.controller;

import com.example.demo.dto.HoaDonChiTiet.DanhSachHoaDonCustom;
import com.example.demo.dto.HoaDonChiTiet.SearchHoaDon;
import com.example.demo.entity.HoaDon;
import com.example.demo.repositories.ThongKeRepository;
import com.example.demo.service.ThongKeService;
import com.example.demo.service.impl.ThongKeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/thong-ke")
@CrossOrigin({"*"})
public class ThongKeController {

    @Autowired
    private ThongKeService thongKeService = new ThongKeServiceImpl();

    @Autowired
    private ThongKeRepository thongKeRepository;


    @GetMapping("/show")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(thongKeService.getAll());
    }

    @GetMapping("/hien-thi")
    public ResponseEntity hienThi(final HoaDon hoaDon,@RequestParam(defaultValue = "0") Integer page ) {
        return ResponseEntity.ok(thongKeService.findDanhSachHoaDon(page));
    }


    @GetMapping("/tim-kiem")
    public ResponseEntity timKiem(final HoaDon hoaDon) {
        List<DanhSachHoaDonCustom> timKiem = thongKeService.timKiem(hoaDon.getMa());
        return ResponseEntity.ok(timKiem);
    }

    @GetMapping("/trang-thai")
    public ResponseEntity timKiemTrangThai(final HoaDon hoaDon) {
        List<DanhSachHoaDonCustom> timKiemTrangThai = thongKeService.timKiemTrangThai(hoaDon.getTrangThai());
        return ResponseEntity.ok(timKiemTrangThai);
    }

    @GetMapping("/tim-ngay")
    public ResponseEntity searchNgay(final SearchHoaDon searchHoaDon) {
        List<DanhSachHoaDonCustom> searchNgay = thongKeService.searchNgay(searchHoaDon);
        return ResponseEntity.ok(searchNgay);
    }

    @GetMapping("/tim-khoang-ngay")
    public ResponseEntity searchKhoangNgay(final SearchHoaDon searchHoaDon) {
        List<DanhSachHoaDonCustom> searchKhoangNgay = thongKeService.searchKhoangNgay(searchHoaDon);
        return ResponseEntity.ok(searchKhoangNgay);
    }


    @GetMapping("/7-ngay-truoc")
    public ResponseEntity load7NgayTruoc() {
        return ResponseEntity.ok(thongKeService.load7NgayTruoc());
    }

    @GetMapping("/1-thang-truoc")
    public ResponseEntity load1ThangTruoc() {
        return ResponseEntity.ok(thongKeService.load1ThangTruoc());
    }

    @GetMapping("/1-nam-truoc")
    public ResponseEntity load1NamTruoc() {
        return ResponseEntity.ok(thongKeService.load1NamTruoc());
    }

    @GetMapping("/thang-nay")
    public ResponseEntity loadThangNay() {
        return ResponseEntity.ok(thongKeService.loadThangNay());
    }

}
