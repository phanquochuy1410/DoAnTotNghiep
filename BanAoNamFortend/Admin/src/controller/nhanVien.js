window.nhanVienController = function ($scope, $http, $routeParams , $filter) {
  var id = $routeParams.id;

  $scope.pageNo = 0;
  $scope.pageSize = 10;
  $scope.totalPages = 0;
  $scope.listNV1 = [];
  $scope.listNV2 = [];

  const apiDangLam = nhanVienApi + "/hien-thi/dang-lam";
  const apiNghiViec = nhanVienApi + "/hien-thi/nghi-viec";
  const apiDetail = nhanVienApi + "/detail";
  const apiAdd = nhanVienApi + "/add";
  const apiUpdate = nhanVienApi + "/update";
  const apiDelete = nhanVienApi + "/delete";
  const apiSearch = nhanVienApi + "/search";

  // Nhân viên đang làm
  $scope.getDangLam = function () {
    $http.get(apiDangLam + "?page=" + $scope.pageNo).then(function (response) {
      $scope.listNV1 = response.data;
    });
  };
  $scope.changePageDL = function (newPage) {
    $scope.pageNo = newPage;
    $scope.getDangLam();
  };
  $scope.getDangLam();

  // Nhân viên nghỉ việc
  $scope.getNghiViec = function () {
    $http.get(apiNghiViec + "?page=" + $scope.pageNo).then(function (response) {
      $scope.listNV2 = response.data;
    });
  };
  $scope.changePageNV = function (newPage) {
    $scope.pageNo = newPage;
    $scope.getNghiViec();
  };
  $scope.getNghiViec();

  // Detail
  if (id) {
    $http.get(`${apiDetail}/${id}`).then(function (response) {
      console.log(response.data);
      $scope.inputNV = response.data;
      console.log(response.data.ngaySinh);
      $scope.inputNV.ngaySinh = $filter("date")(response.data.ngaySinh, "yyyy-MM-dd");
      $scope.inputNV.ngaySinh = new Date(response.data.ngaySinh);
    });
  } else {
    console.error("ID is not valid.");
  }

  function checkEmail(checkEmail) {
    for (var i = 0; i < $scope.listNV1.length; i++) {
      if ($scope.listNV1[i].email === checkEmail) {
        return true;
      }
    }
    return false;
  }

  function checkSdt(checkSdt) {
    for (var i = 0; i < $scope.listNV1.length; i++) {
      if ($scope.listNV1[i].soDienThoai === checkSdt) {
        return true;
      }
    }
    return false;
  }

  //Add

  $scope.check = false;
  $scope.checksdt = false;
  $scope.add = function () {
    $scope.flag = true;
    let newPost = $scope.inputNV;
    if(checkEmail($scope.inputNV.email)){
      $scope.check = true;
      $scope.flag = false;
      return;
    }
    if(checkSdt($scope.inputNV.soDienThoai)){
      $scope.checksdt = true;
      $scope.flag = false;
      return;
    }
    $http.post(apiAdd, newPost).then(function (response) {
    
      alert("Thêm thành công");
      $scope.inputNV = {};
      $scope.getDangLam();
      $scope.getNghiViec();
      window.location.href = "#/nhan-vien";
    });
  };

  //Update
  $scope.update = function () {
    $http.put(`${apiUpdate}/${id}`, $scope.inputNV).then(function (response) {
      console.log(response.data);
      alert("Sửa thành công");
      window.location.href = "#/nhan-vien";
    });
  };

  //Delete
  $scope.delete = function (id) {
    $http.delete(`${apiDelete}/${id}`).then(function (response) {
      window.location.href = "#/nhan-vien";
      $scope.getNghiViec();
      alert("Xóa thành công");
    });
  };

  //Search nhân viên đang làm
  $scope.searchNhanVienDL = function () {
    var sdtSearch = $scope.sdtSearch;
    var page = $scope.pageNo;
    var trangThai = 1;

    if ($scope.sdtSearch === undefined || $scope.sdtSearch.trim() === "") {
      $scope.getDangLam();
    } else {
      $http
        .get(
          apiSearch +
            "?sdtSearch=" +
            sdtSearch +
            "&page=" +
            page +
            "&trangThai=" +
            trangThai
        )
        .then(function (response) {
          if (response.data && response.data.length > 0) {
            $scope.listNV1 = response.data;
          } else {
            alert("Không tìm thấy nhân viên với số điện thoại này.");
          }
        });
    }
  };

  //Search nhân viên nghỉ việc
  $scope.searchNhanVienNV = function () {
    var sdtSearch = $scope.sdtSearch;
    var page = $scope.pageNo;
    var trangThai = 0;

    if ($scope.sdtSearch === undefined || $scope.sdtSearch.trim() === "") {
      $scope.getNghiViec();
    } else {
      $http
        .get(
          apiSearch +
            "?sdtSearch=" +
            sdtSearch +
            "&page=" +
            page +
            "&trangThai=" +
            trangThai
        )
        .then(function (response) {
          if (response.data && response.data.length > 0) {
            $scope.listNV2 = response.data;
          } else {
            alert("Không tìm thấy nhân viên với số điện thoại này.");
          }
        });
    }
  };

  //CheckBox lấy id
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

  // update Trạng Thái
  $scope.update = function (a) {
    if ($scope.listIdCheck.length <= 0) {
      alert("Bạn cần chọn nhân viên để đổi trạng thái");
    } else {
      $http.put(nhanVienApi + "/update-trang-thai?listID=" + $scope.listIdCheck + "&trangThai=" + a).then(function (response) {
          alert("Thành công");
          location.reload();
        })
        .catch(function (err) {
          console.log("Error: " + err);
        });
    }
  };
};
