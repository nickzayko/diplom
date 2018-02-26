// //источник:
//
// // https://books.google.by/books?id=hk3cEYub6TQC&pg=PA100&lpg=PA100&dq=%D0%BA%D0%B0%D0%BA+%D0%BE%D1%82%D1%81%D1%8B%D0%BB%D0%B0%D1%82%D1%8C+%D0%BF%D0%B5%D1%80%D0%B8%D0%BE%D0%B4%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B5+%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81%D1%8B+%D0%BD%D0%B0+%D1%81%D0%B5%D1%80%D0%B2%D0%B5%D1%80+ajax&source=bl&ots=jz1wkW373G&sig=Y0gWfgLpsjn-87URBqL2NbjPLAQ&hl=ru&sa=X&ved=0ahUKEwjZrrDJ1rTZAhXL3KQKHTKRCeMQ6AEITjAE#v=onepage&q=%D0%BA%D0%B0%D0%BA%20%D0%BE%D1%82%D1%81%D1%8B%D0%BB%D0%B0%D1%82%D1%8C%20%D0%BF%D0%B5%D1%80%D0%B8%D0%BE%D0%B4%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B5%20%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81%D1%8B%20%D0%BD%D0%B0%20%D1%81%D0%B5%D1%80%D0%B2%D0%B5%D1%80%20ajax&f=false
//
// var xmlHttp = createXmlHttpRequestObject();
//
// function createXmlHttpRequestObject() {
//     var xmlHttp;
//     try {
//         xmlHttp = new XMLHttpRequest();
//     }
//     catch (e) {
//         //Создаемобъект в браузере
//         var XmlHttpVersions = new Array("MSXML2.XMLHTTP.6.0",
//                                         "MSXML2.XMLHTTP.5.0",
//                                         "MSXML2.XMLHTTP.4.0",
//                                         "MSXML2.XMLHTTP.3.0",
//                                         "MSXML2.XMLHTTP",
//                                         "Microsoft.XMLHTTP.6.0");
//         for (var i =0; i<XmlHttpVersions.length && !xmlHttp; i++ ){
//             try {
//                 xmlHttp = new ActiveXObject(XmlHttpVersions[i]);
//             } catch (e){}
//         }
//     }
//     if (!xmlHttp){
//         alert("Ошибка при создании объекта XMLHttpRequest");
//     } else {
//         return xmlHttp;
//     }
//
// }


