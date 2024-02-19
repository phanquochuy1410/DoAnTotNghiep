package com.example.demo.dto.dgsp;


import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.DanhGiaSanPham;
import com.example.demo.entity.KhachHang;
import com.example.demo.entity.SanPham;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DanhGiaSanPhamRequest {

    private String noiDung;

    private String ngayTao;

    private String ngaySua;

    private String soSao;

    private String idSanPham;

    private String trangThai;

    private String idKhachHang;

    public DanhGiaSanPham map(DanhGiaSanPham dgsp){
        dgsp.setNoiDung(this.noiDung);
//        dgsp.setNgayTao(LocalDate.parse(this.ngayTao));
//        dgsp.setNgaySua(LocalDate.parse(this.ngaySua));
        dgsp.setSoSao(Integer.parseInt(this.soSao));
        dgsp.setTrangThai(0);
//        dgsp.setIdSanPham(SanPham.builder().id(Integer.parseInt(this.idSanPham)).build());
        dgsp.setIdKhachHang(KhachHang.builder().id(Integer.parseInt(this.idKhachHang)).build());
        return dgsp;
    }

}
