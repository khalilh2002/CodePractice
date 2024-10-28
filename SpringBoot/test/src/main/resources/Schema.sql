DROP TABLE IF EXISTS "authors";
DROP TABLE IF EXISTS "books";

CREATE TABLE authors(
  "id" BIGINT DEFAULT  NOT NULL ,
  "name" TEXT,
  CONSTRAINT "author_id_pk" PRIMARY KEY("id")
);

CREATE TABLE books(
  "isbn" text  NOT NULL ,
  "title" text,
  "author_id" bigint,
  CONSTRAINT "book_isbn_pk" PRIMARY KEY("isbn"),
  CONSTRAINT "authors_fk" FOREIGN KEY("author_id")  REFERENCES authors(id)
);
