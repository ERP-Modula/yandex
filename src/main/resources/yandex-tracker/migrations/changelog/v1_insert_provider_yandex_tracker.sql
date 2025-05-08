-- changeset saveliydanko:insert-provider-yandex-tracker
INSERT INTO provider (
    id, name, label, description, alert_notification, code_uri, auth_type
) VALUES (
    'b9d6f0ae-4c5e-47a8-909f-44a7a9f02999',
    'yandexTracker',
    'yandexTracker',
    'Интеграция с системой управления задачами Yandex Tracker. Документация по подключению: {ссылка на нашу доку}',
    'Прежде чем создавать подключение в Modula, потребуется зарегистрировать приложение в Yandex OAuth. 1. Перейдите на страницу управления приложениями: https://oauth.yandex.ru/client/new. 2. Укажите необходимые параметры, включая редирект {domain}/oauth/authorize/. 3. Сохраните client ID и client secret, затем подключите аккаунт Yandex Tracker в Modula.',
    'https://oauth.yandex.ru/',
    'OAUTH2'
);
