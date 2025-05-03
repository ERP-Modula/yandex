-- changeset saveliydanko:insert-oauth2-provider-yandex
INSERT INTO oauth2_provider (
    id, client_id, client_secret, token_uri
) VALUES (
    '38e6e361-56f6-4d44-83b5-91180228c33d',
    'b32b365004534f9597feb8aff212a48c',
    'be9ed420e6204c808214ea01a7536b75',
    'https://oauth.yandex.ru/verification_code'
);
