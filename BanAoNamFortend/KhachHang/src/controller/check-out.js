var app = angular.module("CheckOut", []);
app.controller("CheckOutController", function ($scope, $http) {
  $scope.kh = {
    ten: "",
    tenDem: "",
    ho: "",
    sdt: "",
    idKH: "",
  };
  $scope.loadKH = function () {
    $http.get(gioHangChiTietApi + "/load-khach-hang").then(function (response) {
      let khachHang = response.data;
      $scope.kh.ten = khachHang.ten;
      $scope.kh.tenDem = khachHang.tenDem;
      $scope.kh.ho = khachHang.ho;
      $scope.kh.idKH = khachHang.idKH;
      $scope.kh.sdt = khachHang.sdt;
    });
  };
  $scope.loadKH();
});
