 <div style="margin:65px 0px 0px 170px;" >
  <p>User Login</p>
 </div>
 <div style="margin:0px 170px 0px 170px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;"  >

<div class="row">
             <div class="container" style=" margin-left: 50px;margin-top: 30px;margin-bottom: 30px;">
                  <div class="row" >
                     <div class="col-md-5">
                        <form class="form-horizontal">
                           <div>
                              <p>Username</p>
                              <div class="controls">
                                 <input type="text" id="username" name="username" ng-model="username" placeholder="" class="form-control" >
                              </div>
                           </div>

                           <div style="margin-top: 5px;">
                              <p>Password</p>
                              <div class="controls">
                                 <input type="password" id="password" name="password" ng-model="password" placeholder="" class="form-control">
                              </div>
                           </div>


                           <div class="controls" >
                                 <button class="btn" style=" margin-top:15px;" ng-click="login(username, password)" >Login</button>
                                 <button class="btn" style=" margin-top:15px;">Clear</button>
                           </div>

                        </form>
                     </div>
                  </div>
             </div>
             </div>
 </div>