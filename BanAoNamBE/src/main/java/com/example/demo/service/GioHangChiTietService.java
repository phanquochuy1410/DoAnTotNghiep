package com.example.demo.service;

import com.example.demo.dto.ctsp.ChiTietSanPhamCustom;
import com.example.demo.dto.ctsp.ChiTietSanPhamRequest;
import com.example.demo.dto.gh.GioHangChiTietCustom;
import com.example.demo.dto.gh.GioHangChiTietRequest;
import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GioHangChiTietService {

    GioHangChiTiet update(List<GioHangChiTietRequest> request, Integer trangThai);

    GioHangChiTiet deleteSP(List<GioHangChiTietRequest> requests);

    GioHangChiTiet update1(GioHangChiTietRequest request, Integer trangThai);

    ChiTietSanPhamCustom findSP(String tenSP, Integer mauSac, Integer kichCo);

    GioHangChiTiet addToCart(ChiTietSanPhamRequest request);

    GioHangChiTiet addToCart1(ChiTietSanPhamRequest request);

    HoaDonChiTiet chotDon(List<GioHangChiTietRequest> list, Float tongTien ,String tenNguoiNhan , String soNguoiNhan);

    HoaDonChiTiet chotDonVnpay1(List<GioHangChiTietRequest> list, Float tongTien , String tenNguoiNhan, String soNguoiNhan );

    String chotDonVnpay( Integer tongTien , String don , String urlReturn);

    void removeDonHang(GioHangChiTiet gioHangChiTiet);

    HoaDon chotDon2(Float tongTien , String tenNguoiNhan , String soNguoiNhan);

    HoaDon chotDonVnpay2(Float tongTien,String tenNguoiNhan, String soNguoiNhan);

    List<GioHangChiTietCustom> getListGioHang(String email);

    GioHangChiTiet update(GioHangChiTietRequest chiTiets);

    List<GioHangChiTietCustom> getListGHCheckout(String email);


}
