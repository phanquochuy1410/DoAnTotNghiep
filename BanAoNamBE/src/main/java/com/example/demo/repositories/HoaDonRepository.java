package com.example.demo.repositories;

import com.example.demo.dto.hdct.HoaDonChiTietCustom;
import com.example.demo.dto.hoadon.DonHangCustom;
import com.example.demo.dto.hoadon.HoaDonCustom;
import com.example.demo.dto.hoadon.HoaDonSearch;
import com.example.demo.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
//    @Query(value = "select h.id, h.ma , h.gia , h.so_dien_thoai , h.ten_nguoi_nhan, h.ngay_tao, h.trang_thai from hoa_don h where h.trang_thai = %:trangThaiSearch% ", nativeQuery = true)
//    Page<HoaDonCustom> getListByTrangThai(@Param("trangThaiSearch") Integer i, Pageable pageable);


    @Query("SELECT  hd.id as idHoaDon ,hd.tienShip as tienShip, hd.ma as maHoaDon,hd.idKhachHang.idDiaChi.xa as xa , hd.idKhachHang.idDiaChi.huyen as huyen , hd.idKhachHang.idDiaChi.diaChiCuThe as diaChiCuThe ,  hd.idNhanVien.ten as tenNhanVien, hd.idNhanVien.tenDem as tenDemNhanVien , hd.idNhanVien.ho as hoNhanVien , hd.idKhachHang.ten as tenKhachHang, hd.idKhachHang.tenDem as tenDemKhachHang , hd.idKhachHang.ho as hoKhachHang , hd.idKhachHang.soDienThoai as soDienThoai," +
            "hd.idKhachHang.idDiaChi.thanhPho as thanhPho , hd.trangThai as trangThai , hd.hinhThucThanhToan as hinhThucThanhToan , hd.lyDo as lyDo ," +
            "hd.tenNguoiNhan as tenNguoiNhan , hd.soDienThoai as soNguoiNhan , " +
            "hd.ngayTao as ngayTao , hd.update_at as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien  from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "WHERE hd.trangThai = ?1 " +
            "group by hd.id, hd.lyDo,hd.tenNguoiNhan,hd.tienShip, hd.soDienThoai,hd.idKhachHang.idDiaChi.diaChiCuThe , hd.idKhachHang.idDiaChi.huyen , hd.idKhachHang.idDiaChi.xa, hd.hinhThucThanhToan , hd.ma, hd.idNhanVien.ten , hd.idNhanVien.tenDem , hd.idNhanVien.ho ,hd.idKhachHang.soDienThoai, hd.idKhachHang.tenDem, hd.idKhachHang.ho  , hd.idKhachHang.ten , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.update_at " +
            "order by hd.id desc"
    )
    Page<HoaDonCustom> timKiemTrangThai(Integer trangThai, Pageable pageable);


    @Query("SELECT hd.tinhTrang as tinhTrang,  hd.id as idHoaDon, hd.update_by as nguoiXacNhan , hd.lyDo as lyDo , hd.tienShip as tienShip , hd.ma as maHoaDon,hd.idKhachHang.idDiaChi.xa as xa , hd.idKhachHang.idDiaChi.huyen as huyen , hd.idKhachHang.idDiaChi.diaChiCuThe as diaChiCuThe ,   hd.idKhachHang.ten as tenKhachHang, hd.idKhachHang.tenDem as tenDemKhachHang , hd.idKhachHang.ho as hoKhachHang , hd.idKhachHang.soDienThoai as soDienThoai," +
            "hd.idKhachHang.idDiaChi.thanhPho as thanhPho , hd.trangThai as trangThai , hd.hinhThucThanhToan as hinhThucThanhToan ," +
            "hd.tenNguoiNhan as tenNguoiNhan , hd.soDienThoai as soNguoiNhan , " +
            " hd.ngayTao as ngayTao , hd.update_at as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien  from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "WHERE hd.trangThai = ?1 " +
            "group by  hd.update_by ,hd.tinhTrang, hd.id,hd.lyDo,hd.tienShip,hd.idKhachHang.idDiaChi.diaChiCuThe, hd.tenNguoiNhan, hd.soDienThoai , hd.idKhachHang.idDiaChi.huyen , hd.idKhachHang.idDiaChi.xa, hd.ma, hd.idKhachHang.soDienThoai, hd.idKhachHang.tenDem, hd.idKhachHang.ho , hd.hinhThucThanhToan  , hd.idKhachHang.ten , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.update_at " +
            "order by hd.id desc")
    Page<HoaDonCustom> timKiemTrangThaiHuyHoan(Integer trangThai, Pageable pageable);


    @Query("SELECT  hd.id as idHoaDon , hd.tienShip as tienShip , hd.ma as maHoaDon,hd.idKhachHang.idDiaChi.xa as xa , hd.idKhachHang.idDiaChi.huyen as huyen , hd.idKhachHang.idDiaChi.diaChiCuThe as diaChiCuThe ,   hd.idKhachHang.ten as tenKhachHang, hd.idKhachHang.tenDem as tenDemKhachHang , hd.idKhachHang.ho as hoKhachHang , hd.idKhachHang.soDienThoai as soDienThoai," +
            "hd.idKhachHang.idDiaChi.thanhPho as thanhPho , hd.trangThai as trangThai , hd.hinhThucThanhToan as hinhThucThanhToan ," +
            "hd.tenNguoiNhan as tenNguoiNhan , hd.soDienThoai as soNguoiNhan , " +
            " hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien  from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "WHERE hd.trangThai = ?1 " +
            "group by hd.id,hd.tienShip,hd.idKhachHang.idDiaChi.diaChiCuThe, hd.tenNguoiNhan, hd.soDienThoai , hd.idKhachHang.idDiaChi.huyen , hd.idKhachHang.idDiaChi.xa, hd.ma, hd.idKhachHang.soDienThoai, hd.idKhachHang.tenDem, hd.idKhachHang.ho , hd.hinhThucThanhToan  , hd.idKhachHang.ten , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan " +
            "order by hd.id desc")
    Page<HoaDonCustom> timKiemTrangThaiXacNhan(Integer trangThai, Pageable pageable);

    @Query("SELECT hd.id as idHoaDon , hd.ma as maHoaDon,hd.idKhachHang.idDiaChi.xa as xa , hd.idKhachHang.idDiaChi.huyen as huyen , hd.idKhachHang.idDiaChi.diaChiCuThe as diaChiCuThe ,  hd.idNhanVien.ten as tenNhanVien, hd.idNhanVien.tenDem as tenDemNhanVien , hd.idNhanVien.ho as hoNhanVien , hd.idKhachHang.ten as tenKhachHang, hd.idKhachHang.tenDem as tenDemKhachHang , hd.idKhachHang.ho as hoKhachHang , hd.idKhachHang.soDienThoai as soDienThoai, " +
            "hd.idKhachHang.idDiaChi.thanhPho as thanhPho , hd.trangThai as trangThai , hd.hinhThucThanhToan as hinhThucThanhToan ," +
            " hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien from HoaDon hd " +
            " inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id group by hd.id,hd.idKhachHang.idDiaChi.diaChiCuThe , hd.idKhachHang.idDiaChi.huyen , hd.hinhThucThanhToan , hd.idKhachHang.idDiaChi.xa, hd.ma, hd.idNhanVien.ten , hd.idNhanVien.tenDem , hd.idNhanVien.ho ,hd.idKhachHang.soDienThoai, hd.idKhachHang.tenDem, hd.idKhachHang.ho  , hd.idKhachHang.ten , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan " +
            "order by hd.id desc"
    )
    Page<HoaDonCustom> findDanhSachHoaDon(Pageable pageable);

    @Query("SELECT hd.id as idHoaDon , hd.update_by as nguoiXacNhan , hd.ma as maHoaDon,hd.idKhachHang.idDiaChi.xa as xa , hd.idKhachHang.idDiaChi.huyen as huyen , hd.idKhachHang.idDiaChi.diaChiCuThe as diaChiCuThe ,  hd.idNhanVien.ten as tenNhanVien, hd.idNhanVien.tenDem as tenDemNhanVien , hd.idNhanVien.ho as hoNhanVien , hd.idKhachHang.ten as tenKhachHang, hd.idKhachHang.tenDem as tenDemKhachHang , hd.idKhachHang.ho as hoKhachHang , hd.idKhachHang.soDienThoai as soDienThoai, " +
            "hd.idKhachHang.idDiaChi.thanhPho as thanhPho , hd.trangThai as trangThai , hd.hinhThucThanhToan as hinhThucThanhToan , " +
            " hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM(hdct.gia) as tongTien from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id group by  hd.update_by,  hd.id,hd.idKhachHang.idDiaChi.diaChiCuThe , hd.hinhThucThanhToan  , hd.idKhachHang.idDiaChi.huyen , hd.idKhachHang.idDiaChi.xa, hd.ma, hd.idNhanVien.ten , hd.idNhanVien.tenDem , hd.idNhanVien.ho ,hd.idKhachHang.soDienThoai, hd.idKhachHang.tenDem, hd.idKhachHang.ho  , hd.idKhachHang.ten , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan " +
            "order by hd.id desc")
    List<HoaDonCustom> findAllHD();

    @Query("SELECT hd.id as idHoaDon , hd.update_by as nguoiXacNhan , hd.ma as maHoaDon,hd.idKhachHang.idDiaChi.xa as xa , hd.idKhachHang.idDiaChi.huyen as huyen , hd.idKhachHang.idDiaChi.diaChiCuThe as diaChiCuThe , hd.idKhachHang.ten as tenKhachHang, hd.idKhachHang.tenDem as tenDemKhachHang , hd.idKhachHang.ho as hoKhachHang , hd.idKhachHang.soDienThoai as soDienThoai, " +
            "hd.idKhachHang.idDiaChi.thanhPho as thanhPho , hd.trangThai as trangThai , hd.hinhThucThanhToan as hinhThucThanhToan , " +
            " hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM(hdct.gia) as tongTien from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id group by  hd.update_by,  hd.id,hd.idKhachHang.idDiaChi.diaChiCuThe , hd.hinhThucThanhToan  , hd.idKhachHang.idDiaChi.huyen , hd.idKhachHang.idDiaChi.xa, hd.ma,hd.idKhachHang.soDienThoai, hd.idKhachHang.tenDem, hd.idKhachHang.ho  , hd.idKhachHang.ten , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan " +
            "order by hd.id desc")
    List<HoaDonCustom> findAllHDDH();

    @Query("SELECT hd.id as idHoaDon , hd.ma as maHoaDon,hd.idKhachHang.idDiaChi.xa as xa , hd.idKhachHang.idDiaChi.huyen as huyen , hd.idKhachHang.idDiaChi.diaChiCuThe as diaChiCuThe , hd.idKhachHang.ten as tenKhachHang, hd.idKhachHang.tenDem as tenDemKhachHang , hd.idKhachHang.ho as hoKhachHang , hd.idKhachHang.soDienThoai as soDienThoai, " +
            "hd.idKhachHang.idDiaChi.thanhPho as thanhPho , hd.trangThai as trangThai , hd.hinhThucThanhToan as hinhThucThanhToan , " +
            " hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM(hdct.gia) as tongTien from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id group by hd.id,hd.idKhachHang.idDiaChi.diaChiCuThe , hd.hinhThucThanhToan , hd.idKhachHang.idDiaChi.huyen , hd.idKhachHang.idDiaChi.xa, hd.ma,hd.idKhachHang.soDienThoai, hd.idKhachHang.tenDem, hd.idKhachHang.ho  , hd.idKhachHang.ten , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan " +
            "order by hd.id desc")
    List<HoaDonCustom> findAllHDXN();

    @Query("SELECT hd.id as idHoaDon , hd.ma as maHoaDon,hd.idKhachHang.idDiaChi.xa as xa , hd.idKhachHang.idDiaChi.huyen as huyen , hd.idKhachHang.idDiaChi.diaChiCuThe as diaChiCuThe , hd.idKhachHang.ten as tenKhachHang, hd.idKhachHang.tenDem as tenDemKhachHang , hd.idKhachHang.ho as hoKhachHang , hd.idKhachHang.soDienThoai as soDienThoai, " +
            "hd.idKhachHang.idDiaChi.thanhPho as thanhPho , hd.trangThai as trangThai , hd.hinhThucThanhToan as hinhThucThanhToan , " +
            " hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM(hdct.gia) as tongTien from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id group by hd.id,hd.idKhachHang.idDiaChi.diaChiCuThe , hd.hinhThucThanhToan , hd.idKhachHang.idDiaChi.huyen , hd.idKhachHang.idDiaChi.xa, hd.ma,hd.idKhachHang.soDienThoai, hd.idKhachHang.tenDem, hd.idKhachHang.ho  , hd.idKhachHang.ten , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan " +
            "order by hd.id desc")
    List<HoaDonCustom> findAllHDXL();


    @Query("SELECT hd.tinhTrang as tinhTrang,  hd.id as idHoaDon, hd.update_by as nguoiXacNhan , hd.lyDo as lyDo , hd.tienShip as tienShip , hd.ma as maHoaDon,hd.idKhachHang.idDiaChi.xa as xa , hd.idKhachHang.idDiaChi.huyen as huyen , hd.idKhachHang.idDiaChi.diaChiCuThe as diaChiCuThe ,   hd.idKhachHang.ten as tenKhachHang, hd.idKhachHang.tenDem as tenDemKhachHang , hd.idKhachHang.ho as hoKhachHang , hd.idKhachHang.soDienThoai as soDienThoai," +
            "hd.idKhachHang.idDiaChi.thanhPho as thanhPho , hd.trangThai as trangThai , hd.hinhThucThanhToan as hinhThucThanhToan ," +
            "hd.tenNguoiNhan as tenNguoiNhan , hd.soDienThoai as soNguoiNhan , " +
            " hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien  from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "where (hd.ma like %:search% " +
            "or hd.idKhachHang.ten like %:search% " +
            "or hd.idNhanVien.ten like %:search% " +
            "or hd.idKhachHang.idDiaChi.thanhPho like %:search%) " +
            "and hd.trangThai = :trangThai" +
            " group by hd.id, hd.lyDo,hd.tenNguoiNhan,hd.tienShip, hd.soDienThoai,hd.idKhachHang.idDiaChi.diaChiCuThe , hd.idKhachHang.idDiaChi.huyen , hd.idKhachHang.idDiaChi.xa, hd.hinhThucThanhToan , hd.ma, hd.idNhanVien.ten , hd.idNhanVien.tenDem , hd.idNhanVien.ho ,hd.idKhachHang.soDienThoai, hd.idKhachHang.tenDem, hd.idKhachHang.ho  , hd.idKhachHang.ten , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan " +
            "order by hd.id desc")
    List<HoaDonCustom> timKiem(@Param("trangThai") Integer trangThai, @Param("search") String search);

    @Query("SELECT hd.tinhTrang as tinhTrang,  hd.id as idHoaDon, hd.update_by as nguoiXacNhan , hd.lyDo as lyDo , hd.tienShip as tienShip , hd.ma as maHoaDon,hd.idKhachHang.idDiaChi.xa as xa , hd.idKhachHang.idDiaChi.huyen as huyen , hd.idKhachHang.idDiaChi.diaChiCuThe as diaChiCuThe ,   hd.idKhachHang.ten as tenKhachHang, hd.idKhachHang.tenDem as tenDemKhachHang , hd.idKhachHang.ho as hoKhachHang , hd.idKhachHang.soDienThoai as soDienThoai," +
            "hd.idKhachHang.idDiaChi.thanhPho as thanhPho , hd.trangThai as trangThai , hd.hinhThucThanhToan as hinhThucThanhToan ," +
            "hd.tenNguoiNhan as tenNguoiNhan , hd.soDienThoai as soNguoiNhan , " +
            " hd.ngayTao as ngayTao , hd.update_at as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien  from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "where (hd.ma like %:search% " +
            "or hd.idKhachHang.ten like %:search% " +
            "or hd.idKhachHang.idDiaChi.thanhPho like %:search%) " +
            "and hd.trangThai = :trangThai" +
            " group by  hd.update_by ,hd.tinhTrang, hd.id,hd.lyDo,hd.tienShip,hd.idKhachHang.idDiaChi.diaChiCuThe, hd.tenNguoiNhan, hd.soDienThoai , hd.idKhachHang.idDiaChi.huyen , hd.idKhachHang.idDiaChi.xa, hd.ma, hd.idKhachHang.soDienThoai, hd.idKhachHang.tenDem, hd.idKhachHang.ho , hd.hinhThucThanhToan  , hd.idKhachHang.ten , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan " +
            "order by hd.id desc")
    List<HoaDonCustom> timKiemHuyHoan(@Param("trangThai") Integer trangThai, @Param("search") String search);

    @Query("SELECT  hd.id as idHoaDon , hd.tienShip as tienShip , hd.ma as maHoaDon,hd.idKhachHang.idDiaChi.xa as xa , hd.idKhachHang.idDiaChi.huyen as huyen , hd.idKhachHang.idDiaChi.diaChiCuThe as diaChiCuThe ,   hd.idKhachHang.ten as tenKhachHang, hd.idKhachHang.tenDem as tenDemKhachHang , hd.idKhachHang.ho as hoKhachHang , hd.idKhachHang.soDienThoai as soDienThoai," +
            "hd.idKhachHang.idDiaChi.thanhPho as thanhPho , hd.trangThai as trangThai , hd.hinhThucThanhToan as hinhThucThanhToan ," +
            "hd.tenNguoiNhan as tenNguoiNhan , hd.soDienThoai as soNguoiNhan , " +
            " hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien  from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "where (hd.ma like %:search% " +
            "or hd.idKhachHang.ten like %:search% " +
            "or hd.idKhachHang.email like %:search% )" +
            "and hd.trangThai = :trangThai" +
            " group by hd.id,hd.tienShip,hd.idKhachHang.idDiaChi.diaChiCuThe, hd.tenNguoiNhan, hd.soDienThoai , hd.idKhachHang.idDiaChi.huyen , hd.idKhachHang.idDiaChi.xa, hd.ma, hd.idKhachHang.soDienThoai, hd.idKhachHang.tenDem, hd.idKhachHang.ho , hd.hinhThucThanhToan  , hd.idKhachHang.ten , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan " +
            "order by hd.id desc")
    List<HoaDonCustom> choXacNhanTimKiem(@Param("trangThai") Integer trangThai, @Param("search") String search);

    @Query("SELECT hd.id as idHoaDon , hd.ma as maHoaDon,hd.idKhachHang.idDiaChi.xa as xa , hd.idKhachHang.idDiaChi.huyen as huyen , hd.idKhachHang.idDiaChi.diaChiCuThe as diaChiCuThe ,  hd.idKhachHang.ten as tenNguoiNhan, hd.idKhachHang.tenDem as tenDemKhachHang , hd.idKhachHang.ho as hoKhachHang , hd.idKhachHang.soDienThoai as soNguoiNhan" +
            ",hd.idKhachHang.idDiaChi.thanhPho as thanhPho , hd.trangThai as trangThai , hd.hinhThucThanhToan as hinhThucThanhToan , " +
            "hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "WHERE hd.ngayTao = :#{#x.ngayTaoSearch} and hd.trangThai = :#{#x.trangThaiSearch} " +
            "group by hd.id,hd.idKhachHang.idDiaChi.diaChiCuThe , hd.idKhachHang.idDiaChi.huyen ,  hd.hinhThucThanhToan , hd.idKhachHang.idDiaChi.xa, hd.ma, hd.idKhachHang.soDienThoai, hd.idKhachHang.tenDem, hd.idKhachHang.ho  , hd.idKhachHang.ten , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan")
    List<HoaDonCustom> timKiemNgay(@Param("x") HoaDonSearch x);

    @Query("SELECT hd.id as idHoaDon , hd.ma as maHoaDon,hd.idKhachHang.idDiaChi.xa as xa , hd.idKhachHang.idDiaChi.huyen as huyen , hd.idKhachHang.idDiaChi.diaChiCuThe as diaChiCuThe ,  hd.idKhachHang.ten as tenNguoiNhan, hd.idKhachHang.tenDem as tenDemKhachHang , hd.idKhachHang.ho as hoKhachHang , hd.idKhachHang.soDienThoai as soNguoiNhan" +
            ",hd.idKhachHang.idDiaChi.thanhPho as thanhPho , hd.trangThai as trangThai , hd.hinhThucThanhToan as hinhThucThanhToan , " +
            "hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan ,SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "WHERE (hd.ngayTao between :#{#x.ngayTaoSearch} and :#{#x.ngayTaoSearchs}) and hd.trangThai = :#{#x.trangThaiSearch} " +
            "group by hd.id,hd.idKhachHang.idDiaChi.diaChiCuThe , hd.idKhachHang.idDiaChi.huyen ,  hd.hinhThucThanhToan , hd.idKhachHang.idDiaChi.xa, hd.ma, hd.idKhachHang.soDienThoai, hd.idKhachHang.tenDem, hd.idKhachHang.ho  , hd.idKhachHang.ten , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan")
    List<HoaDonCustom> timKiemKhoangNgay(@Param("x") HoaDonSearch x);

    @Query("SELECT hd.id as idHoaDon , hd.ma as maHoaDon,hd.idKhachHang.idDiaChi.xa as xa , hd.idKhachHang.idDiaChi.huyen as huyen , hd.idKhachHang.idDiaChi.diaChiCuThe as diaChiCuThe ,hd.idNhanVien.ten , hd.idNhanVien.tenDem , hd.idNhanVien.ho ,  hd.idKhachHang.ten as tenNguoiNhan, hd.idKhachHang.tenDem as tenDemKhachHang , hd.idKhachHang.ho as hoKhachHang , hd.idKhachHang.soDienThoai as soNguoiNhan" +
            ",hd.idKhachHang.idDiaChi.thanhPho as thanhPho , hd.trangThai as trangThai , hd.hinhThucThanhToan as hinhThucThanhToan , " +
            "hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "WHERE hd.update_at = :#{#x.ngayTaoSearch} and hd.trangThai = :#{#x.trangThaiSearch} " +
            "group by hd.id,hd.idKhachHang.idDiaChi.diaChiCuThe , hd.idKhachHang.idDiaChi.huyen ,  hd.hinhThucThanhToan , hd.idKhachHang.idDiaChi.xa, hd.ma, hd.idNhanVien.ten , hd.idNhanVien.tenDem , hd.idNhanVien.ho ,hd.idKhachHang.soDienThoai, hd.idKhachHang.tenDem, hd.idKhachHang.ho  , hd.idKhachHang.ten , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan")
    List<HoaDonCustom> timKiemNgay1(@Param("x") HoaDonSearch x);

    @Query("SELECT hd.id as idHoaDon , hd.ma as maHoaDon,hd.idKhachHang.idDiaChi.xa as xa , hd.idKhachHang.idDiaChi.huyen as huyen , hd.idKhachHang.idDiaChi.diaChiCuThe as diaChiCuThe ,hd.idNhanVien.ten , hd.idNhanVien.tenDem , hd.idNhanVien.ho ,  hd.idKhachHang.ten as tenNguoiNhan, hd.idKhachHang.tenDem as tenDemKhachHang , hd.idKhachHang.ho as hoKhachHang , hd.idKhachHang.soDienThoai as soNguoiNhan" +
            ",hd.idKhachHang.idDiaChi.thanhPho as thanhPho , hd.trangThai as trangThai , hd.hinhThucThanhToan as hinhThucThanhToan , " +
            "hd.ngayTao as ngayTao , hd.ngayNhan as ngayNhan , SUM((hdct.gia * hdct.soLuong) - (hdct.giaSauKhuyenMai * hdct.soLuong)) as tongTien from HoaDon hd inner join HoaDonChiTiet hdct " +
            "on hd.id = hdct.idHoaDon.id " +
            "WHERE (hd.update_at between :#{#x.ngayTaoSearch} and :#{#x.ngayTaoSearchs}) and hd.trangThai = :#{#x.trangThaiSearch} " +
            "group by hd.id,hd.idKhachHang.idDiaChi.diaChiCuThe , hd.idKhachHang.idDiaChi.huyen ,  hd.hinhThucThanhToan , hd.idKhachHang.idDiaChi.xa, hd.ma, hd.idNhanVien.ten , hd.idNhanVien.tenDem , hd.idNhanVien.ho ,hd.idKhachHang.soDienThoai, hd.idKhachHang.tenDem, hd.idKhachHang.ho  , hd.idKhachHang.ten , hd.idKhachHang.idDiaChi.thanhPho , hd.trangThai , hd.ngayTao ,  hd.ngayNhan")
    List<HoaDonCustom> timKiemKhoangNgay1(@Param("x") HoaDonSearch x);

    @Query(value = "SELECT Top 1" +
            " hd.ma as maHoaDon , hd.id as idHoaDon FROM hoa_don hd" +
            " ORDER BY hd.id DESC", nativeQuery = true)
    HoaDonCustom findLast();

    @Query(value = "SELECT hd.trang_thai AS trangThai, SUM((hdct.gia * hdct.so_luong) - (hdct.gia_sau_khuyen_mai * hdct.so_luong)) AS tongTien, " +
            "SUM(hdct.so_luong) AS soLuong, hd.id AS IdHoaDon, hd.ngay_tao AS ngayTao, " +
            "hd.ly_do AS lyDo, hd.ngay_nhan AS ngayNhan, hd.update_at AS updateAt " +
            "FROM hoa_don hd " +
            "INNER JOIN hoa_don_chi_tiet hdct ON hdct.id_hoa_don = hd.id " +
            "INNER JOIN khach_hang kh ON hd.id_khach_hang = kh.id " +
            "WHERE kh.email = :email " +
            "GROUP BY hd.trang_thai, hd.id, hd.ngay_tao, hd.ly_do, hd.ngay_nhan, hd.update_at " +
            "ORDER BY hd.update_at DESC", nativeQuery = true)
    List<DonHangCustom> findAllHDByCustomerEmail(@Param("email") String email);

    @Query(value = "SELECT hd.trang_thai AS trangThai,SUM((hdct.gia * hdct.so_luong) - (hdct.gia_sau_khuyen_mai * hdct.so_luong)) AS tongTien," +
            "SUM(hdct.so_luong) AS soLuong, hd.id AS IdHoaDon, hd.ngay_tao AS ngayTao, " +
            "hd.ly_do AS lyDo, hd.ngay_nhan AS ngayNhan, hd.update_at AS updateAt " +
            "FROM hoa_don hd " +
            "INNER JOIN hoa_don_chi_tiet hdct ON hdct.id_hoa_don = hd.id " +
            "INNER JOIN khach_hang kh ON hd.id_khach_hang = kh.id " +
            "WHERE kh.email = :email " +
            "AND hd.trang_thai = :trangThai " +
            "GROUP BY hd.trang_thai, hd.id, hd.ngay_tao, hd.ly_do, hd.ngay_nhan, hd.update_at " +
            "ORDER BY hd.update_at DESC ", nativeQuery = true)
    List<DonHangCustom> findAllHDByEmailAndTrangThai(@Param("email") String email, @Param("trangThai") Integer trangThai);

    @Query(value = "SELECT hd.trang_thai AS trangThai,SUM((hdct.gia * hdct.so_luong) - (hdct.gia_sau_khuyen_mai * hdct.so_luong)) AS tongTien," +
            "SUM(hdct.so_luong) AS soLuong, hd.id AS IdHoaDon, hd.ngay_tao AS ngayTao, " +
            "hd.ly_do AS lyDo, hd.ngay_nhan AS ngayNhan, hd.update_at AS updateAt " +
            "FROM hoa_don hd " +
            "INNER JOIN hoa_don_chi_tiet hdct ON hdct.id_hoa_don = hd.id " +
            "INNER JOIN khach_hang kh ON hd.id_khach_hang = kh.id " +
            "WHERE kh.email = :email " +
            "AND hd.id = :id " +
            "GROUP BY hd.trang_thai, hd.id, hd.ngay_tao, hd.ly_do, hd.ngay_nhan, hd.update_at " +
            "ORDER BY hd.update_at DESC ", nativeQuery = true)
    List<DonHangCustom> findAllHDByEmailAndIdHoaDon(@Param("email") String email, @Param("id") Integer id);
}
