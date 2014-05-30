// $(function() {

//     var colors = Highcharts.getOptions().colors,
//         categories = ['Satisfied', 'Unsatisfied'],
//         name = 'Satisfaction',
//         data1 = [{
//             y: 10,
//             extra: "dfdfdfdf",
//             color: colors[5]
//         }, {
//             y: 90,
//             extra: "dfdfdfdf",
//             color: colors[0]
//         }];

//     data2 = [{
//         y: 60,
//         color: colors[5],
//         extra: "dfdfdfdf"
//     }, {
//         y: 40,
//         extra: "dfdfdfdf",
//         color: colors[0]
//     }];


//     // Build the data arrays
//     var productData1 = [];
//     var productData2 = [];

//     for (var i = 0; i < data1.length; i++) {

//         // add browser data
//         productData1.push({
//             name: categories[i],
//             y: data1[i].y,
//             color: data1[i].color
//         });



//     }

//     for (var i = 0; i < data2.length; i++) {

//         // add browser data
//         productData2.push({
//             name: categories[i],
//             y: data2[i].y,
//             color: data2[i].color
//         });



//     }

//     // Create the chart
//     $('#containerCompare1').highcharts({
//         chart: {
//             type: 'pie'
//         },
//         title: {
//             text: 'Product A'
//         },
//         yAxis: {
//             title: {
//                 text: 'Total percent share'
//             }
//         },
//         plotOptions: {
//             pie: {
//                 shadow: false,
//                 center: ['50%', '50%']
//             }
//         },
//         tooltip: {
//             valueSuffix: '%'
//         },
//         series: [{
//             name: 'Users',
//             data: productData1,
//             size: '60%',

//             dataLabels: {
//                 formatter: function() {
//                     return this.y > 5 ? this.point.name : null;
//                 },
//                 color: 'white',
//                 distance: -30
//             }
//         }]
//     });

//     $('#containerCompare2').highcharts({
//         chart: {
//             type: 'pie'
//         },
//         title: {
//             text: 'Product B'
//         },
//         yAxis: {
//             title: {
//                 text: 'Total percent share'
//             }
//         },
//         plotOptions: {
//             pie: {
//                 shadow: false,
//                 center: ['50%', '50%']
//             }
//         },
//         tooltip: {
//             valueSuffix: '%'
//         },
//         series: [{
//             name: 'Users',
//             data: productData2,
//             size: '60%',
//             dataLabels: {
//                 formatter: function() {
//                     return this.y > 5 ? this.point.name : null;
//                 },
//                 color: 'white',
//                 distance: -30
//             }
//         }]
//     });
// });