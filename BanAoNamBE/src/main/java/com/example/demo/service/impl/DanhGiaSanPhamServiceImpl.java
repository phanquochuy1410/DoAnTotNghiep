package com.example.demo.service.impl;

import com.example.demo.dto.dgsp.DanhGiaSanPhamCustom;
import com.example.demo.dto.dgsp.DanhGiaSanPhamRequest;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.DanhGiaSanPham;
import com.example.demo.entity.KhachHang;
import com.example.demo.entity.SanPham;
import com.example.demo.repositories.DanhGiaSanPhamRepository;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.service.DanhGiaSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DanhGiaSanPhamServiceImpl implements DanhGiaSanPhamService {

    final String AN = "an";
    final String HT = "hienthi";
    final String XL = "xuly";
    final String SP = "sanpham";

    final Integer NOR = 0;
    final Integer DESC = 1;
    final Integer ASC = 2;

    @Autowired
    private DanhGiaSanPhamRepository repo;

    @Autowired
    private SanPhamRepository repoSp;

    @Override
    public List<DanhGiaSanPhamCustom> getElementRequired () {
        return null;
    }

    @Override
    public List<DanhGiaSanPham> getAllPageble ( Integer page ) {
        Pageable pageable = PageRequest.of(page, 5);
        return repo.findAll(pageable).toList();
    }

    @Override
    public List<DanhGiaSanPhamCustom> getAllHienThi ( Integer page ) {
        Pageable pageable = PageRequest.of(page, 5);
        return repo.getAllHienThi(pageable);
    }

    @Override
    public List<DanhGiaSanPhamCustom> getHienThiSp ( Integer page, String tenSp ) {
        Pageable pageable = PageRequest.of(page, 5);
        return repo.getHienThiSanPham(pageable, tenSp);
    }

    @Override
    public List<DanhGiaSanPhamCustom> getHienThiSpLength (String tenSp ) {
        return repo.getHienThiSanPhamLength(tenSp);
    }

    @Override
    public List<DanhGiaSanPhamCustom> getAllXuLy ( Integer page ) {
        Pageable pageable = PageRequest.of(page, 5);
        return repo.getAllXuLy(pageable);
    }

    @Override
    public List<DanhGiaSanPhamCustom> getAllAn ( Integer page ) {
        Pageable pageable = PageRequest.of(page, 5);
        return repo.getAllAn(pageable);
    }

    @Override
    public List<DanhGiaSanPhamCustom> getLength ( String by ) {
        if(by.equalsIgnoreCase(HT)){
            return repo.getLengthHienThi();
        }
        if(by.equalsIgnoreCase(AN)){
            return repo.getLengthAn();
        }
        if(by.equalsIgnoreCase(XL)){
            return repo.getLengthXuLy();
        }
        return null;
    }

    @Override
    public Boolean updateTrangThai ( List<Integer> listId , Integer trangThai ) {
        Boolean bl = false;
        try {
            List<DanhGiaSanPham> dgsp = repo.findAllById(listId);
            for (int i = 0; i < dgsp.size(); i++) {
                dgsp.get(i).setTrangThai(trangThai);
            }
            repo.saveAll(dgsp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public DanhGiaSanPham add ( DanhGiaSanPhamRequest request, String tenSp) {
        SanPham getIdSp = repoSp.getSanPhamByTen(tenSp);
        Integer idSp = getIdSp.getId();
        Optional<Integer> itExits = repo.getDgspDaComment(Integer.parseInt(request.getIdKhachHang()), idSp);
        if(itExits.orElse(null) != null ){
            DanhGiaSanPham danhGiaSanPham = repo.findById(itExits.get()).get();
            danhGiaSanPham.setNgaySua(LocalDate.now());
            danhGiaSanPham.setSoSao(Integer.parseInt(request.getSoSao()));
            danhGiaSanPham.setNoiDung(request.getNoiDung());
            danhGiaSanPham.setTrangThai(0);
            danhGiaSanPham.setNgaySua(LocalDate.now());
            return  repo.save(danhGiaSanPham);
        }else{
            SanPham sanPham = repoSp.getSanPhamByTen(tenSp);
            DanhGiaSanPham danhGiaSanPham = request.map(new DanhGiaSanPham());
            danhGiaSanPham.setNgayTao(LocalDate.now());
            danhGiaSanPham.setIdSanPham(sanPham);
            return repo.save(danhGiaSanPham);
        }
    }

    @Override
    public DanhGiaSanPham update ( DanhGiaSanPhamRequest request, Integer id) {
        Optional<DanhGiaSanPham> optional = Optional.of(repo.findById(id)).orElse(null);
        return optional.map(o -> {
            o.setNoiDung(request.getNoiDung());
            o.setNgaySua(LocalDate.parse(request.getNgaySua()));
            o.setSoSao(Integer.parseInt(request.getSoSao()));
            o.setIdSanPham(SanPham.builder().id(Integer.parseInt(request.getIdSanPham())).build());
            o.setIdKhachHang(KhachHang.builder().id(Integer.parseInt(request.getIdKhachHang())).build());
            return repo.save(o);
        }).orElse(null);
    }

    @Override
    public DanhGiaSanPham delete ( Integer id ) {
        Optional<DanhGiaSanPham> optional = Optional.of(repo.findById(id)).orElse(null);
        return optional.map(o->{
            repo.delete(o);
            return o;
        }).orElse(null);
    }

    @Override
    public List<DanhGiaSanPhamCustom> getFromSp ( Integer by, Integer id ) {
        if(by == NOR){
            return repo.getFromSpNgayTaoAsc(id);
        }
        else if(by == DESC){
            return repo.getFromSpDesc(id);
        }
        else{
            return repo.getFromSpAsc(id);
        }
    }

    @Override
    public List<Integer> getIdChuaMua ( Integer id ) {
        return repo.getIdChuaMua(id);
    }

    @Override
    public Boolean isExits ( Integer idKh , String tenSp ) {
        SanPham getIdSp = repoSp.getSanPhamByTen(tenSp);
        Integer idSp = getIdSp.getId();
        Optional<Integer> itExits = repo.getDgspDaComment(idKh, idSp);
        if(itExits.orElse(null) != null ){
            return true;
        }
        return false;
    }


}
