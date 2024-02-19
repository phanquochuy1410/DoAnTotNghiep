window.ThongKeSanPhamController = function ($http, $scope) {

    $scope.listHDCT = [];

    $scope.tksp = {
        idChiTietSanPham: "",
        soLuong: "",
        gia: ""
    }

    $scope.loadThongKeSanPham = function () {
        $http.get(thongKeSanPhamApi + "/san-pham").then(
            function (response) {
                $scope.DoanhThuSanPham = 0;
                $scope.donHangSanPham = 0;
                $scope.listHDCT = response.data;
                $scope.tongDoanhThuSanPham();
                $scope.tongDonHangSanPham();
                var ten = [];
                var gia = [];
                var ctxD = document.getElementById("doughnutChart").getContext('2d');
                for (var i = 0; i < response.data.length; i++) {
                    ten.push(response.data[i].idChiTietSanPham);
                    gia.push(response.data[i].gia);
                }
                new Chart(ctxD, {
                    type: 'doughnut',

                    data: {
                        labels: ten,
                        datasets: [{
                            data: gia,
                            backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360","#aae2e8","#084c89","#803e21","#f0f3ee","#2596bc","#bb7342","#e9d9dd","#59a1bd","#c0b8b1","#9c7552"],

                        }]
                    },
 
                });

            })
    }

    $scope.loadThongKeSanPham();

    $scope.load7NgayTruoc = function () {
        $http.get(thongKeSanPhamApi + "/7-ngay-truoc").then(
            function (response) {
                $scope.listHDCT = response.data;
                $scope.DoanhThuSanPham = 0;
                $scope.donHangSanPham = 0;
                for (var i = 0; i < $scope.listHDCT.length; i++) {
                    $scope.DoanhThuSanPham += $scope.listHDCT[i].gia;
                }
                for (var i = 0; i < $scope.listHDCT.length; i++) {
                    $scope.donHangSanPham += $scope.listHDCT[i].soLuong;
                }
                var ten1 = [];
                var gia1 = [];
                var ctxD = document.getElementById("doughnutChart").getContext('2d');
                for (var i = 0; i < response.data.length; i++) {
                    ten1.push(response.data[i].idChiTietSanPham);
                    gia1.push(response.data[i].gia);
                }
                new Chart(ctxD, {
                    type: 'doughnut',

                    data: {
                        labels: ten1,
                        datasets: [{
                            data: gia1,
                            backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360","#aae2e8","#084c89","#803e21","#f0f3ee","#2596bc","#bb7342","#e9d9dd","#59a1bd","#c0b8b1","#9c7552"],
                        }]
                    },

                });
            })
    }


    $scope.load1ThangTruoc = function () {
        $http.get(thongKeSanPhamApi + "/1-thang-truoc").then(
            function (response) {
                $scope.listHDCT = response.data;
                $scope.DoanhThuSanPham = 0;
                $scope.donHangSanPham = 0;
                for (var i = 0; i < $scope.listHDCT.length; i++) {
                    $scope.DoanhThuSanPham += $scope.listHDCT[i].gia;
                }
                for (var i = 0; i < $scope.listHDCT.length; i++) {
                    $scope.donHangSanPham += $scope.listHDCT[i].soLuong;
                }
                var ten2 = [];
                var gia2 = [];
                var ctxD = document.getElementById("doughnutChart").getContext('2d');
                for (var i = 0; i < response.data.length; i++) {
                    ten2.push(response.data[i].idChiTietSanPham);
                    gia2.push(response.data[i].gia);
                }
                new Chart(ctxD, {
                    type: 'doughnut',

                    data: {
                        labels: ten2,
                        datasets: [{
                            data: gia2,
                            backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360","#aae2e8","#084c89","#803e21","#f0f3ee","#2596bc","#bb7342","#e9d9dd","#59a1bd","#c0b8b1","#9c7552"],
                        }]
                    },
                });
            })
    }


    $scope.load1NamTruoc = function () {
        $http.get(thongKeSanPhamApi + "/1-nam-truoc").then(
            function (response) {
                $scope.listHDCT = response.data;
                $scope.DoanhThuSanPham = 0;
                $scope.donHangSanPham = 0;
                for (var i = 0; i < $scope.listHDCT.length; i++) {
                    $scope.DoanhThuSanPham += $scope.listHDCT[i].gia;
                }
                for (var i = 0; i < $scope.listHDCT.length; i++) {
                    $scope.donHangSanPham += $scope.listHDCT[i].soLuong;
                }
                var ten3 = [];
                var gia3 = [];
                var ctxD = document.getElementById("doughnutChart").getContext('2d');
                for (var i = 0; i < response.data.length; i++) {
                    ten3.push(response.data[i].idChiTietSanPham);
                    gia3.push(response.data[i].gia);
                }
                new Chart(ctxD, {
                    type: 'doughnut',

                    data: {
                        labels: ten3,
                        datasets: [{
                            data: gia3,
                            backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360","#aae2e8","#084c89","#803e21","#f0f3ee","#2596bc","#bb7342","#e9d9dd","#59a1bd","#c0b8b1","#9c7552"],
                        }]
                    },
                });
            })
    }


    $scope.loadThangNay = function () {
        $http.get(thongKeSanPhamApi + "/thang-nay").then(
            function (response) {
                $scope.listHDCT = response.data;
                $scope.DoanhThuSanPham = 0;
                $scope.donHangSanPham = 0;
                for (var i = 0; i < $scope.listHDCT.length; i++) {
                    $scope.DoanhThuSanPham += $scope.listHDCT[i].gia;
                }
                for (var i = 0; i < $scope.listHDCT.length; i++) {
                    $scope.donHangSanPham += $scope.listHDCT[i].soLuong;
                }
                var ten4 = [];
                var gia4 = [];
                var ctxD = document.getElementById("doughnutChart").getContext('2d');
                for (var i = 0; i < response.data.length; i++) {
                    ten4.push(response.data[i].idChiTietSanPham);
                    gia4.push(response.data[i].gia);
                }
                new Chart(ctxD, {
                    type: 'doughnut',

                    data: {
                        labels: ten4,
                        datasets: [{
                            data: gia4,
                            backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360","#aae2e8","#084c89","#803e21","#f0f3ee","#2596bc","#bb7342","#e9d9dd","#59a1bd","#c0b8b1","#9c7552"],

                        }]
                    },
                });
            })
    }

    $scope.searchNgay = "";

    $scope.timNgay = function () {
        $http.get(thongKeSanPhamApi + "/tim-kiem-ngay?ngayTaoSearch=" + $scope.searchNgay).then(
            function (response) {
                $scope.listHDCT = response.data;
                $scope.DoanhThuSanPham = 0;
                $scope.donHangSanPham = 0;
                for (var i = 0; i < $scope.listHDCT.length; i++) {
                    $scope.DoanhThuSanPham += $scope.listHDCT[i].gia;
                }
                for (var i = 0; i < $scope.listHDCT.length; i++) {
                    $scope.donHangSanPham += $scope.listHDCT[i].soLuong;
                }
                var ten4 = [];
                var gia4 = [];
                var ctxD = document.getElementById("doughnutChart").getContext('2d');
                for (var i = 0; i < response.data.length; i++) {
                    ten4.push(response.data[i].idChiTietSanPham);
                    gia4.push(response.data[i].gia);
                }
                new Chart(ctxD, {
                    type: 'doughnut',

                    data: {
                        labels: ten4,
                        datasets: [{
                            data: gia4,
                            backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360","#aae2e8","#084c89","#803e21","#f0f3ee","#2596bc","#bb7342","#e9d9dd","#59a1bd","#c0b8b1","#9c7552"],

                        }]
                    },
                });
            })
    }

    $scope.batDau = "";
    $scope.ketThuc = "";

    $scope.timKhoangNgay = function () {
        $http.get(thongKeSanPhamApi + "/tim-kiem-khoang-ngay?ngayTaoSearch=" + $scope.batDau 
        + '&ngayTaoSearchs=' + $scope.ketThuc ).then(
            function (response) {
                $scope.listHDCT = response.data;
                $scope.DoanhThuSanPham = 0;
                $scope.donHangSanPham = 0;
                for (var i = 0; i < $scope.listHDCT.length; i++) {
                    $scope.DoanhThuSanPham += $scope.listHDCT[i].gia;
                }
                for (var i = 0; i < $scope.listHDCT.length; i++) {
                    $scope.donHangSanPham += $scope.listHDCT[i].soLuong;
                }
                var ten4 = [];
                var gia4 = [];
                var ctxD = document.getElementById("doughnutChart").getContext('2d');
                for (var i = 0; i < response.data.length; i++) {
                    ten4.push(response.data[i].idChiTietSanPham);
                    gia4.push(response.data[i].gia);
                }
                new Chart(ctxD, {
                    type: 'doughnut',

                    data: {
                        labels: ten4,
                        datasets: [{
                            data: gia4,
                            backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360","#aae2e8","#084c89","#803e21","#f0f3ee","#2596bc","#bb7342","#e9d9dd","#59a1bd","#c0b8b1","#9c7552"],

                        }]
                    },
                });
            })
    }


    // Tổng doanh thu sản phẩm
    $scope.DoanhThuSanPham = 0;
    $scope.tongDoanhThuSanPham = function () {
        $http.get(thongKeSanPhamApi + "/san-pham").then(
            function (response) {
                for (var i = 0; i < $scope.listHDCT.length; i++) {
                    $scope.DoanhThuSanPham += $scope.listHDCT[i].gia;
                }
                return $scope.DoanhThuSanPham;
            })
    }
    // $scope.tongDoanhThuSanPham();

    // Tổng đơn hàng
    $scope.donHangSanPham = 0;
    $scope.tongDonHangSanPham = function () {
        $http.get(thongKeSanPhamApi + "/san-pham").then(
            function (response) {
                for (var i = 0; i < $scope.listHDCT.length; i++) {
                    $scope.donHangSanPham += $scope.listHDCT[i].soLuong;
                }
                return $scope.donHangSanPham;
            })
    }
    // $scope.tongDonHangSanPham();


}