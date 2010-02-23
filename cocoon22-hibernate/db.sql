-- 
-- CREATE DATABASE cocoon22hibernate WITH OWNER = postgres  ENCODING = 'UNICODE';

-- Table: users

-- DROP TABLE users;

CREATE TABLE users
(
  us_id serial NOT NULL,
  us_username character varying(255) NOT NULL,
  us_password character varying(255) NOT NULL,
  us_firstname character varying(255),
  us_surname character varying(255),
  us_email character varying(255),
  CONSTRAINT deals_user_pkey PRIMARY KEY (us_id),
  CONSTRAINT deals_user_us_username_key UNIQUE (us_username)
)
WITH OIDS;
ALTER TABLE users OWNER TO postgres;
