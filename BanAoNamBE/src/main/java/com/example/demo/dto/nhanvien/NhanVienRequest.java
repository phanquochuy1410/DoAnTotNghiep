package com.example.demo.dto.nhanvien;


import com.example.demo.entity.NhanVien;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class NhanVienRequest {

    private String ma;

    private String ho;

    private String tenDem;

    private String ten;

    private String soDienThoai;

    private String gioiTinh;

    private Date ngaySinh;

    private String diaChi;

    private String email;

    private String matKhau;

    private String chucVu;

    private Integer trangThai;

    public NhanVien map(NhanVien nv){
        nv.setMa(this.ma);
        nv.setHo(this.ho);
        nv.setTenDem(this.tenDem);
        nv.setTen(this.ten);
        nv.setSoDienThoai(this.soDienThoai);
        nv.setGioiTinh(Boolean.valueOf(this.gioiTinh));
        nv.setNgaySinh(this.ngaySinh);
        nv.setDiaChi(this.diaChi);
        nv.setDiaChi(this.diaChi);
        nv.setEmail(this.email);
        nv.setMatKhau(this.matKhau);
        nv.setChucVu(Integer.valueOf(this.chucVu));
        nv.setTrangThai(this.trangThai);
        return nv;
    }

}
