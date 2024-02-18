window.IndexController = function ($http, $scope) {
  $scope.listTheLoai = [];

  $scope.loadTheLoai = function () {
    console.log("jshfahoaofjiaf");
    $http.get(trangChuApi + "/the-loai").then(function (response) {
      $scope.listTheLoai = response.data;
      console.log("Thành công" + $scope.listTheLoai);
    });
  };
  $scope.loadTheLoai();
};
