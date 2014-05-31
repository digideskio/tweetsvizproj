/**
 * pass keyword to server then return query results
 */
var globalv;
$(document).ready(function() {
    $('#search').click(function(event) {
        if ($('#compare').is(":checked")) {
            // compare view
            var keyword = $('#keyword').val();
            var keyword2 = $('#keyword2').val();
            $.get('TweetsVizJSPServlet', {
                keyword: keyword,
                keyword2: keyword2,
                compareview: "true"
            }, function(responseText) {
                console.log(responseText);
                displayCompareView($.parseJSON(responseText));
                Pace.stop();
                /*
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
				*/
    
			});
		} else {
			// single view
			var keyword = $('#keyword').val();
			$.get('TweetsVizJSPServlet', {
				keyword : keyword,
				compareview : "false"
			}, function(responseText) {
				// JSON TEST
				var result, sdata;
				try {
					result = $.parseJSON(responseText);
					sdata = result;
					console.log(sdata);
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

function displayCompareView(sdata) {

    var keyword1 = $('#keyword').val();
    var keyword2 = $('#keyword2').val();
    var view1Satisfied = sdata.data1.percentage * 100;
    var view2Satisfied = sdata.data2.percentage * 100;
    var tmp = 100 - view1Satisfied;
    var view1Unsatisfied = parseFloat(tmp.toFixed(2));
    tmp = 100 - view2Satisfied;
    var view2Unsatisfied = parseFloat(tmp.toFixed(2));
    
    var colors = Highcharts.getOptions().colors,
        categories = ['Satisfied', 'Unsatisfied'],
        name = 'Satisfaction',
        data1 = [{
            y: view1Satisfied,

            color: colors[5]
        }, {
            y: view1Unsatisfied,

            color: colors[0]
        }],

        data2 = [{
            y: view2Satisfied,
            color: colors[5],

        }, {
            y: view2Unsatisfied,

            color: colors[0]
        }];


    // Build the data arrays
    var productData1 = [];
    var productData2 = [];

    for (var i = 0; i < data1.length; i++) {

        // add browser data
        productData1.push({
            name: categories[i],
            y: data1[i].y,
            color: data1[i].color
        });



    }

    for (var i = 0; i < data2.length; i++) {

        // add browser data
        productData2.push({
            name: categories[i],
            y: data2[i].y,
            color: data2[i].color
        });



    }

    // Create the chart
    $('#containerCompare1').highcharts({
        chart: {
            type: 'pie'
        },
        title: {
            text: keyword1
        },
        yAxis: {
            title: {
                text: 'Total percent share'
            }
        },
        plotOptions: {
            pie: {
                shadow: false,
                center: ['50%', '50%']
            }
        },
        tooltip: {
            valueSuffix: '%'
        },
        series: [{
            name: 'User Percentage',
            data: productData1,
            size: '60%',

            dataLabels: {
                formatter: function() {
                    return this.y > 5 ? this.point.name : null;
                },
                color: 'white',
                distance: -30
            }
        }]
    });

    $('#containerCompare2').highcharts({
        chart: {
            type: 'pie'
        },
        title: {
            text: keyword2
        },
        yAxis: {
            title: {
                text: 'Total percent share'
            }
        },
        plotOptions: {
            pie: {
                shadow: false,
                center: ['50%', '50%']
            }
        },
        tooltip: {
            valueSuffix: '%'
        },
        series: [{
            name: 'User Percentage',
            data: productData2,
            size: '60%',
            dataLabels: {
                formatter: function() {
                    return this.y > 5 ? this.point.name : null;
                },
                color: 'white',
                distance: -30
            }
        }]
    });


    //Display texts
    var topWordsEle1 = $("#topWords1");
    var topWordsEle2 = $("#topWords2");
    topWordsEle1.empty();
    topWordsEle2.empty();

    var words1 = sdata.data1.topwords;
    var words2 = sdata.data2.topwords;

    for (var i = 0; i < words1.length; i++) {
        topWordsEle1.append("<p>" + (i + 1) + ": " + words1[i] + "</p><br/>");
    };
    for (var i = 0; i < words2.length; i++) {
        topWordsEle2.append("<p>" + (i + 1) + ": " + words2[i] + "</p><br/>");
    };

    var topTweetsEle1 = $("#topTweets1");
    var topTweetsEle2 = $("#topTweets2");
    topTweetsEle1.empty();
    topTweetsEle2.empty();

    var tweets1 = sdata.data1.topmsg;
    var tweets2 = sdata.data2.topmsg;

    for (var i = 0; i < tweets1.length; i++) {
        topTweetsEle1.append("<p>" + (i + 1) + ": " + tweets1[i] + "</p><br/>");
    };
    for (var i = 0; i < tweets2.length; i++) {
        topTweetsEle2.append("<p>" + (i + 1) + ": " + tweets2[i] + "</p><br/>");
    };
}

// single view data
function display3dScatterView(sdata) {
    // Give the points a 3D feel by adding a radial gradient
    Highcharts.getOptions().colors = $.map(Highcharts.getOptions().colors, function(color) {
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
                    bottom: {
                        size: 1,
                        color: 'rgba(0,0,0,0.02)'
                    },
                    back: {
                        size: 1,
                        color: 'rgba(0,0,0,0.04)'
                    },
                    side: {
                        size: 1,
                        color: 'rgba(0,0,0,0.06)'
                    }
                }
            }
        },
        title: {
            text: '3D Tweets Sentiment Visualization (Valence, Arousal, Dominance)'
        },
        subtitle: {
            text: 'Click and drag the plot area to rotate in space'
        },
        tooltip: {
			useHTML : true,
			formatter : function() {
				console.log(this);
				return '<div class="datalabels"><b>' + this.point.type +': ' + this.point.name
						+ '</b><br><b>&bull;valence</b>: ' + this.point.x + '<br><b>&bull;arousal</b>: ' + this.point.y + '<br><b>&bull;dominance</b>: ' + this.point.z + '</div>';
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
            title: {
                text: 'Arousal',
            }
        },
        xAxis: {
            min: 1,
            max: 9,
            gridLineWidth: 1,
            title: {
                text: 'Valence',
                style: {
                    color: Highcharts.getOptions().colors[0]
                }
            }
        },
        zAxis: {
            min: 1,
            max: 9,
            title: {
                text: 'Dominent',
            }
        },
        legend: {
            enabled: false
        },
        series: sdata
    });


    // Add mouse events for rotation
    $(chart.container).bind('mousedown.hc touchstart.hc', function(e) {
        e = chart.pointer.normalize(e);

        var posX = e.pageX,
            posY = e.pageY,
            alpha = chart.options.chart.options3d.alpha,
            beta = chart.options.chart.options3d.beta,
            newAlpha,
            newBeta,
            sensitivity = 5; // lower is more sensitive

        $(document).bind({
            'mousemove.hc touchdrag.hc': function(e) {
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
            'mouseup touchend': function() {
                $(document).unbind('.hc');
            }
        });
    });
}