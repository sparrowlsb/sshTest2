/**
 * Created by lsb on 2018/11/1.
 */
function buyfunction() {
    if (confirm("确定要购买该基金？")) {
        var buyNum = $("#buyNum").val();
        if (buyNum != "" && buyNum > 0) {
            var url = config.api_prefix + config.api_buyFund;
            $.ajax({
                type: 'POST',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: url,

                data: JSON.stringify({"traderMoney": buyNum, "fundId": 1}),
                success: function (data, textStatus) {
                    if (data.result == 1) {
                        alert('交易成功');
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
        }
    }
}
function sellfunction() {
    if (confirm("确定要卖出该基金？")) {
        var sellNum = $("#sellNum").val();
        if (sellNum != "" && sellNum > 0) {
            var url = config.api_prefix + config.api_sellFund;
            $.ajax({
                type: 'POST',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: url,

                data: JSON.stringify({"fundCount": sellNum, "fundId": 1}),
                success: function (data, textStatus) {
                    if (data.result == 1) {
                        alert('交易成功');
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