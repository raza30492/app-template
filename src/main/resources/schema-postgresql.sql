--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.3
-- Dumped by pg_dump version 9.6.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: users; Type: TABLE; Schema: public; Owner: mdzahidraza
--

CREATE TABLE users (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    enabled boolean,
    modified_at timestamp without time zone,
    modified_by character varying(255),
    account_expired boolean NOT NULL,
    account_locked boolean NOT NULL,
    credential_expired boolean NOT NULL,
    email character varying(255) NOT NULL,
    mobile character varying(255),
    name character varying(255) NOT NULL,
    otp character varying(255),
    otp_sent_at timestamp without time zone,
    password character varying(255) NOT NULL,
    retry_count integer,
    roles character varying(255),
    username character varying(255) NOT NULL
);


ALTER TABLE users OWNER TO mdzahidraza;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: mdzahidraza
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO mdzahidraza;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mdzahidraza
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: mdzahidraza
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mdzahidraza
--

SELECT pg_catalog.setval('users_id_seq', 1, true);


--
-- Name: users uk_6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: mdzahidraza
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: users uk_r43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: mdzahidraza
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: mdzahidraza
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: idx7516795akd6qg7e0i8e5rv58s; Type: INDEX; Schema: public; Owner: mdzahidraza
--

CREATE INDEX idx7516795akd6qg7e0i8e5rv58s ON users USING btree (name, email, username);


--
-- PostgreSQL database dump complete
--



--------------------------
--- Creating tables for JdbcTokenStore
---------------------------


--
-- Name: oauth_client_details; Type: TABLE; Schema: public; Owner: mdzahidraza
--

CREATE TABLE oauth_client_details (
    client_id character varying(255),
    resource_ids character varying(255),
    client_secret character varying(255),
    scope character varying(255),
    authorized_grant_types character varying(255),
    web_server_redirect_uri character varying(255),
    authorities character varying(255),
    access_token_validity integer,
    refresh_token_validity integer,
    additional_information character varying(4096),
    autoapprove character varying(255)
);


ALTER TABLE oauth_client_details OWNER TO mdzahidraza;

--
-- Name: oauth_client_details oauth_client_details_pkey; Type: CONSTRAINT; Schema: public; Owner: mdzahidraza
--

ALTER TABLE ONLY oauth_client_details
    ADD CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id);
 

CREATE TABLE  oauth_client_token (
   token_id character varying(256),
   token bytea,
   authentication_id character varying(256),
   user_name character varying(256),
   client_id character varying(256)
);
 
CREATE TABLE  oauth_access_token (
   token_id character varying(256),
   token bytea,
   authentication_id character varying(256),
   user_name character varying(256),
   client_id character varying(256),
   authentication bytea,
   refresh_token character varying(256)
);
 
CREATE TABLE  oauth_refresh_token (
   token_id character varying(256),
   token bytea,
   authentication bytea
);
 
CREATE TABLE  oauth_code (
   code character varying(256),
   authentication bytea
);
    

--
-- Name: ClientDetails; Type: TABLE; Schema: public; Owner: mdzahidraza
--

-- customized oauth_client_details table
CREATE TABLE ClientDetails (
    appId character varying(255),
    resourceIds character varying(255),
    appSecret character varying(255),
    scope character varying(255),
    grantTypes character varying(255),
    redirectUrl character varying(255),
    authorities character varying(255),
    access_token_validity integer,
    refresh_token_validity integer,
    additional_information character varying(4096)
);


ALTER TABLE ClientDetails OWNER TO mdzahidraza;

--
-- Name: ClientDetails ClientDetails_pkey; Type: CONSTRAINT; Schema: public; Owner: mdzahidraza
--

ALTER TABLE ONLY ClientDetails
    ADD CONSTRAINT ClientDetails_pkey PRIMARY KEY (appId);

--
-- PostgreSQL database dump complete
--

