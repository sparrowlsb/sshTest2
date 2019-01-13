


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
                url: "/curb/getHist?page="+page,
                success: function (data, textStatus) {
                    console.log(data.data)
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
        wallets: [],
        qqList: qqList
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
var myWallet2 = new Vue({
    el: '#myWallet2',

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

var m = new Vue({
    el: '#main',
    data: {
        status:0,
        fundId: 1,
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
        wallets:[{type:"请先登录",money:""},{type:"请先登录",money:""},{type:"请先登录",money:""},{type:"请先登录",money:""}],
        USDT:{type:"usdt",money:"0"},
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
        getDailyPrice: function (fundId) {
            var self = this;
            $.ajax({
                type: 'GET',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: "/fund/fundDailyPrice",
                success: function (data, textStatus) {
                    if (data.result == 1){
                        self.fund = data.data.fund1
                        self.fund2 = data.data.fund2
                        self.fund3 = data.data.fund3
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
                    console.log(data.data)
                    if (data.result == 1) {
                        self.charges = data.data.transactionHist;
                    }
                }
            });
        },
        sellUSDT: function() {
            if (confirm("确定要提现usdt？")) {
                var money = $("#sellMoney").val();
                if (!money||money < 0 || money > this.USDT.money) {
                    alert("提现金额不能为0或者超过最大提现金额！");
                    return
                }
                $.ajax({
                    type: "POST",   //提交的方法
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    url: "/curb/sellMoney/", //提交的地址
                    data: JSON.stringify({"sellMoney": money}),

                    error: function (request) {  //失败的话

                    },
                    success: function (data) {  //成功

                        if (data.result == 1) {
                            alert("提现成功，联系兑换商提现！")
                            window.location.reload()
                        } else if (data.result == 0) {

                            if (data.message == "Please confirm the real-name authentication first")
                                alert("提现失败，请先到个人中心进行实名认证！");
                            else if (data.message == "please sell >0 money")
                                alert("提现失败，提现金额需要大于0usdt！");
                            else if (data.message == "total money not enough")
                                alert("提现失败，钱包usdt金额不足");
                            else if (data.message=="Successful completion of a fund transaction before withdrawal"){
                                alert("提现失败，成功完成一次基金交易才能提现");
                            }
                            else if (data.message=="the minimum trader money 10 USDT"){
                                alert("提现失败，最小提现金额 10USDT");
                            }
                            else
                                alert("提现失败，需要先登录用户");

                        }

                    }
                });
            }
        },
        buyUSDT: function() {
            if (confirm("确定要充值usdt？")) {

                var money = $("#buyMoney").val();
                if (!money || money <= 0) {
                    alert("充值金额不能为空或者为0！");
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

                            if (data.message == "Please confirm the real-name authentication first")
                                alert("充值失败，请先到个人中心进行实名认证！");
                            else
                                alert("充值失败，确认是否登录！");


                        }

                    }
                });
            }
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
                        console.log(self.wallets)
                        self.USDT = data.data.fundsDetails[0];

                    }
                }
            });
        },

    },
    mounted: function(){
        this.getUserInfo();
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
    url: "/curb/getHist",

    success: function (data, textStatus) {
        buyDataSet=data.data.transactionHist;
        console.log(buyDataSet)
        $('#buytable').DataTable( {
            searching:false,
            'order' : [5,'desc'],
            data: buyDataSet,
            columns: [
                {title: "订单号",data:"id"},
                {
                    title: "类型",
                    data:"exchangeType",
                    render: function(data,type,row,meta){
                        if (data=="提现"){
                            return '<span style="color:red">'+data+'</span>';
                        }else {
                            return '<span style="color:green">'+data+'</span>';
                        }
                    }
                },
                {title: "交易方式",data:"exchangePlatform"},
                {title: "交易金额",data:"money"},
                {title: "订单状态",data:"status"},
                {title: "交易时间",data:"exchangeDate"}
            ],
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
    }
})

