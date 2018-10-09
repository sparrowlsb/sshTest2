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


//按钮单击时执行
function  loginUser() {

    var user=$("#loginUser").val();
    var password=$("#loginPassword").val();
    var userPatt=new RegExp(/^\w{3,16}$/);
    var passwordPatt=new RegExp(/^\w+/);

    console.log(userPatt.test(user)+"--"+passwordPatt.test(password));
    if(user==""&&password==""){
        alert("填写完整登录信息！");
    } else if(userPatt.test(user)&&!passwordPatt.test(password)){
        alert("请输入正确格式密码！");
    } else if(!userPatt.test(user)&&passwordPatt.test(password)){
        alert("请输入正确格式账号！");
    } else if(userPatt.test(user)&&passwordPatt.test(password)) {
        //取Ajax返回结果
        //为了简单，这里简单地从文件中读取内容作为返回数据
        var url = config.api_prefix + config.api_login;
        $.ajax({
            type: 'POST',
            dataType: "text",
            contentType:"application/json;charset=utf-8",
            url: url,

            data: JSON.stringify({"user": user, "password": password}),
            success: function (data, textStatus) {
                if(data=="success"){
                    alert("登录成功！");
                    window.location.href="/index";

                }else if(data=="error"){
                    alert("登录失败：账户密码错误！");
                    window.location.href="/pages/login.html";
                }
            },
            error: function (data, textStatus) {
                alert("error");
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
                    alert(data.message)
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
                    window.location.href="/index";
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
                    window.location.href="/index";

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
        setTimeout(function () {
            $('#login_box').fadeIn();
        },500)

    }else {
        $('#login_box').fadeOut();
        setTimeout(function () {
            $('#reg_box').fadeIn();
        },500)

    }
}