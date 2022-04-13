////////

const save = document.getElementById("save");
save.addEventListener("click",save_func);

function save_func() {

    const title = document.getElementById("title").value;
    const content = document.getElementById("content").value; 
    const link = document.getElementById("link").value; 

   var data={
       title: title,
       content: content,
       link: link
   };

   fetch("/gallary/create", {
       method: "POST", // POST
       headers: { // 헤더 조작
        "Content-Type": "application/json;",
       },
       body: JSON.stringify(data),
   })
       .then(alert('저장 성공'))
       .then(window.location.href='/home/guest')
       .catch((data) => console.log(data))
}


////////

const dele = document.getElementById("delete");
dele.addEventListener("click", delete_function);


function delete_function(){
    if(confirm("삭제하시겠습니까?")==true){
        var id = document.getElementById("id1").innerText;

        fetch('/gallary/delete/'+id, {
            method: DELETE,
            headers: {
                "Content-Type": "application/json;"
            }
        })
        .then(alert('글이 삭제되었습니다.'))
        .then(window.location.href='/home/guest')
    }
};


////////

const modipage = document.getElementById("");

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



