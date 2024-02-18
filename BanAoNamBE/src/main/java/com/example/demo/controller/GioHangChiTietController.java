package com.example.demo.controller;

import com.example.demo.dto.HoaDonChiTiet.HoaDonChiTietRequest;
import com.example.demo.dto.ctsp.ChiTietSanPhamRequest;
import com.example.demo.dto.gh.GioHangChiTietRequest;
import com.example.demo.dto.khachhang.KhachHangRequest;
import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.service.GioHangChiTietService;
import com.example.demo.service.impl.KhachHangServiceImpl;
import com.example.demo.service.impl.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/gio-hang-chi-tiet")
@CrossOrigin({"*"})
public class GioHangChiTietController {
    @Autowired
    private GioHangChiTietService service;

    @Autowired
    private KhachHangServiceImpl khachHangservice;

    @Autowired
    private VNPayService vnPayService;

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody List<GioHangChiTietRequest> list, @RequestParam("trangThai") Integer trangThai) {
        return ResponseEntity.ok(service.update(list, trangThai));
    }

    @PutMapping("/update-so-luong")
    public ResponseEntity<?> updateHDCT(@RequestBody GioHangChiTietRequest requests) {
        return ResponseEntity.ok(service.update(requests));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteGioHang(@RequestBody List<GioHangChiTietRequest> list){
        return ResponseEntity.ok(service.deleteSP(list));
    }

    @PostMapping("/thanh-toan")
    public ResponseEntity<?> chotDon(@RequestBody List<GioHangChiTietRequest> list, @RequestParam("tongTien") Float tongTien , @RequestParam("tenNguoiNhan") String tenNguoiNhan ,@RequestParam("soNguoiNhan") String soNguoiNhan ) {
        return ResponseEntity.ok(service.chotDon(list, tongTien , tenNguoiNhan , soNguoiNhan));
    }

    @PostMapping("/thanh-toan1")
    public ResponseEntity<?> chotDon1(@RequestBody List<GioHangChiTietRequest> list, @RequestParam("tongTien") Float tongTien , @RequestParam("tenNguoiNhan") String tenNguoiNhan ,@RequestParam("soNguoiNhan") String soNguoiNhan ) {
        return ResponseEntity.ok(service.chotDonVnpay1(list, tongTien, tenNguoiNhan ,soNguoiNhan));
    }

    @GetMapping("/thanh-toan-vnpay")
    public ResponseEntity chotDonVnpay( @RequestParam("don") String don,
                                        @RequestParam("tongTien") Integer tongTien ,
                                        HttpServletRequest request) {

        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = service.chotDonVnpay(tongTien , don, baseUrl);
        URI location = URI.create(vnpayUrl);
        return ResponseEntity.status(HttpStatus.SEE_OTHER).location(location).build();
    }


    @GetMapping("/vnpay-payment")
    public ResponseEntity vnpayPayment(HttpServletRequest request){
        int paymentStatus =vnPayService.orderReturn(request);
        if (paymentStatus == 1){
            return ResponseEntity.ok("Vui lòng quay trở lại để xác nhận thanh toán");
        }else{
            return ResponseEntity.ok("Thanh toán thất bại");
        }
    }

    @GetMapping("/check")
    public ModelAndView check() {
        // Xử lý trang /check và trả về giao diện và dữ liệu
        ModelAndView modelAndView = new ModelAndView("check");
        modelAndView.addObject("message", "Checked!"); // Thêm dữ liệu cho trang
        return modelAndView;
    }

    @GetMapping("/find-sp")
    public ResponseEntity<?> findSP(@RequestParam("tenSP") String tenSP, @RequestParam("mauSac") Integer mauSac, @RequestParam("kichCo") Integer kichCo) {
        return ResponseEntity.ok(service.findSP(tenSP, mauSac, kichCo));
    }

    @PutMapping("/add-to-cart")
    private ResponseEntity<?> addToCart(@RequestBody ChiTietSanPhamRequest request) {
        return ResponseEntity.ok(service.addToCart(request));
    }

    @PutMapping("/add-to-cart1")
    private ResponseEntity<?> addToCart1(@RequestBody ChiTietSanPhamRequest request) {
        return ResponseEntity.ok(service.addToCart1(request));
    }

    @GetMapping("/load-khach-hang")
    public ResponseEntity<?> getKH() {
        return ResponseEntity.ok(khachHangservice.loadKH());
    }

    @GetMapping("/get-page")
    public ResponseEntity<?> getPageGH() {
        return ResponseEntity.ok(service.getListGioHang(khachHangservice.emailKhachHang));
    }

    @GetMapping("/get-list-check-out")
    public ResponseEntity<?> getListCO() {
        return ResponseEntity.ok(service.getListGHCheckout(khachHangservice.emailKhachHang));
    }
}
