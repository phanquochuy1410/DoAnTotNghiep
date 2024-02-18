package com.example.demo.util;

import com.example.demo.repositories.KhachHangRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSetPass(String email , String newPass) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Thay đổi mật khẩu");
        mimeMessageHelper.setText("""
                <div style="box-shadow: 1px">
                             <div>
                               <p>Cảm ơn bạn đã sử dụng trang web của chúng tôi</p>
                               <p>Chúng tôi nhận được rằng bạn đã quên mật khẩu và cần tìm lại</p>
                               <p>Mật khẩu mới của bạn là : """ + newPass + """
                             </div>
                             <div>
                               <a
                                 href="http://127.0.0.1:5500/KhachHang/src/pages/dang-nhap.html"
                                 target="_blank"
                                 style="text-decoration: none"
                               >
                                 <h4>Hãy ấn vào link dưới đây để đăng nhập lại</h4>
                               </a>
                               <p>
                                 Mọi thông tin về khiếu nại thắc mắc. Hoặc bạn không sử dụng dịch vụ
                                 này vui lòng liên hệ :
                               </p>
                               <p> Số điện thoại : 0867147476</p>
                               <p> Email : aophongnamHienDM@gmail.com</p>
                               <p> Địa Chỉ : 13 Trịnh Văn Bô, Phương Canh, Nam Từ Liêm, Hà Nội</p>
                             </div>
                           </div>
                """.formatted(email), true);
        javaMailSender.send(mimeMessage);
    }

}

