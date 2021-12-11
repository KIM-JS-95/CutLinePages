
// 리뷰 저장
$('#save').on('click', function () {

    var data={
             title: $('#title').val(),
             content: $('#content').val(),
             link: $('#link').val()
         };

     $.ajax({
         type: 'POST',
         url: '/adminpages/create',
         dataType: 'json',
         contentType:'application/json; charset=utf-8',
         data: JSON.stringify(data)
     }).done(function(){
      alert('저장 성공');
      window.location.href='/adminpages/review'
     }).fail(function (error) {
     alert(JSON.stringify(error));
     });
 });


// 리뷰 삭제
$('#delete').on('click', function() {
if(confirm("삭제하시겠습니까?")==true){

 var id = document.getElementById("id1").innerText;

              $.ajax({
               type: 'DELETE',
               url: '/adminpages/delete/'+id,

           }).done(function(){
           alert('글이 삭제되었습니다.');
            window.location.href='/adminpages/review'
           }).fail(function (error) {
           alert(JSON.stringify(error));
     });
}
});



// 리뷰 수정 페이지 이동
$('#modipage').on('click', function(){

if(confirm("수정하시겠습니까?")==true){

  var id=document.getElementById("id1").innerText;

   window.location.href= '/adminpages/update/' +id;

}
});

// 게시글 수정
$('#update').on('click', function(){

    if(confirm("수정하시겠습니까?")==true){

      var id=document.getElementById("id1").innerText;

    var data={
             title: $('#title'),
             content: $('#content').val()
         };

     $.ajax({
         type: 'PUT',
         url: '/adminpages/update/' +id,
         dataType: 'json',
         contentType:'application/json;',
         data: JSON.stringify(data)
     }).done(function(){
      alert('저장 성공');
      window.location.href= '/adminpages/review/' +id;
     }).fail(function (error) {
     alert(JSON.stringify(error));
         });
    }
});


