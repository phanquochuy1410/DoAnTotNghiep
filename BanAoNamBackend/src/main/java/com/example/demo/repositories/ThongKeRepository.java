package com.example.demo.repositories;

import com.example.demo.dto.HoaDonChiTiet.DanhSachHoaDonCustom;
import com.example.demo.dto.HoaDonChiTiet.HoaDonChiTietCustom;
import com.example.demo.dto.HoaDonChiTiet.SearchHoaDon;
import com.example.demo.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ThongKeRepository extends JpaRepository<HoaDon, Integer> {

    @Query("SELECT hd.id as id , hd.ma as maHoaDon , hd.tenNguoiNhan as tenKhachHang ," +
            "hd.idKhachHang.idDiaChi.thanhPho as diaChi , hd.trangThai as trangThai , hd.lyDo as lyDo , " +
            "hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan ,SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien , COUNT (hd.ma) as soLuong , hd.hinhThucThanhToan as hinhThucThanhToan from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "group by hd.id , hd.ma , hd.lyDo , hd.tenNguoiNhan , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan , hd.hinhThucThanhToan " +
            "ORDER BY hd.id DESC ")
    List<DanhSachHoaDonCustom> findDanhSachHoaDon(Pageable pageable);


    @Query("SELECT hd.id as id , hd.ma as maHoaDon , hd.tenNguoiNhan as tenKhachHang , " +
            "hd.idKhachHang.idDiaChi.thanhPho as diaChi , hd.trangThai as trangThai , hd.lyDo as lyDo , " +
            "hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien , hd.hinhThucThanhToan as hinhThucThanhToan from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "WHERE " +
            "hd.ma like %:search% " +
            "group by hd.id , hd.ma , hd.lyDo , hd.tenNguoiNhan , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan ,  hd.hinhThucThanhToan " +
            "ORDER BY hd.id DESC ")
    List<DanhSachHoaDonCustom> timKiem(@Param("search") String search);


    @Query("SELECT hd.id as id , hd.ma as maHoaDon , hd.tenNguoiNhan as tenKhachHang , " +
            "hd.idKhachHang.idDiaChi.thanhPho as diaChi , hd.trangThai as trangThai , hd.lyDo as lyDo , " +
            "hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien , hd.hinhThucThanhToan as hinhThucThanhToan from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "WHERE hd.trangThai = ?1 " +
            "group by hd.id , hd.ma , hd.lyDo , hd.tenNguoiNhan , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan ,  hd.hinhThucThanhToan " +
            "ORDER BY hd.id DESC")
    List<DanhSachHoaDonCustom> timKiemTrangThai(Integer trangThai);


    @Query("SELECT hd.id as id , hd.ma as maHoaDon , hd.tenNguoiNhan as tenKhachHang , " +
            "hd.idKhachHang.idDiaChi.thanhPho as diaChi , hd.trangThai as trangThai , hd.lyDo as lyDo , " +
            "hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien , COUNT (hd.ma) as soLuong , hd.hinhThucThanhToan as hinhThucThanhToan from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "WHERE hd.update_at between :#{#x.ngayTaoSearch} and :#{#x.ngayTaoSearchs} " +
            "group by hd.id , hd.ma , hd.lyDo , hd.tenNguoiNhan , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan , hd.hinhThucThanhToan " +
            "ORDER BY hd.id DESC")
    List<DanhSachHoaDonCustom> searchKhoangNgay(@Param("x") SearchHoaDon x);

    @Query("SELECT hd.id as id , hd.ma as maHoaDon , hd.tenNguoiNhan as tenKhachHang ," +
            "hd.idKhachHang.idDiaChi.thanhPho as diaChi , hd.trangThai as trangThai , hd.lyDo as lyDo , " +
            "hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien , COUNT (hd.ma) as soLuong , hd.hinhThucThanhToan as hinhThucThanhToan from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "WHERE hd.update_at = :#{#x.ngayTaoSearch} " +
            "group by hd.id , hd.ma , hd.lyDo , hd.tenNguoiNhan , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan , hd.hinhThucThanhToan " +
            "ORDER BY hd.id DESC ")
    List<DanhSachHoaDonCustom> searchNgay(@Param("x") SearchHoaDon x);

    @Query("SELECT hd.id as id , hd.ma as maHoaDon , hd.tenNguoiNhan as tenKhachHang , " +
            "hd.idKhachHang.idDiaChi.thanhPho as diaChi , hd.trangThai as trangThai , hd.lyDo as lyDo , " +
            " hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien , hd.hinhThucThanhToan as hinhThucThanhToan from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "WHERE hd.ngayTao >= :sevenday " +
            "group by hd.id , hd.ma , hd.lyDo , hd.tenNguoiNhan , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan , hd.hinhThucThanhToan " +
            "ORDER BY hd.id DESC")
    List<DanhSachHoaDonCustom> load7NgayTruoc(@Param("sevenday") LocalDate sevenday);


    @Query("SELECT hd.id as id , hd.ma as maHoaDon , hd.tenNguoiNhan as tenKhachHang , " +
            "hd.idKhachHang.idDiaChi.thanhPho as diaChi , hd.trangThai as trangThai , hd.lyDo as lyDo , " +
            " hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien , hd.hinhThucThanhToan as hinhThucThanhToan from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "WHERE MONTH(hd.ngayTao) = MONTH(:currentDate) AND YEAR(hd.ngayTao) = YEAR(:currentDate)" +
            " group by hd.id , hd.ma , hd.lyDo , hd.tenNguoiNhan , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan , hd.hinhThucThanhToan " +
            "ORDER BY hd.id DESC")
    List<DanhSachHoaDonCustom> loadThangNay(@Param("currentDate") LocalDate currentDate);


    //Thống kê sản phẩm

    @Query("SELECT cthd.idChiTietSanPham.idSanPham.ten as idChiTietSanPham , sum(cthd.soLuong) as soLuong , SUM((cthd.gia * cthd.soLuong) - (cthd.giaSauKhuyenMai * cthd.soLuong)) as gia from ChiTietSanPham ctsp inner join HoaDonChiTiet cthd " +
            "on ctsp.id = cthd.idChiTietSanPham.id " +
            "join HoaDon hd on hd.id = cthd.idHoaDon.id " +
            " where hd.trangThai = 1 group by cthd.idChiTietSanPham.idSanPham.ten " +
            "ORDER BY SUM(cthd.gia) DESC")
    List<HoaDonChiTietCustom> thongKeSanPham();

    @Query("SELECT cthd.idChiTietSanPham.idSanPham.ten as idChiTietSanPham , sum(cthd.soLuong) as soLuong , SUM((cthd.gia * cthd.soLuong) - (cthd.giaSauKhuyenMai * cthd.soLuong)) as gia from ChiTietSanPham ctsp inner join HoaDonChiTiet cthd " +
            "on ctsp.id = cthd.idChiTietSanPham.id " +
            "join HoaDon hd on hd.id = cthd.idHoaDon.id " +
            " where hd.ngayNhan >= :day and hd.trangThai = 1 group by cthd.idChiTietSanPham.idSanPham.ten " +
            "ORDER BY SUM(cthd.gia) DESC")
    List<HoaDonChiTietCustom> load7NgaySanPham(@Param("day") LocalDate day);

    @Query("SELECT cthd.idChiTietSanPham.idSanPham.ten as idChiTietSanPham , sum(cthd.soLuong) as soLuong , SUM((cthd.gia * cthd.soLuong) - (cthd.giaSauKhuyenMai * cthd.soLuong)) as gia from ChiTietSanPham ctsp inner join HoaDonChiTiet cthd " +
            "on ctsp.id = cthd.idChiTietSanPham.id " +
            "join HoaDon hd on hd.id = cthd.idHoaDon.id " +
            " where MONTH(hd.ngayNhan) = MONTH(:currentDate) AND YEAR(hd.ngayNhan) = YEAR(:currentDate) and hd.trangThai = 1 group by cthd.idChiTietSanPham.idSanPham.ten " +
            "ORDER BY SUM(cthd.gia) DESC")
    List<HoaDonChiTietCustom> loadThangNaySanPham(@Param("currentDate") LocalDate currentDate);

    @Query("SELECT cthd.idChiTietSanPham.idSanPham.ten as idChiTietSanPham , sum(cthd.soLuong) as soLuong , SUM((cthd.gia * cthd.soLuong) - (cthd.giaSauKhuyenMai * cthd.soLuong)) as gia from ChiTietSanPham ctsp inner join HoaDonChiTiet cthd " +
            "on ctsp.id = cthd.idChiTietSanPham.id " +
            "join HoaDon hd on hd.id = cthd.idHoaDon.id " +
            " where hd.ngayNhan = :#{#x.ngayTaoSearch} and hd.trangThai = 1 group by cthd.idChiTietSanPham.idSanPham.ten " +
            "ORDER BY SUM(cthd.gia) DESC")
    List<HoaDonChiTietCustom> loadSanPhamBanTheoNgay(@Param("x") SearchHoaDon x);

    @Query("SELECT cthd.idChiTietSanPham.idSanPham.ten as idChiTietSanPham , sum(cthd.soLuong) as soLuong , SUM((cthd.gia * cthd.soLuong) - (cthd.giaSauKhuyenMai * cthd.soLuong)) as gia from ChiTietSanPham ctsp inner join HoaDonChiTiet cthd " +
            "on ctsp.id = cthd.idChiTietSanPham.id " +
            "join HoaDon hd on hd.id = cthd.idHoaDon.id " +
            " where (hd.ngayNhan between :#{#x.ngayTaoSearch} and :#{#x.ngayTaoSearchs}) and hd.trangThai = 1 group by cthd.idChiTietSanPham.idSanPham.ten " +
            "ORDER BY SUM(cthd.gia) DESC")
    List<HoaDonChiTietCustom> loadSanPhamBanTheoKhoangNgay(@Param("x") SearchHoaDon x);

    //Thống kê ngày

    @Query(value = "WITH TongGiaHoaDon AS (SELECT SUM((ct.gia * ct.so_luong) - (ct.gia_sau_khuyen_mai * ct.so_luong)) AS tongTien , hd.ngay_nhan as ngayTao FROM hoa_don_chi_tiet ct join hoa_don hd on hd.id = ct.id_hoa_don where hd.trang_thai = 1 GROUP BY hd.ngay_nhan" +
            ")," +
            "SoLuongHoaDonTheoNgay AS (SELECT count(hd.ma) as soLuong, hd.ngay_nhan AS ngayTao , hd.trang_thai as trangThai FROM hoa_don hd where hd.trang_thai = 1 GROUP BY hd.ngay_nhan , hd.trang_thai" +
            ")" +
            "SELECT T1.soLuong, T2.tongTien, T2.ngayTao FROM SoLuongHoaDonTheoNgay T1" +
            " JOIN TongGiaHoaDon T2 ON T1.NgayTao = T2.NgayTao where T1.trangThai = 1", nativeQuery = true)
    List<DanhSachHoaDonCustom> thongKeTheoNgay();

    @Query(value = "WITH TongGiaHoaDon AS (SELECT SUM((ct.gia * ct.so_luong) - (ct.gia_sau_khuyen_mai * ct.so_luong)) AS tongTien , hd.ngay_nhan as ngayTao FROM hoa_don_chi_tiet ct join hoa_don hd on hd.id = ct.id_hoa_don where hd.trang_thai = 1 GROUP BY hd.ngay_nhan" +
            ")," +
            "SoLuongHoaDonTheoNgay AS (SELECT count(hd.ma) as soLuong, hd.ngay_nhan AS ngayTao , hd.trang_thai as trangThai FROM hoa_don hd where hd.trang_thai = 1 GROUP BY hd.ngay_nhan , hd.trang_thai" +
            ")" +
            "SELECT T1.soLuong, T2.tongTien, T2.ngayTao FROM SoLuongHoaDonTheoNgay T1" +
            " JOIN TongGiaHoaDon T2 ON T1.NgayTao = T2.NgayTao WHERE T1.trangThai = 1 and T2.ngayTao = :#{#x.ngayTaoSearch}", nativeQuery = true)
    List<DanhSachHoaDonCustom> searchNgayInThongKeNgay(@Param("x") SearchHoaDon x);

    @Query(value = "WITH TongGiaHoaDon AS (SELECT SUM((ct.gia * ct.so_luong) - (ct.gia_sau_khuyen_mai * ct.so_luong)) AS tongTien , hd.ngay_nhan as ngayTao FROM hoa_don_chi_tiet ct join hoa_don hd on hd.id = ct.id_hoa_don where hd.trang_thai = 1 GROUP BY hd.ngay_nhan" +
            ")," +
            "SoLuongHoaDonTheoNgay AS (SELECT count(hd.ma) as soLuong, hd.ngay_nhan AS ngayTao , hd.trang_thai as trangThai FROM hoa_don hd where hd.trang_thai = 1  GROUP BY hd.ngay_nhan , hd.trang_thai" +
            ")" +
            "SELECT T1.soLuong, T2.tongTien, T2.ngayTao FROM SoLuongHoaDonTheoNgay T1" +
            " JOIN TongGiaHoaDon T2 ON T1.NgayTao = T2.NgayTao WHERE T1.trangThai = 1 and T2.ngayTao between :#{#x.ngayTaoSearch} and :#{#x.ngayTaoSearchs}", nativeQuery = true)
    List<DanhSachHoaDonCustom> searchKhoangNgayInThongKeNgay(@Param("x") SearchHoaDon x);

    @Query(value = "WITH TongGiaHoaDon AS (SELECT SUM((ct.gia * ct.so_luong) - (ct.gia_sau_khuyen_mai * ct.so_luong)) AS tongTien , hd.update_at as ngayTao FROM hoa_don_chi_tiet ct join hoa_don hd on hd.id = ct.id_hoa_don where hd.trang_thai = 1 GROUP BY hd.update_at" +
            ")," +
            "SoLuongHoaDonTheoNgay AS (SELECT count(hd.ma) as soLuong, hd.update_at AS ngayTao , hd.trang_thai as trangThai FROM hoa_don hd where hd.trang_thai = 1 GROUP BY hd.update_at , hd.trang_thai" +
            ")" +
            "SELECT T1.soLuong, T2.tongTien, T2.ngayTao FROM SoLuongHoaDonTheoNgay T1" +
            " JOIN TongGiaHoaDon T2 ON T1.NgayTao = T2.NgayTao where T1.trangThai = 1 and T2.NgayTao >= :day ", nativeQuery = true)
    List<DanhSachHoaDonCustom> load7NgayTruocInNgay(@Param("day") LocalDate day);

    @Query(value = "WITH TongGiaHoaDon AS (SELECT SUM((ct.gia * ct.so_luong) - (ct.gia_sau_khuyen_mai * ct.so_luong)) AS tongTien , hd.update_at as ngayTao FROM hoa_don_chi_tiet ct join hoa_don hd on hd.id = ct.id_hoa_don where hd.trang_thai = 1 GROUP BY hd.update_at" +
            ")," +
            "SoLuongHoaDonTheoNgay AS (SELECT count(hd.ma) as soLuong, hd.update_at AS ngayTao , hd.trang_thai as trangThai FROM hoa_don hd where hd.trang_thai = 1 GROUP BY hd.update_at , hd.trang_thai" +
            ")" +
            "SELECT T1.soLuong, T2.tongTien, T2.ngayTao FROM SoLuongHoaDonTheoNgay T1" +
            " JOIN TongGiaHoaDon T2 ON T1.NgayTao = T2.NgayTao where T1.trangThai = 1 and MONTH(T2.NgayTao) = MONTH(:currentDate) AND YEAR(T2.NgayTao) = YEAR(:currentDate) ", nativeQuery = true)
    List<DanhSachHoaDonCustom> loadThangNayInNgay(@Param("currentDate") LocalDate currentDate);

    // Thống kê theo tháng

    @Query(value = "WITH TongGiaHoaDon AS (SELECT SUM((ct.gia * ct.so_luong) - (ct.gia_sau_khuyen_mai * ct.so_luong)) AS tongTien ,hd.ngay_nhan as ngayTao FROM hoa_don_chi_tiet ct join hoa_don hd on hd.id = ct.id_hoa_don where hd.trang_thai = 1 GROUP BY hd.ngay_nhan\n" +
            "            ),\n" +
            "            SoLuongHoaDonTheoNgay AS (SELECT count(hd.ma) as soLuong, hd.ngay_nhan AS ngayTao , hd.trang_thai as trangThai FROM hoa_don hd GROUP BY hd.ngay_nhan , hd.trang_thai \n" +
            "            )\n" +
            "            SELECT sum(T1.soLuong) as soLuong , sum(T2.tongTien) as tongTien  , month(T2.ngayTao) as thang FROM SoLuongHoaDonTheoNgay T1 \n" +
            "             JOIN TongGiaHoaDon T2 ON T1.NgayTao = T2.NgayTao where T1.trangThai = 1 and month(T2.ngayTao) = :thang and year(T2.ngayTao) = :nam group by month(T2.ngayTao)", nativeQuery = true)
    List<DanhSachHoaDonCustom> thongKeTheoThang(@Param("thang") Integer thang, @Param("nam") Integer nam);

    @Query(value = "WITH TongGiaHoaDon AS (SELECT SUM((ct.gia * ct.so_luong) - (ct.gia_sau_khuyen_mai * ct.so_luong)) AS tongTien ,hd.ngay_nhan as ngayTao FROM hoa_don_chi_tiet ct join hoa_don hd on hd.id = ct.id_hoa_don where hd.trang_thai = 1 GROUP BY hd.ngay_nhan\n" +
            "            ),\n" +
            "            SoLuongHoaDonTheoNgay AS (SELECT count(hd.ma) as soLuong, hd.ngay_nhan AS ngayTao , hd.trang_thai as trangThai FROM hoa_don hd GROUP BY hd.ngay_nhan , hd.trang_thai \n" +
            "            )\n" +
            "            SELECT sum(T1.soLuong) as soLuong , sum(T2.tongTien) as tongTien  , month(T2.ngayTao) as thang FROM SoLuongHoaDonTheoNgay T1 \n" +
            "             JOIN TongGiaHoaDon T2 ON T1.NgayTao = T2.NgayTao where T1.trangThai = 1 and (month(T2.ngayTao) between :thangBatDau and :thangKetThuc) and year(T2.ngayTao) like :nam group by month(T2.ngayTao)", nativeQuery = true)
    List<DanhSachHoaDonCustom> thongKeTheoKhoangThang(@Param("thangBatDau") Integer thangBatDau, @Param("thangKetThuc") Integer thangKetThuc, @Param("nam") Integer nam);

    @Query(value = "SELECT year(ngay_nhan) as thang from hoa_don group by year(ngay_nhan)", nativeQuery = true)
    List<DanhSachHoaDonCustom> getNam();

    // Thống kê theo năm

    @Query(value = "WITH TongGiaHoaDon AS (SELECT SUM((ct.gia * ct.so_luong) - (ct.gia_sau_khuyen_mai * ct.so_luong)) AS tongTien ,hd.ngay_nhan as ngayTao FROM hoa_don_chi_tiet ct join hoa_don hd on hd.id = ct.id_hoa_don where hd.trang_thai = 1 GROUP BY hd.ngay_nhan\n" +
            "            ),\n" +
            "            SoLuongHoaDonTheoNgay AS (SELECT count(hd.ma) as soLuong, hd.ngay_nhan AS ngayTao , hd.trang_thai as trangThai FROM hoa_don hd GROUP BY hd.ngay_nhan , hd.trang_thai \n" +
            "            )\n" +
            "            SELECT sum(T1.soLuong) as soLuong , sum(T2.tongTien) as tongTien  , year(T2.ngayTao) as nam FROM SoLuongHoaDonTheoNgay T1 \n" +
            "             JOIN TongGiaHoaDon T2 ON T1.NgayTao = T2.NgayTao where T1.trangThai = 1 and year(T2.ngayTao) = :nam group by year(T2.ngayTao)", nativeQuery = true)
    List<DanhSachHoaDonCustom> thongKeTheoNam(@Param("nam") Integer nam);

    // Tổng đơn và tổng tiền bán được trong ngày

    @Query(value = "SELECT hd.id as id ,  hd.trang_thai as trangThai , hd.ngay_nhan as ngayNhan , SUM((hdct.gia * hdct.so_luong) - (hdct.gia_sau_khuyen_mai * hdct.so_luong)) as tongTien , COUNT (hd.ma) as soLuong from hoa_don hd inner join hoa_don_chi_tiet hdct \n" +
            "            on hd.id = hdct.id_hoa_don where hd.update_at = CONVERT(DATE, GETDATE()) and hd.id_khach_hang != 8 \n" +
            "            group by hd.id , hd.trang_thai , hd.ngay_nhan \n" +
            "            ORDER BY hd.id DESC", nativeQuery = true)
    List<DanhSachHoaDonCustom> loadTongTienTrongNgay();

    @Query(value = "SELECT hd.id as id ,  hd.trang_thai as trangThai , hd.ngay_nhan as ngayNhan , SUM((hdct.gia * hdct.so_luong) - (hdct.gia_sau_khuyen_mai * hdct.so_luong)) as tongTien , COUNT (hd.ma) as soLuong from hoa_don hd inner join hoa_don_chi_tiet hdct \n" +
            "            on hd.id = hdct.id_hoa_don where hd.update_at = CONVERT(DATE, GETDATE()) and hd.id_khach_hang = 8 \n" +
            "            group by hd.id , hd.trang_thai , hd.ngay_nhan \n" +
            "            ORDER BY hd.id DESC", nativeQuery = true)
    List<DanhSachHoaDonCustom> loadTongTienTrongNgayTaiQuay();

    @Query(value = "SELECT hd.id as id ,  hd.trang_thai as trangThai , hd.create_at as ngayNhan , SUM((hdct.gia * hdct.so_luong) - (hdct.gia_sau_khuyen_mai * hdct.so_luong)) as tongTien , COUNT (hd.ma) as soLuong from hoa_don hd inner join hoa_don_chi_tiet hdct \n" +
            "            on hd.id = hdct.id_hoa_don where hd.update_at = CONVERT(DATE, GETDATE()) \n" +
            "            group by hd.id , hd.trang_thai , hd.create_at \n" +
            "            ORDER BY hd.id DESC", nativeQuery = true)
    List<DanhSachHoaDonCustom> loadTrangThai();

    @Query(value = "select sp.ten as tenSanPham , SUM((hdct.gia * hdct.so_luong) - (hdct.gia_sau_khuyen_mai * hdct.so_luong)) as tongTien , hd.ngay_nhan from hoa_don hd join hoa_don_chi_tiet hdct \n" +
            "on hdct.id_hoa_don = hd.id join chi_tiet_san_pham ctsp on ctsp.id = hdct.id_chi_tiet_san_pham join san_pham sp on sp.id = ctsp.id_san_pham\n" +
            "where hd.trang_thai = 1 and hd.update_at = CONVERT(DATE, GETDATE()) group by sp.ten , hd.ngay_nhan order by sum(hdct.gia) desc", nativeQuery = true)
    List<DanhSachHoaDonCustom> loadSanPhamBanChay();

}
