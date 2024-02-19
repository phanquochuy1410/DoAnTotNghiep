package com.example.demo.service.impl;

import com.example.demo.dto.khachhang.KhachHangCustom;
import com.example.demo.dto.nhanvien.NhanVienCustom;
import com.example.demo.dto.nhanvien.NhanVienRequest;
import com.example.demo.entity.KhachHang;
import com.example.demo.entity.NhanVien;
import com.example.demo.repositories.NhanVienRepository;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository repository;

    @Override
    public List<NhanVien> getAllNhanVien() {
        return repository.findAll();
    }

    @Override
    public List<NhanVien> getAllByTrangThai(Integer trangThai, Integer page) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<NhanVien> pageResult = repository.findByTrangThai(trangThai, pageable);
        return pageResult.getContent();
    }

    @Override
    public NhanVien add(NhanVienRequest request) {
        NhanVien nv = request.map(new NhanVien());
        return repository.save(nv);
    }

    @Override
    public NhanVienCustom loginNhanVien(String email, String passWord) {
        Optional<NhanVienCustom> kh = repository.loginNhanVien(email, passWord);
        emailNhanVien = email;
        if (kh.isEmpty()) {
            return null;
        } else {
            NhanVienCustom khachHangCustom = kh.get();
            return khachHangCustom;
        }
    }

    @Override
    public NhanVien update(NhanVienRequest request, Integer id) {
        Optional<NhanVien> optional = Optional.of(repository.findById(id)).orElse(null);
        return optional.map(o -> {
            o.setMa(request.getMa());
            o.setTrangThai(request.getTrangThai());
            o.setChucVu(Integer.valueOf(request.getChucVu()));
            o.setEmail(request.getEmail());
            o.setMatKhau(request.getMatKhau());
            o.setDiaChi(request.getDiaChi());
            o.setNgaySinh(request.getNgaySinh());
            o.setGioiTinh(Boolean.valueOf(request.getGioiTinh()));
            o.setTen(request.getTen());
            o.setTenDem(request.getTenDem());
            o.setHo(request.getHo());
            o.setSoDienThoai(request.getSoDienThoai());
            return repository.save(o);
        }).orElse(null);
    }

    @Override
    public List<NhanVien> updateTrangThai(List<Integer> listID, Integer trangThai) {
        List<NhanVien> list = repository.findAllById(listID);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setTrangThai(trangThai);
        }
        return repository.saveAll(list);
    }

    @Override
    public NhanVien delete(Integer id) {
        Optional<NhanVien> optional = Optional.of(repository.findById(id)).orElse(null);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    @Override
    public NhanVien detail(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<NhanVien> searchDL(Integer trangThai, String sdt, int page) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<NhanVien> pageResult = repository.findByTrangThaiAndSoDienThoai(trangThai, sdt, pageable);
        List<NhanVien> searchResults = pageResult.getContent();
        return searchResults;
    }

    public String emailNhanVien = "";

    @Override
    public NhanVien loadNhanVien() {
        return repository.findByEmail(emailNhanVien);
    }

}
