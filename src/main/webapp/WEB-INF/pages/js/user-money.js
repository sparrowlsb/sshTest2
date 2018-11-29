/**
 * Created by Jayson on 2018/10/11.
 */
var vm = new Vue({
    el: '#money-record',
    data: {
        moneyRecord: [
                   ],
        wallets:[{type:"FUND_1",money:"0"}],
        USDT:{type:"usdt",money:"0"},
        currentPage:0,
        pageSize:0,
        totalCount:0,
        totalPages:0
    },
    methods: {
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
        getExchangeHist: function (page) {
            var self = this;
            $.ajax({
                type: 'GET',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: "/curb/getHist?page="+page,
                success: function (data, textStatus) {
                    console.log(data.data)
                    if (data.result == 1) {

                        self.moneyRecord = data.data.transactionHist;
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
        this.getWallets();
        this.getExchangeHist(1)
    },


});
var sellMoney=$("#sellMoney").val();
function sellUSDT() {
    $.ajax({
        type: "POST",   //提交的方法
        dataType: "json",
        contentType:"application/json;charset=utf-8",
        url:"/curb/sellMoney/", //提交的地址
        data:JSON.stringify({"sellMoney": $("#sellMoney").val()}),

        error: function(request) {  //失败的话
            alert("Connection error");sellUSDT
        },
        success: function(data) {  //成功
            if(data.result==1){
                alert("提现成功，联系兑换商提现！")
                window.location.reload()
            }else if(data.result==0){
                alert("提现失败，确认提现额度！");


            }

        }
    });


};
