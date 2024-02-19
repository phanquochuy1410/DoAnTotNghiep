package com.example.demo.service.impl;

import com.example.demo.dto.gh.GioHangChiTietCustom;
import com.example.demo.dto.gh.GioHangChiTietRequest;
import com.example.demo.dto.khachhang.KhachHangCheckOutCustom;
import com.example.demo.dto.khachhang.KhachHangCustom;
import com.example.demo.dto.khachhang.KhachHangRequest;
import com.example.demo.entity.DiaChi;
import com.example.demo.entity.KhachHang;
import com.example.demo.repositories.KhachHangRepository;
import com.example.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository repository;

    @Override
    public List<KhachHang> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        return repository.findAll(pageable).toList();
    }

    @Override
    public KhachHangCustom loGinKhachHang(String email, String passWord) {
        Optional<KhachHangCustom> kh = repository.loginKhachHang(email, passWord);
        emailKhachHang = email;
        if (kh.isEmpty()) {
            return null;
        } else {
            KhachHangCustom khachHangCustom = kh.get();
            return khachHangCustom;
        }
    }

    @Override
    public KhachHang add(KhachHangRequest request) {
        KhachHang kh = request.map(new KhachHang());
        return repository.save(kh);
    }

    @Override
    public KhachHang save(KhachHang khachHang) {
        return repository.save(khachHang);
    }

    private String genMaKH() {
        KhachHangCustom khachHangs = repository.findLast();
        int i = 0;
        while (i < khachHangs.getIdKhachHang()) {
            i++;
            if (khachHangs.getMaKhachHang().equalsIgnoreCase("KH" + i)) {
                int a = i + 1;
                System.out.println(a);
                return "KH" + a;
            }
        }
        return null;
    }

    @Override
    public KhachHang loadKhachHang() {
        return repository.findByEmail(emailKhachHang).orElse(null);
    }

    @Override
    public KhachHangCheckOutCustom loadKH() {
        return repository.loadKHCheckOut(emailKhachHang).orElse(null);
    }

    public String emailKhachHang = "";

    @Override
    public List<GioHangChiTietCustom> getSoLuongSanPham() {
        return repository.getSoLuongSanPham(emailKhachHang);
    }

    @Override
    public KhachHang addRegis(KhachHangRequest request) {
        KhachHang kh = request.mapRegis(new KhachHang());
        Optional<KhachHang> optional = Optional.of(repository.findByEmail(kh.getEmail())).orElse(null);
        if (optional.isPresent()) {
            return null;
        } else {
            String maMoi = genMaKH();
            kh.setMa(maMoi);
            LocalDate localDate = LocalDate.now();
            kh.setCreate_at(localDate);
            return repository.save(kh);
        }
    }

    @Override
    public KhachHang update(KhachHangRequest request, Integer id) {
        Optional<KhachHang> optional = Optional.of(repository.findById(id)).orElse(null);
        return optional.map(o -> {
            o.setMa(request.getMa());
            o.setHo(request.getHo());
            o.setTenDem(request.getTenDem());
            o.setTen(request.getTen());
            o.setSoDienThoai(request.getSoDienThoai());
            if(Integer.parseInt(request.getGioiTinh()) == 0){
                o.setGioiTinh(false);
            }else{
                o.setGioiTinh(true);
            }
            o.setEmail(request.getEmail());
            o.setMatKhau(request.getMatKhau());
            o.setTrangThai(request.getTrangThai());
            o.setUpdate_at(LocalDate.now());
            return repository.save(o);
        }).orElse(null);
    }

    @Override
    public KhachHang detail(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public KhachHang delete(Integer id) {
        Optional<KhachHang> optional = Optional.of(repository.findById(id)).orElse(null);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    @Override
    public List<KhachHang> search(String sdt) {
        List<KhachHang> listSearch = repository.findBySoDienThoai(sdt);
        return listSearch;
    }
}
