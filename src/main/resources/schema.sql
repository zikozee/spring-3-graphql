-- Run this on database problemz

-- table definitions
DROP TABLE IF EXISTS userz_token;
DROP TABLE IF EXISTS solutionz;
DROP TABLE IF EXISTS problemz;
DROP TABLE IF EXISTS userz;

CREATE TABLE userz (
                       id uuid NOT NULL,
                       active bool NOT NULL default true,
                       avatar varchar(255) NULL,
                       creation_timestamp timestamp NOT NULL,
                       display_name varchar(100) NULL,
                       email varchar(100) NOT NULL,
                       hashed_password varchar(255) NOT NULL,
                       username varchar(50) NOT NULL,
                       UNIQUE (username),
                       UNIQUE (email),
                       PRIMARY KEY (id)
);

CREATE TABLE problemz (
                          id uuid NOT NULL,
                          "content" text NOT NULL,
                          creation_timestamp timestamp NOT NULL,
                          tags varchar(255) NOT NULL,
                          title varchar(255) NOT NULL,
                          created_by uuid NOT NULL,
                          PRIMARY KEY (id)
);

ALTER TABLE problemz ADD CONSTRAINT problemz_userz_fk FOREIGN KEY (created_by) REFERENCES userz(id);

CREATE TABLE solutionz (
                           id uuid NOT NULL,
                           category varchar(50) NOT NULL,
                           "content" text NOT NULL,
                           creation_timestamp timestamp NOT NULL,
                           vote_bad_count int4 NOT NULL,
                           vote_good_count int4 NOT NULL,
                           created_by uuid NOT NULL,
                           problemz_id uuid NOT NULL,
                           PRIMARY KEY (id)
);

ALTER TABLE solutionz ADD CONSTRAINT solutionz_problemz_fk FOREIGN KEY (problemz_id) REFERENCES problemz(id);
ALTER TABLE solutionz ADD CONSTRAINT solutionz_userz_fk FOREIGN KEY (created_by) REFERENCES userz(id);

CREATE TABLE userz_token (
                             user_id uuid NOT NULL,
                             auth_token varchar(255) NOT NULL,
                             creation_timestamp timestamp NOT NULL,
                             expiry_timestamp timestamp NOT NULL,
                             PRIMARY KEY (user_id)
);