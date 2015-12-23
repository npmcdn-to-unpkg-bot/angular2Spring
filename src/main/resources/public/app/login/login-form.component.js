(function(app) {
    app.LoginFormComponent = ng.core.Component({
        selector : 'login-form',
        templateUrl : 'app/login/login-form.component.html'
    }).Class({
        constructor : function() {
            this.model = new app.Login(18, 'user', 'password');
            this.submitted = false;
        },
        onSubmit : function() {
            this.submitted = true;
        },
        logout : function() {
            $http.post('logout', {}).success(function() {
                $rootScope.authenticated = false;
                $location.path("/");
              }).error(function(data) {
                $rootScope.authenticated = false;
              });            
        },
    });
})(window.app || (window.app = {}));