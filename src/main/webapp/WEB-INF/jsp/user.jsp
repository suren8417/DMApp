<div ng-show="userUI"  style="margin:65px 0px 0px 170px;" >
   <p>User Registration</p>
</div>
<div ng-show="userUI" style="margin:0px 170px 0px 170px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;" >
<div class="row">
   <div class="container" style=" margin-left: 50px;margin-top: 30px;margin-bottom: 30px;">
      <div class="row">
         <div class="col-md-5">
            <form class="form-horizontal" action="" method="POST">
               <div>
                  <p>Name</p>
                  <div class="controls">
                     <input type="txt" id="name" name="name" placeholder="" class="form-control">
                     <p class="help-block">Please provide your name</p>
                  </div>
               </div>
               <div>
                  <p>Designation</p>
                  <div class="controls">
                     <input type="txt" id="designation" name="designation" placeholder="" class="form-control">
                     <p class="help-block">Please provide your designation</p>
                  </div>
               </div>
               <div>
                  <p>Username</p>
                  <div class="controls">
                     <input type="text" id="username" name="username" placeholder="" class="form-control">
                     <p class="help-block">Username can contain any letters or numbers, without spaces</p>
                  </div>
               </div>
               <div>
                  <p>Password</p>
                  <div class="controls">
                     <input type="password" id="password" name="password" placeholder="" class="form-control">
                     <p class="help-block">Password should be at least 6 characters</p>
                  </div>
               </div>
               <div>
                  <p>Password (Confirm)</p>
                  <div class="controls">
                     <input type="password" id="password_confirm" name="password_confirm" placeholder="" class="form-control">
                     <p class="help-block">Please confirm password</p>
                  </div>
               </div>
               <div class="controls">
                  <p> Roles </p>
                     <div class="col-md-3">
                           <input type="radio" name="optradio"> Viewer
                     </div>
                     <div class="col-md-3">
                           <input type="radio" name="optradio"> DE
                     </div>
                     <div class="col-md-3">
                           <input type="radio" name="optradio"> Curator
                     </div>
                     <div class="col-md-3">
                           <input type="radio" name="optradio"> Admin
                     </div>
                      <p class="help-block">Please select relevant role for user</p>
               </div>

               <div class="controls" >
                     <button class="btn" style=" margin-top:15px;">Add</button>
                     <button class="btn" style=" margin-top:15px;">Remove</button>
                     <button class="btn" style=" margin-top:15px;">Clear</button>
               </div>

            </form>
         </div>
         <div class="col-md-6">
          <div ng-controller="userGrid" style=" width: 700px; height: 600px;margin-left: 25px;margin-top: 25px;">
               <div id="grid1" ui-grid="gridOptions" class="grid"></div>
             </div>
         </div>
      </div>
   </div>
</div>