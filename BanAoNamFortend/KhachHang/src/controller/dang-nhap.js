var app1 = angular.module("DangNhap", []);
app1.controller("MyController", function ($scope, $http, $rootScope) {
  $scope.ketQuaTraVe = false;
  $rootScope.x = {
    id: "",
    ten: "",
  };

  $scope.dangNhapFail = false;
  $scope.dangNhap = function (event) {
    $http
      .get(
        khachHangApi +
          "/login?email=" +
          $scope.loginEmail +
          "&password=" +
          $scope.loginPass
      )
      .then(function (response) {
        $scope.dtKH = response.data;
        let kh = $scope.dtKH;
        console.log($scope.dtKH);
        if (kh == "") {
          $scope.dangNhapFail = true;
          $scope.flag = false;
          event.preventDefault();
        } else {
          $scope.remem();
          window.location.href = "/KhachHang/index.html";
        }
      });
  };

  $scope.isChecked = false;

  $scope.remem = function () {
    var username = $scope.loginEmail;
    var password = $scope.loginPass;
    console.log(username, password);
    console.log($scope.isChecked);
    if ($scope.isChecked) {
      console.log($scope.isChecked);
      localStorage.setItem("savedUsername", username);
      localStorage.setItem("savedPassword", password);
    } else {
      localStorage.removeItem("savedUsername");
      localStorage.removeItem("savedPassword");
    }
  };
  $scope.checkRemem = function () {
    var savedUsername = localStorage.getItem("savedUsername");
    var savedPassword = localStorage.getItem("savedPassword");

    if (savedUsername && savedPassword) {
      $scope.loginEmail = savedUsername;
      $scope.loginPass = savedPassword;
      $scope.isChecked = true;
    }
  };
  $scope.checkRemem();
});
