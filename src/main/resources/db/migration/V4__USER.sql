CREATE TABLE IF NOT EXISTS USER_HD(
    EMAIL VARCHAR(20) PRIMARY KEY NOT NULL,
    IS_VERIFIED CHAR(1) DEFAULT 'N',
    CREATED_ON TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS USER_DET(
    EMAIL VARCHAR(20) NOT NULL,
    USERNAME VARCHAR(20) NOT NULL,
    FIRSTNAME VARCHAR(20) NOT NULL,
    LASTNAME VARCHAR(20) NOT NULL,
    ORGANIZATION VARCHAR(40),
    EXPERIENCE INT DEFAULT 0,
    CREATED_ON TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY(EMAIL, USERNAME),
    CONSTRAINT FK_USER_HD FOREIGN KEY(EMAIL) references USER_HD(EMAIL)
);