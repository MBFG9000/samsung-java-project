# SneakyPeaky

Android‑приложение магазина кроссовок с каталогом, карточками товаров, корзиной и аккаунтом. Данные берутся из FakeStore API, а аутентификация выполняется через Firebase (Email/Password).

## Скриншоты

<table border="1" cellspacing="0" cellpadding="10">
    <tr>
        <td>
            <img src="screenshots/catalog.jpeg" alt="Каталог" width="300">
        </td>
        <td>
            <img src="screenshots/cart.jpeg" alt="Корзина" width="300">
        </td>
        <td>
            <img src="screenshots/account.jpeg" alt="Аккаунт" width="300">
        </td>
    </tr>
</table>

## Функциональность

- каталог товаров с загрузкой из API
- карточка товара
- корзина (добавление, удаление, очистка)
- аккаунт: регистрация, вход, выход (Firebase Auth)

## Источник данных

- FakeStore API: https://fakestoreapi.com/

## Сборка и запуск

Команда для сборки:
```
./gradlew assembleDebug
```

