define(['bajaux/mixin/subscriberMixIn',
        'bajaux/Widget',
        'jquery'], function (
        subscriberMixIn,
        Widget,
        $) {

  'use strict';

  var BIconWidget = function () {
    Widget.apply(this, arguments);
    subscriberMixIn(this);
    this.properties().add('icon', 'sbs');
    this.properties().add({ name: 'color', value: '#f7941d', typeSpec: 'gx:Color' });
    this.properties().add({ name: 'backgroundColor', value: '#ffffff', typeSpec: 'gx:Color' });
    this.properties().add('iconStyle', 'regular');
  };

  BIconWidget.prototype = Object.create(Widget.prototype);
  BIconWidget.prototype.constructor = BIconWidget;

  BIconWidget.prototype.doInitialize = function (dom) {
    var that = this;
    dom.html('<div class="font-awesome-icon-container" style="width: 100%; height: 100%; overflow: hidden;"></div>');
    var style = that.properties().get('iconStyle').value;

    this.jq().load('/module/ntools/rc/icons/'+style+'/'+that.properties().get('icon').value+'.svg', function(response){
      that.jq().find("svg").css('fill',that.properties().get('color').value);
      that.jq().find("svg").css('height','100%');
      that.jq().find("svg").css('width','100%');
    });
    dom.css('background-color', that.properties().get('backgroundColor').value);
    dom.css("overflow", "hidden");

  };




  return BIconWidget;
});