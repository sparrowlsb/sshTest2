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


window.onload = function(){
    $('#selltable').DataTable();
    $('#buytable').DataTable();
}