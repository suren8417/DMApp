<div  style="margin:0px 100px 30px 100px; background-color: #ffffff; border:1px solid #e7e7e7;height:100%;">
   <div class="row" ng-controller="newItemController">
   <legend style="width:94%; margin-left:30px;"><h3><b style="color:#1D1F5A;">Add New Item</b></h3></legend>
      <div  style=" margin-left: 50px; margin-right: 50px;margin-top: 30px;margin-bottom: 30px;">
         <div class="row" >
            <div class="col-md-4">
                  <div>
                    <p style="color:green;font-size: 25px;" ng-show="successMessage"><span class="glyphicon glyphicon-ok"></span> Successfully save</p>
                    <p style="color:green;font-size: 25px;" ng-show="deleteMessage"><span class="glyphicon glyphicon-ok"></span> Successfully remove</p>
                  </div>
                  <div>
                     <p>Item Type</p>
                     <div class="controls">
                        <select ng-model="itemsSelectedType" class="form-control" style="width: 30%;">
                           <option ng-repeat="item in itemType" value="{{item.value}}">{{item.label}}</option>
                        </select>
                        </p>
                        <p style="color:red" ng-show="itemTypeRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Please select item type</p>
                     </div>
                     </p>
                  </div>
                  <div>
                     <p>Title</p>
                     <div class="controls">
                        <input type="text" id="itemTitle" name="itemTitle" placeholder="" ng-model="itemTitle"  class="form-control" maxlength="250">
                        </p>
                        <p style="color:red" ng-show="itemTitleRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Please enter item title</p>
                     </div>
                     </p>
                  </div>
                  <div>
                     <p>Donor</p>
                     <div class="controls">
                        <input type="text" id="itemDonor" name="itemDonor" ng-model="itemDonor" placeholder="" class="form-control" maxlength="250">
                        </p>
                        <p style="color:red" ng-show="itemDonorRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Please enter donor</p>
                     </div>
                     </p>
                  </div>
                  <div>
                     <p>Description</p>
                     <div class="controls">
                        <textarea rows="4" cols="50" id="itemDescription" name="itemDescription" ng-model="itemDescription" class="form-control" maxlength="1000"></textarea>
                        </p>
                        <p style="color:red" ng-show="itemDescriptionRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Please enter description</p>
                     </div>
                     </p>
                  </div>
                  <div>
                     <p>Key Words</p>
                     <div class="controls">
                        <textarea rows="3" cols="50" id="itemKeyWords" name="itemKeyWords" ng-model="itemKeyWords" class="form-control" maxlength="1000"></textarea>
                        </p>
                         <p style="color:red" ng-show="itemKeyWordsRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Please enter keywords</p>
                     </div>
                     </p>
                  </div>

                  <div>
                     <p>Date Of Origin</p>
                     <div class="controls">
                        <p class="input-group" style="width: 200px;">
                           <input onkeydown="return false" id="23224" show-button-bar="false" type="text"  datepicker-popup="{{format}}" ng-model="itemStartDate" is-open="opened3" max-date="dtmax" datepicker-options="dateOptions" class="form-control" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" />
                           <span class="input-group-btn">
                           <button type="button" class="btn btn-default" ng-click="toggleOpenDatePicker($event,'opened3')">  <i class="glyphicon glyphicon-calendar"></i></button>
                           </span>
                           </p>
                           <p style="color:red" ng-show="itemOriginDateRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Please enter origin date</p>
                        </p>
                     </div>
                     </p>
                  </div>
                  <div>
                    <p>Select Item</p>
                       <div class="controls">
						   <div style="position:relative;"> 
								<a class='btn btn-primary' href='javascript:;'>
								Choose File...
									<input type="file" file-model="uploadItem" style='position:absolute;z-index:2;top:0;left:0;filter: alpha(opacity=0);-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";opacity:0;background-color:transparent;color:transparent;' name="file_source" size="40"  onchange='$("#upload-file-info").html($(this).val());'>
								</a>
								&nbsp;
								<span class='label label-info' id="upload-file-info"></span>
							</div>
                         </p>
                          <p style="color:red" ng-show="itemFileRequired"><span class="glyphicon glyphicon-exclamation-sign"></span> Please select file for upload</p>
                       </div>
                      </p>
                  </div>
            </div>
            <div class="col-md-8">
                <div style=" width: 100%; height:575px;margin-top: 25px;">
                    <div id="grid2" ui-grid="collectionGrid" ui-grid-selection class="grid" style="height: 575px;" ></div>
                </div>
            </div>
         </div>
          <div class="row">
            <div class="col-md-12">
                <div style=" width: 100%;margin-top: 25px;">
                    <div id="grid1" ui-grid="itemGrid" ui-grid-selection class="grid" style="height: 400px;" ></div>
                </div>
            </div>
          </div>
          <div class="row">
              <div class="controls">
                  <button class="btn" style=" margin-top:15px;width: 100px;" ng-click="createItem()">Save</button>
                  <button class="btn" style=" margin-top:15px;width: 100px;" ng-click="removeItem()">Remove</button>
                  <button class="btn" style=" margin-top:15px;width: 100px;" ng-click="formClear()">Clear</button>
              </div>
          </div>
         <div ng-bind-html="myHTML" style=" margin-top: 20px;">
         </div>

      </div>
   </div>
</div>

<img src="images/Badge.png" width="10%" style="margin-left:100px;"/>