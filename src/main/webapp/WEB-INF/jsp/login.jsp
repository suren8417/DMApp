<div style="margin:65px 0px 0px 170px;">
    <p>User Login</p>
</div>
<div style="margin:0px 170px 0px 170px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;">

    <div class="row">
        <div class="container" style=" margin-left: 50px;margin-top: 30px;margin-bottom: 30px;">
            <div class="row" ng-controller="LoginController">

                <div class="col-md-4">
                    <div class="controls">
                        <p style="color:red" ng-show="loginError"><span class="glyphicon glyphicon-exclamation-sign"></span>Invalid Username or Password</p>
                    </div>
                    <div>
                        <p>Username</p>
                        <div class="controls">
                            <input type="text" id="username" name="username" ng-model="username" placeholder="" class="form-control" maxlength="10">
                        </div>
                    </div>

                    <div style="margin-top: 5px;">
                        <p>Password</p>
                        <div class="controls">
                            <input type="password" id="password" name="password" ng-model="password" placeholder="" class="form-control" maxlength="10">
                        </div>
                    </div>


                    <div class="controls">
                        <button class="btn" style=" margin-top:15px; width: 100px;" ng-click="login(username, password)">Login</button>

                    </div>

                </div>
                <div class="col-md-6" style="margin-left: 100px;">
                    <img src="images/trinityCollegeKandy.jpg" width="300" height="150" align="right">
                </div>
            </div>
        </div>
    </div>
</div>