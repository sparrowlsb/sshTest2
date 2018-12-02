/**
 * Created by lsb on 2018/11/25.
 */

var main = new Vue({
    el: '#main',
    data: {
        fundId: 1,
        fund: {},
        fund2: {},
        fund3: {},
        maxBuy: 0,
        maxSell: 0
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
        }
    },
    mounted: function(){
        this.getDailyPrice(this.fundId);
        this.getMaxSell(this.fundId);
        this.getMaxBuy();
    }
})