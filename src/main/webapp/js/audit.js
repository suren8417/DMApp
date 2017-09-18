angular.module('tchaApp').controller('auditController', function($scope, $http, $timeout, $sce) {

    $scope.itemName;
    $scope.collectionName;
    $scope.selectedUser;
    $scope.users = [];
    $scope.items = [];
    $scope.collections = [];
    $scope.searchBy = "user";


    $http.get("/TCHA/accounts").success(function(data) {
        angular.forEach(data, function(user) {
            var user ={"label":user.name,"id":user.id}
            $scope.users .push(user);
        });
    });


    $http.get("/TCHA/items/audit").success(function(data) {
        angular.forEach(data.itemDtos, function(item) {
            var user ={"label":item.itemTitle,"id":item.id}
            $scope.items .push(user);
        });
    });

    $http.get("/TCHA/collections").success(function(data) {
        angular.forEach(data.collectionDto, function(collection) {
            var user ={"label":collection.name,"id":collection.id}
            $scope.collections .push(user);
        });
    });
    $scope.auditItemGridColumns = [{
            field: 'operation',
            displayName: 'Operation',
            width: 100
        }, {
            field: 'auditorName',
            displayName: 'Auditor',
            width: 200
        }, {
            field: 'itemCode',
            displayName: 'Item Code',
            width: 200

        },
        {
            field: 'type',
            displayName: 'Type',
            width: 200

        }, {
            field: 'title',
            displayName: 'Title',
            width: 200

        }
        , {
            field: 'dateAdded',
            displayName: 'Date Added',
            width: 200
        }, {
            field: 'addedBy',
            displayName: 'Added By',
            width: 200
        }, {
            field: 'dateValidated',
            displayName: 'Date Validated',
            width: 200
        }, {
            field: 'validatedBy',
            displayName: 'Validated By',
            width: 200
        }, {
            field: 'donor',
            displayName: 'Donor',
            width: 200
        }, {
            field: 'description',
            displayName: 'Description',
            width: 500
        }, {
            field: 'keywords',
            displayName: 'Key words',
            width: 500
        }, {
            field: 'auditTime',
            displayName: 'Audit Time',
            width: 200
        }, {
            field: 'note',
            displayName: 'Note',
            width: 500
        }
    ];


    $scope.auditCollectionGridColumns = [
        {
            field: 'operation',
            displayName: 'Operation',
            width: 200
        }, {
            field: 'auditorName',
            displayName: 'Auditor',
            width: 200
        }, {
            field: 'name',
            displayName: 'Name',
            width: 250
        }, {
            field: 'description',
            displayName: 'Description',
            width: 500
        }, {
            field: 'auditTime',
            displayName: 'Audit Time',
            width: 200
        }
    ];

    $scope.auditItemGrid = {
        enableFullRowSelection: true,
        multiSelect: false,
        enableFiltering: true,
        enableSelectAll: false,
        enableColumnMenus: false,
        columnDefs: $scope.auditItemGridColumns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
        }
    };


    $scope.auditCollectionGrid = {
        enableFullRowSelection: true,
        multiSelect: false,
        enableFiltering: true,
        enableSelectAll: false,
        enableColumnMenus: false,
        columnDefs: $scope.auditCollectionGridColumns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
        }
    };

    $scope.search = function(startDate,endDate) {

        var dataObj = {
            item: $scope.itemName,
            selectedSearch:$scope.searchBy,
            collection: $scope.collectionName,
            userName : $scope.selectedUser,
            startDate: startDate,
            endDate: endDate

        };

        $http.get("/TCHA/audits?searchQuery=" + JSON.stringify(dataObj))
            .success(function(data) {
                $scope.auditItemGrid.data = data.auditItemDtos;
                $scope.auditCollectionGrid.data = data.auditCollectionDtos;
            });

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
    $scope.dtmax = new Date();
});