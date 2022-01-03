
$('#replybutton').on('click', function(){

var id = document.getElementById("id1").innerText;

var data={
    comment: $('#replycreate').val()
    };

$.ajax({
    type: 'POST',
    url: '/gallary/reply/' + id,
    contentType:'application/json; charset=utf-8',
    data: JSON.stringify(data)
}).done(function(){
        alert('저장 성공');
        window.location.reload();
       }).fail(function (error) {
       alert(JSON.stringify(error));
     });
});

