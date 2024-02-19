package com.example.demo.service;

import com.example.demo.dto.ctsp.ChiTietSanPhamCustom;
import com.example.demo.dto.ctsp.ChiTietSanPhamImport;
import com.example.demo.dto.ctsp.ChiTietSanPhamRequest;
import com.example.demo.dto.cuaHang.CuaHangCustum;
import com.example.demo.dto.cuaHang.SearchThuocTinh;
import com.example.demo.dto.kichco.KichCoCustom;
import com.example.demo.dto.mausac.MauSacCustom;
import com.example.demo.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Optional;

public interface ChiTietSanPhamService {

    public List<ChiTietSanPham> getAll();

    public List<ChiTietSanPhamCustom> getAllNoPage();

    public List<ChiTietSanPhamCustom> getTop8(Integer page);

    public List<ChiTietSanPhamCustom> getTop4New();

    public List<ChiTietSanPhamCustom> getKhuyenMai(Integer page);

    public List<ChiTietSanPhamCustom> getAllKhuyenMai();

    public List<ChiTietSanPhamCustom> findByAll( String str, Integer page, Integer min, Integer max, Integer tt,String by );

    public List<ChiTietSanPhamCustom> findByAllLength( String str, Integer min, Integer max, Integer tt,String by );

    public List<ChiTietSanPhamCustom> getAllPageble(Integer page);

    public Optional<ChiTietSanPham>    getById( Integer id);

    public ChiTietSanPham getSLTon(String tenSp, String idKc, String idMs);

    public ChiTietSanPham add( ChiTietSanPhamRequest request );

    public List<ChiTietSanPham> addAll( List<ChiTietSanPhamImport> listRequest );

    public List<ChiTietSanPham> addManyVariable( List<ChiTietSanPhamRequest> listRequest );

    public ChiTietSanPham update( ChiTietSanPhamRequest request, Integer id );

    public ChiTietSanPham updateTrangThai(Integer id, Integer tt);

    public ChiTietSanPham delete(Integer ma);

    public List<MauSacCustom> getMauSacCTSP( String tsp);

    public List<KichCoCustom> getKichCoCTSP( String tsp, String tms);

    List<Integer> getSoLuongTon(List<Integer> listId);

    List<ChiTietSanPhamCustom> searchListSP(String ten);

    List<CuaHangCustum> hienThiSanPhamKhuyenMai(Integer page);

    ChiTietSanPhamCustom getAllAnh(String tenSP, String ms);

    ChiTietSanPhamCustom getChangeDetail(String tenSP, Integer ms, Integer kc);

    List<ChiTietSanPhamCustom> ctspSearch( SearchThuocTinh x, Integer page );

    Integer ctspSearchLength( SearchThuocTinh x);



}
