package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "chi_tiet_san_pham")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ChiTietSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "gia")
    private BigDecimal gia;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "gia_khuyen_mai")
    private BigDecimal giaKhuyenMai;

    @Column(name = "mo_ta")
    private String mota;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "anh")
    private String anh;

    @Column(name = "anh2")
    private String anh2;

    @Column(name = "anh3")
    private String anh3;

    @Column(name = "anh4")
    private String anh4;

    @Column(name = "anh5")
    private String anh5;

    @ManyToOne
    @JoinColumn(name = "id_san_pham" , referencedColumnName = "id")
    private SanPham idSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kich_co" , referencedColumnName = "id")
    private KichCo idKichCo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mau_sac" , referencedColumnName = "id")
    private MauSac idMauSac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hang" , referencedColumnName = "id")
    private Hang idHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_the_loai" , referencedColumnName = "id")
    private TheLoai idTheLoai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chat_lieu" , referencedColumnName = "id")
    private ChatLieu idChatLieu;

    @Column(name = "create_at")
    private LocalDateTime create_at;

    @Column(name = "update_at")
    private LocalDateTime update_at;

    @Column(name = "create_by")
    private String create_by;

    @Column(name = "update_by")
    private String update_by;


}
