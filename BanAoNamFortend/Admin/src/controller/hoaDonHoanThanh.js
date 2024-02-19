window.hoaDonHoanThanhController = function (
  $scope,
  $http,
  $routeParams,
  $location, $timeout,
  $filter
) {
  $scope.listHD = [];
  $scope.listHDHT = [];
  $scope.listHDCT = [];

  $scope.x = {
    id: "",
    gia: "",
    ma: "",
    soDienThoai: "",
    tenNguoiNhan: "",
    tenNguoiDat: "",
    soNguoiDat: "",
    ngayTao: "",
    tenNguoiBan: "",
    thanhPho: "",
    ship: "",
    quan: "",
    phuong: "",
    phuongThuc: "",
    diaChiCuThe: "",
    phuongThuc: "",
  };

  $scope.totalPages = 0;
  $scope.listPageNo = [];
  $scope.pageNo = 0;

  $scope.loadSearchTT = function () {
    $scope.listHD.splice(0, $scope.listHD.length);
    $http
      .get(
        hoaDonApi + "/search-trang-thai?trangThaiS=1&pageNo=" + $scope.pageNo
      )
      .then(
        function (response) {
          $scope.listHD = response.data;
          for (var i = 0; i < response.data.totalPages; i++) {
            $scope.listPageNo.push(i);
          }
          $scope.totalPages = response.data.totalPages;
          $scope.listHD = response.data.content;
          console.log($scope.listHD);
        },
        function (err) {
          console.log(err);
        }
      );
  };
  $scope.loadSearchTT();

  $scope.prevPage = function () {
    $scope.pageNo = 0;
    $scope.loadSearchTT();
  };

  $scope.prevPages = function () {
    if ($scope.pageNo < 1) {
      return alert("Page không được < 0");
    }
    $scope.pageNo--;
    $scope.loadSearchTT();
  };
  $scope.nextPages = function () {
    if ($scope.pageNo + 1 >= $scope.totalPages) {
      return alert("Page không được > " + $scope.totalPages);
    }
    $scope.pageNo++;
    $scope.loadSearchTT();
  };
  $scope.nextPage = function () {
    $scope.pageNo = $scope.totalPages - 1;
    $scope.loadSearchTT();
  };

  $scope.lLength;
  $scope.countHDXL = 0;
  $scope.hoaDonXL = function () {
    $http.get(hoaDonApi + "/get-all-xu-ly").then(function (response) {
      $scope.listHDTC = response.data;
      $scope.lLength = $scope.listHDTC.length;
      for (var i = 0; i < $scope.listHDTC.length; i++) {
        if ($scope.listHDTC[i].trangThai == 6) {
          $scope.countHDXL++;
        }
      }
      return $scope.countHDXL;
    });
  };
  $scope.hoaDonXL();

  $scope.lLength;
  $scope.countHDTH = 0;
  $scope.hoaDonTH = function () {
    $http.get(hoaDonApi + "/get-all").then(function (response) {
      $scope.listHDTH = response.data;
      $scope.lLength = $scope.listHDTH.length;
      for (var i = 0; i < $scope.listHDTH.length; i++) {
        if ($scope.listHDTH[i].trangThai == 5) {
          $scope.countHDTH++;
        }
      }
      return $scope.countHDTH;
    });
  };
  $scope.hoaDonTH();

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
  $scope.lLength;
  $scope.countHDHT = 0;
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

  $scope.countHDXN = 0;
  $scope.hoaDonXN = function () {
    $http.get(hoaDonApi + "/get-all-xac-nhan").then(function (response) {
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
    $http.get(hoaDonApi + "/get-all-huy").then(function (response) {
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

  $scope.search = "";

  $scope.timKiem = function () {
    $http
      .get(hoaDonApi + "/tim-kiem?trangThai=1&searchByMa=" + $scope.search)
      .then(function (response) {
        $scope.listHD = response.data;
        console.log("Thành công ", response);
      });
  };

  $scope.searchNgay = "";
  $scope.timNgay = function () {
    $http
      .get(
        hoaDonApi +
        "/tim-ngay1?trangThaiSearch=1&ngayTaoSearch=" +
        $scope.searchNgay
      )
      .then(function (response) {
        $scope.listHD = response.data;
        console.log(response.data);
      });
  };

  $scope.batDau = "";
  $scope.ketThuc = "";

  $scope.timKhoangNgay = function () {
    $http
      .get(
        hoaDonApi +
        "/tim-khoang-ngay1?trangThaiSearch=1&ngayTaoSearch=" +
        $scope.batDau +
        "&ngayTaoSearchs=" +
        $scope.ketThuc
      )
      .then(function (response) {
        $scope.listHD = response.data;
        console.log(response.data);
      });
  };

  $scope.detail = function (event, index) {
    event.preventDefault();
    let hd = $scope.listHD[index];
    $scope.x.id = hd.idHoaDon;
    $scope.x.ma = hd.maHoaDon;
    $scope.x.gia = hd.tongTien;
    $scope.x.soNguoiDat = hd.soDienThoai;
    $scope.x.tenNguoiNhan = hd.tenNguoiNhan;
    $scope.x.soDienThoai = hd.soNguoiNhan;
    $scope.x.tenNguoiDat =
      hd.hoKhachHang + " " + hd.tenDemKhachHang + " " + hd.tenKhachHang;
    $scope.x.ngayTao = hd.ngayTao;
    $scope.x.tenNguoiBan =
      hd.hoNhanVien + " " + hd.tenDemNhanVien + " " + hd.tenNhanVien;
    $scope.x.thanhPho = hd.thanhPho;
    $scope.x.quan = hd.huyen;
    $scope.x.phuong = hd.xa;
    $scope.x.ship = hd.tienShip;
    $scope.x.diaChiCuThe = hd.diaChiCuThe;
    $scope.vitriUpdate = index;
    $scope.x.hinhThucThanhToan = hd.hinhThucThanhToan;
    $scope.detailHDCT(hd.idHoaDon);
  };

  $scope.listIdCheck = [];
  $scope.checkBoxView = new Map();
  $scope.isDisabled = true;
  $scope.listNgayHoanTra = [];
  var ngayHienTai = new Date();
  $scope.checkId = function (a) {
    $scope.isDisabled = false;
    let checkHT = false;
    let obj = $scope.listHD.find(({idHoaDon}) => idHoaDon === a);
    let ngay = new Date(obj.ngayNhan);
    ngay.setDate(ngay.getDate() + 3);
    ngay = $filter("date")(ngay, "yyyy-MM-dd");

    if ($scope.checkBoxView.has(a)) {
      $scope.checkBoxView.delete(a);
      console.log($scope.checkBoxView);
    } else {
      $scope.checkBoxView.set(a, a);
      console.log($scope.checkBoxView);
    }

    if ($scope.listIdCheck.indexOf(a) >= 0) {
      $scope.removeItem(a, $scope.listIdCheck);
      if($scope.listNgayHoanTra.indexOf(ngay) >= 0){
        $scope.removeItem(ngay, $scope.listNgayHoanTra)
      }
      console.log($scope.listIdCheck, $scope.listNgayHoanTra, ngay);
    } else {
      if (obj.idHoaDon === a) {
        $scope.listNgayHoanTra.push(ngay);
      }

      $scope.listIdCheck.push(a);
    }
   
    
    // for (var i = 0; i < $scope.listHD.length; i++) {
    //   if ($scope.listHD[i].idHoaDon === a) {
    //     var ngay = new Date($scope.listHD[i].ngayNhan);
    //     ngay.setDate(ngay.getDate() + 3);
    //     $scope.listNgayHoanTra.push(ngay);
    //   }
    // }

    for (let index = 0; index < $scope.listNgayHoanTra.length; index++) {
      if(new Date($scope.listNgayHoanTra[index]) <= ngayHienTai){
        $scope.isDisabled = true;
        $scope.showAlert4(); 
        break;
      }
      console.log("IS: ", $scope.isDisabled)
    }
    
    if ($scope.listIdCheck.length <= 0) {
      $scope.isDisabled = true;
    } 

    // if (ngay <= ngayHienTai) {
    //   $scope.isDisabled = true;
    //   $scope.showAlert4();
    // } else
    // $scope.checkTrong();
  };

  $scope.removeItem = function (item, list) {
    let index =  list.indexOf(item);
    if (index !== -1) {
      list.splice(index, 1);
    }
  };

  // $scope.checkTrong = function () {

  //   if ($scope.listIdCheck.length <= 0) {
  //     $scope.isDisabled = true;
  //   } else {
  //     $scope.isDisabled = false;
  //   }
  // };

  $scope.ketQua = "";
  var emailNV = localStorage.getItem("emailNV");
  $scope.update = function (a) {
    if ($scope.listIdCheck.length <= 0) {
      alert("Bạn cần chọn hóa đơn để đổi trạng thái");
    } else {
      $http
        .put(
          hoaDonApi +
          "/update-trang-thai?listID=" +
          $scope.listIdCheck +
          "&trangThai=" +
          a +
          "&email=" +
          emailNV +
          "&lyDo=" +
          $scope.lyDo
        )
        .then(function (response) {
          $scope.ketQua = response.data;
          $scope.showAlert3();
        })
        .catch(function (err) {
          console.log("Error: " + err);
        });
    }
  };

  $scope.showAlert3 = function () {
    $("#myAlert3").modal("show");
    $timeout(function () {
      location.reload();
      $("#myAlert3").modal("hide");
    }, 2000);
  };

  $scope.showAlert4 = function () {
    $("#myAlert4").modal("show");
    $timeout(function () {
      // location.reload();
      $("#myAlert4").modal("hide");
    }, 2000);
  };

  //Phân trang
  $scope.prevPage = function () {
    $scope.pageNo = 0;
    $scope.loadSearchTT();
  };

  $scope.prevPages = function () {
    if ($scope.pageNo < 1) {
      return alert("Đã hết hóa đơn");
    }
    $scope.pageNo--;
    $scope.loadSearchTT();
  };
  $scope.nextPages = function () {
    if ($scope.pageNo + 1 >= $scope.totalPages) {
      return alert("Đã hết hóa đơn");
    }
    $scope.pageNo++;
    $scope.loadSearchTT();
  };
  $scope.nextPage = function () {
    $scope.pageNo = $scope.totalPages - 1;
    $scope.loadSearchTT();
  };
};
