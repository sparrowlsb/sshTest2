/**
 * Created by Jayson on 2018/10/11.
 */
//判断用户是否登录
var url=config.api_prefix+config.api_getUser;
$.ajax({
    type: 'GET',
    dataType: "json",
    contentType: "application/json;charset=utf-8",
    url: url,
    async: false,
    success: function (data, textStatus) {
        if (data.result==0){
            removeCookie("email");
            var uri = window.location.pathname;
            if (uri == "/pages/information_user.html"){
                window.location.href = '/pages/404.html';
            }
        }
    }
});
var email = getCookie("email");

function setCookie(name,value,days){    //封装一个设置cookie的函数
    var oDate=new Date();
    oDate.setDate(oDate.getDate()+days);   //days为保存时间长度
    document.cookie=name+'='+value+';expires='+oDate;
}

function getCookie(name){
    var arr=document.cookie.split(';');
    for(var i=0;i<arr.length;i++){
        var arr2=arr[i].split('=');
        if(arr2[0]==name){
            return arr2[1];  //找到所需要的信息返回出来
        }
    }
    return null;        //找不到就返回空字符串
}
function removeCookie(name){
    setCookie(name, ' ', -1);    //-1表示昨天过期,系统自动删除
}

//导航栏 用户是否存在
if (!email || email == null){
    $("#header .nav").append("<li ><a href=\"login.html\">登录/注册</a></li>");
    $("#inMoney").append("<a  href=\"login.html\" class=\"btn btn-primary\">马上充值</a>");
    $("#login").append("<a href=\"login.html\" class=\"btn1\"><i class=\"fa fa-user\"></i><span>登录/注册</span></a>");
    $("#login2").append("<a href=\"login.html\" class=\"btn2\" data-animation=\"animated bounceInUp\">登录/注册</a>");


}else {
    $("#header .nav").append("<li class='user-head'><a hidden href=\"information_user.html\" ><img href=\"blog.html\" src=\"images/home/heard.png\">&nbsp;&nbsp;&nbsp;&nbsp;"+email+"</a></li>");
    $("#inMoney").append("<a  href=\"information_money.html\" class=\"btn btn-primary\">马上充值</a>");
    $("#login").append("<a href=\"information_user.html\"><img width=\"40\" height=\"40\" src=\"images/home/heard.png\"> &nbsp;&nbsp;&nbsp;&nbsp; <font color=\"#fffffff\">"+email+"</font> </a>");
    $("#login2").append("	<h4><a >欢迎回来！</a> <a href=\"information_user.html\"> <font color=\"#fffffff\">"+email+"</font> </a></a></h4>");

}

//退出登录
function exit() {

    removeCookie("email");
    $.ajax({
        type: 'POST',
        dataType: "text",
        contentType:"application/json;charset=utf-8",
        url: "/login/loginOut",
        success: function (data, textStatus) {
            window.location.href = "/pages/index_cn.html";

        },
        error: function (data, textStatus) {

            console.log(data)

        }

    });
}


//qq 客服
$(document).ready(function(){

    /* ----- 侧边悬浮 ---- */
    $(document).on("mouseenter", ".suspension .a", function(){
        var _this = $(this);
        var s = $(".suspension");
        var isService = _this.hasClass("a-service");
        var isServicePhone = _this.hasClass("a-service-phone");
        var isQrcode = _this.hasClass("a-qrcode");
        if(isService){ s.find(".d-service").show().siblings(".d").hide();}
        if(isServicePhone){ s.find(".d-service-phone").show().siblings(".d").hide();}
        if(isQrcode){ s.find(".d-qrcode").show().siblings(".d").hide();}
    });
    $(document).on("mouseleave", ".suspension, .suspension .a-top", function(){
        $(".suspension").find(".d").hide();
    });
    $(document).on("mouseenter", ".suspension .a-top", function(){
        $(".suspension").find(".d").hide();
    });
    $(document).on("click", ".suspension .a-top", function(){
        $("html,body").animate({scrollTop: 0});
    });
    $(window).scroll(function(){
        var st = $(document).scrollTop();
        var $top = $(".suspension .a-top");
        if(st > 400){
            $top.css({display: 'block'});
        }else{
            if ($top.is(":visible")) {
                $top.hide();
            }
        }
    });

});

var qqList = [
    {qq:2763021115,nick:"24小时平台充值-super man"},
    {qq:464147349,nick:"24小时平台充值-Arthur"},
    {qq:908966762,nick:"24小时平台充值-古月HU"},
    {qq:1019074999,nick:"24小时平台充值-bobo"}
];
