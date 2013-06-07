$(function(){

});
function saveTopic(v){

}
function showReplyForm(v){
    $(v).closest(".-post-main").next().toggle().find("textarea").focus();
}
function saveReply(v){
    $.post(
        "/reply/save",
        $(v).parent().serialize(),
        function(data){
            if(data == "error"){
                alert("评论不能为空，且不超过200字:(");
            }else{
                var form = $(v).closest(".-post-reply-form");
                form.hide("normal");
                form.prev().find(".alert-success").show().fadeOut(5000);
                form.next().html(data).hide().fadeIn("normal");
                form.find("textarea").val("");
            }
        },
        "html"
    );
    return false;
}
function replyPaginate(v){
    $.post(
        $(v).attr("data"),
        function(data){
            $(v).closest(".-post-footer").html(data).hide().fadeIn(2000);
        },
        "html"
    );
}
function replyThisUser(v){
    var reply_form = $(v).closest(".-reply").next();
    if(reply_form.attr("data") == '0'){
        var form = $(v).closest(".-post-footer").prev();
        form.find(":submit").attr("onclick", "return saveReplyForThisUser(this)");
        reply_form.append(form.html()).attr("data", 1);
        reply_form.find("textarea").focus().val("回复@" + $(v).attr("data") + ": ");
    }else if(reply_form.attr("data") == '1'){
        reply_form.toggle().find("textarea").focus();
    }
}
function deleteReply(v){
    $.post(
        $(v).attr("data"),
        function(data){
            $(v).closest(".-post-footer").html(data).hide().fadeIn(2000);
        },
        "html"
    );
}