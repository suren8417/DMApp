<!DOCTYPE html>
<html ng-app="tchaApp">
   <head>
      <title> Trinity College Historical Archive </title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="css/bootstrap.min.css">
      <link rel="stylesheet" href="css/ui-grid.min.css">
      <link rel="stylesheet" href="css/image-gallery.css">
      <script src="js/angular.js"></script>
      <script src="js/angular-route.js"></script>
      <script src="js/angular-cookies.js"></script>
      <script src="js/ui-bootstrap-tpls-0.13.0.js"></script>
      <script src="js/jquery-1.11.3.js"></script>
      <script src="js/bootstrap.min.js"></script>
      <script src="js/ui-grid.min.js"></script>
      <script src="js/dashBoard.js"></script>
      <script src="js/authenticationServices.js"></script>
      <script src="js/login.js"></script>
      <script src="js/search.js"></script>
      <script src="js/user.js"></script>
   </head>

   <body>

      <div >
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
                  <ul class="nav navbar-nav" >
                     <li class="active dropdown" >
                        <a class="dropdown-toggle" data-toggle="dropdown" > <span class="glyphicon glyphicon-cog"></span> Actions <span class="caret"></span></a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" >
                             <li><a href="#/search"><i ></i>Search</a></li>
                             <li><a href="#/addNewItem"><i ></i>New Item</a></li>
                             <li><a href="#/validate"><i ></i>Validate Item</a></li>
                             <li><a href="#/manageCollection"><i ></i>Manage Collection</a></li>
                             <li><a href="#/user"><i ></i>Users</a></li>
                        </ul>
                     </li>
                  </ul>
                  <ul class="nav navbar-nav navbar-right" style="padding-right: 150px;" ng-show="userTask">
                     <li><a href="#"><span class="glyphicon glyphicon-user"><span ng-bind="userType"></span></a></li>
                     <li><a href="#" ng-click="sineOut()"><span class="glyphicon glyphicon-log-in" ></span> Sign Out</a></li>
                  </ul>
               </div>
            </div>
         </nav>
         <div id="main">
           <div ng-view>
           </div>
         </div>
         </div>
      </div>

   </body>
</html>