package com.example.demo.repositories;

import com.example.demo.dto.ctsp.ChiTietSanPhamCustom;
import com.example.demo.dto.cuaHang.CuaHangCustum;
import com.example.demo.dto.cuaHang.CuaHangRequest;
import com.example.demo.dto.cuaHang.SearchThuocTinh;
import com.example.demo.entity.ChiTietSanPham;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CuaHangRepository extends JpaRepository<ChiTietSanPham, Integer> {

    @Query("select ctsp.id as id , ctsp.idSanPham.ten as tenSanPham , ctsp.gia as gia , ctsp.anh as tenAnh , " +
            "ctsp.anh2 as tenAnh2 , ctsp.anh3 as tenAnh3 , ctsp.anh4 as tenAnh4 , ctsp.anh5 as tenAnh5 from ChiTietSanPham ctsp " +
            "where (ctsp.idMauSac.ten IN :#{#x.tenMau} OR :#{#x.tenMau} IS NULL ) " +
            "and (ctsp.idKichCo.ten IN :#{#x.kichCo} OR :#{#x.kichCo} IS NULL ) " +
            "and (ctsp.idTheLoai.ten IN :#{#x.theLoai} OR :#{#x.theLoai} IS NULL ) " +
            "and (ctsp.idChatLieu.ten IN :#{#x.chatLieu} OR :#{#x.chatLieu} IS NULL ) " +
            "and (ctsp.idSanPham.ten like %:#{#x.tenSanPham}% OR :#{#x.tenSanPham} IS NULL ) " +
            "and (ctsp.gia between :#{#x.giaBatDau} and :#{#x.giaKetThuc} OR :#{#x.giaBatDau} IS NULL OR :#{#x.giaKetThuc} IS NULL) " +
            "and (ctsp.idSanPham.idKhuyenMai is null) " +
            "and ctsp.id in (select Max (ct.id) from ChiTietSanPham ct " +
            "join SanPham sp on sp.id = ct.idSanPham.id " +
            "join KichCo kc on kc.id = ct.idKichCo.id " +
            "join MauSac ms on ms.id = ct.idMauSac.id " +
            "join TheLoai tl on tl.id = ct.idTheLoai.id " +
            "join ChatLieu cl on cl.id = ct.idChatLieu.id " +
            "group by ct.idSanPham.ten)")
    List<CuaHangCustum> hienThiSanPhamCuaHang(@Param("x") SearchThuocTinh x, Pageable pageable);

    @Query("select ctsp.id as id , ctsp.idSanPham.ten as tenSanPham , ctsp.gia as gia , ctsp.giaKhuyenMai as giaKM , ctsp.anh as tenAnh " +
            ", ctsp.anh2 as tenAnh2, ctsp.anh3 as tenAnh3, ctsp.anh4 as tenAnh4, ctsp.anh5 as tenAnh5, sp.idKhuyenMai.chietKhau as chietKhau from ChiTietSanPham ctsp " +
            " join  SanPham sp on sp.id = ctsp.idSanPham.id " +
            "where " +
            " (ctsp.idMauSac.ten IN :#{#x.tenMau} OR :#{#x.tenMau} IS NULL ) " +
            "and (ctsp.idKichCo.ten IN :#{#x.kichCo} OR :#{#x.kichCo} IS NULL ) " +
            "and (ctsp.idTheLoai.ten IN :#{#x.theLoai} OR :#{#x.theLoai} IS NULL ) " +
            "and (ctsp.idChatLieu.ten IN :#{#x.chatLieu} OR :#{#x.chatLieu} IS NULL ) " +
            "and (ctsp.idSanPham.ten like %:#{#x.tenSanPham}% OR :#{#x.tenSanPham} IS NULL ) " +
            "and (ctsp.gia between :#{#x.giaBatDau} and :#{#x.giaKetThuc} OR :#{#x.giaBatDau} IS NULL OR :#{#x.giaKetThuc} IS NULL) " +
            "and (ctsp.idSanPham.idKhuyenMai is not null) " +
            "and ctsp.id in (select MAX (ct.id) from ChiTietSanPham ct " +
            "join SanPham sp on sp.id = ct.idSanPham.id " +
            "join KichCo kc on kc.id = ct.idKichCo.id " +
            "join MauSac ms on ms.id = ct.idMauSac.id " +
            "join TheLoai tl on tl.id = ct.idTheLoai.id " +
            "join ChatLieu cl on cl.id = ct.idChatLieu.id " +
            "group by ct.idSanPham.ten)")
    List<CuaHangCustum> hienThiSanPhamCuaHangKhuyenMai(@Param("x") SearchThuocTinh x, Pageable pageable);

    @Query(value = "select ct.id  as id , sp.ten as tenSanPham, ct.gia as gia , ct.anh as tenAnh , ct.anh2 as tenAnh2" +
            ", ct.anh3 as tenAnh3 , ct.anh4 as tenAnh4 , ct.anh5 as tenAnh5 , ct.gia_khuyen_mai as giaKM from chi_tiet_san_pham ct " +
            " join san_pham sp on ct.id_san_pham = sp.id \n " +
            " join mau_sac ms on ms.id = ct.id_mau_sac " +
            " join  kich_co kc on ct.id_kich_co = kc.id\n" +
            " join  the_loai tl on ct.id_the_loai = tl.id\n" +
            " join  chat_lieu cl on ct.id_chat_lieu = cl.id \n" +
            " where ct.create_at >= DATEADD(DAY, -15, GETDATE()) \n" +
            " and ((sp.ten like %:#{#x.tenSanPham}% OR :#{#x.tenSanPham} IS NULL ) " +
            " or (ms.ten like %:#{#x.tenSanPham}% OR :#{#x.tenSanPham} IS NULL ) " +
            " or (tl.ten like %:#{#x.tenSanPham}% OR :#{#x.tenSanPham} IS NULL ) " +
            " or (cl.ten like %:#{#x.tenSanPham}% OR :#{#x.tenSanPham} IS NULL )) " +
            " and (ct.gia between :#{#x.giaBatDau} and :#{#x.giaKetThuc} OR :#{#x.giaBatDau} IS NULL OR :#{#x.giaKetThuc} IS NULL) " +
            " and ct.id in (select MAX(ctsp.id) from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            " join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            " join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            " join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            " join  chat_lieu cl on ctsp.id_chat_lieu = cl.id \n" +
            " group by sp.id) order by ct.id desc", nativeQuery = true)
    List<CuaHangCustum> sanPhamMoi(@Param("x") SearchThuocTinh x, Pageable pageable);


    @Query("select ctsp.id as id , ctsp.idSanPham.ten as tenSanPham , ctsp.gia as gia , ctsp.anh as tenAnh , " +
            "ctsp.anh2 as tenAnh2, ctsp.anh3 as tenAnh3, ctsp.anh4 as tenAnh4, ctsp.anh5 as tenAnh5 from ChiTietSanPham ctsp " +
            "where ctsp.idSanPham.ten like %:#{#x.tenSanPham}% and ctsp.id in (select MIN (ct.id) from ChiTietSanPham ct " +
            "join SanPham sp on sp.id = ct.idSanPham.id " +
            "join KichCo kc on kc.id = ct.idKichCo.id " +
            "join MauSac ms on ms.id = ct.idMauSac.id " +
            "join TheLoai tl on tl.id = ct.idTheLoai.id " +
            "join ChatLieu cl on cl.id = ct.idChatLieu.id " +
            "join Hang h on h.id = ct.idHang.id " +
            "group by ctsp.idSanPham.ten)")
    List<CuaHangCustum> searchSanPham(@Param("x") CuaHangRequest x);


    @Query(value = "select ms.ten as ten from chi_tiet_san_pham ct join san_pham sp on ct.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ct.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ct.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ct.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ct.id_chat_lieu = cl.id \n" +
            "where sp.id_khuyen_mai is null and\n" +
            " ct.id in (select Max(ctsp.id) from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id \n" +
            "  group by sp.id) group by ms.ten",nativeQuery = true)
    List<CuaHangCustum> getMauSac();

    @Query(value = "select kc.ten as ten from chi_tiet_san_pham ct join san_pham sp on ct.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ct.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ct.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ct.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ct.id_chat_lieu = cl.id \n" +
            "where sp.id_khuyen_mai is null and\n" +
            " ct.id in (select Max(ctsp.id) from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id \n" +
            "  group by sp.id) group by kc.ten",nativeQuery = true)
    List<CuaHangCustum> getKichCo();

    @Query(value = "select cl.ten as ten from chi_tiet_san_pham ct join san_pham sp on ct.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ct.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ct.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ct.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ct.id_chat_lieu = cl.id \n" +
            "where sp.id_khuyen_mai is null and\n" +
            " ct.id in (select Max(ctsp.id) from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id \n" +
            "  group by sp.id) group by cl.ten",nativeQuery = true)
    List<CuaHangCustum> getChatLieu();

    @Query(value = "select tl.ten as ten from chi_tiet_san_pham ct join san_pham sp on ct.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ct.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ct.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ct.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ct.id_chat_lieu = cl.id \n" +
            "where sp.id_khuyen_mai is null and\n" +
            " ct.id in (select Max(ctsp.id) from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id \n" +
            "  group by sp.id) group by tl.ten",nativeQuery = true)
    List<CuaHangCustum> getTheLoai();

    @Query(value = "select ms.ten as ten from chi_tiet_san_pham ct join san_pham sp on ct.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ct.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ct.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ct.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ct.id_chat_lieu = cl.id \n" +
            "where sp.id_khuyen_mai is not null and\n" +
            " ct.id in (select Max(ctsp.id) from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id \n" +
            "  group by sp.id) group by ms.ten",nativeQuery = true)
    List<CuaHangCustum> getMauSacKM();

    @Query(value = "select kc.ten as ten from chi_tiet_san_pham ct join san_pham sp on ct.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ct.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ct.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ct.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ct.id_chat_lieu = cl.id \n" +
            "where sp.id_khuyen_mai is not null and\n" +
            " ct.id in (select Max(ctsp.id) from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id \n" +
            "  group by sp.id) group by kc.ten",nativeQuery = true)
    List<CuaHangCustum> getKichCoKM();

    @Query(value = "select cl.ten as ten from chi_tiet_san_pham ct join san_pham sp on ct.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ct.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ct.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ct.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ct.id_chat_lieu = cl.id \n" +
            "where sp.id_khuyen_mai is not null and\n" +
            " ct.id in (select Max(ctsp.id) from chi_tiet_san_pham ctsp \n" +
            " join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "  join  kich_co kc on ctsp.id_kich_co = kc.id\n" +
            "  join  mau_sac ms on ctsp.id_mau_sac = ms.id\n" +
            "  join  the_loai tl on ctsp.id_the_loai = tl.id\n" +
            "  join  chat_lieu cl on ctsp.id_chat_lieu = cl.id \n" +
            "  group by sp.id) group by cl.ten",nativeQuery = true)
    List<CuaHangCustum> getChatLieuKM();

    @Query("select tl.ten as ten , count(ct.idTheLoai) as soLuong from ChiTietSanPham ct join TheLoai tl on tl.id = ct.idTheLoai.id " +
            "where ct.idSanPham.idKhuyenMai is not null " +
            " group by tl.ten")
    List<CuaHangCustum> getTheLoaiKM();
//    @Query("select tl.ten as ten , count(ct.idTheLoai) as soLuong from ChiTietSanPham ct join KichCo tl on tl.id = ct.idKichCo.id " +
//            "where ct.idSanPham.idKhuyenMai is not null " +
//            " group by tl.ten")


}
