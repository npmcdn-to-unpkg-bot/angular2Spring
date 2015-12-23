(function(app) {
  app.Login = Login;
    
  function Login(id,username,password) {
      this.id = id;
      this.username = username;
      this.password = password;
  }
})(window.app || (window.app = {}));