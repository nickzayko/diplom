// function http_request() {
//     if (xmlHttp.readyState == 4 || xmlHttp.readyState == 0){
//
//         // //запрашиваем значения поля формы
//         // name = encodeURIComponent(document.getElementById("").value);
//
//         //выполняем запрос к серверу
//         // xmlHttp.open("GET", "settime.php?name="+name, true);
//         xmlHttp.open("GET", "/menu/messageHandler", true);
//
//         //определим функцииобработки события
//         xmlHttp.onreadystatechange = handler;
//
//         //отправляем запрос к серверу
//         xmlHttp.send(null);
//     } else {
//         //в случае занятости посторяем запрос через 1 секунду
//         setTimeout('http_request()', 1000);
//     }
//
//     //
//     function handler() {
//
//         //начинаем обработку по завершению запооса
//         if (xmlHttp.readyState == 4){
//             //обрабатываем ответ если его статус равен 200
//             if (xmlHttp.status == 200){
//                 //читаем текстовый ответ сервера
//                 xmlResponce = xmlHttp.responseText;
//                 document.getElementById("").innerHTML = '<i>' + xmlResponce + '</i>';
//                 //повторяем вызов функции через 1 секунду
//                 setTimeout('http_request()', 1000);
//             } else {
//                 //если статус ответа отличен от 200, то выводим сообщение об ошибке
//                 alert("Проблема доступа к серверу: " + xmlHttp.statusText);
//             }
//         }
//     }
//
// }