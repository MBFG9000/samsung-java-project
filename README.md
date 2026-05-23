# SneakyPeaky

Android‑приложение магазина кроссовок с каталогом, карточками товаров, корзиной и аккаунтом. Данные берутся из FakeStore API, а аутентификация выполняется через Firebase (Email/Password).

## Скриншоты

_Вставьте сюда изображения интерфейса._

```
![Каталог](screenshots/catalog.png)
![Карточка товара](screenshots/product.png)
![Корзина](screenshots/cart.png)
![Аккаунт](screenshots/account.png)
```

## Функциональность

- каталог товаров с загрузкой из API
- карточка товара
- корзина (добавление, удаление, очистка)
- аккаунт: регистрация, вход, выход (Firebase Auth)

## Стек

- Java + XML
- Clean Architecture (data / domain / presentation)
- Retrofit + Gson
- Firebase Auth (Email/Password)
- RecyclerView
- Glide
- Material Components

## Источник данных

- FakeStore API: https://fakestoreapi.com/

## Сборка и запуск

1. В Firebase Console добавьте Android‑приложение с package name **com.sneakypeaky**.
2. Скачайте `google-services.json` и положите в `app/`.
3. Включите **Email/Password** в Authentication.
4. Синхронизируйте Gradle и запустите приложение.

Команда для сборки:
```
./gradlew assembleDebug
```

## Структура проекта

```
app/src/main/java/com/sneakypeaky/
  data/           // API, DTO, мапперы, репозитории
  domain/         // модели, интерфейсы, use-cases
  presentation/   // UI, адаптеры, viewmodels
```

## Что можно улучшить

- локальное кеширование
- избранное
- поиск и фильтры
- реальная корзина/заказы на сервере
