/**
 * Created by lsb on 2018/9/8.
 */

$("#emalfind").attr("style","display:none;");


var emailReg = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,5}$/


var count = 60; //间隔函数，1秒执行
var InterValObj1; //timer变量，控制时间
var curCount1;//当前剩余秒数

function sendSMS() {
    var code = $('#regCode').val();
    if (!code){
        alert('请输入验证码');
        return;
    }
    curCount1 = count;
    var mail = $.trim($('#regEmail').val());
    if (!emailReg.test(mail )) {
        alert(" 请输入有效的邮箱号");
        return;
    }
    var url = config.api_prefix + config.api_sendEmailVerify;
    $.ajax({
        type: 'POST',
        dataType: "json",
        contentType:"application/json;charset=utf-8",
        url: url,
        data: JSON.stringify({address: mail}),
        success: function (data) {
            // if(data=="success"){
                alert("发送成功！请登录邮箱查看验证码！");
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


//按钮单击时执行
function  loginUser() {
    var email=$("#loginEmail").val();
    var loginCode=$("#loginCode").val();
    var loginPwd=$("#loginPwd").val();

    if(email==""||regPwd==""||regCode==""){
        alert("请输入完整信息！");
    } else {
        //取Ajax返回结果
        //为了简单，这里简单地从文件中读取内容作为返回数据
        var url = config.api_prefix + config.api_login;
        $.ajax({
            type: 'POST',
            dataType: "json",
            contentType:"application/json;charset=utf-8",
            url: url,

            data: JSON.stringify({"verCode": loginCode,"email": email,"password": $.md5(loginPwd)}),
            success: function (data) {
                if(data.result==1){
                    // alert("登录成功！");
                    //set cookie
                    setCookie('email',email,1);
                    window.location.href="/pages/index_cn.html";
                }else if(data.result==0){
                    if(data.message=="the vercode not get") {
                        alert("请输入验证码！");
                    }else if(data.message=="the vercode not right"){
                        alert("验证码不正确！");
                    }else if(data.message=="error"){
                        alert("账号密码错误！");
                    }
                    verifyClick()
                }
            },
            error: function (data, textStatus) {
                alert("error");
                verifyClick()
                console.log(data)
            }

        });
    }

};

function checkEmailExists() {
    var email=$("#regEmail").val();
    if (email!=""){
        var url = config.api_prefix + config.api_checkEmail;
        $.ajax({
            type: 'POST',
            dataType: "json",
            contentType:"application/json;charset=utf-8",
            url: url,

            data: JSON.stringify({"email": email}),
            success: function (data, textStatus) {
                if(data.result==1){
                    $("#regEmail").removeClass('input-error');
                }else if(data.result==0){
                    $("#regEmail").addClass('input-error');
                    // alert(data.message)
                }
            },
            error: function (data, textStatus) {
                alert("error");
                console.log(data)

            }

        });
    }
}

function registerUser() {

    var email=$("#regEmail").val();
    var regCode=$("#regCode").val();
    var regECode=$("#regECode").val();
    var regPwd=$("#regPwd").val();
    var regRPwd=$("#regRPwd").val();
    var emailPatt=new RegExp(/^\w+@\w+\.[a-zA-Z]+(\.[a-zA-Z]+)?$/);
    if(email==""||regPwd==""||regRPwd==""||regCode==""||regECode==""){
        alert("请输入完整信息！");
    }else if(!emailPatt.test(email)){
        alert("填写邮箱格式不正确！");
    }else if(regPwd.length < 6){
        alert("密码必须大于6位！");
    }else if(regPwd!=regRPwd){
        alert("密码输入不一致！");
    }else{
        //取Ajax返回结果
        //为了简单，这里简单地从文件中读取内容作为返回数据
        var url = config.api_prefix + config.api_regist;
        $.ajax({
            type: 'POST',
            dataType: "json",
            contentType:"application/json;charset=utf-8",
            url: url,

            data: JSON.stringify({"verEmailCode": regECode,"verCode": regCode,"email": email,"password": $.md5(regPwd)}),
            success: function (data, textStatus) {
                if(data.result==1){
                    alert("注册成功！");
                    //set cookie
                    setCookie('email',email,1);
                    window.location.href="/pages/index_cn.html";
                }else if(data.result==0){
                    if(data.message=="the vercode not get") {
                        alert("请输入验证码！");
                    }else if(data.message=="the verCode not right"){
                        alert("验证码不正确！");
                    }else if(data.message=="the verEmailCode not get"){
                        alert("请发送邮箱验证码！");
                    }else if(data.message=="error email"){
                        alert("邮箱地址不正确！");
                    }else if(data.message=="the verEmailCode not right"){
                        alert("邮箱验证码不正确！");
                    }else if(data.message=="emailExistence"){
                        alert("邮箱已被注册！");
                    }
                    verifyClick()
                }

            },
            error: function (data, textStatus) {

                verifyClick()

            }

        });
    }

    $("#reg_box").find('input[type="text"], input[type="password"], textarea').each(function(){
        if( $(this).val() == "" ) {
            e.preventDefault();
            $(this).addClass('input-error');
        }
        else {
            $(this).removeClass('input-error');
        }
    });

};
function forgetpassword() {

    var user=$("#loginUser").val();
    var password=$("#loginPassword").val();
    var userPatt=new RegExp(/^\w{3,16}$/);
    var passwordPatt=new RegExp(/^\w+/);

    console.log(userPatt.test(user)+"--"+passwordPatt.test(password));
    if(userPatt.test(user)&&passwordPatt.test(password)) {
        //取Ajax返回结果
        //为了简单，这里简单地从文件中读取内容作为返回数据
        $.ajax({
            type: 'POST',
            dataType: "text",
            contentType:"application/json;charset=utf-8",
            url: "/loginByPassword",

            data: JSON.stringify({"user": user, "password": password}),
            success: function (data, textStatus) {
                if(data=="success"){
                    window.location.href="/index_cn";

                }else if(data=="error"){
                    alert("账户密码错误！");
                    window.location.href="/pages/login.html";
                }
            },
            error: function (data, textStatus) {
                alert("error");
                console.log(data)

            }

        });
    }
    if(userPatt.test(user)&&!passwordPatt.test(password)){
        alert("请输入正确密码！");
    }
    if(!userPatt.test(user)&&passwordPatt.test(password)){
        alert("请输入正确账号！");
    }
}

//点击刷新验证码图片
function verifyClick() {
    var time = new Date().getTime();
    var url = config.api_prefix + config.api_getVerifyImg + "?" + time;
    $('.verifyImg').attr("src",url);
}

//页面加载
jQuery(document).ready(function() {
    //验证码
    verifyClick();

    /*
     Fullscreen background
     */
    $.backstretch("assets/img/backgrounds/1.jpg");

    $('#top-navbar-1').on('shown.bs.collapse', function(){
        $.backstretch("resize");
    });
    $('#top-navbar-1').on('hidden.bs.collapse', function(){
        $.backstretch("resize");
    });

    /*
     Form validation
     */
    $('.form-bottom input[type="text"], .registration-form input[type="password"], .registration-form textarea').on('focus', function() {
        $(this).removeClass('input-error');
    });

    $('.form-bottom').on('submit', function(e) {

        $(this).find('input[type="text"], input[type="password"], textarea').each(function(){
            if( $(this).val() == "" ) {
                e.preventDefault();
                $(this).addClass('input-error');
            }
            else {
                $(this).removeClass('input-error');
            }
        });

    });


});

function change(type){
    if (type == 'login'){
        $('#reg_box').fadeOut().delay(1000);
        $('#forget_box').fadeOut().delay(1000);
        setTimeout(function () {
            $('#login_box').fadeIn();
        },500)

    }else if (type == 'reg') {
        $('#login_box').fadeOut();
        $('#forget_box').fadeOut();
        setTimeout(function () {
            $('#reg_box').fadeIn();
        },500)

    }else if (type == 'forget') {
        $('#login_box').fadeOut();
        $('#reg_box').fadeOut();
        setTimeout(function () {
            $('#forget_box').fadeIn();
        },500)

    }
}

function forgetPWD() {
    //change login password
    var email = $("input[name='forgetEmail']").val();
    var pwd = $("input[name='forgetPwd']").val();
    var emailPatt=new RegExp(/^\w+@\w+\.[a-zA-Z]+(\.[a-zA-Z]+)?$/);
    if (!emailPatt.test(email)){
        alert("填写邮箱格式不正确！");
        return
    }
    if (pwd.length < 6){
        alert("密码不能小于6位");
        return
    }
    var code = $("input[name='forgetECode']").val();
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
}

var InterValObj2; //timer变量，控制时间
var curCount2;//当前剩余秒数
function sendSMS1() {
    var email = $("input[name='forgetEmail']").val();
    var emailPatt=new RegExp(/^\w+@\w+\.[a-zA-Z]+(\.[a-zA-Z]+)?$/);
    curCount2 = count;
    if (!emailPatt.test(email )) {
        alert(" 请输入有效的邮箱号");
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
            alert("发送成功！请登录邮箱查看验证码！");
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
    $("#btnSendCode2").attr("disabled", "true");
    $("#btnSendCode2").html( + curCount2 + "秒再获取");
    InterValObj2 = window.setInterval(SetRemainTime2, 1000); //启动计时器，1秒执行一次
    //向后台发送处理数据

}
function SetRemainTime2() {
    if (curCount1 == 0) {
        window.clearInterval(InterValObj2);//停止计时器
        $("#btnSendCode2").removeAttr("disabled");//启用按钮
        $("#btnSendCode2").html("重新发送");
    }
    else {
        curCount2--;
        $("#btnSendCode2").html( + curCount2 + "秒再获取");
    }
}