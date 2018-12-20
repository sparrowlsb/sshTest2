/**
 * Created by Jayson on 2018/10/17.
 */

var url=config.api_prefix+config.api_getUser;
$.ajax({

    type: 'GET',
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    url: url,
    success: function (data, textStatus) {
        if (data.result==1){

        }
        else if (data.result==0){
            window.location.href = '/pages/404.html';
        }

    }
});
var count = 60; //间隔函数，1秒执行
var InterValObj1; //timer变量，控制时间
var curCount1;//当前剩余秒数
function sendSMS() {
    var

        curCount1 = count;
    if (!email || email == null) {
        alert("请重新登陆");
        return;
    }
    var url = config.api_prefix + config.api_sendEmailVerify;
    $.ajax({
        type: 'POST',
        dataType: "json",
        contentType:"application/json;charset=utf-8",
        url: url,
        data: JSON.stringify({address: email}),
        success: function (data) {
            // if(data=="success"){
            alert("发送成功！请登陆邮箱查看验证码！");
            // }else if(data=="error"){
            //     alert("发送失败！");
            // }
        },
        error: function (data) {
            alert("error");
            console.log(data)
        }
    });

    //设置button效果，开始计时
    $("#btnSendCode1").attr("disabled", "true");
    $("#btnSendCode1").html( + curCount1 + "秒再获取");
    InterValObj1 = window.setInterval(SetRemainTime1, 1000); //启动计时器，1秒执行一次
    //向后台发送处理数据

}


function SetRemainTime1() {
    if (curCount1 == 0) {
        window.clearInterval(InterValObj1);//停止计时器
        $("#btnSendCode1").removeAttr("disabled");//启用按钮
        $("#btnSendCode1").html("重新发送");
    }
    else {
        curCount1--;
        $("#btnSendCode1").html( + curCount1 + "秒再获取");
    }
}

function save() {
    if ($("#tab1-item1").hasClass("active")){
        //change login password
        var email = getCookie("email");
        var pwd = $("input[name='loginPwd']").val();
        if (pwd.length < 6){
            alert("密码不能小于6位");
            return
        }
        var code = $("input[name='code']").val();
        if (!email || !pwd || !code){
            alert("不能为空");
            return
        }
        var url = config.api_prefix + config.api_changePwd;
        $.ajax({
            type: 'POST',
            dataType: "json",
            contentType:"application/json;charset=utf-8",
            url: url,

            data: JSON.stringify({"verEmailCode": code,"email": email,"password": $.md5(pwd)}),
            success: function (data) {
                if(data.result==1){
                    alert("修改成功！");
                    window.location.reload();
                }else if(data.result==0){
                    alert(data.message);
                }
            },
            error: function (data, textStatus) {
                alert("error");
                console.log(data)
            }
        });
    }else {
        //change pay password
        var email = getCookie("email");
        var pwd = $("input[name='payPwd']").val();
        if (pwd.length < 6){
            alert("密码不能小于6位");
            return
        }
        var code = $("input[name='code']").val();
        if (!email || !pwd || !code){
            alert("不能为空");
            return
        }
        var url = config.api_prefix + config.api_changeFinaPwd;
        $.ajax({
            type: 'POST',
            dataType: "json",
            contentType:"application/json;charset=utf-8",
            url: url,

            data: JSON.stringify({"verEmailCode": code,"email": email,"passwordFinance": $.md5(pwd)}),
            success: function (data) {
                if(data.result==1){
                    alert("修改成功！");
                    window.location.reload();
                }else if(data.result==0){
                    alert(data.message);
                }
            },
            error: function (data, textStatus) {
                alert("error");
                console.log(data)
            }
        });
    }
}