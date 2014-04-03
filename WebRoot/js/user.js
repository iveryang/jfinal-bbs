$(function(){

});
function saveReply(v){
    $.post(
        $(v).attr("data"),
        $(v).parent().serialize(),
        function(data){
            if(data == "error"){
                alert("评论不能为空，且不超过200字 :(");
            }else{
                var footer = $(v).closest(".-post-footer");
                footer.children(".-reply-list").html(data).hide().fadeIn("normal");
                footer.find("textarea").val("");
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
            $(v).closest(".-reply-list").html(data).hide().fadeIn(2000);
        },
        "html"
    );
}
function replyThisUser(v){
    var footer = $(v).closest(".-post-footer").find("textarea").focus().val("回复 @" + $(v).attr("data") + " ");
}
function login(v){
    var passwordInput = $(v).closest("form").find("input[name='password']");
    passwordInput.val(md5(passwordInput.val()));
    return true;
}