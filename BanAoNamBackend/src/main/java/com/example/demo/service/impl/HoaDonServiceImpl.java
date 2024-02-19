package com.example.demo.service.impl;

import com.example.demo.dto.HoaDonChiTiet.DonMuaKhachHang;
import com.example.demo.config.PdfInvoiceGenerator;
import com.example.demo.dto.diachi.DiaChiCustom;
import com.example.demo.dto.hoadon.DonHangCustom;
import com.example.demo.dto.hoadon.HoaDonCustom;
import com.example.demo.dto.hoadon.HoaDonRequest;
import com.example.demo.dto.hoadon.HoaDonSearch;
import com.example.demo.dto.khachhang.KhachHangCustom;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.DiaChi;
import com.example.demo.entity.HoaDon;
import com.example.demo.repositories.ChiTietSanPhamRepository;
import com.example.demo.repositories.DiaChiRepository;
import com.example.demo.repositories.HoaDonChiTietRepository;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.entity.NhanVien;
import com.example.demo.repositories.HoaDonChiTietRepository;
import com.example.demo.repositories.HoaDonRepository;
import com.example.demo.repositories.NhanVienRepository;
import com.example.demo.service.HoaDonService;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    @Autowired
    private HoaDonRepository repo;

    @Autowired
    private HoaDonChiTietRepository hdctRepo;

    @Autowired
    private NhanVienRepository repoNV;

    @Autowired
    private ChiTietSanPhamRepository repoCTSP;

    @Autowired
    private DiaChiRepository repoDC;


    @Override
    public HoaDon save(HoaDon hoaDon) {
        return repo.save(hoaDon);
    }

    public Page<HoaDonCustom> getPage(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 10);
        return repo.findDanhSachHoaDon(pageable);
    }

    @Override
    public List<HoaDonCustom> getAll() {
        return repo.findAllHD();
    }

    @Override
    public HoaDon updateHoaDon(HoaDonRequest request) {
        Optional<HoaDon> optional = repo.findById(request.getId());
        HoaDon hoaDon = optional.get();
        hoaDon.setTenNguoiNhan(request.getTenNguoiNhan());
        hoaDon.setSoDienThoai(request.getSoDienThoai());
        hoaDon.setTienShip(request.getShip());
        hoaDon.setGia(request.getTongTien());
        repo.save(hoaDon);
        DiaChiCustom diaChi = repoDC.getListDC(hoaDon.getIdKhachHang().getEmail());
        DiaChi diaChi1 = repoDC.findById(diaChi.getId()).get();
        diaChi1.setXa(request.getPhuong());
        diaChi1.setHuyen(request.getQuan());
        diaChi1.setThanhPho(request.getThanhPho());
        diaChi1.setDiaChiCuThe(request.getDiaChiCuThe());
        repoDC.save(diaChi1);
        return hoaDon;
    }


    @Override
    public List<HoaDonCustom> getAllHD() {
        return repo.findAllHDXN();
    }

    @Override
    public List<HoaDonCustom> getAllXL() {
        return repo.findAllHDXL();
    }

    @Override
    public List<DonHangCustom> findAllHDByEmail(String email) {
        return repo.findAllHDByCustomerEmail(email);
    }

    @Override
    public List<DonHangCustom> findAllHDByEmailAndTrangThai(String email, Integer trangThai) {
        return repo.findAllHDByEmailAndTrangThai(email, trangThai);
    }

    @Override
    public List<DonHangCustom> findAllHDByEmailAndIdHoaDon(String email, Integer id) {
        return repo.findAllHDByEmailAndIdHoaDon(email, id);
    }

    @Override
    public HoaDon updateDonHang(Integer id, Integer trangThai, String lyDo) {
        HoaDon hd = repo.findById(id).orElse(null);
        hd.setTrangThai(trangThai);
        hd.setLyDo(lyDo);
        hd.setUpdate_at(LocalDate.now());
        List<HoaDonChiTiet> hoaDonChiTiets = hdctRepo.findAllByIdHoaDon(hd);
        if (hd.getTrangThai() == 4) {
            hd.setUpdate_at(LocalDate.now());
            for (int j = 0; j < hoaDonChiTiets.size(); j++) {
                ChiTietSanPham chiTietSanPham = repoCTSP.findById(hoaDonChiTiets.get(j).getIdChiTietSanPham().getId()).get();
                chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + hoaDonChiTiets.get(j).getSoLuong());
                repoCTSP.save(chiTietSanPham);
            }
        }
        if (hd.getTrangThai() == 5) {
            hd.setUpdate_at(LocalDate.now());
            hd.setTinhTrang(0);
        }
        return repo.save(hd);
    }

    @Override
    public HoaDon xuLyHoanTra(Integer idHoaDon) {
        HoaDon hoaDon = repo.findById(idHoaDon).get();
        List<HoaDonChiTiet> hoaDonChiTiets = hdctRepo.findAllByIdHoaDon(hoaDon);
        for (int j = 0; j < hoaDonChiTiets.size(); j++) {
            ChiTietSanPham chiTietSanPham = repoCTSP.findById(hoaDonChiTiets.get(j).getIdChiTietSanPham().getId()).get();
            chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + hoaDonChiTiets.get(j).getSoLuong());
            chiTietSanPham.setUpdate_at(LocalDateTime.now());
            repoCTSP.save(chiTietSanPham);
        }
        hoaDon.setTinhTrang(1);
        return repo.save(hoaDon);
    }


    @Override
    public Page<HoaDonCustom> findHoaDonByTrangThai(Integer i, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 15);
        return repo.timKiemTrangThai(i, pageable);
    }

    @Override
    public Page<HoaDonCustom> findHoaDonHuyHoan(Integer i, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 15);
        return repo.timKiemTrangThaiHuyHoan(i, pageable);
    }

    @Override
    public Page<HoaDonCustom> findHoaDonXacNhan(Integer i, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 15);
        return repo.timKiemTrangThaiXacNhan(i, pageable);
    }

    @Override
    public List<HoaDonCustom> findHoaDonByMa(Integer trangThai, String ma) {
        return repo.timKiem(trangThai, ma);
    }

    @Override
    public List<HoaDonCustom> searchHuyHoan(Integer trangThai, String ma) {
        return repo.timKiemHuyHoan(trangThai, ma);
    }
    
    @Override
    public List<HoaDonCustom> findHoaDonChoXacNhanByMa(Integer trangThai, String ma) {
        return repo.choXacNhanTimKiem(trangThai, ma);
    }

    @Override
    public List<HoaDonCustom> timKiemNgay(HoaDonSearch hoaDonSearch) {
        return repo.timKiemNgay(hoaDonSearch);
    }

    @Override
    public List<HoaDonCustom> timKiemNgay1(HoaDonSearch hoaDonSearch) {
        return repo.timKiemNgay1(hoaDonSearch);
    }

    @Override
    public List<HoaDonCustom> timKiemKhoangNgay(HoaDonSearch hoaDonSearch) {
        return repo.timKiemKhoangNgay(hoaDonSearch);
    }

    @Override
    public List<HoaDonCustom> timKiemKhoangNgay1(HoaDonSearch hoaDonSearch) {
        return repo.timKiemKhoangNgay1(hoaDonSearch);
    }

    @Override
    public List<HoaDon> updateTrangThai(List<Integer> listId, Integer trangThai, String email, String lyDo) {
        NhanVien nhanVien = repoNV.findByEmail(email);
        try {
            List<HoaDon> hoaDons = repo.findAllById(listId);
            for (int i = 0; i < hoaDons.size(); i++) {
                hoaDons.get(i).setTrangThai(trangThai);
                hoaDons.get(i).setNgayNhan(LocalDate.now());
                hoaDons.get(i).setUpdate_at(LocalDate.now());
                hoaDons.get(i).setLyDo(lyDo);
                hoaDons.get(i).setIdNhanVien(nhanVien);
                if (trangThai == 4) {
                    hoaDons.get(i).setUpdate_by(nhanVien.getHo() + " " + nhanVien.getTenDem() + " " + nhanVien.getTen());
                    hoaDons.get(i).setIdNhanVien(nhanVien);
                    List<HoaDonChiTiet> hoaDonChiTiets = hdctRepo.findAllByIdHoaDon(hoaDons.get(i));
                    for (int j = 0; j < hoaDonChiTiets.size(); j++) {
                        ChiTietSanPham chiTietSanPham = repoCTSP.findById(hoaDonChiTiets.get(j).getIdChiTietSanPham().getId()).get();
                        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + hoaDonChiTiets.get(j).getSoLuong());
                        chiTietSanPham.setUpdate_at(LocalDateTime.now());
                        repoCTSP.save(chiTietSanPham);
                    }
                }
                if (trangThai == 2) {
                    hoaDons.get(i).setUpdate_by(nhanVien.getHo() + " " + nhanVien.getTenDem() + " " + nhanVien.getTen());
                    hoaDons.get(i).setIdNhanVien(nhanVien);
                    List<HoaDonChiTiet> hoaDonChiTiet = hdctRepo.findAllByIdHoaDon(hoaDons.get(i));
                    String downloadFolder = System.getProperty("user.home") + File.separator + "Downloads";
                    String filePath = downloadFolder + File.separator + hoaDons.get(i).getMa() + ".pdf";
                    PdfInvoiceGenerator.generateInvoice(filePath, hoaDons.get(i), hoaDonChiTiet);
                }

                if (trangThai == 5) {
                    hoaDons.get(i).setTinhTrang(1);
                    List<HoaDonChiTiet> hoaDonChiTiets = hdctRepo.findAllByIdHoaDon(hoaDons.get(i));
                    for (int j = 0; j < hoaDonChiTiets.size(); j++) {
                        ChiTietSanPham chiTietSanPham = repoCTSP.findById(hoaDonChiTiets.get(j).getIdChiTietSanPham().getId()).get();
                        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + hoaDonChiTiets.get(j).getSoLuong());
                        chiTietSanPham.setUpdate_at(LocalDateTime.now());
                        repoCTSP.save(chiTietSanPham);
                    }
                }
            }
            return repo.saveAll(hoaDons);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<HoaDonCustom> findAllHDDH() {
        return repo.findAllHDDH();
    }


}
