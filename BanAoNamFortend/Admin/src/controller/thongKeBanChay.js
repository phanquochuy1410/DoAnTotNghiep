window.ThongKeBanChayController = function ($http, $scope, $filter) {

    $scope.listSPBC = [];
    $scope.listTT = [];
    $scope.listTTC = [];
    $scope.listTTT = [];

    $scope.sanPhamBanChay = function () {
        $http.get(thongKeBanChayApi + "/load-san-pham-ban-chay").then(
            function (response) {
                $scope.listSPBC = response.data;
                console.log($scope.listSPBC)
                var ten = [];
                var gia = [];
                var ctxD = document.getElementById("doughnutChart").getContext('2d');
                for (var i = 0; i < response.data.length; i++) {
                    ten.push(response.data[i].tenSanPham);
                    gia.push(response.data[i].tongTien);
                }
                new Chart(ctxD, {
                    type: 'doughnut',

                    data: {
                        labels: ten,
                        datasets: [{
                            data: gia,
                            backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360", "#aae2e8", "#084c89", "#803e21", "#f0f3ee", "#2596bc", "#bb7342", "#e9d9dd", "#59a1bd", "#c0b8b1", "#9c7552"],

                        }]
                    },

                });

            }
        )
    }
    $scope.sanPhamBanChay();


    $scope.loadTrangThai = function () {
        $http.get(thongKeBanChayApi + "/load-trang-thai").then(
            function (response) {
                $scope.countXN = 0;
                $scope.listTTC = response.data;
                console.log($scope.listTT)
                $scope.soLuong = response.data.length;
                $scope.countHT = 0;
                $scope.countDG = 0;
                $scope.countDH = 0;
                $scope.countCH = 0;
                $scope.countDT = 0;


                for (var i = 0; i < response.data.length; i++) {
                    if (response.data[i].trangThai == 0) {
                        $scope.countXN++;
                    }
                }
                for (var i = 0; i < response.data.length; i++) {
                    if (response.data[i].trangThai == 1) {
                        $scope.countHT++;
                    }
                }
                for (var i = 0; i < response.data.length; i++) {
                    if (response.data[i].trangThai == 3) {
                        $scope.countDG++;
                    }
                }
                for (var i = 0; i < response.data.length; i++) {
                    if (response.data[i].trangThai == 4) {
                        $scope.countDH++;
                    }
                }
                for (var i = 0; i < response.data.length; i++) {
                    if (response.data[i].trangThai == 2) {
                        $scope.countCH++;
                    }
                }
                for (var i = 0; i < response.data.length; i++) {
                    if (response.data[i].trangThai == 5) {
                        $scope.countDT++;
                    }
                }
            }
        )
    }
    $scope.loadTrangThai();

    $scope.tongDoanhThuNgay = function () {
        $http.get(thongKeBanChayApi + "/load-tong-tien-ngay").then(
            function (response) {
                $scope.tongTien = 0;
                $scope.listTT = response.data;
                console.log($scope.listTT)
                for (var i = 0; i < response.data.length; i++) {
                    if (response.data[i].trangThai == 1) {
                        $scope.tongTien += response.data[i].tongTien;
                    }
                }
            }
        )
    }
    $scope.tongDoanhThuNgay();

    $scope.tongDoanhThuNgayTaiQuay = function () {
        $http.get(thongKeBanChayApi + "/load-tong-tien-ngay-tai-quay").then(
            function (response) {
                $scope.tongTienTQ = 0;
                $scope.listTTT = response.data;
                console.log($scope.listTTT)
                for (var i = 0; i < response.data.length; i++) {
                    if (response.data[i].trangThai == 1) {
                        $scope.tongTienTQ += response.data[i].tongTien;
                        console.log($scope.tongTienTQ)
                    }

                }
            }
        )
    }
    $scope.tongDoanhThuNgayTaiQuay();
}