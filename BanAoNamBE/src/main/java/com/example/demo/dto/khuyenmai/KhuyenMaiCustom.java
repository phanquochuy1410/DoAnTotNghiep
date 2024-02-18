package com.example.demo.dto.khuyenmai;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface KhuyenMaiCustom {
    Integer getId ();

    String getMa ();

    String getTen ();

    LocalDate getNgayBatDau ();

    LocalDate getNgayKetThuc ();

    Integer getChietKhau();

    Integer getTrangThai();

}
