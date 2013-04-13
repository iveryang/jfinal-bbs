//////////////////////////////////////////// 以下是jquery插件:jquery.cookie.js ////////////////////////////////////////////////
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        define(['jquery'], factory);
    } else {
        factory(jQuery);
    }
}(function ($) {
    var pluses = /\+/g;
    function raw(s) {
        return s;
    }
    function decoded(s) {
        return decodeURIComponent(s.replace(pluses, ' '));
    }
    function converted(s) {
        if (s.indexOf('"') === 0) {
            s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
        }
        try {
            return config.json ? JSON.parse(s) : s;
        } catch(er) {}
    }
    var config = $.cookie = function (key, value, options) {
        if (value !== undefined) {
            options = $.extend({}, config.defaults, options);
            if (typeof options.expires === 'number') {
                var days = options.expires, t = options.expires = new Date();
                t.setDate(t.getDate() + days);
            }
            value = config.json ? JSON.stringify(value) : String(value);
            return (document.cookie = [
                config.raw ? key : encodeURIComponent(key),
                '=',
                config.raw ? value : encodeURIComponent(value),
                options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
                options.path    ? '; path=' + options.path : '',
                options.domain  ? '; domain=' + options.domain : '',
                options.secure  ? '; secure' : ''
            ].join(''));
        }
        var decode = config.raw ? raw : decoded;
        var cookies = document.cookie.split('; ');
        var result = key ? undefined : {};
        for (var i = 0, l = cookies.length; i < l; i++) {
            var parts = cookies[i].split('=');
            var name = decode(parts.shift());
            var cookie = decode(parts.join('='));
            if (key && key === name) {
                result = converted(cookie);
                break;
            }
            if (!key) {
                result[name] = converted(cookie);
            }
        }
        return result;
    };
    config.defaults = {};
    $.removeCookie = function (key, options) {
        if ($.cookie(key) !== undefined) {
            $.cookie(key, '', $.extend({}, options, { expires: -1 }));
            return true;
        }
        return false;
    };
}));
//////////////////////////////////////////// 以下是统计用的js ////////////////////////////////////////////////
//GA
(function (i, s, o, g, r, a, m) { i['GoogleAnalyticsObject'] = r;
    i[r] = i[r] || function () {
        (i[r].q = i[r].q || []).push(arguments)
    }, i[r].l = 1 * new Date();
    a = s.createElement(o),
        m = s.getElementsByTagName(o)[0];
    a.async = 1;
    a.src = g;
    m.parentNode.insertBefore(a, m)
})(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');
ga('create', 'UA-39800051-1', 'cloudfoundry.com');
ga('send', 'pageview');
//Bai Du Tong Ji
var _hmt = _hmt || [];
(function () {
    var hm = document.createElement("script");
    hm.src = "//hm.baidu.com/hm.js?e72171b21ce3178036d1361f5812d3c5";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();
//////////////////////////////////////////// 以下是自己写的js ////////////////////////////////////////////////
$(function(){
    $(window).bind("scroll", function(){
        fix_nav_bar();
    });
});
function _reply(v){
    $(v).parent().next().toggle();
}
function _ajax_reply_save(v){
    var name = $(v).parent().find("[name='reply.userName']").val().trim();
    var content = $(v).parent().find("textarea").val().trim();
    if(name == ''){ alert("昵称不能为空：)"); return false; }
    if(name.length > 10){ alert("昵称长度不能超过10个字：)"); return false; }
    if(content == ''){ alert("回复内容不能为空：)"); return false; }
    if(content.length > 200){ alert("回复内容不能超过200字：)");return false; }
    $.post(
        "/reply/save",
        $(v).parent().serialize(),
        function(data){
            if(data == "error"){
                alert("你输入的不科学~！");
            }else{
                $(v).parent().hide("normal");
                var post_content_footer = $(v).closest("._post_content_footer");
                post_content_footer.find("p:eq(0) span:eq(0) v").show().fadeOut(5000);
                post_content_footer.next().html(data).hide().fadeIn("normal");
                $(v).parent().find("textarea").val("");
            }
        },
        "html"
    );
    return false;
}
function _ajax_reply_paginate(v){
    $.post(
        $(v).attr("data"),
        function(data){
            $(v).closest("._topic_post_info").html(data).hide().fadeIn("normal");
        },
        "html"
    );
}
function _ajax_reply_delete(v){
    $.post(
        $(v).attr("data"),
        function(data){
            $(v).closest("._topic_post_info").html(data).hide().fadeIn("normal");
        },
        "html"
    );
}
function fix_nav_bar(){
    var title = $("._title");
    var scroll = $(window).scrollTop();
    if(scroll > 60){
        title.addClass("_title_fixed");
    }else{
        title.removeClass("_title_fixed");
    }
}