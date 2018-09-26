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
        data: mail,
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
        $.ajax({
            type: 'POST',
            dataType: "text",
            contentType:"application/json;charset=utf-8",
            url: "/loginByPassword",

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
function registerUser() {

    alert("657657！");
    var user=$("#registerUser").val();
    var email=$("#registerEmail").val();
    var password=$("#registerPassword").val();
    var password2=$("#registerPassword2").val();
    var userPatt=new RegExp(/^\w{3,16}$/);
    var passwordPatt=new RegExp(/^\w+/);
    var emailPatt=new RegExp(/^\w+@\w+\.[a-zA-Z]+(\.[a-zA-Z]+)?$/);
    console.log(userPatt.test(user)+"--"+passwordPatt.test(password)+"--"+emailPatt.test(email));
    if(user==""||email==""||password==""){
        alert("请输入完整信息！");
    }else if(!userPatt.test(user)||!passwordPatt.test(password)||!emailPatt.test(email)){
        alert("填写格式不正确！");
    }else if(password!=password2){
        alert("密码输入不一致！");
    }else if(userPatt.test(user)&&passwordPatt.test(password)&&emailPatt.test(email)) {
        //取Ajax返回结果
        //为了简单，这里简单地从文件中读取内容作为返回数据
        $.ajax({
            type: 'POST',
            dataType: "text",
            contentType:"application/json;charset=utf-8",
            url: "/registerUser",

            data: JSON.stringify({"user": user,"email": email,"password": password}),
            success: function (data, textStatus) {
                if(data=="success"){
                    alert("注册成功！");
                    window.location.href="/index";
                }else if(data=="emailerror"){
                    alert("注册失败：存在已有的email！");
                    window.location.href="/pages/login.html";
                }else if(data=="usernameerror"){
                    alert("注册失败：存在已有的username！");
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
    $('.registration-form input[type="text"], .registration-form input[type="password"], .registration-form textarea').on('focus', function() {
        $(this).removeClass('input-error');
    });

    $('.registration-form').on('submit', function(e) {

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