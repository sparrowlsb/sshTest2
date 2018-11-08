/**
 * Created by Jayson on 2018/10/11.
 */
var vm = new Vue({
    el: '#money-record',
    data: {
        moneyRecord: [
            {time:"2018-9-18 20:20:09",money:"1000",status:"成功",type:"提现",account:"hay868@qq.com",accountType:"支付宝"},
            {time:"2018-9-18 18:20:09",money:"1000",status:"正在进行...",type:"充值"},
            {time:"2018-9-18 12:20:09",money:"1000",status:"成功",type:"充值",account:"hay868@qq.com",accountType:"支付宝"},
        ],
        wallets:[{type:"FUND_1",money:"100"}]
    },
    methods: {
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
                    }
                }
            });
        }
    },
    mounted: function(){
        this.getWallets()
    }
})