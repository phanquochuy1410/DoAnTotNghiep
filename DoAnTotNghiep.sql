CREATE DATABASE DoAnTotNghiep;
GO 
USE DoAnTotNghiep;

GO
CREATE TABLE san_pham(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	id_khuyen_mai INT NULL,
	ma VARCHAR(10) NOT NULL,
	ten NVARCHAR(max) NULL,
	create_at DATETIME NULL,
	update_at DATETIME NULL,
	create_by NVARCHAR(30),
	update_by NVARCHAR(30),
	CONSTRAINT FK_san_pham_khuyen_mai FOREIGN KEY(id_khuyen_mai) REFERENCES khuyen_mai(id)
);
GO
CREATE TABLE hang(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma VARCHAR(10) NOT NULL,
	ten NVARCHAR(30) NULL,
	create_at DATETIME NULL,
	update_at DATETIME NULL,
	create_by NVARCHAR(30),
	update_by NVARCHAR(30),
);
GO
CREATE TABLE mau_sac(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma VARCHAR(10) NOT NULL,
	ten NVARCHAR(30) NULL,
	create_at DATETIME NULL,
	update_at DATETIME NULL,
	create_by NVARCHAR(30),
	update_by NVARCHAR(30),
);
GO
CREATE TABLE kich_co(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma VARCHAR(10) NOT NULL,
	ten NVARCHAR(30) NULL,
	create_at DATETIME NULL,
	update_at DATETIME NULL,
	create_by NVARCHAR(30),
	update_by NVARCHAR(30)
);
GO
CREATE TABLE the_loai(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma VARCHAR(10) NOT NULL,
	ten NVARCHAR(30) NULL,
	create_at DATETIME NULL,
	update_at DATETIME NULL,
	create_by NVARCHAR(30),
	update_by NVARCHAR(30),
)
GO
CREATE TABLE chat_lieu(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma VARCHAR(10) NOT NULL,
	ten NVARCHAR(30) NULL,
	create_at DATETIME NULL,
	update_at DATETIME NULL,
	create_by NVARCHAR(30),
	update_by NVARCHAR(30),
);
GO
CREATE TABLE khuyen_mai(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma VARCHAR(10) NOT NULL,
	ten NVARCHAR(30) NULL,
	ngay_bat_dau DATETIME NULL,
	ngay_ket_thuc DATETIME NULL,
	chiet_khau INT NULL,
	trang_thai INT NULL,
	create_at DATETIME NULL,
	update_at DATETIME NULL,
	create_by NVARCHAR(30),
	update_by NVARCHAR(30),
);
GO
CREATE TABLE chi_tiet_san_pham(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	id_san_pham INT NULL,
	id_kich_co INT NULL,
	id_mau_sac INT NULL,
	id_hang INT NULL,
	id_the_loai INT NULL,
	id_chat_lieu INT NULL,
	anh NVARCHAR(MAX) NULL,
	ma NVARCHAR(30) NULL,
	gia MONEY NULL,
	so_luong INT NULL,
	gia_khuyen_mai MONEY NULL,
	mo_ta NVARCHAR(MAX),
	trang_thai INT NULL,
	create_at DATETIME NULL,
	update_at DATETIME NULL,
	create_by NVARCHAR(30),
	update_by NVARCHAR(30),
	CONSTRAINT FK_chi_tiet_san_pham FOREIGN KEY(id_san_pham) REFERENCES san_pham(id),
	CONSTRAINT FK_chi_tiet_kich_co FOREIGN KEY(id_kich_co) REFERENCES kich_co(id),
	CONSTRAINT FK_chi_tiet_mau_sac FOREIGN KEY(id_mau_sac) REFERENCES mau_sac(id),
	CONSTRAINT FK_chi_tiet_hang FOREIGN KEY(id_hang) REFERENCES hang(id),
	CONSTRAINT FK_chi_tiet_the_loai FOREIGN KEY(id_the_loai) REFERENCES the_loai(id),
	CONSTRAINT FK_chi_tiet_chat_lieu FOREIGN KEY(id_chat_lieu) REFERENCES chat_lieu(id),
);
alter table chi_tiet_san_pham add anh2 NVARCHAR(MAX) NULL
alter table chi_tiet_san_pham add anh3 NVARCHAR(MAX) NULL
alter table chi_tiet_san_pham add anh4 NVARCHAR(MAX) NULL
alter table chi_tiet_san_pham add anh5 NVARCHAR(MAX) NULL

GO
CREATE TABLE nhan_vien(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma VARCHAR(10) NOT NULL,
	ho NVARCHAR(10) NULL,
	ten_dem NVARCHAR(10),
	ten NVARCHAR(10) NULL,
	so_dien_thoai VARCHAR(11),
	gioi_tinh INT NULL,
	ngay_sinh DATE NULL,
	dia_chi NVARCHAR(100) NULL,
	email NVARCHAR(100) NULL,
	mat_khau NVARCHAR(100) NULL,
	chuc_vu INT NULL,
	trang_thai INT NULL,
	create_at DATETIME NULL,
	update_at DATETIME NULL,
	create_by NVARCHAR(30),
	update_by NVARCHAR(30),
);

GO
CREATE TABLE dia_chi(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma VARCHAR(10) NOT NULL,
	xa NVARCHAR(50) NULL,
	huyen NVARCHAR(50) NULL,
	thanh_pho NVARCHAR(50) NULL,
	dia_chi_cu_the NVARCHAR(MAX) NULL,
	create_at DATETIME NULL,
	update_at DATETIME NULL,
	create_by NVARCHAR(30),
	update_by NVARCHAR(30),
);

GO
CREATE TABLE khach_hang(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	id_dia_chi INT NULL,
	ma VARCHAR(10) NOT NULL,
	ho NVARCHAR(10) NULL,
	ten_dem NVARCHAR(10),
	ten NVARCHAR(10) NULL,
	so_dien_thoai VARCHAR(11),
	gioi_tinh INT NULL,
	email NVARCHAR(100) NULL,
	mat_khau NVARCHAR(100) NULL,
	trang_thai INT NULL,
	create_at DATETIME NULL,
	update_at DATETIME NULL,
	create_by NVARCHAR(30),
	update_by NVARCHAR(30),
	CONSTRAINT FK_khach_hang_dia_chi FOREIGN KEY(id_dia_chi) REFERENCES dia_chi(id)
);

GO
CREATE TABLE hoa_don(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	id_khach_hang INT NULL,
	id_nhan_vien INT NULL,
	ma VARCHAR(10) NOT NULL,
	gia MONEY NULL,
	ngay_tao DATETIME NULL,
	ngay_nhan DATETIME NULL,
	so_dien_thoai VARCHAR(11) NULL,
	ten_nguoi_nhan NVARCHAR(50) NULL,
	ly_do NVARCHAR(MAX),
	trang_thai INT NULL,
	create_at DATETIME NULL,
	update_at DATETIME NULL,
	create_by NVARCHAR(30),
	update_by NVARCHAR(30),
	CONSTRAINT FK_hoa_don_khach_hang FOREIGN KEY(id_khach_hang) REFERENCES khach_hang(id),
	CONSTRAINT FK_hoa_don_nhan_vien FOREIGN KEY(id_nhan_vien) REFERENCES nhan_vien(id)
);
alter table hoa_don add ly_do NVARCHAR(MAX) NULL
alter table hoa_don add hinh_thuc_thanh_toan int NULL
alter table hoa_don add tien_ship MONEY NULL

GO
CREATE TABLE hoa_don_chi_tiet(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	id_chi_tiet_san_pham INT NULL,
	id_hoa_don INT NULL,
	so_luong INT NULL,
	gia MONEY NULL,
	gia_sau_khuyen_mai MONEY NULL,
	trang_thai INT NULL,
	create_at DATETIME NULL,
	update_at DATETIME NULL,
	create_by NVARCHAR(30),
	update_by NVARCHAR(30),
	CONSTRAINT FK_chi_tiet_san_pham_chi_tiet_hoa_don FOREIGN KEY(id_chi_tiet_san_pham) REFERENCES chi_tiet_san_pham(id),
	CONSTRAINT FK_hoa_don_chi_tiet_hoa_don FOREIGN KEY(id_hoa_don) REFERENCES hoa_don(id)

);

GO
CREATE TABLE gio_hang(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	id_khach_hang INT NULL,
	ma VARCHAR(10) NOT NULL,
	ngay_tao DATETIME NULL,
	gia MONEY NULL,
	ngay_thanh_toan DATETIME NULL,
	trang_thai INT NULL,
	create_at DATETIME NULL,
	update_at DATETIME NULL,
	create_by NVARCHAR(30),
	update_by NVARCHAR(30),
	CONSTRAINT FK_khach_hang_gio_hang FOREIGN KEY(id_khach_hang) REFERENCES khach_hang(id)
);

GO
CREATE TABLE gio_hang_chi_tiet(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	id_chi_tiet_san_pham INT NULL,
	id_gio_hang INT NULL,
	so_luong INT NULL,
	gia MONEY NULL,
	trang_thai INT NULL,
	create_at DATETIME NULL,
	update_at DATETIME NULL,
	create_by NVARCHAR(30),
	update_by NVARCHAR(30),
	CONSTRAINT FK_chi_tiet_san_pham_gio_hang_chi_tiet FOREIGN KEY(id_chi_tiet_san_pham) REFERENCES chi_tiet_san_pham(id),
	CONSTRAINT FK_gio_hang_chi_tiet FOREIGN KEY(id_gio_hang) REFERENCES gio_hang(id)

);

GO
CREATE TABLE danh_gia_san_pham(
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	id_san_pham INT NULL,
	id_khach_hang INT NULL,
	noi_dung NVARCHAR(MAX) NULL,
	ngay_tao DATETIME NULL,
	ngay_sua DATETIME NULL,
	trang_thai INT NULL,
	so_sao INT NULL,
	CONSTRAINT FK_san_pham_danh_gia FOREIGN KEY(id_san_pham) REFERENCES san_pham(id),
	CONSTRAINT FK_khach_hang_danh_gia FOREIGN KEY(id_khach_hang) REFERENCES khach_hang(id),
);
GO
