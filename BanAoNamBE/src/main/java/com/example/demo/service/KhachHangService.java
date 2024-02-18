package com.example.demo.service;

import com.example.demo.dto.gh.GioHangChiTietCustom;
import com.example.demo.dto.gh.GioHangChiTietRequest;
import com.example.demo.dto.khachhang.KhachHangCheckOutCustom;
import com.example.demo.dto.khachhang.KhachHangCustom;
import com.example.demo.dto.khachhang.KhachHangRequest;
import com.example.demo.entity.KhachHang;

import java.util.List;

public interface KhachHangService {

    List<KhachHang> getAll(Integer page);

    KhachHangCustom loGinKhachHang(String email, String passWord);

    KhachHang save(KhachHang khachHang);

    KhachHang loadKhachHang();

    KhachHang add(KhachHangRequest request);

    KhachHangCheckOutCustom loadKH();

    KhachHang addRegis(KhachHangRequest request);

    KhachHang update(KhachHangRequest request, Integer id);

    KhachHang detail(Integer id);

    KhachHang delete(Integer id);

    List<KhachHang> search(String sdt);

    List<GioHangChiTietCustom> getSoLuongSanPham();
}
