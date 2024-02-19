var app2 = angular.module("QuenMatKhau", []);
app2.controller("QuenMatKhauController", function ($scope, $http, $rootScope) {
  $scope.quenMK = function () {
    $http
      .put(
        khachHangApi + "/forgot-password?email=" + $scope.sentEmail,
        alert("Cảm ơn bạn đã sử dụng dịch vụ."),
        ($rootScope.sendData = $scope.sentEmail),
        (window.location.href = "/KhachHang/src/pages/dang-nhap.html")
      )
      .then(function (err) {
        console.log("Tài khoản này chưa được đăng ký.");
      });
  };
});
