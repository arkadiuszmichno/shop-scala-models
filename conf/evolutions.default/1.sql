# --- !Ups

CREATE TABLE "category"
(
    "id"   INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name" VARCHAR NOT NULL
);

CREATE TABLE "user"
(
    "id"        INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "firstName" VARCHAR NOT NULL,
    "lastName"  VARCHAR NOT NULL,
    "login"     VARCHAR NOT NULL,
    "gender"    VARCHAR NOT NULL,
);

CREATE TABLE "book"
(
    "id"          INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"        VARCHAR NOT NULL,
    "description" VARCHAR NOT NULL,
    "category"    INT     NOT NULL,
    FOREIGN KEY (category) references category (id)
);

CREATE TABLE "bookReview"
(
    "id"     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "book"   INT     NOT NULL,
    "user"   INT     NOT NULL,
    "review" INTEGER NOT NULL,
    FOREIGN KEY (book) references book (id),
    FOREIGN KEY (user) references user (id)
);

CREATE TABLE "career"
(
    "id"          INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "position"    VARCHAR NOT NULL,
    "description" VARCHAR NOT NULL
);

CREATE TABLE "cart"
(
    "id"   INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "user" INT     NOT NULL,
    "book" INT     NOT NULL,
    FOREIGN KEY (user) references user (id),
    FOREIGN KEY (book) references book (id)
);

CREATE TABLE "discountCoupon"
(
    "id"    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"  VARCHAR NOT NULL,
    "value" INT     NOT NULL
);

CREATE TABLE "giftCard"
(
    "id"       INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"     VARCHAR NOT NULL,
    "value"    INT     NOT NULL,
    "category" INT     NOT NULL,
    FOREIGN KEY (category) references category (id)
);

CREATE TABLE "order"
(
    "id"    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "user"  INT     NOT NULL,
    "price" INT     NOT NULL,
    FOREIGN KEY (user) references user (id)
);

CREATE TABLE "return"
(
    "id"   INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "book" INT     NOT NULL,
    "user" INT     NOT NULL,
    FOREIGN KEY (book) references book (id),
    FOREIGN KEY (user) references user (id)
);
# --- !Downs

DROP TABLE "category" IF EXISTS;
DROP TABLE "user" IF EXISTS;
DROP TABLE "book" IF EXISTS;
DROP TABLE "bookReview" IF EXISTS;
DROP TABLE "career" IF EXISTS;
DROP TABLE "cart" IF EXISTS;
DROP TABLE "discountCoupon" IF EXISTS;
DROP TABLE "giftCard" IF EXISTS;
DROP TABLE "order" IF EXISTS;
DROP TABLE "return" IF EXISTS;