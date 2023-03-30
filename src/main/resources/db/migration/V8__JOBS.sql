CREATE SEQUENCE IF NOT EXISTS PK_JOBS
AS BIGINT
INCREMENT BY 1
MINVALUE 0
START 0;

CREATE TABLE IF NOT EXISTS JOBS(
    ID BIGINT PRIMARY KEY,
    DESIGNATION VARCHAR(20) NOT NULL,
    LOCATION VARCHAR(20) NOT NULL,
    MIN_YEARS INT DEFAULT 0,
    MAX_YEARS INT DEFAULT 0,
    DESCRIPTION TEXT
)