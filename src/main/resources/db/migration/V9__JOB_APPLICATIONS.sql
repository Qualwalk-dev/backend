CREATE SEQUENCE IF NOT EXISTS PK_JOBS_APPLICATIONS
AS BIGINT
INCREMENT BY 1
MINVALUE 0
START 0;

CREATE TABLE IF NOT EXISTS JOB_APPLICATIONS(
    ID BIGINT PRIMARY KEY,
    JOB_ID BIGINT NOT NULL,
    EMAIL VARCHAR(20) NOT NULL,
    PHONE_NUMBER VARCHAR(20) NOT NULL,
    CONSTRAINT FK_JOB_ID FOREIGN KEY(JOB_ID) REFERENCES JOBS(ID)
)