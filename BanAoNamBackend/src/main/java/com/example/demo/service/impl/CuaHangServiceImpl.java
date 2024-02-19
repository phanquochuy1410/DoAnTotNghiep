package com.example.demo.service.impl;

import com.example.demo.dto.ctsp.ChiTietSanPhamCustom;
import com.example.demo.dto.cuaHang.CuaHangCustum;
import com.example.demo.dto.cuaHang.CuaHangRequest;
import com.example.demo.dto.cuaHang.SearchThuocTinh;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.repositories.CuaHangRepository;
import com.example.demo.service.CuaHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CuaHangServiceImpl implements CuaHangService {

    @Autowired
    private CuaHangRepository cuaHangRepository;


    @Override
    public List<CuaHangCustum> hienThi(SearchThuocTinh searchThuocTinh, Integer page) {
        Pageable pageable = PageRequest.of(page , 12);
        return cuaHangRepository.hienThiSanPhamCuaHang(searchThuocTinh , pageable);
    }

    @Override
    public List<CuaHangCustum> hienThiKhuyenMai(SearchThuocTinh searchThuocTinh, Integer page) {
        Pageable pageable = PageRequest.of(page , 12);
        return cuaHangRepository.hienThiSanPhamCuaHangKhuyenMai(searchThuocTinh , pageable);
    }

    @Override
    public List<CuaHangCustum> sanPhamMoi(SearchThuocTinh searchThuocTinh , Integer page) {
        Pageable pageable = PageRequest.of(page , 9);
        return cuaHangRepository.sanPhamMoi(searchThuocTinh , pageable);
    }

    @Override
    public List<ChiTietSanPham> getAll() {
        return cuaHangRepository.findAll();
    }

    @Override
    public List<CuaHangCustum> getMauSac() {
        return cuaHangRepository.getMauSac();
    }

    @Override
    public List<CuaHangCustum> getKichCo() {
        return cuaHangRepository.getKichCo();
    }

    @Override
    public List<CuaHangCustum> getChatLieu() {
        return cuaHangRepository.getChatLieu();
    }

    @Override
    public List<CuaHangCustum> getTheLoai() {
        return cuaHangRepository.getTheLoai();
    }

    @Override
    public List<CuaHangCustum> searchSanPham(CuaHangRequest cuaHangRequest) {
        return cuaHangRepository.searchSanPham(cuaHangRequest);
    }

    @Override
    public List<CuaHangCustum> getMauSacKM() {
        return cuaHangRepository.getMauSacKM();
    }

    @Override
    public List<CuaHangCustum> getKichCoKM() {
        return cuaHangRepository.getKichCoKM();
    }

    @Override
    public List<CuaHangCustum> getChatLieuKM() {
        return cuaHangRepository.getChatLieuKM();
    }

    @Override
    public List<CuaHangCustum> getTheLoaiKM() {
        return cuaHangRepository.getTheLoaiKM();
    }


}
