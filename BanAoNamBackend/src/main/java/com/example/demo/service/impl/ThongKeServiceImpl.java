package com.example.demo.service.impl;

import com.example.demo.dto.HoaDonChiTiet.DanhSachHoaDonCustom;
import com.example.demo.dto.HoaDonChiTiet.HoaDonChiTietCustom;
import com.example.demo.dto.HoaDonChiTiet.SearchHoaDon;
import com.example.demo.entity.HoaDon;
import com.example.demo.repositories.ThongKeRepository;
import com.example.demo.service.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ThongKeServiceImpl implements ThongKeService {

    @Autowired
    private ThongKeRepository thongKeRepository;

    @Override
    public List<HoaDon> getAll() {
        return thongKeRepository.findAll();
    }

    @Override
    public List<DanhSachHoaDonCustom> timKiem(String search) {
        return thongKeRepository.timKiem(search);
    }

    @Override
    public List<DanhSachHoaDonCustom> timKiemTrangThai(Integer trangThai) {
        return thongKeRepository.timKiemTrangThai(trangThai);
    }

    @Override
    public List<HoaDonChiTietCustom> thongKeSanPham() {
        return thongKeRepository.thongKeSanPham();
    }

    @Override
    public List<DanhSachHoaDonCustom> searchNgay(SearchHoaDon searchHoaDon) {
        return thongKeRepository.searchNgay(searchHoaDon);
    }

    @Override
    public List<DanhSachHoaDonCustom> load7NgayTruoc() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -6);
        Date sevenDay = calendar.getTime();
        LocalDate localDate = sevenDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return thongKeRepository.load7NgayTruoc(localDate);
    }

    @Override
    public List<DanhSachHoaDonCustom> load1ThangTruoc() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date sevenDay = calendar.getTime();
        LocalDate localDate = sevenDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return thongKeRepository.load7NgayTruoc(localDate);
    }

    @Override
    public List<DanhSachHoaDonCustom> loadThangNay() {
        LocalDate date = LocalDate.now();
        return thongKeRepository.loadThangNay(date);
    }

    @Override
    public List<DanhSachHoaDonCustom> load1NamTruoc() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        Date sevenDay = calendar.getTime();
        LocalDate localDate = sevenDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return thongKeRepository.load7NgayTruoc(localDate);
    }

    @Override
    public List<DanhSachHoaDonCustom> searchKhoangNgay(SearchHoaDon searchHoaDon) {
        return thongKeRepository.searchKhoangNgay(searchHoaDon);
    }

    @Override
    public List<DanhSachHoaDonCustom> searchNgayInThongKeNgay(SearchHoaDon searchHoaDon) {
        return thongKeRepository.searchNgayInThongKeNgay(searchHoaDon);
    }

    @Override
    public List<DanhSachHoaDonCustom> searchKhoangNgayInThongKeNgay(SearchHoaDon searchHoaDon) {
        return thongKeRepository.searchKhoangNgayInThongKeNgay(searchHoaDon);
    }


    @Override
    public List<DanhSachHoaDonCustom> findDanhSachHoaDon( Integer page) {
        Pageable pageable = PageRequest.of(page, 20);
        return thongKeRepository.findDanhSachHoaDon(pageable);
    }

    @Override
    public List<DanhSachHoaDonCustom> thongKeTheoNgay() {
        return thongKeRepository.thongKeTheoNgay();
    }

    @Override
    public List<DanhSachHoaDonCustom> thongKeTheoThang(Integer thang , Integer nam) {
        return thongKeRepository.thongKeTheoThang(thang , nam);
    }

    @Override
    public List<DanhSachHoaDonCustom> thongKeTheoKhoangThang(Integer thangBatDau, Integer thangKetThuc, Integer nam) {
        return thongKeRepository.thongKeTheoKhoangThang(thangBatDau, thangKetThuc, nam);
    }

    @Override
    public List<DanhSachHoaDonCustom> getNam() {
        return thongKeRepository.getNam();
    }

    @Override
    public List<DanhSachHoaDonCustom> thongKeTheoNam(Integer nam) {
        return thongKeRepository.thongKeTheoNam(nam);
    }

    @Override
    public List<HoaDonChiTietCustom> load7NgaySanPham() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -6);
        Date date = calendar.getTime();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return thongKeRepository.load7NgaySanPham(localDate);
    }

    @Override
    public List<HoaDonChiTietCustom> load1ThangSanPham() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date date = calendar.getTime();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return thongKeRepository.load7NgaySanPham(localDate);
    }

    @Override
    public List<HoaDonChiTietCustom> load1NamSanPham() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        Date date = calendar.getTime();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return thongKeRepository.load7NgaySanPham(localDate);
    }

    @Override
    public List<HoaDonChiTietCustom> loadThangNaySanPham() {
        LocalDate date = LocalDate.now();
        return thongKeRepository.loadThangNaySanPham(date);
    }

    @Override
    public List<DanhSachHoaDonCustom> load7NgayInNgay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -6);
        Date date = calendar.getTime();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return thongKeRepository.load7NgayTruocInNgay(localDate);
    }

    @Override
    public List<DanhSachHoaDonCustom> load1ThangInNgay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date date = calendar.getTime();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return thongKeRepository.load7NgayTruocInNgay(localDate);
    }

    @Override
    public List<DanhSachHoaDonCustom> loadThangNayInNgay() {
        LocalDate date = LocalDate.now();
        return thongKeRepository.loadThangNayInNgay(date);
    }

    @Override
    public List<DanhSachHoaDonCustom> load1NamInNgay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        Date date = calendar.getTime();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return thongKeRepository.load7NgayTruocInNgay(localDate);
    }

    @Override
    public List<DanhSachHoaDonCustom> loadTongTienTrongNgay() {
        return thongKeRepository.loadTongTienTrongNgay();
    }

    @Override
    public List<DanhSachHoaDonCustom> loadTongTienTrongNgayTaiQuay() {
        return thongKeRepository.loadTongTienTrongNgayTaiQuay();
    }

    @Override
    public List<DanhSachHoaDonCustom> loadSanPhamBanChay() {
        return thongKeRepository.loadSanPhamBanChay();
    }

    @Override
    public List<DanhSachHoaDonCustom> loadTrangThai() {
        return thongKeRepository.loadTrangThai();
    }

    @Override
    public List<HoaDonChiTietCustom> loadSanPhamBanTheoNgay(SearchHoaDon searchHoaDon) {
        return thongKeRepository.loadSanPhamBanTheoNgay(searchHoaDon);
    }

    @Override
    public List<HoaDonChiTietCustom> loadSanPhamBanTheoKhoangNgay(SearchHoaDon searchHoaDon) {
        return thongKeRepository.loadSanPhamBanTheoKhoangNgay(searchHoaDon);
    }
}
