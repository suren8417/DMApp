angular.module('tchaApp').controller('auditController', function($scope, $http, $timeout, $sce) {

    $scope.itemName;
    $scope.collectionName;
    $scope.selectedUser = null;
    $scope.users = [];
    $scope.items = [];
    $scope.collections = [];
    $scope.showAuditby = true;
    $scope.showCollectinby = false;
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
    $scope.auditItemGridColumns = [

        {
            field: 'name',
            displayName: 'Name',
            width: 500

        },
        {
            field: 'auditorName',
            displayName: 'Auditor Name'

        }, {
            field: 'type',
            displayName: 'Type'

        }
        , {
            field: 'auditTime',
            displayName: 'Audit Time',
            width: 200

        }, {
            field: 'operation',
            displayName: 'Operation'

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

    $scope.showByItem = function() {
        $scope.showAuditby = false;
    };

    $scope.showByUser = function() {
        $scope.showAuditby = true;
    };

    $scope.showByCollection= function() {
        if($scope.showCollectinby){
            $scope.showCollectinby = false;
            $scope.collectionName = null;

        }else{
            $scope.showCollectinby = true;
            $scope.itemName = null;
        }
    };

    $scope.search = function(itemName,collectionName,selectedUser,startDate,endDate) {

        var dataObj = {
            item: itemName,
            collection: collectionName,
            userName : selectedUser,
            startDate: startDate,
            endDate: endDate

        };

        $http.get("/TCHA/audits?searchQuery=" + JSON.stringify(dataObj))
            .success(function(data) {
                $scope.auditItemGrid.data = data;
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