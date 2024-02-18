package com.example.demo.repositories;

import com.example.demo.dto.HoaDonChiTiet.DonMuaKhachHang;
import com.example.demo.dto.hdct.HoaDonChiTietCustom;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Integer> {

    @Query(value = "select hdct.id as idHoaDonChiTiet , hdct.idHoaDon.ma as maHoaDon , hdct.idHoaDon.idKhachHang.ten as tenKhachHang , " +
            "hdct.giaSauKhuyenMai as khuyenMai , hdct.idChiTietSanPham.idMauSac.ten as mauSac, hdct.idChiTietSanPham.anh as anh, " +
            "hdct.idChiTietSanPham.anh2 as anh2, hdct.idChiTietSanPham.anh3 as anh3, hdct.idChiTietSanPham.anh4 as anh4, hdct.idChiTietSanPham.anh5 as anh5, " +
            "hdct.idChiTietSanPham.idKichCo.ten as kichCo , hdct.idChiTietSanPham.idSanPham.ten as tenSanPham , " +
            "hdct.idChiTietSanPham.id as idChiTietSanPham , hdct.idChiTietSanPham.soLuong as soLuongTon , hdct.soLuong as soLuong , " +
            "hdct.idChiTietSanPham.gia as gia ,hdct.gia as thanhTien " +
            "from HoaDonChiTiet hdct " +
            " where hdct.idHoaDon.id = ?1")
    List<HoaDonChiTietCustom> listHDCT(Integer idSearch);

    @Query(value = "select * from hoa_don_chi_tiet hdct " +
            " where hdct.id_hoa_don = ?1" , nativeQuery = true)
    List<HoaDonChiTiet> listHDCTS(Integer idSearch);


    List<HoaDonChiTiet> findAllByIdHoaDon(HoaDon hoaDon);

    @Query(value = "SELECT kc.ten AS kichCo,ctsp.gia as gia , hdct.gia_sau_khuyen_mai as giaKhuyenMai , sp.ten AS sanPham, ms.ten AS mauSac, hdct.so_luong AS soLuong, " +
            "hdct.gia AS donGia, SUM((hdct.so_luong * hdct.gia) - (hdct.so_luong * hdct.gia_sau_khuyen_mai)) AS tongTien, hd.id AS hoaDon, " +
            "hdct.id AS HDCT, ctsp.anh AS anh, ctsp.anh2 AS anh2, ctsp.anh3 AS anh3, ctsp.anh4 AS anh4, ctsp.anh5 AS anh5, hd.trang_thai AS trangThai, hd.ngay_tao AS ngayTao, ctsp.id AS idCTSP " +
            "FROM hoa_don_chi_tiet hdct " +
            "INNER JOIN hoa_don hd ON hd.id = hdct.id_hoa_don " +
            "INNER JOIN chi_tiet_san_pham ctsp ON hdct.id_chi_tiet_san_pham = ctsp.id " +
            "INNER JOIN san_pham sp ON ctsp.id_san_pham = sp.id " +
            "INNER JOIN mau_sac ms ON ctsp.id_mau_sac = ms.id " +
            "INNER JOIN kich_co kc ON ctsp.id_kich_co = kc.id " +
            "INNER JOIN khach_hang kh ON hd.id_khach_hang = kh.id " +
            "WHERE hd.id = :id " +
            "GROUP BY kc.ten, sp.ten,ctsp.gia, ms.ten, hdct.so_luong, hd.ngay_tao,hdct.gia_sau_khuyen_mai ," +
            "hdct.gia, hd.trang_thai, hd.id, hdct.id, ctsp.anh, ctsp.anh2, ctsp.anh3, ctsp.anh4, ctsp.anh5, hd.trang_thai, ctsp.id " +
            "ORDER BY hd.ngay_tao DESC", nativeQuery = true)
    List<DonMuaKhachHang> findSPByIdHD(@Param("id")Integer id);
}


