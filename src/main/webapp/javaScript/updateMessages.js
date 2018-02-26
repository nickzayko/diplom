// $.ajax({
//     type: 'GET',
//     url: '/menu/messageHandler',
//     success: function updateMrssage () {
//         setTimeout(updateMrssage, 1000);
//     }
// })
// noinspection JSAnnotator
// ---------------------------------------------------------------
// function show() {
//     $.ajax({
//         url: "/menu/messageHandler",
//         cache: false,
//         success: function(html){
//             $("#content").html(html);
//         }
//     });
// }
//     $(document).ready(function(){
//     show(); setInterval('show()',1000);
// })
// ----------------------------------------------------------------
//     $.ajax({
//         dataType: 'json',
//         type: 'post',
//         url: '/menu/updateMessages',
//         success: function (jsondata) {
//             $('.results').html(jsondata);
//         }
//     })