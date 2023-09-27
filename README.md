# Техническое задание для компаний Solva

Использовались - Spring (Boot) Framework, Hibernate, JpaRepository, PostgreSQL, Swagger.


Курс валют с сайта - https://www.alphavantage.co/ 
(не отправлять больше 100 запросов а то в блок (забыл поставить в cron промежуток))


Для проверки запросов использовался - Postman 

#  account-controller
```
/account/update/{accountId}           - обновление аккаунта с получением id
/account/add                          - добовление нового аккаунта
/account/delete/{accountId}           - удаление аккаунта 
/account/                             - вывод всех пользователей 
/account/changeLimit/{accountNumber}  - поменять лимит аккаунта
```
![image](https://github.com/Temirbayev/solva_project/assets/60303183/96ce7a72-6e08-4cb3-83d3-27a43d327dc9)


#  Создание нового аккаунта пример:
```
{
  "accountNumber": "123123123",
  "balanceUsd": 1000,
  "limitUsd": 0  // есть исключение в коде если не поставлен месячный лимит то значение будет 1000 
}
```
#  transaction-controller
```
/transactions/transfer - трансфер 
/transactions/limit    - все аккаунты которые имеют лимиты
```
![image](https://github.com/Temirbayev/solva_project/assets/60303183/c3ab7cb2-54cb-4519-a0ce-f3c361138214)


#  Трансфер с одного аккаунта перевод к другому аккаунту
![image](https://github.com/Temirbayev/solva_project/assets/60303183/1c349a4a-a3fa-477d-97b5-358600de7244)

# Получение кто имеет лимит 
![image](https://github.com/Temirbayev/solva_project/assets/60303183/96ca8061-ae23-46d9-ade3-72279594cda9)


