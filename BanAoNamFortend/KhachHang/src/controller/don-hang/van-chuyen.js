window.vanChuyenController = function($scope, $http){
  $scope.listHDCTDetails = {};
  // Hóa Đơn
  $scope.getHD = function () {
    $http.get(donHangApi + "/van-chuyen").then(function (response) {
      $scope.listHD = response.data;
      $scope.listAllHDCT = [];

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
    console.log(hd);
    if (hd.trangThai == 1) {
      // Nếu trạng thái là 1, chuyển hướng đến đường dẫn mua lại
      // location.href = "#muaLai/" + dh.idCTSP
    } else {
      // Ngược lại, chuyển hướng đến đường dẫn thông tin
      location.href = "#tt-don-hang/" + hd.idHoaDon;
    }
  };
}