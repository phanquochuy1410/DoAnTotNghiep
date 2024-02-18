window.TrangChuConTroller = function ($http, $scope, $filter, $interval) {
  $scope.listTop8 = [];
  $scope.listTop4 = [];
  $scope.listKhuyenMai = [];
  $scope.listSanPhamKhuyenMai = [];
  $scope.listLength = 0;
  $scope.page = 0;
  $scope.pageKM = 0;

  $scope.loadTop8 = function () {
    $http.get(chiTietSanPhamApi + "/get-top-8?page=" + $scope.page).then(
      function (response) {
        $scope.listTop8 = response.data;
        $scope.totalPages = response.data.totalPages;
        console.log("Top 8",  $scope.listTop8);
      },
      function (err) {
        console.log(err);
      }
    );
  };

  $scope.loadTopNew4 = function () {
    $http.get(chiTietSanPhamApi + "/get-top-new-4").then(
      function (response) {
        $scope.listTop4 = response.data;
        $scope.listTop4.forEach(element => {
          console.log("Top 4 ", element);
        });
      },
      function (err) {
        console.log(err);
      }
    );
  };

  $scope.loadSanPhamKhuyenMai = function () {
    $http.get(chiTietSanPhamApi + "/get-san-pham-km?page=" + $scope.pageKM).then(
      function (response) {
        $scope.listSanPhamKhuyenMai = response.data;
        console.log($scope.listSanPhamKhuyenMai);
      },
      function (err) {
        console.log(err);
      }
    );
  };

  $scope.loadPageKhuyenMai = function () {
    $http.get(chiTietSanPhamApi + "/fe-khach-hang/get-khuyen-mai?page=" + $scope.pageNo).then(
      function (response) {
        $scope.listKhuyenMai = response.data;
        console.log($scope.listKhuyenMai);
      },
      function (err) {
        console.log(err);
      }
    );
  };

  $scope.loadAllKhuyenMai = function () {
    $http.get(chiTietSanPhamApi + "/fe-khach-hang/get-all-khuyen-mai").then(
      function (response) {
        $scope.listLength = response.data.length;
        console.log($scope.listLength);
      },
      function (err) {
        console.log(err);
      }
    );
  };

  //Phan trang khuyen mai

  $scope.prevPages = function () {
    $scope.pageKM = 0;
    $scope.loadSanPhamKhuyenMai();
  };

  $scope.prevPagess = function () {
    $scope.pageKM--;
    if ($scope.pageKM < 0) {
      $scope.pageKM = Math.ceil($scope.listLength/8) - 1;
    }
    $scope.loadSanPhamKhuyenMai();
  };

  $scope.nextPagess = function () {
    $scope.pageKM++;
    if ($scope.pageKM > Math.ceil($scope.listLength/8) - 1) {
      $scope.pageKM = 0;
    }
    $scope.loadSanPhamKhuyenMai();
  };

  $scope.nextPages = function () {
    $scope.pageKM = Math.ceil($scope.listLength/8) - 1;
    $scope.loadSanPhamKhuyenMai();
  };

  $scope.loadTop8();
  $scope.loadTopNew4();
  $scope.loadPageKhuyenMai();
  $scope.loadAllKhuyenMai();
  $scope.loadSanPhamKhuyenMai();

  //show1 
  $scope.images = [
    "img/banner-1.jpg",
    "img/tet.jpg",
    "img/banner-3.png",
    "img/tet1.jpg",
  ];

  $scope.currentImageIndex = 0;

  $scope.prevImage = function () {
    if ($scope.currentImageIndex > 0) {
      $scope.currentImageIndex--;
    }
  };

  $scope.nextImage = function () {
    if ($scope.currentImageIndex < $scope.images.length - 1) {
      $scope.currentImageIndex++;
    }
  };

  $interval(function () {
    if ($scope.currentImageIndex < $scope.images.length - 1) {
      $scope.currentImageIndex++;
    } else {
      $scope.currentImageIndex = 0;
    }
  }, 5000);


  // Show 2
  $scope.image = [
    "img/mau-1.jpg",
    "img/mau-2.jpg",
    "img/mau-1.jpg",
    "img/mau-2.jpg",
  ];

  $scope.currentImageIndexs = 0;

  $scope.prevImages = function () {
    if ($scope.currentImageIndexs > 0) {
      $scope.currentImageIndexs--;
    }
  };

  $scope.nextImages = function () {
    if ($scope.currentImageIndexs < $scope.image.length - 1) {
      $scope.currentImageIndexs++;
    }
  };

  $interval(function () {
    if ($scope.currentImageIndexs < $scope.image.length - 1) {
      $scope.currentImageIndexs++;
    } else {
      $scope.currentImageIndexs = 0;
    }
  }, 5000);

  // Show 3
  $scope.imagess = [
    "img/mau-3.jpg",
    "img/mau-4.jpg",
    "img/mau-3.jpg",
    "img/mau-4.jpg",
  ];

  $scope.currentImageIndexss = 0;

  $scope.prevImagess = function () {
    if ($scope.currentImageIndexss > 0) {
      $scope.currentImageIndexss--;
    }
  };

  $scope.nextImagess = function () {
    if ($scope.currentImageIndexss < $scope.imagess.length - 1) {
      $scope.currentImageIndexss++;
    }
  };

  $interval(function () {
    if ($scope.currentImageIndexss < $scope.imagess.length - 1) {
      $scope.currentImageIndexss++;
    } else {
      $scope.currentImageIndexss = 0;
    }
  }, 5000);

  // Show 4
  $scope.imagees = [
    "img/tet.jpg",
    "img/tet1.jpg",
    "img/tet2.jpg"
  ];

  $scope.currentImageIndexes = 0;

  $scope.prevImagees = function () {
    if ($scope.currentImageIndexes > 0) {
      $scope.currentImageIndexes--;
    }
  };

  $scope.nextImagees = function () {
    if ($scope.currentImageIndexes < $scope.imagees.length - 1) {
      $scope.currentImageIndexes++;
    }
  };

  $interval(function () {
    if ($scope.currentImageIndexes < $scope.imagees.length - 1) {
      $scope.currentImageIndexes++;
    } else {
      $scope.currentImageIndexes = 0;
    }
  }, 5000);

  //Phân trang1
  $scope.prevPage = function () {
    $scope.page = 0;
    $scope.loadTop8();
  };

  $scope.prevPages = function () {
    if ($scope.page < 1) {
      return;
    }
    $scope.page--;
    $scope.loadTop8();
  };
  $scope.nextPages = function () {
    if ($scope.page > ($scope.totalPages - 1)) {
      return ;
    }
    $scope.page++;
    $scope.loadTop8();
  };
  $scope.nextPage = function () {
    $scope.page = $scope.totalPages - 1;
    $scope.loadTop8();
  };

};

