package com.example.demo.controller;

import com.example.demo.dto.dgsp.DanhGiaSanPhamRequest;
import com.example.demo.service.DanhGiaSanPhamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/danh-gia-san-pham")
@CrossOrigin ( {"*"})
public class DanhGiaSanPhamController {
    @Autowired
    private DanhGiaSanPhamService service;

//    @GetMapping ()
//    public ResponseEntity<?> getElementRequired () {
//        return ResponseEntity.ok;
//    }

    @GetMapping ( "/get-all" )
    public ResponseEntity<?> getAllPageable ( @RequestParam ( defaultValue = "0", name = "page" ) Integer page ) {
        return ResponseEntity.ok(service.getAllPageble(page));
    }

    @GetMapping ( "/get-all-hien-thi" )
    public ResponseEntity<?> getHienThi( @RequestParam ( defaultValue = "0", name = "page" ) Integer page ) {
        return ResponseEntity.ok(service.getAllHienThi(page));
    }

    @GetMapping ( "/get-hien-thi-san-pham" )
    public ResponseEntity<?> getHienThiSp( @RequestParam ( defaultValue = "0", name = "page" ) Integer page, @RequestParam(name = "tenSp") String tenSp ) {
        return ResponseEntity.ok(service.getHienThiSp(page, tenSp));
    }

    @GetMapping ( "/get-hien-thi-san-pham-length")
    public ResponseEntity<?> getHienThiSpLength(@RequestParam(name = "tenSp") String tenSp ) {
        return ResponseEntity.ok(service.getHienThiSpLength(tenSp));
    }


    @GetMapping ( "/get-all-xu-ly" )
    public ResponseEntity<?> getXuLy( @RequestParam ( defaultValue = "0", name = "page" ) Integer page ) {
        return ResponseEntity.ok(service.getAllXuLy(page));
    }

    @GetMapping ( "/get-all-an" )
    public ResponseEntity<?> getAn( @RequestParam ( defaultValue = "0", name = "page" ) Integer page ) {
        return ResponseEntity.ok(service.getAllAn(page));
    }

    @GetMapping ( "/get-id-chua-mua" )
    public ResponseEntity<?> getIdChuaMua( @RequestParam Integer id ) {
        return ResponseEntity.ok(service.getIdChuaMua(id));
    }

    @GetMapping ( "/get-length-tt" )
    public ResponseEntity<?> getLengthTT( @RequestParam ( defaultValue = "0", name = "by" ) String by ) {
        return ResponseEntity.ok(service.getLength(by));
    }

    @GetMapping ( "/get-from-san-pham" )
    public ResponseEntity<?> getFromSanPham( @RequestParam ( defaultValue = "0", name = "id" ) Integer id,
                                             @RequestParam (defaultValue = "0", name = "by", required = false) Integer by) {
        return ResponseEntity.ok(service.getFromSp(by, id));
    }

    @GetMapping ( "/check-da-ton-tai-danh-gia" )
    public ResponseEntity<?> isExits( @RequestParam ( defaultValue = "0", name = "idKh" ) Integer id,
                                             @RequestParam (defaultValue = "0", name = "tenSp", required = false) String tenSp) {
        return ResponseEntity.ok(service.isExits(id, tenSp));
    }

    @PutMapping("/update-trang-thai")
    public ResponseEntity<?> updateTrangThai(@RequestParam("listID") List<Integer> idTT , @RequestParam("trangThai") Integer trangThai){
        return ResponseEntity.ok(service.updateTrangThai(idTT , trangThai));
    }

    @PostMapping ( "/add" )
    public ResponseEntity<?> add ( @Param ("ten") String ten,
                                   @RequestBody @Valid DanhGiaSanPhamRequest request , BindingResult result ) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return ResponseEntity.ok(errors);
        } else {
            return ResponseEntity.ok(service.add(request, ten));
        }
    }

    @PutMapping ( "/update/{id}" )
    public ResponseEntity<?> update ( @PathVariable ("id") String id , @RequestBody @Valid DanhGiaSanPhamRequest request , BindingResult result ) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return ResponseEntity.ok(errors);
        } else {
            return ResponseEntity.ok(service.update(request, Integer.parseInt(id)));
        }

    }

    @DeleteMapping ( "/delete/{id}" )
    public ResponseEntity<?> delete ( @PathVariable ( "id" ) Integer id ) {
        return ResponseEntity.ok(service.delete(id));
    }
}
