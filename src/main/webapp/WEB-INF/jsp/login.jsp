<div style="margin:150px 100px 0px 100px;">

<center><img src="images/Tri_Logo.png" width="30%"/></center>

    <div class="row" style="margin:20px 0px 0px 0px;">
            <div ng-controller="LoginController">
                <div class="col-md-4 col-md-offset-4">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">User Login</h3>
						</div>
						<div class="panel-body">
							<div class="controls">
								<p style="color:red" ng-show="loginError"><span class="glyphicon glyphicon-exclamation-sign"></span> Invalid Username or Password</p>
							</div>
							
							<div class="input-group controls">
							  <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
							  <input type="text" id="username" name="username" ng-model="username" placeholder="Username" class="form-control" maxlength="10">
							</div>
							
							<div class="input-group controls" style="margin-top: 20px;">
							  <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></span>
							  <input type="password" id="password" name="password" ng-model="password" placeholder="Password" class="form-control" maxlength="10" onkeydown="if (event.keyCode == 13) document.getElementById('loginBtn').click()">
							</div>
							
							<div class="controls">
								<button class="btn" id="loginBtn" style=" margin-top:15px; width: 100px;" ng-click="login(username, password)">Login</button>
							</div>
						</div>
					</div>
				
                </div>
            </div>
    </div>
<center><img src="images/Badge.png" width="30%"/></center>
</div>