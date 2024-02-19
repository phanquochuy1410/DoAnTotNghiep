package com.example.demo.controller;

import com.example.demo.dto.hoadon.HoaDonCustom;
import com.example.demo.dto.hoadon.HoaDonRequest;
import com.example.demo.dto.hoadon.HoaDonSearch;
import com.example.demo.entity.HoaDon;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hoa-don")
@CrossOrigin({"*"})
public class HoaDonController {
    @Autowired
    private HoaDonService service;

    @GetMapping("/get-page")
    public ResponseEntity<?> getPageHoaDon(@RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo) {
        return ResponseEntity.ok(service.getPage(pageNo));
    }

    @PutMapping("/update/khach-hang")
    public ResponseEntity<?> updateKhachHang(@RequestBody HoaDonRequest request) {
        return ResponseEntity.ok(service.updateHoaDon(request));
    }

    @GetMapping("/tim-ngay")
    public ResponseEntity searchNgay(final HoaDonSearch hoaDonSearch) {
        List<HoaDonCustom> searchNgay = service.timKiemNgay(hoaDonSearch);
        return ResponseEntity.ok(searchNgay);
    }

    @GetMapping("/tim-ngay1")
    public ResponseEntity searchNgay1(final HoaDonSearch hoaDonSearch) {
        List<HoaDonCustom> searchNgay = service.timKiemNgay1(hoaDonSearch);
        return ResponseEntity.ok(searchNgay);
    }

    @GetMapping("/tim-khoang-ngay")
    public ResponseEntity searchKhoangNgay(final HoaDonSearch hoaDonSearch) {
        List<HoaDonCustom> searchNgay = service.timKiemKhoangNgay(hoaDonSearch);
        return ResponseEntity.ok(searchNgay);
    }

    @GetMapping("/tim-khoang-ngay1")
    public ResponseEntity searchKhoangNgay1(final HoaDonSearch hoaDonSearch) {
        List<HoaDonCustom> searchNgay = service.timKiemKhoangNgay1(hoaDonSearch);
        return ResponseEntity.ok(searchNgay);
    }

    @PutMapping("/update-trang-thai")
    public ResponseEntity<?> updateTrangThai(@RequestParam("listID") List<Integer> idTT, @RequestParam("trangThai") Integer trangThai, @RequestParam("email") String email, @RequestParam("lyDo") String lyDo) {
        return ResponseEntity.ok(service.updateTrangThai(idTT, trangThai, email, lyDo));
    }

    @GetMapping("/xu-ly-hoan-tra")
    public ResponseEntity<?> xuLyHoanTra(@RequestParam("idHoaDon") Integer idHoaDon) {
        return ResponseEntity.ok(service.xuLyHoanTra(idHoaDon));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/get-all-huy")
    public ResponseEntity<?> getAllHuy() {
        return ResponseEntity.ok(service.findAllHDDH());
    }

    @GetMapping("/get-all-xac-nhan")
    public ResponseEntity<?> getAllHD() {
        return ResponseEntity.ok(service.getAllHD());
    }

    @GetMapping("/get-all-xu-ly")
    public ResponseEntity<?> getAllXL() {
        return ResponseEntity.ok(service.getAllXL());
    }

    @GetMapping("/tim-kiem")
    public ResponseEntity<?> searchHDByMa(@RequestParam("trangThai") Integer trangThai, @RequestParam("searchByMa") String ma) {
        return ResponseEntity.ok(service.findHoaDonByMa(trangThai, ma));
    }

    @GetMapping("/tim-kiem-huy-hoan")
    public ResponseEntity<?> searchHDByMaHuyHoan(@RequestParam("trangThai") Integer trangThai, @RequestParam("searchByMa") String ma) {
        return ResponseEntity.ok(service.searchHuyHoan(trangThai, ma));
    }

    @GetMapping("/tim-kiem-cho-xn")
    public ResponseEntity<?> searchHDXNByMa(@RequestParam("trangThai") Integer trangThai, @RequestParam("searchByMa") String ma) {
        return ResponseEntity.ok(service.findHoaDonChoXacNhanByMa(trangThai, ma));
    }

    @GetMapping("/search-trang-thai")
    public ResponseEntity<?> getListTrangThai(@RequestParam("trangThaiS") Integer trangThaiS, @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo) {
        return ResponseEntity.ok(service.findHoaDonByTrangThai(trangThaiS, pageNo));
    }

    @GetMapping("/search-trang-thai-huy-hoan")
    public ResponseEntity<?> getListTrangThaiHuyHoan(@RequestParam("trangThaiS") Integer trangThaiS, @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo) {
        return ResponseEntity.ok(service.findHoaDonHuyHoan(trangThaiS, pageNo));
    }

    @GetMapping("/search-trang-thai-xac-nhan")
    public ResponseEntity<?> getListTrangThaiXacNhan(@RequestParam("trangThaiS") Integer trangThaiS, @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo) {
        return ResponseEntity.ok(service.findHoaDonXacNhan(trangThaiS, pageNo));
    }
}
