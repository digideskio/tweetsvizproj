/**
 * pass keyword to server then return query results
 */
var globalv;
$(document).ready(function() {
	$('#search').click(function(event) {
		if($('#compare').is(":checked")) {
			var keyword = $('#keyword').val();
			var keyword2 = $('#keyword2').val();
			var isCompareView = "true";
			$.get('TweetsVizJSPServlet', {
				keyword : keyword,
				keyword2 : keyword2
			}, function(responseText) {
				var result;
				var sdata = [];
				// JSON TEST
				// TODO
				try {
					result = $.parseJSON(responseText);
					sdata.push(result);
		            display3dScatterView(sdata);
		            Pace.stop();
				} catch (err) {
					// TODO
					// disable tab showing
				  console.log(responseText);
				  alert(responseText);
				}
			});
		} else {
			var keyword = $('#keyword').val();
			var isCompareView = "false";
			$.get('TweetsVizJSPServlet', {
				keyword : keyword
			}, function(responseText) {
				// JSON TEST
				try {
					result = $.parseJSON(responseText);
					sdata.push(result);
		            display3dScatterView(sdata);
		            Pace.stop();
				} catch (err) {
				  console.log(responseText);
				  alert(responseText);
				}
			});
		}
		
	});
});

// series data
function display3dScatterView (sdata) {
    // Give the points a 3D feel by adding a radial gradient
    Highcharts.getOptions().colors = $.map(Highcharts.getOptions().colors, function (color) {
        return {
            radialGradient: {
                cx: 0.4,
                cy: 0.3,
                r: 0.5
            },
            stops: [
                [0, color],
                [1, Highcharts.Color(color).brighten(-0.2).get('rgb')]
            ]
        };
    });

    // Set up the chart
    var chart = new Highcharts.Chart({
        chart: {
            renderTo: 'container',
            margin: 100,
            type: 'scatter',
            options3d: {
                enabled: true,
                alpha: 10,
                beta: 30,
                depth: 250,
                viewDistance: 5,

                frame: {
                    bottom: { size: 1, color: 'rgba(0,0,0,0.02)' },
                    back: { size: 1, color: 'rgba(0,0,0,0.04)' },
                    side: { size: 1, color: 'rgba(0,0,0,0.06)' }
                }
            }
        },
        title: {
            text: 'Draggable box'
        },
        subtitle: {
            text: 'Click and drag the plot area to rotate in space'
        },
        tooltip: {
			useHTML : true,
			formatter : function() {
				console.log(this);
				return '<div class="datalabels"><b>msg: ' + this.point.name
						+ '</b><br>x: ' + this.point.x + '<br>y: ' + this.point.y + '<br>z: ' + this.point.z + '</div>';
			}
        },
        plotOptions: {
            scatter: {
                width: 10,
                height: 10,
                depth: 10
            }
        },
        yAxis: {
            min: 1,
            max: 9,
            title: null
        },
        xAxis: {
            min: 1,
            max: 9,
            gridLineWidth: 1
        },
        zAxis: {
            min: 1,
            max: 9
        },
        legend: {
            enabled: false
        },
        series: sdata
    });


    // Add mouse events for rotation
    $(chart.container).bind('mousedown.hc touchstart.hc', function (e) {
        e = chart.pointer.normalize(e);

        var posX = e.pageX,
            posY = e.pageY,
            alpha = chart.options.chart.options3d.alpha,
            beta = chart.options.chart.options3d.beta,
            newAlpha,
            newBeta,
            sensitivity = 5; // lower is more sensitive

        $(document).bind({
            'mousemove.hc touchdrag.hc': function (e) {
                // Run beta
                newBeta = beta + (posX - e.pageX) / sensitivity;
                newBeta = Math.min(100, Math.max(-100, newBeta));
                chart.options.chart.options3d.beta = newBeta;

                // Run alpha
                newAlpha = alpha + (e.pageY - posY) / sensitivity;
                newAlpha = Math.min(100, Math.max(-100, newAlpha));
                chart.options.chart.options3d.alpha = newAlpha;

                chart.redraw(false);
            },                            
            'mouseup touchend': function () { 
                $(document).unbind('.hc');
            }
        });
    });
}