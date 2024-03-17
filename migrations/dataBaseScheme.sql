--liquibase formatted sql

--changeset qwerty142:1
CREATE TABLE chat
(
    chatId BIGINT NOT NULL PRIMARY KEY
);

CREATE TABLE link
(
    linkId BIGINT NOT NULL PRIMARY KEY,
    url TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE chatAndLink
(
    linkId BIGINT NOT NULL REFERENCES link (linkId),
    chatId BIGINT NOT NULL REFERENCES chat (chatId),
    PRIMARY KEY(linkId, chatId)
);

CREATE TABLE linkStackoverflow (
    linkId BIGINT PRIMARY KEY REFERENCES link (linkId),
    commentsAmount INT DEFAULT 0,
    answersAmount INT DEFAULT 0,
    answered BOOLEAN DEFAULT FALSE
);
