window.hoanThanhController = function ($scope, $http) {
  $scope.listHDCTDetails = {};
  // Hóa Đơn
  $scope.getHD = function () {
    $http.get(donHangApi + "/hoan-thanh").then(function (response) {
      $scope.listHD = response.data;
      console.log("THIS IS LIST HD", $scope.listHD);
      $scope.listAllHDCT = [];
      $scope.input = response.data;

      // Sử dụng Promise.all để đợi cho tất cả các cuộc gọi getHDCT kết thúc
      var promises = $scope.listHD.map(function (hd) {
        return $scope.getHDCT(hd.idHoaDon);
      });

      Promise.all(promises).then(function () {
        console.log("Tất cả các chi tiết hóa đơn đã được tải xong.");
      });
    });
  };
  $scope.getHD();

  // Hóa Đơn Chi Tiết
  $scope.getHDCT = function (id) {
    return $http
      .get(donHangApi + "/get-hdct?id=" + id)
      .then(function (response) {
        $scope.listHDCTDetails[id] = response.data;
        console.log(response.data);
        console.log("Đã tải HDCT cho hóa đơn có ID:", id);
      })
      .catch(function (error) {
        console.error("Lỗi khi tải HDCT cho hóa đơn có ID:", id, error);
      });
  };



  //Trả Hàng

  const apiHuyHD = donHangApi + "/tra-don-hang";
  $scope.traLaiHang = function (id) {
    console.log(id);
    if (!$scope.input.lyDoTra) {
      $scope.hoanThanh = true;
      $scope.flag = false;
      return;
    }
    $http
      .put(`${apiHuyHD}/${id}`, $scope.input.lyDoTra)
      .then(function (response) {
        console.log("Okkkkkkkkk", response.data);
        $scope.checkTrong();
        // window.location.href = "#/tra-hang";
      })
      .catch(function (error) {
        console.error("Lỗi khi trả đơn hàng:", error);
      });
  };

  $scope.y = {
    idHD: ""
  }
  $scope.traTrue = false;
  $scope.traFail = false;
  
  $scope.layId = function (idHD) {
    $scope.y.idHD = idHD;
    console.log("Id", $scope.y.idHD);
    var ngayHienTai = new Date();
    for (var i = 0; i < $scope.input.length; i++) {
      if ($scope.input[i].idHoaDon === idHD) {
        var ngay = new Date($scope.input[i].updateAt);
        ngay.setDate(ngay.getDate() + 3);
      }
    }
    console.log("HOA DON", ngay);
    if (ngay <= ngayHienTai) {
      $scope.traTrue = false;
      $scope.traFail = true;
    } else {
      $scope.traTrue = true;
      $scope.traFail = false;
    }

  };
  $scope.hoanThanh = false;
  $scope.traLaiHang1 = function (id) {
    if (!$scope.input.lyDoTra) {
      $scope.hoanThanh = true;
      $scope.flag = false;
      return;
    }
    console.log($scope.y.idHD);
    $http
      .put(`${apiHuyHD}/${$scope.y.idHD}`, $scope.input.lyDoTra)
      .then(function (response) {
        console.log("Okkkkkkkkk", response.data);
        $scope.checkTrong();
        window.location.href = "#/tra-hang";
      })
      .catch(function (error) {
        console.error("Lỗi khi trả đơn hàng:", error);
      });
  };


  $scope.isDisabled = true;
  $scope.checkTrong = function () {
    if ($scope.input.lyDoTra <= 0) {
      $scope.isDisabled = true;
    } else {
      $scope.isDisabled = false;
    }
  };

  $scope.z = {
    tenSanPham: "",
    soLuongSP: "",
    donGiaSP: ""
  }

  $scope.muaLai = function (hd) {
    let hdct = $scope.listHDCTDetails[hd.idHoaDon];
    console.log("Sản phẩm", hdct);
    if (hdct && hdct.length > 0) {
      // Duyệt qua tất cả các HDCT
      hdct.forEach(function (hdctItem) {
        $scope.z.tenSanPham = hdctItem.idCTSP;
        $scope.z.soLuongSP = hdctItem.soLuong;
        $scope.z.donGiaSP = hdctItem.donGia;
        console.log("ID CTSP:", $scope.z.tenSanPham);
        console.log("Số Lượng:", $scope.z.soLuongSP);
        console.log("Đơn Giá:", $scope.z.donGiaSP);
      });
    }
  };

  // Nút Mua Lại / Thông tin
  $scope.thayDoiTrangThai = function (hd) {
    if (hd.trangThai == 1 || hd.trangThai == 4 || hd.trangThai == 5) {
      $scope.addToCart($scope.z.tenSanPham, $scope.z.soLuongSP, $scope.z.donGiaSP)
      location.href = "./src/pages/gio-hang.html"
    } else {
      location.href = "#tt-don-hang/" + hd.idHoaDon;
    }
  };

  // Mua Lại
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
};
