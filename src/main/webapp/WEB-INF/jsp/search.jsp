 <div ng-show="searchUI"  style="margin:65px 0px 0px 170px;" >
   <p>Search</p>
    </div>
       <div ng-show="searchUI" class="jumbotron"  style="margin:0px 170px 0px 170px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;" >
            <div class="row">
               <div class="col-md-2"></div>
               <div class="col-md-8">
                  <div class="input-group">
                     <input type="text" class="form-control" placeholder="Search for...">
                     <span class="input-group-btn">
                     <button class="btn btn-default" type="button">search <span class="glyphicon glyphicon-search"></span></button>
                     </span>
                  </div>
               </div>
               <div class="col-md-2"></div>
            </div>
            <div class="row">
               <div class="col-md-2"></div>
               <div class="col-md-1"></div>
               <div class="col-md-1">
                  <div class="checkbox">
                     <label><input type="checkbox"> Image</label>
                  </div>
               </div>
               <div class="col-md-1">
                  <div class="checkbox">
                     <label><input type="checkbox"> Document</label>
                  </div>
               </div>
               <div class="col-md-1">
                  <div class="checkbox">
                     <label><input type="checkbox"> Audio</label>
                  </div>
               </div>
               <div class="col-md-1">
                  <div class="checkbox">
                     <label><input type="checkbox"> Video </label>
                  </div>
               </div>
               <div class="col-md-1">
                  <div class="checkbox">
                     <label><input type="checkbox"> Collection</label>
                  </div>
               </div>
               <div class="col-md-2"></div>
            </div>
              <div class="row">
              <div class="col-md-2"></div>
               <div class="col-md-1"></div>
                  <div class="col-md-2">
                      <p class="input-group">
                        <input id="2322" type="text" class="form-control" datepicker-popup="{{format}}" ng-model="dt" is-open="opened"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
                        <span class="input-group-btn">
                          <button type="button" class="btn btn-default" ng-click="toggleOpenDatePicker($event,'opened')"> From <i class="glyphicon glyphicon-calendar"></i></button>
                        </span>
                      </p>
                  </div>
                  <div class="col-md-1"></div>
                  <div class="col-md-2">
                      <p class="input-group">
                       <input id="23dd22" type="text" class="form-control" datepicker-popup="{{format}}" ng-model="dt1" is-open="opened1"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
                        <span class="input-group-btn">
                          <button type="button" class="btn btn-default" ng-click="toggleOpenDatePicker($event,'opened1')"> To <i class="glyphicon glyphicon-calendar"></i></button>
                        </span>
                      </p>
                  </div>
         <div class="col-md-2"></div>
    </div>
</div>