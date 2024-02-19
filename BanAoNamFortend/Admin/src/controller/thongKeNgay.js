window.thongKeNgayController = function ($scope, $http) {

    $scope.listTKN = [];

    $scope.tkn = {
        ngayTao: "",
        tongTien: "",
        soLuong: "",
    };

    var dataChart = [];
    $scope.loadThongKeNgay = function () {
        $http.get(thongKeNgayApi + "/hien-thi").then(
            function (response) {
                var dataChart = [];
                $scope.listTKN = response.data;
                for (var i = 0; i < $scope.listTKN.length; i++) {
                    dataChart = $scope.listTKN.map(item => ({
                        x: new Date(item.ngayTao),
                        y: item.tongTien,
                        soLuong: item.soLuong,
                    }))
                }

                var chart = new CanvasJS.Chart("chartContainer", {
                    animationEnabled: true,
                    title: {
                        text: "Biểu đồ thống kê doanh thu theo ngày"
                    },
                    subtitles: [{
                        text: "Click từng cột trong biểu đồ để xem số liệu"
                    }],
                    axisX: {
                        title: "Ngày",
                        valueFormatString: "DD-MM-YYYY",
                        labelFormatter: function (e) {
                            var dataPoints = e.chart.options.data[0].dataPoints;
                            for (var i = 0; i < dataPoints.length; i++) {
                                if (formatDate(dataPoints[i].x) === formatDate(new Date(e.value))) {
                                    return formatDate(dataPoints[i].x);
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
                function formatDate(date) {
                    var day = date.getDate();
                    var month = date.getMonth() + 1;
                    var year = date.getFullYear();
                    return day + '-' + month + '-' + year;
                }
                $scope.doanhThu = 0;
                $scope.donHang = 0;
                $scope.tongDoanThu();
                $scope.tongDonHang();
            });
    };


    // $scope.loadThongKeNgay();


    $scope.doanhThu = 0;
    $scope.tongDoanThu = function () {
        $http.get(thongKeNgayApi + "/hien-thi").then(
            function (response) {
                for (var i = 0; i < response.data.length; i++) {
                    $scope.doanhThu += response.data[i].tongTien;
                }
                return $scope.doanhThu;
            });
    };
    // $scope.tongDoanThu();

    $scope.donHang = 0;
    $scope.tongDonHang = function () {
        $http.get(thongKeNgayApi + "/hien-thi").then(
            function (response) {
                for (var i = 0; i < response.data.length; i++) {
                    $scope.donHang += response.data[i].soLuong;
                }
                return $scope.donHang;
            });
    };
    // $scope.tongDonHang();

    $scope.load7Ngay = function () {
        $http.get(thongKeNgayApi + "/7-ngay-truoc")
            .then(
                function (response) {
                    var dataChart = [];

                    for (var i = 0; i < response.data.length; i++) {
                        dataChart = response.data.map(item => ({
                            x: new Date(item.ngayTao),
                            y: item.tongTien,
                            soLuong: item.soLuong,
                        }))
                    }

                    var chart = new CanvasJS.Chart("chartContainer", {
                        animationEnabled: true,
                        title: {
                            text: "Biểu đồ thống kê doanh thu theo ngày"
                        },
                        subtitles: [{
                            text: "Click từng cột trong biểu đồ để xem số liệu"
                        }],
                        axisX: {
                            title: "Ngày",
                            valueFormatString: "DD-MM-YYYY",
                            labelFormatter: function (e) {
                                var dataPoints = e.chart.options.data[0].dataPoints;
                                for (var i = 0; i < dataPoints.length; i++) {
                                    if (formatDate(dataPoints[i].x) === formatDate(new Date(e.value))) {
                                        return formatDate(dataPoints[i].x);
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
                    function formatDate(date) {
                        var day = date.getDate();
                        var month = date.getMonth() + 1;
                        var year = date.getFullYear();
                        return day + '-' + month + '-' + year;
                    }
                    $scope.doanhThu = 0;
                    $scope.donHang = 0;
                    for (var i = 0; i < response.data.length; i++) {
                        $scope.donHang += response.data[i].soLuong;
                    }
                    for (var i = 0; i < response.data.length; i++) {
                        $scope.doanhThu += response.data[i].tongTien;
                    }
                }
            )
    };

    $scope.load1ThangTruoc = function () {
        $http.get(thongKeNgayApi + "/1-thang-truoc")
            .then(
                function (response) {
                    var dataChart = [];
                    for (var i = 0; i < response.data.length; i++) {
                        dataChart = response.data.map(item => ({
                            x: new Date(item.ngayTao),
                            y: item.tongTien,
                            soLuong: item.soLuong,
                        }))
                    }

                    var chart = new CanvasJS.Chart("chartContainer", {
                        animationEnabled: true,
                        title: {
                            text: "Biểu đồ thống kê doanh thu theo ngày"
                        },
                        subtitles: [{
                            text: "Click từng cột trong biểu đồ để xem số liệu"
                        }],
                        axisX: {
                            title: "Ngày",
                            valueFormatString: "DD-MM-YYYY",
                            labelFormatter: function (e) {
                                var dataPoints = e.chart.options.data[0].dataPoints;
                                for (var i = 0; i < dataPoints.length; i++) {
                                    if (formatDate(dataPoints[i].x) === formatDate(new Date(e.value))) {
                                        return formatDate(dataPoints[i].x);
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
                    function formatDate(date) {
                        var day = date.getDate();
                        var month = date.getMonth() + 1;
                        var year = date.getFullYear();
                        return day + '-' + month + '-' + year;
                    }
                    $scope.doanhThu = 0;
                    $scope.donHang = 0;
                    for (var i = 0; i < response.data.length; i++) {
                        $scope.donHang += response.data[i].soLuong;
                    }
                    for (var i = 0; i < response.data.length; i++) {
                        $scope.doanhThu += response.data[i].tongTien;
                    }
                }
            )
    };

    $scope.load1NamTruoc = function () {
        $http.get(thongKeNgayApi + "/1-nam-truoc")
            .then(
                function (response) {
                    var dataChart = [];
                    for (var i = 0; i < response.data.length; i++) {
                        dataChart = response.data.map(item => ({
                            x: new Date(item.ngayTao),
                            y: item.tongTien,
                            soLuong: item.soLuong,
                        }))
                    }

                    var chart = new CanvasJS.Chart("chartContainer", {
                        animationEnabled: true,
                        title: {
                            text: "Biểu đồ thống kê doanh thu theo ngày"
                        },
                        subtitles: [{
                            text: "Click từng cột trong biểu đồ để xem số liệu"
                        }],
                        axisX: {
                            title: "Ngày",
                            valueFormatString: "DD-MM-YYYY",
                            labelFormatter: function (e) {
                                var dataPoints = e.chart.options.data[0].dataPoints;
                                for (var i = 0; i < dataPoints.length; i++) {
                                    if (formatDate(dataPoints[i].x) === formatDate(new Date(e.value))) {
                                        return formatDate(dataPoints[i].x);
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
                    function formatDate(date) {
                        var day = date.getDate();
                        var month = date.getMonth() + 1;
                        var year = date.getFullYear();
                        return day + '-' + month + '-' + year;
                    }
                    $scope.doanhThu = 0;
                    $scope.donHang = 0;
                    for (var i = 0; i < response.data.length; i++) {
                        $scope.donHang += response.data[i].soLuong;
                    }
                    for (var i = 0; i < response.data.length; i++) {
                        $scope.doanhThu += response.data[i].tongTien;
                    }
                }
            )
    };

    $scope.loadThangNay = function () {
        $http.get(thongKeNgayApi + "/thang-nay")
            .then(
                function (response) {
                    var dataChart = [];
                    for (var i = 0; i < response.data.length; i++) {
                        dataChart = response.data.map(item => ({
                            x: new Date(item.ngayTao),
                            y: item.tongTien,
                            soLuong: item.soLuong,
                        }))
                    }

                    var chart = new CanvasJS.Chart("chartContainer", {
                        animationEnabled: true,
                        title: {
                            text: "Biểu đồ thống kê doanh thu theo ngày"
                        },
                        subtitles: [{
                            text: "Click từng cột trong biểu đồ để xem số liệu"
                        }],
                        axisX: {
                            title: "Ngày",
                            valueFormatString: "DD-MM-YYYY",
                            labelFormatter: function (e) {
                                var dataPoints = e.chart.options.data[0].dataPoints;
                                for (var i = 0; i < dataPoints.length; i++) {
                                    if (formatDate(dataPoints[i].x) === formatDate(new Date(e.value))) {
                                        return formatDate(dataPoints[i].x);
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
                    function formatDate(date) {
                        var day = date.getDate();
                        var month = date.getMonth() + 1;
                        var year = date.getFullYear();
                       return day + '-' + month + '-' + year;
                    }
                    $scope.doanhThu = 0;
                    $scope.donHang = 0;
                    for (var i = 0; i < response.data.length; i++) {
                        $scope.donHang += response.data[i].soLuong;
                    }
                    for (var i = 0; i < response.data.length; i++) {
                        $scope.doanhThu += response.data[i].tongTien;
                    }
                }
            )
    };

    $scope.formatDate = function () {
        var input = document.getElementById('dateInput');
        var value = $scope.searchNgay.replace(/\D/g, '');
        var formattedValue = value.replace(/(\d{2})(\d{2})(\d{4})?/, '$1-$2-$3');
        $scope.searchNgay = formattedValue;

        if (value.length >= 2 && value.length < 4) {
            input.setSelectionRange(4, 4);
        }
    }

    $scope.searchNgay = "";
    $scope.timNgay = function () {
        $http.get(thongKeNgayApi + "/tim-ngay?ngayTaoSearch=" + $scope.searchNgay)
            .then(
                function (response) {
                    var dataChart = [];
                    for (var i = 0; i < response.data.length; i++) {
                        dataChart = response.data.map(item => ({
                            x: new Date(item.ngayTao),
                            y: item.tongTien,
                            soLuong: item.soLuong,
                        }))
                    }

                    var chart = new CanvasJS.Chart("chartContainer", {
                        animationEnabled: true,
                        title: {
                            text: "Biểu đồ thống kê doanh thu theo ngày"
                        },
                        subtitles: [{
                            text: "Click từng cột trong biểu đồ để xem số liệu"
                        }],
                        axisX: {
                            title: "Ngày",
                            valueFormatString: "DD-MM-YYYY",
                            labelFormatter: function (e) {
                                var dataPoints = e.chart.options.data[0].dataPoints;
                                for (var i = 0; i < dataPoints.length; i++) {
                                    if (formatDate(dataPoints[i].x) === formatDate(new Date(e.value))) {
                                        return formatDate(dataPoints[i].x);
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
                    function formatDate(date) {
                        var day = date.getDate();
                        var month = date.getMonth() + 1;
                        var year = date.getFullYear();
                       return day + '-' + month + '-' + year;
                    }
                    $scope.doanhThu = 0;
                    $scope.donHang = 0;
                    for (var i = 0; i < response.data.length; i++) {
                        $scope.donHang += response.data[i].soLuong;
                    }
                    for (var i = 0; i < response.data.length; i++) {
                        $scope.doanhThu += response.data[i].tongTien;
                    }
                }
            )
    };
    // $scope.timNgay();

    $scope.formatDates = function () {
        var input = document.getElementById('dateInputs');
        var value = $scope.batDau.replace(/\D/g, '');
        var formattedValue = value.replace(/(\d{2})(\d{2})(\d{4})?/, '$1-$2-$3');
        $scope.batDau = formattedValue;

        if (value.length >= 2 && value.length < 4) {
            input.setSelectionRange(4, 4);
        }
    }

    $scope.formatDatess = function () {
        var input = document.getElementById('dateInputss');
        var value = $scope.ketThuc.replace(/\D/g, '');
        var formattedValue = value.replace(/(\d{2})(\d{2})(\d{4})?/, '$1-$2-$3');
        $scope.ketThuc = formattedValue;

        if (value.length >= 2 && value.length < 4) {
            input.setSelectionRange(4, 4);
        }
    }

    $scope.batDau = "";
    $scope.ketThuc = "";
    $scope.timKhoangNgay = function () {
        $http.get(thongKeNgayApi + "/tim-khoang-ngay?ngayTaoSearch=" + $scope.batDau + "&ngayTaoSearchs=" + $scope.ketThuc)
            .then(
                function (response) {
                    var dataChart = [];
                    $scope.listHD = response.data;
                    for (var i = 0; i < response.data.length; i++) {
                        dataChart = response.data.map(item => ({
                            x: new Date(item.ngayTao),
                            y: item.tongTien,
                            soLuong: item.soLuong,
                        }))
                    }

                    var chart = new CanvasJS.Chart("chartContainer", {
                        animationEnabled: true,
                        title: {
                            text: "Biểu đồ thống kê doanh thu theo ngày"
                        },
                        subtitles: [{
                            text: "Click từng cột trong biểu đồ để xem số liệu"
                        }],
                        axisX: {
                            title: "Ngày",
                            valueFormatString: "DD-MM-YYYY",
                            labelFormatter: function (e) {
                                var dataPoints = e.chart.options.data[0].dataPoints;
                                for (var i = 0; i < dataPoints.length; i++) {
                                    if (formatDate(dataPoints[i].x) === formatDate(new Date(e.value))) {
                                        return formatDate(dataPoints[i].x);
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
                    function formatDate(date) {
                        var day = date.getDate();
                        var month = date.getMonth() + 1;
                        var year = date.getFullYear();
                        return day + '-' + month + '-' + year;
                    }
                    $scope.doanhThu = 0;
                    $scope.donHang = 0;
                    for (var i = 0; i < response.data.length; i++) {
                        $scope.donHang += response.data[i].soLuong;
                    }
                    for (var i = 0; i < response.data.length; i++) {
                        $scope.doanhThu += response.data[i].tongTien;
                    }
                }
            )
    };


}