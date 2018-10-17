/**
 * Created by Jayson on 2018/10/11.
 */
var vm = new Vue({
    el: '#user-info',
    data: {
        user: {email:"464147349@qq.com",name:"Jason"}
    },
    methods: {
        details: function() {
            return  this.site + " - 学的不仅是技术，更是梦想！";
        },
        save: function () {
            if (!user.email || !user.name){
                alert("不能为空");
                return
            }
            var url = config.api_prefix + config.api_updateUser;
            $.ajax({
                type: 'POST',
                dataType: "json",
                contentType:"application/json;charset=utf-8",
                url: url,

                data: JSON.stringify({"email": email}),
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
})