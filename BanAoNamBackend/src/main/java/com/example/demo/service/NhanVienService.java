package com.example.demo.service;

import com.example.demo.dto.nhanvien.NhanVienCustom;
import com.example.demo.dto.nhanvien.NhanVienRequest;
import com.example.demo.entity.KhachHang;
import com.example.demo.entity.NhanVien;

import java.util.List;

public interface NhanVienService {

    List<NhanVien> getAllNhanVien();

    List<NhanVien> getAllByTrangThai(Integer trangThai, Integer page);

    NhanVien add(NhanVienRequest request);

    NhanVienCustom loginNhanVien(String email, String passWord);

    NhanVien update(NhanVienRequest request, Integer id);

    List<NhanVien> updateTrangThai(List<Integer> listID, Integer trangThai);

    NhanVien delete(Integer id);

    NhanVien detail(Integer id);

    List<NhanVien> searchDL(Integer trangThai, String sdt, int page);

    NhanVien loadNhanVien();
}
