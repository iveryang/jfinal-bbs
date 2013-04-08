function _reply(v){
    $(v).parent().next().toggle("normal");
}
function _ajax_reply_save(v){
    var name = $(v).parent().find("[name='reply.userName']").val().trim();
    var content = $(v).parent().find("textarea").val().trim();
    if(name == ''){
        alert("昵称不能为空：)"); return false;
    }
    if(name.length > 10){
        alert("昵称长度不能超过10个字：)"); return false;
    }
    if(content == ''){
        alert("回复内容不能为空：)"); return false;
    }
    if(content.length > 200){
        alert("回复内容不能超过200字：)");return false;
    }
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