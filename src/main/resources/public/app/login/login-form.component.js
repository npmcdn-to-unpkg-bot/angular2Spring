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
            this.login(this.model);
        },
        login : function(model) {
            ajaxSend({
                type : 'POST',
                url : '/login',
                data : {
                    'username': model.username,
                    'password': model.password
                },
                success : function(data, textStatus) {
                    console.log(data)
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log(textStatus)
                    console.log(errorThrown)
                }
            });
            // var csrfToken = $.cookie('XSRF-TOKEN');
            // fetch('login', {
            // method: 'POST',
            // headers: {
            // 'Accept': 'application/json',
            // 'Content-Type': 'application/json',
            //                  'X-CSRF-TOKEN' : csrfToken,
            //                },
            //                body: JSON.stringify({
            //                  model
            //                })
            //              })           
        },
    });
})(window.app || (window.app = {}));