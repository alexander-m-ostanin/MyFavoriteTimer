Мой пример простого Android приложения для удобного использования набора избранных таймеров обратного отсчета.

Приложение реализует следующие пользовательские истории:
1. Просмотреть список сохраненных таймеров;
2. Создать новый таймер;
3. Редактировать существующий таймер;
4. Сохранить созданный или отредактированный таймер;
5. Запустить таймер;
6. Приостановить таймер;
7. Сбросить таймер.

Используемые архитектурные паттерны и технологии:
1. Clean architecture;
2. MVVP;
3. Jetpack Compose;
4. Koin dependency injection;
5. Room database.

Чтобы система не убивала таймер в свернутом приложении и при выключенном экране - функциональность таймера вынесена в отдельный сервис.

![image](https://github.com/alexander-m-ostanin/MyFavoriteTimer/assets/59962448/6401dfbb-e26d-4bd5-a8a7-a3734610b67e)
