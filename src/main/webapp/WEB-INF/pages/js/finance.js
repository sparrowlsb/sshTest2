var histDataSet

var charge = new Vue({
    el: '#charge',
    data: {
        charges: [],
        currentPage:0,
        pageSize:0,
        totalCount:0,
        totalPages:0
    },
    methods: {
        buySaleClass: function (type) {
            if (type == "充值"){
                return 'label-success'
            }else if (type == "提现"){
                return "label-danger"
            }
        },
        nextPage: function () {
            if (this.currentPage < this.totalPages) {
                this.getExchangeHist(this.currentPage+1)
            }
        },
        prePage: function () {
            if (this.currentPage >1) {
                this.getExchangeHist(this.currentPage-1)
            }
        },
        ajaxData: function (page) {
            var self = this;
            $.ajax({
                type: 'GET',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: "/curb/getHist?page="+page,
                success: function (data, textStatus) {
                    if (data.result == 1) {

                        self.charges = data.data.transactionHist;
                        self.currentPage = data.data.currentPage;
                        self.pageSize = data.data.pageSize;
                        self.totalCount = data.data.totalCount;
                        self.totalPages = data.data.totalPages;
                    }
                }
            });
        }
    },
    mounted: function(){
        this.ajaxData(1)
    }
})

var myWallet = new Vue({
    el: '#myWallet',

    data: {
        wallets: []
    },
    methods: {
        ajaxData: function () {
            var self = this;
            $.ajax({
                type: 'GET',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: "/fund/fundsDetails",
                success: function (data, textStatus) {
                    if (data.result == 1) {
                        self.wallets = data.data.fundsDetails;
                    }
                }
            });
        }
    },
    mounted: function(){
        this.ajaxData()
    }
})

var tradeRecord = new Vue({
    el: '#tradeRecord',
    data: {
        records: [],
        currentPage:0,
        pageSize:0,
        totalCount:0,
        totalPages:0
    },
    methods: {
        buySaleClass: function (type) {
            if (type == "BUY"){
                return 'label-success'
            }else if (type == "SELL"){
                return "label-danger"
            }
        },
        buySaleText: function (type) {
            if (type == "BUY"){
                return "买入"
            }else if (type == "SELL"){
                return "卖出"
            }
        },
        nextPage: function () {
            if (this.currentPage < this.totalPages) {
                this.ajaxData(this.currentPage+1)
            }
        },
        prePage: function () {
            if (this.currentPage >1) {
                this.ajaxData(this.currentPage-1)
            }
        },
        ajaxData: function (page) {
            var self = this;
            $.ajax({
                type: 'GET',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: "/fund/getHistory?page="+page,
                success: function (data, textStatus) {
                    if (data.result == 1) {
                        self.records = data.data.HIST;
                        self.currentPage = data.data.currentPage;
                        self.pageSize = data.data.pageSize;
                        self.totalCount = data.data.totalCount;
                        self.totalPages = data.data.totalPages;
                    }
                }
            });
        }
    },
    mounted: function(){
        this.ajaxData(1)
    }
})

var main = new Vue({
    el: '#main',
    data: {
        fundId: document.getElementById('fundId').innerHTML,
        fund: {},
        fund2: {},
        fund3: {},
        maxBuy: 0,
        maxSell: 0,
        email: email,
        charges: [],
        currentPage:0,
        pageSize:0,
        totalCount:0,
        totalPages:0,
        wallets:[{type:"请先登录",money:""},{type:"",money:""},{type:"",money:""},{type:"",money:""}],
        USDT:{type:"usdt",money:"0"},
    },
    methods: {
        getDailyPrice: function (fundId) {
            var self = this;
            $.ajax({
                type: 'GET',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: "/fund/fundDailyPrice",
                success: function (data, textStatus) {
                    if (data.result == 1){
                        if (data.data!=null) {
                            self.fund = data.data.fund1
                            self.fund2 = data.data.fund2
                            self.fund3 = data.data.fund3
                        }
                    }
                }
            });
        },
        getMaxBuy: function (fundId) {
            var self = this;
            $.ajax({
                type: 'GET',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: "/fund/totalCount?type=USDT",
                success: function (data, textStatus) {
                    if (data.result == 1){
                        self.maxBuy = data.data.totalcount;
                    }
                }
            });
        },
        getMaxSell: function (fundId) {
            var self = this;
            $.ajax({
                type: 'GET',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: "/fund/totalCount?type=FUND_"+fundId,
                success: function (data, textStatus) {
                    if (data.result == 1) {
                        self.maxSell = data.data.totalcount;
                    }
                }
            });
        },
        getChargeHist: function () {
            var self = this;
            $.ajax({
                type: 'GET',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: "/curb/getHist",
                success: function (data, textStatus) {
                    if (data.result == 1) {
                        self.charges = data.data.transactionHist;
                    }
                }
            });
        },
        sellUSDT: function() {
            var money= $("#sellMoney").val();
            if (!money || money > this.USDT.money){
                alert("提现金额不能为空或者超过最大提现金额！");
                return
            }
            $.ajax({
                type: "POST",   //提交的方法
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: "/curb/sellMoney/", //提交的地址
                data: JSON.stringify({"sellMoney": money}),

                error: function (request) {  //失败的话
                    alert("Connection error");
                    sellUSDT
                },
                success: function (data) {  //成功
                    if (data.result == 1) {
                        alert("提现成功，联系兑换商提现！")
                        window.location.reload()
                    } else if (data.result == 0) {
                        alert("提现失败，确认提现额度！");


                    }

                }
            });
        },
        buyUSDT: function() {
            var money= $("#buyMoney").val();
            if (!money || money <= 0){
                alert("提现金额不能为空或者为0！");
                return
            }
            $.ajax({
                type: "POST",   //提交的方法
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: "/curb/buyMoney/", //提交的地址
                data: JSON.stringify({"sellMoney": money}),

                error: function (request) {  //失败的话
                    alert("Connection error");
                    sellUSDT
                },
                success: function (data) {  //成功
                    if (data.result == 1) {
                        alert("充值成功，联系兑换商充值！")
                        window.location.reload()
                    } else if (data.result == 0) {
                        alert("充值失败，确认充值额度！");


                    }

                }
            });
        },
        getWallets: function () {
            var self = this;
            $.ajax({
                type: 'GET',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: "/fund/fundsDetails",
                success: function (data, textStatus) {
                    if (data.result == 1) {
                        self.wallets = data.data.fundsDetails;
                        self.USDT = data.data.fundsDetails[0];

                    }
                }
            });
        },
    },
    mounted: function(){
        this.getDailyPrice(this.fundId);
        this.getMaxSell(this.fundId);
        this.getMaxBuy();
        this.getWallets();
    }
})
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
            'order' : [6,'desc'],
            data: buyDataSet,
            columns: [
                {title: "订单号"},
                {title: "类型"},
                {title: "基金编号"},
                {title: "基金名称"},
                {title: "订单金额(USDT)"},
                {title: "订单状态"},
                {title: "交易时间"},
                {title: "操作"}
            ],
            "columnDefs": [{
                // 定义操作列
                "targets": 7,
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

            if (confirm("确定要删除该订单？")) {
                var table = $('#buytable').DataTable();
                table.row($(this).parents('tr')).remove().draw();
                var id = $('td', $(this).parents('tr')).eq(0).text();



                $.ajax({
                    type: "POST",
                    url: "/fund/revokefund",
                    dataType: "json",
                    contentType:"application/json;charset=utf-8",

                    data: JSON.stringify({"id": id,"type":"BUY"}),
                    success: function (data, textStatus) {
                        if (data.result == 1) {
                            alert('撤销成功');
                        }else {
                            alert('撤销失败');
                        }
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
        $('#selltable').DataTable( {
            'order' : [6,'desc'],
            data: sellDataSet,
            columns: [
                {title: "订单号"},
                {title: "类型"},
                {title: "基金编号"},
                {title: "基金名称"},
                {title: "订单数量"},
                {title: "订单状态"},
                {title: "交易时间"},
                {title: "操作"}
            ],
            "columnDefs": [{
                // 定义操作列
                "targets": 7,
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

            if (confirm("确定要撤销该订单？")) {
                var table = $('#selltable').DataTable();
                table.row($(this).parents('tr')).remove().draw();
                var id = $('td', $(this).parents('tr')).eq(0).text();


                $.ajax({
                    type: "POST",
                    url: "/fund/revokefund",
                    dataType: "json",
                    contentType:"application/json;charset=utf-8",

                    data: JSON.stringify({"id": id,"type":"SELL"}),
                    success: function (data, textStatus) {
                        if (data.result == 1) {
                            alert('撤销成功');
                        }else {
                            alert('撤销失败');
                        }
                    },

                    error:function(data){
                        alert('撤销失败');
                    }
                });
            }

        });
    }
})
// var buyCurbDataSet=null;
// $.ajax({
//     type: 'GET',
//     dataType: "json",
//     contentType: "application/json;charset=utf-8",
//     url: "/curb/getHist",
//
//     success: function (data, textStatus) {
//         buyCurbDataSet=data.data.transactionHist;
//         console.log(buyCurbDataSet)
//         $('#buytable').DataTable( {
//             searching:false,
//             'order' : [5,'desc'],
//             data: buyCurbDataSet,
//             columns: [
//                 {title: "订单号",data:"id"},
//                 {
//                     title: "类型",
//                     data:"exchangeType",
//                     render: function(data,type,row,meta){
//                         if (data=="提现"){
//                             return '<span style="color:red">'+data+'</span>';
//                         }else {
//                             return '<span style="color:green">'+data+'</span>';
//                         }
//                     }
//                 },
//                 {title: "交易方式",data:"exchangePlatform"},
//                 {title: "交易金额",data:"money"},
//                 {title: "订单状态",data:"status"},
//                 {title: "交易时间",data:"exchangeDate"}
//             ],
//             language: {
//                 "processing": "处理中...",
//                 "lengthMenu": "显示 _MENU_ 项结果",
//                 "zeroRecords": "没有匹配结果",
//                 "info": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
//                 "infoEmpty": "显示第 0 至 0 项结果，共 0 项",
//                 "infoFiltered": "(由 _MAX_ 项结果过滤)",
//                 "infoPostFix": "",
//                 "search": "搜索:",
//                 "url": "",
//                 "emptyTable": "表中数据为空",
//                 "loadingRecords": "载入中...",
//                 "infoThousands": ",",
//                 "paginate": {
//                     "first": "首页",
//                     "previous": "上页",
//                     "next": "下页",
//                     "last": "末页"
//                 },
//                 "aria": {
//                     "sortAscending": ": 以升序排列此列",
//                     "sortDescending": ": 以降序排列此列"
//                 }
//             }
//         } );
//     }
// })

