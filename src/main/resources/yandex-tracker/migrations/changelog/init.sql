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

-- changeset saveliydanko:insert-oauth2-provider-yandex
INSERT INTO oauth2_provider (
    id, client_id, client_secret, token_uri
) VALUES (
    'b9d6f0ae-4c5e-47a8-909f-44a7a9f02999',
    'b32b365004534f9597feb8aff212a48c',
    'be9ed420e6204c808214ea01a7536b75',
    'https://oauth.yandex.ru/verification_code'
);

-- changeset saveliydanko:insert-module-configuration-yandex-tracker
INSERT INTO module_configuration (
    id, name, label, description, icon_path, is_public, theme, author_id, auth_type, provider_id, rest_api_base_url, webhook_base_url
) VALUES (
    'f8eecf30-37b9-41c6-bbc2-e7adcb21a617',
    'yandex-tracker',
    'YandexTracker',
    'Yandex Tracker integration module',
    '/icons/yandex-tracker.png',
    true,
    'black',
    'system',
    'OAUTH2',
    'b9d6f0ae-4c5e-47a8-909f-44a7a9f02999',
    'https://api.tracker.yandex.net/v2',
    'https://api.tracker.yandex.net/v2'
);

-- changeset saveliydanko:insert-input-parameter-connection-id
INSERT INTO input_parameter (
    id, name, label, required, help, type
) VALUES (
    'aa7e2181-6ae5-49a8-98b7-fbe0ce1234e9',
    'connectionId',
    'ID подключения',
    true,
    'Внутренний идентификатор подключения к Yandex-Tracker',
    'CONNECTION'
);

-- Action: fetchCurrentUser
insert into module_action (id, name, label, description, category, endpoint_url)
values (
    'e5db4078-557b-49d0-8743-cd2e45f61959',
    'fetchCurrentUser',
    'Fetch Current User',
    'Fetch Current User',
    'tracker',
    '/myself'
    );

-- Bind Action and Module
insert into module_configuration_actions (module_configuration_id, actions_id)
values (
    'f8eecf30-37b9-41c6-bbc2-e7adcb21a617',
    'e5db4078-557b-49d0-8743-cd2e45f61959'
);

-- Insert Connection into Input Parameter
INSERT INTO module_action_input_parameters (module_action_id, input_parameters_id)
VALUES (
    'e5db4078-557b-49d0-8743-cd2e45f61959',
    'aa7e2181-6ae5-49a8-98b7-fbe0ce1234e9'
);

--
INSERT INTO input_parameter (id, name, label, required, help, type)
VALUES (
    '7473ded4-bff7-4f61-b53b-b29702261095',
    'fetchCurrentUser',
    'Поля сделки',
    true,
    'Объект с полями сделки Bitrix24',
    'COLLECTION'
);
