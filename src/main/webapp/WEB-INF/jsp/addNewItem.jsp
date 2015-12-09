<div  style="margin:65px 0px 0px 170px;">
   <p>Add New Item</p>
</div>
<div  style="margin:0px 170px 0px 170px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;">
   <div class="row">
      <div  style=" margin-left: 50px; margin-right: 50px;margin-top: 30px;margin-bottom: 30px;">
         <div class="row" ng-controller="newItemController">
            <div class="col-md-4">

                  <div>
                     <p>Item Type</p>
                     <div class="controls">
                        <select ng-model="itemsSelectedType" class="form-control" style="width: 30%;">
                           <option ng-repeat="item in itemType" value="{{item.value}}">{{item.label}}</option>
                        </select>
                     </div>
                     </p>
                  </div>
                  <div>
                     <p>Title</p>
                     <div class="controls">
                        <input type="text" id="itemTitle" name="itemTitle" placeholder="" ng-model="itemTitle"  class="form-control">
                     </div>
                     </p>
                  </div>
                  <div>
                     <p>Donor</p>
                     <div class="controls">
                        <input type="text" id="itemDonor" name="itemDonor" ng-model="itemDonor" placeholder="" class="form-control">
                     </div>
                     </p>
                  </div>
                  <div>
                     <p>Description</p>
                     <div class="controls">
                        <textarea rows="4" cols="50" id="itemDescription" name="itemDescription" ng-model="itemDescription" class="form-control"></textarea>
                     </div>
                     </p>
                  </div>
                  <div>
                     <p>Key Words</p>
                     <div class="controls">
                        <textarea rows="3" cols="50" id="itemKeyWords" name="itemKeyWords" ng-model="itemKeyWords" class="form-control"></textarea>
                     </div>
                     </p>
                  </div>

                  <div>
                     <p>Date Of Origin</p>
                     <div class="controls">
                        <p class="input-group">
                           <input id="23224" show-button-bar="false" type="text"  datepicker-popup="{{format}}" ng-model="itemStartDate" is-open="opened3"  datepicker-options="dateOptions" class="form-control" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
                           <span class="input-group-btn">
                           <button type="button" class="btn btn-default" ng-click="toggleOpenDatePicker($event,'opened3')">  <i class="glyphicon glyphicon-calendar"></i></button>
                           </span>
                        </p>
                     </div>
                     </p>
                  </div>
                  <div>
                    <p>Select Item</p>
                       <div class="controls">
                         <input type="file" class="form-control" file-model="uploadItem"/>
                       </div>
                      </p>
                  </div>
                  <div class="controls">
                     <button class="btn" style=" margin-top:15px;" ng-click="createItem()">Add</button>
                     <button class="btn" style=" margin-top:15px;" ng-click="removeItem()">Remove</button>
                     <button class="btn" style=" margin-top:15px;" ng-click="clearItem()">Clear</button>
                  </div>

            </div>
            <div class="col-md-8">
               <div style=" width: 100%; height:625px;margin-top: 25px;">
                  <div id="grid1" ui-grid="itemGrid" ui-grid-selection class="grid" style="height: 625px;" ></div>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>