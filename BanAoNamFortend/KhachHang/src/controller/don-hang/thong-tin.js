window.ThongTinController = function ($scope, $http, $routeParams) {
  var id = $routeParams.id;

  const apiDetailHD = donHangApi + "/thong-tin-hd";
  const apiDetailHDCT = donHangApi + "/thong-tin-hdct";
  const apiHuyHD = donHangApi + "/huy-don-hang";

  // Hóa Đơn
  $scope.getThongTinHD = function () {
    $http.get(`${apiDetailHD}/${id}`).then(function (response) {
      $scope.listHD = response.data;
      $scope.input = response.data;
      console.log("HoaDon", $scope.listHD)
    });
  };
  $scope.getThongTinHD();

  // Hóa Đơn Chi Tiết
  $scope.getThongTinHDCT = function () {
    $http.get(`${apiDetailHDCT}/${id}`).then(function (response) {
      console.log(response.data);
      $scope.listHDCT = response.data;
    });
  };
  $scope.getThongTinHDCT();

  //Hủy Hóa Đơn
  $scope.huyDonHang = function () {
    console.log($scope.input.lyDoHuy);
    if (!$scope.input.lyDoHuy) {
      // alert("Vui lòng chọn lý do hủy đơn hàng.");
      return;
    }
    $http
      .put(`${apiHuyHD}/${id}`, $scope.input.lyDoHuy)
      .then(function (response) {
        // alert("Đơn Hàng Đã Được Hủy");
        window.location.href = "#/da-huy";
      })
      .catch(function (error) {
        console.error("Lỗi khi hủy đơn hàng:", error);
        // Xử lý lỗi nếu cần thiết
      });
  };

  //Quay lại
  $scope.quayLai = function (hd) {
    if (hd.trangThai == 1) {
      location.href = "#/hoan-thanh";
    }
    if (hd.trangThai == 2) {
      location.href = "#/van-chuyen";
    }
    if (hd.trangThai == 3) {
      location.href = "#/dang-giao";
    }
    if (hd.trangThai == 0) {
      location.href = "#/cho-thanh-toan";
    }
  };

  $scope.traTrue = false;
  $scope.traFail = false;
  $scope.checkTraHang = function () {
    var ngayHienTai = new Date();
    for (var i = 0; i < $scope.input.length; i++) {
      var ngay = new Date($scope.input[i].updateAt);
      ngay.setDate(ngay.getDate() + 3);
    }
    console.log("Ngày đơn hàng :", ngay)
    console.log("Ngày hiện tại :", ngayHienTai)
    if (ngay <= ngayHienTai) {
      $scope.traTrue = false;
      $scope.traFail = true;
    } else {
      $scope.traTrue = true;
      $scope.traFail = false;
    }
  }

  //Trả Hàng
  $scope.hoanThanh = false;
  const traHang = donHangApi + "/tra-don-hang";
  $scope.traLaiHang = function (id, event) {
    console.log("Id: ", $scope.input);
    console.log("Id1: ", id);
    event.preventDefault();
    if (!$scope.input.lyDoTra) {
      $scope.hoanThanh = true;
      $scope.flag = false;
      return;
    } else {
      $http
        .put(`${traHang}/${id}`, $scope.input.lyDoTra)
        .then(function (response) {
          $scope.checkTrong();
          window.location.href = "#/tra-hang";
        })
        .catch(function (error) {
          console.error("Lỗi khi trả đơn hàng:", error);
        });
    }

  };

  $scope.isDisabled = true;
  $scope.checkTrong = function () {
    if ($scope.input.lyDoTra <= 0) {
      $scope.isDisabled = true;
    } else {
      $scope.isDisabled = false;
    }
  };
};
