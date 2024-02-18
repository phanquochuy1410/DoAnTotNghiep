window.dgspXuLyController = function (
  $scope,
  $http,
  $routeParams,
  $location
) {
  
  $scope.listXuLy = [];
  $scope.search = "";
  $scope.pageNo = 0;
  $scope.totalPages = 0;
  $scope.listLength = 0;

  $scope.loadData = function () {
    $http.get(dangGiaSanPhamApi + "/get-all-xu-ly?page=" + $scope.pageNo).then(
      function (response) {
        $scope.listXuLy = response.data;
        console.log($scope.listXuLy);
      },
      function (err) {
        console.log(err);
      }
    );
  };
  $scope.loadData();

  $scope.listIdCheck = [];
  $scope.checkId = function (a) {
    if ($scope.listIdCheck.indexOf(a) >= 0) {
      $scope.removeItem(a);
      console.log($scope.listIdCheck);
    } else {
      $scope.listIdCheck.push(a);
      console.log($scope.listIdCheck);
    }
  };

  $scope.removeItem = function (item) {
    var index = $scope.listIdCheck.indexOf(item);
    if (index !== -1) {
      $scope.listIdCheck.splice(index, 1);
    }
  };

  $scope.ketQua = "";
  $scope.update = function (a) {
    $http.put(dangGiaSanPhamApi + "/update-trang-thai?listID=" + $scope.listIdCheck + "&trangThai=" + a).then(
      function (response) {
        console.log(response.data);
        location.reload();
      },
      function (err) {
        console.log("Error: " + err);
      }
    );
  };

  $scope.getLength = function () {
    $http.get(dangGiaSanPhamApi + "/get-length-tt?by=" + "xuly").then(
      function (response) {
        $scope.listLength = response.data.length;
        $scope.totalPages = Math.ceil($scope.listLength / 5) - 1;
        console.log($scope.listLength);
      },
      function (err) {
        console.log(err);
      }
    );
  };
  $scope.getLength();
  
  $scope.prevPage = function () {
    $scope.pageNo = 0;
    $scope.loadData();
  };
  
  $scope.prevPages = function () {
    $scope.pageNo--;
    if ($scope.pageNo < 0) {
      $scope.pageNo = Math.ceil($scope.listLength / 5) - 1;
    }
    $scope.loadData();
  };
  
  $scope.nextPages = function () {
    $scope.pageNo++;
    if ($scope.pageNo > Math.ceil($scope.listLength / 5) - 1) {
      $scope.pageNo = 0;
    }
    $scope.loadData();
  };
  
  $scope.nextPage = function () {
    $scope.pageNo = Math.ceil($scope.listLength / 5) - 1;
    $scope.loadData();
  };

};
