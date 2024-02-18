window.SanPhamMoiConTroller = function ($http, $scope, $filter) {
  $scope.listSPM = [];

  $scope.page = 0;
  $scope.listPage = 0;
  $scope.totalPages = 0;

  $scope.searchTenSanPham = "";

  $scope.invalidNumber = false;


  $scope.loadSanPhamMoi = function () {
    $scope.searchThuocTinh = function () {
      if (!/^\d+$/.test($scope.giaBatDau)) {
        $scope.invalidNumber = true;
      } else {
        $scope.invalidNumber = false;
      }

      var apiUrl = sanPhamMoiApi + '/hien-thi?page=' + $scope.page + "&";

      if ($scope.searchTenSanPham && $scope.searchTenSanPham.trim() !== "") {
        apiUrl += "tenSanPham=" + $scope.searchTenSanPham + "&";
      }

      if ($scope.giaBatDau && $scope.giaBatDau.trim() !== "") {
        apiUrl += "giaBatDau=" + $scope.giaBatDau + "&";
      }

      if ($scope.giaKetThuc && $scope.giaKetThuc.trim() !== "") {
        apiUrl += "giaKetThuc=" + $scope.giaKetThuc + "&";
      }

      if (apiUrl.charAt(apiUrl.length - 1) === "&") {
        apiUrl = apiUrl.slice(0, -1);
      }

      $http.get(apiUrl).then(function (response) {
        $scope.totalPages = response.data.length;
        $scope.listSPM = response.data;
        console.log($scope.listSPM);
      });
    };
    $scope.searchThuocTinh();
  };
  $scope.loadSanPhamMoi();

  //Phân trang
  $scope.prevPage = function () {
    $scope.page = 0;
    $scope.loadSanPhamMoi();
  };

  $scope.prevPages = function () {
    if ($scope.page < 1) {
      $scope.page = Math.ceil($scope.listPage / 9);
    }
    $scope.page--;
    $scope.loadSanPhamMoi();
  };
  $scope.nextPages = function () {
    if ($scope.page > Math.ceil($scope.totalPages / 9)) {
      $scope.page = Math.ceil($scope.totalPages / 9);
    }
    $scope.page++;
    $scope.loadSanPhamMoi();
  };
  $scope.nextPage = function () {
    $scope.page = $scope.totalPages - 1;
    $scope.loadSanPhamMoi();
  };
};
