<!DOCTYPE html>
<html ng-app="tchaApp">
   <head>
      <title> Trinity College Historical Archive </title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="css/bootstrap.min.css">
      <link rel="stylesheet" href="css/ui-grid.min.css">
	  <link rel="stylesheet" href="css/custom.css">
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
      <script src="js/addNewItem.js"></script>
      <script src="js/collection.js"></script>
      <script src="js/validate.js"></script>
   </head>

   <body> 

      <div >
         <nav class="navbar navbar-default navbar-fixed-top" ng-show="userTask">
            <div class="container-fluid">
               <div class="navbar-header"  style="padding-left: 150px; width: 30%">
                  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>                        
                  </button>
                  <a class="navbar-brand" href="#/search"> <img src="images/Tri_Logo.png" width="200px" style="margin-top:-10px;"/> </a>  
               </div>
               <div class="collapse navbar-collapse" id="myNavbar"  >
                  <ul class="nav navbar-nav" >
                     <li class="active dropdown">
                        <a href="" class="dropdown-toggle" data-toggle="dropdown" > <span class="glyphicon glyphicon-cog"></span> Actions <span class="caret"></span></a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" >
                              <li ng-repeat="a in subjects" ><a href={{'#/'+a.rout}} >{{a.displayText}}</a></li>
                        </ul>
                     </li>
                  </ul>
                  <ul class="nav navbar-nav navbar-right" style="padding-right: 150px;">
<%--                     <li><span class="glyphicon glyphicon-user" ><span ng-bind="userType" style=" margin-left: 8px;"></span></span></li>--%>
                     <li><a href="#/login" ><span class="glyphicon glyphicon-log-in" ></span> Sign Out</a></li> 
                  </ul>
               </div> 
            </div>
			<div class="progress" style="width:100%;height:2px;margin-bottom: 0px">
				<div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%;height:2px;"></div>
			</div>
			<div class="progress" style="width:100%;height:2px;margin-bottom: 0px">
				<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%;height:2px;"></div>
			</div>
			<div class="progress" style="width:100%;height:2px;margin-bottom: 0px">
				<div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%;height:2px;"></div>
			</div>
         </nav>
         <div id="main" style="margin-top: 100px;">
           <div ng-view>
           </div>
         </div>
		   
         </div>

   </body>
</html>