package com.example.demo.service.impl;

import com.example.demo.dto.ctsp.ChiTietSanPhamCustom;
import com.example.demo.dto.ctsp.ChiTietSanPhamImport;
import com.example.demo.dto.ctsp.ChiTietSanPhamRequest;
import com.example.demo.dto.cuaHang.CuaHangCustum;
import com.example.demo.dto.cuaHang.SearchThuocTinh;
import com.example.demo.dto.kichco.KichCoCustom;
import com.example.demo.dto.mausac.MauSacCustom;
import com.example.demo.entity.ChatLieu;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.Hang;
import com.example.demo.entity.KichCo;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.SanPham;
import com.example.demo.entity.TheLoai;
import com.example.demo.repositories.ChatLieuRepository;
import com.example.demo.repositories.ChiTietSanPhamRepository;
import com.example.demo.repositories.HangRepository;
import com.example.demo.repositories.KichCoRepository;
import com.example.demo.repositories.MauSacRepository;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.repositories.TheLoaiRepository;
import com.example.demo.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

    @Autowired
    private ChiTietSanPhamRepository repo;

    @Autowired
    private SanPhamRepository sanPhamRepo;

    @Autowired
    private ChatLieuRepository chatLieuRepo;

    @Autowired
    private KichCoRepository kichCoRepo;

    @Autowired
    private HangRepository hangRepo;

    @Autowired
    private TheLoaiRepository theLoaiRepo;

    @Autowired
    private MauSacRepository mauSacRepo;


    @Override
    public List<ChiTietSanPham> getAll() {
        return repo.findAll();
    }

    @Override
    public List<ChiTietSanPhamCustom> getAllNoPage () {
        return repo.getAllNoPage();
    }

    @Override
    public List<ChiTietSanPhamCustom> getTop8 ( Integer page) {
        Pageable pageable = PageRequest.of(page , 4);
        return repo.getTop8(pageable);
    }

    @Override
    public List<ChiTietSanPhamCustom> getTop4New () {
        return repo.getTop4();
    }

    @Override
    public List<ChiTietSanPhamCustom> getKhuyenMai ( Integer page ) {
        Pageable pageable = PageRequest.of(page , 8);
        return repo.getKhuyenMai(pageable);
    }

    @Override
    public List<ChiTietSanPhamCustom> getAllKhuyenMai () {
        return repo.getAllKhuyenMai();
    }

    @Override
    public List<ChiTietSanPhamCustom> findByAll ( String str , Integer page, Integer min, Integer max, Integer tt, String by ) {
        Pageable pageable = PageRequest.of(page , 10);
        if(str == "" && (min == 0 || max == 0) && tt == -1 && by.equalsIgnoreCase("0")){
            return repo.getAll(pageable);
        }else{
            if(min == 0 && max == 0 && tt == -1){
                return repo.searchByString(str,pageable);
            }else{
                if(min == 0 && max == 0){
                    return repo.searchByRequireNoMinMax(str,tt,pageable);
                }
                else if(tt <= -1){
                    if(by.equalsIgnoreCase("Số lượng")){
                        return repo.searchBySoLuongRequireNoTT(str, min, max,pageable);
                    }else{
                        return repo.searchByGiaRequireNoTT(str,min,max,pageable);
                    }
                }else if(tt > -1){
                    if(by.equalsIgnoreCase("Số lượng")){
                        return repo.searchAllBySoLuong(str, tt,min, max,pageable);
                    }else{
                        return repo.searchAllByGia(str,tt,min,max,pageable);
                    }
                }
                else{
                    return repo.searchByString(str,pageable);
                }
            }
        }
    }

    @Override
    public List<ChiTietSanPhamCustom> findByAllLength ( String str , Integer min , Integer max , Integer tt , String by ) {
        if(str == "" && (min == 0 || max == 0) && tt == -1 && by.equalsIgnoreCase("0")){
            return repo.getAllNoPage();
        }
        if(min == 0 && max == 0 && tt == -1){
            return repo.searchByStringNoP(str);
        }else{
            if(min == 0 && max == 0){
                return repo.searchByRequireNoMinMaxNoP(str,tt);
            }
            else if(tt <= -1){
                if(by.equalsIgnoreCase("Số lượng")){
                    return repo.searchBySoLuongRequireNoTTNoP(str, min, max);
                }else{
                    return repo.searchByGiaRequireNoTTNoP(str,min,max);
                }
            }else if(tt > -1){
                if(by.equalsIgnoreCase("Số lượng")){
                    return repo.searchAllBySoLuongNoP(str, tt,min, max);
                }else{
                    return repo.searchAllByGiaNoP(str,tt,min,max);
                }
            }
            else{
                return repo.searchByStringNoP(str);
            }
        }
    }

    @Override
    public List<ChiTietSanPhamCustom> getAllPageble ( Integer page ) {
        Pageable pageable = PageRequest.of(page , 10);
//        repo.findAll().stream().forEach(e -> {
//            if(e.getSoLuong() <= 0){
//                e.setTrangThai(0);
////                e.setUpdate_at(LocalDateTime.now());
//                repo.save(e);
//            }
//            else{
//                e.setTrangThai(0);
//                e.setUpdate_at(LocalDateTime.now());
//                repo.save(e);
//            }
//        });
        return repo.getAll(pageable);
    }

    @Override
    public Optional<ChiTietSanPham> getById(Integer id) {
        Optional<ChiTietSanPham> optional = Optional.of(repo.findById(id)).orElse(null);
        return optional;
    }

    @Override
    public ChiTietSanPham getSLTon ( String tenSp , String tenKc , String tenMs ) {
        SanPham sp = sanPhamRepo.getSanPhamByTen(tenSp);
        KichCo kc = kichCoRepo.findByTen(tenKc);
        MauSac ms = mauSacRepo.findByTen(tenMs);

        return repo.findChiTietSanPhamByIdSanPhamAndIdMauSacAndIdKichCo(sp, ms, kc);
    }

    @Override
    public ChiTietSanPham add ( ChiTietSanPhamRequest request ) {
        ChiTietSanPham checkTrung = checkTrung(request);
        if(checkTrung != null){
            ChiTietSanPham chiTietSanPham = repo.findById(checkTrung.getId()).get();
            Integer soLuongBanDau = chiTietSanPham.getSoLuong();
            chiTietSanPham.setSoLuong(soLuongBanDau + Integer.parseInt(request.getSoLuong()));
            chiTietSanPham.setUpdate_at(LocalDateTime.now());
            return repo.save(chiTietSanPham);
        }else{
            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
            chiTietSanPham.setMa(genMa());
            ChiTietSanPham ctsp = request.map(chiTietSanPham);
            return repo.save(ctsp);
        }
    }

    @Override
    public List<ChiTietSanPham> addAll ( List<ChiTietSanPhamImport> listRequest ) {
        List<ChiTietSanPham> listContain = new ArrayList<>();
        for (ChiTietSanPhamImport request : listRequest) {
            ChiTietSanPham checkTrungImport = checkTrungImport(request);
            if(checkTrungImport != null){
                ChiTietSanPham chiTietSanPham = repo.findById(checkTrungImport.getId()).get();
                Integer soLuongBanDau = chiTietSanPham.getSoLuong();
                chiTietSanPham.setSoLuong(soLuongBanDau + Integer.parseInt(request.getSoLuong()));
                chiTietSanPham.setUpdate_at(LocalDateTime.now());
                repo.save(chiTietSanPham);
            }else{
                SanPham sanPham = sanPhamRepo.getSanPhamByTen(request.getSanPham());
                KichCo kichCo = kichCoRepo.findByTen(request.getKichCo());
                TheLoai theLoai = theLoaiRepo.findByTen(request.getTheLoai());
                MauSac mauSac = mauSacRepo.findByTen(request.getMauSac());
                Hang hang = hangRepo.findByTen(request.getHang());
                ChatLieu chatLieu = chatLieuRepo.findByTen(request.getChatLieu());

                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setMa(genMa());
                ctsp.setGia(BigDecimal.valueOf(Double.parseDouble(request.getGia())));
                ctsp.setSoLuong(Integer.valueOf(request.getSoLuong()));
                ctsp.setGiaKhuyenMai(BigDecimal.ZERO);
                ctsp.setMota(request.getMoTa());
                ctsp.setTrangThai(1);
                ctsp.setAnh(request.getAnh());
                ctsp.setAnh2(request.getAnh2());
                ctsp.setAnh3(request.getAnh3());
                ctsp.setAnh4(request.getAnh4());
                ctsp.setAnh5(request.getAnh5());
                ctsp.setIdSanPham(sanPham);
                ctsp.setIdKichCo(kichCo);
                ctsp.setIdMauSac(mauSac);
                ctsp.setIdChatLieu(chatLieu);
                ctsp.setIdHang(hang);
                ctsp.setIdTheLoai(theLoai);
                ctsp.setCreate_at(LocalDateTime.now());
                ctsp.setUpdate_at(LocalDateTime.now());
                listContain.add(ctsp);
                repo.save(ctsp);
            }
        }
        return listContain;
    }

    @Override
    public List<ChiTietSanPham> addManyVariable ( List<ChiTietSanPhamRequest> listRequest ) {
        List<ChiTietSanPham> listContain = new ArrayList<>();
        for (ChiTietSanPhamRequest request : listRequest) {
            ChiTietSanPham checkTrung = checkTrung(request);
            if(checkTrung != null){
                ChiTietSanPham chiTietSanPham = repo.findById(checkTrung.getId()).get();
                Integer soLuongBanDau = chiTietSanPham.getSoLuong();
                chiTietSanPham.setSoLuong(soLuongBanDau + Integer.parseInt(request.getSoLuong()));
                chiTietSanPham.setUpdate_at(LocalDateTime.now());
                repo.save(chiTietSanPham);
            }else{
                SanPham sanPham = sanPhamRepo.findById(Integer.parseInt(request.getIdSanPham())).orElse(null);
                KichCo kichCo = kichCoRepo.findById(Integer.parseInt(request.getIdKichCo())).orElse(null);
                TheLoai theLoai = theLoaiRepo.findById(Integer.parseInt(request.getIdTheLoai())).orElse(null);
                MauSac mauSac = mauSacRepo.findById(Integer.parseInt(request.getIdMauSac())).orElse(null);
                Hang hang = hangRepo.findById(Integer.parseInt(request.getIdHang())).orElse(null);
                ChatLieu chatLieu = chatLieuRepo.findById(Integer.parseInt(request.getIdChatLieu())).orElse(null);

                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setMa(genMa());
                ctsp.setGia(BigDecimal.valueOf(Double.parseDouble(request.getGia())));
                ctsp.setSoLuong(Integer.valueOf(request.getSoLuong()));
                ctsp.setGiaKhuyenMai(BigDecimal.valueOf(Double.valueOf(request.getGiaKhuyenMai())));
                ctsp.setMota(request.getMota());
                ctsp.setTrangThai(1);
                ctsp.setAnh(request.getAnh());
                ctsp.setAnh2(request.getAnh2());
                ctsp.setAnh3(request.getAnh3());
                ctsp.setAnh4(request.getAnh4());
                ctsp.setAnh5(request.getAnh5());
                ctsp.setIdSanPham(sanPham);
                ctsp.setIdKichCo(kichCo);
                ctsp.setIdMauSac(mauSac);
                ctsp.setIdChatLieu(chatLieu);
                ctsp.setIdHang(hang);
                ctsp.setIdTheLoai(theLoai);
                ctsp.setCreate_at(LocalDateTime.now());
                ctsp.setUpdate_at(LocalDateTime.now());
                listContain.add(ctsp);
                repo.save(ctsp);
            }
        }
        return listContain;
    }

    @Override
    public ChiTietSanPham update(ChiTietSanPhamRequest request, Integer id) {
        ChiTietSanPham optional = repo.findById(id).orElse(null);
        if(validateAnhUpdate(request.getAnh())){
            optional.setAnh(request.getAnh());
        }
        if(validateAnhUpdate(request.getAnh2())){
            optional.setAnh2(request.getAnh2());
        }
        if(validateAnhUpdate(request.getAnh3())){
            optional.setAnh3(request.getAnh3());
        }
        if(validateAnhUpdate(request.getAnh4())){
            optional.setAnh4(request.getAnh4());
        }
        if(validateAnhUpdate(request.getAnh5())){
            optional.setAnh5(request.getAnh5());
        }
        optional.setGia(BigDecimal.valueOf(Double.parseDouble(request.getGia())));
        optional.setSoLuong(Integer.valueOf(request.getSoLuong()));
        optional.setGiaKhuyenMai(BigDecimal.valueOf(Double.parseDouble(request.getGiaKhuyenMai())));
        optional.setMota(request.getMota());
        if(Integer.parseInt(request.getSoLuong()) > 0){
            optional.setTrangThai(1);
        }
        optional.setTrangThai(Integer.valueOf(request.getTrangThai()));
        optional.setIdChatLieu(ChatLieu.builder().id(Integer.parseInt(request.getIdChatLieu())).build());
        optional.setIdSanPham(SanPham.builder().id(Integer.parseInt(request.getIdSanPham())).build());
        optional.setIdKichCo(KichCo.builder().id(Integer.parseInt(request.getIdKichCo())).build());
        optional.setIdMauSac(MauSac.builder().id(Integer.parseInt(request.getIdMauSac())).build());
        optional.setIdHang(Hang.builder().id(Integer.parseInt(request.getIdHang())).build());
        optional.setIdTheLoai(TheLoai.builder().id(Integer.parseInt(request.getIdTheLoai())).build());
        optional.setUpdate_at(LocalDateTime.now());
        return repo.save(optional);
    }

    @Override
    public ChiTietSanPham updateTrangThai ( Integer id, Integer tt ) {
        Optional<ChiTietSanPham> optional = Optional.of(repo.findById(id)).orElse(null);
        return optional.map(o -> {
            o.setTrangThai(tt);
            o.setUpdate_at(LocalDateTime.now());
            return repo.save(o);
        }).orElse(null);
    }

    private String genMa(){
        String maCV = " ";
        String s1 = "CTSP";

        for (int i = 1; i < repo.findAll().size() +2; i++) {
            maCV = s1 +i;
        }
        return maCV;
    }

    private Boolean validateAnhUpdate(String tenAnh){
        if(tenAnh == null || tenAnh == ""){
            return false;
        }
        return true;
    }

    private ChiTietSanPham checkTrung(ChiTietSanPhamRequest request){
        ChiTietSanPham ctsp = repo.findChiTietSanPhamByIdSanPhamAndIdMauSacAndIdKichCo(
                SanPham.builder().id(Integer.parseInt(request.getIdSanPham())).build(),
                MauSac.builder().id(Integer.parseInt(request.getIdMauSac())).build(),
                KichCo.builder().id(Integer.parseInt(request.getIdKichCo())).build()
        );
        if(ctsp == null){
            return null;
        }
        return ctsp;
    }

    private ChiTietSanPham checkTrungImport(ChiTietSanPhamImport request){
        SanPham sp = sanPhamRepo.getSanPhamByTen(request.getSanPham());
        MauSac ms = mauSacRepo.findByTen(request.getMauSac());
        KichCo kc = kichCoRepo.findByTen(request.getKichCo());
        ChiTietSanPham ctsp = repo.findChiTietSanPhamByIdSanPhamAndIdMauSacAndIdKichCo(
                sp,
                ms,
                kc
        );
        if(ctsp == null){
            return null;
        }
        return ctsp;
    }

//    private Boolean checkTrung(ChiTietSanPhamRequest request){
//        for (ChiTietSanPham ctsp: repo.findAll()) {
//            if(ctsp.getIdSanPham().getId() == Integer.parseInt(request.getIdSanPham())
//                    && ctsp.getIdMauSac().getId() == Integer.parseInt(request.getIdMauSac())
//                    && ctsp.getIdKichCo().getId() == Integer.parseInt(request.getIdKichCo())
//                    && ctsp.getIdChatLieu().getId() == Integer.parseInt(request.getIdChatLieu())
//            ){
//                System.out.println(ctsp.toString());
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    public ChiTietSanPham delete(Integer id) {
        Optional<ChiTietSanPham> optional = Optional.of(repo.findById(id)).orElse(null);
        return optional.map(o -> {
            repo.delete(o);
            return o;
        }).orElse(null);
    }

    @Override
    public List<MauSacCustom> getMauSacCTSP ( String tsp ) {
        return repo.getMauSacCTSP(tsp);
    }

    @Override
    public List<KichCoCustom> getKichCoCTSP ( String tsp , String tms ) {
        return repo.getSizeCTSP(tsp, tms);
    }

    @Override
    public List<Integer> getSoLuongTon ( List<Integer> listId ) {
        List<Integer> soLuongTon = new ArrayList<>();
        for (Integer id : listId) {
           ChiTietSanPham ctsp = repo.findById(id).get();
           soLuongTon.add(ctsp.getSoLuong());
        }
        return soLuongTon;
    }


    @Override
    public List<ChiTietSanPhamCustom> searchListSP ( String ten ) {
        return repo.searchSP(ten);
    }

    @Override
    public List<CuaHangCustum> hienThiSanPhamKhuyenMai(Integer page) {
        Pageable pageable = PageRequest.of(page , 8);
        return repo.hienThiSanPhamKhuyenMai(pageable);
    }

    @Override
    public ChiTietSanPhamCustom getAllAnh ( String tenSP , String ms ) {
        return repo.getAllAnh(tenSP, ms);
    }

    @Override
    public ChiTietSanPhamCustom getChangeDetail ( String tenSP , Integer ms , Integer kc ) {
        return repo.getChangeDetail(tenSP, ms, kc);
    }

    @Override
    public List<ChiTietSanPhamCustom> ctspSearch ( SearchThuocTinh x , Integer page ) {
        Pageable pageable = PageRequest.of(page , 10);
        repo.findAll().stream().forEach(e -> {
            if(e.getSoLuong() <= 0) {
                e.setTrangThai(0);
                e.setUpdate_at(LocalDateTime.now());
                repo.save(e);
            }
        });
        return repo.ctspSearch(x, pageable);
    }

    @Override
    public Integer ctspSearchLength ( SearchThuocTinh x ) {
        return repo.ctspSearchLength(x);
    }
}
