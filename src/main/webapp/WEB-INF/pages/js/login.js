/**
 * Created by lsb on 2018/9/8.
 */
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

            data: JSON.stringify({"user": $("#loginUser").val(), "password": $.sha1($("#loginPassword").val())}),
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

            data: JSON.stringify({"user": $("#registerUser").val(),"email": $("#registerEmail").val(),"password": $.sha1($("#registerPassword").val())}),
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

            data: JSON.stringify({"user": $("#loginUser").val(), "password": $.sha1($("#loginPassword").val())}),
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