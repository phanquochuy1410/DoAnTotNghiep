var app3 = angular.module("DatLaiMatKhau", []);
app3.controller(
  "DatLaiMatKhauController",
  function ($scope, $http, $rootScope) {
    $scope.datMK = function () {
      $scope.getData = $rootScope.sendData;
      console.log($scope.getData);
      if ($scope.checkPass === 0) {
        $http({
          method: "PUT",
          url: khachHangApi + "/set-pass?email=" + $scope.getData,
          headers: {
            newPass: $scope.Pass,
          },
        }).then(
          function (response) {
            var passE = response.headers("newPass");
            console.log(passE);
          },
          function (error) {
            console.log("Error: " + error);
          }
        );
      } else {
        alert("Hai mật khẩu không khớp");
      }
    };

    $scope.checkPass = function () {
      var pass = $scope.Pass;
      var cfPass = $scope.cfPass;
      console.log(pass);
      console.log(cfPass);
      var comparison = pass.localeCompare(cfPass);
      return comparison;
    };
  }
);
