package com.example.demo.controller;

import com.example.demo.service.impl.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/vnpay")
@CrossOrigin({"*"})
public class Controller {

    @Autowired
    private VNPayService vnPayService;


    @GetMapping("/submitOrder")
    public ResponseEntity submidOrder(@RequestParam("amount") int amount , @RequestParam("don") String don ,
                            HttpServletRequest request){
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(amount, don, baseUrl);
        URI location = URI.create(vnpayUrl);
        return ResponseEntity.status(HttpStatus.SEE_OTHER).location(location).build();
    }

    @GetMapping("/vnpay-payment")
    public ResponseEntity GetMapping(HttpServletRequest request , @RequestParam("vnp_TransactionStatus") Integer trangThai){
        int paymentStatus =vnPayService.orderReturn(request);
        if (trangThai == 00){
            return ResponseEntity.ok("Vui lòng quay trở lại để xác nhận thanh toán");
        }else{
            return ResponseEntity.ok("Thanh toán thất bại");
        }
    }
}
