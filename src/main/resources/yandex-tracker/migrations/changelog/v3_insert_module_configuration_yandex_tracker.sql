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