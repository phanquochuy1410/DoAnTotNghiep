package com.example.demo.repositories;

import com.example.demo.dto.ctsp.ChiTietSanPhamCustom;
import com.example.demo.dto.cuaHang.CuaHangCustum;
import com.example.demo.dto.cuaHang.SearchThuocTinh;
import com.example.demo.dto.kichco.KichCoCustom;
import com.example.demo.dto.mausac.MauSacCustom;
import com.example.demo.entity.ChatLieu;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.KhuyenMai;
import com.example.demo.entity.KichCo;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Integer> {


    @Query("select ctsp.id as id , ctsp.idSanPham.ten as sanPham , ctsp.gia as gia , ctsp.anh as anh, " +
            "ctsp.anh2 as anh2, ctsp.anh3 as anh3, ctsp.anh4 as anh4, ctsp.anh5 as anh5,  " +
            "ctsp.giaKhuyenMai as giaKhuyenMai, ctsp.idHang.ten as hang, ctsp.idChatLieu.ten as chatLieu, " +
            "ctsp.idKichCo.ten as kichCo, ctsp.idMauSac.ten as mauSac, ctsp.idTheLoai.ten as theLoai, ctsp.soLuong as soLuong, " +
            " ctsp.mota as moTa, ctsp.trangThai as trangThai from ChiTietSanPham ctsp " +
            "where (ctsp.idMauSac.ten = :#{#x.mauSac} OR :#{#x.mauSac} IS NULL ) " +
            "and (ctsp.idKichCo.ten = :#{#x.tenKichCo} OR :#{#x.tenKichCo} IS NULL ) " +
            "and (ctsp.idTheLoai.ten = :#{#x.tenTheLoai} OR :#{#x.tenTheLoai} IS NULL ) " +
            "and (ctsp.idChatLieu.ten = :#{#x.tenChatLieu} OR :#{#x.tenChatLieu} IS NULL ) " +
            "and (ctsp.idHang.ten = :#{#x.tenHang} OR :#{#x.tenHang} IS NULL ) " +
            "and (ctsp.idSanPham.ten like %:#{#x.tenSanPham}% OR :#{#x.tenSanPham} IS NULL ) " +
            "and (ctsp.gia between :#{#x.giaBatDau} and :#{#x.giaKetThuc} OR :#{#x.giaBatDau} IS NULL OR :#{#x.giaKetThuc} IS NULL) " +
            "order by ctsp.update_at desc")
    List<ChiTietSanPhamCustom> ctspSearch(@Param("x") SearchThuocTinh x, Pageable pageable);

    @Query("select count(ctsp.id) as soLuong from ChiTietSanPham ctsp " +
            "where (ctsp.idMauSac.ten = :#{#x.mauSac} OR :#{#x.mauSac} IS NULL ) " +
            "and (ctsp.idKichCo.ten = :#{#x.tenKichCo} OR :#{#x.tenKichCo} IS NULL ) " +
            "and (ctsp.idTheLoai.ten = :#{#x.tenTheLoai} OR :#{#x.tenTheLoai} IS NULL ) " +
            "and (ctsp.idChatLieu.ten = :#{#x.tenChatLieu} OR :#{#x.tenChatLieu} IS NULL ) " +
            "and (ctsp.idHang.ten = :#{#x.tenHang} OR :#{#x.tenHang} IS NULL ) " +
            "and (ctsp.idSanPham.ten like %:#{#x.tenSanPham}% OR :#{#x.tenSanPham} IS NULL ) " +
            "and (ctsp.gia between :#{#x.giaBatDau} and :#{#x.giaKetThuc} OR :#{#x.giaBatDau} IS NULL OR :#{#x.giaKetThuc} IS NULL) ")
    Integer ctspSearchLength(@Param("x") SearchThuocTinh x);

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', kc.ten as N'KichCo', ms.ten as N'MauSac', \n" +
            "h.ten as N'Hang', tl.ten as N'TheLoai', cl.ten as N'ChatLieu', gia, so_luong as N'SoLuong', gia_khuyen_mai as N'GiaKhuyenMai', " +
            "mo_ta as N'MoTa', trang_thai as N'TrangThai', ctsp.anh as N'Anh', ctsp.anh2 as 'Anh2'," +
            "ctsp.anh3 as 'Anh3', ctsp.anh4 as 'Anh4', ctsp.anh5 as 'Anh5' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id\n" +
            "where (ctsp.ma like %:search% or sp.ten like %:search% or kc.ten like %:search% or \n" +
            "ms.ten like %:search% or tl.ten like %:search% or cl.ten like %:search% or h.ten like %:search%)\n" +
            " and ((gia between :min and :max)) order by ctsp.create_at desc", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchByGiaRequireNoTT( @Param ("search") String search,
                                                @Param("min") Integer min,
                                                @Param("max") Integer max,
                                                       Pageable pageable);

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', kc.ten as N'KichCo', ms.ten as N'MauSac', \n" +
            "h.ten as N'Hang', tl.ten as N'TheLoai', cl.ten as N'ChatLieu', gia, so_luong as N'SoLuong', gia_khuyen_mai as N'GiaKhuyenMai', " +
            "mo_ta as N'MoTa', trang_thai as N'TrangThai', ctsp.anh as N'Anh', ctsp.anh2 as 'Anh2'," +
            "ctsp.anh3 as 'Anh3', ctsp.anh4 as 'Anh4', ctsp.anh5 as 'Anh5' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id\n" +
            "where (ctsp.ma like %:search% or sp.ten like %:search% or kc.ten like %:search% or \n" +
            "ms.ten like %:search% or tl.ten like %:search% or cl.ten like %:search% or h.ten like %:search%)\n" +
            " and ((so_luong between :min and :max)) order by ctsp.create_at desc", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchBySoLuongRequireNoTT( @Param ("search") String search,
                                                    @Param("min") Integer min,
                                                    @Param("max") Integer max,
                                                           Pageable pageable);

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', kc.ten as N'KichCo', ms.ten as N'MauSac', \n" +
            "h.ten as N'Hang', tl.ten as N'TheLoai', cl.ten as N'ChatLieu', gia, so_luong as N'SoLuong', gia_khuyen_mai as N'GiaKhuyenMai', " +
            "mo_ta as N'MoTa', trang_thai as N'TrangThai', ctsp.anh as N'Anh', ctsp.anh2 as 'Anh2'," +
            "ctsp.anh3 as 'Anh3', ctsp.anh4 as 'Anh4', ctsp.anh5 as 'Anh5' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id\n" +
            "where (ctsp.ma like %:search% or sp.ten like %:search% or kc.ten like %:search% or \n" +
            "ms.ten like %:search% or tl.ten like %:search% or cl.ten like %:search% or h.ten like %:search%)\n" +
            " and ctsp.trang_thai = :tt order by ctsp.create_at desc", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchByRequireNoMinMax( @Param ("search") String search,
                                                        @Param("tt") Integer tt,
                                                        Pageable pageable);

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', kc.ten as N'KichCo', ms.ten as N'MauSac', \n" +
            "h.ten as N'Hang', tl.ten as N'TheLoai', cl.ten as N'ChatLieu', gia, so_luong as N'SoLuong', gia_khuyen_mai as N'GiaKhuyenMai', " +
            "mo_ta as N'MoTa', trang_thai as N'TrangThai', ctsp.anh as N'Anh', ctsp.anh2 as 'Anh2'," +
            "ctsp.anh3 as 'Anh3', ctsp.anh4 as 'Anh4', ctsp.anh5 as 'Anh5' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id\n" +
            "where (ctsp.ma like %:search% or sp.ten like %:search% or kc.ten like %:search% or \n" +
            "ms.ten like %:search% or tl.ten like %:search% or cl.ten like %:search% or h.ten like %:search%) \n" +
            "order by ctsp.create_at desc", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchByString( @Param ("search") String search,
                                               Pageable pageable);

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', kc.ten as N'KichCo', ms.ten as N'MauSac', \n" +
            "h.ten as N'Hang', tl.ten as N'TheLoai', cl.ten as N'ChatLieu', gia, so_luong as N'SoLuong', gia_khuyen_mai as N'GiaKhuyenMai', " +
            "mo_ta as N'MoTa', trang_thai as N'TrangThai', ctsp.anh as N'Anh', ctsp.anh2 as 'Anh2'," +
            "ctsp.anh3 as 'Anh3', ctsp.anh4 as 'Anh4', ctsp.anh5 as 'Anh5' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id\n" +
            "where (ctsp.ma like %:search% or sp.ten like %:search% or kc.ten like %:search% or \n" +
            "ms.ten like %:search% or tl.ten like %:search% or cl.ten like %:search% or h.ten like %:search%)\n" +
            "and ctsp.trang_thai = :tt and (so_luong between :min and :max) order by ctsp.create_at desc", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchAllBySoLuong( @Param ("search") String search,
                                                   @Param("tt") Integer tt,
                                                   @Param("min") Integer min,
                                                   @Param("max") Integer max,
                                                   Pageable pageable);

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', kc.ten as N'KichCo', ms.ten as N'MauSac', \n" +
            "h.ten as N'Hang', tl.ten as N'TheLoai', cl.ten as N'ChatLieu', gia, so_luong as N'SoLuong', gia_khuyen_mai as N'GiaKhuyenMai', " +
            "mo_ta as N'MoTa', trang_thai as N'TrangThai', ctsp.anh as N'Anh', ctsp.anh2 as 'Anh2'," +
            "ctsp.anh3 as 'Anh3', ctsp.anh4 as 'Anh4', ctsp.anh5 as 'Anh5' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id\n" +
            "where (ctsp.ma like %:search% or sp.ten like %:search% or kc.ten like %:search% or \n" +
            "ms.ten like %:search% or tl.ten like %:search% or cl.ten like %:search% or h.ten like %:search%)\n" +
            "and ctsp.trang_thai = :tt and ((gia between :min and :max)) order by ctsp.create_at desc", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchAllByGia( @Param ("search") String search,
                                               @Param("tt") Integer tt,
                                               @Param("min") Integer min,
                                               @Param("max") Integer max,
                                               Pageable pageable);

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', kc.ten as N'KichCo', ms.ten as N'MauSac', \n" +
            "h.ten as N'Hang', tl.ten as N'TheLoai', cl.ten as N'ChatLieu', gia, so_luong as N'SoLuong', gia_khuyen_mai as N'GiaKhuyenMai', " +
            "mo_ta as N'MoTa', trang_thai as N'TrangThai', ctsp.anh as N'Anh', ctsp.anh2 as 'Anh2'," +
            "ctsp.anh3 as 'Anh3', ctsp.anh4 as 'Anh4', ctsp.anh5 as 'Anh5' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id\n" +
            "where (ctsp.ma like %:search% or sp.ten like %:search% or kc.ten like %:search% or \n" +
            "ms.ten like %:search% or tl.ten like %:search% or cl.ten like %:search% or h.ten like %:search%)\n" +
            " and ((gia between :min and :max)) order by ctsp.create_at desc", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchByGiaRequireNoTTNoP( @Param ("search") String search,
                                                       @Param("min") Integer min,
                                                       @Param("max") Integer max);

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', kc.ten as N'KichCo', ms.ten as N'MauSac', \n" +
            "h.ten as N'Hang', tl.ten as N'TheLoai', cl.ten as N'ChatLieu', gia, so_luong as N'SoLuong', gia_khuyen_mai as N'GiaKhuyenMai', " +
            "mo_ta as N'MoTa', trang_thai as N'TrangThai', ctsp.anh as N'Anh', ctsp.anh2 as 'Anh2'," +
            "ctsp.anh3 as 'Anh3', ctsp.anh4 as 'Anh4', ctsp.anh5 as 'Anh5' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id\n" +
            "where (ctsp.ma like %:search% or sp.ten like %:search% or kc.ten like %:search% or \n" +
            "ms.ten like %:search% or tl.ten like %:search% or cl.ten like %:search% or h.ten like %:search%)\n" +
            " and ((so_luong between :min and :max)) order by ctsp.create_at desc", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchBySoLuongRequireNoTTNoP( @Param ("search") String search,
                                                           @Param("min") Integer min,
                                                           @Param("max") Integer ma);

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', kc.ten as N'KichCo', ms.ten as N'MauSac', \n" +
            "h.ten as N'Hang', tl.ten as N'TheLoai', cl.ten as N'ChatLieu', gia, so_luong as N'SoLuong', gia_khuyen_mai as N'GiaKhuyenMai', " +
            "mo_ta as N'MoTa', trang_thai as N'TrangThai', ctsp.anh as N'Anh', ctsp.anh2 as 'Anh2'," +
            "ctsp.anh3 as 'Anh3', ctsp.anh4 as 'Anh4', ctsp.anh5 as 'Anh5' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id\n" +
            "where (ctsp.ma like %:search% or sp.ten like %:search% or kc.ten like %:search% or \n" +
            "ms.ten like %:search% or tl.ten like %:search% or cl.ten like %:search% or h.ten like %:search%)\n" +
            " and ctsp.trang_thai = :tt order by ctsp.create_at desc", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchByRequireNoMinMaxNoP( @Param ("search") String search,
                                                        @Param("tt") Integer tt);

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', kc.ten as N'KichCo', ms.ten as N'MauSac', \n" +
            "h.ten as N'Hang', tl.ten as N'TheLoai', cl.ten as N'ChatLieu', gia, so_luong as N'SoLuong', gia_khuyen_mai as N'GiaKhuyenMai', " +
            "mo_ta as N'MoTa', trang_thai as N'TrangThai', ctsp.anh as N'Anh', ctsp.anh2 as 'Anh2'," +
            "ctsp.anh3 as 'Anh3', ctsp.anh4 as 'Anh4', ctsp.anh5 as 'Anh5' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id\n" +
            "where (ctsp.ma like %:search% or sp.ten like %:search% or kc.ten like %:search% or \n" +
            "ms.ten like %:search% or tl.ten like %:search% or cl.ten like %:search% or h.ten like %:search%) \n" +
            "order by ctsp.create_at desc", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchByStringNoP( @Param ("search") String search);

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', kc.ten as N'KichCo', ms.ten as N'MauSac', \n" +
            "h.ten as N'Hang', tl.ten as N'TheLoai', cl.ten as N'ChatLieu', gia, so_luong as N'SoLuong', gia_khuyen_mai as N'GiaKhuyenMai', " +
            "mo_ta as N'MoTa', trang_thai as N'TrangThai', ctsp.anh as N'Anh', ctsp.anh2 as 'Anh2'," +
            "ctsp.anh3 as 'Anh3', ctsp.anh4 as 'Anh4', ctsp.anh5 as 'Anh5' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id\n" +
            "where (ctsp.ma like %:search% or sp.ten like %:search% or kc.ten like %:search% or \n" +
            "ms.ten like %:search% or tl.ten like %:search% or cl.ten like %:search% or h.ten like %:search%)\n" +
            "and ctsp.trang_thai = :tt and (so_luong between :min and :max) order by ctsp.create_at desc", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchAllBySoLuongNoP( @Param ("search") String search,
                                                   @Param("tt") Integer tt,
                                                   @Param("min") Integer min,
                                                   @Param("max") Integer max);

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', kc.ten as N'KichCo', ms.ten as N'MauSac', \n" +
            "h.ten as N'Hang', tl.ten as N'TheLoai', cl.ten as N'ChatLieu', gia, so_luong as N'SoLuong', gia_khuyen_mai as N'GiaKhuyenMai', " +
            "mo_ta as N'MoTa', trang_thai as N'TrangThai', ctsp.anh as N'Anh', ctsp.anh2 as 'Anh2'," +
            "ctsp.anh3 as 'Anh3', ctsp.anh4 as 'Anh4', ctsp.anh5 as 'Anh5' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id\n" +
            "where (ctsp.ma like %:search% or sp.ten like %:search% or kc.ten like %:search% or \n" +
            "ms.ten like %:search% or tl.ten like %:search% or cl.ten like %:search% or h.ten like %:search%)\n" +
            "and ctsp.trang_thai = :tt and ((gia between :min and :max)) order by ctsp.create_at desc", nativeQuery = true)
    List<ChiTietSanPhamCustom> searchAllByGiaNoP( @Param ("search") String search,
                                               @Param("tt") Integer tt,
                                               @Param("min") Integer min,
                                               @Param("max") Integer max);

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', kc.ten as N'KichCo', ms.ten as N'MauSac', \n" +
            "h.ten as N'Hang', tl.ten as N'TheLoai', cl.ten as N'ChatLieu', gia, so_luong as N'SoLuong', gia_khuyen_mai as N'GiaKhuyenMai', " +
            "mo_ta as N'MoTa', ctsp.trang_thai as N'TrangThai', ctsp.anh as N'Anh', ctsp.anh2 as N'Anh2'," +
            "ctsp.anh3 as N'Anh3', ctsp.anh4 as N'Anh4', ctsp.anh5 as N'Anh5', ctsp.gia_khuyen_mai as N'GiaKhuyenMai' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id order by ctsp.create_at desc \n", nativeQuery = true)
    List<ChiTietSanPhamCustom> getAll(Pageable pageable);

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', kc.ten as N'KichCo', ms.ten as N'MauSac', \n" +
            "h.ten as N'Hang', tl.ten as N'TheLoai', cl.ten as N'ChatLieu', gia, so_luong as N'SoLuong', gia_khuyen_mai as N'GiaKhuyenMai', " +
            "mo_ta as N'MoTa', ctsp.trang_thai as N'TrangThai', ctsp.anh as N'Anh', ctsp.anh2 as 'Anh2'," +
            "ctsp.anh3 as 'Anh3', ctsp.anh4 as 'Anh4', ctsp.anh5 as 'Anh5', ctsp.gia_khuyen_mai as N'GiaKhuyenMai' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id\n", nativeQuery = true)
    List<ChiTietSanPhamCustom> getAllNoPage();

    @Query(value = "select ctsp.id, ctsp.gia, sp.ten as N'SanPham', ctsp.anh as N'Anh' , " +
            "ctsp.anh2 as N'Anh2' , ctsp.anh3 as N'Anh3', ctsp.anh4 as N'Anh4', ctsp.anh5 as N'Anh5', ctsp.gia_khuyen_mai as khuyenMai   from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n " +
            " where " +
            " ctsp.id in (select MAX(ctsp.id) as N'Id' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  where sp.ten in (select top 8  sp.ten as N'Sản Phẩm'  from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join hoa_don_chi_tiet hdct on hdct.id_chi_tiet_san_pham = ctsp.id\n" +
            "  group by sp.ten order by sum(hdct.so_luong) desc)\n" +
            "  group by sp.ten) group by ctsp.id, ctsp.gia, sp.ten, ctsp.anh, ctsp.anh2, ctsp.anh3, ctsp.anh4, ctsp.anh5, ctsp.gia_khuyen_mai", nativeQuery = true)
    List<ChiTietSanPhamCustom> getTop8(Pageable pageable);

    @Query(value = "select top 4 ct.id  as id , sp.ten as sanPham, ct.gia as gia , ct.anh as anh, " +
            "ct.anh2 as anh2, ct.anh3 as anh3, ct.anh4 as anh4, ct.anh5 as anh5 , ct.gia_khuyen_mai as khuyenMai from chi_tiet_san_pham ct \n" +
            "             join san_pham sp on ct.id_san_pham = sp.id \n" +
            "             join mau_sac ms on ms.id = ct.id_mau_sac \n" +
            "             join  kich_co kc on ct.id_kich_co = kc.id\n" +
            "             join  the_loai tl on ct.id_the_loai = tl.id\n" +
            "             join  chat_lieu cl on ct.id_chat_lieu = cl.id \n" +
            "             where ct.create_at >= DATEADD(DAY, -15, GETDATE()) \n" +
            "             and ct.id in (select max(ctsp.id) from chi_tiet_san_pham ctsp \n" +
            "             join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "            join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "             join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "             join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "            join  chat_lieu cl on ctsp.id_chat_lieu = cl.id \n" +
            "group by sp.ten ) order by ct.id desc", nativeQuery = true)
    List<ChiTietSanPhamCustom> getTop4();

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', ctsp.anh as N'Anh', gia as N'Gia', gia_khuyen_mai as N'GiaKhuyenMai'" +
            " from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n"
            , nativeQuery = true)
    List<ChiTietSanPhamCustom> getKhuyenMai(Pageable pageable);

    @Query(value = "select ctsp.id, ctsp.ma as N'Ma', sp.ten as N'SanPham', ctsp.anh as N'Anh', gia as N'Gia', gia_khuyen_mai as N'GiaKhuyenMai'" +
            " from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n"
            , nativeQuery = true)
    List<ChiTietSanPhamCustom> getAllKhuyenMai();

    @Query(value = "select distinct ms.ten as N'Ten', ms.id as N'Id' from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id\n" +
            "  where sp.ten = :tenSP ", nativeQuery = true)
    List<MauSacCustom> getMauSacCTSP(@Param("tenSP") String tsp);

    @Query(value = "select kc.ten, kc.id from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id\n" +
            "  where sp.ten = :tenSP and ms.ten = :ms ", nativeQuery = true)
    List<KichCoCustom> getSizeCTSP(@Param("tenSP") String tsp,
                                   @Param("ms") String ms);

    @Query(value = "  select ctsp.ma ,anh, anh2, anh3, anh4, anh5 from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id \n" +
            "  where ctsp.ma = (select min(ctsp.ma) from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  where sp.ten = :tenSP and ms.ten = :ms )", nativeQuery = true)
    ChiTietSanPhamCustom getAllAnh(@Param("tenSP") String tsp,
                                   @Param("ms") String ms);

    @Query(value = "  select ctsp.id as id , ctsp.ma as 'Ma', ctsp.so_luong as 'SoLuong', ctsp.gia as 'Gia', ctsp.gia_khuyen_mai as N'GiaKhuyenMai', ctsp.trang_thai as N'TrangThai'  from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id\n" +
            "  join  hang h on ctsp.id_hang = h.id \n" +
            "  where ctsp.ma = (select min(ctsp.ma) from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  where sp.ten = :tenSP and ms.id = :ms and kc.id = :kc )", nativeQuery = true)
    ChiTietSanPhamCustom getChangeDetail(@Param("tenSP") String tsp,
                                         @Param("ms") Integer ms,
                                         @Param("kc") Integer kc);

    ChiTietSanPham findChiTietSanPhamByIdSanPhamAndIdMauSacAndIdKichCo(
            SanPham idSp,
            MauSac idMs,
            KichCo idKc);
    List<ChiTietSanPham> findChiTietSanPhamByIdSanPham(SanPham idSp);

    List<ChiTietSanPham> findAllByIdSanPhamIdKhuyenMai( KhuyenMai idKhuyenMai );

    @Query(value = "select ctsp.id as id,ctsp.giaKhuyenMai as khuyenMai  , ctsp.idSanPham.ten as ten , ctsp.idKichCo.ten as kichCo , ctsp.idMauSac.ten as mauSac , ctsp.gia as gia, ctsp.soLuong as soLuong from ChiTietSanPham ctsp inner join SanPham sp" +
            "  on sp.id = ctsp.idSanPham.id " +
            "where ctsp.idSanPham.ten like %:ten% ")
    List<ChiTietSanPhamCustom> searchSP(@Param("ten") String ten);

    @Query("select ctsp.id as id , ctsp.idSanPham.ten as tenSanPham , ctsp.gia as gia , ctsp.giaKhuyenMai as giaKM , ctsp.anh as tenAnh , sp.idKhuyenMai.chietKhau as chietKhau ," +
            " ctsp.anh2 as tenAnh2 , ctsp.anh3 as tenAnh3 , ctsp.anh4 as tenAnh4 , ctsp.anh5 as tenAnh5 from ChiTietSanPham ctsp " +
            " join SanPham  sp on sp.id = ctsp.idSanPham.id " +
            "where ctsp.idSanPham.idKhuyenMai is not null " +
            "and ctsp.id in (select MAX (ct.id) from ChiTietSanPham ct " +
            "join SanPham sp on sp.id = ct.idSanPham.id " +
            "join KichCo kc on kc.id = ct.idKichCo.id " +
            "join MauSac ms on ms.id = ct.idMauSac.id " +
            "join TheLoai tl on tl.id = ct.idTheLoai.id " +
            "join ChatLieu cl on cl.id = ct.idChatLieu.id " +
            "group by ct.idSanPham.ten)")
    List<CuaHangCustum> hienThiSanPhamKhuyenMai(Pageable pageable);

}
