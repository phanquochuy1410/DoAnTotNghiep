window.anhController = function ($scope, $http, $routeParams, $location) {

    // $scope.page = 0;
    // $scope.listSP = [];
    // $scope.mauSacs = [];
    // $scope.kichCos = [];
    // $scope.sanPhams = [];
    // $scope.searchTen = "";
    // $scope.searchMauSac = "";
    // $scope.searchKichCo = "";

    // $scope.hienThiSanPham = function () {
    //     $scope.timKiem = function () {
    //         var url = muaTaiQuayApi + "/hien-thi?page=" + $scope.page + "&";

    //         if ($scope.searchTen && $scope.searchTen.trim() !== "") {
    //             url += "tenSanPham=" + $scope.searchTen + "&";
    //         }

    //         if ($scope.searchMauSac && $scope.searchMauSac.trim() !== "") {
    //             url += "mauSac=" + $scope.searchMauSac + "&";
    //         }

    //         if ($scope.searchKichCo && $scope.searchKichCo.trim() !== "") {
    //             url += "tenKichCo=" + $scope.searchKichCo + "&";
    //         }
    //         if (url.charAt(url.length - 1) === "&") {
    //             url = url.slice(0, -1);
    //         }

    //         $http.get(url).then(function (response) {
    //             barcodeMappings = response.data;
    //             console.log(barcodeMappings);
    //         });
    //     };
    //     $scope.timKiem();
    // };
    // $scope.hienThiSanPham();

    // var barcodeMappings = [];
    // $scope.scanning = false;

    // $scope.showCame = false;
    // $scope.showTrue = function () {
    //     $scope.hienThiSanPham();
    //     $scope.showCame = true;
    // };

    // $scope.showFalse = function () {
    //     $scope.hienThiSanPham();
    //     $scope.showCame = false;
    // };

    // function initQuagga() {
    //     Quagga.init({
    //         inputStream: {
    //             name: "Live",
    //             type: "LiveStream",
    //             target: document.querySelector('#barcode-scanner'),
    //             constraints: {
    //                 facingMode: "environment"
    //             },
    //         },
    //         decoder: {
    //             readers: [
    //                 "code_128_reader"
    //             ]
    //         }
    //     }, function (err) {
    //         if (err) {
    //             console.error("Error initializing QuaggaJS:", err);
    //             return;
    //         }
    //         console.log("QuaggaJS initialized successfully");
    //         Quagga.start();
    //     })
    // };
    // initQuagga();

    // Quagga.onDetected(function (result) {
    //     $scope.scanning = false;
    //     var scannedBarcode = parseInt(result.codeResult.code);
    //     var foundMapping = barcodeMappings.find(function (mapping) {
    //         return mapping.id === scannedBarcode;
    //     });

    //     if (foundMapping) {
    //         $scope.barcodeResult = foundMapping.sanPham;
    //         console.log($scope.barcodeResult);
    //     } else {
    //         $scope.barcodeResult = "Unknown Barcode";
    //     }

    //     $scope.$apply();
    //     setTimeout(function () {
    //         Quagga.start();
    //         $scope.$apply(function () {
    //             $scope.scanning = true;
    //         });
    //     }, 1000);
    // });

    // $scope.toggleScan = function () {
    //     $scope.scanning = !$scope.scanning;
    //     if ($scope.scanning) {
    //         $scope.barcodeResult = "";
    //     } else {
    //         $scope.barcodeResult = "";
    //     }
    // };
    // initQuagga();
};
