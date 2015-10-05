angular.module('tchaApp', ['ui.bootstrap', 'ui.grid', 'ui.grid.selection']);

angular.module('tchaApp').controller('dashBordController', function($scope) {

    $scope.subjects = ['Search', 'Recent Additions', 'Add New Item', 'Validate', 'Users'];

    $scope.selectedItem;
    $scope.searchUI = false;
    $scope.recentAdditionsUI = false;
    $scope.addNewItemUI = false;
    $scope.validateUI = false;
    $scope.userUI = false;

    $scope.dropboxitemselected = function(item) {
        $scope.selectedItem = item;
        if (item == "Search") {
            $scope.searchUI = true;
            $scope.recentAdditionsUI = false;
            $scope.addNewItemUI = false;
            $scope.validateUI = false;
            $scope.userUI = false;
        }
        if (item == "Recent Additions") {
            $scope.recentAdditionsUI = true;
            $scope.searchUI = false;
            $scope.addNewItemUI = false;
            $scope.validateUI = false;
            $scope.userUI = false;
        }
        if (item == "Add New Item") {
            $scope.addNewItemUI = true;
            $scope.searchUI = false;
            $scope.recentAdditionsUI = false;
            $scope.validateUI = false;
            $scope.userUI = false;
        }
        if (item == "Validate") {
            $scope.validateUI = true;
            $scope.searchUI = false;
            $scope.recentAdditionsUI = false;
            $scope.addNewItemUI = false;
            $scope.userUI = false;
        }
        if (item == "Users") {
            $scope.userUI = true;
            $scope.searchUI = false;
            $scope.recentAdditionsUI = false;
            $scope.addNewItemUI = false;
            $scope.validateUI = false;
        }



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
    $scope.format = $scope.formats[0];


});

angular.module('tchaApp').controller('login', function($scope) {
    $scope.showModal = false;
    $scope.toggleModal = function() {
        $scope.showModal = !$scope.showModal;
    };
});

angular.module('tchaApp').directive('modal', function() {
    return {
        template: '<div class="modal fade" data-keyboard="false" data-backdrop="static">' +
            '<div class="modal-dialog">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            //'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' +
            '<h4 class="modal-title">{{ title }}</h4>' +
            '</div>' +
            '<div class="modal-body" ng-transclude></div>' +
            '</div>' +
            '</div>' +
            '</div>',
        restrict: 'E',
        transclude: true,
        replace: true,
        scope: true,
        link: function postLink(scope, element, attrs) {
            scope.title = attrs.title;

            scope.$watch(attrs.visible, function(value) {
                if (value == true)
                    $(element).modal('show');
                else
                    $(element).modal('hide');
            });

            $(element).on('shown.bs.modal', function() {
                scope.$apply(function() {
                    scope.$parent[attrs.visible] = true;
                });
            });

            $(element).on('hidden.bs.modal', function() {
                scope.$apply(function() {
                    scope.$parent[attrs.visible] = false;
                });
            });
        }
    };
});