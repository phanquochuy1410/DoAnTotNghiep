window.theLoaiController = function ($scope, $http, $routeParams, $location) {
  $scope.listTL = [];
  // $scope.listCV = [];

  $scope.x = {
    id: "",
    ten: "",
    ma: "",
  };

  // $http.get(api + "/showChucVu").then(function (response) {
  //     $scope.listCV = response.data;
  // });

  $scope.totalPages = 0;
  $scope.listPageNo = [];
  $scope.pageNo = 0;
  $scope.vitriUpdate = -1;

  // $scope.maSearch = "";
  // $scope.tenSearch = "";

  //   $scope.loadData = function () {
  //     $scope.search = function (event) {
  //       event.preventDefault();
  //       $http
  //         .get(
  //           api +
  //             "/show?pageNo=" +
  //             $scope.pageNo +
  //             "&ten=" +
  //             $scope.tenSearch +
  //             "&ma=" +
  //             $scope.maSearch
  //         )
  //         .then(function (response) {
  //           $scope.listNV = [];
  //           $scope.listNV = response.data.content;
  //           for (var i = 0; i < response.data.totalPages; i++) {
  //             $scope.listPageNo.push(i);
  //           }
  //           $scope.totalPages = response.data.totalPages;
  //           $scope.listNV = response.data.content;
  //         });
  //     };
  //     $scope.search(event);
  //   };

  //   $scope.loadData();
  //load Data
  $scope.loadData = function () {
    $http.get(theLoaiApi + "/getAll?page=" + $scope.pageNo).then(
      function (response) {
        $scope.listTL = response.data;
        for (var i = 0; i < response.data.totalPages; i++) {
          $scope.listPageNo.push(i);
        }
        $scope.totalPages = response.data.totalPages;
        $scope.listTL = response.data.content;
      },
      function (err) {
        console.log(err);
      }
    );
  };

  $scope.loadData();

  //Add
  $scope.add = function (event) {
    event.preventDefault();

    // Kiểm tra trường ten trước
    if ($scope.x.ten === "") {
      alert("Không được bỏ trống trường 'ten'");
      return;
    }

    // Kiểm tra trùng tên
    if (isDuplicateName($scope.x.ten)) {
      alert("Tên đã tồn tại, không thể thêm trùng tên.");
      return;
    }

    // Thực hiện POST request nếu không trùng tên
    $http.post(theLoaiApi + "/add", $scope.x).then(
      function (response) {
        alert("Thêm thành công !");
        $scope.loadData();
      },
      function (err) {
        alert("Thêm thất bại !");
      }
    );
  };

  // Hàm kiểm tra trùng tên
  function isDuplicateName(name) {
    for (var i = 0; i < $scope.listTL.length; i++) {
      if ($scope.listTL[i].ten === name) {
        return true;
      }
    }
    return false;
  }

  $scope.detail = function (event, index) {
    event.preventDefault();
    let ao = $scope.listTL[index];
    $scope.x.id = ao.id;
    $scope.x.ma = ao.ma;
    $scope.x.ten = ao.ten;
    $scope.vitriUpdate = index;
  };
  
  $scope.delete = function (event, id) {
    event.preventDefault();
    $http.delete(theLoaiApi + "/delete/" + id).then(
      function (response) {
        alert("Xóa thành công !");
        $scope.loadData();
      },
      function (response) {
        alert("Xóa thất bại !");
      }
    );
  };

  // //update
  $scope.update = function (event) {
    event.preventDefault();
    let idCL = $scope.listTL[$scope.vitriUpdate];

    console.log($scope.vitriUpdate);
    // Kiểm tra trường ten trước
    if ($scope.x.ten === "") {
      alert("Không được bỏ trống trường 'ten'");
      return;
    }

    // Kiểm tra trùng tên
    if (isDuplicateName($scope.x.ten)) {
      alert("Tên đã tồn tại, không thể thêm trùng tên.");
      return;
    }
    $http.put(theLoaiApi + "/update/" + idCL.id, $scope.x).then(
      function (response) {
        alert("Sửa thành công !");
        $scope.loadData();
      },
      function (response) {
        alert("Sửa thất bại !");
      }
    );
  };

  // Search
  // $scope.maSearch = "";
  // $scope.tenSearch = "";
  // $scope.search = function (event) {
  //     event.preventDefault();
  //     $http.get(api + "/search?ma=" + $scope.maSearch
  //         + "&ten=" + $scope.tenSearch).then(
  //             function (response) {
  //                 $scope.listNV = response.data;
  //                 console.log("Thành công !", response);
  //             },
  //             function (err) {
  //                 console.log("Thất bại !", err);
  //             }
  //         );
  // };

  //Phân trang

  $scope.prevPage = function () {
    $scope.pageNo = 0;
    $scope.loadData();
  };

  $scope.prevPages = function () {
    if ($scope.pageNo < 1) {
      return alert("Page không được < 0");
    }
    $scope.pageNo--;
    $scope.loadData();
  };
  $scope.nextPages = function () {
    if ($scope.pageNo + 1 >= $scope.totalPages) {
      return alert("Page không được > " + $scope.totalPages);
    }
    $scope.pageNo++;
    $scope.loadData();
  };
  $scope.nextPage = function () {
    $scope.pageNo = $scope.totalPages - 1;
    $scope.loadData();
  };
};