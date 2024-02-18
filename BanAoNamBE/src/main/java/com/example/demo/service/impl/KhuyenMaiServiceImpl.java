package com.example.demo.service.impl;

import com.example.demo.dto.khuyenmai.KhuyenMaiCustom;
import com.example.demo.dto.khuyenmai.KhuyenMaiRequest;
import com.example.demo.dto.khuyenmai.SearchKhuyenMai;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.KhuyenMai;
import com.example.demo.entity.SanPham;
import com.example.demo.repositories.ChiTietSanPhamRepository;
import com.example.demo.repositories.KhuyenMaiRepository;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.service.KhuyenMaiService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class KhuyenMaiServiceImpl implements KhuyenMaiService {

    public final Integer SBD = 1;
    public final Integer DHD = 0;
    public final Integer HH = 2;

    @Autowired
    private KhuyenMaiRepository repository;

    @Autowired
    private SanPhamRepository repoSp;

    @Autowired
    private ChiTietSanPhamRepository repoCTSP;

    @Override
    public Page<KhuyenMai> getAll ( int page ) {
        Pageable pageable = PageRequest.of(page , 15);
        return repository.findAll(pageable);
    }

    @Override
    public List<KhuyenMai> getElementRequired () {
        return repository.findAll();
    }
    private String genMa(){
        String maCV = " ";
        String s1 = "KM";

        for (int i = 1; i < getElementRequired().size() +2; i++) {
            maCV = s1 +i;
        }
        return maCV;
    }

    @Override
    public KhuyenMai add ( KhuyenMaiRequest request ) {
        KhuyenMai khuyenMai = request.map(new KhuyenMai());
        khuyenMai.setMa(genMa());
        return repository.save(khuyenMai);    }

    @Override
    public KhuyenMai update ( KhuyenMaiRequest request , Integer id ) {
        Optional<KhuyenMai> optional = Optional.ofNullable(repository.findById(id).orElse(null));
        List<ChiTietSanPham> getAllKm = repoCTSP.findAllByIdSanPhamIdKhuyenMai(optional.get());
        return optional.map(o -> {
            o.setMa(request.getMa());
            o.setTen(request.getTen());
            o.setNgayBatDau(request.getNgayBatDau());
            o.setNgayKetThuc(request.getNgayKetThuc());
            o.setChietKhau(request.getChietKhau());
            for (ChiTietSanPham ctsp : getAllKm) {
                BigDecimal tienChietKhau = ctsp.getGia().multiply(BigDecimal.valueOf(0.01))
                        .multiply(new BigDecimal(request.getChietKhau()));
                ctsp.setGiaKhuyenMai(tienChietKhau);
                ctsp.setUpdate_at(LocalDateTime.now());
            }
            repoCTSP.saveAll(getAllKm);
            if(request.getNgayBatDau().isAfter(LocalDate.now())){
                o.setTrangThai(SBD);
            }
            if(request.getNgayKetThuc().isAfter(LocalDate.now()) && request.getNgayBatDau().isBefore(LocalDate.now())){
                o.setTrangThai(DHD);
            }
            return repository.save(o);
        }).orElse(null);    }

    @Override
    public KhuyenMai delete ( Integer id ) {
        Optional<KhuyenMai> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    @Override
    public List<KhuyenMai> updateTrangThai () {
        List<Integer> listSpIdHH = repoSp.getIdSpHH();
        listSpIdHH.stream().forEach(
            id -> {
                SanPham sp = repoSp.findById(id).orElse(null);
                List<ChiTietSanPham> list = repoCTSP.findChiTietSanPhamByIdSanPham(sp);
                list.stream().forEach(ctsp -> {
                    ctsp.setGiaKhuyenMai(BigDecimal.ZERO);
                    ctsp.setUpdate_at(LocalDateTime.now());
                });
                repoCTSP.saveAll(list);
                sp.setIdKhuyenMai(null);
                repoSp.save(sp);
            }
        );

        List<KhuyenMai> listKm = repository.findAllByNgayKetThucIsBefore(LocalDate.now());
        listKm.stream().forEach(km -> {
            km.setTrangThai(HH);
            km.setUpdate_at(LocalDateTime.now());
        });

        List<KhuyenMai> listDHD = repository.findMiddle();
        listDHD.stream().forEach(km ->{
            km.setTrangThai(DHD);
            km.setUpdate_at(LocalDateTime.now());
        });

        return repository.saveAll(listKm);
    }

    @Override
    public List<KhuyenMaiCustom> timKiem(SearchKhuyenMai searchKhuyenMai) {
        return repository.timKiem(searchKhuyenMai);
    }

}
