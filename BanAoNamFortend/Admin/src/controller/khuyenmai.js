window.khuyenMaiController = function (
  $scope,
  $http,
  $filter , $timeout
) {
  $scope.listKM = [];
  $scope.x = {};
  $scope.totalPages = 0;
  $scope.listPageNo = [];
  $scope.pageNo = 0;
  $scope.vitriUpdate = -1;
  var today = new Date();
  $scope.nbd = today.toISOString().split('T')[0];

  $scope.setDate = function () {
    document.getElementById("value").value = $scope.nbd;

  };

  $scope.timKiem = "";
  $scope.trangThai = "";
  $scope.search = function () {
    var apiUrl = khuyenMaiApi + "/tim-kiem?" + "&"
    if ($scope.timKiem && $scope.timKiem.trim() !== "") {
      apiUrl += "tenSearch=" + $scope.timKiem + "&";
    }
    if ($scope.trangThai && $scope.trangThai.trim() !== "") {
      apiUrl += "trangThaiSearch=" + $scope.trangThai + "&";
    }
    if (apiUrl.charAt(apiUrl.length - 1) === "&") {
      apiUrl = apiUrl.slice(0, -1);
    }

    $http.get(apiUrl).then(
      function (response) {
        $scope.listKM = response.data;
        console.log($scope.listKM);
      }
    )
  };

  //load Data
  $scope.loadData = function () {
    $http.get(khuyenMaiApi + "/getAll?page=" + $scope.pageNo).then(
      function (response) {
        $scope.listKM = response.data;
        for (var i = 0; i < response.data.totalPages; i++) {
          $scope.listPageNo.push(i);
        }
        $scope.totalPages = response.data.totalPages;
        $scope.listKM = response.data.content;
        console.log( "List : ",response.data.content);

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
    $scope.x.id = null;
    $scope.x.ngayBatDau = $filter("date")($scope.x.ngayBatDau, "yyyy-MM-dd");
    $scope.x.ngayKetThuc = $filter("date")($scope.x.ngayKetThuc, "yyyy-MM-dd");
    console.log("Tg: "  + $filter("date")($scope.x.ngayBatDau, "yyyy-MM-dd"))
    let flag = true;
    $scope.checkNBD = false;
    $scope.checkNKT = false;
    $scope.checkTrongNKT = false;
    $scope.checkTrongNBD = false;
    $scope.check = false;

    if ($scope.x.ngayBatDau > $scope.x.ngayKetThuc) {
      flag = false;
      $scope.checkNBD = true;
    }
    if ($scope.x.ngayKetThuc < $scope.x.ngayBatDau) {
      flag = false;
      $scope.checkNKT = true;
    }
    if (!document.getElementById("ngay-ket-thuc").value) {
      flag = false;
      $scope.checkTrongNKT = true;
    }
    if (!document.getElementById("ngay-bat-dau").value) {
      flag = false;
      $scope.checkTrongNBD = true;
    }

    if(flag){
      $http.post(khuyenMaiApi + "/add", $scope.x).then(
        function (response) {
          $scope.showAlert();
          $scope.loadData();
        },
        function (err) {
          alert("Thêm thất bại !");
        }
      );
    }
  };

  $scope.showAlert = function () {
    $("#myAlert").modal("show");
    $timeout(function () {
      location.reload();
      $("#myAlert").modal("hide");
    }, 2000);
  };

  $scope.timKiem = "";
  $scope.trangThai = "";
  $scope.search = function () {
    var apiUrl = khuyenMaiApi + "/tim-kiem?" + "&"
    if ($scope.timKiem && $scope.timKiem.trim() !== "") {
      apiUrl += "tenSearch=" + $scope.timKiem + "&";
    }
    if ($scope.trangThai && $scope.trangThai.trim() !== "") {
      apiUrl += "trangThaiSearch=" + $scope.trangThai + "&";
    }
    if (apiUrl.charAt(apiUrl.length - 1) === "&") {
      apiUrl = apiUrl.slice(0, -1);
    }

    $http.get(apiUrl).then(
      function (response) {
        $scope.listKM = response.data;
        console.log($scope.listKM);
      }
    )
  };

  $scope.detail = function (event, index) {

  }

  $scope.details = function (event, index) {
    event.preventDefault();
    let ao = $scope.listKM[index];
    $scope.x.id = ao.id;
    $scope.x.ma = ao.ma;
    $scope.x.ten = ao.ten;
    $scope.x.chietKhau = ao.chietKhau;
    console.log(ao.ngayBatDau)

    $scope.x.ngayBatDau = $filter("date")(ao.ngayBatDau, "yyyy-MM-dd");
    document.getElementById("ngay-bat-dau").value = $scope.x.ngayBatDau;
    $scope.x.ngayKetThuc = $filter("date")(ao.ngayKetThuc, "yyyy-MM-dd");
    document.getElementById("ngay-ket-thuc").value = $scope.x.ngayKetThuc;

    $scope.vitriUpdate = index;
  };

  $scope.delete = function (event, id) {
    event.preventDefault();
    $http.delete(khuyenMaiApi + "/delete/" + id).then(
      function (response) {
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
    let idCL = $scope.listKM[$scope.vitriUpdate];
    $scope.x.ngayBatDau = $filter("date")($scope.x.ngayBatDau, "yyyy-MM-dd");
    $scope.x.ngayKetThuc = $filter("date")($scope.x.ngayKetThuc, "yyyy-MM-dd");
    console.log($scope.vitriUpdate);
    $http.put(khuyenMaiApi + "/update/" + idCL.id, $scope.x).then(
      function (response) {
        $scope.showAlert();
        // $scope.updateHetHan();
        $scope.loadData();
      },
      function (response) {
        alert("Sửa thất bại !");
      }
    );
  };

  $scope.updateHetHan = function () {
    $http.put(khuyenMaiApi + "/update-tt-km").then(
      function (response) {
        // $scope.showAlert();
        $scope.loadData();
      },
      function (response) {
        alert("Sửa thất bại !");
      }
    );
  };
  $scope.updateHetHan();


  //Phân trang

  $scope.prevPages = function () {
    if ($scope.pageNo < 1) {
      return ;
    }
    $scope.pageNo--;
    $scope.loadData();
  };
  $scope.nextPages = function () {
    if ($scope.pageNo + 1 >= $scope.totalPages) {
      return ;
    }
    $scope.pageNo++;
    $scope.loadData();
  };
  $scope.nextPage = function () {
    $scope.pageNo = $scope.totalPages - 1;
    $scope.loadData();
  };
}