<!DOCTYPE html>
<html ng-app="tchaApp">
   <head>
      <title> Trinity College Historical Archive </title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="css/bootstrap.min.css">
      <link rel="stylesheet" href="css/ui-grid.min.css">
      <script src="js/angular.js"></script>
      <script src="js/ui-bootstrap-tpls-0.13.0.js"></script>
      <script src="js/dashBoard.js"></script>
      <script src="js/user.js"></script>
      <script src="js/jquery-1.11.3.js"></script>
      <script src="js/bootstrap.min.js"></script>
      <script src="js/ui-grid.min.js"></script
   </head>

   <body>

      <div ng-controller="dashBordController">
         <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
               <div class="navbar-header"  style="padding-left: 150px;">
                  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>                        
                  </button>
                  <a class="navbar-brand" href="#"> <span class="glyphicon glyphicon-home"></span> Trinity College Historical Archive </a>
               </div>
               <div class="collapse navbar-collapse" id="myNavbar"  >
                  <ul class="nav navbar-nav">
                     <li class="active dropdown" ng-show="userTask">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"> <span class="glyphicon glyphicon-wrench"></span> Actions <span class="caret"></span></a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                           <li ng-repeat="a in subjects"><a href="#" ng-click="dropboxitemselected(a)">{{a}}</a></li>
                        </ul>
                     </li>
                  </ul>
                  <ul class="nav navbar-nav navbar-right" style="padding-right: 150px;" ng-show="userTask">
                     <li><a href="#"><span class="glyphicon glyphicon-user"><span ng-bind="userType"></span></a></li>
                     <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Sign Out</a></li>
                  </ul>
               </div>
            </div>
         </nav>
          <jsp:include page="login.jsp" />
         <jsp:include page="search.jsp" />
         <jsp:include page="addNewItem.jsp" />
         <jsp:include page="recentAdditions.jsp"/>
         <jsp:include page="validate.jsp" />
         <jsp:include page="user.jsp" />
      </div>

   </body>
</html>