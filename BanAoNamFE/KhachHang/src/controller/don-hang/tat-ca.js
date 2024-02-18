window.tatCaController = function ($http, $scope) {
  $scope.listHDCTDetails = {};
  // Hóa Đơn
  $scope.getHD = function () {
    $http.get(donHangApi + "/get-all-hd").then(function (response) {
      $scope.listHD = response.data;
      $scope.listAllHDCT = [];
      console.log("Hóa đơn :" ,$scope.listHD )

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

  // Nút Mua Lại / Thông tin
  $scope.thayDoiTrangThai = function (hd) {
    if (hd.trangThai == 1 || hd.trangThai == 4 || hd.trangThai == 5) {
      let hdct = $scope.listHDCTDetails[hd.idHoaDon];
      if (hdct && hdct.length > 0) {
        // Duyệt qua tất cả các HDCT
        hdct.forEach(function (hdctItem) {
          console.log("ID CTSP:", hdctItem.idCTSP);
          console.log("Số Lượng:", hdctItem.soLuong);
          console.log("Đơn Giá:", hdctItem.donGia);

          $scope.addToCart(hdctItem.idCTSP, hdctItem.soLuong, hdctItem.donGia);
          // alert("Đã Thêm Đơn Hàng Vào Giỏ Hàng")
          location.href = "./src/pages/gio-hang.html";
        });
      } else {
        console.error(
          "Không có thông tin HDCT cho hóa đơn có ID:",
          hd.idHoaDon
        );
      }
    } else {
      // Ngược lại, chuyển hướng đến đường dẫn thông tin
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
        // alert("Đã thêm vào giỏ hàng");
      }),
      function (err) {
        console.log("Error: " + err);
      };
  };

  // Hiển thị modal khi ấn mua lại
  $scope.openModal = function (hd) {
    $scope.selectedHD = hd;
    if (hd.trangThai == 1 || hd.trangThai == 4 || hd.trangThai == 5) {
      $("#exampleModalToggle5").modal("show");
    } else {
      location.href = "#tt-don-hang/" + hd.idHoaDon;
    }
  };
};
