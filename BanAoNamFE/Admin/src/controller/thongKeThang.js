window.thongKeThangController = function ($scope, $http) {

    $scope.listTKT = [];
    $scope.listN = [];

    $scope.loadNam = function () {
        $http.get(thongKeThangApi + "/hien-thi-nam").then(
            function (response) {
                $scope.listN = response.data;
                console.log($scope.listN);
            }
        )
    };
    $scope.loadNam();

    $scope.thang = "";
    $scope.nam = "";
    $scope.donHang = 0;
    $scope.tongTien = 0;

    $scope.loadThongKeThang = function () {
        $http.get(thongKeThangApi + "/hien-thi?thang=" + $scope.thang + "&nam=" + $scope.nam).then(
            function (response) {
                var dataChart = [];
                $scope.listTKT = response.data;
                console.log($scope.listTKT);

                $scope.donHang = 0;
                $scope.tongTien = 0;
                for (var i = 0; i < $scope.listTKT.length; i++) {
                    $scope.donHang += $scope.listTKT[i].soLuong;
                }
                for (var i = 0; i < $scope.listTKT.length; i++) {
                    $scope.tongTien += $scope.listTKT[i].tongTien;
                }
                dataChart = $scope.listTKT.map(item => ({
                    x: item.thang,
                    y: item.tongTien,
                    soLuong: item.soLuong,
                }));

                var chart = new CanvasJS.Chart("chartContainer", {
                    animationEnabled: true,
                    title: {
                        text: "Biểu đồ thống kê doanh thu theo tháng"
                    },
                    subtitles: [{
                        text: "Click từng cột trong biểu đồ để xem số liệu"
                    }],
                    axisX: {
                        title: "Tháng",
                        labelFormatter: function (e) {
                            var dataPoints = e.chart.options.data[0].dataPoints; // Thay đổi thành chỉ số của dãy dữ liệu bạn đang sử dụng
                            for (var i = 0; i < dataPoints.length; i++) {
                                if (dataPoints[i].x === e.value) {
                                    return e.value;
                                }
                            }
                            return "";
                        }
                    },
                    axisY: {
                        title: "Giá",
                        prefix: "VND ",
                        titleFontColor: "#4F81BC",
                        lineColor: "#4F81BC",
                        labelFontColor: "#4F81BC",
                        tickColor: "#4F81BC",
                        includeZero: true
                    },
                    axisY2: {
                        title: "Đơn hàng",
                        titleFontColor: "#C0504E",
                        lineColor: "#C0504E",
                        labelFontColor: "#C0504E",
                        tickColor: "#C0504E",
                        includeZero: true
                    },
                    toolTip: {
                        shared: true
                    },
                    data: [{
                        type: "column",
                        name: "Tổng doanh thu",
                        showInLegend: true,
                        yValueFormatString: "#,##0.# VND",
                        dataPoints: dataChart
                    },
                    {
                        type: "column",
                        name: "Tổng đơn hàng",
                        axisYType: "secondary",
                        showInLegend: true,
                        yValueFormatString: "#,##0.# Đơn",
                        dataPoints: dataChart.map(item => ({
                            x: item.x,
                            y: item.soLuong
                        }))
                    }
                    ]
                });
                chart.render();
            });
    };


    $scope.thangBatDau = "";
    $scope.thangKetThuc = "";
    $scope.nam1 = "";

    $scope.loadThongKeKhoangThang = function () {
        $http.get(thongKeThangApi + "/hien-thi-khoang?thangBatDau=" + $scope.thangBatDau + "&thangKetThuc=" + $scope.thangKetThuc + "&nam=" + $scope.nam1).then(
            function (response) {
                var dataChart = [];
                $scope.listTKT = response.data;
                console.log($scope.listTKT);
                $scope.donHang = 0;
                $scope.tongTien = 0;
                for (var i = 0; i < $scope.listTKT.length; i++) {
                    $scope.donHang += $scope.listTKT[i].soLuong;
                }
                for (var i = 0; i < $scope.listTKT.length; i++) {
                    $scope.tongTien += $scope.listTKT[i].tongTien;
                }

                dataChart = $scope.listTKT.map(item => ({
                    x: item.thang,
                    y: item.tongTien,
                    soLuong: item.soLuong,
                }));

                var chart = new CanvasJS.Chart("chartContainer", {
                    animationEnabled: true,
                    title: {
                        text: "Biểu đồ thống kê doanh thu theo tháng"
                    },
                    subtitles: [{
                        text: "Click từng cột trong biểu đồ để xem số liệu"
                    }],
                    axisX: {
                        title: "Tháng",
                        labelFormatter: function (e) {
                            var dataPoints = e.chart.options.data[0].dataPoints; // Thay đổi thành chỉ số của dãy dữ liệu bạn đang sử dụng
                            for (var i = 0; i < dataPoints.length; i++) {
                                if (dataPoints[i].x === e.value) {
                                    return e.value;
                                }
                            }
                            return "";
                        }
                    },
                    axisY: {
                        title: "Giá",
                        prefix: "VND ",
                        titleFontColor: "#4F81BC",
                        lineColor: "#4F81BC",
                        labelFontColor: "#4F81BC",
                        tickColor: "#4F81BC",
                        includeZero: true
                    },
                    axisY2: {
                        title: "Đơn hàng",
                        titleFontColor: "#C0504E",
                        lineColor: "#C0504E",
                        labelFontColor: "#C0504E",
                        tickColor: "#C0504E",
                        includeZero: true
                    },
                    toolTip: {
                        shared: true
                    },
                    data: [{
                        type: "column",
                        name: "Tổng doanh thu",
                        showInLegend: true,
                        yValueFormatString: "#,##0.# VND",
                        dataPoints: dataChart
                    },
                    {
                        type: "column",
                        name: "Tổng đơn hàng",
                        axisYType: "secondary",
                        showInLegend: true,
                        yValueFormatString: "#,##0.# Đơn",
                        dataPoints: dataChart.map(item => ({
                            x: item.x,
                            y: item.soLuong
                        }))
                    }
                    ]
                });
                chart.render();
            });
    };
    // $scope.loadThongKeKhoangThang();


}