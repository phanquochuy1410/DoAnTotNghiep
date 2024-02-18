var app = angular.module("myModule", ["ngRoute"]);
app.config(function ($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix("");
  $routeProvider
    .when("/thong-ke", {
      templateUrl: "src/page/thong-ke.html",
      controller: ThongKeController,
    })
    .when("/thong-ke-ban-chay", {
      templateUrl: "src/page/thong-ke-all-ngay.html",
      controller: ThongKeBanChayController,
    })
    .when("/chatLieu", {
      templateUrl: "src/page/chat-lieu.html",
      controller: chatLieuController,
    })
    .when("/chi-tiet-san-pham", {
      templateUrl: "src/page/chi-tiet-san-pham.html",
      controller: chiTietSanPhamController,
    })
    .when("/update-form-chi-tiet-san-pham/:id", {
      templateUrl: "src/page/update-form-chi-tiet-san-pham.html",
      controller: formCTSP,
    })
    .when("/add-form-chi-tiet-san-pham/add", {
      templateUrl: "src/page/add-form-chi-tiet-san-pham.html",
      controller: formCTSP,
    })
    .when("/hang", {
      templateUrl: "src/page/hang.html",
      controller: hangController,
    })
    .when("/kichCo", {
      templateUrl: "src/page/kich-co.html",
      controller: kichCoController,
    })
    .when("/mauSac", {
      templateUrl: "src/page/mau-sac.html",
      controller: mauSacController,
    })
    .when("/theLoai", {
      templateUrl: "src/page/the-loai.html",
      controller: theLoaiController,
    })
    .when("/sanPham", {
      templateUrl: "src/page/san-pham.html",
      controller: sanPhamController,
    })
    .when("/khuyenMai", {
      templateUrl: "src/page/khuyen-mai.html",
      controller: khuyenMaiController,
    })
    .when("/nhan-vien", {
      templateUrl: "src/page/nhan-vien/nhan-vien.html",
      controller: nhanVienController,
    })
    .when("/nhan-vien-dl/detail/:id", {
      templateUrl: "src/page/nhan-vien/nhan-vien-detail-dl.html",
      controller: nhanVienController,
    })
    .when("/nhan-vien-nv/detail/:id", {
      templateUrl: "src/page/nhan-vien/nhan-vien-detail-nv.html",
      controller: nhanVienController,
    })
    .when("/khach-hang", {
      templateUrl: "src/page/khach-hang/khach-hang.html",
      controller: khachHangController,
    })
    .when("/khach-hang/detail/:id", {
      templateUrl: "src/page/khach-hang/khach-hang-detail.html",
      controller: khachHangController,
    })
    .when("/xuLy", {
      templateUrl: "src/page/hoa-don-da-xu-ly.html",
      controller: hoaDonXuLy,
    })
    .when("/choGiao", {
      templateUrl: "src/page/cho-giao-hoa-don.html",
      controller: hoaDonChoGiaoController,
    })
    .when("/dangGiao", {
      templateUrl: "src/page/hoa-don-dang-giao.html",
      controller: hoaDonDangGiaoController,
    })
    .when("/huy", {
      templateUrl: "src/page/hoa-don-huy.html",
      controller: hoaDonHuyController,
    })
    .when("/traHang", {
      templateUrl: "src/page/hoa-don-tra-hang.html",
      controller: hoaDonTraHangController,
    })
    .when("/donHang", {
      templateUrl: "src/page/hoa-don-cho-xac-nhan.html",
      controller: hoaDonChoXacNhanController,
    })
    .when("/hoanThanh", {
      templateUrl: "src/page/hoa-don-hoan-thanh.html",
      controller: hoaDonHoanThanhController,
    })
    .when("/thong-ke", {
      templateUrl: "src/page/thong-ke.html",
      controller: ThongKeController,
    })
    .when("/thong-ke-san-pham", {
      templateUrl: "src/page/thong-ke-san-pham.html",
      controller: ThongKeSanPhamController,
    })
    .when("/thong-ke-theo-ngay", {
      templateUrl: "src/page/thong-ke-theo-ngay.html",
      controller: thongKeNgayController,
    })
    .when("/thong-ke-theo-thang", {
      templateUrl: "src/page/thong-ke-theo-thang.html",
      controller: thongKeThangController,
    })
    .when("/thong-ke-theo-nam", {
      templateUrl: "src/page/thong-ke-nam.html",
      controller: thongKeNamController,
    })
    .when("/dgsp-xu-ly", {
      templateUrl: "src/page/dgsp-cho-xu-ly.html",
      controller: dgspXuLyController,
    })
    .when("/dgsp-hien-thi", {
      templateUrl: "src/page/dgsp-hien-thi.html",
      controller: dgspHienThiController,
    })
    .when("/dgsp-san-pham", {
      templateUrl: "src/page/dgsp-san-pham.html",
      controller: dgspSanPhamController,
    })
    .when("/dgsp-an", {
      templateUrl: "src/page/dgsp-an.html",
      controller: dgspAnController,
    })
    .when("/mua-tai-quay", {
      templateUrl: "src/page/mua-hang-tai-quay.html",
      controller: muaTaiQuayController,
    })

    .when("/trang-chu", {
      templateUrl: "src/page/trang-chu.html",
    })
    .when("/anh", {
      templateUrl: "src/page/anh.html",
      controller: anhController,
    })
    // .when("/detail/:id", {
    //   templateUrl: "src/page/chat-lieu.html",
    //   controller: chatLieuController,
    // });
    .otherwise({
      redirectTo: "/trang-chu",
    });
});

app.controller("indexController", function ($scope, $http) {
  $scope.nhanVien = {};

  $scope.loadNhanVien = function () {
    $http.get(nhanVienApi + "/load-nhan-vien").then(function (response) {
      $scope.nhanVien = response.data;
      console.log("Nhân viên ",$scope.nhanVien);
      localStorage.setItem("nhanVien", $scope.nhanVien.email);
    });
  };
  $scope.loadNhanVien();

  $scope.x = {};

  $scope.idSua = "";
  $scope.detail = function (event, id) {
    event.preventDefault();
    // console.log( $scope.idSua)
    $http.get(nhanVienApi + "/detail/" + id).then(function (response) {
      $scope.x = response.data;
      $scope.idSua = id;
      console.log($scope.x.id);
    });
  };
});
