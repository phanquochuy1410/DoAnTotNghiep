window.sanPhamController = function ($scope, $http, $timeout) {
  $scope.listSP = [];


  $scope.khuyenMai = [];
  $scope.x = {
    id: 0,
    ten: "",
    ma: "",
    idKhuyenMai: "",
  };


  $scope.totalPages = 0;
  $scope.listPageNo = [];
  $scope.pageNo = 0;
  $scope.vitriUpdate = -1;

  //load Data
  $scope.loadData = function () {
    $http.get(sanPhamApi + "/getAll?page=" + $scope.pageNo).then(
      function (response) {
        $scope.totalPages = response.data.totalPages;
        $scope.listSP = response.data.content;
        console.log($scope.listSP)
      },
      function (err) {
        console.log(err);
      }
    );
  };

  $scope.loadData();
  //load cbb
  $scope.getCbbKM = function () {
    $http.get(sanPhamApi + "/get-cbb-khuyen-mai").then(
      function (response) {
        $scope.khuyenMai = response.data;
        console.log($scope.khuyenMai);
        return $scope.khuyenMai;
      },
      function (err) {
        console.log(err);
      }
    );
  };
  $scope.getCbbKM();

  $scope.listSpAll = [];
  $scope.listIdAll = [];
  $scope.getCbbSP = function () {
    $http.get(sanPhamApi + "/get-cbb-san-pham").then(
      function (response) {
        $scope.listSpAll = response.data;
        console.log($scope.listSpAll);
        for (var i = 0; i < $scope.listSpAll.length; i++) {
          $scope.listIdAll.push(0);
        }
        console.log($scope.listIdAll);
        return $scope.listSpAll;
      },
      function (err) {
        console.log(err);
      }
    );
  };
  $scope.getCbbSP();
  //Add



  $scope.add = function (event) {
    event.preventDefault();
    let flag = true;
    // Kiểm tra trường ten trước
    if ($scope.x.ten === "") {
      $scope.empty = true;
      flag = false;
    }

    // Kiểm tra trùng tên
    if (isDuplicateName($scope.x.ten)) {
      $scope.trung = true;
      flag = false;
    }

    // Thực hiện POST request nếu không trùng tên
    $scope.x.id = null;
    $scope.x.idKhuyenMai = null;
    if (flag) {
      $http.post(sanPhamApi + "/add", $scope.x).then(
        function (response) {
          $scope.trung = false;
          $scope.empty = false;
          $scope.x.ten = "";
          $scope.loadData();
        },
        function (err) {
          alert("Thêm thất bại !");
        }
      );
    }
  };

  $scope.searchTen = "";

  $scope.timKiem = function () {
    $http.get(sanPhamApi + "/tim-kiem-ten?ten=" + $scope.searchTen).then(
      function (response) {
        $scope.listSP = response.data;
        console.log(tim - kiem - ten)
      })
  };

  // Hàm kiểm tra trùng tên
  function isDuplicateName(name) {
    for (var i = 0; i < $scope.listSP.length; i++) {
      if ($scope.listSP[i].ten === name) {
        return true;
      }
    }
    return false;
  }

  // Detai;
  $scope.idSua = "";
  $scope.detail = function (event, id) {
    event.preventDefault();
    $http.get(sanPhamApi + "/get-by/" + id).then(function (response) {
      $scope.x = response.data;
      $scope.x.id = response.data.id;
      $scope.x.ma = response.data.ma;
      $scope.x.ten = response.data.ten;
      $scope.x.idKhuyenMai = response.data.idKhuyenMai;
      $scope.idSua = id;
      console.log($scope.x)
    });
  };

  $scope.delete = function (event, id) {
    event.preventDefault();
    $http.delete(sanPhamApi + "/delete/" + id).then(
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
  $scope.update = function (event, id) {
    event.preventDefault();
    if ($scope.x.ten === "") {
      alert("Không được bỏ trống trường 'ten'");
      return;
    }

    // Kiểm tra trùng tên
    if (isDuplicateName($scope.x.ten)) {
      alert("Tên đã tồn tại, không thể thêm trùng tên.");
      return;
    }
    $http.put(sanPhamApi + "/update/" + id, $scope.x).then(
      function (response) {
        alert("Sửa thành công !");
        $scope.loadData();
      },
      function (response) {
        alert("Sửa thất bại !");
      }
    );
  };

  $scope.listIdCheck = [];
  $scope.checkId = function (a) {
    let indexOfA = $scope.listIdAll.indexOf(a);
    if (indexOfA >= 0) {
      $scope.removeItem(a);
      console.log($scope.listIdAll);
    } else {
      $scope.listIdAll[a - 1] = a;
      console.log($scope.listIdAll);
    }
  };

  $scope.removeItem = function (item) {
    let index = $scope.listIdAll.indexOf(item);
    $scope.listIdAll[index] = 0;
  };

  $scope.idChon = 0;

  $scope.updateKM = function (a) {
    $scope.listIdCheck = [];
    console.log(a);
    $scope.listIdAll.forEach(element => {
      if (element !== 0) {
        $scope.listIdCheck.push(element);
      }
    });
    console.log($scope.listIdCheck);
    $http
      .put(sanPhamApi + "/updateKM?list=" + $scope.listIdCheck + "&idKM=" + a)
      .then(
        function (response) {
          console.log(response.data);
          $scope.showAlert();
        },
        function (err) {
          console.log("Error: " + err);
        }
      );
  };

  $scope.showAlert = function () {
    $("#myAlert").modal("show");
    $timeout(function () {
      location.reload();
      $("#myAlert").modal("hide");
    }, 2000);
  };


  $scope.selectAll = function () {
    $scope.listSpAll.forEach((element) => {
      $scope.removeItem(element.id);
    });
    if ($scope.all === true) {
      $scope.listSpAll.forEach((element, index) => {
        $scope.listIdAll[index] = element.id;
      });
    }
    console.log($scope.listIdAll);
  };

  //Phan trang

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