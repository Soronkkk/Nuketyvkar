
Чтобы запустить приложение:

1) Склонировать репозиторий с проектом.

2) Выбрать JDK (Java 8).

3) Запустить Maven, чтобы он подхватил все зависимости.

4) Установить драйвер postgresql.

5) Подключиться к базе данных. (перед этим создав её в pgAdmin, если есть такая необходимость)

         5.1 url: jdbc:postgresql://localhost:5432/nuketyvkar
        
         5.2 database name: nuketyvkar
     
         5.3 port: 5432
     
         5.4 username: postgres
    
         5.5 password: qwerty
  
6) Запустить класс Application

        com/project/pet/nuketyvkar/NuketyvkarApplication.java
        
7) Войти под одной из учетных записей:
        
        Login : admin 
        Password: 12345
        
        Login: moder
        Password: 12345
        
        Login: user
        Password: 12345
        
