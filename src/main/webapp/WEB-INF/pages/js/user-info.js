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
        }
    }
})

