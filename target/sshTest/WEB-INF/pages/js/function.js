var arry=["image1","image2","image3"];
function emojify(name) {
    var out = `<img src="images/home/` + name + `.png">`
    return out
}
function imageurl(name) {
    var out = 'images/home/' + name + '.png'
    return out
}

// cast returns a spell (function) that decorates the wizard
function cast(emoji) {
    if (!emoji) {
        emoji = "¯\\_(ツ)_/¯"
    }
    return function (wizard) {
        return emoji + wizard + emoji
    }
}

var list = new Vue({
    el: "#list",
    data: {
        image1: emojify(arry[0]),
        image2: emojify(arry[1]),
        image3: emojify(arry[2])
    }
});
var menu = new Vue({
    el: '#menu',
    data: {
        menus: {
            aboutus : 'About 1',
            aboutus2: 'About 2',
            service: 'service',
            pricing: 'pricing',
            contact: 'contact',
            contact2: 'contact2',
            aboutus2: 'aboutus2',
        }
    }
});
var program_menu = new Vue({
    el: '#program_menu',
    data: {
        program_menus: {
            blog : 'Blog Default',
            blogtwo: 'Timeline Blog',
            blogone: '2 Columns + Right Sidebar',
            blogthree: '1 Column + Left Sidebar',
            blogfour: 'Blog Masonary',
            blogdetails: 'Blog Details'
        }
    }
});
var history_menu = new Vue({
    el: '#history_menu',
    data: {
        history_menus: {
            portfolio : '历史项目'
        }
    }
});
var history_ico = new Vue({
    el: '#history_ico',
    data: {

        history_icos: {

            '1' : imageurl("client1"),
            '32': imageurl("client2"),
            '3' : imageurl("client3"),
            '4' : imageurl("client4"),
            '5' : imageurl("client5"),
            '6' : imageurl("client6"),


        }
    }
})