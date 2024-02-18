package com.example.demo.service.impl;

import com.example.demo.dto.diachi.DiaChiCustom;
import com.example.demo.dto.diachi.DiaChiRequest;
import com.example.demo.entity.DiaChi;
import com.example.demo.entity.KhachHang;
import com.example.demo.repositories.DiaChiRepository;
import com.example.demo.service.DiaChiService;
import com.example.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DiaChiServiceImpl implements DiaChiService {

    @Autowired
    private DiaChiRepository repo;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private KhachHangServiceImpl repoKH;

    @Override
    public DiaChi save(DiaChiRequest request) {
        DiaChi newDiaChi = DiaChi.builder()
                .create_at(LocalDateTime.now())
                .diaChiCuThe(request.getDcct())
                .ma(genMaDC())
                .huyen(request.getQuan())
                .thanhPho(request.getThanhPho())
                .xa(request.getPhuong())
                .build();
        repo.save(newDiaChi);
        getKhach(newDiaChi);
        return newDiaChi;
    }

    private KhachHang getKhach(DiaChi diaChi) {
        Optional<KhachHang> khachHang = Optional.of(khachHangService.loadKhachHang());
        return khachHang.map(o -> {
            o.setMatKhau(khachHang.get().getMatKhau());
            o.setMa(khachHang.get().getMa());
            o.setEmail(khachHang.get().getEmail());
            o.setIdDiaChi(diaChi);
            o.setGioiTinh(khachHang.get().getGioiTinh());
            o.setTen(khachHang.get().getTen());
            o.setTenDem(khachHang.get().getTenDem());
            o.setHo(khachHang.get().getHo());
            o.setTrangThai(khachHang.get().getTrangThai());
            o.setId(khachHang.get().getId());
            o.setCreate_at(khachHang.get().getCreate_at());
            o.setUpdate_at(khachHang.get().getUpdate_at());
            o.setCreate_by(khachHang.get().getCreate_by());
            o.setUpdate_by(khachHang.get().getUpdate_by());
            return khachHangService.save(o);
        }).orElse(null);
    }

    @Override
    public DiaChi update(DiaChiRequest request) {
        Optional<DiaChi> diaChi = Optional.of(repo.findById(request.getId()).orElse(null));
        return diaChi.map(o -> {
            o.setId(request.getId());
            o.setDiaChiCuThe(request.getDcct());
            o.setThanhPho(request.getThanhPho());
            o.setUpdate_at(LocalDateTime.now());
            o.setHuyen(request.getQuan());
            o.setXa(request.getPhuong());
            return repo.save(o);
        }).orElse(null);
    }

    private String genMaDC() {
        DiaChiCustom khachHangs = repo.findLast();
        int i = 0;
        while (i < khachHangs.getId()) {
            i++;
            if (khachHangs.getMa().equalsIgnoreCase("DC" + i)) {
                int a = i + 1;
                System.out.println(a);
                return "DC" + a;
            }
        }
        return null;
    }

    @Override
    public DiaChiCustom getListDC() {
        return repo.getListDC(repoKH.emailKhachHang);
    }
}
