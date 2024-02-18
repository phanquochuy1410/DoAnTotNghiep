package com.example.demo.service.impl;

import com.example.demo.entity.KhachHang;
import com.example.demo.repositories.KhachHangRepository;
import com.example.demo.service.SendMailService;
import com.example.demo.util.EmailUtil;
import com.example.demo.util.PasswordGenerator;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private KhachHangRepository khachHangRepository;

    private String genPass() {
        String randomPassword = PasswordGenerator.generateRandomPassword(12);
        return randomPassword;
    }

    @Override
    public String forgotPass(String email) {
        KhachHang kh = khachHangRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Tài khoản" + email + " chưa được đăng kí"));
        try {
            String newPass = genPass();
            emailUtil.sendSetPass(email, newPass.trim());
            kh.setMatKhau(newPass);
            khachHangRepository.save(kh);
        } catch (MessagingException e) {
            throw new RuntimeException("Không thể xác minh để thay mật khẩu." +
                    " Vui lòng thử lại");
        }
        return "Vui lòng check email để đặt lại mật khẩu";
    }

//    @Override
//    public String setPass(String email, String newPass) {
//        KhachHang kh = khachHangRepository.findByEmail(email).orElseThrow(
//                () -> new RuntimeException("Tài khoản" + email + " chưa được đăng kí"));
//        kh.setMatKhau(newPass);
//        khachHangRepository.save(kh);
//        return "Mật khẩu đã được đổi lại. Vui lòng đăng nhập lại";
//    }
}
