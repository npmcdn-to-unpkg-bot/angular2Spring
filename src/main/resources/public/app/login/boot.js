(function(app) {
  document.addEventListener('DOMContentLoaded', function() {
    ng.platform.browser.bootstrap(app.LoginFormComponent);
  });
})(window.app || (window.app = {}));