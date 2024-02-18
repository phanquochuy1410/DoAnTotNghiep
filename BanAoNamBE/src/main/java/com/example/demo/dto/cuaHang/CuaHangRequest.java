package com.example.demo.dto.cuaHang;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
public class CuaHangRequest {

    private Integer id;

    private String tenSanPham;

    private Float gia;

    private Float giaKM;

    private String tenAnh;

    private String ten;

    private Integer soLuong;



}
