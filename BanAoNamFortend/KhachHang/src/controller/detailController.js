window.DetailController = function ($http, $scope, $filter, $routeParams, $interval, $timeout) {
  $scope.id = $routeParams.id;
  $scope.pageNo = 0;
  $scope.soLuongMua = 1;
  $scope.selectMS = 0;
  $scope.selectKC = 0;
  $scope.check = 0;
  $scope.dgspGetSP = [];
  $scope.listMS = [];
  $scope.listKC = [];
  $scope.listBL = [];
  $scope.sanPham = {
    id: 0,
    ten: "",
  };
  $scope.idKh = localStorage.getItem("AllKH");

  $scope.dgsp = {
    noiDung: "",
    soSao: -1,
    idKhachHang: "",
  }

  $scope.images = [];

  $scope.detailCTSP = {
    id: "",
    gia: 0,
    soLuong: "",
    giaKhuyenMai: 0,
    mota: "",
    idChatLieu: "",
    idMauSac: "",
    idSanPham: 0,
    idTheLoai: "",
    idHang: "",
    idKichCo: "",
    tenSP: "",
  };

  $scope.getDataId = function () {
    $http.get(chiTietSanPhamApi + "/get-by/" + $scope.id).then(
      function (response) {
        $scope.chiTietSanPham = response.data;
        $scope.detailCTSP.gia = $scope.chiTietSanPham.gia;
        $scope.detailCTSP.soLuong = $scope.chiTietSanPham.soLuong;
        $scope.detailCTSP.giaKhuyenMai = $scope.chiTietSanPham.giaKhuyenMai;
        $scope.detailCTSP.mota = $scope.chiTietSanPham.mota;
        $scope.detailCTSP.trangThai = $scope.chiTietSanPham.trangThai;
        $scope.detailCTSP.idChatLieu = $scope.chiTietSanPham.idChatLieu.id;
        $scope.detailCTSP.idKichCo = $scope.chiTietSanPham.idKichCo.id;
        $scope.detailCTSP.idHang = $scope.chiTietSanPham.idHang.id;
        $scope.detailCTSP.idTheLoai = $scope.chiTietSanPham.idTheLoai.id;
        $scope.detailCTSP.idSanPham = $scope.chiTietSanPham.idSanPham.id;
        $scope.detailCTSP.idMauSac = $scope.chiTietSanPham.idMauSac.id;
        localStorage.setItem("dgTenSp", response.data.idSanPham.ten);
        if ($scope.validateSlideShow($scope.chiTietSanPham.anh)) {
          $scope.images.push($scope.chiTietSanPham.anh);
        }
        if ($scope.validateSlideShow($scope.chiTietSanPham.anh2)) {
          $scope.images.push($scope.chiTietSanPham.anh2);
        }
        if ($scope.validateSlideShow($scope.chiTietSanPham.anh3)) {
          $scope.images.push($scope.chiTietSanPham.anh3);
        }
        if ($scope.validateSlideShow($scope.chiTietSanPham.anh4)) {
          $scope.images.push($scope.chiTietSanPham.anh4);
        }
        if ($scope.validateSlideShow($scope.chiTietSanPham.anh5)) {
          $scope.images.push($scope.chiTietSanPham.anh5);
        }
        $scope.detailCTSP.tenSP = $scope.chiTietSanPham.idSanPham.ten;
        console.log($scope.images);
        console.log($scope.chiTietSanPham)
        $scope.getMauSac = function () {
          $http
            .get(
              chiTietSanPhamApi +
              "/fe-khach-hang/mau-sac?tenSP=" +
              $scope.chiTietSanPham.idSanPham.ten
            )
            .then(
              function (response) {
                $scope.listMS = response.data;
                $scope.selectMS = $scope.listMS[0].id;
                console.log($scope.listMS);
                console.log($scope.selectMS);
                $scope.handleRadioClick(
                  $scope.listMS[0].ten,
                  $scope.detailCTSP.tenSP
                );
              },
              function (err) {
                console.log(err);
              }
            );
        };
        $scope.getMauSac();
      },
      function (err) {
        console.log(err);
      }
    );
  };
  $scope.getDataId();

  $scope.listTop8 = [];
  $scope.listChangeEle = [];

  $scope.changeElement = function (idKc, idMs, tenSP) {
    $http
      .get(
        chiTietSanPhamApi +
        "/fe-khach-hang/change-detail?tenSP=" +
        tenSP +
        "&ms=" +
        idMs +
        "&kc=" +
        idKc
      )
      .then(
        function (response) {
          $scope.detailCTSP.id = response.data.id;
          $scope.detailCTSP.gia = response.data.gia;
          $scope.detailCTSP.giaKhuyenMai = response.data.giaKhuyenMai;
          $scope.detailCTSP.trangThai = response.data.trangThai;
          $scope.detailCTSP.soLuong = response.data.soLuong;
          localStorage.setItem("ctsp", $scope.detailCTSP.id)
          // console.log("Test response:" + response.data.id);
          console.log("Test response2222:", localStorage.getItem("ctsp"));

        },
        function (err) {
          console.log(err);
        }
      );
  };

  $scope.handleRadioClick = function (ms, tenSP) {
    console.log(ms, tenSP);
    $scope.selectKC = 0;
    $scope.currentImageIndex = 0;
    $http
      .get(
        chiTietSanPhamApi +
        "/fe-khach-hang/kich-co?tenSP=" +
        tenSP +
        "&tenMS=" +
        ms
      )
      .then(
        function (response) {
          $scope.listKC = response.data;
          console.log($scope.listKC);
        },
        function (err) {
          console.log(err);
        }
      );

    $http
      .get(
        chiTietSanPhamApi +
        "/fe-khach-hang/all-anh?tenSP=" +
        tenSP +
        "&tenMS=" +
        ms
      )
      .then(
        function (response) {
          $scope.images = [];
          $scope.newImage = response.data;
          if ($scope.validateSlideShow(response.data.anh)) {
            $scope.images.push(response.data.anh);
          }
          if ($scope.validateSlideShow(response.data.anh2)) {
            $scope.images.push(response.data.anh2);
          }
          if ($scope.validateSlideShow(response.data.anh3)) {
            $scope.images.push(response.data.anh3);
          }
          if ($scope.validateSlideShow(response.data.anh4)) {
            $scope.images.push(response.data.anh4);
          }
          if ($scope.validateSlideShow(response.data.anh5)) {
            $scope.images.push(response.data.anh5);
          }
          console.log($scope.newImage);
        },
        function (err) {
          console.log(err);
        }
      );
  };

  $scope.ctsp = {
    id: "",
    gia: 0,
    soLuong: 0,
  };

  $scope.checkFail = false;
  $scope.ctsp = {};

  $scope.checkDangNhap2 = false;
  $scope.checkDangNhap3 = true;

  $scope.dangNhap1 = function () {

    // if(){

    // }else
     if ($scope.isLogin == null || $scope.isLogin === "undefined") {
      $scope.checkDangNhap3 = true;
      $scope.checkDangNhap2 = false;
    } else {
      $scope.checkDangNhap3 = false;
      $scope.checkDangNhap2 = true;
    }
  };

  $scope.getPageGH = function () {
    $http.get(gioHangChiTietApi + "/get-page").then(
      function (response) {
        $scope.listGHCT = response.data;
        localStorage.setItem("gioHang", response.data);
        console.log("LOL: ", localStorage.getItem("gioHang"));
        for (var i = 0; i < $scope.listGHCT.length; i++) {
          console.log("IDCTSP", $scope.listGHCT[i].idCTSP)
          localStorage.setItem("idCTSanPham", $scope.listGHCT[i].idCTSP);
          // console.log(localStorage.getItem("idCTSanPham"));
        }

        for (var i = 0; i < $scope.listGHCT.length; i++) {
          if ($scope.listGHCT[i].trangThaiSanPham == 0) {
            $scope.listGHCT[i].trangThaiSanPhamTrueFalse = true;
          } else {
            $scope.listGHCT[i].trangThaiSanPhamTrueFalse = false;
          }
        }
        console.log($scope.listGHCT);
      },
      function (err) {
        console.log(err);
      }
    );
  };
  $scope.getPageGH();
  $scope.checkDangNhap = false;
  $scope.checkDangNhap1 = false;


  $scope.dangNhap = function () {

    if ($scope.isLogin == null || $scope.isLogin === "undefined") {
      $scope.checkDangNhap1 = true;
      $scope.checkDangNhap = false;
    } else {
      $scope.checkDangNhap1 = false;
      $scope.checkDangNhap = true;
    }
  };

  $scope.showAlert = function () {
    $("#myAlert").modal("show");
    $timeout(function () {
      $("#myAlert").modal("hide");
    }, 2000);
  };

  $scope.showAlert1 = function () {
    $("#myAlert1").modal("show");
    $timeout(function () {
      $("#myAlert1").modal("hide");
    }, 2000);
  };

  $scope.showAlert2 = function () {
    $("#myAlert2").modal("show");
    $timeout(function () {
      $("#myAlert2").modal("hide");
    }, 2000);
  };

  $scope.showAlert3 = function () {
    $("#myAlert3").modal("show");
    $timeout(function () {
      $("#myAlert3").modal("hide");
    }, 2000);
  };

  $scope.showAlert4 = function () {
    $("#myAlert4").modal("show");
    $timeout(function () {
      $("#myAlert4").modal("hide");
    }, 2000);
  };

  $scope.test = function (event, soLuongMua, gia, mau, kichCo) {
    console.log(mau, kichCo, $scope.detailCTSP.tenSP, "SLM: " + soLuongMua);
    $scope.flag = true;
    $scope.checkFail = false;
    $scope.checkSoLuong = false;
    $scope.checkAm = false;
    $scope.hetHang = false;
    $scope.soLuongTrong = false;

    // if (soLuongMua === "") {
    //   $scope.showAlert3();
    //   $scope.flag = false;
    //   event.preventDefault();
    // }
    if (kichCo <= 0) {
      $scope.flag = false;
      event.preventDefault();
      $scope.showAlert();
      return;
    }
    if (soLuongMua > $scope.detailCTSP.soLuong) {
      $scope.flag = false;
      $scope.showAlert1();
      event.preventDefault();
    }
    if ($scope.detailCTSP.trangThai === 0) {
      $scope.showAlert2();
      $scope.flag = false;
      event.preventDefault();
    }
    if (soLuongMua <= 0) {
      $scope.flag = false;
      $scope.showAlert4();
      event.preventDefault();
    }
    if ($scope.flag) {
      $http
        .get(
          gioHangChiTietApi +
          "/find-sp?tenSP=" +
          $scope.detailCTSP.tenSP +
          "&mauSac=" +
          mau +
          "&kichCo=" +
          kichCo
        )
        .then(function (response) {
          $scope.ctsp = response.data;
          console.log($scope.ctsp);
          $scope.addToCart($scope.ctsp.id, soLuongMua, gia);
          location.reload();
        });
    }
  };

  $scope.test1 = function (event, soLuongMua, gia, mau, kichCo) {
    console.log(mau, kichCo, $scope.detailCTSP.tenSP, "SLM: " + soLuongMua);
    $scope.flag = true;
    $scope.checkFail = false;
    $scope.checkSoLuong = false;
    $scope.checkAm = false;
    $scope.hetHang = false;
    $scope.soLuongTrong = false;

    // if (soLuongMua === "") {
    //   $scope.showAlert3();
    //   $scope.flag = false;
    //   event.preventDefault();
    // }
    if (kichCo <= 0) {
      $scope.flag = false;
      event.preventDefault();
      $scope.showAlert();
      return;
    }
    if (soLuongMua > $scope.detailCTSP.soLuong) {
      $scope.flag = false;
      $scope.showAlert1();
      event.preventDefault();
    }
    if ($scope.detailCTSP.trangThai === 0) {
      $scope.showAlert2();
      $scope.flag = false;
      event.preventDefault();
    }
    if (soLuongMua <= 0) {
      $scope.flag = false;
      $scope.showAlert4();
      event.preventDefault();
    }
    if ($scope.flag) {
      $http
        .get(
          gioHangChiTietApi +
          "/find-sp?tenSP=" +
          $scope.detailCTSP.tenSP +
          "&mauSac=" +
          mau +
          "&kichCo=" +
          kichCo
        )
        .then(function (response) {
          $scope.ctsp = response.data;
          console.log($scope.ctsp);
          $scope.addToCart1($scope.ctsp.id, soLuongMua, gia);
        });
    }
  };

  $scope.increment = function () {
    $scope.soLuongMua++;
  };

  $scope.decrement = function () {
    if ($scope.soLuongMua > 1) {
      $scope.soLuongMua--;
    }
  };


  $scope.addToCart = function (id, soLuongMua, gia) {
    $scope.cart = {
      id: id,
      soLuong: soLuongMua,
      gia: gia,
    };
    console.log($scope.cart);
    $http
      .put(gioHangChiTietApi + "/add-to-cart", $scope.cart)
      .then(function (response) {
      }),
      function (err) {
        console.log("Error: " + err);
      };
  };

  $scope.addToCart1 = function (id, soLuongMua, gia) {
    $scope.cart = {
      id: id,
      soLuong: soLuongMua,
      gia: gia,
    };
    console.log($scope.cart);
    $http
      .put(gioHangChiTietApi + "/add-to-cart1", $scope.cart)
      .then(function (response) {
      }),
      function (err) {
        console.log("Error: " + err);
      };
  };


  $scope.loadData = function () {
    let temptTen = localStorage.getItem("dgTenSp");
    console.log("TEMPT: " + temptTen);
    $http.get(dangGiaSanPhamApi + "/get-hien-thi-san-pham?page=" + $scope.pageNo + "&tenSp=" + temptTen).then(
      function (response) {
        $scope.listBL = response.data;
        console.log("BL: ", $scope.listBL);
      },
      function (err) {
        console.log(err);
      }
    );
  };
  $scope.loadData();

  $scope.listIdChuaMua = [];
  $scope.checkMuaChua = function () {
    $http.get(dangGiaSanPhamApi + "/get-id-chua-mua?id=" + $scope.idKh).then(
      function (response) {
        $scope.listIdChuaMua = response.data;
        console.log($scope.listIdChuaMua);
      },
      function (err) {
        console.log(err);
      }
    );
  };
  if ($scope.idKh > 0) {
    $scope.checkMuaChua();
  };

  $scope.isLogin = localStorage.getItem('khachHang');
  // console.log("TKKH: " + $scope.isLogin, $scope.isLogin.length);
  console.log("ALO: " + localStorage.getItem('khachHang'));
  console.log("ALOooooo: ", $scope.isLogin);
  $scope.hienThiDanhGia = false;
  $scope.checkLogin = function () {
    if ($scope.isLogin !== "undefined" || $scope.isLogin !== null) {
      $scope.hienThiDanhGia = true;
    }
    if ($scope.isLogin === null || $scope.isLogin === "undefined") {
      $scope.hienThiDanhGia = false;
    };
    console.log("ISLPG: " + $scope.hienThiDanhGia);
  };
  $scope.checkLogin();


  $scope.add = function (event, tenSP) {
    let itExits = false;
    $scope.flag = true;
    $scope.xuLyBinhLuan = false;
    $scope.thieuSao = false;
    $scope.chuaMuaHang = false;
    $scope.dgsp.idKhachHang = $scope.idKh;
    if ($scope.dgsp.soSao < 0) {
      $scope.thieuSao = true;
      $scope.flag = false;
    }
    for (var i = 0; i < $scope.listIdChuaMua.length; i++) {
      if ($scope.detailCTSP.idSanPham == $scope.listIdChuaMua[i]) {
        $scope.flag = false;
        $scope.chuaMuaHang = true;
      }
    };
    event.preventDefault();
    if ($scope.flag) {
      $http.get(dangGiaSanPhamApi + "/check-da-ton-tai-danh-gia?idKh=" + $scope.idKh + "&tenSp=" + tenSP).then(
        function (response) {
          itExits = true;
          $scope.isExits = itExits;
          console.log(response.data);
        },
        function (err) {
          console.log(err);
        }
      );
      $http.post(dangGiaSanPhamApi + "/add?ten=" + tenSP, $scope.dgsp).then(
        function (response) {
          $scope.refresh();
          if (itExits == false) {
            $scope.xuLyBinhLuan = true;
          }
          console.log(response.data);
        },
        function (err) {
          console.log("Error: " + err);
        }
      );
    } else {
      console.log("Fail");
    }
  };

  $scope.refresh = function () {
    $scope.dgsp.soSao = 0;
    $scope.dgsp.noiDung = "";
  };

  $scope.numberDG = 0;
  $scope.getLength = function () {
    $http.get(dangGiaSanPhamApi + "/get-hien-thi-san-pham-length?tenSp=" + localStorage.getItem("dgTenSp")).then(
      function (response) {
        $scope.listLength = response.data.length;
        $scope.numberDG = $scope.listLength;
        console.log($scope.listLength);
      },
      function (err) {
        console.log(err);
      }
    );
  };
  $scope.getLength();



  // Phân trang

  $scope.prevPage = function () {

    $scope.pageNo = 0;
    $scope.loadData();
  };

  $scope.prevPages = function () {
    $scope.pageNo--;
    if ($scope.pageNo < 0) {
      $scope.pageNo = Math.ceil($scope.listLength / 5) - 1;
    }
    $scope.loadData();
  };

  $scope.nextPages = function () {

    $scope.pageNo++;
    console.log(Math.ceil($scope.listLength / 5) - 1)
    if ($scope.pageNo > Math.ceil($scope.listLength / 5) - 1) {
      $scope.pageNo = 0;
    }
    $scope.loadData();
  };

  $scope.nextPage = function () {

    $scope.pageNo = Math.ceil($scope.listLength / 5) - 1;
    $scope.loadData();
  };

  $scope.currentImageIndex = 0;

  $scope.slideShowPrev = function () {
    $scope.currentImageIndex--;
    if ($scope.currentImageIndex < 0) {
      $scope.currentImageIndex = $scope.images.length - 1
    }
  };

  $scope.slideshowNext = function () {
    $scope.currentImageIndex++;
    if ($scope.currentImageIndex === $scope.images.length) {
      $scope.currentImageIndex = 0;
    }
  };

  $interval(function () {
    if ($scope.currentImageIndex < $scope.images.length - 1) {
      $scope.currentImageIndex++;
    } else {
      $scope.currentImageIndex = 0;
    }
  }, 5000);

  $scope.validateSlideShow = function (text) {
    if (text === undefined || text === "" || text === null) {
      return false;
    } else {
      return true;
    }
  }

};
