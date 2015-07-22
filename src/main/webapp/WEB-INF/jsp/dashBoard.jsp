<html >
<head>
    <link rel="stylesheet" type="text/css" href = "css/bootstrap.min.css">
    <script src="js/angular.min.js"></script>
    <script src="js/ui-bootstrap-tpls-0.13.0.js"></script>
    <script src="js/myUsers.js"></script>
</head>
<body>
  <div ng-app="my-app">
    <div ng-controller="treeController">
      <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
          <a class="navbar-brand" href="#">
            <span>Trinity College Historical Archive</span>
          </a>
        </div>
        <ul class="nav navbar-nav">
          <li class="dropdown" dropdown on-toggle="toggled(open)">
            <a href="#" class="dropdown-toggle" dropdown-toggle role="button">
              Dropdown
              <span class='caret'></span>
            </a>
            <tree tree='tree'></tree>
          </li>
          <li class="dropdown" dropdown on-toggle="toggled(open)">
            <a href="#" class="dropdown-toggle" dropdown-toggle role="button">
              Just a clone
              <span class='caret'></span>
            </a>
            <tree tree='tree'></tree>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</body>
</html>