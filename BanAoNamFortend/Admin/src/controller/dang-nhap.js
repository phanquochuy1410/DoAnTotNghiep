var app1 = angular.module("DangNhap1", []);
app1.controller("MyController1", function ($scope, $http, $rootScope) {
  $scope.ketQuaTraVe = false;
  $rootScope.x = {
    id: "",
    ten: "",
  };

  $scope.dangNhap = function () {
    $http
      .get(
        nhanVienApi +
        "/login-nhan-vien?email=" +
        $scope.loginEmail +
        "&password=" +
        $scope.loginPass
      )
      .then(function (response) {
        $scope.dtKH = response.data;
        let kh = $scope.dtKH;
        console.log($scope.dtKH);
        if (kh == "") {
          alert("Email hoặc Mật Khẩu sai");
        } else {
          if (kh.chucVu == 0) {
            // alert("Đăng nhập thành công! Xin chào bạn " + kh.ten);
            localStorage.setItem("emailNV", $scope.loginEmail);
            $scope.remem();
            window.location.href = "/Admin/index.html";
          } else {
            // alert("Đăng nhập thành công! Xin chào bạn " + kh.ten);
            localStorage.setItem("emailNV", $scope.loginEmail);
            $scope.remem();
            window.location.href = "/Admin/index-nhan-vien.html";
          }
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
