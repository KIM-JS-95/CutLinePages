$('#save').on('click', function () {
    var data={
             title: $('#title').val(),
             content: $('#content').val(),
             link: $('#link').val()
         };

     $.ajax({
         type: 'POST',
         url: '/gallary/create',
         contentType:'application/json; charset=utf-8',
         data: JSON.stringify(data)
     }).done(function(){
      alert('저장 성공');
      window.location.href='/home/guest'
     }).fail(function (error) {
     alert(JSON.stringify(error));
     });
 });


$('#delete').on('click', function() {
if(confirm("삭제하시겠습니까?")==true){
 var id = document.getElementById("id1").innerText;
              $.ajax({
               type: 'DELETE',
               url: '/gallary/delete/'+id ,
               contentType:'application/json; charset=utf-8',
           }).done(function(){
           alert('글이 삭제되었습니다.');
            window.location.href='/gallary'
           }).fail(function (error) {
           alert(JSON.stringify(error));
     });
}
});

$('#update').on('click', function(){

    if(confirm("수정하시겠습니까?")==true){
      var id=document.getElementById("id1").innerText
    var data={
             title: $('#title').val(),
             content: $('#content').val()
         };

     $.ajax({
         type: 'PUT',
         url: '/gallary/update/' +id,
         contentType:'application/json; charset=utf-8',
         data: JSON.stringify(data)
     }).done(function(){
      alert('저장 성공');
      window.location.href='/gallary'
     }).fail(function (error) {
     alert(JSON.stringify(error));
         });
    }
});