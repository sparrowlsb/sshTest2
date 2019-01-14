var finance_main = new Vue({
    el: '#main',
    data: {
        status:0,
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
        //历史交易记录字段
        tradeRecords: [],
        tradeCurrentPage:0,
        tradePageSize:0,
        tradeTotalCount:0,
        tradeTotalPages:0,
        //充值提现记录
        charges: [],
        chargeCurrentPage:0,
        chargePageSize:0,
        chargeTotalCount:0,
        chargeTotalPages:0,

        buyTable: {},
        sellTable: {},
        qqList: qqList
    },
    methods: {
        getUserInfo: function () {
            var self = this;
            var url=config.api_prefix+config.api_getUser;
            $.ajax({

                type: 'GET',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: url,
                success: function (data, textStatus) {

                    self.status=data.result


                }
            });
        },
        getDailyPrice: function () {
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
                url: "/fund/totalCount?type=FUND_"+self.fundId,
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
        buyfunction: function () {
            var self = this;
            if (confirm("确定要购买该基金？")) {
                var buyNum = $("#buyNum").val();
                if (buyNum != "" && buyNum >= 10) {
                    var url = config.api_prefix + config.api_buyFund;
                    $.ajax({
                        type: 'POST',
                        dataType: "json",
                        contentType: "application/json;charset=utf-8",
                        url: url,

                        data: JSON.stringify({"traderMoney": buyNum, "fundId": self.fundId}),
                        success: function (data, textStatus) {
                            if (data.result == 1) {
                                alert('交易成功');
                                $("#buyNum").val(null);
                                //更新信息
                                // this.getUserInfo();
                                // this.getDailyPrice(this.fundId);
                                // this.getMaxSell(this.fundId);
                                self.getMaxBuy();
                                self.getWallets();
                                self.tradeAjaxData(1);
                                self.buyTable.ajax.reload();
                            }else {
                                alert('交易失败');
                            }
                            var table = $('#buytable').DataTable();
                            table.draw(false);

                        },
                        error: function (data, textStatus) {
                            alert("交易失败");
                            console.log(data)

                        }

                    });
                }else{
                    alert("最小交易额 10USDT")
                }

            }
        },
        sellfunction: function () {
            var self = this;
            if (confirm("确定要卖出该基金？")) {
                var sellNum = $("#sellNum").val();
                if (sellNum != "" && sellNum > 0) {
                    var url = config.api_prefix + config.api_sellFund;
                    $.ajax({
                        type: 'POST',
                        dataType: "json",
                        contentType: "application/json;charset=utf-8",
                        url: url,

                        data: JSON.stringify({"fundCount": sellNum, "fundId": self.fundId}),
                        success: function (data, textStatus) {
                            if (data.result == 1) {
                                alert('交易成功');
                                $("#sellNum").val(null);
                                //更新信息
                                // this.getUserInfo();
                                // this.getDailyPrice(this.fundId);
                                self.getMaxSell();
                                // self.getMaxBuy();
                                self.getWallets();
                                self.tradeAjaxData(1);
                                self.sellTable.ajax.reload();
                            }else {
                                alert('交易失败');
                            }
                            var table = $('#selltable').DataTable();
                            table.draw( false );

                        },
                        error: function (data, textStatus) {
                            alert("交易失败");
                            console.log(data)

                        }

                    });
                }
            }
        },

        //历史交易记录method
        tradeBuySaleClass: function (type) {
            if (type == "BUY"){
                return 'label-success'
            }else if (type == "SELL"){
                return "label-danger"
            }
        },
        tradeBuySaleText: function (type) {
            if (type == "BUY"){
                return "买入"
            }else if (type == "SELL"){
                return "卖出"
            }
        },
        tradeNextPage: function () {
            if (this.tradeCurrentPage < this.tradeTotalPages) {
                this.tradeAjaxData(this.tradeCurrentPage+1)
            }
        },
        tradePrePage: function () {
            if (this.tradeCurrentPage >1) {
                this.tradeAjaxData(this.tradeCurrentPage-1)
            }
        },
        tradeAjaxData: function (page) {
            var self = this;
            $.ajax({
                type: 'GET',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: "/fund/getHistory?page="+page,
                success: function (data, textStatus) {
                    if (data.result == 1) {
                        self.tradeRecords = data.data.HIST;
                        self.tradeCurrentPage = data.data.currentPage;
                        self.tradePageSize = data.data.pageSize;
                        self.tradeTotalCount = data.data.totalCount;
                        self.tradeTotalPages = data.data.totalPages;
                    }
                }
            });
        },
        //今日交易记录表格
        todayTradeRecord: function () {
            var self = this;
            //买入记录表格
            this.buyTable = $('#buytable').DataTable( {
                searching:false,
                order : [6,'desc'],
                ajax : config.api_prefix + "fund/getDailyBuyHistory",
                destroy: true,
                retrieve:true,
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
                                //更新信息
                                // self.getUserInfo();
                                // self.getDailyPrice();
                                // self.getMaxSell();
                                self.getMaxBuy();
                                self.getWallets();
                                self.tradeAjaxData(1);
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
            //卖出记录表格
            this.sellTable = $('#selltable').DataTable( {
                searching:false,
                order : [6,'desc'],
                ajax : config.api_prefix + "fund/getDailySellHistory",
                destroy: true,
                retrieve:true,
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
                                //更新信息
                                // self.getUserInfo();
                                // self.getDailyPrice();
                                self.getMaxSell();
                                // self.getMaxBuy();
                                self.getWallets();
                                self.tradeAjaxData(1);
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
        },
        //充值提现记录
        chargeBuySaleClass: function (type) {
            if (type == "充值"){
                return 'label-success'
            }else if (type == "提现"){
                return "label-danger"
            }
        },
        chargeNextPage: function () {
            if (this.chargeCurrentPage < this.chargeTotalPages) {
                this.chargeAjaxData(this.chargeCurrentPage+1)
            }
        },
        chargePrePage: function () {
            if (this.chargeCurrentPage >1) {
                this.chargeAjaxData(this.chargeCurrentPage-1)
            }
        },
        chargeAjaxData: function (page) {
            var self = this;
            $.ajax({
                type: 'GET',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: "/curb/getHist?page="+page,
                success: function (data, textStatus) {
                    if (data.result == 1) {

                        self.charges = data.data.transactionHist;
                        self.chargeCurrentPage = data.data.currentPage;
                        self.chargePageSize = data.data.pageSize;
                        self.chargeTotalCount = data.data.totalCount;
                        self.chargeTotalPages = data.data.totalPages;
                    }
                }
            });
        },
    },
    mounted: function(){
        this.getUserInfo();
        this.getDailyPrice();
        this.getMaxSell();
        this.getMaxBuy();
        this.getWallets();

        //历史交易记录获取
        this.tradeAjaxData(1);
        //今日交易记录表格
        this.todayTradeRecord();
        //充值提现记录
        this.chargeAjaxData(1)
    }
})

