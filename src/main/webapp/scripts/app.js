'use strict'

var app = angular.module('Pocket-Calculator', ['ngResource']);

app.factory("AddService", function($resource) {
  return $resource("/api/calculator/add/:first/:second");
});

app.factory("SubtractService", function($resource) {
  return $resource("/api/calculator/subtract/:first/:second");
});

app.factory("MultiplyService", function($resource) {
  return $resource("/api/calculator/multiply/:first/:second");
});

app.factory("DivideService", function($resource) {
  return $resource("/api/calculator/divide/:first/:second");
});

app.controller('appCtrl', function($scope, AddService, SubtractService, MultiplyService, DivideService) {
	$scope.pad = ['7', '8', '9', '/', '*', '4', '5', '6', '-', '+', '1', '2', '3', '.', '?', 'clear', '0', '='];
	$scope.formula = ['0'];
	$scope.firstNumber = [];
	$scope.secondNumber = [];
	$scope.result = 0;
	$scope.firstValue = 0;
	$scope.secondValue = 0;
	$scope.typingFirstNumber = true;
	$scope.continueCalculation = false;
	$scope.service;

	$scope.add = function(item) {
        if (item == 'clear') {
            $scope.reset();
        } else if(item == '?') {
            alert("Pocket Calculator");
        } else if(item == '-' || item == '+' || item == '/' || item == '*') {
            if($scope.typingFirstNumber) {
                $scope.firstValue = eval($scope.firstNumber.join(''));
                $scope.typingFirstNumber = false;
            }
            if($scope.continueCalculation) {
                $scope.continueCalculation = false;
                $scope.formula = [];
                $scope.formula.push($scope.result);
            }
            (! /[0-9]/.test(item) && ! /[0-9]/.test($scope.formula.slice(-1)[0])) ? $scope.remove() : null;
            ($scope.formula == '0' && /[0-9]/.test(item)) ? $scope.formula = [item] : $scope.formula.push(item);

            $scope.selectService(item);
        } else if(item == '=') {
            if($scope.continueCalculation) {
                return;
            }
            $scope.secondValue = eval($scope.secondNumber.join(''));
            $scope.service.get({ first: $scope.firstValue, second: $scope.secondValue }, function(data) {
                $scope.result = data.value;
                $scope.onCalculation(data);
            }, function(error) {
                $scope.onException(error.data);
            });
            $scope.continueCalculation = true;
            $scope.typingFirstNumber = true;
        } else {
            if($scope.continueCalculation) {
                $scope.reset();
                $scope.continueCalculation = false;
            }

            if($scope.typingFirstNumber) {
                if($scope.firstNumber.length >= 10) {
                    console.log("Max 10 digit");
                    return;
                }
                $scope.firstNumber.push(item);
            }
            else {
                if($scope.secondNumber.length >= 10) {
                    console.log("Max 10 digit");
                    return;
                }
                $scope.secondNumber.push(item);
            }

            (! /[0-9]/.test(item) && ! /[0-9]/.test($scope.formula.slice(-1)[0])) ? $scope.remove() : null;
            ($scope.formula == '0' && /[0-9]/.test(item)) ? $scope.formula = [item] : $scope.formula.push(item);
            $scope.result = 0;
        }
	};

	$scope.reset = function() {
		$scope.formula = ['0'];
		$scope.firstNumber = [];
		$scope.secondNumber = [];
		$scope.result = 0;
	};

    $scope.onCalculation = function(data) {
        $scope.firstNumber = [];
        $scope.firstNumber.push(data.value);
        $scope.secondNumber = [];
    };

    $scope.onException = function(error) {
        alert(error.message);
    }

    $scope.remove = function() {
        $scope.formula.pop();
        ($scope.formula.length == 0) ? $scope.reset() : null;
    }

    $scope.selectService = function(operation) {
        if(operation == "+") {
            $scope.service = AddService;
        }
        else if(operation == "-") {
            $scope.service = SubtractService;
        }
        else if(operation == "*") {
            $scope.service = MultiplyService;
        }
        else if(operation == "/") {
            $scope.service = DivideService;
        }
    }
});

app.directive('onTouch', [ '$parse', function($parse) {
  return {
        restrict: 'A',
        link: function(scope, elm, attrs) {
            var ontouchFn = $parse(attrs.onTouch);
            var params = Array.prototype.slice.call(arguments);
            params = params.splice(1);
            ( 'ontouchstart' in window ) ?
            elm.bind('touchstart', function(evt) {
                scope.$apply(function() {
                    ontouchFn(scope, { $event: evt, $params: params });
                });
            }) :
            elm.bind('click', function(evt){
                    scope.$apply(function() {
                        ontouchFn(scope, { $event: evt, $params: params });
                    });
            });
        }
    };
}]);