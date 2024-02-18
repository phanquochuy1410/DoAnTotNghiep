window.dgspSanPhamController = function (
  $scope,
  $http,
  $routeParams,
  $location
) {
  $scope.listHienThi = [];
  $scope.listDGFromSp = {};
  $scope.by = 'review';
  $scope.modalBy = 0;
  $scope.nameSearch = "";
  $scope.orderBy = 0;
  $scope.pageNo = 0;
  $scope.totalPages = 0;
  $scope.listLength = 0;

  $scope.loadData = function () {
    $http
      .get(
        sanPhamApi +
          "/danh-gia-find?page=" +
          $scope.pageNo +
          "&by=" +
          $scope.by +
          "&order=" +
          $scope.orderBy +
          "&name=" +
          $scope.nameSearch
      )
      .then(
        function (response) {
          $scope.listHienThi = response.data;
          console.log($scope.listHienThi);
        },
        function (err) {
          console.log(err);
        }
      );
  };
  $scope.loadData();

  $scope.getLength = function () {
    $http
    .get(
      sanPhamApi + "/danh-gia-find-length?by=" + $scope.by +
        "&order=" +
        $scope.orderBy +
        "&name=" +
        $scope.nameSearch
    )
    .then(
      function (response) {
        $scope.listLength = response.data.length;
        $scope.totalPages = Math.ceil($scope.listLength / 10) - 1;
        console.log($scope.listLength);
      },
      function (err) {
        console.log(err);
      }
    );
  };
  $scope.getLength();

  $scope.getFromSp = function (id) {
    $scope.temptId = id;
    $http
    .get(
      dangGiaSanPhamApi +
        "/get-from-san-pham?id=" +
        id +
        "&by=" +        
        $scope.modalBy
    )
    .then(
      function (response) {
        $scope.listDGFromSp = response.data;
        console.log($scope.listDGFromSp);
      },
      function (err) {
        console.log(err);
      }
    );
  };

  $scope.prevPage = function (ten) {
    $scope.getLength();
    $scope.pageNo = 0;
    $scope.loadData();
  };

  $scope.prevPages = function (ten) {
    $scope.getLength();
    $scope.pageNo--;
    if ($scope.pageNo < 0) {
      $scope.pageNo = Math.ceil($scope.listLength / 10) - 1;
    }
    $scope.loadData();
  };

  $scope.nextPages = function (ten) {
    $scope.getLength();
    $scope.pageNo++;
    if ($scope.pageNo > Math.ceil($scope.listLength / 10) - 1) {
      $scope.pageNo = 0;
    }
    $scope.loadData();
  };

  $scope.nextPage = function (ten) {
    $scope.getLength();
    $scope.pageNo = Math.ceil($scope.listLength / 10) - 1;
    $scope.loadData();
  };
};
