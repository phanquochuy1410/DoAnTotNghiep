window.VnpayConTroller = function ($http , $scope , $window ,  $location) {

  $scope.tongTien = "";
  $scope.don = "";

  $scope.thanhToan = function () {
    var url = vnpayApi + "/submitOrder?amount=" + $scope.tongTien + "&don=" + $scope.don;
    // console.log("Sending GET request to: ", url);
    $window.location.href = url;
  };
}

app.controller("DeltailKhachHang", function ($scope, $http) {

  const apiDetail = khachHangApi + "/detail"
  
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

})