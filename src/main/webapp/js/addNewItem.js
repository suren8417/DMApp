angular.module('tchaApp').controller('newItemController', function($scope, $http) {

    $scope.itemsSelectedType;
    $scope.itemTitle;
    $scope.itemDonor;
    $scope.itemDescription;
    $scope.itemKeyWords;
    $scope.itemStartDate;
    $scope.uploadItem;
    $scope.id;


    $scope.itemType = [{
        label: 'Image',
        value: 'Image'
    }, {
        label: 'Document',
        value: 'Document'
    }, {
        label: 'Audio',
        value: 'Audio'
    }, {
        label: 'Video',
        value: 'Video'
    }];

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

    $http.get("/TCHA/items").success(function(data) {
        $scope.itemGrid.data = data.itemDtos;
    });

    $scope.itemGrid = {
        enableFullRowSelection: true,
        multiSelect: false,
        columnDefs: $scope.itemGridColumns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function(row) {
                $scope.itemsSelectedType = row.entity.itemsSelectedType;
                $scope.itemTitle = row.entity.itemTitle;
                $scope.itemDonor = row.entity.itemDonor;
                $scope.itemDescription = row.entity.itemDescription;
                $scope.itemKeyWords = row.entity.itemKeyWords;
                $scope.itemStartDate = row.entity.itemStartDate;
                $scope.id = row.entity.id;
                $scope.uploadItem = null;
            });
        }
    };

    $scope.clearItem = function() {
        $scope.itemsSelectedType = null;
        $scope.itemTitle = null;
        $scope.itemDonor = null;
        $scope.itemDescription = null;
        $scope.itemKeyWords = null;
        $scope.itemStartDate = null;
        $scope.uploadItem = null;
        $scope.id = null;
    };


    $scope.clear = function() {
        $scope.dt = null;
    };

    $scope.toggleMin = function() {
        $scope.minDate = $scope.minDate ? null : new Date();
    };

    $scope.toggleMin();

    $scope.toggleOpenDatePicker = function($event, datePicker) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope[datePicker] = !$scope[datePicker];
    };

    $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1
    };

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[1];



    $scope.createItem = function() {

        var dataObj = {
            id: $scope.id,
            itemsSelectedType: $scope.itemsSelectedType,
            itemTitle: $scope.itemTitle,
            itemDonor: $scope.itemDonor,
            itemDescription: $scope.itemDescription,
            itemKeyWords: $scope.itemKeyWords,
            itemStartDate: $scope.itemStartDate
        };
        var fd = new FormData();
        fd.append('itemDto', JSON.stringify(dataObj));
        if ($scope.uploadItem !== null) {
            fd.append('file', $scope.uploadItem);
            var res = $http.post("/TCHA/items/item", fd, {
                transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            });
            res.success(function(data, status, headers, config) {
                $scope.itemGrid.data = data.itemDtos;
                $scope.clearItem();
            });
            res.error(function(data, status, headers, config) {
                //alert( "failure message: " + JSON.stringify({data: data}));
            });
        } else {
            var res = $http.post("/TCHA/items/itemDetail", fd, {
                transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            });
            res.success(function(data, status, headers, config) {
                $scope.itemGrid.data = data.itemDtos;
                $scope.clearItem();
            });
            res.error(function(data, status, headers, config) {
                //alert( "failure message: " + JSON.stringify({data: data}));
            });
        }

    };


    $scope.removeItem = function() {
        if ($scope.id !== null) {
            var res = $http.delete("/TCHA/items/item?deleteItem=" + $scope.id);
            res.success(function(data, status, headers, config) {
                $scope.itemGrid.data = data.itemDtos;
                 $scope.clearItem();
            });
            res.error(function(data, status, headers, config) {
                //alert( "failure message: " + JSON.stringify({data: data}));
            });
        }
    };

});

angular.module('tchaApp').directive('fileModel', ['$parse', function($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function() {
                scope.$apply(function() {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);