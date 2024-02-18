package com.example.demo.service;

import com.example.demo.dto.HoaDonChiTiet.DonMuaKhachHang;
import com.example.demo.dto.HoaDonChiTiet.HoaDonChiTietRequest;
import com.example.demo.dto.hdct.HoaDonChiTietCustom;
import com.example.demo.entity.HoaDonChiTiet;

import java.util.List;

public interface HoaDonChiTietService {

    HoaDonChiTiet save(HoaDonChiTiet hoaDonChiTiet);

    List<HoaDonChiTietCustom> findAllByIdHoaDon(Integer idHD);

    List<HoaDonChiTiet> findAllByIdHoaDonS(Integer idHD);

    HoaDonChiTiet deleteSP(Integer id);

    HoaDonChiTiet addToCart(HoaDonChiTietRequest request , Integer idHoaDon);

    HoaDonChiTiet update(List<HoaDonChiTietRequest> chiTiets, List<Integer> soLuongs);

    List<DonMuaKhachHang> findSPByIdHD(Integer id);

    HoaDonChiTiet updateTaiQuay(HoaDonChiTietRequest hdct, Integer soLuongs);
}
