<div style="margin:0px 100px 0px 100px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;">
    <div class="row">
	<legend style="width:94%; margin-left:30px;"><h3><b style="color:#1D1F5A;">User Registration</b></h3></legend> 
        <div class="container" style=" margin-left: 50px;margin-top: 30px;margin-bottom: 30px;">
            <div class="row" ng-controller="userController">
                <div class="col-md-5">

                  <form name="userForm">
                        <div>
                            <p>Username</p>
                            <div class="controls">
                                <input type="text" id="username" name="username" placeholder="" class="form-control" ng-model="userName" maxlength="10" >
                                </p>
                                <p style="color:red" ng-show="userNameRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Please enter username</p>
                                <p style="color:red" ng-show="userNameExist"><span class="glyphicon glyphicon-exclamation-sign"></span> Username Exists</p>
                            </div>
                        </div>
                        <div>
                            <p style="margin-top: 10px;">Password</p>
                            <div class="controls">
                                <input type="password" id="password1" name="password1" placeholder="" class="form-control" ng-model="password" maxlength="10" >
                                </p>
                                <p style="color:red" ng-show="passwordRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Please enter password</p>
                            </div>
                        </div>
                        <div>
                            <p style="margin-top: 10px;">Password (Confirm)</p>
                            <div class="controls">
                                <input type="password" id="password_confirm" name="password_confirm" placeholder="" class="form-control" ng-model="rePassword" maxlength="10" >
                                </p>
                                 <p style="color:red" ng-show="rePasswordRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Please confirm password</p>
                                 <p style="color:red" ng-show="reCorrectPasswordRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Password not match</p>
                            </div>
                        </div>
                        <div >
                            <p style="margin-top: 10px;"> Roles </p>
                            <div class="controls">

                                <select ng-model="selectedItemvalue" class="form-control" style="width: 30%;" required>
                                    <option ng-repeat="sel in selectables" >{{sel.label}}</option>
                                </select>
                                </p>
                                 <p style="color:red" ng-show="roleRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Please select role</p>
                            </div>
                        </div>
                      <div>
                          <p style="color:green;font-size: 25px;" ng-show="successMessage"><span class="glyphicon glyphicon-ok"></span> Successfully saved</p>
                          <p style="color:green;font-size: 25px;" ng-show="deleteMessage"><span class="glyphicon glyphicon-ok"></span> Successfully removed</p>
                      </div>
                        <div class="controls">
                            <button class="btn" style=" margin-top:15px;width: 100px;" ng-click="createOrUpdateUser()">Save</button>
                            <button class="btn" style=" margin-top:15px;width: 100px;" ng-click="removeUser()">Remove</button>
                            <button class="btn" style=" margin-top:15px;width: 100px;" ng-click="formClear()">Clear</button>
                        </div>

                  </form>
                </div>
                <div class="col-md-6">
                    <div style=" width: 350px; height: 325px;margin-left: 25px;margin-top: 25px;">
                        <div id="grid1" ui-grid="gridOptions" ui-grid-selection class="grid" style="height: 310px;" ></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<img src="images/Badge.png" width="10%" style="margin-left:100px;"/>