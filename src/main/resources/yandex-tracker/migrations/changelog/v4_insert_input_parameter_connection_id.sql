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