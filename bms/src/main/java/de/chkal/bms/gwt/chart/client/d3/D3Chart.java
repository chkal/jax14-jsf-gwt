package de.chkal.bms.gwt.chart.client.d3;

import com.google.gwt.core.client.JsArray;

import de.chkal.bms.model.Kuchen;

/**
 * Das d3.js Chart
 */
public class D3Chart {

  private JsArray<D3Artikel> artikel = JsArray.createArray().cast();

  public D3Chart(String id) {
    initChart(id, 450, 12);
  }

  public void reset() {
    artikel.setLength(0);
  }

  public void addArtikel(Kuchen kuchen, double kosten) {

    D3Artikel chartArtikel = D3Artikel.createObject().cast();
    
    chartArtikel.init(kuchen, kosten);

    artikel.push(chartArtikel);
    
  }

  public void redraw() {
    updateChart(artikel);
  }

  private native void initChart(String id, int size, int maxPrice) /*-{

    var d3 = $wnd.d3;

    var margin = 30;
    width = size - 2 * margin;

    // setup x
    var xScale = d3.scale.linear()
        .domain([0, maxPrice])
        .range([0, width]);
    var xAxis = d3.svg.axis()
        .scale(xScale)
        .orient("bottom");

    // setup y
    var yScale = d3.scale.linear()
        .domain([0, maxPrice])
        .range([width, 0]);
    var yAxis = d3.svg.axis()
        .scale(yScale)
        .orient("left");

    // the color scale
    var cScale = d3.scale.linear()
        .domain([-50, 0, 50])
        .range(["green", "#333", "red"]);

    // map values to screen positions
    xMap = function(d) { 
      return xScale(d.kostenAlt);
    };
    yMap = function(d) { 
      return yScale(d.kostenNeu);
    };
    cMap = function(d) { 
      return cScale(d.relativ);
    };

    // create the svg drawing area
    svg = d3.select("#" + id).append("svg")
        .attr("width", size)
        .attr("height", size)
        .attr("class", "chart")
      .append("g")
        .attr("transform", "translate(" + margin + "," + margin + ")");

    // the tooltip
    tooltip = d3.select("body").append("div")
        .attr("class", "tooltip")
        .style("opacity", 0);

    // x-axis
    svg.append("g")
        .attr("class", "x axis")
        .attr("transform", "translate(0," + width + ")")
        .call(xAxis)
      .append("text")
        .attr("class", "axis-label")
        .attr("x", width)
        .attr("y", -6)
        .style("text-anchor", "end")
        .text("Bisherige Kosten (€)");

    // y-axis
    svg.append("g")
        .attr("class", "y axis")
        .call(yAxis)
      .append("text")
        .attr("class", "axis-label")
        .attr("transform", "rotate(-90)")
        .attr("y", 6)
        .attr("dy", ".71em")
        .style("text-anchor", "end")
        .text("Neue Kosten (€)");

    // diagonal
    svg.append("line")
      .attr('class', 'diagonal')
      .attr('x1', 0)
      .attr('y1', width)
      .attr('x2', width)
      .attr('y2', 0);

  }-*/;

  private native void updateChart(JsArray<D3Artikel> data) /*-{

    function prettyPrint(value, fd) {
      return parseFloat( value ).toFixed( fd );
    }

    var circle = svg.selectAll(".dot")
      .data(data);

    circle.enter().append("circle")
      .attr("class", "dot")
      .attr("r", 0.1)
      .attr("cx", width/2)
      .attr("cy", width)
      .attr("fill-opacity", 0)
      .style("fill", 'red') 
      .on("mouseover", function(d) {

          tooltip.transition()
              .duration(600)
              .style("opacity", .9);

          var template = 
              '<span class="n">NAME</span>' +
              '<table>' + 
              '<tr><td class="l">Alte Kosten:</td><td class="v">ALT€</td></tr>' +
              '<tr><td class="l">Neue Kosten:</td><td class="v">NEU€</td></tr>' +
              '<tr><td class="l">Differenz:</td><td class="v">ABS€</td></tr>' +
              '<tr><td class="l">Relativ:</td><td class="v">REL%</td></tr>' +
              '</table>';

          var content = template
              .replace(/NAME/, d.name)
              .replace(/ALT/, prettyPrint( d.kostenAlt, 2  ) )
              .replace(/NEU/, prettyPrint( d.kostenNeu, 2 ) )
              .replace(/ABS/, prettyPrint( d.differenz, 2 ) )
              .replace(/REL/, prettyPrint( d.relativ, 1 ) );

          tooltip.html(content)
              .style("left", ($wnd.d3.event.pageX + 5) + "px")
              .style("top", ($wnd.d3.event.pageY - 28) + "px");
              
      })
      .on("mouseout", function(d) {
          tooltip.transition()
               .duration(500)
               .style("opacity", 0);
      });

    circle
      .transition()
      .duration(1000)
      .attr("fill-opacity", 1.0)
      .attr("r", 6.0)
      .attr("cx", xMap)
      .attr("cy", yMap)
      .style('fill', cMap);

  }-*/;

}
