/**
 * Created by lsb on 2018/10/18.
 */

// 静态数据
var ajaxData1 = {
    "data":[
        {
            "id": "1",
            "type": "BUY",
            "createDate": "2015-03-12",
            "count": "5000rmb",
            "fund_id":1,
            "operation": "修改"
        },
        {
            "name": "baukh",
            "age": "28",
            "createDate": "2015-03-12",
            "info": "野生前端程序",
            "operation": "修改"
        },
        {
            "name": "baukh",
            "age": "28",
            "createDate": "2015-03-12",
            "info": "野生前端程序",
            "operation": "修改"
        },
        {
            "name": "baukh",
            "age": "28",
            "createDate": "2015-03-12",
            "info": "野生前端程序",
            "operation": "修改"
        },
        {
            "name": "baukh",
            "age": "28",
            "createDate": "2015-03-12",
            "info": "野生前端程序",
            "operation": "修改"
        },{
            "name": "baukh",
            "age": "28",
            "createDate": "2015-03-12",
            "info": "野生前端程序",
            "operation": "修改"
        },
        {
            "name": "baukh",
            "age": "28",
            "createDate": "2015-03-12",
            "info": "野生前端程序",
            "operation": "修改"
        },
        {
            "name": "baukh",
            "age": "28",
            "createDate": "2015-03-12",
            "info": "野生前端程序",
            "operation": "修改"
        }
    ],
    "totals": 8
};

// 实例化
var table = document.querySelector('table[grid-manager]');
function init(){
    table.GM({
        supportRemind: true
        ,height: 'auto'
        ,i18n:'zh-cn'
        ,textConfig:{
            'page-go': {
                'zh-cn':'跳转',
                'en-us':'Go '
            }
        }
        ,supportSetTop: true
        ,gridManagerName:'aaa'
        ,disableCache:false
        ,disableOrder:false
        ,supportSorting: true
        ,supportDrag:true
        ,supportAjaxPage:true
        ,emptyTemplate: '<div class="gm-emptyTemplate">什么也没有</div>'
        ,ajax_data : ajaxData1
        ,isCombSorting: false
        ,pageSize:20
        ,query: {ex: '用户自定义的查询参数,格式:{key:value}'}
        ,columnData: [{
            key: 'id',
            remind: 'the username',
            sorting: 'up',
            width: '100px',
            text: 'id'
        },{
            key: 'type',
            remind: 'the username',
            sorting: 'up',
            width: '100px',
            text: '类型'
        },{
            key: 'count',
            remind: 'the age',
            sorting: '',
            width: '100px',
            text: '交易数量'
        },{
            key: 'fund_id',
            remind: 'the createDate',
            sorting: 'down',
            width: '100px',
            text: '基金id'
        }, {
            key: 'createDate',
            remind: 'the createDate',
            sorting: 'down',
            width: '100px',
            text: '交易时间'
        }, {
            key: 'operation',
            remind: 'the operation',
            sorting: '',
            width: '100px',
            text: 'operation',
            template: function(operation, rowObject){
                //operation:当前key所对应的单条数据；rowObject：单个一行完整数据
                return '<a href=javascript:alert("这是一个按纽");>'+operation+'</a>';
            }
        }
        ]
        ,pagingBefore:function(query){
            console.log('Event: 分页前', query);
        }
        ,pagingAfter: function(query){
            console.log('Event: 分页后', query);
        }
        ,sortingBefore:function(query){
            console.log('Event: 排序前', query);
        }
        ,sortingAfter: function(query){
            console.log('Event: 排序后', query);
        }
        ,ajax_success: function(data){
            console.log('Event: ajax_success', data);
        }
    }, function(query){
        // 渲染完成后的回调函数
        console.log(query);
    });
}





// 绑定修改数据方法事件
let newData = ajaxData1;

// 初始进入时, 执行init 方法
init();

