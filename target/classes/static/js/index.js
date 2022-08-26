var percentScroll = 0;
$(document).bind('mousewheel DOMMouseScroll', function (event) { //on也可以 bind监听
    //获取滚动条的页面百分比
    window.onscroll = function () {
        //htmlHeight 是网页的总高度
        var htmlHeight = document.documentElement.scrollHeight;
        //clientHeight是网页在浏览器中的可视高度，
        var clientHeight = document.documentElement.clientHeight;
        //scrollTop是浏览器滚动条的top位置，
        var scrollTop = document.documentElement.scrollTop;
        //通过判断滚动条的top位置与可视网页之和与整个网页的高度来返回各项参数
        percentScroll = (scrollTop / (htmlHeight - clientHeight)) * 100; //该值为滚动条的页面百分比
        if (percentScroll <= 100) {
            $('#example4')
                .progress({
                    percent: percentScroll
                });
        }
    }
});

/* 鼠标点击文字特效 */
var font = new Array("富强", "民主", "文明", "和谐", "自由", "平等", "公正", "法治", "爱国", "敬业", "诚信", "友善");
var color = new Array('#ff0000', '#eb4310', '#fbb417', '#ffff00', '#cdd541', '#99cc33', '#3f9337',
    '#219167', '#24998d', '#1f9baa', '#0080ff', '#3366cc', '#333399', '#003366', '#800080', '#a1488e',
    '#c71585', '#bd2158', '#eec142', );
var f_idx = Math.floor(Math.random() * 20) % font.length;
jQuery(document).ready(function($) {
    $("body").click(function(e) {
        var $i = $("<span />").text(font[f_idx]);
        f_idx = Math.floor(Math.random() * 20) % font.length;
        c_idx = Math.floor(Math.random() * 40) % color.length;
        var x = e.pageX,
            y = e.pageY;
        $i.css({
            "z-index": 9999999999999999999999999,
            "top": y - 20,
            "left": x,
            "position": "absolute",
            "font-weight": "bold",
            "color": color[c_idx]
        });
        $("body").append($i);
        $i.animate({
                "top": y - 180,
                "opacity": 0
            },
            1500,
            function() {
                $i.remove();
            });
    });
});


$(document).on("click", "#search_btn", function () {
    window.location.href = "/search?title=" + $.trim($("#title").val());
})
$(document).on("keyup","#Search_for_articles",function () {
    if(event.keyCode==13){
        window.location.href = "/search?title=" + $.trim($("#title").val());
    }
})

/* 鼠标蜘蛛网特效 */

/*
!
    function () {
        function n(n, e, t) {
            return n.getAttribute(e) || t
        }

        function e(n) {
            return document.getElementsByTagName(n)
        }

        function t() {
            var t = e("script"),
                o = t.length,
                i = t[o - 1];
            return {
                l: o,
                z: n(i, "zIndex", -1),
                o: n(i, "opacity", .5),
                c: n(i, "color", "0,0,0"),
                n: n(i, "count", 99)
            }
        }

        function o() {
            a = m.width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth,
                c = m.height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
        }

        function i() {
            r.clearRect(0, 0, a, c);
            var n, e, t, o, m, l;
            s.forEach(function (i, x) {
                for (i.x += i.xa, i.y += i.ya, i.xa *= i.x > a || i.x < 0 ? -1 : 1, i.ya *= i.y > c || i.y < 0 ? -1 : 1, r.fillRect(i.x - .5, i.y - .5, 1, 1), e = x + 1; e < u.length; e++) n = u[e],
                null !== n.x && null !== n.y && (o = i.x - n.x, m = i.y - n.y, l = o * o + m * m, l < n.max && (n === y && l >= n.max / 2 && (i.x -= .03 * o, i.y -= .03 * m), t = (n.max - l) / n.max, r.beginPath(), r.lineWidth = t / 2, r.strokeStyle = "rgba(" + d.c + "," + (t + .2) + ")", r.moveTo(i.x, i.y), r.lineTo(n.x, n.y), r.stroke()))
            }),
                x(i)
        }

        var a, c, u, m = document.createElement("canvas"),
            d = t(),
            l = "c_n" + d.l,
            r = m.getContext("2d"),
            x = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame ||
                function (n) {
                    window.setTimeout(n, 1e3 / 45)
                },
            w = Math.random,
            y = {
                x: null,
                y: null,
                max: 2e4
            };
        m.id = l,
            m.style.cssText = "position:fixed;top:0;left:0;z-index:" + d.z + ";opacity:" + d.o,
            e("body")[0].appendChild(m),
            o(),
            window.onresize = o,
            window.onmousemove = function (n) {
                n = n || window.event,
                    y.x = n.clientX,
                    y.y = n.clientY
            },
            window.onmouseout = function () {
                y.x = null,
                    y.y = null
            };
        for (var s = [], f = 0; d.n > f; f++) {
            var h = w() * a,
                g = w() * c,
                v = 2 * w() - 1,
                p = 2 * w() - 1;
            s.push({
                x: h,
                y: g,
                xa: v,
                ya: p,
                max: 6e3
            })
        }
        u = s.concat([y]),
            setTimeout(function () {
                    i()
                },
                100)
    }();
*/