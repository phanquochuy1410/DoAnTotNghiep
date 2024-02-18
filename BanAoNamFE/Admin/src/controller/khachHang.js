window.khachHangController = function ($scope, $http, $routeParams) {

    const apiHienThi = khachHangApi + "/hien-thi?page="
    const apiDetail = khachHangApi + "/detail"
    const apiSearch = khachHangApi + "/search";

    $scope.listKH = [];
    $scope.pageNo = 0;
    $scope.pageSize = 10;
    $scope.totalPages = 0;

    var id = $routeParams.id;

    $scope.totalPages = 0;
    $scope.listPageNo = [];
    $scope.pageNo = 0;
    $scope.vitriUpdate = -1;
  
    $scope.loadData = function () {
      $http.get(apiHienThi + $scope.pageNo).then(
        function (response) {
          $scope.listKH = response.data;
        },
        function (err) {
          console.log(err);
        }
      );
    };
    $scope.changePageDL = function (newPage) {
      $scope.pageNo = newPage;
      $scope.loadData();
    };
    $scope.loadData();

    $scope.input = {
       id: "",
       ten: "",
       soDienThoai: "",
       gioiTinh: "",
       email: "",
       idDiaChi: "",
    }

    $http.get(`${apiDetail}/${id}`).then(function (response) {
      console.log(response.data);
      $scope.input = response.data;
    })

    // Search
    $scope.searchKH = function () {
      var sdtSearch = $scope.sdtSearch;
  
      if ($scope.sdtSearch === undefined || $scope.sdtSearch.trim() === "") {
        $scope.loadData();
      } else {
        $http
          .get(apiSearch +"?sdtSearch=" + sdtSearch)
          .then(function (response) {
            if (response.data && response.data.length > 0) {
              $scope.listKH = response.data;
            } else {
              alert("Không tìm thấy khách hàng với số điện thoại này.");
            }
          });
      }
    };



}