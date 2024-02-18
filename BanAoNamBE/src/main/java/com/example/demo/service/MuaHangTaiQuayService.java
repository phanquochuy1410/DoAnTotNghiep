package com.example.demo.service;

import com.example.demo.dto.HoaDonChiTiet.HoaDonChiTietRequest;
import com.example.demo.dto.ctsp.ChiTietSanPhamCustom;
import com.example.demo.dto.cuaHang.CuaHangCustum;
import com.example.demo.dto.cuaHang.SearchThuocTinh;
import com.example.demo.dto.hoadon.HoaDonCustom;
import com.example.demo.dto.hoadon.HoaDonRequest;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.repositories.MuaHangTaiQuayRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface MuaHangTaiQuayService {

    List<ChiTietSanPhamCustom> hienThiSanPham(SearchThuocTinh searchThuocTinh , Integer page);



    HoaDonChiTiet addSanPhamIntoHoaDon(Integer idHoaDon, Integer idChiTietSanPham);

    HoaDon addHoaDon(String email);

    List<HoaDonCustom> findMaHoaDon();

//    HoaDonChiTiet updateSoLuong(HoaDonChiTietRequest request , Integer id);

    HoaDonChiTiet updateSoLuong(HoaDonChiTiet request , Integer id);

    HoaDon thanhToan(HoaDonRequest request);

    Integer soLuongTon(Integer id);

    Integer soLuong();
}
