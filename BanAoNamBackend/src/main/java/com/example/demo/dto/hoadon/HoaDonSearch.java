package com.example.demo.dto.hoadon;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
public class HoaDonSearch {

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngayTaoSearch;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngayTaoSearchs;

    private String maHoaDonSearch;

    private String tenKhachHangSearch;

    private String diaChiSearch;

    private Integer trangThaiSearch;
}
