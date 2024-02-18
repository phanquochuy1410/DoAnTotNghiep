package com.example.demo.dto.HoaDonChiTiet;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class SearchHoaDon {

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngayTaoSearch;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate ngayTaoSearchs;

    private String maHoaDonSearch;

    private String tenKhachHangSearch;

    private String diaChiSearch;

    private Integer trangThaiSearch;

    private String searchTK;

    private Integer searchTrangThai;

}
