window.muaTaiQuayController = function (
  $scope,
  $http,
  $timeout,
  $route,
  $routeParams,
  $location
) {
  $scope.listSP = [];
  $scope.mauSacs = [];
  $scope.kichCos = [];
  $scope.sanPhams = [];
  $scope.listHD = [];
  $scope.listHDCT = [];
  $scope.page = 0;

  $scope.x = {
    id: "",
    gia: "",
    ma: "",
    tongTien: "",
    tenNguoiNhan: "",
    ngayTao: "",
    tenNguoiBan: "",
    phuongThuc: "",
  };

  $scope.searchTen = "";
  $scope.searchMauSac = "";
  $scope.searchKichCo = "";
  $scope.idctsp = "";

  $scope.soLuongTonKho = [];
  $scope.hienThiSanPham = function () {
    $scope.timKiem = function () {
      $scope.soLuongTonKho = [];
      var url = muaTaiQuayApi + "/hien-thi?page=" + $scope.page + "&";

      if ($scope.searchTen && $scope.searchTen.trim() !== "") {
        url += "tenSanPham=" + $scope.searchTen + "&";
      }

      if ($scope.idctsp && $scope.idctsp.trim() !== "") {
        url += "id=" + $scope.idctsp + "&";
      }

      if ($scope.searchMauSac && $scope.searchMauSac.trim() !== "") {
        url += "mauSac=" + $scope.searchMauSac + "&";
      }

      if ($scope.searchKichCo && $scope.searchKichCo.trim() !== "") {
        url += "tenKichCo=" + $scope.searchKichCo + "&";
      }
      if (url.charAt(url.length - 1) === "&") {
        url = url.slice(0, -1);
      }

      $http.get(url).then(function (response) {
        $scope.listSP = response.data;
        barcodeMappings = response.data;
        $scope.listSP.forEach(element => {
          $scope.soLuongTonKho.push(element);
        });
        console.log("LISTSP ", $scope.listSP);
      });
    };
    $scope.timKiem();
  };
  $scope.hienThiSanPham();

  // quét mã 

  var barcodeMappings = [];
  $scope.scanning = false;

  $scope.showCame = false;
  $scope.showTrue = function () {
    $scope.scanning = !$scope.scanning;
    if($scope.scanning){
      initQuagga();
      $scope.showCame = true;
    }else{
      Quagga.stop();
      $scope.showCame = false;
    }
  };

  // $scope.showFalse = function () {
  //   $scope.showCame = false;
  // };

  $scope.barcodeResult = "";
  $scope.isBarcodeDisplayed = false;

  function initQuagga() {
    Quagga.init({
      inputStream: {
        name: "Live",
        type: "LiveStream",
        target: document.querySelector('#barcode-scanner'),
        constraints: {
          width: 640,
          height: 480,
          facingMode: "environment"
        },
      },
      decoder: {
        readers: [
          "code_128_reader"
        ]
      }
    }, function (err) {
      if (err) {
        console.error("Đã xảy ra lỗi :", err);
        return;
      }
      console.log("Mở came thành công ");
      Quagga.start();
    })
  };

  $scope.sanPham = {};

  Quagga.onDetected(function (result) {
    if (!$scope.isBarcodeDisplayed) {
      $scope.isBarcodeDisplayed = true;
      $scope.scanning = false;
      var scannedBarcode = parseInt(result.codeResult.code);
      var foundMapping = barcodeMappings.find(function (mapping) {
        return mapping.id === scannedBarcode;
      });

      if (foundMapping) {
        $scope.barcodeResult = foundMapping.id;
        $scope.sanPham = foundMapping.id;
        console.log($scope.sanPham);
        $scope.themSanPham($scope.sanPham);
        $scope.$apply();
      } else {
        $scope.barcodeResult = "Mã không hợp lệ";
        $scope.$apply();
      }
    }

    $scope.$apply();
    setTimeout(function () {
      Quagga.start();
      $scope.$apply(function () {
        $scope.scanning = true;
        $scope.isBarcodeDisplayed = false;
      });
    }, 2000);
  });

  $scope.toggleScan = function () {
    $scope.scanning = !$scope.scanning;
    if ($scope.scanning) {
      $scope.barcodeResult = "";
    } else {
      $scope.barcodeResult = "";
    }
  };


  $scope.xoa = function () {
    $scope.idctsp = "";
    $scope.hienThiSanPham();
  };

  $scope.getCbbMauSac = function () {
    $http.get(mauSacApi + "/get-cbb-mau-sac").then(
      function (response) {
        $scope.mauSacs = response.data;
        console.log($scope.mauSacs);
        return $scope.mauSacs;
      },
      function (err) {
        console.log(err);
      }
    );
  };
  $scope.getCbbMauSac();

  $scope.getCbbKichCo = function () {
    $http.get(kichCoApi + "/get-cbb-kich-co").then(
      function (response) {
        $scope.kichCos = response.data;
        console.log($scope.kichCos);
        return $scope.kichCos;
      },
      function (err) {
        console.log(err);
      }
    );
  };
  $scope.getCbbKichCo();

  $scope.getCbbSanPham = function () {
    $http.get(sanPhamApi + "/get-cbb-san-pham").then(
      function (response) {
        $scope.sanPhams = response.data;
        console.log($scope.sanPhams);
        return $scope.sanPhams;
      },
      function (err) {
        console.log(err);
      }
    );
  };
  $scope.getCbbSanPham();

  var emailNV = localStorage.getItem("emailNV");
  console.log(emailNV);

  $scope.addHoaDon = function () {
    $http
      .post(muaTaiQuayApi + "/add-hoa-don?email=" + emailNV)
      .then(function (response) {
        $route.reload();
      });
  };

  $scope.hienThiHoaDon = function () {
    $http.get(muaTaiQuayApi + "/hien-thi-ma").then(function (response) {
      $scope.listHD = response.data;
      console.log(response.data);
    });
  };
  $scope.hienThiHoaDon();

  $scope.tt = 0;
  $scope.idHdTempt = 0;
  $scope.soLuongTempt = [];
  $scope.failValidateSl = [];
  $scope.failSoLuongAm = [];
  $scope.tongTienTempt = [];
  $scope.listCheckTrong = [];
  $scope.detailHDCT = function (a) {
    console.log(a);
    $scope.idHdTempt = a;
    $scope.soLuongTempt = [];
    $scope.tongTienTempt = [];
    $scope.failSoLuongAm = [];
    $scope.listCheckTrong = [];
    $scope.failValidateSl = [];
    $http.get(hoaDonChiTietApi + "/detail?id=" + a).then(
      function (response) {
        $scope.listHDCT = response.data;
        $scope.listHDCT.forEach(element => {
          $scope.soLuongTempt.push(element.soLuong);
          $scope.failValidateSl.push(false);
          $scope.failSoLuongAm.push(false);
          $scope.listCheckTrong.push(false);
          $scope.tongTienTempt.push((element.thanhTien - element.khuyenMai) * element.soLuong);
        });
        console.log($scope.listHDCT, $scope.soLuongTempt, $scope.tongTienTempt);
        $scope.tt = $scope.tongTienHoaDon($scope.listHDCT);
        console.log("Tổng tiền :", $scope.tt);

        $scope.dh = $scope.tongDonHang();
        console.log("Số sản phẩm", $scope.dh);

        $scope.x.tongTien = $scope.tt;
        $scope.tienMat = 0;
        $scope.tienMat1 = 0;
        $scope.chuyenKhoan = 0;
        $scope.chuyenKhoan1 = 0;
        $scope.tienThua = 0;
        $scope.tienThua1 = 0;
        $scope.tienThua2 = 0;
      },
      function (err) {
        console.log(err);
      }
    );
  };

  $scope.tongTienHoaDon = function (list) {
    var tongTienHD = 0;
    for (var i = 0; i < list.length; i++) {
      tongTienHD += (list[i].thanhTien * list[i].soLuong) - (list[i].khuyenMai * list[i].soLuong);
    }
    $scope.x.tongTien = tongTienHD;
    return tongTienHD;
  }

  $scope.tongDonHang = function () {
    var donHang = 0;
    for (var i = 0; i < $scope.soLuongTempt.length; i++) {
      donHang += $scope.soLuongTempt[i];
    }
    return donHang;
  }

  $scope.detail = function (event, index) {
    event.preventDefault();
    let hd = $scope.listHD[index];
    $scope.x.id = hd.idHoaDon;
    $scope.x.ma = hd.maHoaDon;
    $scope.x.tenNguoiNhan = hd.tenNguoiNhan;
    // $scope.x.tongTien = $scope.tt;
    hd.tongTien = $scope.tt
    console.log("Hoa don", hd);
    $scope.detailHDCT(hd.idHoaDon);
  };

  $scope.listHDCTUp = [];
  $scope.tongGia = 0;
  $scope.themSoLuong = function (hdct, index) {
    $http
      .get(hoaDonChiTietApi + "/detail?id=" + $scope.idHdTempt)
      .then(function (response) {
        console.log("REs: ", response.data);
        console.log(
          "SLT: ",
          $scope.soLuongTempt[index],
          " hdct.sl: ",
          response.data[index].soLuong,
          "CTSPSL: ",
          response.data[index]
        );
        var km = 0;

        checkAm($scope.soLuongTempt, index, $scope.failSoLuongAm);
        validateTrong($scope.soLuongTempt, index, $scope.listCheckTrong);

        if (
          $scope.soLuongTempt[index] - response.data[index].soLuong >
          response.data[index].soLuongTon
        ) {
          km = hdct.khuyenMai * $scope.soLuongTempt[index];
          $scope.tongTienTempt[index] = hdct.thanhTien * $scope.soLuongTempt[index] - km;
          console.log("HDCT: " + km, hdct.khuyenMai, hdct.thanhTien);
          $scope.failValidateSl[index] = true;
        } else {
          km = hdct.khuyenMai * $scope.soLuongTempt[index];
          $scope.tongTienTempt[index] = hdct.thanhTien * $scope.soLuongTempt[index] - km;
          $scope.failValidateSl[index] = false;
        }

        if ($scope.failValidateSl[index] === true
          || $scope.failSoLuongAm[index] === true
          || $scope.listCheckTrong[index] === true
        ) {
          $scope.checkTrong = true;
        } else {
          $scope.checkTrong = false;
          $scope.x.tongTien = 0;
          $scope.tt = 0;
          $http.put(hoaDonChiTietApi + "/update-tai-quay?sl=" + $scope.soLuongTempt[index], hdct).then(
            function (response) {
              console.log(response.data);
              $scope.tongTienTempt.forEach(element => {
                $scope.x.tongTien += element;
                $scope.tt += element;
              });
              $scope.dh = $scope.tongDonHang();
              $scope.tienKhach();
            },
            function (err) {
              console.log(err);
            }
          );
        }
        console.log("HDCT", $scope.listHDCT, "TTTEMPT: ", $scope.tongTienTempt);
      });
    console.log($scope.soLuongTempt[index], $scope.soLuongTempt);
  };

  function checkAm(list, index, listValidate) {
    if (list[index] === undefined || list[index] == "") {
      listValidate[index] = true;
    } else {
      listValidate[index] = false;
    }
  }

  function validateTrong(list, index, listValidate) {
    if (list[index] === null) {
      listValidate[index] = true;
    } else {
      listValidate[index] = false;
    }
  }

  $scope.removeItem1 = function (item) {
    var index = $scope.listHDCTUp.indexOf(item);
    if (index !== -1) {
      $scope.listHDCTUp.splice(index, 1);
    }
  };

  $scope.remove = function (event, a) {
    event.preventDefault();
    $http
      .delete(hoaDonChiTietApi + "/delete/sp?id=" + a)
      .then(function (response) {
        $scope.showAlert2();
        $scope.detailHDCT($scope.idHdTempt);
      });
  };

  $scope.showAlert = function () {
    $("#myAlert").modal("show");
    $timeout(function () {
      $("#myAlert").modal("hide");
    }, 2000);
  };

  $scope.showAlert3 = function () {
    $("#myAlert3").modal("show");
    $timeout(function () {
      $route.reload()
      $("#myAlert3").modal("hide");
    }, 2000);
  };

  $scope.showAlert2 = function () {
    $("#myAlert2").modal("show");
    $timeout(function () {
      $("#myAlert2").modal("hide");
    }, 1000);
  };

  $scope.showAlert1 = function () {
    $("#myAlert1").modal("show");
    $timeout(function () {
      $("#myAlert1").modal("hide");
    }, 1000);
  };

  $scope.showAlert4 = function () {
    $("#myAlert4").modal("show");
    $timeout(function () {
      $("#myAlert4").modal("hide");
    }, 2000);
  };

  $scope.showAlert5 = function () {
    $("#myAlert5").modal("show");
    $timeout(function () {
      $("#myAlert5").modal("hide");
    }, 2000);
  };

  $scope.alertCart = function () {
    $("#alertCart").modal("show");
    $timeout(function () {
      $("#alertCart").modal("hide");
    }, 2000);
  };

  // for (var i = 0; i < $scope.listSP.length; i++) {
  //   if ($scope.listSP[i].id === id) {
  //     result.push($scope.listSP[i].soLuong);
  //   }
  // }

  $scope.themSanPham = function (id) {
    let ifNotExists = true;
    for (var i = 0; i < $scope.listHDCT.length; i++) {
      if($scope.listHDCT[i].idChiTietSanPham === id){
        ifNotExists = false;
        $scope.alertCart();
      }
    }
    var result = 0;
    if(ifNotExists){
      $http
      .get(muaTaiQuayApi + "/so-luong-ton?id=" + id)
      .then(function (response) {
        result = response.data;
        console.log(" Số lượng sản phẩm : ", result);
        if ($scope.x.id == "") {
          $scope.showAlert4();
        } else if (result <= 0) {
          $scope.showAlert5();
        }
        else {
          $http
            .get(
              muaTaiQuayApi +
              "/add-sp-hd?idHoaDon=" +
              $scope.x.id +
              "&idChiTietSanPham=" +
              id
            )
            .then(function (response) {
              $scope.showAlert1();
              $scope.detailHDCT($scope.idHdTempt);
            });
        }
      });
    }   
  };

  $scope.tienMat = 0;
  $scope.tienMat1 = 0;
  $scope.chuyenKhoan = 0;
  $scope.chuyenKhoan1 = 0;
  $scope.tienThua = 0;
  $scope.tienThua1 = 0;
  $scope.tienThua2 = 0;
  $scope.hinhThuc = 0;

  $scope.tienKhach = function () {
    console.log("LOL", $scope.tt)
    if ($scope.hinhThuc == 1) {
      $scope.tienThua = $scope.tienMat - $scope.tt;
    }
    if ($scope.hinhThuc == 2) {
      $scope.tienThua1 = $scope.chuyenKhoan - $scope.tt;
    }
    var tm = parseFloat($scope.tienMat1) || 0;
    var ck = parseFloat($scope.chuyenKhoan1) || 0;
    var ttt = parseFloat($scope.tt) || 0
    if ($scope.hinhThuc == 3) {
      $scope.tienThua2 = tm + ck - ttt;
    }
    $scope.checkRong();
  }

  $scope.checkTrong = true;
  $scope.checkRong = function () {
    var tong = 0
    var tmat = parseFloat($scope.tienMat1) || 0;
    var ckhoan = parseFloat($scope.chuyenKhoan1) || 0;
    var ttien = parseFloat($scope.tt) || 0
    for (var i = 0; i < $scope.listHDCT.length; i++) {
      tong += ($scope.listHDCT[i].thanhTien * $scope.soLuongTempt[i]) - ($scope.listHDCT[i].khuyenMai * $scope.soLuongTempt[i]);
      if (
        $scope.tienMat >= tong ||
        $scope.chuyenKhoan >= tong ||
        tmat + ckhoan >= ttien
      ) {
        if ($scope.failValidateSl.includes(true) || $scope.failSoLuongAm.includes(true)) {
          $scope.checkTrong = true;
        } else {
          $scope.checkTrong = false;
        }
      } else {
        $scope.checkTrong = true;
      }
    }
    console.log(tong);
    return tong;
  };

  $scope.thanhToan = function () {
    $http.put(muaTaiQuayApi + '/thanh-toan', $scope.x).then(
      function (response) {
        $scope.showAlert3();
      });
  }
  $scope.checkSoLuong = false;
  $scope.soLuongHD = 0;
  $scope.soLuongHoaDon = function () {
    $http.get(muaTaiQuayApi + '/so-luong-hoa-don').then(
      function(response){
        $scope.soLuongHD = response.data;
        console.log("Số lượng hóa đơn :",$scope.soLuongHD);
        if($scope.soLuongHD > 9){
          $scope.checkSoLuong = true;
        }else{
          $scope.checkSoLuong = false;
        }
      });
  };
  $scope.soLuongHoaDon();
};
