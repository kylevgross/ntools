/*
  This widget demonstrates how to create powerful visualizations using open web technology.
  Please note, this isn't a complete example nor is this part of Tridium's commercial 
  offering to be used onsite. It's merely a code example to show what can be done.

  This gauge uses a really powerful open source visualization library called 'd3'...

  http://d3js.org/

  There are a plethora of great web technologies out there. We look forward to seeing how
  you incorporate them into Niagara 4 :¬)))).
*/
define(['baja!',
        'baja!control:ControlPoint,baja:StatusNumeric',
        'underscore', // Underscore is a great JavaScript utility library: http://underscorejs.org/
        'bajaux/Widget',
        'bajaux/mixin/subscriberMixIn',
        'd3',
        'css!nmodule/docDeveloper/rc/CircularGaugeStyle'], function (
        baja,  
        types,
        _,
        Widget,
        subscriberMixIn,
        d3) {

  'use strict';

  var options = {
    left: 20,
    top: 10
  };

  var CircularGauge = function () {
    var that = this;

    Widget.apply(that, arguments);
    subscriberMixIn(that);

    that.properties().addAll([
    {
      name: "backgroundColor",
      value: "steelblue",
      typeSpec: "gx:Color"
    },
    {
      name: "autoScale",
      value: true
    },
    {
      name: "autoScaleStep",
      value: 10
    },
    {
      name: "max",
      value: 100
    },
    {
      name: "min",
      value: 0
    } 
    ]);
  };

  CircularGauge.prototype = Object.create(Widget.prototype);
  CircularGauge.prototype.constructor = CircularGauge;

  function makeModel(widget) {
    var value = widget.value(),
        props = widget.properties(),
        min = widget.$min = widget.$min === undefined ? props.getValue("min") : widget.$min,
        max = widget.$max = widget.$max === undefined ? props.getValue("max") : widget.$max,
        autoScale = props.getValue("autoScale"),
        autoScaleStep = props.getValue("autoScaleStep");
    
    if (value === undefined || value === null) {
      value = 0;
    }
    // If this is a control point then get its out value...
    else if (value.getType().is("control:ControlPoint")) {
      value = value.getOut().getValue();
    }
    // If this is a StatusNumeric then get its value...
    else if (value.getType().is("baja:StatusNumeric")) {
      value = value.getValue();
    }
    else {
      // Just hope we have a value...
      value = parseFloat(value);
    }

    if (!_.isNumber(value)) {
      value = 0;
    }

    if (autoScale) {
      // Figure out the minimum scale.
      while (value < min) {
        min -= autoScaleStep;
      }

      // Figure out the maximum scale.
      while (value > max) {
        max += autoScaleStep;
      }
    }

    return {
      max: max,
      min: min,
      data: [{
        value: value
      }]
    };  
  }

  function render(widget) {
    var jq = widget.jq(),
        width = (jq.width() || 800) - options.left * 2,
        height = 50,
        model = makeModel(widget),
        x, 
        y, 
        gauge, 
        bk, 
        grid, 
        axis,
        bar;




                    // Pick colors and value
                    var mainColor = 'green';
                    var maxValue = 100;

                    // Set height and width
                    var height = 220;
                    var width = 220;

                    // Create a radial scale representing 75% of a circle
                    var scale = d3.scale.linear()
                        .domain([0, maxValue])
                        .range([ - Math.PI * 0.75, Math.PI * 0.75])
                        .clamp(true);

                    // Define arc
                    var arc = d3.svg.arc()
                        .startAngle(function(d){
                            return scale(0);
                        })
                        .endAngle(function(d){
                            return scale(d);
                        })
                        .innerRadius(70)
                        .outerRadius(85);

                    // SVG setup
                    var svg  = d3.select(this).append('svg')
                        .attr('width', width)
                        .attr('height', height)
                        .style('background', 'white')
                        .append('g')
                        .attr('transform', 'translate(' + width / 2 + ',' + height / 2 + ')');

                    // Background arc
                    svg.append('path')
                        .data(maxValue)
                        .attr('d', arc)
                        .style('fill', 'lightgray');

                    // Fill arc
                    svg.append('path')
                        .data(model.value)
                        .attr('d', arc)
                        .style('fill', mainColor);

                    // Text
                    svg.append('text')
                        .data(model.value)
                        .attr('class', 'meter-center-text')
                        .style('text-anchor', 'middle')
                        .style('fill', mainColor)
                        .text(function(d){
                            return parseFloat(d);
                        })
                        .attr('transform', 'translate(' + 0 + ',' + 20 + ')');

                };





  CircularGauge.prototype.doInitialize = function (jq) {
    var that = this;

    jq.addClass("circular-gauge-outer");

    d3.select(jq[0])
      .append('svg')
      .attr('top', 0)
      .attr('left', 0)
      .attr('width', "100%")
      .attr('height', "100%")
      .attr('class', 'circular-gauge')
      .append('g')
      .attr('transform', 'translate(' + options.left + ',' + options.top + ')');

    that.getSubscriber().attach("changed", function() {
      render(that);
    });  

    render(that);
  };

  CircularGauge.prototype.doLoad = function (value) {
    render(this);
  };

  CircularGauge.prototype.doLayout =
    CircularGauge.prototype.doChanged = function() {
      render(this);
    };

  CircularGauge.prototype.doDestroy = function () {
    this.jq().removeClass("circular-gauge-outer");
  };

  return CircularGauge;
});
