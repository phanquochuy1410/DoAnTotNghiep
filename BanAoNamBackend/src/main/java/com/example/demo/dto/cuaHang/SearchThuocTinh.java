package com.example.demo.dto.cuaHang;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SearchThuocTinh {

    private Integer id;

    private List<String> tenMau;

    private List<Integer> tenMaus;

    private List<String> kichCo;

    private List<String> theLoai;

    private List<String> chatLieu;

    private String tenSanPham;

    private String mauSac;

    private String tenHang;

    private String tenTheLoai;

    private String tenChatLieu;

    private String tenKichCo;

    private Float giaBatDau;

    private Float giaKetThuc;


}
