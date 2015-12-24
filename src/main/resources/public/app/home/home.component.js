(function(app) {
  app.HomeComponent = ng.core
    .Component({
      selector: 'home',
      templateUrl : 'app/home/home.component.html'
    })
    .Class({
      constructor: function() {
          
      },
      logout : function() {
      },

    });
})(window.app || (window.app = {}));