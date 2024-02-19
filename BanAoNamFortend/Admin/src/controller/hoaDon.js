window.hoaDonController = function ($scope, $http, $routeParams, $location) {
  $scope.listHD = [];
  $scope.listHDCT = [];

  $scope.totalPages = 0;
  $scope.listPageNo = [];
  $scope.pageNo = 0;
  $scope.lLength = 0;
  $scope.loadData = function () {
    $http.get(hoaDonApi + "/get-page?page=" + $scope.pageNo).then(
      function (response) {
        $scope.listHD = response.data;
        for (var i = 0; i < response.data.totalPages; i++) {
          $scope.listPageNo.push(i);
        }
        $scope.totalPages = response.data.totalPages;
        $scope.listHD = response.data.content;
      },
      function (err) {
        console.log(err);
      }
    );
  };

  $scope.countHDHT = 0;
  $scope.lLength = 0;
  $scope.hoaDonHT = function () {
    $http.get(hoaDonApi + "/get-all").then(function (response) {
      $scope.listHDHT = response.data;
      $scope.listHDTC = response.data;
      $scope.lLength = $scope.listHDTC.length;
      for (var i = 0; i < $scope.listHDHT.length; i++) {
        if ($scope.listHDHT[i].trangThai == 1) {
          $scope.countHDHT++;
        }
      }
      return $scope.countHDHT;
    });
  };
  $scope.hoaDonHT();

  $scope.countHDDG = 0;
  $scope.hoaDonDG = function () {
    $http.get(hoaDonApi + "/get-all").then(function (response) {
      $scope.listHDDG = response.data;
      for (var i = 0; i < $scope.listHDDG.length; i++) {
        if ($scope.listHDDG[i].trangThai == 3) {
          $scope.countHDDG++;
        }
      }
      return $scope.countHDDG;
    });
  };
  $scope.hoaDonDG();

  $scope.countHDCG = 0;
  $scope.hoaDonCG = function () {
    $http.get(hoaDonApi + "/get-all").then(function (response) {
      $scope.listHDCG = response.data;
      for (var i = 0; i < $scope.listHDCG.length; i++) {
        if ($scope.listHDCG[i].trangThai == 2) {
          $scope.countHDCG++;
        }
      }
      return $scope.countHDCG;
    });
  };
  $scope.hoaDonCG();

  $scope.countHDH = 0;
  $scope.hoaDonH = function () {
    $http.get(hoaDonApi + "/get-all").then(function (response) {
      $scope.listHDH = response.data;
      for (var i = 0; i < $scope.listHDH.length; i++) {
        if ($scope.listHDH[i].trangThai == 4) {
          $scope.countHDH++;
        }
      }
      return $scope.countHDH;
    });
  };
  $scope.hoaDonH();

  $scope.loadData();
  $scope.x = {
    id: "",
    gia: "",
    ma: "",
    soDienThoai: "",
    phuongThuc: "",
    tenNguoiNhan: "",
    ngayTao: "",
    tenNguoiBan: "",
    thanhPho: "",
    quan: "",
    phuong: "",
    diaChiCuThe: "",
    phuongThuc: "",
  };

  $scope.loadData();

  $scope.detailHDCT = function (a) {
    console.log(a);
    $scope.listHDCT.splice(0, $scope.listHDCT.length);
    $http.get(hoaDonChiTietApi + "/detail?id=" + a).then(
      function (response) {
        $scope.listHDCT = response.data;
      },
      function (err) {
        console.log(err);
        console.log($scope.listHDCT);
      }
    );
  };

  $scope.searchNgay = "";
  $scope.timNgay = function () {
    $http
      .get(hoaDonApi + "/tim-ngay?ngayTaoSearch=" + $scope.searchNgay)
      .then(function (response) {
        $scope.listHD = response.data;
      });
  };

  $scope.countHDXN = 0;
  $scope.hoaDonXN = function () {
    $http.get(hoaDonApi + "/get-all").then(function (response) {
      $scope.listHDXN = response.data;
      for (var i = 0; i < $scope.listHDXN.length; i++) {
        if ($scope.listHDXN[i].trangThai == 0) {
          $scope.countHDXN++;
        }
      }
      return $scope.countHDXN;
    });
  };
  $scope.hoaDonXN();

  $scope.search = "";
  $scope.timKiem = function (event) {
    event.preventDefault();
    $http
      .get(hoaDonApi + "/tim-kiem?searchByMa=" + $scope.search)
      .then(function (response) {
        $scope.listHD = response.data;
        // console.log("Thành công ", response);
        $scope.search = "";
      });
  };
  $scope.detail = function (event, index) {
    event.preventDefault();
    let hd = $scope.listHD[index];
    $scope.x.id = hd.idHoaDon;
    $scope.x.ma = hd.maHoaDon;
    $scope.x.gia = hd.tongTien;
    $scope.x.soDienThoai = hd.soDienThoai;
    $scope.x.tenNguoiNhan =
      hd.hoKhachHang + " " + hd.tenDemKhachHang + " " + hd.tenKhachHang;
    $scope.x.ngayTao = hd.ngayTao;
    $scope.x.tenNguoiBan =
      hd.hoNhanVien + " " + hd.tenDemNhanVien + " " + hd.tenNhanVien;
    $scope.x.thanhPho = hd.thanhPho;
    console.log($scope.x.thanhPho);
    $scope.x.quan = hd.huyen;
    $scope.x.phuong = hd.xa;
    $scope.x.diaChiCuThe = hd.diaChiCuThe;
    $scope.vitriUpdate = index;
    $scope.x.hinhThucThanhToan = hd.hinhThucThanhToan;
    $scope.detailHDCT(hd.idHoaDon);
  };

   //Phân trang
   $scope.prevPage = function () {
    $scope.pageNo = 0;
    $scope.loadData();
  };

  $scope.prevPages = function () {
    if ($scope.pageNo < 1) {
      return alert("Đã hết hóa đơn");
    }
    $scope.pageNo--;
    $scope.loadData();
  };
  $scope.nextPages = function () {
    if ($scope.pageNo + 1 >= $scope.totalPages) {
      return alert("Đã hết hóa đơn");
    }
    $scope.pageNo++;
    $scope.loadData();
  };
  $scope.nextPage = function () {
    $scope.pageNo = $scope.totalPages - 1;
    $scope.loadData();
  };
};
