var successSellTransData=[];
var successBuyTransData=[];
ajax6 = $.ajax({
    type: 'GET',
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    url: "/fund/getSuccessHistory?fundId=1",
    success: function (data, textStatus) {
        var TransData=data.data;

        for (var i = 0; i < TransData.length; i++) {
            if (TransData[i][0]=='SELL'){
                successSellTransData.push([TransData[i][1],TransData[i][2]])
            }else{
                successBuyTransData.push([TransData[i][1],TransData[i][2]])
            }
        }
    },
    error: function (data, textStatus) {
        alert("error");
        console.log(data)

    }

});

var rawData;
$.when(ajax6).done(function (){
    $.ajax({
        type: 'GET',
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        url: "/fund/fundInfo?fundId=1",

        success: function (data, textStatus) {

            rawData=data.data;
            var dom = document.getElementById("kline");
            var myLineChart = echarts.init(dom);
            var app = {};
            line = null;
            var upColor = 'rgb(199,62,49)';
            var downColor = 'rgb(37,166,91)';


            function splitData(rawData) {
                var categoryData = [];
                var values = [];
                var volumes = [];
                var involumes = [];
                var outvolumes = [];

                var name;
                for (var i = 0; i < rawData.length; i++) {
                    categoryData.push(rawData[i].splice(0, 1)[0]);
                    rawData[i].splice(0, 1);

                    values.push(rawData[i]);
                    involumes.push(rawData[i][2]);
                    outvolumes.push(rawData[i][3]);
                    if(i==0){
                        volumes.push([i,rawData[i][1],1]);
                    }else {
                        console.log(rawData[1][0]);
                        volumes.push([i, rawData[i][1], rawData[i][0] > rawData[i - 1][0] ? 1 : -1]);
                    }
                }
                console.log(involumes);
                return {
                    categoryData: categoryData,
                    values: values,
                    volumes: volumes,
                    involumes:involumes,
                    outvolumes:outvolumes
                };
            }

            function calculate(data) {
                var result = [];
                for (var i = 0, len = data.values.length; i < len; i++) {
                    result.push(data.values[i][0]);
                }
                return result;
            }



            var data = splitData(rawData);
            myLineChart.setOption(line = {
                backgroundColor: '#fff',
                animation: false,
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        crossStyle:{
                            width:1.5,
                            type:'dashed',
                            color: {
                                type: 'radial',
                                x: 0.5,
                                y: 0.5,
                                r: 0.5,
                                colorStops: [{
                                    offset: 0, color: 'red' // 0% 处的颜色
                                }, {
                                    offset: 1, color: 'blue' // 100% 处的颜色
                                }],
                                globalCoord: false // 缺省为 false
                            }
                        }
                    },
                    backgroundColor: 'rgba(245, 245, 245, 0.8)',
                    borderWidth: 10,
                    borderColor: '#ccc',
                    padding: 10,
                    textStyle: {
                        color: '#000',
                        fontStyle:'normal',
                        fontWeight:'bold'

                    },
                    position: function (pos, params, el, elRect, size) {
                        var obj = {top: 10};
                        obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30;
                        return obj;
                    }
                    // extraCssText: 'width: 170px'
                },
                axisPointer: {
                    link: {xAxisIndex: 'all'},
                    label: {
                        backgroundColor: '#777'
                    }
                },
                toolbox: {
                    feature: {
                        dataZoom: {
                            yAxisIndex: false
                        },
                        brush: {
                            type: ['lineX', 'clear']
                        }
                    }
                },
                brush: {
                    xAxisIndex: 'all',
                    brushLink: 'all',
                    outOfBrush: {
                        colorAlpha: 0.1
                    }
                },
                visualMap: {
                    show: false,
                    seriesIndex: 3,
                    dimension: 2,
                    pieces: [{
                        value: 1,
                        color: downColor
                    }, {
                        value: -1,
                        color: upColor
                    }]
                },
                grid: [
                    {
                        left: '10%',
                        right: '10%',
                        bottom: 200
                    },
                    {
                        left: '10%',
                        right: '10%',
                        height: 80,
                        bottom: 80
                    }
                ],
                xAxis: [
                    {
                        type: 'category',
                        data: data.categoryData,
                        scale: true,
                        boundaryGap : false,
                        axisLine: {
                            onZero: false,
                            lineStyle:{
                                width:3,
                                type:'solid'
                            }
                        },
                        axisTick:{
                            lineStyle:{

                                width:3

                            }
                        },
                        splitLine: {show: false},
                        splitNumber: 20,
                        min: 'dataMin',
                        max: 'dataMax',
                        axisPointer: {
                            z: 100
                        }
                    },
                    {
                        type: 'category',
                        gridIndex: 1,
                        data: data.categoryData,
                        scale: true,
                        boundaryGap : false,
                        axisLine: {onZero: false},
                        axisTick: {show: false},
                        splitLine: {show: false},
                        axisLabel: {show: false},
                        splitNumber: 20,
                        min: 'dataMin',
                        max: 'dataMax'
                        // axisPointer: {
                        //     label: {
                        //         formatter: function (params) {
                        //             var seriesValue = (params.seriesData[0] || {}).value;
                        //             return params.value
                        //             + (seriesValue != null
                        //                 ? '\n' + echarts.format.addCommas(seriesValue)
                        //                 : ''
                        //             );
                        //         }
                        //     }
                        // }
                    }
                ],
                yAxis: [
                    {
                        scale: true,
                        splitArea: {
                            show: true
                        },
                        axisLine: {
                            onZero: false,
                            lineStyle:{
                                width:3,
                                type:'solid'
                            }
                        },
                        axisTick:{
                            lineStyle:{

                                width:3

                            }
                        },
                    },
                    {
                        scale: true,
                        gridIndex: 1,
                        splitNumber: 2,
                        axisLabel: {show: false},
                        axisLine: {show: false},
                        axisTick: {show: false},
                        splitLine: {show: false}
                    }
                ],
                dataZoom: [
                    {
                        type: 'inside',
                        xAxisIndex: [0, 1],
                        start: 45,
                        end: 100
                    },
                    {
                        show: false,
                        xAxisIndex: [0, 1],
                        type: 'slider',
                        top: '85%',
                        start: 45,
                        end: 100
                    }
                ],
                series: [
                    {
                        name: '卖出',
                        type: 'scatter',
                        symbolSize: 20,
                        itemStyle: {
                            normal: {
                                shadowBlur: 10,
                                shadowColor: 'rgba(120, 36, 50, 0.5)',
                                shadowOffsetY: 5,
                                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                                    offset: 0,
                                    color: 'rgb(251, 118, 123)'
                                }, {
                                    offset: 1,
                                    color: 'rgb(204, 46, 72)'
                                }])
                            }
                        },
                        data: successSellTransData
                    },
                    {
                        name: '买入',
                        type: 'scatter',
                        symbolSize:20,
                        itemStyle: {
                            normal: {
                                shadowBlur: 10,
                                shadowColor: 'rgba(25, 100, 150, 0.5)',
                                shadowOffsetY: 5,
                                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                                    offset: 0,
                                    color: 'rgb(129, 227, 238)'
                                }, {
                                    offset: 1,
                                    color: 'rgb(25, 183, 207)'
                                }])
                            }
                        },
                        data: successBuyTransData
                    },

                    {
                        name: '当日价格',
                        type: 'line',
                        data: calculate(data),
                        smooth: true,
                        symbolSize: 8,   //设定实心点的大小
                        symbol:'circle',
                        lineStyle: {

                            normal: {
                                width: 3,
                                color: {
                                    type: 'radial',
                                    x: 0.5,
                                    y: 0.5,
                                    r: 0.5,
                                    colorStops: [{
                                        offset: 0, color: 'rgb(61,70,77)' // 0% 处的颜色
                                    }, {
                                        offset: 0, color: 'rgb(61,70,77)' // 0% 处的颜色
                                    }],
                                    globalCoord: false // 缺省为 false
                                },
                            }
                        },

                        areaStyle: {
                            normal: {
                                color: {
                                    type: 'radial',
                                    x: 0.5,
                                    y: 0.5,
                                    r: 0.5,
                                    colorStops: [{
                                        offset: 0, color: 'rgb(53,60,66)' // 0% 处的颜色
                                    }, {
                                        offset: 0, color: 'rgb(53,60,66)' // 0% 处的颜色
                                    }],
                                    globalCoord: false // 缺省为 false
                                },}
                        },
                        itemStyle:{
                            color: 'rgb(255, 70, 131)',
                            lineStyle: {
                                width: 30,

                            },

                            type:"solid",
                            normal: {opacity: 0.5}
                        }
                    },

                    {
                        name: '成交量',
                        type: 'bar',
                        xAxisIndex: 1,
                        yAxisIndex: 1,
                        data: data.volumes
                    },

                ]
            }, true);


            if (myLineChart && typeof line === "object") {
                myLineChart.setOption(line, true);
            }





            var dom3 = document.getElementById("zhuzhuangtu");
            var myChart3 = echarts.init(dom3,'infographic');

            var option3 = {
                legend: {
                    data:['买入','卖出']
                },
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                xAxis: [
                    {
                        type : 'category',
                        data: data.categoryData,
                        onZero: false,
                        boundaryGap : true,
                        axisLine: {
                            onZero: false,
                            lineStyle:{
                                width:3,
                                type:'solid'
                            }
                        },
                        axisTick:{
                            lineStyle:{

                                width:3

                            }
                        },
                        lineStyle: {
                            width: 3,
                            type: 'solid'
                        },
                        splitLine: {show: false},
                        splitNumber: 20,
                        min: 'dataMin',
                        max: 'dataMax',
                        axisPointer: {
                            z: 10
                        }
                    }],
                yAxis : [
                    {
                        axisLine: {
                            onZero: false,
                            lineStyle:{
                                width:3,
                                type:'solid'
                            }
                        },
                        axisTick:{
                            lineStyle:{

                                width:3

                            }
                        },
                        type : 'value'
                    }
                ],
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                dataZoom: [{
                    textStyle: {
                        color: '#8392A5'
                    },
                    start: 45,
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
                        name: '买入',
                        type: 'bar',
                        data: data.involumes,
                        smooth: true,
                        showSymbol: true,
                        stack: '总量',
                        itemStyle: {
                            normal: {
                                color:upColor,
                                width: 5
                            }
                        }
                    },
                    {
                        name: '卖出',
                        type: 'bar',
                        data: data.outvolumes,
                        smooth: true,
                        showSymbol: true,
                        stack: '总量',
                        itemStyle: {
                            normal: {
                                color:downColor,
                                width: 5
                            }
                        }
                    }


                ]
            };


            if (option3 && typeof option3 === "object") {
                myChart3.setOption(option3, true);
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
        },
        error: function (data, textStatus) {
            alert("error");
            console.log(data)

        }

    });
})


