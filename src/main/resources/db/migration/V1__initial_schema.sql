CREATE TABLE IF NOT EXISTS public.dance
(
    id   BIGINT       NOT NULL,
    name VARCHAR(255) NOT NULL,
    version INT,
    CONSTRAINT pk_dances PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS dance_sequence
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    OWNED BY dance.id;