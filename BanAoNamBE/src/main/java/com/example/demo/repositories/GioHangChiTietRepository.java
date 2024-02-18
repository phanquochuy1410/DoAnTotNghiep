package com.example.demo.repositories;

import com.example.demo.dto.ctsp.ChiTietSanPhamCustom;
import com.example.demo.dto.gh.GioHangChiTietCustom;
import com.example.demo.dto.gh.GioHangChiTietRequest;
import com.example.demo.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Integer> {
    @Query(value = "select ghct.idChiTietSanPham.trangThai as trangThaiSanPham, ghct.idChiTietSanPham.id as idCTSP ,ghct.idChiTietSanPham.soLuong as soLuongTon ,ghct.idChiTietSanPham.giaKhuyenMai as khuyenMai , ghct.idGioHang.id as idGH , (ghct.idChiTietSanPham.gia * ghct.soLuong - ghct.soLuong * ghct.idChiTietSanPham.giaKhuyenMai) as thanhTien, ghct.id as id ,ghct.idChiTietSanPham.idSanPham.ten as ten , ghct.soLuong as soLuong , ghct.idChiTietSanPham.idHang.ten as hang , ghct.idChiTietSanPham.idMauSac.ten as mau , ghct.idChiTietSanPham.idKichCo.ten as size , ghct.idChiTietSanPham.idChatLieu.ten as chatLieu , " +
            "ghct.idChiTietSanPham.anh2 as anh2, ghct.idChiTietSanPham.anh3 as anh3, ghct.idChiTietSanPham.anh4 as anh4 , ghct.idChiTietSanPham.anh5 as anh5 , ghct.idChiTietSanPham.anh as anh , ghct.idChiTietSanPham.gia as gia , ghct.idChiTietSanPham.idSanPham.ma as maSanPham , ghct.idChiTietSanPham.mota as moTa from GioHangChiTiet ghct" +
            " where ghct.idGioHang.idKhachHang.email like ?1 and ghct.trangThai = 1 ")
    List<GioHangChiTietCustom> getPageGH(String email);

    @Query(value = "select ghct.idChiTietSanPham.id as idCTSP , ghct.trangThai as trangThai , ghct.idGioHang.id as idGH , ghct.gia as thanhTien, ghct.id as id ,ghct.idChiTietSanPham.idSanPham.ten as ten , ghct.soLuong as soLuong , ghct.idChiTietSanPham.idHang.ten as hang , ghct.idChiTietSanPham.idMauSac.ten as mau , ghct.idChiTietSanPham.idKichCo.ten as size , ghct.idChiTietSanPham.idChatLieu.ten as chatLieu , ghct.idChiTietSanPham.anh as anh , ghct.idChiTietSanPham.gia as gia , ghct.idChiTietSanPham.idSanPham.ma as maSanPham , ghct.idChiTietSanPham.mota as moTa from GioHangChiTiet ghct" +
            " where ghct.idGioHang.idKhachHang.email like ?1 and ghct.trangThai = 1 ")
    List<GioHangChiTietCustom> getListGH(String email);

    @Query(value = "select ghct.idChiTietSanPham.id as idCTSP , ghct.idChiTietSanPham.giaKhuyenMai as khuyenMai,ghct.idChiTietSanPham.soLuong as soLuongTon , ghct.idGioHang.id as idGH , " +
            "ghct.idChiTietSanPham.anh2 as anh2, ghct.idChiTietSanPham.anh3 as anh3, ghct.idChiTietSanPham.anh4 as anh4, ghct.idChiTietSanPham.anh5 as anh5, (ghct.idChiTietSanPham.gia * ghct.soLuong - ghct.soLuong * ghct.idChiTietSanPham.giaKhuyenMai) as thanhTien, ghct.id as id ,ghct.idChiTietSanPham.idSanPham.ten as ten , ghct.soLuong as soLuong , ghct.idChiTietSanPham.idHang.ten as hang , ghct.idChiTietSanPham.idMauSac.ten as mau , ghct.idChiTietSanPham.idKichCo.ten as size , ghct.idChiTietSanPham.idChatLieu.ten as chatLieu , ghct.idChiTietSanPham.anh as anh , ghct.idChiTietSanPham.gia as gia , ghct.idChiTietSanPham.idSanPham.ma as maSanPham , ghct.idChiTietSanPham.mota as moTa from GioHangChiTiet ghct" +
            " where ghct.idGioHang.idKhachHang.email like ?1 and ghct.trangThai = 2 ")
    List<GioHangChiTietCustom> getSPGHCheckOut(String email);

    @Query(value = "select ghct.id as id , ghct.soLuong as soLuong ,  ghct.trangThai as trangThai , ghct.idChiTietSanPham as idCTSP  from GioHangChiTiet ghct where ghct.idChiTietSanPham.id = ?1 and ghct.idGioHang.idKhachHang.email = ?2 ")
    Optional<GioHangChiTietCustom> getGHCT(Integer id, String email);

    @Query(value = "select ctsp.id as id from ChiTietSanPham ctsp" +
            " where ctsp.idSanPham.ten like ?1 and ctsp.idMauSac.id = ?2 and  ctsp.idKichCo.id  = ?3 ")
    Optional<ChiTietSanPhamCustom> getSP(String tenSP, Integer mauSP, Integer kichCo);

}
