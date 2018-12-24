/**
 * Created by Jayson on 2018/10/11.
 */
var idCardOn="";
var idCardUnder="";


var main = new Vue({
    el: '#userinfo',
    data: {

        user: {}

    },
    mounted: function(){

        this.getInfo();

    },
    methods: {
        getInfo: function () {
            var self = this;
            var url =config.api_prefix+config.api_getInfo;
            $.ajax({
                type: 'GET',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: url,
                success: function (data, textStatus) {
                    if (data.result == 1){
                        self.user = data.data.userInfo
                        console.log(self.user)
                    }
                }
            });
        },
        updateInfo: function () {

            var name=document.getElementById('name').value;
            var idCard=document.getElementById('id').value;
            var usdtAddress=document.getElementById('usdtAddress').value;
            if (!name || !idCard||!idCardOn||!idCardUnder||!usdtAddress){
                alert("不能为空");
                return
            }
            var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
            if(reg.test(idCard) === false)
            {
                alert("身份证输入不合法");
                return  false;
            }
            //上传图片
            var retImgUrl1,retImgUrl2;
            var uploadStatus = true;
            var url =config.api_prefix+config.api_uploadImage;
            //图片1
            var formData = new FormData();
            formData.append('image', $('#image1')[0].files[0]);
            $.ajax({
                url: url,
                type: 'POST',
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                async: false,
                success: function (data) {
                    if (data.result == 1){
                        retImgUrl1 = data.url;
                    }else{
                        alert(data.message);
                        uploadStatus = false
                    }
                }
            });
            //图片2
            formData = new FormData();
            formData.append('image', $('#image2')[0].files[0]);
            $.ajax({
                url: url,
                type: 'POST',
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                async: false,
                success: function (data) {
                    if (data.result == 1){
                        retImgUrl2 = data.url;
                        console.log(retImgUrl2)
                    }else{
                        alert(data.message);
                        uploadStatus = false
                        console.log(retImgUrl2)
                    }
                }
            });
            if (!uploadStatus){
                return;
            }

            //更新用户信息
            url =config.api_prefix+config.api_updateUserInfo;
            $.ajax({
                type: 'POST',
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                url: url,
                data: JSON.stringify({"name": name,"personCode": idCard,"idCardOn": retImgUrl1,"idCardUnder": retImgUrl2,"usdtAddress": usdtAddress}),
                success: function (data, textStatus) {
                    if (data.result == 1){
                        alert("成功更新用户信息，等待审核确认。。。。");
                        location.reload()
                    }else{
                        alert(data.message)
                    }
                }
            });
        }
    },

})



var imgFile = new ImgUploadeFiles('.box',function(e){
    this.init({
        TYPE:1,
        MAX : 1, //限制个数
        MH : 5800, //像素限制高度
        MW : 5900, //像素限制宽度
        callback : function(arr){

            idCardOn=arr;
        }
    });
});
var imgFile1 = new ImgUploadeFiles('.box2',function(e){
    this.init({
        TYPE:2,
        MAX : 1,
        MH : 5800, //像素限制高度
        MW : 5900, //像素限制宽度
        callback : function(arr){
            idCardUnder=arr;
        }
    });
});

var timer = setInterval("uploadImages()",1000);//1000为1秒钟

function uploadImages()
{
    if(idCardOn!=""&&idCardUnder!=""){
        clearInterval(timer);

    }
}
