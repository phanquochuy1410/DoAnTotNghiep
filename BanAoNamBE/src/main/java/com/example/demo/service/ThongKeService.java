package com.example.demo.service;

import com.example.demo.dto.HoaDonChiTiet.DanhSachHoaDonCustom;
import com.example.demo.dto.HoaDonChiTiet.HoaDonChiTietCustom;
import com.example.demo.dto.HoaDonChiTiet.SearchHoaDon;
import com.example.demo.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ThongKeService {

    List<HoaDon> getAll();

    List<DanhSachHoaDonCustom> findDanhSachHoaDon(Integer page);

    List<DanhSachHoaDonCustom> timKiem(String search);

    List<DanhSachHoaDonCustom> timKiemTrangThai(Integer trangThai);

    List<DanhSachHoaDonCustom> searchNgay(SearchHoaDon searchHoaDon);

    List<DanhSachHoaDonCustom> load7NgayTruoc();

    List<DanhSachHoaDonCustom> load1ThangTruoc();

    List<DanhSachHoaDonCustom> loadThangNay();

    List<DanhSachHoaDonCustom> load1NamTruoc();

    List<DanhSachHoaDonCustom> searchKhoangNgay(SearchHoaDon searchHoaDon);

    List<HoaDonChiTietCustom> thongKeSanPham();

    List<DanhSachHoaDonCustom> searchNgayInThongKeNgay(SearchHoaDon searchHoaDon);

    List<DanhSachHoaDonCustom> searchKhoangNgayInThongKeNgay(SearchHoaDon searchHoaDon);

    List<DanhSachHoaDonCustom> thongKeTheoNgay();

    List<DanhSachHoaDonCustom> thongKeTheoThang(Integer thang , Integer nam);

    List<DanhSachHoaDonCustom> thongKeTheoKhoangThang(Integer thangBatDau , Integer thangKetThuc , Integer nam);

    List<DanhSachHoaDonCustom> getNam();

    List<DanhSachHoaDonCustom> thongKeTheoNam(Integer nam);

    List<HoaDonChiTietCustom> load7NgaySanPham();

    List<HoaDonChiTietCustom> load1ThangSanPham();

    List<HoaDonChiTietCustom> load1NamSanPham();

    List<HoaDonChiTietCustom> loadThangNaySanPham();

    List<DanhSachHoaDonCustom> load7NgayInNgay();

    List<DanhSachHoaDonCustom> load1ThangInNgay();

    List<DanhSachHoaDonCustom> loadThangNayInNgay();

    List<DanhSachHoaDonCustom> load1NamInNgay();

    List<DanhSachHoaDonCustom> loadTongTienTrongNgay();

    List<DanhSachHoaDonCustom> loadTongTienTrongNgayTaiQuay();

    List<DanhSachHoaDonCustom> loadSanPhamBanChay();

    List<DanhSachHoaDonCustom> loadTrangThai();

    List<HoaDonChiTietCustom> loadSanPhamBanTheoNgay(SearchHoaDon searchHoaDon);

    List<HoaDonChiTietCustom> loadSanPhamBanTheoKhoangNgay(SearchHoaDon searchHoaDon);

}
