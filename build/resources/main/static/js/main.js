
$('#save').on('click', function () {

 var data={
             userid: $('#userid').val(),
             userpw: $('#userpw').val()
         };
    $.ajax({
         type: 'POST',
         url: '/user/create',
         contentType:'application/json; charset=utf-8',
         data: JSON.stringify(data)
     }).done(function(){

     uploadFile();

      alert('저장 성공');
      window.location.href='/'
     }).fail(function (error) {
     alert(JSON.stringify(error));
         });
 });


function uploadFile() {
  $.ajax({
    url: "/uploadFile",
    type: "POST",
    data: new FormData($("#upload-file-form")[0]),
    enctype: 'multipart/form-data',
    processData: false,
    contentType: false,
    cache: false,
    success: function () {
    },
    error: function () {

    }
  });
}

$('#button').on('click', function(){
var data = {
    userid: $('#userid').val(),
    userpw: $('#userpw').val()
};

    $.ajax({
        type: 'POST',
        url: '/user/login',
        contentType:'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(function(){
      alert('로그인 했습니다.');
      window.location.href='/gallary'

    }).fail(function(error){
        alert("아이디가 존재하지 않습니다.");
    });
});

