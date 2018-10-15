/**
 * Created by Jayson on 2018/10/11.
 */
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
    setCookie(name,1,-1);    //-1表示昨天过期,系统自动删除
}

//导航栏 用户是否存在
if (!email || email == null){
    $("#header .nav").append("<li ><a href=\"login.html\">登录/注册</a></li>");
}else {
    $("#header .nav").append("<li ><a hidden href=\"information_user.html\" ><img href=\"blog.html\" height=\"60\" width=\"60\" src=\"images/home/heard.png\"></a></li>");
}

//退出登录
function exit() {
    removeCookie("email");
    location.href = "/pages/index.html"
}