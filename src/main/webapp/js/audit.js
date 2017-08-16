angular.module('tchaApp').controller('auditController', function($scope, $http, $timeout, $sce) {

    $scope.itemName;
    $scope.selectedUser = null;
    $scope.users = [];

    $http.get("/TCHA/accounts").success(function(data) {
        angular.forEach(data, function(user) {
            var user ={"label":user.name,"id":user.id}
            $scope.users .push(user);
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

    $scope.search = function(itemName,selectedUser,startDate,endDate) {

        var dataObj = {
            name: itemName,
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