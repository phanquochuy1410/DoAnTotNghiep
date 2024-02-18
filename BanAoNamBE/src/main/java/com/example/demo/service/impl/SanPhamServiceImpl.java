package com.example.demo.service.impl;

import com.example.demo.dto.khuyenmai.KhuyenMaiCustom;
import com.example.demo.dto.sanpham.SanPhamCustom;
import com.example.demo.dto.sanpham.SanPhamRequest;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.KhuyenMai;
import com.example.demo.entity.SanPham;
import com.example.demo.repositories.ChiTietSanPhamRepository;
import com.example.demo.repositories.KhuyenMaiRepository;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;



@Service
public class SanPhamServiceImpl implements SanPhamService {

    final Integer DESC = 0;

    @Autowired
    private SanPhamRepository repository;

    @Autowired
    private KhuyenMaiRepository repositoryKM;

    @Autowired
    private ChiTietSanPhamRepository repoCTSP;

    @Override
    public Page<SanPham> getAll ( int page ) {
        Pageable pageable = PageRequest.of(page , 10);

        return repository.findAll(pageable);
    }

    @Override
    public Integer getChietKhau ( Integer idSp ) {
        return repository.getChietKhau(idSp);
    }

    @Override
    public SanPham detail ( Integer id ) {
        return repository.findById(id).orElse(null);
    }


    @Override
    public List<SanPham> getCbb () {
        return repository.findAll();
    }

    @Override
    public List<KhuyenMaiCustom> getCbbKM () {
        return repositoryKM.viewCbbKm();
    }


    public List<SanPham> getElementRequired () {
        return repository.findAll();
    }

    @Override
    public SanPham getById (Integer id) {
        Optional<SanPham> optional = Optional.of(repository.findById(id)).orElse(null);
        return optional.get();
    }

    @Override
    public SanPham getByTen ( String ten ) {
        return repository.getSanPhamByTen(ten);
    }

    private String genMa () {
        String maCV = " ";
        String s1 = "SP";

        for (int i = 1; i < getElementRequired().size() + 2; i++) {
            maCV = s1 + i;
        }
        return maCV;
    }


    @Override
    public SanPham add ( SanPhamRequest request ) {
        if(repository.findAll().stream().anyMatch(sp -> sp.getTen().equalsIgnoreCase(request.getTen()))){
            return null;
        }
        SanPham sanPham = request.map(new SanPham());
        sanPham.setMa(genMa());
        sanPham.setCreate_at(new Date());
        sanPham.setUpdate_at(new Date());
        return repository.save(sanPham);
    }

    @Override
    public SanPham update ( SanPhamRequest request , Integer id ) {
        Optional<SanPham> optional = Optional.ofNullable(repository.findById(id).orElse(null));
        return optional.map(o -> {
            o.setMa(request.getMa());
            o.setTen(request.getTen());
            return repository.save(o);
        }).orElse(null);
    }

    @Override
    public SanPham delete ( Integer id ) {
        Optional<SanPham> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    @Override
    public Boolean updateKM ( List<Integer> listKM , Integer idKM ) {
        Boolean bl = false;
        try {
            List<SanPham> sanPhams = repository.findAllById(listKM);

            for (int i = 0; i < sanPhams.size(); i++) {
                if(idKM == 0){
                    sanPhams.get(i).setIdKhuyenMai(null);
                    sanPhams.get(i).setUpdate_at(new Date());
                    List<ChiTietSanPham> listCt = repoCTSP.findChiTietSanPhamByIdSanPham(sanPhams.get(i));
                    for (ChiTietSanPham ctsp : listCt) {
                        ctsp.setGiaKhuyenMai(BigDecimal.ZERO);
                        ctsp.setUpdate_at(LocalDateTime.now());
                    }
                    repoCTSP.saveAll(listCt);
                }else{
                    sanPhams.get(i).setIdKhuyenMai(KhuyenMai.builder().id(idKM).build());
                    List<ChiTietSanPham> listCt = repoCTSP.findChiTietSanPhamByIdSanPham(sanPhams.get(i));
                    for (ChiTietSanPham ctsp : listCt) {
                        KhuyenMai km = repositoryKM.findById(idKM).get();
                        BigDecimal tienChietKhau = ctsp.getGia().multiply(BigDecimal.valueOf(0.01))
                                .multiply(new BigDecimal(km.getChietKhau()));
                        ctsp.setGiaKhuyenMai(tienChietKhau);
                        ctsp.setUpdate_at(LocalDateTime.now());
                    }
                    repoCTSP.saveAll(listCt);
                }
                repository.saveAll(sanPhams);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SanPhamCustom> findByDG ( Integer page , String name , String by, Integer order ) {
        Pageable pageable = PageRequest.of(page , 10);
        List<SanPhamCustom> list;
        if(name == null || name.equals("")){
            if(by.equalsIgnoreCase("review")){
                if(order == DESC){
                    list = repository.getDGByCountDesc(pageable);
                }else{
                    list =  repository.getDGByCountAsc(pageable);
                }
            }else{
                if(order == DESC){
                    list = repository.getDGBySumDesc(pageable);
                }else{
                    list = repository.getDGBySumAsc(pageable);
                }
            }
        }else{
            list = repository.getDGByTen(pageable, name);
        }
        return list;
    }

    @Override
    public List<SanPhamCustom> findByDGLength ( String name , String by , Integer order ) {
        List<SanPhamCustom> list;
        if(name == null || name.equals("")){
            if(by.equalsIgnoreCase("review")){
                if(order == DESC){
                    list = repository.getDGByCountDescLength();
                }else{
                    list =  repository.getDGByCountAscLength();
                }
            }else{
                if(order == DESC){
                    list = repository.getDGBySumDescLength();
                }else{
                    list = repository.getDGBySumAscLength();
                }
            }
        }else{
            list = repository.getDGByTenLength(name);
        }
        return list;
    }

    @Override
    public List<SanPham> searchTen(String ten) {
        return repository.searchTen(ten);
    }

}
