package com.example.demo.controller;

import com.example.demo.dto.sanpham.SanPhamRequest;
import com.example.demo.service.SanPhamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping ( "/san-pham" )
@CrossOrigin ( {"*"} )
public class SanPhamController {
    @Autowired
    private SanPhamService service;

//    @GetMapping ()
//    public ResponseEntity<?> getData(){
//        return ResponseEntity.ok(service.getData());
//    }

    @GetMapping ( "/getAll" )
    public ResponseEntity<?> getAll ( @RequestParam ( defaultValue = "0", name = "page" ) Integer page ) {
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping ( "/get-by/{id}" )
    public ResponseEntity<?> getById ( @PathVariable ( "id" ) Integer id ) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping ( "/get-by-ten" )
    public ResponseEntity<?> getById ( @RequestParam ( "ten" ) String ten ) {
        return ResponseEntity.ok(service.getByTen(ten));
    }

    @GetMapping ( "/get-chiet-khau/{id}" )
    public ResponseEntity<?> getChietKhau ( @PathVariable ( "id" ) Integer id ) {
        return ResponseEntity.ok(service.getChietKhau(id));
    }

    @GetMapping ( "/get-cbb-san-pham" )
    public ResponseEntity<?> getCbb () {
        return ResponseEntity.ok(service.getCbb());
    }

    @GetMapping ( "/get-cbb-khuyen-mai" )
    public ResponseEntity<?> getCbbKM () {
        return ResponseEntity.ok(service.getCbbKM());
    }

    @GetMapping ( "/danh-gia-find" )
    public ResponseEntity<?> dangGiaFind (@RequestParam ( defaultValue = "0", name = "page", required = false ) Integer page,
                                          @RequestParam (name = "by", required = false ) String by,
                                          @RequestParam (name = "order", required = false ) Integer order,
                                          @RequestParam (name = "name", required = false ) String name) {
        return ResponseEntity.ok(service.findByDG(page,name,by,order));
    }

    @GetMapping ( "/danh-gia-find-length" )
    public ResponseEntity<?> dangGiaFindLength (@RequestParam (name = "by", required = false ) String by,
                                                @RequestParam (name = "order", required = false ) Integer order,
                                                @RequestParam (name = "name", required = false ) String name) {
        return ResponseEntity.ok(service.findByDGLength(name,by,order));
    }

    @PostMapping ( "/add" )
    public ResponseEntity<?> add ( @RequestBody @Valid SanPhamRequest request , BindingResult result ) {

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return ResponseEntity.ok(errors);
        } else {
            return ResponseEntity.ok(service.add(request));
        }
    }

    @PutMapping ( "/update/{id}" )
    public ResponseEntity<?> update ( @PathVariable ( "id" ) int id , @RequestBody SanPhamRequest request ) {
        return ResponseEntity.ok(service.update(request , id));
    }

    @PutMapping("/updateKM")
    public ResponseEntity<?> updateKM(@RequestParam("list") List<Integer> list, @RequestParam("idKM") Integer idKM) {
        return ResponseEntity.ok(service.updateKM(list, idKM));
    }

    @DeleteMapping ( "/delete/{id}" )
    public ResponseEntity<?> delete ( @PathVariable ( "id" ) Integer id ) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping ( "/tim-kiem-ten" )
    public ResponseEntity<?> searchTen (@RequestParam ("ten") String ten) {
        return ResponseEntity.ok(service.searchTen(ten));
    }
}
