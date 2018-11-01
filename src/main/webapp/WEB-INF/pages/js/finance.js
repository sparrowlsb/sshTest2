var histDataSet



var myWallet = new Vue({
    el: '#myWallet',
    data: {
        wallets: [{num:1000,wallet:"usdt"},{num:1000,wallet:"基金1"},{num:1000,wallet:"基金2"},{num:1000,wallet:"基金3"}]
    }
})

var tradeRecord = new Vue({
    el: '#tradeRecord',
    data: {
        records: [{type:"2",date:"2018-10-28 16：30：89",order:"9652",fund:"基金1",num:"1000",status:"完全成交"},{type:"1",date:"2018-10-28 16：30：89",order:"9652",fund:"基金1",num:"1000",status:"完全成交"}]
    },
    methods: {
        buySaleClass: function (type) {
            if (type == 1){
                return 'label-success'
            }else if (type == 2){
                return "label-danger"
            }
        },
        buySaleText: function (type) {
            if (type == 1){
                return "买入"
            }else if (type == 2){
                return "卖出"
            }
        }
    }
})

$.ajax({
    type: 'GET',
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    url: "/fund/fundDailyPrice?fundId=1",
    success: function (data, textStatus) {
        var dailyPrice=data.data;
        var fundDailyPrice = new Vue({
            el: '#fundDailyPrice',

            data: dailyPrice
        })
    }
});

var buyDataSet=null;
$.ajax({
    type: 'GET',
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    url: "/fund/getDailyBuyHistory",

    success: function (data, textStatus) {
        buyDataSet=data.data;
        $('#buytable').DataTable( {
            searching:false,
            'order' : [5,'desc'],
            data: buyDataSet,
            columns: [
                {title: "订单号"},
                {title: "类型"},
                {title: "基金编号"},
                {title: "订单金额"},
                {title: "订单状态"},
                {title: "交易时间"},
                {title: "操作"}
            ],
            "columnDefs": [{
                // 定义操作列
                "targets": 6,
                "data": null,
                "render": function(data, type, row) {
                    var html = '<a href="javascript:void(0);"  class="delete btn btn-default btn-xs"><i class="fa fa-times"></i> 撤销</a>'
                    return html;
                }
            }],
            language: {
                "processing": "处理中...",
                "lengthMenu": "显示 _MENU_ 项结果",
                "zeroRecords": "没有匹配结果",
                "info": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "infoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "infoFiltered": "(由 _MAX_ 项结果过滤)",
                "infoPostFix": "",
                "search": "搜索:",
                "url": "",
                "emptyTable": "表中数据为空",
                "loadingRecords": "载入中...",
                "infoThousands": ",",
                "paginate": {
                    "first": "首页",
                    "previous": "上页",
                    "next": "下页",
                    "last": "末页"
                },
                "aria": {
                    "sortAscending": ": 以升序排列此列",
                    "sortDescending": ": 以降序排列此列"
                }
            }
        } );
// 初始化刪除按钮
        $('#buytable tbody').on('click', 'a.delete', function(e) {
            e.preventDefault();

            if (confirm("确定要删除该属性？")) {
                var table = $('#buytable').DataTable();
                table.row($(this).parents('tr')).remove().draw();
                var id = $('td', $(this).parents('tr')).eq(0).text();
                console.log("id"+id);



                $.ajax({
                    type: "POST",
                    url: "/fund/revokefund",
                    dataType: "json",
                    contentType:"application/json;charset=utf-8",

                    data: JSON.stringify({"id": id,"type":"BUY"}),
                    success: function (data, textStatus) {
                        alert('撤销成功');
                    },

                    error:function(data){
                        alert('撤销失败');
                    }
                });
            }

        });
    }
})
var sellDataSet=null;
$.ajax({
    type: 'GET',
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    url: "/fund/getDailySellHistory",

    success: function (data, textStatus) {
        sellDataSet=data.data;
        console.log(111+data.data);
        $('#selltable').DataTable( {
            'order' : [5,'desc'],
            data: sellDataSet,
            columns: [
                {title: "订单号"},
                {title: "类型"},
                {title: "基金编号"},
                {title: "订单金额"},
                {title: "订单状态"},
                {title: "交易时间"},
                {title: "操作"}
            ],
            "columnDefs": [{
                // 定义操作列
                "targets": 6,
                "data": null,
                "render": function(data, type, row) {
                    var html = '<a href="javascript:void(0);"  class="delete btn btn-default btn-xs"><i class="fa fa-times"></i> 撤销</a>'
                    return html;
                }
            }],
            language: {
                "processing": "处理中...",
                "lengthMenu": "显示 _MENU_ 项结果",
                "zeroRecords": "没有匹配结果",
                "info": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "infoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "infoFiltered": "(由 _MAX_ 项结果过滤)",
                "infoPostFix": "",
                "search": "搜索:",
                "url": "",
                "emptyTable": "表中数据为空",
                "loadingRecords": "载入中...",
                "infoThousands": ",",
                "paginate": {
                    "first": "首页",
                    "previous": "上页",
                    "next": "下页",
                    "last": "末页"
                },
                "aria": {
                    "sortAscending": ": 以升序排列此列",
                    "sortDescending": ": 以降序排列此列"
                }
            }
        } );
// 初始化刪除按钮
        $('#selltable tbody').on('click', 'a.delete', function(e) {
            e.preventDefault();

            if (confirm("确定要删除该属性？")) {
                var table = $('#selltable').DataTable();
                table.row($(this).parents('tr')).remove().draw();
                var id = $('td', $(this).parents('tr')).eq(0).text();
                console.log("id"+id);



                $.ajax({
                    type: "POST",
                    url: "/fund/revokefund",
                    dataType: "json",
                    contentType:"application/json;charset=utf-8",

                    data: JSON.stringify({"id": id,"type":"SELL"}),
                    success: function (data, textStatus) {
                        alert('撤销成功');
                    },

                    error:function(data){
                        alert('撤销失败');
                    }
                });
            }

        });
    }
})

