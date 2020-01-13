
Чтобы запустить приложение:

1) Склонировать репозиторий с проектом.

2) Выбрать JDK (Java 8).

3) Запустить Maven, чтобы он подхватил все зависимости.

4) Установить драйвер postgresql.

5) Подключиться к базе данных.

         5.1 url: jdbc:postgresql://localhost:5432/nuketyvkar-db
        
         5.2 database name: nuketyvkar-db
     
         5.3 port: 5432
     
         5.4 username: postgres
    
         5.5 password: qwerty
  
6) Запустить класс Application

        com/simbirsoft/project/nuketyvkar/Application.java
        
7) Войти под одной из учетных записей:
        
        Login : admin 
        Password: 12345
        
        Login: moder
        Password: 12345
        
        Login: user
        Password: 12345
        
8) Для комфортной разработки рекомендуется установить плагин Lombok.
