package com.example.demo.dto.diachi;

import com.example.demo.entity.DiaChi;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaChiRequest {

    String thanhPho;

    String quan;

    String phuong;

    String dcct;

    Integer id;

    public DiaChi map(DiaChi dc) {
        dc.setDiaChiCuThe(this.dcct);
        dc.setHuyen(this.quan);
        dc.setXa(this.phuong);
        dc.setThanhPho(this.thanhPho);
        return dc;
    }

}
