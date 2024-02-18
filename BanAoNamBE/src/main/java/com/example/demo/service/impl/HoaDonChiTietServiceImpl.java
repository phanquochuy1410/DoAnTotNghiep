package com.example.demo.service.impl;

import com.example.demo.dto.HoaDonChiTiet.DonMuaKhachHang;
import com.example.demo.dto.HoaDonChiTiet.HoaDonChiTietRequest;
import com.example.demo.dto.hdct.HoaDonChiTietCustom;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.repositories.ChiTietSanPhamRepository;
import com.example.demo.repositories.HoaDonChiTietRepository;
import com.example.demo.repositories.HoaDonRepository;
import com.example.demo.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HoaDonChiTietRepository repo;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    public HoaDonChiTiet save(HoaDonChiTiet hoaDonChiTiet) {
        return repo.save(hoaDonChiTiet);
    }

    @Override
    public List<HoaDonChiTietCustom> findAllByIdHoaDon(Integer idHD) {
        return repo.listHDCT(idHD);
    }

    @Override
    public List<HoaDonChiTiet> findAllByIdHoaDonS(Integer idHD) {
        return repo.listHDCTS(idHD);
    }

    @Override
    public HoaDonChiTiet deleteSP(Integer id) {
        Optional<HoaDonChiTiet> hoaDonChiTiet = repo.findById(id);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(hoaDonChiTiet.get().getIdChiTietSanPham().getId()).orElse(null);
        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + hoaDonChiTiet.get().getSoLuong());
        chiTietSanPham.setUpdate_at(LocalDateTime.now());
        chiTietSanPham.setTrangThai(1);
        repo.delete(hoaDonChiTiet.get());
        return hoaDonChiTiet.get();
    }

    @Override
    public HoaDonChiTiet addToCart(HoaDonChiTietRequest request, Integer idHoaDon) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHoaDon);
        List<HoaDonChiTiet> list = repo.findAllByIdHoaDon(hoaDon.get());
        for (int i = 0; i < list.size(); i++) {
            if (request.getIdChiTietSanPham().equals(String.valueOf(list.get(i).getIdChiTietSanPham().getId()))) {
                HoaDonChiTiet hoaDonChiTietSS = request.map1(new HoaDonChiTiet());
                hoaDonChiTietSS.setId(list.get(i).getId());
                hoaDonChiTietSS.setSoLuong(Integer.valueOf(request.getSoLuong()) + list.get(i).getSoLuong());
                hoaDonChiTietSS.setGia(request.getThanhTien());
                hoaDonChiTietSS.setTrangThai(1);
                hoaDonChiTietSS.setGiaSauKhuyenMai(request.getKhuyenMai());
                updateSoLuongTon(request.getSoLuong(), Integer.valueOf(request.getIdChiTietSanPham()));
                hoaDonChiTietSS.setUpdate_at(LocalDateTime.now());
                hoaDonChiTietSS.setCreate_at(list.get(i).getCreate_at());
                hoaDonChiTietSS.setCreate_by(list.get(i).getCreate_by());
                return repo.save(hoaDonChiTietSS);
            }
        }
        HoaDonChiTiet hoaDonChiTietSS = request.map(new HoaDonChiTiet());
        hoaDonChiTietSS.setCreate_at(LocalDateTime.now());
        hoaDonChiTietSS.setTrangThai(1);
        updateSoLuongTon(1, Integer.valueOf(request.getIdChiTietSanPham()));
        return repo.save(hoaDonChiTietSS);
    }

    @Override
    public HoaDonChiTiet update(List<HoaDonChiTietRequest> chiTiets, List<Integer> soLuongs) {
        for (int i = 0; i < chiTiets.size(); i++) {
            Optional<HoaDonChiTiet> optional = repo.findById(Integer.valueOf(chiTiets.get(i).getIdHoaDonChiTiet()));
            HoaDonChiTiet hoaDonChiTiet = optional.get();
            setSLg(soLuongs.get(i), hoaDonChiTiet.getId(), Integer.valueOf(chiTiets.get(i).getIdChiTietSanPham()));
            hoaDonChiTiet.setSoLuong(soLuongs.get(i));
            hoaDonChiTiet.setGia(chiTiets.get(i).getThanhTien());
            hoaDonChiTiet.setUpdate_at(LocalDateTime.now());
            hoaDonChiTiet.setTrangThai(1);
            repo.save(hoaDonChiTiet);
        }
        return null;
    }

    @Override
    public List<DonMuaKhachHang> findSPByIdHD(Integer id) {
        return repo.findSPByIdHD(id);
    }

    @Override
    public HoaDonChiTiet updateTaiQuay ( HoaDonChiTietRequest hdct , Integer soLuongs ) {
        HoaDonChiTiet dbHdct = repo.findById(Integer.parseInt(hdct.getIdHoaDonChiTiet())).get();
        setSLg(soLuongs, dbHdct.getId(), Integer.valueOf(hdct.getIdChiTietSanPham()));
        dbHdct.setSoLuong(soLuongs);
        dbHdct.setGia(hdct.getThanhTien());
        dbHdct.setUpdate_at(LocalDateTime.now());
        repo.save(dbHdct);
        return null;
    }

    private void setSLg(Integer soLuong, Integer idHDCT, Integer idCTSP) {
        Optional<ChiTietSanPham> chiTietSanPham = chiTietSanPhamRepository.findById(idCTSP);
        Optional<HoaDonChiTiet> hoaDonChiTiet = repo.findById(idHDCT);
        Integer soLuongChange = soLuong - hoaDonChiTiet.get().getSoLuong();
        ChiTietSanPham chiTiet = new ChiTietSanPham();
        chiTiet.setId(idCTSP);
//        if (hoaDonChiTiet.get().getSoLuong() - soLuong > 0) {
//            chiTiet.setSoLuong(chiTietSanPham.get().getSoLuong() + (hoaDonChiTiet.get().getSoLuong() - soLuong));
//        } else {
//            chiTiet.setSoLuong(chiTietSanPham.get().getSoLuong() - (soLuong - hoaDonChiTiet.get().getSoLuong()));
//        }
        chiTiet.setSoLuong(chiTietSanPham.get().getSoLuong() - soLuongChange);
        chiTiet.setMa(chiTietSanPham.get().getMa());
        chiTiet.setAnh(chiTietSanPham.get().getAnh());
        chiTiet.setAnh2(chiTietSanPham.get().getAnh2());
        chiTiet.setAnh3(chiTietSanPham.get().getAnh3());
        chiTiet.setAnh4(chiTietSanPham.get().getAnh4());
        chiTiet.setAnh5(chiTietSanPham.get().getAnh5());
        chiTiet.setGiaKhuyenMai(chiTietSanPham.get().getGiaKhuyenMai());
        chiTiet.setMota(chiTietSanPham.get().getMota());
        chiTiet.setIdHang(chiTietSanPham.get().getIdHang());
        chiTiet.setIdMauSac(chiTietSanPham.get().getIdMauSac());
        chiTiet.setCreate_at(chiTietSanPham.get().getCreate_at());
        chiTiet.setCreate_by(chiTietSanPham.get().getCreate_by());
        chiTiet.setIdChatLieu(chiTietSanPham.get().getIdChatLieu());
        chiTiet.setIdKichCo(chiTietSanPham.get().getIdKichCo());
        chiTiet.setIdTheLoai(chiTietSanPham.get().getIdTheLoai());
        chiTiet.setIdSanPham(chiTietSanPham.get().getIdSanPham());
        chiTiet.setGia(chiTietSanPham.get().getGia());
        chiTiet.setTrangThai(1);
        chiTiet.setUpdate_at(LocalDateTime.now());
        chiTietSanPhamRepository.save(chiTiet);
    }


    public void updateSoLuongTon(Integer soLuong, Integer idChiTietSanPham) {
        Optional<ChiTietSanPham> chiTietSanPham = chiTietSanPhamRepository.findById(idChiTietSanPham);
        ChiTietSanPham chiTiet = new ChiTietSanPham();
        chiTiet.setSoLuong(chiTietSanPham.get().getSoLuong() - soLuong);
        chiTiet.setId(idChiTietSanPham);
        chiTiet.setIdHang(chiTietSanPham.get().getIdHang());
        chiTiet.setIdMauSac(chiTietSanPham.get().getIdMauSac());
        chiTiet.setCreate_at(chiTietSanPham.get().getCreate_at());
        chiTiet.setCreate_by(chiTietSanPham.get().getCreate_by());
        chiTiet.setIdChatLieu(chiTietSanPham.get().getIdChatLieu());
        chiTiet.setIdKichCo(chiTietSanPham.get().getIdKichCo());
        chiTiet.setIdTheLoai(chiTietSanPham.get().getIdTheLoai());
        chiTiet.setIdSanPham(chiTietSanPham.get().getIdSanPham());
        chiTiet.setGia(chiTietSanPham.get().getGia());
        chiTiet.setTrangThai(1);
        chiTiet.setUpdate_at(LocalDateTime.now());
        chiTiet.setMa(chiTietSanPham.get().getMa());
        chiTiet.setAnh(chiTietSanPham.get().getAnh());
        chiTiet.setAnh2(chiTietSanPham.get().getAnh2());
        chiTiet.setAnh3(chiTietSanPham.get().getAnh3());
        chiTiet.setAnh4(chiTietSanPham.get().getAnh4());
        chiTiet.setAnh5(chiTietSanPham.get().getAnh5());
        chiTiet.setGiaKhuyenMai(chiTietSanPham.get().getGiaKhuyenMai());
        chiTiet.setMota(chiTietSanPham.get().getMota());
        chiTietSanPhamRepository.save(chiTiet);
    }

}
