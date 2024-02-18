package com.example.demo.dto.khuyenmai;

import com.example.demo.entity.KhuyenMai;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class KhuyenMaiRequest {

    public final Integer SBD = 1;
    public final Integer DHD = 0;
    public final Integer HH = 2;

    String ma;

    @NotBlank
    String ten;

    LocalDate ngayBatDau;

    LocalDate ngayKetThuc;

    Integer chietKhau;

    Integer trangThai;

    public KhuyenMai map ( KhuyenMai kh ) {
        kh.setMa(this.getMa());
        kh.setTen(this.getTen());
        kh.setNgayBatDau(this.getNgayBatDau());
        kh.setNgayKetThuc(this.getNgayKetThuc());
        kh.setChietKhau(this.getChietKhau());
        if(this.ngayKetThuc.isAfter(LocalDate.now()) && this.ngayBatDau.isBefore(LocalDate.now())){
            kh.setTrangThai(DHD);
        }
        if(this.ngayBatDau.isAfter(LocalDate.now())){
            kh.setTrangThai(SBD);
        }
        return kh;
    }
}
