INSERT INTO san_pham(ma , ten) values
('SP1', N'Tanktop thể thao Dri-Breathe'),
('SP2', N'Tanktop thể thao Active'),
('SP3', N'Tanktop Gym Powerfit'),
('SP4', N'Áo thể thao dài tay Active'),
('SP5', N'Áo ba lỗ Basics V2'),
('SP6', N'Áo thể thao Active'),
('SP7', N'T-Shirt chạy bộ Care & Share'),
('SP8', N'T-Shirt chạy bộ Graphic Special'),
('SP9', N'Áo Sweatshirt thể thao Active'),
('SP10', N'T-Shirt thể thao Promax-S1'),
('SP11', N'T-Shirt thể thao Active'),
('SP12', N'Áo Polo dài tay Essentials'),
('SP13', N'Polo Ice Cooling'),
('SP14', N'Polo Pique Cotton'),
('SP15', N'Polo thể thao Promax-S1'),
('SP16', N'Polo Pique Cotton USA'),
('SP17', N'Polo Excool'),
('SP18', N'Polo Excool Woven'),
('SP19', N'Áo Polo Tay Dài Valentino Creations - V25-021072-B'),
('SP20', N'Áo Polo Tay Dài Valentino Creations - V25-021075-D'),
('SP21', N'Áo Thun Polo Tay Dài Valentino Creations - V25-021077-C-GY3'),
('SP22', N'Áo Thun Polo Tay Dài Valentino Creations - VLST-22001-C-GR3'),
('SP23', N'Áo Thun Polo Tay Dài Valentino Creations - VLST-22004-C-BR3'),
('SP24', N'Áo Thun Polo Tay Dài Valentino Creations - VLST-22006-A-BL6'),
('SP25', N'Áo Thun Tay Ngắn Valentino Creations - VSST-20021-A-BL8'),
('SP26', N'Áo Thun Polo Tay Ngắn Valentino Creations - VSST-22010-B-BL8'),
('SP27', N'Áo Thun Tay Ngắn Valentino Creations - VSST-23051-B-GY5'),
('SP28', N'Áo Thun Polo Tay Ngắn Valentino Creations - VSST-22009-A-BR5')


INSERT INTO the_loai (ma , ten) values
('TL1',N'Áo Polo'),
('TL2',N'Áo Thun'),
('TL3',N'Áo Thể Thao'),
('TL4',N'Áo Tanktop')

INSERT INTO mau_sac(ma , ten) values
('MS7',N'Cam'),
('MS8',N'Rêu'),
('MS1',N'Đen'),
('MS2',N'Trắng'),
('MS3',N'Vàng'),
('MS4',N'Xám'),
('MS5',N'Xanh'),
('MS6',N'Hồng'),
('MS9',N'Nâu')

INSERT INTO kich_co(ma , ten) values
('KC1',N'S'),
('KC2',N'M'),
('KC3',N'L'),
('KC4',N'XL'),
('KC5',N'XXL'),
('KC6',N'XXXL')

INSERT INTO chat_lieu(ma , ten) values
('CL1',N'Vải Excool'),
('CL2',N'Vải Polyester tính năng'),
('CL3',N'Vải Recycle'),
('CL4',N'Vải Cotton')

INSERT INTO hang(ma , ten) values
('H1',N'GUCCI'),
('H1',N'Lacoste'),
('H1',N'ADIDAS'),
('H1',N'TOMMY'),
('H1',N'H&M')

INSERT INTO nhan_vien(ma , ho , ten_dem , ten , so_dien_thoai , gioi_tinh , ngay_sinh , email , mat_khau , chuc_vu , dia_chi , trang_thai) values
('NV1',N'Phan' , N'Quốc' , N'Huy' , '0396850883',1,'2003-10-14','huypq@gmail.com','123',1,N'Nam Định',1),
('NV2',N'Đào' , N'Minh' , N'Hiền' , '0396850883',1,'2003-10-14','hiendm@gmail.com','123',1,N'Nam Định',1),
('NV3',N'Vũ' , N'Minh' , N'Long' , '0396850883',1,'2003-10-14','longvm@gmail.com','123',1,N'Nam Định',1),
('NV4',N'Lương' , N'Thịnh' , N'Hưng' , '0396850883',1,'2003-10-14','hunglt@gmail.com','123',1,N'Nam Định',1),
('NV5',N'Phạm' , N'Phú' , N'Tiên' , '0396850883',1,'2003-10-14','tienpp@gmail.com','123',1,N'Nam Định',1)

INSERT INTO khach_hang(ma , ho , ten_dem , ten , so_dien_thoai , gioi_tinh , email , mat_khau , trang_thai , id_dia_chi) values
('KH1',N'Phan' , N'Quốc' , N'Huy' , '0396850883',1,'huypq@gmail.com','123',1,1),
('KH2',N'Đào' , N'Minh' , N'Hiền' , '0396850883',1,'hiendm@gmail.com','123',1,2),
('KH3',N'Vũ' , N'Minh' , N'Long' , '0396850883',1,'longvm@gmail.com','123',1,1),
('KH4',N'Lương' , N'Thịnh' , N'Hưng' , '0396850883',1,'hunglt@gmail.com','123',1,2),
('KH5',N'Phạm' , N'Phú' , N'Tiên' , '0396850883',1,'tienpp@gmail.com','123',1,1)

INSERT INTO dia_chi(ma , xa , huyen , thanh_pho , dia_chi_cu_the ) values
('DC1',N'Hồng Thuận',N'Giao Thủy',N'Nam Định',N'Xóm 5'),
('DC2',N'Di Trạch',N'Hoài Đức',N'Hà Nội',N'Đan Khê')

INSERT INTO gio_hang(ma) values
('GH1')
INSERT INTO gio_hang_chi_tiet(id_gio_hang) values
(1)
INSERT INTO hoa_don(ma) values
('HD1')