window.CuaHangKhuyenMaiConTroller = function ($http, $scope, $filter) {
  $scope.listCHKM = [];
  $scope.listMS = [];
  $scope.listKC = [];
  $scope.listCL = [];
  $scope.listTL = [];

  $scope.chkm = {};

  $scope.page = 0;
  $scope.listPage = 0;
  $scope.totalPages = 0;

  $scope.tenMau = "";
  $scope.kichCo = "";
  $scope.chatLieu = "";
  $scope.theLoai = "";
  $scope.giaBatDau = "";
  $scope.giaKetThuc = "";

  $scope.listCheckMauSac = [];
  $scope.listCheckKichCo = [];
  $scope.listCheckChatLieu = [];
  $scope.listCheckTheLoai = [];

  $scope.searchTenSanPham = "";

  $scope.invalidNumber = false;

  $scope.loadDataCuaHang = function () {
    $scope.searchThuocTinh = function () {
      if (!/^\d+$/.test($scope.giaBatDau)) {
        $scope.invalidNumber = true;
      } else {
        $scope.invalidNumber = false;
      }

      var apiUrl = cuaHangKhuyenMaiApi + "/hien-thi?page=" + $scope.page + "&";

      if ($scope.listCheckMauSac.length > 0) {
        apiUrl += "tenMau=" + $scope.listCheckMauSac.join(",") + "&";
      }

      if ($scope.tenMau && $scope.tenMau.trim() !== "") {
        apiUrl += "tenMau=" + $scope.tenMau + "&";
      }

      if ($scope.listCheckKichCo.length > 0) {
        apiUrl += "kichCo=" + $scope.listCheckKichCo.join(",") + "&";
      }

      if ($scope.kichCo && $scope.kichCo.trim() !== "") {
        apiUrl += "kichCo=" + $scope.kichCo + "&";
      }

      if ($scope.listCheckChatLieu.length > 0) {
        apiUrl += "chatLieu=" + $scope.listCheckChatLieu.join(",") + "&";
      }

      if ($scope.chatLieu && $scope.chatLieu.trim() !== "") {
        apiUrl += "chatLieu=" + $scope.chatLieu + "&";
      }

      if ($scope.listCheckTheLoai.length > 0) {
        apiUrl += "theLoai=" + $scope.listCheckTheLoai.join(",") + "&";
      }

      if ($scope.theLoai && $scope.theLoai.trim() !== "") {
        apiUrl += "theLoai=" + $scope.theLoai + "&";
      }

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
        $scope.listCHKM = response.data;
        console.log("Hi ", $scope.listCHKM);
      });
    };
    $scope.searchThuocTinh();
  };
  $scope.loadDataCuaHang();

  $scope.listCheckMauSac = [];
  $scope.checkMauSac = function (a) {
    if ($scope.listCheckMauSac.indexOf(a) >= 0) {
      $scope.removeItemMS(a);
      console.log($scope.listCheckMauSac);
    } else {
      $scope.listCheckMauSac.push(a);
      console.log($scope.listCheckMauSac);
    }
  };

  $scope.removeItemMS = function (item) {
    var index = $scope.listCheckMauSac.indexOf(item);
    if (index !== -1) {
      $scope.listCheckMauSac.splice(index, 1);
    }
  };

  $scope.listCheckKichCo = [];
  $scope.checkKichCo = function (a) {
    if ($scope.listCheckKichCo.indexOf(a) >= 0) {
      $scope.removeItemKC(a);
      console.log($scope.listCheckKichCo);
    } else {
      $scope.listCheckKichCo.push(a);
      console.log($scope.listCheckKichCo);
    }
  };

  $scope.removeItemKC = function (item) {
    var index = $scope.listCheckKichCo.indexOf(item);
    if (index !== -1) {
      $scope.listCheckKichCo.splice(index, 1);
    }
  };

  $scope.listCheckChatLieu = [];
  $scope.checkChatLieu = function (a) {
    if ($scope.listCheckChatLieu.indexOf(a) >= 0) {
      $scope.removeItemCL(a);
      console.log($scope.listCheckChatLieu);
    } else {
      $scope.listCheckChatLieu.push(a);
      console.log($scope.listCheckChatLieu);
    }
  };

  $scope.removeItemCL = function (item) {
    var index = $scope.listCheckChatLieu.indexOf(item);
    if (index !== -1) {
      $scope.listCheckChatLieu.splice(index, 1);
    }
  };

  $scope.listCheckTheLoai = [];
  $scope.checkTheLoai = function (a) {
    if ($scope.listCheckTheLoai.indexOf(a) >= 0) {
      $scope.removeItemTL(a);
      console.log($scope.listCheckTheLoai);
    } else {
      $scope.listCheckTheLoai.push(a);
      console.log($scope.listCheckTheLoai);
    }
  };

  $scope.removeItemTL = function (item) {
    var index = $scope.listCheckTheLoai.indexOf(item);
    if (index !== -1) {
      $scope.listCheckTheLoai.splice(index, 1);
    }
  };

  $scope.soLuongMauSac = 0;
  $scope.loadMauSac = function () {
    $http.get(cuaHangKhuyenMaiApi + "/mau-sac").then(function (response) {
      $scope.listMS = response.data;
      for (var i = 0; i < response.data.length; i++) {
        $scope.soLuongMauSac += response.data[i].soLuong;
      }
    });
  };
  $scope.loadMauSac();

  $scope.soLuongKichCo = 0;
  $scope.loadKichCo = function () {
    $http.get(cuaHangKhuyenMaiApi + "/kich-co").then(function (response) {
      $scope.listKC = response.data;
      for (var i = 0; i < response.data.length; i++) {
        $scope.soLuongKichCo += response.data[i].soLuong;
      }
    });
  };
  $scope.loadKichCo();

  $scope.soLuongTheLoai = 0;
  $scope.loadTheLoai = function () {
    $http.get(cuaHangKhuyenMaiApi + "/the-loai").then(function (response) {
      $scope.listTL = response.data;
      for (var i = 0; i < response.data.length; i++) {
        $scope.soLuongTheLoai += response.data[i].soLuong;
      }
    });
  };
  $scope.loadTheLoai();

  $scope.soLuongChatLieu = 0;
  $scope.loadChatLieu = function () {
    $http.get(cuaHangKhuyenMaiApi + "/chat-lieu").then(function (response) {
      $scope.listCL = response.data;
      for (var i = 0; i < response.data.length; i++) {
        $scope.soLuongChatLieu += response.data[i].soLuong;
      }
    });
  };
  $scope.loadChatLieu();

  //Phân trang
  $scope.prevPage = function () {
    $scope.page = 0;
    $scope.loadDataCuaHang();
  };

  $scope.prevPages = function () {
    if ($scope.page < 1) {
      $scope.page = Math.ceil($scope.listPage / 12);
    }
    $scope.page--;
    $scope.loadDataCuaHang();
  };
  $scope.nextPages = function () {
    if ($scope.page > Math.ceil($scope.totalPages / 12)-1) {
      $scope.page = Math.ceil($scope.totalPages / 12);
    }
    $scope.page++;
    $scope.loadDataCuaHang();
  };
  $scope.nextPage = function () {
    $scope.page = $scope.totalPages - 1;
    $scope.loadDataCuaHang();
  };
};
