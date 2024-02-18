window.thongKeNamController = function ($scope, $http) {

    $scope.listTKN = [];
    $scope.listN = [];

    $scope.loadNam = function () {
        $http.get(thongKeNamApi + "/hien-thi-nam").then(
            function (response) {
                $scope.listN = response.data;
                console.log($scope.listN);
            }
        )
    };
    $scope.loadNam();

    $scope.nam = "";
    $scope.donHang = 0;
    $scope.tongTien = 0;

    $scope.loadThongKeNam = function () {
        $http.get(thongKeNamApi + "/hien-thi?nam=" + $scope.nam).then(
            function (response) {
                var dataChart = [];
                $scope.listTKN = response.data;
                console.log($scope.listTKN);

                $scope.donHang = 0;
                $scope.tongTien = 0;
                for (var i = 0; i < $scope.listTKN.length; i++) {
                    $scope.donHang += $scope.listTKN[i].soLuong;
                }
                for (var i = 0; i < $scope.listTKN.length; i++) {
                    $scope.tongTien += $scope.listTKN[i].tongTien;
                }
                dataChart = $scope.listTKN.map(item => ({
                    x: item.nam,
                    y: item.tongTien,
                    soLuong: item.soLuong,
                }));
                dataChart = dataChart.filter(item => item.x);

                var chart = new CanvasJS.Chart("chartContainer", {
                    animationEnabled: true,
                    title: {
                        text: "Biểu đồ thống kê doanh thu theo năm"
                    },
                    subtitles: [{
                        text: "Click từng cột trong biểu đồ để xem số liệu"
                    }],
                    axisX: {
                        title: "Năm",
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
                        shared: true,
                        contentFormatter: function (e) {
                            var content = "<strong>Năm:</strong> " + e.entries[0].dataPoint.x + "<br/>";
                            content += "<strong>Tổng doanh thu:</strong> " + e.entries[0].dataPoint.y + " VND<br/>";
                            content += "<strong>Tổng đơn hàng:</strong> " + e.entries[1].dataPoint.y + " Đơn";
                            return content;
                        }
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

};
