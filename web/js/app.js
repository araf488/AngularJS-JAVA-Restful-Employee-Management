// create the module and name it scotchApp
var scotchApp = angular.module('scotchApp', ['ngRoute']);

// configure our routes
scotchApp.config(function ($routeProvider) {
    $routeProvider
            .when('/', {
                templateUrl: 'pages/login.html',
                controller: 'loginController'
            })
            .when('/home', {
                resolve:{
                    "check":function($location, $rootScope){
                        if(!$rootScope.loggedIn){
                            $location.path('/');
                        }
                    }
                },
                templateUrl: 'pages/home.html',
                controller: 'mainController'
            })
            .when('/employee', {
                resolve:{
                    "check":function($location, $rootScope){
                        if(!$rootScope.loggedIn){
                            $location.path('/');
                        }
                    }
                },
                templateUrl: 'pages/employee.html',
                controller: 'employeeController'
            })
            .when('/nemployee', {
                resolve:{
                    "check":function($location, $rootScope){
                        if(!$rootScope.loggedIn){
                            $location.path('/');
                        }
                    }
                },
                templateUrl: 'pages/nemployee.html',
                controller: 'nemployeeController'
            })
            .when('/nemployee', {
                resolve:{
                    "check":function($location, $rootScope){
                        if(!$rootScope.loggedIn){
                            $location.path('/');
                        }
                    }
                },
                templateUrl: 'pages/nemployee.html',
                controller: 'nemployeeController'
            })
            .when('/salary', {
                resolve:{
                    "check":function($location, $rootScope){
                        if(!$rootScope.loggedIn){
                            $location.path('/');
                        }
                    }
                },
                templateUrl: 'pages/salary.html',
                controller: 'salaryController'
            })
            .when('/product', {
                resolve:{
                    "check":function($location, $rootScope){
                        if(!$rootScope.loggedIn){
                            $location.path('/');
                        }
                    }
                },
                templateUrl: 'pages/product.html',
                controller: 'productController'
            })
            .when('/nproduct', {
                resolve:{
                    "check":function($location, $rootScope){
                        if(!$rootScope.loggedIn){
                            $location.path('/');
                        }
                    }
                },
                templateUrl: 'pages/nproduct.html',
                controller: 'nproductController'
            });
});

// create the controller and inject Angular's $scope
scotchApp.controller('loginController', function ($scope, $location, $rootScope) {
    $scope.submit = function(){
        function _clearFormData() {
        $scope.user = "";
        $scope.pass = "";
    };
        if($scope.user === 'admin' && $scope.pass === 'admin'){
            $rootScope.loggedIn = true;
            $location.path('/home');
        }else{
            alert('Error in password or username');
            _clearFormData();
        }
    };
});

scotchApp.controller('mainController', function ($scope) {
    // create a message to display in our view
    $scope.message = 'Everyone come and see how good I look!';
});

scotchApp.controller('salaryController', function ($scope, $http) {
    // create a message to display in our view
    $scope.salarys = [];
    $scope.salaryForm = {
        id: -1,
        ename: "",
        month: "",
        year: "",
        salary: "",
        status: "",
        myVar: true
    };

    _refreshSalaryData();
    //HTTP POST/PUT methods for add/edit country 
    // with the help of id, we are going to find out whether it is put or post operation
    $scope.submitSalary = function () {
        var method = "";
        var url = "";
        if ($scope.salaryForm.id === -1) {
            //Id is absent in form data, it is create new country operation
            method = "POST";
            url = 'rest/salary';
        } else {
            //Id is present in form data, it is edit country operation
            method = "PUT";
            url = 'rest/salary';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.salaryForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    //HTTP DELETE- delete country by Id
    $scope.deleteSalary = function (salary) {
        $http({
            method: 'DELETE',
            url: 'rest/salary/' + salary.id
        }).then(_success, _error);
    };

    // In case of edit, populate form fields and assign form.id with country id
    $scope.editSalary = function (salary) {

        $scope.productForm.ename = salary.ename;
        $scope.productForm.month = salary.month;
        $scope.productForm.year = salary.year;
        $scope.productForm.salary = salary.salary;
        $scope.productForm.status = salary.status;
        $scope.productForm.id = salary.id;
    };
    
    function _refreshSalaryData() {
        $http({
            method: 'GET',
            url: 'rest/salary'
        }).then(function successCallback(response) {
            $scope.salarys = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    $scope.selectSalary = function (salary) {
        console.log(salary);
        $scope.clickedSalary = salary;
    };

    function _success(response) {
        _refreshSalaryData();
        _clearFormData();
    }

    function _error(response) {
        console.log(response.statusText);
    }

    //Clear the form
    function _clearFormData() {
        $scope.salaryForm.id = -1;
        $scope.salaryForm.ename = "";
        $scope.salaryForm.month = "";
        $scope.salaryForm.year = "";
        $scope.salaryForm.salary = "";
        $scope.salaryForm.status = "";
    };
    
    $scope.current = "March";
    $scope.currentY = "2017";
});

scotchApp.controller('employeeController', function ($scope, $http) {
    $scope.clickedEmployee = {};
    $scope.employees = [];
    $scope.currentPage = 1;
    $scope.employeeForm = {
        id: -1,
        ename: "",
        full_name: "",
        email: "",
        mobile: "",
        address: "",
        gender: "",
        salary: "",
        hire_date: "",
        rank: "",
        myVar: true
    };

    //Now load the data from server
    _refreshEmployeeData();
    //HTTP POST/PUT methods for add/edit country 
    // with the help of id, we are going to find out whether it is put or post operation
    $scope.submitEmployee = function () {
        var method = "";
        var url = "";
        if ($scope.employeeForm.id === -1) {
            //Id is absent in form data, it is create new country operation
            method = "POST";
            url = 'rest/employee';
        } else {
            //Id is present in form data, it is edit country operation
            method = "PUT";
            url = 'rest/employee';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.employeeForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    //HTTP DELETE- delete country by Id
    $scope.deleteEmployee = function (employee) {
        $http({
            method: 'DELETE',
            url: 'rest/employee/' + employee.id
        }).then(_success, _error);
    };

    // In case of edit, populate form fields and assign form.id with country id
    $scope.editEmployee = function (employee) {

        $scope.employeeForm.ename = employee.ename;
        $scope.employeeForm.full_name = employee.full_name;
        $scope.employeeForm.email = employee.email;
        $scope.employeeForm.mobile = employee.mobile;
        $scope.employeeForm.address = employee.address;
        $scope.employeeForm.gender = employee.gender;
        $scope.employeeForm.salary = employee.salary;
        $scope.employeeForm.hire_date = employee.hire_date;
        $scope.employeeForm.rank = employee.rank;
        $scope.employeeForm.id = employee.id;
    };

    /* Private Methods */
    //HTTP GET- get all countries collection
    function _refreshEmployeeData() {
        $http({
            method: 'GET',
            url: 'rest/employee'
        }).then(function successCallback(response) {
            $scope.employees = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    $scope.selectEmployee = function (employee) {
        console.log(employee);
        $scope.clickedEmployee = employee;
    };

    function _success(response) {
        _refreshEmployeeData();
        _clearFormData();
    }

    function _error(response) {
        console.log(response.statusText);
    }
    
    $scope.totalItems = $scope.employees.length;  
       $scope.numPerPage = 5;  
       $scope.paginate = function (value) {  
         var begin, end, index;  
         begin = ($scope.currentPage - 1) * $scope.numPerPage;  
         end = begin + $scope.numPerPage;  
         index = $scope.employees.indexOf(value);  
         return (begin <= index && index < end);  
    };

    //Clear the form
    function _clearFormData() {
        $scope.employeeForm.id = -1;
        $scope.employeeForm.ename = "";
        $scope.employeeForm.full_name = "";
        $scope.employeeForm.email = "";
        $scope.employeeForm.mobile = "";
        $scope.employeeForm.address = "";
        $scope.employeeForm.gender = "";
        $scope.employeeForm.salary = "";
        $scope.employeeForm.hire_date = "";
        $scope.employeeForm.rank = "";
    }
    ;
});

scotchApp.controller('nemployeeController', function ($scope, $http) {
    $scope.clickedEmployee = {};
    $scope.employees = [];
    $scope.employeeForm = {
        id: -1,
        ename: "",
        full_name: "",
        email: "",
        mobile: "",
        address: "",
        gender: "",
        salary: "",
        hire_date: "",
        rank: "",
        myVar: true
    };

    //Now load the data from server
    _refreshEmployeeData();
    //HTTP POST/PUT methods for add/edit country 
    // with the help of id, we are going to find out whether it is put or post operation
    $scope.submitEmployee = function () {
        var method = "";
        var url = "";
        if ($scope.employeeForm.id === -1) {
            //Id is absent in form data, it is create new country operation
            method = "POST";
            url = 'rest/employee';
        } else {
            //Id is present in form data, it is edit country operation
            method = "PUT";
            url = 'rest/employee';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.employeeForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    //HTTP DELETE- delete country by Id
    $scope.deleteEmployee = function (employee) {
        $http({
            method: 'DELETE',
            url: 'rest/employee/' + employee.id
        }).then(_success, _error);
    };

    // In case of edit, populate form fields and assign form.id with country id
    $scope.editEmployee = function (employee) {

        $scope.employeeForm.ename = employee.ename;
        $scope.employeeForm.full_name = employee.full_name;
        $scope.employeeForm.email = employee.email;
        $scope.employeeForm.mobile = employee.mobile;
        $scope.employeeForm.address = employee.address;
        $scope.employeeForm.gender = employee.gender;
        $scope.employeeForm.salary = employee.salary;
        $scope.employeeForm.hire_date = employee.hire_date;
        $scope.employeeForm.rank = employee.rank;
        $scope.employeeForm.id = employee.id;
    };

    /* Private Methods */
    //HTTP GET- get all countries collection
    function _refreshEmployeeData() {
        $http({
            method: 'GET',
            url: 'rest/employee'
        }).then(function successCallback(response) {
            $scope.employees = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    $scope.selectEmployee = function (employee) {
        console.log(employee);
        $scope.clickedEmployee = employee;
    };

    function _success(response) {
        _refreshEmployeeData();
        _clearFormData();
    }

    function _error(response) {
        console.log(response.statusText);
    }

    //Clear the form
    function _clearFormData() {
        $scope.employeeForm.id = -1;
        $scope.employeeForm.ename = "";
        $scope.employeeForm.full_name = "";
        $scope.employeeForm.email = "";
        $scope.employeeForm.mobile = "";
        $scope.employeeForm.address = "";
        $scope.employeeForm.gender = "";
        $scope.employeeForm.salary = "";
        $scope.employeeForm.hire_date = "";
        $scope.employeeForm.rank = "";
    }
    ;
});

scotchApp.controller('productController', function ($scope, $http) {
    $scope.clickedProduct = {};
    $scope.products = [];
    $scope.productForm = {
        id: -1,
        pname: "",
        model: "",
        qty: "",
        p_price: "",
        s_price: "",
        p_date: "",
        myVar: true
    };

    //Now load the data from server
    _refreshProductData();
    //HTTP POST/PUT methods for add/edit country 
    // with the help of id, we are going to find out whether it is put or post operation
    $scope.submitProduct = function () {
        var method = "";
        var url = "";
        if ($scope.productForm.id === -1) {
            //Id is absent in form data, it is create new country operation
            method = "POST";
            url = 'rest/product';
        } else {
            //Id is present in form data, it is edit country operation
            method = "PUT";
            url = 'rest/product';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.productForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    //HTTP DELETE- delete country by Id
    $scope.deleteProduct = function (product) {
        $http({
            method: 'DELETE',
            url: 'rest/product/' + product.id
        }).then(_success, _error);
    };

    // In case of edit, populate form fields and assign form.id with country id
    $scope.editProduct = function (product) {

        $scope.productForm.pname = product.pname;
        $scope.productForm.model = product.model;
        $scope.productForm.qty = product.qty;
        $scope.productForm.p_price = product.p_price;
        $scope.productForm.s_price = product.s_price;
        $scope.productForm.p_date = product.p_date;
        $scope.productForm.id = product.id;
    };

    /* Private Methods */
    //HTTP GET- get all countries collection
    function _refreshProductData() {
        $http({
            method: 'GET',
            url: 'rest/product'
        }).then(function successCallback(response) {
            $scope.products = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    $scope.selectProduct = function (product) {
        console.log(product);
        $scope.clickedProduct = product;
    };

    function _success(response) {
        _refreshProductData();
        _clearFormData();
    }

    function _error(response) {
        console.log(response.statusText);
    }

    //Clear the form
    function _clearFormData() {
        $scope.productForm.id = -1;
        $scope.productForm.pname = "";
        $scope.productForm.model = "";
        $scope.productForm.qty = "";
        $scope.productForm.p_price = "";
        $scope.productForm.s_price = "";
        $scope.productForm.p_date = "";
    }
    ;
});

scotchApp.controller('nproductController', function ($scope, $http) {
    $scope.clickedProduct = {};
    $scope.products = [];
    $scope.productForm = {
        id: -1,
        pname: "",
        model: "",
        qty: "",
        p_price: "",
        s_price: "",
        p_date: "",
        myVar: true
    };

    //Now load the data from server
    _refreshProductData();
    //HTTP POST/PUT methods for add/edit country 
    // with the help of id, we are going to find out whether it is put or post operation
    $scope.submitProduct = function () {
        var method = "";
        var url = "";
        if ($scope.productForm.id === -1) {
            //Id is absent in form data, it is create new country operation
            method = "POST";
            url = 'rest/product';
        } else {
            //Id is present in form data, it is edit country operation
            method = "PUT";
            url = 'rest/product';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.productForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    //HTTP DELETE- delete country by Id
    $scope.deleteProduct = function (product) {
        $http({
            method: 'DELETE',
            url: 'rest/product/' + product.id
        }).then(_success, _error);
    };

    // In case of edit, populate form fields and assign form.id with country id
    $scope.editProduct = function (product) {

        $scope.productForm.pname = product.pname;
        $scope.productForm.model = product.model;
        $scope.productForm.qty = product.qty;
        $scope.productForm.p_price = product.p_price;
        $scope.productForm.s_price = product.s_price;
        $scope.productForm.p_date = product.p_date;
        $scope.productForm.id = product.id;
    };

    /* Private Methods */
    //HTTP GET- get all countries collection
    function _refreshProductData() {
        $http({
            method: 'GET',
            url: 'rest/product'
        }).then(function successCallback(response) {
            $scope.products = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    $scope.selectProduct = function (product) {
        console.log(product);
        $scope.clickedProduct = product;
    };

    function _success(response) {
        _refreshProductData();
        _clearFormData();
    }

    function _error(response) {
        console.log(response.statusText);
    }

    //Clear the form
    function _clearFormData() {
        $scope.productForm.id = -1;
        $scope.productForm.pname = "";
        $scope.productForm.model = "";
        $scope.productForm.qty = "";
        $scope.productForm.p_price = "";
        $scope.productForm.s_price = "";
        $scope.productForm.p_date = "";
    }
    ;
});



