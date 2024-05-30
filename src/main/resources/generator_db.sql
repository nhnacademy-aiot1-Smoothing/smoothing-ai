CREATE TABLE power_generator
(
    generator_id    VARCHAR(50) NOT NULL,
    generator_state VARCHAR(50) NULL,
    CONSTRAINT pk_power_generator PRIMARY KEY (generator_id)
);

CREATE TABLE generator_logs
(
    log_id       BIGINT AUTO_INCREMENT NOT NULL,
    generator_id VARCHAR(50)           NOT NULL,
    time         datetime              NOT NULL,
    message      VARCHAR(100)          NOT NULL,
    CONSTRAINT pk_generator_logs PRIMARY KEY (log_id)
);

ALTER TABLE generator_logs
    ADD CONSTRAINT FK_GENERATOR_LOGS_ON_GENERATOR FOREIGN KEY (generator_id) REFERENCES power_generator (generator_id);

INSERT INTO power_generator (generator_id, generator_state)
VALUES ('generator_1', 'Active'),
       ('generator_2', 'Active'),
       ('generator_3', 'Active');