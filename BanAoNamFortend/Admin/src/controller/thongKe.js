window.ThongKeController = function ($http, $scope, $filter) {

    $scope.listHD = [];
    $scope.listHDFull = {};

    $scope.tk = {
    }

    $scope.page = 0;
    $scope.listPage = [];
    $scope.totalPages = 0;


    $scope.soLuong = 0;
    $scope.countHT = 0;
    $scope.countDG = 0;
    $scope.countDH = 0;
    $scope.countXN = 0;
    $scope.countCH = 0;
    $scope.countDT = 0;

    $scope.loadThongKe = function () {
        $http.get(thongKeApi + "/hien-thi?page=" + $scope.page).then(
            function (response) {

                $scope.listHD = response.data;
                for (var i = 0; i < response.data.totalPages; i++) {
                    $scope.listPage.push(i);
                }
                $scope.totalPages = response.data.totalPages;
                $scope.listHD = response.data;
                $scope.loadTongDonHang();
                $scope.trangThaiHoanThanh();
                $scope.trangThaiDangGiao();
                $scope.trangThaiDaHuy();
                $scope.trangThaiXacNhan();
                $scope.trangThaiChoGiao();
                $scope.trangThaiDaTra();
                console.log($scope.listHD);
            });
    };
    $scope.loadThongKe();

    $scope.load7NgayTruoc = function () {
        $http.get(thongKeApi + "/7-ngay-truoc").then(
            function (response) {
                $scope.listHD = response.data;
                $scope.soLuong = response.data.length;
                $scope.countHT = 0;
                $scope.countDG = 0;
                $scope.countDH = 0;
                $scope.countXN = 0;
                $scope.countCH = 0;
                $scope.countDT = 0;

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
                    if (response.data[i].trangThai == 0) {
                        $scope.countXN++;
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
            });
    };
    // $scope.load7NgayTruoc();

    $scope.load1ThangTruoc = function () {
        $http.get(thongKeApi + "/1-thang-truoc").then(
            function (response) {
                $scope.listHD = response.data;
                $scope.soLuong = response.data.length;
                $scope.countHT = 0;
                $scope.countDG = 0;
                $scope.countDH = 0;
                $scope.countXN = 0;
                $scope.countCH = 0;
                $scope.countDT = 0;

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
                    if (response.data[i].trangThai == 0) {
                        $scope.countXN++;
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
            });
    };

    $scope.loadThangNay = function () {
        $http.get(thongKeApi + "/thang-nay").then(
            function (response) {
                $scope.listHD = response.data;
                $scope.soLuong = response.data.length;
                $scope.countHT = 0;
                $scope.countDG = 0;
                $scope.countDH = 0;
                $scope.countXN = 0;
                $scope.countCH = 0;
                $scope.countDT = 0;

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
                    if (response.data[i].trangThai == 0) {
                        $scope.countXN++;
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
            });
    };

    $scope.load1NamTruoc = function () {
        $http.get(thongKeApi + "/1-nam-truoc").then(
            function (response) {
                $scope.listHD = response.data;
                $scope.soLuong = response.data.length;
                $scope.countHT = 0;
                $scope.countDG = 0;
                $scope.countDH = 0;
                $scope.countXN = 0;
                $scope.countCH = 0;
                $scope.countDT = 0;

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
                    if (response.data[i].trangThai == 0) {
                        $scope.countXN++;
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
            });
    };


    $scope.loadTongDonHang = function () {
        $http.get(thongKeApi + "/show").then(
            function (response) {
                $scope.listHDFull = response.data;
                $scope.soLuong = 0;
                $scope.soLuong = response.data.length;
                console.log($scope.listHDFull);
            },
        );
    };
    // $scope.loadTongDonHang();


    $scope.trangThaiHoanThanh = function () {
        $http.get(thongKeApi + "/show").then(
            function (response) {
                $scope.countHT = 0;
                for (var i = 0; i < response.data.length; i++) {
                    if (response.data[i].trangThai == 1) {
                        $scope.countHT++;
                    }
                }
                return $scope.countHT;
            });
    };
    // $scope.trangThaiHoanThanh();

    $scope.trangThaiXacNhan = function () {
        $http.get(thongKeApi + "/show").then(
            function (response) {
                $scope.countXN = 0;
                for (var i = 0; i < response.data.length; i++) {
                    if (response.data[i].trangThai == 0) {
                        $scope.countXN++;
                    }
                }
                return $scope.countXN;
            });
    };
    // $scope.trangThaiXacNhan();

    $scope.trangThaiChoGiao = function () {
        $http.get(thongKeApi + "/show").then(
            function (response) {
                $scope.countCH = 0;
                for (var i = 0; i < response.data.length; i++) {
                    if (response.data[i].trangThai == 2) {
                        $scope.countCH++;
                    }
                }
                return $scope.countCH;
            });
    };
    // $scope.trangThaiChoGiao();


    $scope.trangThaiDangGiao = function () {
        $http.get(thongKeApi + "/show").then(
            function (response) {
                $scope.countDG = 0;
                for (var i = 0; i < response.data.length; i++) {
                    if (response.data[i].trangThai == 3) {
                        $scope.countDG++;
                    }
                }
                return $scope.countDG;
            });
    };
    // $scope.trangThaiDangGiao();


    $scope.trangThaiDaHuy = function () {
        $http.get(thongKeApi + "/show").then(
            function (response) {
                $scope.countDH = 0;
                for (var i = 0; i < response.data.length; i++) {
                    if (response.data[i].trangThai == 4) {
                        $scope.countDH++;
                    }
                }
                return $scope.countDH;
            });
    };
    // $scope.trangThaiDaHuy();

    $scope.trangThaiDaTra = function () {
        $http.get(thongKeApi + "/show").then(
            function (response) {
                $scope.countDT = 0;
                for (var i = 0; i < response.data.length; i++) {
                    if (response.data[i].trangThai == 5) {
                        $scope.countDT++;
                    }
                }
                return $scope.countDT;
            });
    };
    // $scope.trangThaiDaTra();

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
        $http.get(thongKeApi + "/tim-ngay?ngayTaoSearch=" + $scope.searchNgay)
            .then(
                function (response) {
                    $scope.listHD = response.data;
                    $scope.soLuong = response.data.length;
                    $scope.countHT = 0;
                    $scope.countDG = 0;
                    $scope.countDH = 0;
                    $scope.countXN = 0;
                    $scope.countCH = 0;
                    $scope.countDT = 0;

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
                        if (response.data[i].trangThai == 0) {
                            $scope.countXN++;
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
    };

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

    $scope.loadNgay = function () {
        $scope.formatDates();
        $scope.timNgay();
    };

    $scope.batDau = "";
    $scope.ketThuc = "";
    $scope.timKhoangNgay = function () {
        $http.get(thongKeApi + "/tim-khoang-ngay?ngayTaoSearch=" + $scope.batDau + "&ngayTaoSearchs=" + $scope.ketThuc)
            .then(
                function (response) {
                    $scope.listHD = response.data;
                    $scope.soLuong = response.data.length;
                    $scope.countHT = 0;
                    $scope.countDG = 0;
                    $scope.countDH = 0;
                    $scope.countXN = 0;
                    $scope.countCH = 0;
                    $scope.countDT = 0;

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
                        if (response.data[i].trangThai == 0) {
                            $scope.countXN++;
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
    };


    $scope.search = "";
    $scope.timKiem = function () {
        $http.get(thongKeApi + "/tim-kiem?ma=" + $scope.search)
            .then(
                function (response) {
                    $scope.listHD = response.data;
                    // console.log("Thành công ", response);
                    // $scope.search = "";
                }
            )
    };

    $scope.trangThai = "";
    $scope.hienThi = document.getElementById('hienThi');
    $scope.timKiemTrangThai = function () {
        $http.get(thongKeApi + "/trang-thai?trangThai=" + $scope.trangThai)
            .then(
                function (response) {
                    for (var i = 0; i < response.data.length; i++) {
                        if (response.data[i].value == $scope.trangThai.value) {
                            $scope.listHD = response.data;
                        }
                        if ($scope.trangThai.value == "") {
                            $scope.listHD = response.data;
                        }
                    }
                }
            )
    };

    $scope.exportData = function () {
        var blob = new Blob([document.getElementById('exportable').innerHTML], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "thongKeDonHang.xls");
    };

    $scope.READ = function () {
        $scope.data = [];
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xlsx|.xls)$/;
        var xlsxflag = false;
        if ($("#ngexcelfile").val().toLowerCase().indexOf(".xlsx") > 0) {
            xlsxflag = true;
        } else {
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

    //Phân trang
    $scope.prevPage = function () {
        $scope.page = 0;
        $scope.loadThongKe();
    };

    $scope.prevPages = function () {
        if ($scope.page < 1) {
            return alert("Đã hết hóa đơn");
        }
        $scope.page--;
        $scope.loadThongKe();
    };
    $scope.nextPages = function () {
        if ($scope.page + 1 >= $scope.totalPages) {
            return alert("Đã hết hóa đơn ");
        }
        $scope.page++;
        $scope.loadThongKe();
    };
    $scope.nextPage = function () {
        $scope.page = $scope.totalPages - 1;
        $scope.loadThongKe();
    };


}