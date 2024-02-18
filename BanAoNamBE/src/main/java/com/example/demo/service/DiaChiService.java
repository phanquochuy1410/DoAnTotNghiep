package com.example.demo.service;

import com.example.demo.dto.diachi.DiaChiCustom;
import com.example.demo.dto.diachi.DiaChiRequest;
import com.example.demo.entity.DiaChi;


public interface DiaChiService {

    DiaChi save(DiaChiRequest request);

    DiaChi update(DiaChiRequest request);

    DiaChiCustom getListDC();
}
