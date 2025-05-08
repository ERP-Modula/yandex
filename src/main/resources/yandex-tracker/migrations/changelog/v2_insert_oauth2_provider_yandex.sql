-- changeset saveliydanko:insert-oauth2-provider-yandex
INSERT INTO oauth2_provider (
    id, client_id, client_secret, token_uri
) VALUES (
    'b9d6f0ae-4c5e-47a8-909f-44a7a9f02999',
    'b32b365004534f9597feb8aff212a48c',
    'be9ed420e6204c808214ea01a7536b75',
    'https://oauth.yandex.ru/verification_code'
);
