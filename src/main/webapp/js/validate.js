angular.module('tchaApp').controller('validateItemController', function($scope, $http, $timeout) {

    $scope.successMessage = false;
    $scope.rejectMessage = false;
    $scope.selectItems = [];
    $scope.itemRequired = false;
    $scope.validateItemGridColumns = [{
            field: 'itemsSelectedType',
            displayName: 'Type',
            width: 100
        }, {
            field: 'status',
            displayName: 'Status',
            width: 100
        }, {
            field: 'itemTitle',
            displayName: 'Title',
            width: 200
        }, {
            field: 'itemDonor',
            displayName: 'Donor',
            width: 300
        }, {
            field: 'itemDescription',
            displayName: 'Description',
            width: 800
        }, {
            field: 'itemKeyWords',
            displayName: 'KeyWords',
            width: 500
        }, {
            field: 'itemStartDate',
            displayName: 'DateOfOrigin',
            width: 150
        }, {
            field: 'id',
            displayName: 'Id',
            visible: false
        }, {
            field: 'itemName',
            displayName: 'ItemName',
            width: 200
        }

    ];

    $scope.formClear = function() {
        $scope.successMessage = false;
        $scope.rejectMessage = false;
        $scope.itemRequired = false
        $http.get("/TCHA/items").success(function(data) {
            $scope.validateItemGrid.data = data.itemDtos;
        });
    }

    $scope.clearValidate = function() {
        $scope.selectItems = [];
    };

    $scope.validateItemGrid = {
        enableFullRowSelection: true,
        multiSelect: true,
        columnDefs: $scope.validateItemGridColumns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function(row) {

                if ($scope.selectItems.indexOf(row.entity.id) == -1) {
                    $scope.selectItems.push(row.entity.id);
                } else {
                    $scope.selectItems.splice($scope.selectItems.indexOf(row.entity.id), 1);
                }


            });
        }
    };

    $http.get("/TCHA/items").success(function(data) {
        $scope.validateItemGrid.data = data.itemDtos;
    });


    function validateForm() {
        if ($scope.selectItems.length === 0) {
            $scope.itemRequired = true
            return false;
        }
        return true;
    }
    $scope.validateItem = function() {
        $scope.formClear();
        if (validateForm()) {

            var fd = new FormData();
            fd.append('itemArray', JSON.stringify($scope.selectItems));
            var res = $http.post("/TCHA/items/validateItem", fd, {
                transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            });
            res.success(function(data, status, headers, config) {
                $scope.validateItemGrid.data = data.itemDtos;;
                $scope.successMessage = true;;
                $scope.selectItems = [];
            });
            res.error(function(data, status, headers, config) {
                //alert( "failure message: " + JSON.stringify({data: data}));
            });
        }
    };

    $scope.rejectItem = function() {
        $scope.formClear();
        if (validateForm()) {

            var fd = new FormData();
            fd.append('itemArray', JSON.stringify($scope.selectItems));
            var res = $http.post("/TCHA/items/rejectItem", fd, {
                transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            });
            res.success(function(data, status, headers, config) {
                $scope.validateItemGrid.data = data.itemDtos;;
                $scope.rejectMessage = true;;
                $scope.selectItems = [];
            });
            res.error(function(data, status, headers, config) {
                //alert( "failure message: " + JSON.stringify({data: data}));
            });
        }
    };
});