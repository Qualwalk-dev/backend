ALTER TABLE COMBOS_HD
ADD COLUMN IMAGE BYTEA NULL,
ADD COLUMN IMAGE_NAME VARCHAR(100),
ADD COLUMN IMAGE_SIZE BIGINT,
ADD COLUMN IMAGE_CONTENT_TYPE VARCHAR(30);