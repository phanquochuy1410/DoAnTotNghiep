package com.example.demo.dto.ctsp;


import com.example.demo.entity.ChatLieu;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.Hang;
import com.example.demo.entity.KichCo;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.SanPham;
import com.example.demo.entity.TheLoai;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ChiTietSanPhamRequest {

    private Integer id;

    private String ma;

    private String gia;

    private String soLuong;

    private String giaKhuyenMai;

    private String mota;

    private String trangThai;

    private String idSanPham;

    private String idKichCo;

    private String idMauSac;

    private String idHang;

    private String idTheLoai;

    private String idChatLieu;

    private String anh;

    private String anh2;

    private String anh3;

    private String anh4;

    private String anh5;

    public ChiTietSanPham map(ChiTietSanPham ctsp) {
//        Integer ck = ctsp.getIdKhuyenMai().getChietKhau();
//        BigDecimal giaKM = ctsp.getGia().multiply(new BigDecimal(ck)).divide(new BigDecimal(0.01));
        ctsp.setGia(BigDecimal.valueOf(Double.parseDouble(this.gia)));
        ctsp.setSoLuong(Integer.valueOf(this.soLuong));
        ctsp.setGiaKhuyenMai(BigDecimal.valueOf(Double.parseDouble(this.giaKhuyenMai)));
        ctsp.setMota(this.mota);
        ctsp.setTrangThai(1);
        ctsp.setAnh(this.anh);
        ctsp.setAnh2(this.anh2);
        ctsp.setAnh3(this.anh3);
        ctsp.setAnh4(this.anh4);
        ctsp.setAnh5(this.anh5);
        ctsp.setIdSanPham((SanPham.builder().id(Integer.parseInt(this.idSanPham)).build()));
        ctsp.setIdKichCo(KichCo.builder().id(Integer.parseInt(this.idKichCo)).build());
        ctsp.setIdMauSac(MauSac.builder().id(Integer.parseInt(this.idMauSac)).build());
        ctsp.setIdChatLieu(ChatLieu.builder().id(Integer.parseInt(this.idChatLieu)).build());
        ctsp.setIdHang(Hang.builder().id(Integer.parseInt(this.idHang)).build());
        ctsp.setIdTheLoai(TheLoai.builder().id(Integer.parseInt(this.idTheLoai)).build());
        ctsp.setCreate_at(LocalDateTime.now());
        ctsp.setUpdate_at(LocalDateTime.now());
        return ctsp;
    }


}
