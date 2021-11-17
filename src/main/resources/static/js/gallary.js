$('#save').on('click', function () {
    var data={
             title: $('#title').val(),
             content: $('#content').val(),
             link: $('#link').val()
         };

         var form =$('#form')[0];
         var formData = new FormData(form);
         formData.append('file', $('#file'));
         formData.append('key', new Blob([JSON.stringify(data)] ,
         {type: "application/json"
       }));


     $.ajax({
         type: 'POST',
         url: '/gallary/create',
         processData: false,
         contentType:false,
         data: formData,
     }).done(function(){
      alert('저장 성공');
      window.location.href='/home/guest'
     }).fail(function (error) {
     alert(JSON.stringify(error));
     });
 });


// 페이지 생성
$('#delete').on('click', function() {
if(confirm("삭제하시겠습니까?")==true){

 var id = document.getElementById("id1").innerText;

              $.ajax({
               type: 'DELETE',
               url: '/gallary/delete/'+id ,
               contentType:'application/json; charset=utf-8',
           }).done(function(){
           alert('글이 삭제되었습니다.');
            window.location.href='/home/guest'
           }).fail(function (error) {
           alert(JSON.stringify(error));
     });
}
});



// 페이지 이동
$('#modipage').on('click', function(){

if(confirm("수정하시겠습니까?")==true){

  var id=document.getElementById("id1").innerText;

   window.location.href= '/gallary/update/' +id;

}
});


// 게시글 수정
$('#update').on('click', function(){

    if(confirm("수정하시겠습니까?")==true){

      var id=document.getElementById("id1").innerText;

    var data={
             title: $('#title').val(),
             content: $('#content').val(),
             link: $('#link').val()
         };

     $.ajax({
         type: 'PUT',
         url: '/gallary/update/' +id,
         contentType:'application/json; charset=utf-8',
         data: JSON.stringify(data)
     }).done(function(){
      alert('저장 성공');
      window.location.href= '/gallary/view/' +id;
     }).fail(function (error) {
     alert(JSON.stringify(error));
         });
    }
});


