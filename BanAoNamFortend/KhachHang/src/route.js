var app = angular.module("myModule", ["ngRoute", "jkAngularRatingStars"]);
app.config(function ($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix("");
  $routeProvider
    .when("/", {
      templateUrl: "src/pages/trang-chu.html",
      controller: TrangChuConTroller,
    })
    .when("/trang-chu", {
      templateUrl: "src/pages/trang-chu.html",
      controller: TrangChuConTroller,
    })
    .when("/cua-hang", {
      templateUrl: "src/pages/cua-hang.html",
      controller: CuaHangConTroller,
    })
    .when("/ve-chung-toi", {
      templateUrl: "src/pages/ve-chung-toi.html",
      controller: VeChungToiConTroller,
    })
    .when("/xac-nhan", {
      templateUrl: "src/pages/xac-nhan-thanh-toan.html",
    })
    .when("/thanh-cong", {
      templateUrl: "src/pages/thanh-toan-thanh-cong.html",
    })
    .when("/cua-hang-khuyen-mai", {
      templateUrl: "src/pages/cua-hang-khuyen-mai.html",
      controller: CuaHangKhuyenMaiConTroller,
    })
    .when("/san-pham-moi", {
      templateUrl: "src/pages/san-pham-moi.html",
      controller: SanPhamMoiConTroller,
    })
    .when("/detail/:id", {
      templateUrl: "src/pages/detail.html",
      controller: DetailController,
    })
    .when("/tab-pane-1/:id", {
      templateUrl: "src/pages/detail.html",
      controller: DetailController,
    })
    .when("/tab-pane-3/:id", {
      templateUrl: "src/pages/detail-danh-gia.html",
      controller: DetailController,
    })
    .when("/don-hang", {
      templateUrl: "src/pages/don-hang.html",
      controller: tatCaController,
    })
    .when("/cho-thanh-toan", {
      templateUrl: "src/pages/don-hang/cho-thanh-toan.html",
      controller: choThanhToanController,
    })
    .when("/van-chuyen", {
      templateUrl: "src/pages/don-hang/van-chuyen.html",
      controller: vanChuyenController,
    })
    .when("/hoan-thanh", {
      templateUrl: "src/pages/don-hang/hoan-thanh.html",
      controller: hoanThanhController,
    })
    .when("/dang-giao", {
      templateUrl: "src/pages/don-hang/dang-giao.html",
      controller: dangGiaoController,
    })
    .when("/da-huy", {
      templateUrl: "src/pages/don-hang/da-huy.html",
      controller: daHuyController,
    })
    .when("/tra-hang", {
      templateUrl: "src/pages/don-hang/tra-hang.html",
      controller: traHangController,
    })
    .when("/tt-don-hang/:id", {
      templateUrl: "src/pages/don-hang/thong-tin-don-hang.html",
      controller: ThongTinController,
    })
    .when("/muaLai/:id", {
      templateUrl: "src/pages/gio-hang.html",
      controller: tatCaController,
    });
});

app.controller("indexController", function ($scope, $http) {
  $scope.khachHang = {
    id: "",
    ma: "",
    ho: "",
    tenDem: "",
    ten: "",
    soDienThoai: "",
    gioiTinh: "",
    email: "",
    matKhau: "",
    idDiaChi: "",
  };

  $scope.khachHangTempt = "";

  $scope.loadKhachHang = function () {
    console.log("Loading khach");
    $http.get(khachHangApi + "/load-khach-hang").then(function (response) {
      // $scope.khachHang = response.data;
      // let khachHangTempt = response.data;
      console.log("Loading khach trong func");
      $scope.khachHangTempt = response.data;
      $scope.khachHang.id = response.data.id;
      $scope.khachHang.ma = response.data.ma;
      $scope.khachHang.ho = response.data.ho;
      $scope.khachHang.tenDem = response.data.tenDem;
      $scope.khachHang.ten = response.data.ten;
      $scope.khachHang.soDienThoai = response.data.soDienThoai;
      $scope.khachHang.gioiTinh = response.data.gioiTinh;
      $scope.khachHang.email = response.data.email;

      console.log("LOL" + JSON.stringify($scope.khachHangTempt));
      localStorage.setItem("khachHang", $scope.khachHang.email);
      localStorage.setItem("AllKH", $scope.khachHang.id);
      console.log("Email: ", localStorage.getItem("khachHang"));
    });
  };
  $scope.loadKhachHang();

  $scope.logOut = function () {
    localStorage.removeItem("khachHang");
  };


  $scope.dangXuat = function () {
    $http
      .get(
        khachHangApi +
        "/login?email=null" +
        $scope.loginEmail +
        "&password=null" +
        $scope.loginPass
      )
      .then(function (response) {
        $scope.dtKH = response.data;
        console.log($scope.dtKH);
        localStorage.removeItem("khachHang");
        window.location.href = "/KhachHang/index.html";
      });
  };

  $scope.listKH = [];
  $scope.soLuong = 0;
  $scope.loadSoLuong = function () {
    $http.get(khachHangApi + "/so-luong").then(function (response) {
      $scope.listKH = response.data;
      for (var i = 0; i < $scope.listKH.length; i++) {
        $scope.soLuong = $scope.listKH[i].soLuongSanPhamInGioHang
      }
      console.log("Số lượng : ", $scope.soLuong);
    });
  };
  $scope.loadSoLuong();

  $scope.x = {
    id: "",
    ma: "",
    ho: "",
    tenDem: "",
    ten: "",
    soDienThoai: "",
    gioiTinh: "",
    email: "",
    matKhau: "",
    idDiaChi: "",
    trangThai: "",
  };

  $scope.idSua = "";
  $scope.idTempt = "";
  $scope.detail = function (event, id) {
    event.preventDefault();
    $http.get(khachHangApi + "/detail/" + id).then(function (response) {
      $scope.x.id = response.data.id;
      $scope.x.ma = response.data.ma;
      $scope.x.ho = response.data.ho;
      $scope.x.tenDem = response.data.tenDem;
      $scope.x.ten = response.data.ten;
      $scope.x.soDienThoai = response.data.soDienThoai;
      if (response.data.gioiTinh === false) {
        $scope.x.gioiTinh = 0;
      } else {
        $scope.x.gioiTinh = 1;
      }
      $scope.x.email = response.data.email;
      $scope.x.trangThai = response.data.trangThai;
      $scope.x.matKhau = response.data.matKhau;
      $scope.idSua = id;
      console.log("Id: " + JSON.stringify(response.data));
    });
  };

  $scope.update = function (event) {
    $scope.khachHang.id = $scope.x.id;
    $scope.khachHang.ma = $scope.x.ma;
    $scope.khachHang.ho = $scope.x.ho;
    $scope.khachHang.tenDem = $scope.x.tenDem;
    $scope.khachHang.ten = $scope.x.ten;
    $scope.khachHang.soDienThoai = $scope.x.soDienThoai;
    $scope.khachHang.gioiTinh = $scope.x.gioiTinh;
    $scope.khachHang.email = $scope.x.email;
    $scope.khachHang.matKhau = $scope.x.matKhau;
    $scope.khachHang.trangThai = $scope.x.trangThai;

    event.preventDefault();
    $http
      .put(khachHangApi + "/update/" + $scope.idSua, $scope.khachHang)
      .then(function (response) {
        console.log(response.data);
      });
  };
});
