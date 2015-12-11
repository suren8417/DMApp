angular.module('tchaApp').controller('collectionController', function($scope, $http, $timeout) {

    $scope.collectionName;
    $scope.collectionDescription;
    $scope.collectionId = null;
    $scope.selectItems = [];
    $scope.successMessage = false;
    $scope.deleteMessage = false;

    $scope.collectionNameRequired = false;
    $scope.collectionDescriptionRequired = false;

    $scope.columns = [{
            field: 'name',
            displayName: 'CollectionName',
            width: 250
        }, {
            field: 'description',
            displayName: 'CollectionDescription',
            width: 700
        }, {
            field: 'id',
            displayName: 'Id',
            visible: false
        }

    ];

    $scope.itemGridColumns = [{
            field: 'itemsSelectedType',
            displayName: 'Type',
            width: 100
        }, {
            field: 'itemTitle',
            displayName: 'Title',
            width: 300
        }, {
            field: 'itemDonor',
            displayName: 'Donor',
            width: 300
        }, {
            field: 'itemDescription',
            displayName: 'Description',
            width: 500
        }, {
            field: 'itemKeyWords',
            displayName: 'KeyWords',
            width: 250
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
            width: 250
        }

    ];


    $scope.collectionGrid = {
        enableFullRowSelection: true,
        multiSelect: false,
        columnDefs: $scope.columns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function(row) {
                $scope.collectionId = row.entity.id;
                $scope.collectionName = row.entity.name;
                $scope.collectionDescription = row.entity.description;;
            });
        }
    };

    $scope.itemGrid = {

        enableRowSelection: true,
        multiSelect: true,
        columnDefs: $scope.itemGridColumns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            $scope.gridApi.selection.toggleRowSelection($scope.itemGrid.data[0]);
            gridApi.selection.on.rowSelectionChanged($scope, function(row) {
                if ($scope.selectItems.indexOf(row.entity.id) == -1) {
                    $scope.selectItems.push(row.entity.id);
                } else {
                    $scope.selectItems.splice($scope.selectItems.indexOf(row.entity.id), 1);
                }
            });
        }
    };

    $http.get("/TCHA/collections").success(function(data) {
        $scope.itemGrid.data = data.itemDtos;
        /*          $timeout(function() {
                       if($scope.gridApi.selection.selectRow){
                         $scope.gridApi.selection.selectRow($scope.itemGrid.data[3]);
                            $scope.gridApi.selection.selectRow($scope.itemGrid.data[1]);
                       }
                     });*/
        $scope.collectionGrid.data = data.collectionDto;
    });


    $scope.formClear = function() {
        $scope.clearItem();
        $scope.successMessage = false;
        $scope.deleteMessage = false;

        $http.get("/TCHA/collections").success(function(data) {
         $scope.itemGrid.data = data.itemDtos;
         $scope.collectionGrid.data = data.collectionDto;
        });
    };

    $scope.clearItem = function() {
        $scope.collectionName = null;
        $scope.collectionDescription = null;
        $scope.collectionId = null;
        $scope.selectItems = [];

        $scope.collectionNameRequired = false;
        $scope.collectionDescriptionRequired = false;

    };


    function validateForm() {

        if ($scope.collectionName == null) {
            $scope.collectionNameRequired = true;
            return false;
        }
        if ($scope.collectionDescription == null) {
            $scope.collectionDescriptionRequired = true;
            return false;
        }

        return true;
    }

    $scope.createAndUpdateCollection = function() {
        $scope.deleteMessage = false;
        if (validateForm()) {

            var dataObj = {
                id: $scope.collectionId,
                name: $scope.collectionName,
                description: $scope.collectionDescription
            };
            var fd = new FormData();
            fd.append('itemArray', JSON.stringify($scope.selectItems));
            fd.append('collection', JSON.stringify(dataObj));
            var res = $http.post("/TCHA/collections/collection", fd, {
                transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            });
            res.success(function(data, status, headers, config) {
                $scope.collectionGrid.data = data.collectionDto;
                $scope.successMessage = true;;
                $scope.clearItem();
            });
            res.error(function(data, status, headers, config) {
                //alert( "failure message: " + JSON.stringify({data: data}));
            });
        }
    };

    $scope.removeCollection = function() {
        $scope.successMessage = false;
        if ($scope.id !== null) {
            var res = $http.delete("/TCHA/collections/collection?deleteCollection=" + $scope.collectionId);
            res.success(function(data, status, headers, config) {
                $scope.collectionGrid.data = data.collectionDto;
                $scope.clearItem();
                $scope.deleteMessage = true;
            });
            res.error(function(data, status, headers, config) {
                //alert( "failure message: " + JSON.stringify({data: data}));
            });
        }
    };


});