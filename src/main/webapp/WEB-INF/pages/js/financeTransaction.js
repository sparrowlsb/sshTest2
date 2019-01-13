/**
 * Created by lsb on 2018/11/1.
 */
function buyfunction(fundId) {
    if (confirm("确定要购买该基金？")) {
        var buyNum = $("#buyNum").val();
        if (buyNum != "" && buyNum >= 0) {
            var url = config.api_prefix + config.api_buyFund;
            $.ajax({
                type: 'POST',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: url,

                data: JSON.stringify({"traderMoney": buyNum, "fundId": fundId}),
                success: function (data, textStatus) {
                    if (data.result == 1) {
                        alert('交易成功');

                        window.location.reload()
                    }else {
                        if (data.message=="the minimum trader money 10 USDT"){
                            alert("交易失败，最小买入金额 10USDT")
                        }
                        else if (data.message=="please entry the trader money"){
                            alert("交易失败，输入大于0的交易金额")
                        } else if (data.message=="please entry the right transaction money"){
                            alert("交易失败，交易金额不足")
                        }

                    }
                    var table = $('#buytable').DataTable();
                    table.draw(false);

                },
                error: function (data, textStatus) {
                    alert("交易失败");
                    console.log(data)

                }

            });
        }

    }
}
function sellfunction(fundId) {
    if (confirm("确定要卖出该基金？")) {
        var sellNum = $("#sellNum").val();
        if (sellNum != "" && sellNum > 0) {
            var url = config.api_prefix + config.api_sellFund;
            $.ajax({
                type: 'POST',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: url,

                data: JSON.stringify({"fundCount": sellNum, "fundId": fundId}),
                success: function (data, textStatus) {
                    if (data.result == 1) {
                        alert('交易成功');
                        window.location.reload()
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
}