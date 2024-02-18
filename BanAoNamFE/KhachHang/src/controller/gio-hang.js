var app = angular.module("GioHang", []);
app.controller(
  "CheckOutController",
  function ($scope, $http, $timeout, $window) {
    $scope.TongTienHoaDon = 0;

    $scope.x = {
      tenNguoiNhan: "",
      soNguoiNhan: "",
    };

    $scope.showNguoiNhan = false;
    var g = 0;

    $scope.nguoiKhacNhan = function () {
      if (g == 0) {
        $scope.showNguoiNhan = true;
        g = 1;
      } else {
        $scope.showNguoiNhan = false;
        g = 0;
      }
    };

    $scope.listIdSp = [];
    $scope.getListGH = function () {
      $http
        .get(gioHangChiTietApi + "/get-list-check-out")
        .then(function (response) {
          $scope.listGH = response.data;
          for (var i = 0; i < $scope.listGH.length; i++) {
            var tongTien = $scope.listGH[i].thanhTien;
            $scope.soLuong = $scope.listGH[i].soLuong
            $scope.soLuongTon = $scope.listGH[i].soLuongTon
            console.log("Số lượng", $scope.soLuong)
            console.log("Số lượng tồn", $scope.soLuongTon)
            $scope.TongTienHoaDon += tongTien;
            console.log("HIO " + $scope.TongTienHoaDon, JSON.stringify($scope.listGH));
            $scope.listIdSp.push($scope.listGH[i].idCTSP);
            console.log("IDDH: ", $scope.listGH[i].id, $scope.listIdSp);
            console.log(localStorage.getItem("TongTienHoaDon"));
          }
        });
    };
    $scope.getListGH();
    $scope.listGH = [];

    $scope.kh = {
      ten: "",
      tenDem: "",
      ho: "",
      sdt: "",
      email: "",
      idKH: "",
    };
    $scope.listDC = {};

    $scope.dcupdate = {
      id: "",
      phuong: "",
      quan: "",
      thanhPho: "",
      dcct: "",
    };

    var request = {
      method: "PUT",
      url: diaChiApi + "/update-dia-chi",
      data: $scope.dcupdate,
    };

    var newDC = {
      method: "POST",
      url: diaChiApi + "/save-dia-chi",
      data: $scope.dcupdate,
    };

    $scope.updateDC = function () {
      console.log($scope.listDC);
      if ($scope.listDC.id != null) {
        let thanhPho = document.getElementById("province");
        let newThanhPho = thanhPho.options[thanhPho.selectedIndex].text;
        let quan = document.getElementById("district");
        let newQuan = quan.options[quan.selectedIndex].text;
        let huyen = document.getElementById("ward");
        let newHuyen = huyen.options[huyen.selectedIndex].text;
        $scope.dcupdate.id = $scope.listDC.id;
        $scope.dcupdate.dcct = $scope.dcct;
        $scope.dcupdate.thanhPho = newThanhPho;
        $scope.dcupdate.phuong = newHuyen;
        $scope.dcupdate.quan = newQuan;
        $http(request).then(
          function (response) {
            alert("Update thành công");
            location.reload();
          },
          function (err) {
            console.log(err);
          }
        );
      } else {
        let thanhPho = document.getElementById("province");
        let newThanhPho = thanhPho.options[thanhPho.selectedIndex].text;
        let quan = document.getElementById("district");
        let newQuan = quan.options[quan.selectedIndex].text;
        let huyen = document.getElementById("ward");
        let newHuyen = huyen.options[huyen.selectedIndex].text;
        $scope.dcupdate.dcct = $scope.dcct;
        $scope.dcupdate.thanhPho = newThanhPho;
        $scope.dcupdate.phuong = newHuyen;
        $scope.dcupdate.quan = newQuan;
        $http(newDC).then(
          function (response) {
            location.reload();
          },
          function (err) {
            console.log(err);
          }
        );
      }
    };

    $scope.returnCart = function () {
      $http.put(gioHangChiTietApi + "/update?trangThai=1", $scope.listGH);
    };

    $scope.showAlertt = function (a) {
      $("#myAlertt").modal("show");
      $scope.alMess = a;
      $timeout(function () {
        $("#myAlertt").modal("hide");
      }, 3000);
    };

    $scope.tenNguoiNhan = "";
    $scope.soNguoiNhan = "";

    $scope.thanhToan = function () {
      console.log("OK:" + $scope.listGH);
      if ($scope.showNguoiNhan == false) {
        $scope.tenNguoiNhan = "Không có";
        $scope.soNguoiNhan = "Không có";
      }
      $http
        .post(
          gioHangChiTietApi +
          "/thanh-toan?tongTien=" +
          $scope.TongTienHoaDon +
          "&tenNguoiNhan=" +
          $scope.tenNguoiNhan +
          "&soNguoiNhan=" +
          $scope.soNguoiNhan,
          $scope.listGH
        )
        .then(function (response) {
          $window.location = "../pages/thanh-toan-thanh-cong.html";
        });
    };

    $scope.checkTrue = false;
    $scope.checkFail = false;
    $scope.checkSoLuongFalse = false;

    $scope.checkThanhToan = function () {
      $http
        .get(chiTietSanPhamApi + "/get-so-luong-ton?lid=" + $scope.listIdSp)
        .then(function (response) {
          console.log(response.data, $scope.listGH.length);
          for (var i = 0; i < $scope.listGH.length; i++) {
            $scope.soLuong = $scope.listGH[i].soLuong
            $scope.soLuongTon = $scope.listGH[i].soLuongTon
            if ($scope.soLuong > response.data[i]) {
              $scope.checkSoLuongFalse = true;
              $scope.checkTrue = false;
              $scope.checkFail = false;
              return;
            } else if ($scope.listDC.dcct == null) {
              $scope.checkTrue = false;
              $scope.checkFail = true;
              $scope.checkSoLuongFalse = false;

            } else {
              $scope.checkTrue = true;
              $scope.checkFail = false;
              $scope.checkSoLuongFalse = false;
            }
            console.log("LOL");
          }
        });
    };

    $scope.thanhToanAndVnpay = function () {
      console.log($scope.listGH);
      if ($scope.showNguoiNhan == false) {
        $scope.tenNguoiNhan = "Không có";
        $scope.soNguoiNhan = "Không có";
      }
      $http
        .post(
          gioHangChiTietApi +
          "/thanh-toan1?tongTien=" +
          $scope.TongTienHoaDon +
          "&tenNguoiNhan=" +
          $scope.tenNguoiNhan +
          "&soNguoiNhan=" +
          $scope.soNguoiNhan,
          $scope.listGH
        )
        .then(function (response) {
          $window.location = "../pages/thanh-toan-thanh-cong.html";
        });
    };


    $scope.thanhToanVnpay = function () {

      if ($scope.showNguoiNhan == false) {
        $scope.tenNguoiNhan = "Không có";
        $scope.soNguoiNhan = "Không có";
      }
      for (var i = 0; i < $scope.listGH.length; i++) {
        $scope.soLuong = $scope.listGH[i].soLuong
        $scope.soLuongTon = $scope.listGH[i].soLuongTon
        console.log("Số lượng", $scope.soLuong)
        console.log("Số lượng tồn", $scope.soLuongTon)
        if ($scope.soLuong > $scope.soLuongTon) {
          $scope.showAlertt("Số lượng không đủ");
        } else {
          var url =
            gioHangChiTietApi +
            "/thanh-toan-vnpay?tongTien=" +
            $scope.TongTienHoaDon +
            "&don=ThanhToanVnpay";
          $window.open(url, "_blank");
          // $window.location = "../pages/xac-nhan-thanh-toan.html";
        }
      }
    };

    $scope.loadDC = function () {
      $http.get(diaChiApi + "/load-dia-chi").then(function (response) {
        $scope.listDC = response.data;
        console.log(response.data);
      });
    };
    $scope.loadDC();

    $scope.loadKH = function () {
      $http
        .get(gioHangChiTietApi + "/load-khach-hang")
        .then(function (response) {
          let khachHang = response.data;
          $scope.kh.ten = khachHang.ten;
          $scope.kh.tenDem = khachHang.tenDem;
          $scope.kh.ho = khachHang.ho;
          $scope.kh.email = khachHang.email;
          $scope.kh.idKH = khachHang.idKH;
          $scope.kh.sdt = khachHang.sdt;
        });
    };
    $scope.loadKH();
  },
  app.controller(
    "GioHangController",
    function ($scope, $http, DataService, $timeout, $location, $window) {
      $scope.pageNo = 0;
      $scope.listPageNo = [];
      $scope.listGHCT = [];


      $scope.gioHang = {
        idCTSP: "",
        chatLieu: "",
        gia: "",
        hang: "",
        idGH: "",
        khuyenMai: "",
        maSanPham: "",
      }

      $scope.getPageGH = function () {
        $http.get(gioHangChiTietApi + "/get-page").then(
          function (response) {
            $scope.listGHCT = response.data;
            $scope.gioHangss = response.data;
            console.log("LOL: ", $scope.gioHangss);
            for (var i = 0; i < $scope.listGHCT.length; i++) {
              if ($scope.listGHCT[i].trangThaiSanPham == 0) {
                $scope.listGHCT[i].trangThaiSanPhamTrueFalse = true;
              } else {
                $scope.listGHCT[i].trangThaiSanPhamTrueFalse = false;
              }
            }
            console.log("vvvvvvvvv", $scope.listGHCT.soLuongTon);

            $scope.checkGioTrong();
          },
          function (err) {
            console.log(err);
          }
        );
      };
      $scope.getPageGH();

      $scope.checkGioTrong = function () {
        console.log($scope.listGHCT);
        if ($scope.listGHCT.length > 0) {
          $scope.showTrong1 = true;
          $scope.showTrong = false;
        } else {
          $scope.showTrong1 = false;
          $scope.showTrong = true;
        }
      };

      $scope.listIdCheck = [];
      $scope.checkId = function (a) {
        if ($scope.listIdCheck.indexOf(a) >= 0) {
          // $scope.TongTien = $scope.TongTien - a.thanhTien;
          console.log($scope.TongTien);
          for (var i = 0; i < $scope.listIdCheck.length; i++) {
            var index = DataService.getData().indexOf($scope.listIdCheck[i]);
            if (index >= 0) {
              DataService.removeItem($scope.listIdCheck[i]);
              console.log(DataService.getData());
            }
          }
          $scope.removeItem(a);

        } else {
          $scope.listIdCheck.push(a);
          // $scope.TongTien = $scope.TongTien + a.thanhTien;
          console.log($scope.TongTien);
          for (var i = 0; i < $scope.listIdCheck.length; i++) {
            var index = DataService.getData().indexOf($scope.listIdCheck[i]);
            if (index < 0) {
              DataService.setData($scope.listIdCheck[i]);
              console.log(DataService.getData());
            }
          }
          localStorage.setItem("TongTienHoaDon", $scope.TongTien);
          // console.log("listCheck1 :",$scope.listIdCheck);
          console.log("Tổng : ", localStorage.getItem("TongTienHoaDon"));
          localStorage.setItem("myArray", JSON.stringify(DataService.getData())
          );
          var storedArray = JSON.parse(localStorage.getItem("myArray"));
          console.log("DATA", storedArray);
        }
        console.log("listCheck :", $scope.listIdCheck);
        $scope.TongTien = 0;
        for (var j = 0; j < $scope.listIdCheck.length; j++) {
          $scope.TongTien += $scope.listIdCheck[j].thanhTien;
        }
        console.log("Tổng tiền1 :", $scope.TongTien);
        $scope.checkTrong();
      };

      $scope.isDisabled = true;
      $scope.checkTrong = function () {
        if ($scope.listIdCheck.length <= 0) {
          $scope.isDisabled = true;
        } else {
          $scope.isDisabled = false;
        }
        $scope.xacNhanVali();
      };

      $scope.checkEnter = function (event, ghct) {
        if (event.keyCode === 13) {
          $scope.capNhatSoLuongHang(ghct);
        }
      };

      $scope.showAlert = function (a) {
        $("#myAlert").modal("show");
        $scope.alMess = a;
        $timeout(function () {
          $("#myAlert").modal("hide");
        }, 3000);
      };

      $scope.showAlerts = function (a) {
        $("#myAlerts").modal("show");
        $scope.alMess = a;
        $timeout(function () {
          $("#myAlerts").modal("hide");
        }, 3000);
      };
      $scope.showAlertss = function (a) {
        $("#myAlertss").modal("show");
        $scope.alMess = a;
        $timeout(function () {
          $("#myAlertss").modal("hide");
        }, 3000);
      };

      $scope.showAlert1 = function () {
        $("#myAlert1").modal("show");
        $timeout(function () {
          $("#myAlert1").modal("hide");
        }, 2000);
      };

      $scope.capNhatSoLuongHang = function (ghct) {
        if (ghct.soLuong <= 0 || ghct.soLuong == null) {
          $scope.showAlert1();
        } else if (ghct.soLuong > ghct.soLuongTon) {
          $scope.showAlert("Số lượng không đủ");
        } else {
          $http
            .put(gioHangChiTietApi + "/update-so-luong", ghct)
            .then(function () {
              // location.reload();
            });
        }
      };

      $scope.xacNhan12 = false;
      $scope.xacNhan34 = true;
      $scope.checkSoLuong = false;

      $scope.xacNhanVali = function () {
        for (var i = 0; i < DataService.getData().length; i++) {
          if (DataService.getData()[i].soLuong > DataService.getData()[i].soLuongTon) {
            $scope.showAlertss("Số lượng không đủ");
            $scope.isDisabled = true;
          }
          if (DataService.getData()[i].soLuong <= 0 || DataService.getData()[i].soLuong == null) {
            $scope.showAlert1("Số lượng lớn hơn 0");
            $scope.isDisabled = true;
          }
          if (DataService.getData()[i].trangThaiSanPhamTrueFalse == true) {
            $scope.showAlerts("Không thể thanh toán sản phẩm đã hết hàng");
            $scope.isDisabled = true;
          }
          if (localStorage.getItem("TongTienHoaDon") <= 0) {
            $scope.isDisabled = true;
          }
          if ($scope.listIdCheck.length <= 0) {
            $scope.xacNhan12 = false;
            $scope.xacNhan34 = true;
            $scope.checkSoLuong = false;
          } else {
            $scope.xacNhan12 = true;
            $scope.xacNhan34 = false;
            $scope.checkSoLuong = false;
          }
        }
      };

      $scope.listGHXOa = [];
      $scope.xoaSP = function () {
        $scope.listGHXOa = DataService.getData();
        console.log($scope.listGHXOa);
        var config = {
          headers: {
            "Content-Type": "application/json",
          },
          data: $scope.listGHXOa,
        };
        $http.delete(gioHangChiTietApi + "/delete", config).then(function () {
          location.reload();
        });
      };

      $scope.checkOut = function () {
        $http
          .put(
            gioHangChiTietApi + "/update?trangThai=2",
            DataService.getData()
          )
          .then(function (response) {
            $window.location = "../pages/checkout.html";
          });
      };

      $scope.themSoLuong = function (ghct) {
        ghct.thanhTien = (ghct.soLuong * ghct.gia) - (ghct.khuyenMai * ghct.soLuong);
        console.log("Tổng : ", localStorage.getItem("TongTienHoaDon"));
        $scope.TongTien = 0;
        for (var j = 0; j < $scope.listIdCheck.length; j++) {
          $scope.TongTien += $scope.listIdCheck[j].thanhTien;
        }
        console.log("Tổng tiền2 :", $scope.TongTien);
        console.log("list gio hàng : ", $scope.listIdCheck);
        $scope.capNhatSoLuongHang(ghct);
      };

      $scope.removeItem = function (item) {
        var index = $scope.listIdCheck.indexOf(item);
        if (index !== -1) {
          $scope.listIdCheck.splice(index, 1);
        }
      };
    }
  )
);
