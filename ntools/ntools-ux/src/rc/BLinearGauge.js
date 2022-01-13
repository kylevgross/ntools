
define(['baja!',
        'baja!control:ControlPoint,baja:StatusNumeric',
        'underscore',
        'bajaux/Widget',
        'bajaux/mixin/subscriberMixIn',
        'd3',
        'css!nmodule/ntools/rc/LinearGaugeStyle'], function (
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

  var LinearGauge = function () {
    var that = this;

    Widget.apply(that, arguments);
    subscriberMixIn(that);

    that.properties().addAll([
    {
      name: "barColor",
      value: "#f7941d",
      typeSpec: "gx:Color"
    },
    {
          name: "bgColor",
          value: "#606060",
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

  LinearGauge.prototype = Object.create(Widget.prototype);
  LinearGauge.prototype.constructor = LinearGauge;

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
        height = (jq.height() || 800) - options.top * 3.5,
        model = makeModel(widget),
        x, 
        y, 
        gauge, 
        bk, 
        grid, 
        axis,
        bar;

    x = d3.scale.linear()
        .domain([model.min, model.max])
        .range([0, width]);

    y = d3.scale.linear()
        .range([0, height]);

    gauge = d3.select(widget.jq()[0])
        .select(".linear-gauge")
        .select("g");
    
    // Render the background
    bk = gauge.selectAll(".linear-grid-background")
      .data(model.data);


    bk.enter()
      .append("rect")
      .attr("class", "linear-grid-background");



    bk.attr("width", width)
      .attr("height", height);



    // The background has a grid on top.
    grid = gauge.selectAll(".linear-grid")
      .data(model.data);   

    grid.enter()
      .append("g")
      .attr("class", "linear-grid");

    grid.attr("transform", "translate(0," + height + ")")
      .call(d3.svg.axis().scale(x).ticks(50).tickSize(-height))
        .selectAll(".linear-tick")
          .data(x.ticks(10), function(d) { return d; })
        .exit()
          .classed("linear-minor", true);

    // The background has an axis drawn at the bottom of it.
    axis = gauge.selectAll(".linear-axis")
      .data(model.data);       

    axis.enter()
      .append("g")
      .attr("class", "linear-axis");

    axis.attr("transform", "translate(0," + height + ")")
      .call(d3.svg.axis().scale(x).ticks(10));

    // The data bar is drawn on top.
    bar = gauge.selectAll(".linear-bar")
      .data(model.data);  

    bar.enter()
      .append("g")
      .append("rect")
      .attr("class", "linear-bar")
      .attr("x", 0)
      .attr("y", 0)
      .attr("rx", 1);


    bar.attr("height", height / 2)
      .transition()
      .attr("fill", widget.properties().getValue("barColor"))
      .attr("width", function (d) { 
        var w = x(d.value); 
        if (w > width) {
          w = width;
        }
        else if (w < 0) {
          w = 0;
        }
        return w;
      });


  }

  LinearGauge.prototype.doInitialize = function (jq) {
    var that = this;

    jq.addClass("linear-gauge-outer");

    d3.select(jq[0])
      .append('svg')
      .attr('top', 0)
      .attr('left', 0)
      .attr('width', "100%")
      .attr('height', "100%")
      .attr('overflow', 'hidden')
      .attr('class', 'linear-gauge')
      .append('g')
      .attr('transform', 'translate(' + options.left + ',' + options.top + ')');


    that.getSubscriber().attach("changed", function() {
      render(that);
    });  

    render(that);
  };

  LinearGauge.prototype.doLoad = function (value) {
    render(this);
  };

  LinearGauge.prototype.doLayout =
    LinearGauge.prototype.doChanged = function() {
      render(this);
    };

  LinearGauge.prototype.doDestroy = function () {
    this.jq().removeClass("linear-gauge-outer");
  };

  return LinearGauge;
});
