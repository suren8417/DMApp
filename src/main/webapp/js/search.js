
angular.module('tchaApp').controller('searchController', function($scope,$http) {

 $scope.searchData=null;
 $scope.searchDataInDetail=null;
 $scope.resultCount;
 $scope.searchSummary= false;
 $scope.searchInDetail= false;

 $scope.search = function(searchText,image,document,audio,video,collection,startDate,endDate){
    $http.get("/TCHA/searches?searchText="+ searchText+"&image="+image+"&document="+document+"&audio="+audio+"&video="+video+"&collection="+collection+"&startDate="+startDate+"&endDate="+endDate)
         .success(function(data) {
             $scope.searchData = data.searchResultDtos;
         });

 $scope.searchSummary= true;
 $scope.searchInDetail= false;
 $scope.resultCount= $scope.searchData.length.toString() +" Results were retrieved";
 };

$scope.showSearchInDetail = function(id){
alert(id);
/*  $scope.searchInDetail= true;
  $scope.searchSummary= false;
  $scope.searchDataInDetail=  { "title":"2005 Cricket team - Image ",
                                "description":"I actually quite "};*/
};

$scope.hidePreviousSearchDetails= function(){
  $scope.searchSummary= false;
  $scope.searchInDetail= false;
  $scope.searchData=null;
  $scope.searchDataInDetail=null;;

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



    var IMAGE_WIDTH = 950;
    $scope.IMAGE_LOCATION = "http://rabidgadfly.com/assets/angular/gallery1/";
    $scope.galleryData = [
                        {
                          "title":"Altair",
                          "id":"ED02-0185-01",
                          "desc":"General Atomics Aeronautical Systems, Inc., is developing the Altair version of its Predator B unmanned reconnaissance aircraft, shown here, under NASA's Environmental Research Aircraft and Sensor Technology (ERAST) project. NASA plans to use the Altair as a technology demonstrator testbed aircraft to validate a variety of command and control technologies for unmanned aerial vehicles (UAV), as well as demonstrate the capability to perform a variety of Earth science missions.",
                          "image":"altair7.jpg"
                        },
                        {
                          "title":"Altair",
                          "id":"ED03-0078-1",
                          "desc":"The Altair unmanned aerial vehicle (UAV), built by General Atomics Aeronautical Systems, Inc. for NASA, is poised for flight at GA-ASI's flight test facility at El Mirage, California. The Altair is a modified civil version of the QM-9 Predator B UAV developed by GA-ASI for the U.S. Air Force.",
                          "image":"altair6.jpg"
                        },
                            {
                                "title":"Altair",
                                "id":"EC03-0154-3",
                                "desc":"The remotely-piloted Altair unmanned aerial vehicle (UAV) took to the air on its first checkout flight on June 9, 2003 at El Mirage, California. The aircraft was developed for NASA by General Atomics Aeronautical Systems, Inc. as a long-endurance, high-altitude platform for development of UAV technologies and environmental science missions.",
                                "image":"altair5.jpg"
                            },
                            {
                                "title":"Altair",
                                "id":"ED05-0082-03",
                                "desc":"The long, slender wings of General Atomics Altair UAV are in evidence during a series of climatic and environmental monitoring missions for NOAA and NASA in the spring of 2005.",
                                "image":"altair4.jpg"
                            },
                            {
                                "title":"Altair",
                                "id":"EC05-0090-19",
                                "desc":"A satellite antenna, electro-optical/infrared and ocean color sensors (front) were among payloads installed on the Altair for the NOAA-NASA UAV flight demonstration.",
                                "image":"altair3.jpg"
                            },
                            {
                                "title":"Altair",
                                "id":"ED06-0208-3",
                                "desc":"Equipped with a pod-mounted infrared imaging sensor, the Altair UAS aided fire mapping efforts over wildfires in central and southern California in late 2006.",
                                "image":"altair2.jpg"
                            },
                            {
                                "title":"Altair",
                                "id":"ED06-0208-1",
                                "desc":"A high-tech infrared imaging sensor in its underbelly pod, the Altair unmanned aircraft flew repeated passes over the Esperanza fire to aid firefighting efforts.",
                                "image":"altair1.jpg"
                            }
                        ];
    $scope.selected =  $scope.galleryData[0];

    // Scroll to appropriate position based on image index and width
    $scope.scrollTo = function(image,ind) {
        $scope.listposition = {left:(IMAGE_WIDTH * ind * -1) + "px"};
        $scope.selected = image;
    };

});

