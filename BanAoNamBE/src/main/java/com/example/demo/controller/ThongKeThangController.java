package com.example.demo.controller;

import com.example.demo.service.ThongKeService;
import com.example.demo.service.impl.ThongKeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thong-ke-thang")
@CrossOrigin({"*"})
public class ThongKeThangController {

    @Autowired
    private ThongKeService thongKeService = new ThongKeServiceImpl();

    @GetMapping("/hien-thi")
    public ResponseEntity thongKeThang(@RequestParam("thang") Integer thang, @RequestParam("nam") Integer nam) {
        return ResponseEntity.ok(thongKeService.thongKeTheoThang(thang, nam));
    }

    @GetMapping("/hien-thi-khoang")
    public ResponseEntity thongKeKhoangThang(@RequestParam("thangBatDau") Integer thangBatDau,
                                             @RequestParam("thangKetThuc") Integer thangKetThuc,
                                            @RequestParam("nam") Integer nam) {
        return ResponseEntity.ok(thongKeService.thongKeTheoKhoangThang(thangBatDau , thangKetThuc, nam));
    }

    @GetMapping("/hien-thi-nam")
    public ResponseEntity getNam() {
        return ResponseEntity.ok(thongKeService.getNam());
    }
}
