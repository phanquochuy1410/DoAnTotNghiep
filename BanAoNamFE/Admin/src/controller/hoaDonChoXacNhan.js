window.hoaDonChoXacNhanController = function ($scope, $http, $timeout, $route, $routeParams) {
  $scope.listHD = [];
  $scope.listHDHT = [];
  $scope.listHDCT = [];

  $scope.x = {
    id: "",
    tongTien: "",
    ma: "",
    soDienThoai: "",
    tenNguoiNhan: "",
    tenNguoiDat: "",
    soNguoiDat: "",
    ngayTao: "",
    tenNguoiBan: "",
    thanhPho: "",
    quan: "",
    phuong: "",
    phuongThuc: "",
    diaChiCuThe: "",
    ship: "",
    phuongThuc: "",
  };

  $scope.soLuongTempt = [];
  $scope.totalPages = 0;
  $scope.listPageNo = [];
  $scope.pageNo = 0;

  $scope.reload = function () {
    $scope.loadSearchTT();
  };

  $scope.loadSearchTT = function () {
    $scope.listHD.splice(0, $scope.listHD.length);
    $http
      .get(
        hoaDonApi +
        "/search-trang-thai-xac-nhan?trangThaiS=0&pageNo=" +
        $scope.pageNo
      )
      .then(
        function (response) {
          $scope.listHD = response.data;
          console.log("LOL, ", $scope.listHD);
          for (var i = 0; i < response.data.totalPages; i++) {
            $scope.listPageNo.push(i);
          }
          $scope.totalPages = response.data.totalPages;
          $scope.listHD = response.data.content;
          console.log("LoL@: ", $scope.listHD);
        },
        function (err) {
          console.log(err);
        }
      );
  };
  $scope.loadSearchTT();

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

  $scope.loadData = function () {
    $scope.loadSearchTT();
  };

  $scope.idHoaDonTempt = 0;
  $scope.hoaDonH();
  $scope.failValidateSl = [];
  $scope.failSoLuongAm = [];
  $scope.tongTienTempt = [];
  $scope.listId = [];
  $scope.listCheckTrong = [];
  $scope.detailHDCT = function (a) {
    console.log("A: ", a);
    $scope.soLuongTempt = [];
    $scope.tongTienTempt = [];
    $scope.listId = [];
    $scope.listCheckTrong = [];
    $scope.idHoaDonTempt = a;
    $scope.listHDCT.splice(0, $scope.listHDCT.length);
    $http.get(hoaDonChiTietApi + "/detail?id=" + a).then(
      function (response) {
        $scope.listHDCT = response.data;
        $scope.listHDCT.forEach(element => {
          $scope.soLuongTempt.push(element.soLuong);
          $scope.failValidateSl.push(false);
          $scope.listCheckTrong.push(false);
          $scope.failSoLuongAm.push(false);
          $scope.listHDCTUp.push(element);
          $scope.tongTienTempt.push((element.thanhTien - element.khuyenMai) * element.soLuong);
          $scope.listId.push(element.idChiTietSanPham);
        });
        console.log($scope.listHDCT, $scope.soLuongTempt, $scope.tongTienTempt, $scope.listId);
        for (var j = 0; j < $scope.listHDCT.length; j++) {
          if ($scope.soLuongTempt[j] - $scope.listHDCT[j].soLuong > $scope.listHDCT[j].soLuongTon) {
            $scope.discmm = true;
            $scope.failValidateSl[j] = true;
          } else {
            $scope.discmm = false;
            $scope.failValidateSl[j] = false;
          }
        }
      },
      function (err) {
        console.log(err);
      }
    );
  };

  $scope.timKiem = function () {
    $http
      .get(
        hoaDonApi + "/tim-kiem-cho-xn?trangThai=0&searchByMa=" + $scope.search
      )
      .then(function (response) {
        $scope.listHD = response.data;
        console.log("Thành công ", response.data);
      });
  };

  $scope.searchNgay = "";
  $scope.timNgay = function () {
    $http
      .get(
        hoaDonApi +
        "/tim-ngay?trangThaiSearch=0&ngayTaoSearch=" +
        $scope.searchNgay
      )
      .then(function (response) {
        $scope.listHD = response.data;
      });
  };

  $scope.batDau = "";
  $scope.ketThuc = "";

  $scope.timKhoangNgay = function () {
    $http
      .get(
        hoaDonApi +
        "/tim-khoang-ngay?trangThaiSearch=0&ngayTaoSearch=" +
        $scope.batDau +
        "&ngayTaoSearchs=" +
        $scope.ketThuc
      )
      .then(function (response) {
        $scope.listHD = response.data;
        console.log(response.data);
      });
  };

  $scope.ketQua = "";
  $scope.addToCart = async function (result) {
    let ifNotExists = true;
    var cartItem = {
      idChiTietSanPham: result.id,
      soLuong: 1,
      khuyenMai: result.khuyenMai,
      thanhTien: result.gia,
      idHoaDon: $scope.x.id,
    };
    $scope.listId.forEach(element => {
      if(result.id === element){
        ifNotExists = false;
        $scope.showAlertCart();
      };
    })
    if(ifNotExists){
      $http.get(chiTietSanPhamApi + "/get-by/" + result.id).then(function (response) {
        console.log("response: " + JSON.stringify(response.data.soLuong));
        if (response.data.soLuong > 0) {
          $http
            .post(hoaDonChiTietApi + "/add-to-cart?idHoaDon=" + $scope.x.id, cartItem)
            .then(function (response) {
              location.reload();
            });
        } else {
          $scope.showAlert2();
        }
      })
    }
  };

  $scope.detail = function (event, index) {
    event.preventDefault();
    let hd = $scope.listHD[index];
    console.log("hd: ", hd);
    $scope.x.id = hd.idHoaDon;
    $scope.x.ma = hd.maHoaDon;
    $scope.x.tongTien = hd.tongTien;
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

  $scope.soLt = {};
  $scope.shipTrong = false;
  $scope.shipAm = false;
  $scope.capNhatDonHang = function (event) {
    $http
      .get(chiTietSanPhamApi + "/get-so-luong-ton?lid=" + $scope.listId)
      .then(function (response) {

        $scope.shipTrong = false;
        $scope.shipAm = false;
        $scope.flag = true;
        $scope.listHDCT.forEach(function (item, index) {
          console.log(
            "ELE: " + item.soLuong,
            "IDX: " + index,
            "SLT: " + item.soLuongTon
          );

          if($scope.x.ship === undefined){
            $scope.flag = false;
            $scope.shipAm = true;
          }

          if($scope.x.ship === null){
            $scope.flag = false;
            $scope.shipTrong = true;
          }

          if ($scope.soLuongTempt[index] - item.soLuong > response.data[index]) {
            $scope.failValidateSl[index] = true;
            console.log("HOADASL: " + $scope.flag);
          } else {
            $scope.failValidateSl[index] = false;
          }

          if ($scope.failValidateSl.includes(true)) {
            $scope.discmm = true;
            $scope.flag = false;
          } else {
            $scope.discmm = false;
          }

        });
        console.log("FALG: " + $scope.flag);
        console.log("CNDHSLT: ", $scope.soLuongTempt);
        if ($scope.flag) {
          $http
            .put(
              hoaDonChiTietApi + "/update?sl=" + $scope.soLuongTempt,
              $scope.listHDCT
            )
            .then(function () {
              $scope.capNhatKhachHang();

              // location.reload();
              $timeout(function () {
                $("#staticBackdrop").modal("hide");
                location.reload();
                $timeout(function () {
                  $("#staticBackdrop").modal("show");
                }, 500);
              }, 2000);
            });
        }
      });

  };

  $scope.capNhatKhachHang = function () {
    console.log("TT", $scope.x.tongTien);
    $scope.x.tongTien = 0;
    $scope.tongTienTempt.forEach(element => { $scope.x.tongTien += element })
    $http.put(hoaDonApi + "/update/khach-hang", $scope.x).then(function () {
      $scope.showAlert1();
    });
  };

  $scope.showAlert1 = function () {
    $("#myAlert1").modal("show");
    $timeout(function () {
      location.reload();
      $("#myAlert1").modal("hide");
    }, 2000);
  };


  $scope.listHDCTUp = [];
  $scope.themSoLuong = function (hdct, index) {
    $http
      .get(hoaDonChiTietApi + "/detail?id=" + $scope.idHoaDonTempt)
      .then(function (response) {
        console.log("REs: ", response.data);
        console.log(
          "SLT: ",
          $scope.soLuongTempt[index],
          " hdct.sl: ",
          hdct.soLuong,
          "CTSPSL: ",
          response.data[index]
        );
        var km = 0;

        checkAm($scope.soLuongTempt, index, $scope.failSoLuongAm);
        validateTrong($scope.soLuongTempt, index, $scope.listCheckTrong);

        if ($scope.soLuongTempt[index] - hdct.soLuong > response.data[index].soLuongTon) {
          km = hdct.khuyenMai * $scope.soLuongTempt[index];
          $scope.tongTienTempt[index] = hdct.thanhTien * $scope.soLuongTempt[index] - km;
          console.log("HDCT: " + km, hdct.khuyenMai, hdct.gia);
          $scope.failValidateSl[index] = true;
        } else {
          km = hdct.khuyenMai * $scope.soLuongTempt[index];
          $scope.tongTienTempt[index] = hdct.thanhTien * $scope.soLuongTempt[index] - km;
          $scope.failValidateSl[index] = false;
        }

        if ($scope.failValidateSl.includes(true) || $scope.failSoLuongAm.includes(true) || $scope.listCheckTrong.includes(true)) {
          $scope.discmm = true;
        } else {
          $scope.discmm = false;
        }

        // if ($scope.listHDCTUp.indexOf(hdct) >= 0) {
        //   $scope.removeItem1(hdct);
        //   console.log($scope.listHDCTUp);
        // }
        $scope.listHDCT[index] = hdct;
        console.log("HDCT", $scope.listHDCT, "TTTEMPT: ", $scope.tongTienTempt);
      });
  };

  function checkAm(list, index, listValidate) {
    if (list[index] === undefined) {
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

  $scope.remove = function (event, a, index) {
    event.preventDefault();
    if ($scope.listHDCT.length < 2) {
      $scope.showAlert();
    } else {
      $scope.soLuongTempt.splice(index, 1);
      $scope.failValidateSl.splice(index, 1);
      $scope.failSoLuongAm.splice(index, 1);
      $http.delete(hoaDonChiTietApi + "/delete/sp?id=" + a).then(function () {
        location.reload();
      });
    }
  };

  $scope.showAlert = function () {
    $("#myAlert").modal("show");
    $timeout(function () {
      $("#myAlert").modal("hide");
    }, 3000);
  };

  $scope.showAlerts = function () {
    $("#myAlerts").modal("show");
    $timeout(function () {
      $("#myAlerts").modal("hide");
    }, 2000);
  };

  $scope.showAlert2 = function () {
    $("#myAlert2").modal("show");
    $timeout(function () {
      $("#myAlert2").modal("hide");
    }, 2000);
  };

  $scope.showAlertCart = function () {
    $("#myAlertCart").modal("show");
    $timeout(function () {
      $("#myAlertCart").modal("hide");
    }, 2000);
  };

  $scope.removeItem1 = function (item) {
    var index = $scope.listHDCTUp.indexOf(item);
    if (index !== -1) {
      $scope.listHDCTUp.splice(index, 1);
    }
  };

  $scope.listIdCheck = [];
  $scope.listCheckShip = [];
  $scope.listShip = [];
  $scope.listIdHd = new Map();
  $scope.checkId = function (a, b) {
    
    if($scope.listCheckShip.indexOf(b) >= 0 && $scope.listIdHd.has(a)){
      $scope.removeItem(b, $scope.listCheckShip);
    }else{
      if(b == null){
        $scope.listCheckShip.push(b);
      }
    }

    if($scope.listShip.indexOf(b) >= 0 && $scope.listIdHd.has(a)){
      $scope.removeItem(b, $scope.listShip);
    }else{
      if(b != null){
        $scope.listShip.push(b);
      }
    }

    if ($scope.listIdCheck.indexOf(a) >= 0) {
      $scope.removeItem(a, $scope.listIdCheck);
    } else {
      if (b != null) {
        $scope.listIdCheck.push(a);
      }
    } 
    
    if($scope.listIdHd.has(a)){
      $scope.listIdHd.delete(a);
    }else{
      if (b == null) {
        $scope.showAlerts();
      }
      $scope.listIdHd.set(a, a);
    }

    console.log("LOL: ", $scope.listIdCheck, $scope.listCheckShip, $scope.listShip, $scope.listIdHd);
    $scope.checkTrong();
  };

  $scope.removeItem = function (item, list) {
    console.log("The item: ", item);
    var index = list.indexOf(item);
    if (index !== -1) {
      list.splice(index, 1);
    }
  };

  $scope.isDisabled = true;
  $scope.checkTrong = function () {
    if ($scope.listIdCheck.length <= 0 || $scope.listCheckShip.includes(null)) {
      $scope.isDisabled = true;
    } else {
      $scope.isDisabled = false;
    }
  };

  var emailNV = localStorage.getItem("emailNV");
  $scope.update = function (a) {
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
  };

  
  $scope.huyDon = function () {
    let listHuyHd = [];
    for(let [key, value] of $scope.listIdHd){
      listHuyHd.push(value);
    }
    console.log(listHuyHd);
    $http
      .put(
        hoaDonApi +
        "/update-trang-thai?listID=" +
        listHuyHd +
        "&trangThai=" +
        4 +
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
  };

  $scope.showAlert3 = function () {
    $("#myAlert3").modal("show");
    $timeout(function () {
      location.reload();
      $("#myAlert3").modal("hide");
    }, 2000);
  };

  $scope.searchSP = "";
  $scope.searchResults = [];
  $scope.showSP = false;

  $scope.getListSP = async function () {
    $http
      .get(chiTietSanPhamApi + "/search-list-sp?ten=" + $scope.searchSP)
      .then(
        function (response) {
          $scope.searchResults = response.data;
        },
        function (err) {
          console.log(err);
        }
      );
  };

  $scope.checkTienShip = function (a) {
    if (a == null) {
      $scope.discmm = false;
    } else {
      $scope.discmm = true;
    }
  };

  $scope.searchListSP = function () {
    if ($scope.searchSP) {
      $scope.getListSP();
      console.log($scope.searchResults);
      $scope.showSP = true;
    } else {
      $scope.showSP = false;
    }
  };

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

  $scope.totalPages = 0;
  $scope.listPageNo = [];
  $scope.pageNo = 0;

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
