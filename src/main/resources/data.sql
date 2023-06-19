/*==============================================================*/
/* Table: PRICES                                                */
/*==============================================================*/
create table PRICES (
   ID                   INTEGER       primary key,
   BRAND_ID             INTEGER          not null,
   START_DATE           TIMESTAMP            null,
   END_DATE             TIMESTAMP            null,
   PRICE_LIST           INTEGER          not null,
   PRODUCT_ID           INTEGER          not null,
   PRIORITY             INTEGER              null,
   PRICE                DOUBLE               null,
   CURR                 CHARACTER VARYING(9) null
);

/*==============================================================*/
/* Table: BRAND                                                */
/*==============================================================*/
create table BRAND (
   ID                   INTEGER       primary key,
   NAME                 CHARACTER VARYING(50) null
);

alter table PRICES
   add constraint FK_PRICES_FK_BRAND foreign key (BRAND_ID)
      references BRAND (ID);

INSERT INTO BRAND (ID, NAME) VALUES (1, 'ZARA');
INSERT INTO BRAND (ID, NAME) VALUES (2, 'ZARA DOS');

INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) VALUES (1, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR');
INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) VALUES (2, 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR');
INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) VALUES (3, 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR');
INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) VALUES (4, 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');
