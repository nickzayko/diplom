# "Online chat"
Web project named "Online chat" - проект, который позволит пройти регистрацию нового пользователя, 
авторизацию существующего пользователя, создать/найти тематический чат, осуществить беседу в чате, 
подгрузить предыдущие сообщения, выполнить некоторые другие функции.

Стек технологий: Java 1.8; Maven; Spring; MySQL; Hibernate; JSP; HTML; CSS; JSTL; JQuery; Tomcat; EasyMock, ReCaptcha Google

Чтобы успешно стартовать проект с помощью контейнера сервлетов Tomcat необходимо предварительно
выполнить следующие пункты:

1) Установить сервер базы данных MySQL
2) Убедиться, что с сервером базы данных установлено соединение
3) Выполнить скрипт из файла, расположенного в директории: src\main\resources\SQLStartScript\SQLStartScript.sql
(Предварительно установить соединение с БД, зайти в указанную директорию -> нажать правую кнопку мыши -> в меню выбрать "SQL start script",После успешного выплнения должна 
создаться база данных diploma_project, а в ней необходимые таблицы,
созданыие базы данных и таблиц можно выполнить в ручную, но это не гарантирует правильного создания схем б/д)
4) Настроить запуск контейнера сервлета, при этом указать artifactDeployment
5) Произвести запуск проекта

По всем вопросам можно обращаться в почту kolya-zayko@rambler.ru или vk https://vk.com/nick_zayko. 
