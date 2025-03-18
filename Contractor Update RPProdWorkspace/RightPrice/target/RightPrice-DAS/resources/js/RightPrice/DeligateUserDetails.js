//var app = angular.module('RightPriceApp', ['ngMessages']);
app
		.controller(
				"DeligateUserController",
				[
						'$scope',
						'$location',
						'$anchorScroll',
						'$http',
						'$window',
						'WebServiceFactory',
						'$localStorage',
						'$sessionStorage',
						function($scope, $location, $anchorScroll, $http,
								$window, WebServiceFactory, $localStorage,
								$sessionStorage, $index) {
							var contextPath = "/RightPrice-DAS";
							$scope.deligateUser = [];
							$scope.onSubmit = false;
							$sessionStorage.proxyUserId = null;
							// localStorage.removeItem("proxyArray");

							$scope.getDelicgateUserAccess = function(user) {
								var userss = "";
								  if(user.length>10){
								var base64key = "QmFyMTIzNDVCYXIxMjM0NQ==";
								var parsedBase64Key = CryptoJS.enc.Base64
										.parse(base64key);
								var ive = CryptoJS.enc.Utf8
										.parse('RandomInitVector');
								var encrypted = CryptoJS.AES.decrypt(
										user, parsedBase64Key, {
											iv : ive
										});
								
								var decryptedText = encrypted
										.toString(CryptoJS.enc.Utf8);
								user=decryptedText;
								  }
								var getDelicgateUserAccess = function(response) {
									if (response.data != undefined) {
										$scope.deligateUser = response.data;
										
										$scope.deligateUser.push({
											"id" : 100,
											"proxyADId" : decryptedText,
											"userADId" : decryptedText
										});
									}
								}
								WebServiceFactory.getDelicgateUserAccess(decryptedText)
										.then(getDelicgateUserAccess);
							};

							$scope.validateForm = function(deligateUserForm) {
								$scope.onSubmit = true;
								if (deligateUserForm) {
									$scope.onSubmit = false;
									$scope.assaginDeligateUser();
								}
							};

							$scope.assaginDeligateUser = function() {
								console
										.log("inside the function..............");
								var proxyId = $scope.deligateUserForm.proxyModel;
								console.log("---------------------");
								console.log(proxyId);
								console.log($scope.deligateUser);

								/*
								 * var proxyIdVal =
								 * $.grep($scope.deligateUser, function(deligateUserVal) {
								 *                     return deligateUserVal.id ==
								 * proxyId;                 })[0].proxyADId;
								 */
								for (var i = 0; i < $scope.deligateUser.length; i++) {
									if ($scope.deligateUser[i].id == proxyId) {
										var proxyIdVal = $scope.deligateUser[i].proxyADId;
										$sessionStorage.proxyIdVal = $scope.deligateUser[i].proxyADId;
									}

								}
								console.log("The Proxy id is........... ");
								console.log(proxyIdVal);
								$sessionStorage.proxyUserId = proxyIdVal;
								$scope.getUserRoles(proxyIdVal);
							}

							$scope.getUserRoles = function(proxyId) {
								//alert(proxyId)
								var getUserRoles = function(response) {
									$scope.getUserRoles = response.data;
									$scope.getUserRoles.splice(0, 0, 'USER');
									if ($scope.getUserRoles != undefined) {
										/*
										 * if(localStorage.getItem("proxyArray") !=
										 * null ||
										 * localStorage.getItem("proxyArray") !=
										 * undefined ) {
										 * localStorage.removeItem("proxyArray"); }
										 */
										sessionStorage.setItem("proxyArray",
												$scope.getUserRoles);
										sessionStorage.getItem("proxyArray");
										window.location = "MyDashBoard";
									}
								}
								WebServiceFactory.getUserRoles(proxyId).then(
										getUserRoles);
							}

						} ]);