angular.module('tchaApp').controller('userGrid', function($scope) {

    $scope.UserName;
    $scope.selectedItemvalue;
    $scope.selectables = [{
        label: 'Viewer',
        value: 'Viewer'
    }, {
        label: 'DataEntry',
        value: 'DataEntry'
    }, {
        label: 'Curator',
        value: 'Curator'
    }, {
        label: 'Administrator',
        value: 'Administrator'
    }];

    $scope.columns = [{
        field: 'Username',
        width: 200
    }, {
        field: 'Role',
        width: 100
    }];
    $scope.gridOptions = {
        enableFullRowSelection: true,
        multiSelect: false,
        columnDefs: $scope.columns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function(row) {
                $scope.UserName = row.entity.Username;
                $scope.selectedItemvalue = row.entity.Role;;
            });
        }
    };

    $scope.gridOptions.data = [{
        "Username": "Carney",
        "Role": "DataEntry"
    }, {
        "Username": "Ase",
        "Role": "Viewer"
    }, {
        "Username": "Bdd",
        "Role": "Administrator"
    }]


});