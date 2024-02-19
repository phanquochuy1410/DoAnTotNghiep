package com.example.demo.repositories;

import com.example.demo.dto.ctsp.ChiTietSanPhamCustom;
import com.example.demo.dto.cuaHang.CuaHangCustum;
import com.example.demo.dto.cuaHang.SearchThuocTinh;
import com.example.demo.dto.hoadon.HoaDonCustom;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuaHangTaiQuayRepository extends JpaRepository<ChiTietSanPham , Integer> {


    @Query("select ctsp.id as id , sp.ten as sanPham , kc.ten as kichCo , ms.ten as mauSac , ctsp.gia as gia , ctsp.giaKhuyenMai as giaKhuyenMai , ctsp.anh as anh , " +
            "ctsp.anh2 as anh2 , ctsp.anh3 as anh3 , ctsp.anh4 as anh4 , ctsp.anh5 as anh5 , ctsp.soLuong as soLuong from ChiTietSanPham ctsp " +
            "join SanPham sp on sp.id = ctsp.idSanPham.id " +
            "join KichCo kc on kc.id = ctsp.idKichCo.id " +
            "join MauSac ms on ms.id = ctsp.idMauSac.id " +
            "join TheLoai tl on tl.id = ctsp.idTheLoai.id " +
            "join ChatLieu cl on cl.id = ctsp.idChatLieu.id " +
            "where (ctsp.idSanPham.ten like %:#{#x.tenSanPham}% OR :#{#x.tenSanPham} IS NULL ) " +
            "and (ctsp.id = :#{#x.id} OR :#{#x.id} IS NULL ) " +
            "and (ctsp.idMauSac.ten = :#{#x.mauSac} OR :#{#x.mauSac} IS NULL ) " +
            "and (ctsp.idKichCo.ten = :#{#x.tenKichCo} OR :#{#x.tenKichCo} IS NULL ) " +
            "order by ctsp.id desc")
    List<ChiTietSanPhamCustom> hienThiSanPham(@Param("x") SearchThuocTinh x, Pageable pageable);

    @Query("SELECT ctsp.soLuong FROM ChiTietSanPham ctsp WHERE ctsp.id = :id")
    Integer getSoLuong(@Param("id") Integer id);

    @Query("SELECT  hd.id as idHoaDon, hd.ma as maHoaDon , hd.trangThai as trangThai , hd.gia as tongTien , hd.idKhachHang.ten as tenNguoiNhan , hd.idNhanVien.ten as nguoiXacNhan , hd.ngayTao from HoaDon hd " +
            "WHERE hd.trangThai = 7 ")
    List<HoaDonCustom> findMaHoaDon();

    @Query("SELECT count(hd.id) as soLuongHoaDon from HoaDon hd " +
            "WHERE hd.trangThai = 7 ")
    Integer soLuong();

}
