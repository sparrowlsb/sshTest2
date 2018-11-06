

var rawData;
$.ajax({
	type: 'GET',
	dataType: "json",
	contentType: "application/json;charset=utf-8",
	url: "/fund/fundInfo?fundId=1",

	success: function (data, textStatus) {

        rawData=data.data;

        console.log(2222+JSON.stringify(data.data));
		// rawData=JSON.stringify(data.data);
        var dom = document.getElementById("container");
        var myChart = echarts.init(dom,'infographic');
        var app = {};
        option = null;

        app.title = '基金走势图';


        function calculateMA(dayCount, data) {
            var result = [];
            for(var i = 0, len = data.length; i < len; i++){
                result.push(data[i][1]);
            }

            console.log(result)
            return result;
        }
        function calculateMA2( data) {
            var result = [];
            for(var i = 0, len = data.length; i < len; i++){
                result.push(data[i][2]);
            }

            console.log(result)
            return result;
        }

        var dates = rawData.map(function (item) {
            return item[0];
        });

        var data = rawData.map(function (item) {
            return [+item[1], +item[2], +item[3], +item[4], +item[5]];
        });
        option = {
            animation: false,
            legend: {
                data: [ '基金1',"成交量"],
                inactiveColor: '#666',
                textStyle: {
                    color: '#22'
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    animation: false,
                    type: 'cross',
                    lineStyle: {
                        color: '#376df4',
                        width: 2,
                        opacity: 1
                    }
                }
            },

            axisPointer: {
                link: {xAxisIndex: 'all'},
                label: {
                    backgroundColor: '#777'
                }
            },

            grid: [
                {
                    left: '10%',
                    right: '8%',
                    height: '50%'
                },
                {
                    left: '10%',
                    right: '8%',
                    top: '63%',
                    height: '16%'
                }
            ],
            xAxis: [
                {
                    type: 'category',
                    data: dates,
                    axisLine: { lineStyle: { color: '#8392A5' } }
                },
                {
                    type: 'category',
                    gridIndex: 1,
                    data: dates,
                    axisLine: { lineStyle: { color: '#8392A5' } }

                }
            ],

            yAxis: [
                {
                    // type: 'value',
                    // scale: true,
                    // name: '销量',
                    max: 1.5,
                    // min: 0,
                    // boundaryGap: [0.02, 0.02],
                    //
                    // scale: true,
                    axisLine:{
                        lineStyle:{
                            color:'#4A5675',
                            // width:2  
                        }
                    },
                    name: '基金价格',
                    type: 'value',
                    splitLine: {
                        show: false
                    },
                    scale:true,


                },
                {
                    scale: true,
                    gridIndex: 1,
                    splitNumber: 2,
                    axisLabel: {show: true},
                    axisLine: {show: true},
                    axisTick: {show: false},
                    splitLine: {show: false}


                }
            ],
            dataZoom: [
                {
                    type: 'inside',
                    xAxisIndex: [0, 1],
                    start: 98,
                    end: 100
                },
                {
                    show: true,
                    xAxisIndex: [0, 1],
                    type: 'slider',
                    top: '80%',
                    start: 98,
                    end: 100
                }
            ],
            series: [

                {

                    name: '基金1',
                    type: 'line',
                    data: calculateMA(5, data),
                    smooth: true,
                    showSymbol: false,
                    symbolSize: 10,   //设定实心点的大小

                    lineStyle: {

                        normal: {
                            width: 3
                        }
                    }
                },

                {
                    name: '买入',
                    symbolSize: 10,
                    data: [
                        ['2015/3/19', 3582.27],
                        ['2015/4/20', 4217.08],
                        ['2015/8/26', 2927.29]

                    ],
                    type: 'scatter'
                },
                {
                    name: '卖出',
                    symbolSize: 10,

                    data: [


                        ['2015/6/12', 5166.35]


                    ],
                    type: 'scatter'
                },
                {
                    name: '成交量',
                    type: 'bar',
                    xAxisIndex: 1,
                    yAxisIndex: 1,
                    data: calculateMA2( data),
                    color: "#999efe"
                }


            ] };


        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }


        var dom2 = document.getElementById("bingtu");
        var myChart2 = echarts.init(dom2,'infographic');
        var app2 = {};
        option2 = null;
        app2.title = '折柱混合';

        option2 = {
            visualMap: {
                show: false,
                min: 80,
                max: 600,
                inRange: {
                    colorLightness: [0, 1]
                }
            },
            series : [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius: '55%',
                    data:[
                        {value:235, name:'BTC'},
                        {value:274, name:'ETH'},
                        {value:310, name:'EOS'},
                        {value:335, name:'XRP'},
                        {value:400, name:'HT'}
                    ],
                    roseType: 'angle',
                    label: {
                        normal: {
                            textStyle: {
                                fontSize: 10,
                                color: '#235894'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            lineStyle: {
                                color: '#235894'
                            }
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#c23531',
                            shadowBlur: 200,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        if (option2 && typeof option2 === "object") {
            myChart2.setOption(option2, true);
        }

        var dom3 = document.getElementById("zhuzhuangtu");
        var myChart3 = echarts.init(dom3,'infographic');
        var app3 = {};

        app3.title = '2015 年上证指数';

        function calculateMA3( data) {
            var result = [];
            for(var i = 0, len = data.length; i < len; i++){
                result.push(data[i][2]);
            }

            console.log(result)
            return result;
        }
        function calculateMA4( data) {
            var result = [];
            for(var i = 0, len = data.length; i < len; i++){
                result.push(data[i][3]);
            }

            console.log(result)
            return result;
        }
        function calculateMA5( data) {
            var result = [];
            for(var i = 0, len = data.length; i < len; i++){
                result.push(data[i][4]);
            }

            console.log(result)
            return result;
        }
        var option3 = {
            legend: {
                data: [ '总交易量','买入', '卖出'],
                inactiveColor: '#777',
                textStyle: {
                    color: '#ffe325'
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    animation: false,
                    type: 'cross',
                    lineStyle: {
                        color: '#376df4',

                        width: 2,
                        opacity: 1
                    }
                }
            },
            xAxis: {
                type: 'category',
                data: dates,
                axisLine: { lineStyle: { color: '#8392A5' } }
            },
            yAxis: {
                scale: true,
                axisLine: { lineStyle: { color: '#8392A5' } },
                splitLine: { show: false }
            },
            grid: {
                bottom: 80
            },
            dataZoom: [{
                textStyle: {
                    color: '#8392A5'
                },
                start: 99,
                end: 100,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                dataBackground: {
                    areaStyle: {
                        color: '#8392A5'
                    },
                    lineStyle: {
                        opacity: 0.8,
                        color: '#8392A5'
                    }
                },
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            }, {
                type: 'inside'
            }],
            animation: false,
            series: [

                {
                    name: '总交易量',
                    type: 'bar',
                    data: calculateMA3( data),
                    smooth: true,
                    showSymbol: false,
                    lineStyle: {
                        normal: {
                            width: 5
                        }
                    }
                },
                {
                    name: '买入',
                    type: 'bar',
                    data: calculateMA4( data),
                    smooth: true,
                    showSymbol: false,
                    lineStyle: {
                        normal: {
                            width: 5
                        }
                    }
                },
                {
                    name: '卖出',
                    type: 'bar',
                    data: calculateMA5( data),
                    smooth: true,
                    showSymbol: false,
                    lineStyle: {
                        normal: {
                            width: 5
                        }
                    }
                }


            ]
        };


        if (option3 && typeof option3 === "object") {
            myChart3.setOption(option3, true);
        }

    },
	error: function (data, textStatus) {
		alert("error");
		console.log(data)

	}

});


