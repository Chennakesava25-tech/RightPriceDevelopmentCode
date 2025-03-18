//directive to check greater then other element value 

app.directive('greater', function () {
	console.log("Grteater Then called");
    return {
        require: 'ngModel',
        scope: {
            AvlAmount: "=greater"
        },
        link: function (scope, element, attributes, control) {
        	control.$validators.greater = function(modelValue,viewValue) {
        		var tempViewValue=parseFloat(viewValue);
        		console.log("tempViewValue"+tempViewValue);
        		var tempAvlAmount=parseFloat(scope.AvlAmount);
        		console.log("tempAvlAmount"+tempAvlAmount);
        		
        		console.log(tempViewValue > tempAvlAmount)
        		if((tempViewValue > tempAvlAmount)){
        			//control.$setValidity('ngInvalid',true);
        			return false;
        		}else{
        			return true;
        		}
            };
            scope.$watch("AvlAmount", function() {
        		control.$validate();
            });
        }
    };
});

//directive for form invalid focus
app.directive('form', function () {
    return {
        restrict: 'E',
        link: function (scope, elem) {
            // set up event handler on the form element
            elem.on('submit', function () {
                // find the first invalid element
                var firstInvalid = elem[0].querySelector('.ng-invalid');
                // if we find one, set focus
                if (firstInvalid) {
                    firstInvalid.focus();
                }
            });
        }
    };
});

/*//directive for form invalid focus
app.directive('form', function () {
    return {
        restrict: 'E',
        link: function (scope, elem) {
            // set up event handler on the form element
            elem.on('save', function () {
                // find the first invalid element
                var firstInvalid = elem[0].querySelector('.ng-invalid');
                // if we find one, set focus
                if (firstInvalid) {
                    firstInvalid.focus();
                }
            });
        }
    };
});*/

//directive for form invalid focus
app.directive('save', function () {
    return {
        restrict: 'A',
        scope: {
            form: "=save"
        },
        link: function (scope, elem) {
        	
            // set up event handler on the form element
            elem.on('click', function () {
            	var formId = angular.element(document.getElementById('frmRateCard'));
                // find the first invalid element
                var firstInvalid = formId[0].querySelector('.ng-invalid');
                // if we find one, set focus
                if (firstInvalid) {
                    firstInvalid.focus();
                }
            });
        }
    };
});

//directive for form invalid focus
app.directive('save', function () {
    return {
        restrict: 'A',
        scope: {
            form: "=save"
        },
        link: function (scope, elem) {
        	
            // set up event handler on the form element
            elem.on('click', function () {
            	var formId = angular.element(document.getElementById('frmPolicyDataEntry'));
                // find the first invalid element
                var firstInvalid = formId[0].querySelector('.ng-invalid');
                // if we find one, set focus
                if (firstInvalid) {
                    firstInvalid.focus();
                }
            });
        }
    };
});

//directive for form invalid focus
app.directive('update', function () {
  return {
      restrict: 'A',
      scope: {
          form: "=update"
      },
      link: function (scope, elem) {
      	
          // set up event handler on the form element
          elem.on('click', function () {
          	var formId = angular.element(document.getElementById('frmPolicyDataUpdate'));
              // find the first invalid element
              var firstInvalid = formId[0].querySelector('.ng-invalid');
              // if we find one, set focus
              if (firstInvalid) {
                  firstInvalid.focus();
              }
          });
      }
  };
});

//check file size
/*app.directive('checkFileSize', function() {
	  return {
	    link: function(scope, elem, attr, ctrl) {
	      function bindEvent(element, type, handler) {
	        if (element.addEventListener) {
	          element.addEventListener(type, handler, false);
	        } else {
	          element.attachEvent('on' + type, handler);
	        }
	      }
	      bindEvent(elem[0], 'change', function() {
	        alert('File size:' + this.files[0].size);
	        if(this.files[0].size < 1024 * 1024 * 4){
	        	alert("false");
	        	return false;
	  		}else{
	  			alert("true");
	  			return true;
	  		}
	      });
	    }
	  }
	});*/
//cmp date fields
app.directive('dateCompare', function () {
	console.log("dateCompare Then called");
  return {
      require: 'ngModel',
      scope: {
          fromDate: "=dateCompare"
      },
      link: function (scope, element, attributes, control) {
      	control.$validators.dateCompare = function(modelValue,viewValue) {
      		if(scope.fromDate != undefined && modelValue != undefined && scope.fromDate !="" && modelValue !=""){
      			var startDateArray =  scope.fromDate.split("/");
      			var fromDateArray =  modelValue.split("/");
      			var startDate  = new Date(startDateArray[1] + '/' + startDateArray[0] + '/' + startDateArray[2]);
      			var endDate = new Date(fromDateArray[1] + '/' + fromDateArray[0] + '/' + fromDateArray[2]);
      			if(startDate.getTime() == endDate.getTime()){
      				return true;
      			}
      			if(startDate.getTime() > endDate.getTime()){
      				return false;
      			}else{
      				return true;
      			}
      		}
      		return true;
          };
          scope.$watch("fromDate", function() {
      		control.$validate();
          });
      }
  };
});

//cmp End and Start Dates
app.directive('endDateCompare', function () {
	console.log("Compairing with Start date with end date Then called");
  return {
      require: 'ngModel',
      scope: {
          fromDate: "=endDateCompare"
      },
      link: function (scope, element, attributes, control) {
      	control.$validators.endDateCompare = function(modelValue,viewValue) {
      		if(scope.fromDate != undefined && modelValue != undefined){
      			var startDateArray =  scope.fromDate.split("/");
      			var fromDateArray =  modelValue.split("/");
      			var startDate  = new Date(startDateArray[1] + '/' + startDateArray[0] + '/' + startDateArray[2]);
      			var endDate = new Date(fromDateArray[1] + '/' + fromDateArray[0] + '/' + fromDateArray[2]);
      			if(startDate.getTime() >= endDate.getTime()){
      				return false;
      			}else{
      				return true;
      			}
      		}
      		return true;
          };
          scope.$watch("fromDate", function() {
      		control.$validate();
          });
      }
  };
});

app.directive('checkDuplicateValues', function () {
	console.log("Compairing with Start date with end date Then called");
  return {
      require: 'ngModel',
      scope: {
          fromDate: "=databinding"
      },
      link: function (scope, element, attributes, control) {
      	control.$validators.checkDuplicateValues = function(modelValue,viewValue) {
      		if(scope.fromDate != undefined && modelValue != undefined && scope.fromDate != "" && modelValue != ""){
      			console.log("The Value from the Directive is.........")
      			console.log(scope.fromDate[0]);
      			var startDateArray =  scope.fromDate[0].split("/");
      			var endDateArray = scope.fromDate[1].split("/");
      			var fromDateArray =  modelValue.split("/");
      			var startDate  = new Date(startDateArray[1] + '/' + startDateArray[0] + '/' + startDateArray[2]);
      			var endDate  = new Date(endDateArray[1] + '/' + endDateArray[0] + '/' + endDateArray[2]);
      			var expectedDate = new Date(fromDateArray[1] + '/' + fromDateArray[0] + '/' + fromDateArray[2]);
      			if(expectedDate.getTime() <= startDate.getTime()){
      				return false;
      			} else if(expectedDate.getTime() > endDate.getTime()) {
      				
      				return false;
      			
      			} else {
      				
      				return true;
      			}
      		}
      		return true;
          };
          scope.$watch("fromDate", function() {
      		control.$validate();
          });
      }
  };
});

//Comparing the Expected Date with start and End Dates.
app.directive('checkExpectedDate', function () {
	console.log("Compairing with Start date with end date Then called");
  return {
      require: 'ngModel',
      scope: {
          fromDate: "=databinding"
      },
      link: function (scope, element, attributes, control) {
      	control.$validators.checkExpectedDate = function(modelValue,viewValue) {
      		if(scope.fromDate != undefined && modelValue != undefined && scope.fromDate != "" && modelValue != ""){
      			console.log("The Value from the Directive is.........")
      			console.log(scope.fromDate[0]);
      			var startDateArray =  scope.fromDate[0].split("/");
      			var endDateArray = scope.fromDate[1].split("/");
      			var fromDateArray =  modelValue.split("/");
      			var startDate  = new Date(startDateArray[1] + '/' + startDateArray[0] + '/' + startDateArray[2]);
      			var endDate  = new Date(endDateArray[1] + '/' + endDateArray[0] + '/' + endDateArray[2]);
      			var expectedDate = new Date(fromDateArray[1] + '/' + fromDateArray[0] + '/' + fromDateArray[2]);
      			if(expectedDate.getTime() <= startDate.getTime()){
      				return false;
      			} else if(expectedDate.getTime() > endDate.getTime()) {
      				
      				return false;
      			
      			} else {
      				
      				return true;
      			}
      		}
      		return true;
          };
          scope.$watch("fromDate", function() {
      		control.$validate();
          });
      }
  };
});

//Comparing the Expected end Date with start and End Dates.
app.directive('checkExpectedEndDate', function () {
	console.log("Compairing with Start date with end date Then called");
  return {
      require: 'ngModel',
      scope: {
          fromDate: "=databinding"
      },
      link: function (scope, element, attributes, control) {
      	control.$validators.checkExpectedEndDate = function(modelValue,viewValue) {
      		if(scope.fromDate != undefined && modelValue != undefined && scope.fromDate != "" && modelValue != ""){
      			console.log("The Value from the Directive is.........")
      			console.log(scope.fromDate[0]);
      			var startDateArray =  scope.fromDate[0].split("/");
      			var endDateArray = scope.fromDate[1].split("/");
      			var actionarray=scope.fromDate[2];
      			var fromDateArray =  modelValue.split("/");
      			var startDate  = new Date(startDateArray[1] + '/' + startDateArray[0] + '/' + startDateArray[2]);
      			var endDate  = new Date(endDateArray[1] + '/' + endDateArray[0] + '/' + endDateArray[2]);
      			var expectedendDate = new Date(fromDateArray[1] + '/' + fromDateArray[0] + '/' + fromDateArray[2]);
      			if(actionarray==0){
      					if(expectedendDate.getTime() <= startDate.getTime()){
      				return false;
      			} else if(expectedendDate.getTime() <= endDate.getTime()) {
      				
      				return false;
      			
      			} else {
      				
      				return true;
      			}
      			}
      			else if(actionarray==1){
      				return true;
      			}
      		
      		}
      		return true;
          };
          scope.$watch("fromDate", function() {
      		control.$validate();
          });
      }
  };
});

//Comparing the Expected end Date with start and End Dates version 2 for masters. 
app.directive('checkExpectedEndDateMasterSalery', function () {
	console.log("Compairing with Start date with end date Then called");
  return {
      require: 'ngModel',
      scope: {
          fromDate: "=databinding"
      },
      link: function (scope, element, attributes, control) {
      	control.$validators.checkExpectedEndDate = function(modelValue,viewValue) {
      		if(scope.fromDate != undefined && modelValue != undefined && scope.fromDate != "" && modelValue != ""){
      			console.log("The Value from the Directive is.........")
      			console.log(scope.fromDate[0]);
      			var startDateArray =  scope.fromDate[0].split("/");
      			var endDateArray = scope.fromDate[1].split("/");
      			var actionarray=scope.fromDate[2];
      			var fromDateArray =  modelValue.split("/");
      			var startDate  = new Date(startDateArray[1] + '/' + startDateArray[0] + '/' + startDateArray[2]);
      			var endDate  = new Date(endDateArray[1] + '/' + endDateArray[0] + '/' + endDateArray[2]);
      			var expectedendDate = new Date(fromDateArray[1] + '/' + fromDateArray[0] + '/' + fromDateArray[2]);
      			if(actionarray==0){
      					if(expectedendDate.getTime() <= startDate.getTime()){
      				return false;
      			} else {
      				
      				return true;
      			}
      			}
      			else if(actionarray==1){
      				return true;
      			}
      		
      		}
      		return true;
          };
          scope.$watch("fromDate", function() {
      		control.$validate();
          });
      }
  };
});


//directive to check greater then zero //positive number
app.directive('positive', function () {
	//console.log("positive called");
	  return {
	      restrict: 'A',
	      require: 'ngModel',
	      link: function (scope, element, attrs, ctrl) {
	      	ctrl.$validators.positive = function(modelValue,viewValue) {
      		if(modelValue == undefined){
      			return true;
      		}
      		if((viewValue<0)){
      			return false;
      		}else{
      			return true;
      		}
          };
	      
	}
};
});

app.directive('validFile', function () {
	console.log("valid File Type called");
	return {
	    require: 'ngModel',
	    link: function (scope, elem, attrs, ngModel) {
	        var validFormats = ['msg','zip','pdf','xlsx','xls','txt','jpeg','jpg','doc','docx'];
	        elem.bind('change', function () {
	            validFileType(false);
	            scope.$apply(function () {
	                ngModel.$render();
	            });
	        });
	        ngModel.$render = function () {
	            ngModel.$setViewValue(elem.val());
	        };
	        function validFileType(boolean) {
	            ngModel.$setValidity('extension', boolean);
	        }
	        ngModel.$parsers.push(function(value) {
	            var ext = value.substr(value.lastIndexOf('.')+1);
	            if(ext=='') {
	            	validFileType(true);
	            	return true;
	            	}
	            if(validFormats.indexOf(ext) == -1){
	                return value;
	                scope.$apply();
	            }
	            validFileType(true);
	            return value;
	        });
	        validFileType(true);
	        return true;
	    }
	  };
	});

app.directive('checkFileSize', function() {
	console.log("valid File Size called");
	  return {
		  require: 'ngModel',
		  scope: {
			  checkFileSize: "="
	       },
	    link: function(scope, element, attributes,ngModel) {
	      function bindEvent(element, type, handler) {
	        if (element.addEventListener) {
	          element.addEventListener(type, handler, false);
	        } else {
	          element.attachEvent('on' + type, handler);
	        }
	      }
	      function validSize(boolean) {
	            ngModel.$setValidity('checkfilesize', boolean);
	      }
	      
	      bindEvent(element[0], 'change', function() {
	    	  if(this.files[0] != undefined){
	    		var size = parseInt(this.files[0].size );
	    		
		        if(size>1024 * 1024 * 15){
		        	console.log("inside if");
		        	validSize(false);
		        }else{
		        	console.log("inside else");
		        	validSize(true);
		        	//for over come the lezzy binding use below 
		        	scope.$apply();
		        }
	    	  }
	    	  else{
	    		  validSize(true);
	    	  }
	    	  
	      });
	  
	    }
	  }; 
	  
	});

//directive to check less than 100 percentage 
app.directive('checklthundredpercente', function () {
	//console.log("positive called");
	  return {
	      restrict: 'A',
	      require: 'ngModel',
	      link: function (scope, element, attrs, ctrl) {
	      	ctrl.$validators.checklthundredpercente = function(modelValue,viewValue) {
        		if(modelValue == undefined){
        			return true;
        		}
        		if((viewValue <= 100)){
        			return true;
        		}else{
        			return false;
        		}
            }; 
	}
 };
});


app.directive('checkltransitionmonth', function () {
	//console.log("positive called");
	  return {
	      restrict: 'A',
	      require: 'ngModel',
	      link: function (scope, element, attrs, ctrl) {
	      	ctrl.$validators.checkltransitionmonth = function(modelValue,viewValue) {
        		if(modelValue == undefined){
        			return true;
        		}
        		if((viewValue <= 12)){
        			return true;
        		}else{
        			return false;
        		}
            }; 
	}
 };
});

//directive to check equal to 100 percentage 
app.directive('checkhundredpercente', function () {
	//console.log("positive called");
	return {
		restrict: 'A',
		require: 'ngModel',
		link: function (scope, element, attrs, ctrl) {
			ctrl.$validators.checkhundredpercente = function(modelValue,viewValue) {
				if(modelValue == undefined){
					return true;
				}
				if((viewValue == 100) || (viewValue == 0)){
					return true;
				}else{
					return false;
				}
			}; 
		}
	};
});


//directive to check equal to 100 percentage 
app.directive('checkoffshorehundredpercente', function () {
	//console.log("positive called");
	return {
		restrict: 'A',
		require: 'ngModel',
		link: function (scope, element, attrs, ctrl) {
			ctrl.$validators.checkoffshorehundredpercente = function(modelValue,viewValue) {
				if(modelValue == undefined){
					return true;
				}
				if((viewValue == 100) || (viewValue == 0)){
					return true;
				}else{
					return false;
				}
			}; 
		}
	};
});

//directive to check the total utilization value matches the onsite percentage
app.directive('checktotalutilizationvalue', function () {
	//console.log("positive called");
	  return {
	      require: 'ngModel',
	      scope: {
	         onsitePerc:"=databinding"
	      },
	      link: function (scope, element, attributes, control) {
	      	control.$validators.checktotalutilizationvalue = function(modelValue,viewValue) {
	      		var onsitePercentage = scope.onsitePerc[0];
	      		if(modelValue == undefined) {
	      			return false;
	      		}
	      		if(modelValue == onsitePercentage) {
	      			return true;
	      		} else if(modelValue < onsitePercentage) {
	      			return false;
	      		} else {
	      			return false;
	      		}
	          };
	          scope.$watch("modelValue", function() {
	      		control.$validate();
	          });
	      }
	  };
});

//directive to check equal to 100 percentage 
app.directive('checkMonthValue', function () {
	//console.log("positive called");
	console.log("Compairing the value with the max month value");
	  return {
	      require: 'ngModel',
	      scope: {
	         maxMonth:"=databinding"
	      },
	      link: function (scope, element, attributes, control) {
	      	control.$validators.checkMonthValue = function(modelValue,viewValue) {
	      		console.log("The Model Val is....... "+ modelValue);
	      		console.log("The max Month Val is........... "+ scope.maxMonth[0]);
	      		var maxMonth = scope.maxMonth[0];
	      		if(modelValue == undefined  || modelValue == "N/A") {
	      			return true;
	      		}
	      		if(modelValue == "M"  || modelValue == "Y") {
	      			return true;
	      		}
	      		if(modelValue <= maxMonth) {
	      			return true;
	      		} else {
	      			return false
	      		}
	      		
	          };
	          scope.$watch("modelValue", function() {
	      		control.$validate();
	          });
	      }
	  };
});


app.directive('lowerthan', function () {
	console.log("lower Then called");
    return {
        require: 'ngModel',
        scope: {
        	minimumexperience: "=lowerthan"
        },
        link: function (scope, element, attributes, control) {
        	control.$validators.lowerthan = function(modelValue,viewValue) {
        		var minimumExpValue=parseFloat(viewValue);
        		var maximumExp=parseFloat(scope.minimumexperience);
        		if((minimumExpValue < maximumExp)){
        			//control.$setValidity('ngInvalid',true);
        			return false;
        		}else{
        			return true;
        		}
            };
            scope.$watch("minimumexperience", function() {
        		control.$validate();
            });
        }
    };
});

app.directive('previousCompare', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attributes, control) {
        	control.$validators.previousCompare = function(modelValue,viewValue) {
        		if(viewValue != undefined && modelValue != undefined){
          			var startDateArray =  viewValue.split("/");
          			var startDate  = new Date(startDateArray[1] + '/' + startDateArray[0] + '/' + startDateArray[2]);
          			var endDate = new Date();
          			endDate.setHours(0, 0, 0, 0);
          			
          			if(startDate.getTime() < endDate.getTime()){
          				return false;
          			}else{
          				return true;
          			}
          		}
          		return true;          		
            };
            scope.$watch("viewValue", function() {
        		control.$validate();
            });
        }
    };
});

//directive to check less than 23.75 onsite hrs per day 
app.directive('maxonsitehrsperday', function () {
	//console.log("positive called");
	  return {
	      restrict: 'A',
	      require: 'ngModel',
	      link: function (scope, element, attrs, ctrl) {
	      	ctrl.$validators.checklthundredpercente = function(modelValue,viewValue) {
        		if(modelValue == undefined){
        			return true;
        		}
        		if((viewValue <= 23.75)){
        			return true;
        		}else{
        			return false;
        		}
            }; 
	}
 };
});


//validFile
app.directive('validFileRateCard', function () {
       return {
           require: 'ngModel',
           link: function (scope, elem, attrs, ngModel) {
               var validFormats = ['xls','xlsx','xlsm','xltx','xltm'];
               elem.bind('change', function () {
                   validFileType(false);
                   scope.$apply(function () {
                       ngModel.$render();
                   });
               });
               ngModel.$render = function () {
                   ngModel.$setViewValue(elem.val());
               };
               function validFileType(boolean) {
                   ngModel.$setValidity('extension', boolean);
               }
               ngModel.$parsers.push(function(value) {
                   var ext = value.substr(value.lastIndexOf('.')+1);
                   if(ext=='') {
                     validFileType(true);
                     return true;
                     }
                   if(validFormats.indexOf(ext) == -1){
                       return value;
                       scope.$apply();
                   }
                   validFileType(true);
                   return value;
               });
               validFileType(true);
               return true;
          }
  };
});

app.directive('totalcheck', function () {
	console.log("totalcheck called");
    return {
        require: 'ngModel',
        link: function (scope, element, attributes, control) {
        	control.$validators.totalcheck = function(modelValue,viewValue) {
        		if(viewValue == undefined || viewValue == ""){
          			return true;
          		}
        		if(viewValue != undefined || viewValue != ""){
        			
        			if(viewValue > 100){
        				return false;
        				}
            		else {
            			return true;
            			}
        		}
        		else {return true;}
        		
            };
            scope.$watch("viewValue", function() {
        		control.$validate();
            });
            scope.$watch("modelValue", function() {
        		control.$validate();
            });
        }
    };
});


app.directive('validFileExcel', function () {
	console.log("valid File Type called");
	return {
	    require: 'ngModel',
	    link: function (scope, elem, attrs, ngModel) {
	        var validFormats = ['xlsx','xls'];
	        elem.bind('change', function () {
	            validFileType(false);
	            scope.$apply(function () {
	                ngModel.$render();
	            });
	        });
	        ngModel.$render = function () {
	            ngModel.$setViewValue(elem.val());
	        };
	        function validFileType(boolean) {
	            ngModel.$setValidity('extension', boolean);
	        }
	        ngModel.$parsers.push(function(value) {
	            var ext = value.substr(value.lastIndexOf('.')+1);
	            if(ext=='') {
	            	validFileType(true);
	            	return true;
	            	}
	            if(validFormats.indexOf(ext) == -1){
	                return value;
	                scope.$apply();
	            }
	            validFileType(true);
	            return value;
	        });
	        validFileType(true);
	        return true;
	    }
	  };
	});

app.directive('utilization', function () {
    return {
        require: 'ngModel',
        scope: {
        	isChkbxSel: "=utilization"
        },
        link: function (scope, element, attributes, control) {
        	control.$validators.utilization = function(modelValue,viewValue) {
        		var amount=parseFloat(viewValue);
        		if( scope.isChkbxSel && (amount < 0)){
        			return false;
        		} else if (scope.isChkbxSel && (amount > 100)) {
        			return false;
        		}else{
        			return true;
        		}
            };
            scope.$watch("isChkbxSel", function() {
        		control.$validate();
            });
        }
    };
});

app.directive("decimals", function ($filter) {
    return {
        restrict: "A", // Only usable as an attribute of another HTML element
        require: "?ngModel",
        scope: {
            decimals: "@",
            decimalPoint: "@"
        },
        link: function (scope, element, attr, ngModel) {
            var decimalCount = parseInt(scope.decimals) || 2;
            var decimalPoint = scope.decimalPoint || ".";

            // Run when the model is first rendered and when the model is changed from code
            ngModel.$render = function() {
                if (ngModel.$modelValue != null && ngModel.$modelValue >= 0) {
                    if (typeof decimalCount === "number") {
                        element.val(ngModel.$modelValue.toFixed(decimalCount).toString().replace(".", "."));
                    } else {
                        element.val(ngModel.$modelValue.toString().replace(".", "."));
                    }
                }
            }

            // Run when the view value changes - after each keypress
            // The returned value is then written to the model
            ngModel.$parsers.unshift(function(newValue) {
                if (typeof decimalCount === "number") {
                    var floatValue = parseFloat(newValue.replace(".", "."));
                    if (decimalCount === 0) {
                        return parseInt(floatValue);
                    }
                    return parseFloat(floatValue.toFixed(decimalCount));
                }
                
                return parseFloat(newValue.replace(".", "."));
            });

            // Formats the displayed value when the input field loses focus
            element.on("change", function(e) {
                var floatValue = parseFloat(element.val().replace(".", "."));
                if (!isNaN(floatValue) && typeof decimalCount === "number") {
                    if (decimalCount === 0) {
                        element.val(parseInt(floatValue));
                    } else {
                        var strValue = floatValue.toFixed(decimalCount);
                        element.val(strValue.replace(".", decimalPoint));
                    }
                }
            });
        }
    }
});

app.filter('headerFilter', function () {
	  return function (input) {
	      return input.replace(/,/g, ' - ');
	  };
	});

app.directive('checkDealStartDate', function () {
	console.log("Compairing with Start date with end date Then called");
	  return {
	      require: 'ngModel',
	      scope: {
	          fromDate: "=databinding"
	      },
	      link: function (scope, element, attributes, control) {
	      	control.$validators.checkDealStartDate = function(modelValue,viewValue) {
	      		if(scope.fromDate != undefined && modelValue != undefined && scope.fromDate != "" && modelValue != ""){
	      			console.log("The Value from the Directive is.........")
	      			console.log(scope.fromDate[0]);
	      			var startDateArray =  scope.fromDate[0].split("/");
	      			var fromDateArray =  modelValue.split("/");
	      			var startDate  = new Date(startDateArray[1] + '/' + startDateArray[0] + '/' + startDateArray[2]);
	      			var expectedendDate = new Date(fromDateArray[1] + '/' + fromDateArray[0] + '/' + fromDateArray[2]);
	      					if(expectedendDate.getTime() < startDate.getTime()){
	      				return false;
	      				} else {
	      				return true;
	      			}
	      		
	      		}
	      		return true;
	          };
	          scope.$watch("fromDate", function() {
	      		control.$validate();
	          });
	      }
	  };
});

app.directive('checkDealEndDate', function () {
	console.log("Compairing with Start date with end date Then called");
	  return {
	      require: 'ngModel',
	      scope: {
	          fromDate: "=databinding"
	      },
	      link: function (scope, element, attributes, control) {
	      	control.$validators.checkDealEndDate = function(modelValue,viewValue) {
	      		if(scope.fromDate != undefined && modelValue != undefined && scope.fromDate != "" && modelValue != ""){
	      			console.log("The Value from the Directive is.........")
	      			console.log(scope.fromDate[0]);
	      			var endDateArray =  scope.fromDate[0].split("/");
	      			var fromDateArray =  modelValue.split("/");
	      			var endDate  = new Date(endDateArray[1] + '/' + endDateArray[0] + '/' + endDateArray[2]);
	      			var expectedendDate = new Date(fromDateArray[1] + '/' + fromDateArray[0] + '/' + fromDateArray[2]);
	      					if(expectedendDate.getTime() > endDate.getTime()){
	      				return false;
	      				} else {
	      				return true;
	      			}
	      		
	      		}
	      		return true;
	          };
	          scope.$watch("fromDate", function() {
	      		control.$validate();
	          });
	      }
	  };
});

app.filter('underscoreless', function () {
	  return function (input) {
	      return input.replace(/_/g, ' ');
	  };
	});
