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
        this.properties().add({ name: 'backgroundColor', value: '#000000', typeSpec: 'gx:Color' });
            this.properties().add('iconStyle', 'regular');


  };

  BIconWidget.prototype = Object.create(Widget.prototype);
  BIconWidget.prototype.constructor = BIconWidget;

  BIconWidget.prototype.doInitialize = function (dom) {
    var that = this;
    dom.html('<div class="font-awesome-icon-container" style="width: 100%; height: 100%; overflow: hidden;"></div>');
    var style = that.properties().get('iconStyle').value;

    this.jq().load('/module/ntools/rc/icons/'+style+'/'+that.properties().get('icon').value+'.svg', function(response){
      that.jq().find("svg").css('height','100%');
      that.jq().find("svg").css('width','100%');
    });
       dom.css('background-color', that.properties().get('backgroundColor').value);

   dom.css("overflow", "hidden");
  };

  BIconWidget.prototype.doLoad = function(component) {

    var that = this;
    that.getSubscriber().attach("changed", function(property) {
      if (property.getName() === "out") {
        updateColor(that, component, property);
      }
    });

    updateColor(that, component, null);
  };

   function updateColor(widget, component, changedProperty)
    {
      var jq = widget.jq(),
      fill = jq.find("svg"),
      changedPropertyValue,
      enumValue;

      if (changedProperty) {
          try {
            changedPropertyValue = component.get(changedProperty)
          } catch (err) {
          }
      }
      try
      {
        if (changedProperty && component && changedPropertyValue)
        {
          if (typeof changedPropertyValue.getTag === "function")
          {
            enumValue = changedPropertyValue.getTag();
          }
          else if (typeof changedPropertyValue.getValue === "function")
          {
            enumValue = changedPropertyValue.getValue().getTag();
          }
          else
          {
            return;
          }
        }
        else if (component)
        {
          if (typeof component.getTag === "function")
          {
            enumValue = component.getTag();
          }
          else if (typeof component.getOut === "function")
          {
            enumValue = component.getOut().getValue().getTag();
          }
          else if (typeof component.getValue === "function")
          {
            enumValue = component.getValue().getTag();
          }
        }
      }
      catch(err)
      {
        throw err;
      }

      switch (enumValue) {


        case ("AllGood"):
                fill.attr("fill", "#08bf08");
            break;

        case ("CommDown"):
                fill.attr("fill", "#fac600");
            break;

        case ("InAlarm"):
               fill.attr("fill", "#cf1624");
            break;

        case ("Disabled"):
                fill.attr("fill", "#808080");
            break;
        case ("Overridden"):
                fill.attr("fill", "#bfaddd");
            break;

        default:
            fill.attr("fill", "#101010");
      }
    }
  return BIconWidget;
});