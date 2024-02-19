package com.example.demo.service;

import com.example.demo.dto.ctsp.ChiTietSanPhamCustom;
import com.example.demo.dto.cuaHang.CuaHangCustum;
import com.example.demo.dto.cuaHang.CuaHangRequest;
import com.example.demo.dto.cuaHang.SearchThuocTinh;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.SanPham;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CuaHangService {


    List<CuaHangCustum> hienThi(SearchThuocTinh searchThuocTinh , Integer page );

    List<CuaHangCustum> hienThiKhuyenMai(SearchThuocTinh searchThuocTinh , Integer page );

    List<CuaHangCustum> sanPhamMoi(SearchThuocTinh searchThuocTinh, Integer page);

    List<ChiTietSanPham> getAll();

    List<CuaHangCustum> getMauSac();

    List<CuaHangCustum> getKichCo();

    List<CuaHangCustum> getChatLieu();

    List<CuaHangCustum> getTheLoai();

    List<CuaHangCustum> searchSanPham(CuaHangRequest cuaHangRequest);

    List<CuaHangCustum> getMauSacKM();

    List<CuaHangCustum> getKichCoKM();

    List<CuaHangCustum> getChatLieuKM();

    List<CuaHangCustum> getTheLoaiKM();


}
