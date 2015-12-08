angular.module('tchaApp').controller('userController', function($scope,$http) {

    var deployedAt = window.location.href.substring(0, window.location.href);

    $scope.userName= null;
    $scope.selectedItemvalue=null;
    $scope.password=null;
    $scope.rePassword=null;
    $scope.userId=null;

    $scope.userNameRequired=false;
    $scope.passwordRequired=false;
    $scope.rePasswordRequired=false;
    $scope.reCorrectPasswordRequired=false;
    $scope.roleRequired=false;
    $scope.successMessage=false;
    $scope.deleteMessage=false;
    $scope.userNameExist=false;

    $scope.selectables = [{
        label: 'Viewer',
        id: '2'
    }, {
        label: 'DataEntry',
        id: '3'
    }, {
        label: 'Curator',
        id: '4'
    }, {
        label: 'Administrator',
        id: '1'
    }];

    $scope.columns = [
       {
        field: 'name',
        displayName:'Username',
        width: 200
    }, {
        field: 'roleName',
        displayName:'Role',
        width: 100
    },
       {field: 'roleId',
        displayName:'RoleId',
        visible:false
       }
     ,
       {field: 'password',
        displayName:'Password',
        visible:false
       }
     ,
     { field: 'id',
       displayName:'Id',
       visible:false
     }
    ];

    $scope.gridOptions = {
        enableFullRowSelection: true,
        multiSelect: false,
        columnDefs: $scope.columns,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function(row) {
                $scope.userName = row.entity.name;
                $scope.selectedItemvalue = row.entity.roleName;
                $scope.password= row.entity.password;
                $scope.rePassword= row.entity.password;
                $scope.userId=row.entity.id;
            });
        }
    };

    function selectRoleId(selectValue) {
      for (i in $scope.selectables) {
        if($scope.selectables[i].label ==selectValue){
          return $scope.selectables[i].id;
        }
       }
    }

    function validateForm() {

    if($scope.userName == null){
         $scope.userNameRequired=true;
         return false;
    }
    if($scope.password == null){
         $scope.passwordRequired=true;
         return false;
    }
    if($scope.rePassword == null){
         $scope.rePasswordRequired=true;
         return false;
    }
     if($scope.rePassword !== $scope.password){
             $scope.reCorrectPasswordRequired=true;
             return false;
        }
    if($scope.selectedItemvalue == null){
             $scope.roleRequired=true;
             return false;
    }

     return true;
    }

    $http.get(deployedAt+"/TCHA/accounts").success(function(data) {
          $scope.gridOptions.data = data;
    });


    $scope.formClear = function() {
     $scope.clearUser();
     $scope.successMessage=false;
     $scope.deleteMessage=false;
     $scope.userNameExist=false;
    };

    $scope.clearUser = function() {
      $scope.userName= null;
      $scope.selectedItemvalue=null;
      $scope.password=null;
      $scope.rePassword=null;
      $scope.userId=null;

      $scope.userNameRequired=false;
      $scope.passwordRequired=false;
      $scope.rePasswordRequired=false;
      $scope.reCorrectPasswordRequired=false;
      $scope.roleRequired=false;

    };


    $scope.removeUser = function() {
    if($scope.userId !== null){
     var  selectedRoleId = selectRoleId($scope.selectedItemvalue);
       	var dataObj = {id:$scope.userId,name : $scope.userName, password : $scope.rePassword, roleId : selectedRoleId, roleName:$scope.selectedItemvalue};
       	var res = $http.delete(deployedAt+"/TCHA/accounts/account?deleteUser="+ $scope.userId);
       	res.success(function(data, status, headers, config) {
       		  $scope.gridOptions.data = data;
       		  $scope.successMessage=false;
       		  $scope.deleteMessage=true;
       		   $scope.clearUser();
       	});
       	res.error(function(data, status, headers, config) {
       			alert( "failure message: " + JSON.stringify({data: data}));
       	});
     }
    };

    $scope.createOrUpdateUser = function() {
    if(validateForm()){
        if($scope.userId == null){
         $scope.createUser();}
        else{
         $scope.updateUser();
        }
        }
       $scope.deleteMessage=false;
       $scope.userNameExist=false;

    };

    $scope.updateUser = function() {
      var  selectedRoleId = selectRoleId($scope.selectedItemvalue);
      	var dataObj = {id:$scope.userId,name : $scope.userName, password : $scope.rePassword, roleId : selectedRoleId, roleName:$scope.selectedItemvalue};
      	var res = $http.put(deployedAt+"/TCHA/accounts/account", dataObj);

      	res.success(function(data, status, headers, config) {
      	        $scope.gridOptions.data = data;
      	        $scope.successMessage=true;
      	});
      	res.error(function(data, status, headers, config) {
      			alert( "failure message: " + JSON.stringify({data: data}));
      	});
    };

    $scope.createUser = function() {
        var  selectedRoleId = selectRoleId($scope.selectedItemvalue);
      	var dataObj = {name : $scope.userName, password : $scope.rePassword, roleId : selectedRoleId, roleName:$scope.selectedItemvalue};
      	var res = $http.post(deployedAt+"/TCHA/accounts/account", dataObj);
      	res.success(function(data, status, headers, config) {
      	 $scope.gridOptions.data = data;
      	 $scope.successMessage=true;
      	});
      	res.error(function(data, status, headers, config) {
      	$scope.userNameExist=true;
      	//alert( "failure message: " + JSON.stringify({data: data}));
      	});
    };


});