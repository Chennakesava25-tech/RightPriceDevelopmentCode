/**
 * http://usejsdoc.org/
 */

var app = angular.module("RightPriceApp", ['ui.filters','ngMessages','ngStorage']).factory('myInterceptor',
function ($q,$rootScope) {
	//alert("Loder called");
	var interceptor = {
	        'request': function (config) {
	        //$('#loadingWidget').show();
	         $rootScope.loading = 1;
	        // Successful request method
	            return config; // or $q.when(config);
	        },
	        'response': function (response) {
	         $rootScope.loading = 0;
	         //$('#loadingWidget').hide();
	        // successful response
	            return response; // or $q.when(config);
	        },
	        'requestError': function (rejection) {
	            // an error happened on the request
	            // if we can recover from the error
	            // we can return a new request
	            // or promise
	            return response; // or new promise
	                // Otherwise, we can reject the next
	                // by returning a rejection
	                // return $q.reject(rejection);
	        },
	        'responseError': function (rejection) {
	            // an error happened on the request
	            // if we can recover from the error
	            // we can return a new response
	            // or promise
	            return rejection; // or new promise
	                // Otherwise, we can reject the next
	                // by returning a rejection
	                // return $q.reject(rejection);
	        }
	    };

    return interceptor;
}).config(function($httpProvider) {
   $httpProvider.interceptors.push('myInterceptor');
});