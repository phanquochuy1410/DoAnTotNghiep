var app3 = angular.module("DangKi", []);
app3.controller("DangKiController", function ($scope, $http, $rootScope) {
  const apiAdd = khachHangApi + "/add-regis";
  $scope.dangKi = function () {
    let newPost = $scope.inputKH;
    $http.post(apiAdd, newPost).then(function (response) {
      alert("Đăng kí thành công");
      window.location.href = "/KhachHang/src/pages/dang-nhap.html";
    });
  };
});
