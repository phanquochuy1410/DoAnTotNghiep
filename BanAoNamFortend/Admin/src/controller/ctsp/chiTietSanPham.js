window.chiTietSanPhamController = function (
  $scope,
  $http,
  $routeParams,
  $location,
  $timeout,
  $parse,
  $filter
) {

  $scope.totalPages = 0;
  $scope.selectNum = "0";
  $scope.pageNo = 0;
  $scope.trangThai = -1;
  $scope.listCTSP = [];
  $scope.listFull = {};
  $scope.listLength = 0;
  $scope.min = 0;
  $scope.max = 0;
  $scope.eleName = "all";
  $scope.findByNumber = "";
  $scope.findByElement = "";
  $scope.names = ["Sản Phẩm", "Hãng", "Chất Liệu", "Màu Sắc", "Thể Loại", "Kích Cỡ"];
  $scope.numbers = ["Số lượng", "Giá"];
  $scope.chatLieu = "";
  $scope.mauSac = "";
  $scope.theLoai = "";
  $scope.sanPham = "";
  $scope.hang = "";
  $scope.kichCo = "";

  $scope.checkApi = function (ten, page, ifSearch) {
    if(ifSearch === true){
      $scope.pageNo = 0;
      page = 0;
    }
    if (!/^\d+$/.test($scope.min)) {
      $scope.invalidNumber = true;
    } else {
      $scope.invalidNumber = false;
    }

    var first = ""; 
  
    first = "/ctsp-search";

    let apiUrl = chiTietSanPhamApi + first;

    apiUrl += "?page=" + page + "&";

    if ($scope.mauSac && $scope.mauSac.trim() !== "") {
      apiUrl += "mauSac=" + $scope.mauSac + "&";
    }

    if ($scope.kichCo && $scope.kichCo.trim() !== "") {
      apiUrl += "tenKichCo=" + $scope.kichCo + "&";
    }

    if ($scope.chatLieu && $scope.chatLieu.trim() !== "") {
      apiUrl += "tenChatLieu=" + $scope.chatLieu + "&";
    }

    if ($scope.theLoai && $scope.theLoai.trim() !== "") {
      apiUrl += "tenTheLoai=" + $scope.theLoai + "&";
    }

    if ($scope.hang && $scope.hang.trim() !== "") {
      apiUrl += "tenHang=" + $scope.hang + "&";
    }

    if (ten && ten.trim() !== "") {
      apiUrl += "tenSanPham=" + ten + "&";
    }

    if ($scope.min) {
      apiUrl += "giaBatDau=" + $scope.min + "&";
    }

    if ($scope.max) {
      apiUrl += "giaKetThuc=" + $scope.max + "&";
    }

    if (apiUrl.charAt(apiUrl.length - 1) === "&") {
      apiUrl = apiUrl.slice(0, -1);
    }
    console.log(apiUrl);
    return apiUrl;
  };

  $scope.checkApiLength = function (ten) {
    if (!/^\d+$/.test($scope.min)) {
      $scope.invalidNumber = true;
    } else {
      $scope.invalidNumber = false;
    }

    var first = ""; 
  
    first = "/ctsp-search-length";

    let apiUrl = chiTietSanPhamApi + first + "?";

    if ($scope.mauSac && $scope.mauSac.trim() !== "") {
      apiUrl += "mauSac=" + $scope.mauSac + "&";
    }

    if ($scope.kichCo && $scope.kichCo.trim() !== "") {
      apiUrl += "tenKichCo=" + $scope.kichCo + "&";
    }

    if ($scope.chatLieu && $scope.chatLieu.trim() !== "") {
      apiUrl += "tenChatLieu=" + $scope.chatLieu + "&";
    }

    if ($scope.theLoai && $scope.theLoai.trim() !== "") {
      apiUrl += "tenTheLoai=" + $scope.theLoai + "&";
    }

    if ($scope.hang && $scope.hang.trim() !== "") {
      apiUrl += "tenHang=" + $scope.hang + "&";
    }

    if (ten && ten.trim() !== "") {
      apiUrl += "tenSanPham=" + ten + "&";
    }

    if ($scope.min) {
      apiUrl += "giaBatDau=" + $scope.min + "&";
    }

    if ($scope.max) {
      apiUrl += "giaKetThuc=" + $scope.max + "&";
    }

    if (apiUrl.charAt(apiUrl.length - 1) === "&") {
      apiUrl = apiUrl.slice(0, -1);
    }
    
    return apiUrl;
  };

  $scope.loadData = function () {
    $http.get(chiTietSanPhamApi + "/get-all?page=" + $scope.pageNo).then(
      function (response) {
        $scope.listCTSP = response.data;
        console.log("Sản phẩm",$scope.listCTSP);
      },
      function (err) {
        console.log(err);
      }
    );
  };
  // $scope.loadData();

  // $scope.findAll = function () {
  //   $http.get(chiTietSanPhamApi + "/find-all").then(
  //     function (response) {
  //       $scope.listLength = response.data.length;
  //       console.log(Math.ceil($scope.listLength / 10));
  //     },
  //     function (err) {
  //       console.log(err);
  //     }
  //   );
  // };
  // $scope.findAll();

  $scope.getAllNoPage = function () {
    $http.get(chiTietSanPhamApi + "/get-all-no-page").then(
      function (response) {
        $scope.listFull = response.data;
      },
      function (err) {
        console.log(err);
      }
    );
  };

  $scope.delete = function (event, id) {
    event.preventDefault();
    $http.delete(chiTietSanPhamApi + "/delete/" + id).then(
      function (response) {
      },
      function (response) { }
    );
  };

  $scope.newSearchLength = function (ten) {
    console.log(ten);
    let api = $scope.checkApiLength(ten);
    $http.get(api).then(
        function (response) {
          $scope.listLength = response.data;
          $scope.totalPages = Math.ceil($scope.listLength / 10) - 1;
          console.log("TL " + $scope.listLength);
        },
        function (err) {
          console.log(err);
        }
      );
  };
  $scope.newSearchLength();

  $scope.newSearch = function (ten, validate) {
    $scope.newSearchLength(ten);
    console.log(ten, validate);
    let api = $scope.checkApi(ten, $scope.pageNo, validate);
    $http.get(api).then(
        function (response) {
          $scope.listCTSP = response.data;
          console.log($scope.listCTSP);
        },
        function (err) {
          console.log(err);
        }
      );
  };
  $scope.newSearch();



  // $scope.searchLength = function (ten) {
  //   console.log(ten);
  //   $http.get(chiTietSanPhamApi + "/search-length?name=" + ten
  //     + "&min=" + $scope.min + "&max="
  //     + $scope.max + "&tt=" + $scope.trangThai
  //     + "&by=" + $scope.eleName).then(
  //       function (response) {
  //         $scope.listLength = response.data.length;
  //         console.log($scope.listLength);
  //       },
  //       function (err) {
  //         console.log(err);
  //       }
  //     );
  // };


  $scope.exportData = function () {
    var blob = new Blob([document.getElementById('exportable').innerHTML], {
      type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
    });
    saveAs(blob, "ctspExport.xls");
  };

  $scope.prevPage = function (ten) {
    $scope.newSearchLength(ten);
    $scope.pageNo = 0;
    $scope.newSearch(ten, false);
  };

  $scope.prevPages = function (ten) {
    $scope.newSearchLength(ten);
    $scope.pageNo--;
    if ($scope.pageNo < 0) {
      $scope.pageNo = Math.ceil($scope.listLength / 10) - 1;
    }
    $scope.newSearch(ten, false);
  };

  $scope.nextPages = function (ten) {
    $scope.newSearchLength(ten);
    $scope.pageNo++;
    if ($scope.pageNo > Math.ceil($scope.listLength / 10) - 1) {
      $scope.pageNo = 0;
    }
    $scope.newSearch(ten, false);
  };

  $scope.nextPage = function (ten) {

    $scope.newSearchLength(ten);
    $scope.pageNo = Math.ceil($scope.listLength / 10) - 1;
    $scope.newSearch(ten, false);
  };


  $scope.getCbbCL = function () {
    $http.get(chatLieuApi + "/get-cbb-chat-lieu").then(
      function (response) {
        $scope.chatLieus = response.data;
        console.log($scope.chatLieus);
        return $scope.chatLieus;
      },
      function (err) {
        console.log(err);
      }
    );
  }

  $scope.getCbbHang = function () {
    $http.get(hangApi + "/get-cbb-hang").then(
      function (response) {
        $scope.hangs = response.data;
        console.log($scope.hangs);
        return $scope.hangs;
      },
      function (err) {
        console.log(err);
      }
    );
  }

  $scope.getCbbSanPham = function () {
    $http.get(sanPhamApi + "/get-cbb-san-pham").then(
      function (response) {
        $scope.sanPhams = response.data;
        console.log($scope.sanPhams);
        return $scope.sanPhams;
      },
      function (err) {
        console.log(err);
      }
    );
  }

  $scope.getCbbKichCo = function () {
    $http.get(kichCoApi + "/get-cbb-kich-co").then(
      function (response) {
        $scope.kichCos = response.data;
        console.log($scope.kichCos);
        return $scope.kichCos;
      },
      function (err) {
        console.log(err);
      }
    );
  }

  $scope.getCbbTheLoai = function () {
    $http.get(theLoaiApi + "/get-cbb-the-loai").then(
      function (response) {
        $scope.theLoais = response.data;
        console.log($scope.theLoais);
        return $scope.theLoais;
      },
      function (err) {
        console.log(err);
      }
    );
  }

  $scope.getCbbMauSac = function () {
    $http.get(mauSacApi + "/get-cbb-mau-sac").then(
      function (response) {
        $scope.mauSacs = response.data;
        console.log($scope.mauSacs);
        return $scope.mauSacs;
      },
      function (err) {
        console.log(err);
      }
    );
  }

  $scope.data = [];

  $scope.doiTT = function (id, tt) {
    
    var trangThai = 0;
    if(tt == 0){
      trangThai = 1;
    }else{
      trangThai = 0;
    }
    console.log("DOITT: ", id, tt, trangThai);
      $http.put(chiTietSanPhamApi + "/update-trang-thai/" + id + "?tt=" + trangThai).then(
        function (response) {
          console.log("RES: ", response.data);
          $scope.newSearch();
        },
        function (err) {
          console.log("Error: " + err);
        }
      );
  };

  $scope.READ = function () {
    $scope.data = [];
    var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xlsx|.xls)$/;
    var xlsxflag = false;
    if ($("#ngexcelfile").val().toLowerCase().indexOf(".xlsx") > 0) {
      xlsxflag = true;
    }else{
      alert("Không phải file excel");
      return;
    }
    var reader = new FileReader();
    reader.onload = function (e) {
      var data = e.target.result;
      if (xlsxflag) {
        var workbook = XLSX.read(data, { type: 'binary' });
      }
      else {
        var workbook = XLS.read(data, { type: 'binary' });
      }

      var sheet_name_list = workbook.SheetNames;
      var cnt = 0;
      sheet_name_list.forEach(function (y) { /*Iterate through all sheets*/

        if (xlsxflag) {
          var exceljson = XLSX.utils.sheet_to_json(workbook.Sheets[y]);
        }
        else {
          var exceljson = XLS.utils.sheet_to_row_object_array(workbook.Sheets[y]);
        }
        if (exceljson.length > 0) {
          for (var i = 0; i < exceljson.length; i++) {
            $scope.data.push(exceljson[i]);
            console.log($scope.data[i]);
            $scope.$apply();
          }
        }
      });
    }
    if (xlsxflag) {
      reader.readAsArrayBuffer($("#ngexcelfile")[0].files[0]);
    }
    else {
      reader.readAsBinaryString($("#ngexcelfile")[0].files[0]);
    }
  };
  
  $scope.SaveAll = function () {
      $http.post(chiTietSanPhamApi + "/excel-add-all", $scope.data).then(
        function (response) {
          console.log(response.data);
          location.reload();
        },
        function (err) {
          console.log("Error: " + err);
        }
      );
      $scope.data = [];
  };



  $scope.getCbbHang();
  $scope.getCbbKichCo();
  $scope.getCbbMauSac();
  $scope.getCbbSanPham();
  $scope.getCbbHang();
  $scope.getCbbTheLoai();
  $scope.getCbbCL();
  $scope.getAllNoPage();
};
