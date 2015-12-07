angular.module('tchaApp').controller('collectionController', function($scope) {

    $scope.collectionName;
    $scope.collectionDescription;

    $scope.columns = [{
        field: 'collectionName',
        width: 200
    }, {
        field: 'collectionDescription',
        width: 700
    }];
    $scope.collectionGrid = {
        enableFullRowSelection: true,
        multiSelect: false,
        columnDefs: $scope.columns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function(row) {
                $scope.collectionName = row.entity.collectionName;
                $scope.collectionDescription = row.entity.collectionDescription;;
            });
        }
    };

    $scope.collectionGrid.data = [{
        "collectionName": "New Year 2001",
        "collectionDescription": "validation error key if the ngModel value does not match a RegExp found by evaluating the Angular expression given in the attribute value. If the expression evaluates to a RegExp object"
    }, {
        "collectionName": "1985 Football team",
        "collectionDescription": "validation error key if the ngModel value does not"
    }, {
        "collectionName": "2000 Big match",
        "collectionDescription": " match a RegExp found by evaluating the Angular expression given in the attribute value. If the expression evaluates to a RegExp object"
    }, {
        "collectionName": "2000 Big match",
        "collectionDescription": "found by evaluating the Angular expression given in the attribute value. If the expression evaluates "
    }, {
        "collectionName": "2000 Big match",
        "collectionDescription": "found by evaluating the Angular expression given in the attribute value. If the expression evaluates "
    }, {
        "collectionName": "2000 Big match",
        "collectionDescription": "found by evaluating the Angular expression given in the attribute value. If the expression evaluates "
    }, {
        "collectionName": "2000 Big match",
        "collectionDescription": "found by evaluating the Angular expression given in the attribute value. If the expression evaluates "
    }, {
        "collectionName": "2000 Big match",
        "collectionDescription": "found by evaluating the Angular expression given in the attribute value. If the expression evaluates "
    }]


    $scope.itemGridColumns = [{
            field: 'Type',
            width: 100
        }, {
            field: 'Title',
            width: 200
        }, {
            field: 'DateOfOrigin',
            width: 150
        }, {
            field: 'DateOfAdd',
            width: 150
        }, {
            field: 'DateOfValidate',
            width: 150
        }, {
            field: 'Donor',
            width: 150
        }, {
            field: 'Description',
            width: 400
        }, {
            field: 'Status',
            width: 100
        }

    ];

    $scope.itemGrid = {
        enableFullRowSelection: true,
        multiSelect: true,
        columnDefs: $scope.itemGridColumns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function(row) {

            });
        }
    };



  $scope.itemGrid.data = [{
         "Type": "Carney",
         "Title": "DataEntry",
         "DateOfOrigin": "DataEntry",
         "DateOfAdd": "DataEntry",
         "DateOfValidate": "DataEntry",
         "Donor": "DataEntry",
         "Description": "DataEntry",
         "Status": "DataEntry"
     }, {
         "Type": "Carney",
         "Title": "DataEntry",
         "DateOfOrigin": "DataEntry",
         "DateOfAdd": "DataEntry",
         "DateOfValidate": "DataEntry",
         "Donor": "DataEntry",
         "Description": "DataEntry",
         "Status": "DataEntry"
     }, {
         "Type": "Carney",
         "Title": "DataEntry",
         "DateOfOrigin": "DataEntry",
         "DateOfAdd": "DataEntry",
         "DateOfValidate": "DataEntry",
         "Donor": "DataEntry",
         "Description": "DataEntry",
         "Status": "DataEntry"
     }];


});