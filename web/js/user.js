$(function(){

});
function saveTopic(v){

}
function showReplyForm(v){
    $(v).parent().next().toggle();
}
function saveReply(v){
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
function replyPaginate(v){
    $.post(
        $(v).attr("data"),
        function(data){
            $(v).closest("._topic_post_info").html(data).hide().fadeIn("normal");
        },
        "html"
    );
}
function deleteReply(v){
    $.post(
        $(v).attr("data"),
        function(data){
            $(v).closest("._topic_post_info").html(data).hide().fadeIn("normal");
        },
        "html"
    );
}