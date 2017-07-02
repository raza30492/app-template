--
INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities,
 access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES
("client", "secret", "read,write,trust", "password,authorization_code,refresh_token,implicit", null, null, 43200, 43200, null, true),
("client-web", "secret-web", "read,write,trust", "password,authorization_code,refresh_token,implicit", null, null, 3600, 7200, null, true);

--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: mdzahidraza
--

INSERT INTO users (id, created_at, created_by, enabled, modified_at, modified_by, account_expired, account_locked, credential_expired, email, mobile, name, otp, otp_sent_at, password, retry_count, roles, username) VALUES
(1,'2017-07-02 20:39:15.831',NULL,true,'2017-07-02 20:39:15.831',NULL,false,false,false,'zahid7292@gmail.com','8987525008','Md Zahid Raza',NULL,NULL,'$2a$10$c6Yb6G41usEidZAc8Oc6JuL09VmyrypL1V80l9/k0upXdtCvomRNK',NULL,'ROLE_ADMIN','zahid7292');