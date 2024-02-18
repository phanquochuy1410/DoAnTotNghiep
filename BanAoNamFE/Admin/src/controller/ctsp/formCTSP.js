window.formCTSP = function ($scope, $http, $routeParams, $location, $timeout,
  $parse, $filter) {
  $scope.id = $routeParams.id;

  $scope.listAddMany = [];
  $scope.mslc = "";
  $scope.kclc = "";
  $scope.chiTietSanPham = {};
  $scope.chatLieus = [];
  $scope.hangs = [];
  $scope.sanPhams = [];
  $scope.kichCos = [];
  $scope.mauSacs = [];
  $scope.theLoais = [];
  $scope.sanPhamCT = {};

  $scope.soLuongFail = false;
  $scope.giaFail = false;
  $scope.theLoaiFail = false;
  $scope.sanPhamFail = false;
  $scope.mauSacFail = false;
  $scope.chatLieuFail = false;
  $scope.hangFail = false;
  $scope.kichCoFail = false;
  $scope.x = {
    ten: "",
  };
  $scope.luaChonTT = "sanpham";

  $scope.editCTSP = {
    tempt: 0,
    ma: "",
    gia: 0,
    soLuong: 0,
    giaKhuyenMai: 0,
    trangThai: 1,
    mota: "",
    anh: "",
    anh2: "",
    anh3: "",
    anh4: "",
    anh5: "",
    idChatLieu: 0,
    idMauSac: 0,
    idSanPham: 0,
    idTheLoai: 0,
    idHang: 0,
    idKichCo: 0,
    createAt: new Date(),
    updateAt: new Date()
  };

  $scope.getDataId = function () {
    $http.get(chiTietSanPhamApi + "/get-by/" + $scope.id).then(
      function (response) {
        $scope.chiTietSanPham = response.data;
        $scope.editCTSP.ma = $scope.chiTietSanPham.ma;
        $scope.editCTSP.gia = $scope.chiTietSanPham.gia;
        $scope.editCTSP.soLuong = $scope.chiTietSanPham.soLuong;
        $scope.editCTSP.giaKhuyenMai = $scope.chiTietSanPham.giaKhuyenMai;
        $scope.editCTSP.mota = $scope.chiTietSanPham.mota;
        $scope.editCTSP.trangThai = 1;
        $scope.editCTSP.idChatLieu = $scope.chiTietSanPham.idChatLieu.id;
        $scope.editCTSP.idKichCo = $scope.chiTietSanPham.idKichCo.id;
        $scope.editCTSP.idHang = $scope.chiTietSanPham.idHang.id;
        $scope.editCTSP.idTheLoai = $scope.chiTietSanPham.idTheLoai.id;
        $scope.editCTSP.idSanPham = $scope.chiTietSanPham.idSanPham.id;
        $scope.editCTSP.idMauSac = $scope.chiTietSanPham.idMauSac.id;
        $scope.editCTSP.anh = $scope.chiTietSanPham.anh;
        $scope.editCTSP.anh2 = $scope.chiTietSanPham.anh2;
        $scope.editCTSP.anh3 = $scope.chiTietSanPham.anh3;
        $scope.editCTSP.anh4 = $scope.chiTietSanPham.anh4;
        $scope.editCTSP.anh5 = $scope.chiTietSanPham.anh5;
        // $scope.editCTSP.createAt = $filter("date")($scope.chiTietSanPham.create_at, "yyyy-MM-dd");
        // document.getElementById("ntCTSP").value = $scope.editCTSP.createAt;
        // $scope.editCTSP.updateAt = $filter("date")($scope.chiTietSanPham.update_at, "yyyy-MM-dd");
        // document.getElementById("nsCTSP").value = $scope.editCTSP.updateAt;
        console.log($scope.editCTSP);
        console.log(response.data);
      },
      function (err) {
        console.log(err);
      }
    );
  };


  $scope.add = function (event) {
    $scope.flag = true;
    $scope.editCTSP.anh = $scope.fileNames[0];
    $scope.editCTSP.anh2 = $scope.fileNames[1];
    $scope.editCTSP.anh3 = $scope.fileNames[2];
    $scope.editCTSP.anh4 = $scope.fileNames[3];
    $scope.editCTSP.anh5 = $scope.fileNames[4];
    $scope.editCTSP.idMauSac = $scope.mslc.id;
    $scope.editCTSP.idKichCo = $scope.kclc.id;

    if ($scope.editCTSP.soLuong < 0) {
      $scope.soLuongFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.editCTSP.gia < 0) {
      $scope.giaFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.editCTSP.idChatLieu <= 0) {
      $scope.chatLieuFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.editCTSP.idHang <= 0) {
      $scope.hangFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.listChonNhieuMauSac.length < 0) {
      $scope.soLuongFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.listChonNhieuKichCo.length < 0) {
      $scope.giaFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.editCTSP.idSanPham <= 0) {
      $scope.sanPhamFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.editCTSP.idTheLoai <= 0) {
      $scope.theLoaiFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.flag) {
      $http.post(chiTietSanPhamApi + "/add", $scope.editCTSP).then(
        function (response) {
          console.log(response.data);
          location.reload();
        },
        function (err) {
          console.log("Error: " + err);
        }
      );
    } else {
      console.log("Fail");
    }
  };

  $scope.update = function (event) {
    $scope.flag = true;
    $scope.editCTSP.anh = $scope.fileNames[0];
    $scope.editCTSP.anh2 = $scope.fileNames[1];
    $scope.editCTSP.anh3 = $scope.fileNames[2];
    $scope.editCTSP.anh4 = $scope.fileNames[3];
    $scope.editCTSP.anh5 = $scope.fileNames[4];
    if($scope.editCTSP.soLuong === undefined) {
      $scope.flag = false;
      event.preventDefault();
    }
    if($scope.editCTSP.gia === undefined) {
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.editCTSP.soLuong < 0) {
      $scope.soLuongFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.editCTSP.gia < 0) {
      $scope.giaFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.flag) {
      $http.put(chiTietSanPhamApi + "/update/" + $scope.id, $scope.editCTSP).then(
        function (response) {
          console.log(response.data);
        },
        function (err) {
          console.log("Error: " + err);
        }
      );
    } else {
      console.log("Fail");
    }

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

  $scope.thumbnail = {
    dataUrl: [],
  };
  $scope.fileNames = [];
  $scope.fileReaderSupported = window.FileReader != null;
  $scope.photoChanged = function (files, index) {
    if (files != null) {
      var index = this.$index;
      var file = files[0];
      $scope.fileNames[index] = files[0].name;
      $scope.$apply();
      if ($scope.fileReaderSupported && file.type.indexOf("image") > -1) {
        $timeout(function () {
          var fileReader = new FileReader();
          fileReader.readAsDataURL(file);
          fileReader.onload = function (e) {
            $timeout(function () {
              $scope.thumbnail[index] = {
                dataUrl: e.target.result,
              };
            });
          };
        });
      }
    }
  };


  $scope.tenSanPhamChon = "";
  $scope.caculateGKM = function (idSP, gia) {
    $http.get(sanPhamApi + "/get-chiet-khau/" + idSP).then(
      function (response) {
        $scope.sanPhamCT = response.data;
        let giaChietKhau = 0;
        console.log("SPCK: " + $scope.sanPhamCT);
        // let giaKM = 0;
        if (($scope.sanPhamCT === "" || $scope.sanPhamCT === 0) || gia === 0) {
          $scope.editCTSP.giaKhuyenMai = giaChietKhau;
          $scope.listAddMany.forEach(element => {
            element.giaKhuyenMai = 0;
          });
        } else {
          giaChietKhau = gia * 0.01 * $scope.sanPhamCT;
          $scope.editCTSP.giaKhuyenMai = giaChietKhau;
          $scope.listAddMany.forEach(element => {
            element.giaKhuyenMai = giaChietKhau;
          });
        }
        $scope.listAddMany.forEach(element => {
          console.log(element);
        });
      },
      function (err) {
        console.log(err);
      }
    );
    $http.get(sanPhamApi + "/get-by/" + idSP).then(
      function (response) {
        $scope.tenSanPhamChon = response.data.ten;
        // let giaKM = 0
      },
      function (err) {
        console.log(err);
      }
    );
    $scope.changeAllGia(gia);
  };

  $scope.caculateGKMUnq = function (sp, gia) {
    $http.get(sanPhamApi + "/get-chiet-khau/" + sp.idSanPham).then(
      function (response) {
        $scope.sanPhamCT = response.data;
        let giaChietKhau = 0;
        let sanPhamUnq = sp;
        console.log("Unique: " + sanPhamUnq, "GIKM: " + $scope.sanPhamCT);
        // let giaKM = 0;
        if (($scope.sanPhamCT === "" || $scope.sanPhamCT === 0) || gia === 0) {
          sanPhamUnq.giaKhuyenMai = 0;          
        } else {
          giaChietKhau = gia * 0.01 * $scope.sanPhamCT;
          sanPhamUnq.giaKhuyenMai = giaChietKhau;
        }
        $scope.listAddMany.forEach(element => {
          console.log(element);
        });
      },
      function (err) {
        console.log(err);
      }
    );
  };


  $scope.changeAllGia = function (gia) {
    $scope.listAddMany.forEach(element => {
      element.gia = gia;
    }); 
  };

  $scope.changeAllSl = function (sl) {
    $scope.listAddMany.forEach(element => {
      element.soLuong = sl;
    }); 
  };

  $scope.changeAllMt = function (mt) {
    $scope.listAddMany.forEach(element => {
      element.mota = mt;
    }); 
  };

  $scope.savethuoctinh = function(event){
    let api = "";
    $scope.trung = false;
    $scope.flag = true;
    const regex = new RegExp("^[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ0-9 \-]*$");
    let valid = regex.test($scope.x.ten);
    console.log("TT", $scope.x.ten, $scope.luaChonTT)
    if($scope.x.ten === "" || valid === false){
      console.log("Hi");
      $scope.flag = false;
    }
    // console.log(valid);
    // if(valid){
    //   console.log("NPPPPP");
    //   $scope.flag = false;
    // }
    if($scope.luaChonTT == "sanpham"){
      api = sanPhamApi;
      if(isDuplicateName($scope.x.ten, $scope.sanPhams)){
        $scope.trung = true;
        $scope.flag = false;
      }
    }
    if($scope.luaChonTT == "hang"){
      api = hangApi;
      if(isDuplicateName($scope.x.ten, $scope.hangs)){
        $scope.trung = true;
        $scope.flag = false;
      }
    }
    if($scope.luaChonTT == "chatlieu"){
      api = chatLieuApi;
      if(isDuplicateName($scope.x.ten, $scope.chatLieus)){
        $scope.trung = true;
        $scope.flag = false;
      }
    }
    if($scope.luaChonTT == "theloai"){
      api = theLoaiApi;
      if(isDuplicateName($scope.x.ten, $scope.theLoais)){
        $scope.trung = true;
        $scope.flag = false;
      }
    }
    if($scope.luaChonTT == "mausac"){
      api = mauSacApi;
      if(isDuplicateName($scope.x.ten, $scope.mauSacs)){
        $scope.trung = true;
        $scope.flag = false;
      }
    }
    if($scope.luaChonTT == "kichco"){
      api = kichCoApi;
      if(isDuplicateName($scope.x.ten, $scope.kichCos)){
        $scope.trung = true;
        $scope.flag = false;
      }
    }
    if($scope.flag){
      $http.post(api + "/add", $scope.x).then(
        function (response) {
          $("#thuocTinh").modal('hide');
          $scope.getCbbHang();
          $scope.getCbbKichCo();
          $scope.getCbbMauSac();
          $scope.getCbbSanPham();
          $scope.getCbbHang();
          $scope.getCbbTheLoai();
          $scope.getCbbCL();
          console.log(response);
        },
        function (err) {
          console.log("Error: " + err);
        }
      );
    }
  }

  function isDuplicateName(name, list) {
    for (var i = 0; i < list.length; i++) {
      if (list[i].ten === name) {
        return true;
      }
    }
    return false;
  }

  $scope.listChonNhieuMauSac = [];
  $scope.addListMauSac = function (ms) {
    var trung = $filter('filter')($scope.listChonNhieuMauSac, {'id':ms.id});
    if(trung.length == 0){
      $scope.listChonNhieuMauSac.push(ms);
      $scope.changeData();
    }else{
      console.log("Đã tồn tại màu này")
    }
  };
  
  $scope.listChonNhieuKichCo = [];
  $scope.addListKichCo = function (kc) {
    var trung = $filter('filter')($scope.listChonNhieuKichCo, {'id':kc.id});
    if(trung.length == 0){
      $scope.listChonNhieuKichCo.push(kc);
      $scope.changeData();
    }else{
      console.log("Đã tồn tại size này")
    }
  };

  $scope.removeListMS = function (ms) {
    for (let index = 0; index < $scope.listChonNhieuMauSac.length; index++) {
      if($scope.listChonNhieuMauSac[index].id == ms.id){
        $scope.listChonNhieuMauSac.splice(index, 1);
      }
    }
    $scope.changeData();
  };

  $scope.removeListKC = function (kc) {
    for (let index = 0; index < $scope.listChonNhieuKichCo.length; index++) {
      if($scope.listChonNhieuKichCo[index].id == kc.id){
        $scope.listChonNhieuKichCo.splice(index, 1);
      }
    }
    $scope.changeData();
  };

  $scope.hienThiTenKichCo = [{
    id: 0,
    ten: "",
  }];

  $scope.listFileNames = [];
  $scope.changeData = function(){
    $scope.listMsHt = $scope.listChonNhieuMauSac;
    $scope.listKcHt = $scope.listChonNhieuKichCo;
    $scope.listFileNames = [];
    $scope.hienThiTenKichCo = [];
    $scope.listAddMany = [];
    let length = $scope.listMsHt.length * $scope.listKcHt.length;
    let endMS = 0;
    let tempId = 0;
    console.log(length / $scope.listMsHt.length, length)

    for (let i = 0; i < $scope.listKcHt.length; i++) {
      $scope.hienThiTenKichCo[$scope.listKcHt[i].id] = $scope.listKcHt[i].ten;
    }

    for(var i=0; i < length; i++){
      let uniqueCTSP = new Object();
      uniqueCTSP = angular.copy($scope.editCTSP);
      uniqueCTSP.tempt = tempId;

      let uniqueNameImg = [];
      uniqueNameImg = angular.copy($scope.fileNames);

      $scope.listFileNames.push(uniqueNameImg);
      $scope.listAddMany.push(uniqueCTSP);
      tempId += 1;
    }

    for(var z=0; z < length; z++){
      if(z % (length / $scope.listMsHt.length) === (0) && z !== 0){
        endMS += 1;
      }
      $scope.listAddMany[z].idMauSac = $scope.listMsHt[endMS].id;
    }

    for(var i=0; i < $scope.listMsHt.length; i++){
      let addTheoMs = $filter('filter')($scope.listAddMany, {'idMauSac':$scope.listMsHt[i].id});
      for(var x=0; x < addTheoMs.length; x++){
         addTheoMs[x].idKichCo = $scope.listKcHt[x].id;
      }
    }
    $scope.listAddMany.forEach(element => {
      console.log(element);
    }); 

  }

  $scope.saveTest =  function(){
    // $scope.listAddMany.forEach(element => {
    //   console.log(element);
    // });
  }

  $scope.saveAnh = function(temptId){
    $scope.flag = true;
    let name = '#Anh' + temptId;
    console.log(name);
    if ($scope.fileNames.length < 3) {
      $scope.failImg = true;
      $scope.flag = false;
    }

    if($scope.flag){
      let addTheoMs = $filter('filter')($scope.listAddMany, {'tempt':temptId});
      $scope.listFileNames[temptId] = $scope.fileNames;
      addTheoMs[0].anh = $scope.listFileNames[temptId][0];
      addTheoMs[0].anh2 = $scope.listFileNames[temptId][1];
      addTheoMs[0].anh3 = $scope.listFileNames[temptId][2];
      addTheoMs[0].anh4 = $scope.listFileNames[temptId][3];
      addTheoMs[0].anh5 = $scope.listFileNames[temptId][4];
      $(name).modal('hide')
      $scope.fileNames = [];
      $scope.failImg = false;
    }
    $scope.listAddMany.forEach(element => {
      console.log(element);
    }); 

  }

  $scope.saveVariables = function (event) {
    $scope.flag = true;
    $scope.sp3Anh = false;
    $scope.soLuongFail = false;
    $scope.giaFail = false;
    $scope.chatLieuFail = false;
    $scope.hangFail = false;
    $scope.mauSacFail = false;
    $scope.kichCoFail = false;
    $scope.sanPhamFail = false;
    $scope.theLoaiFail = false;
    $scope.isIntGia = false;
    $scope.isIntSl = false;
    console.log("Files Name: ", $scope.listFileNames)

    if($scope.editCTSP.soLuong === undefined) {
      $scope.flag = false;
      event.preventDefault();
    }
    if($scope.editCTSP.gia === undefined) {
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.editCTSP.soLuong < 0) {
      $scope.soLuongFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.editCTSP.gia < 0) {
      $scope.giaFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.editCTSP.idChatLieu <= 0) {
      $scope.chatLieuFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.editCTSP.idHang <= 0) {
      $scope.hangFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.listChonNhieuMauSac.length <= 0) {
      $scope.mauSacFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.listChonNhieuKichCo.length <= 0) {
      $scope.kichCoFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.editCTSP.idSanPham <= 0) {
      $scope.sanPhamFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    if ($scope.editCTSP.idTheLoai <= 0) {
      $scope.theLoaiFail = true;
      $scope.flag = false;
      event.preventDefault();
    }
    $scope.listAddMany.forEach(element => {
      if(Number.isInteger(element.gia) !== true) {
        $scope.isIntGia = true;
        $scope.flag = false;
        event.preventDefault();
      }
      if(Number.isInteger(element.soLuong) !== true) {
        $scope.isIntSl = true;
        $scope.flag = false;
        event.preventDefault();
      }
    });
    $scope.listFileNames.forEach(element => {
      if(element.length <= 0){
        $scope.flag = false;
        $scope.sp3Anh = true;
        event.preventDefault();
      }
    }); 
    if($scope.flag){
      $http.post(chiTietSanPhamApi + "/add-many-variable", $scope.listAddMany).then(
        function (response) {
          console.log(response.data);
        },
        function (err) {
          console.log("Error: " + err);
        }
      );
      $scope.data = [];
    }
};

  $scope.getCbbHang();
  $scope.getCbbKichCo();
  $scope.getCbbMauSac();
  $scope.getCbbSanPham();
  $scope.getCbbHang();
  $scope.getCbbTheLoai();
  $scope.getCbbCL();
  $scope.getDataId();


};
