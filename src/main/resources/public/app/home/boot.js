(function(app) {
  document.addEventListener('DOMContentLoaded', function() {
    ng.platform.browser.bootstrap(app.HomeComponent);
  });
})(window.app || (window.app = {}));