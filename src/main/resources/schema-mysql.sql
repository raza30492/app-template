
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `modified_at` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `credential_expired` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `otp` varchar(255) DEFAULT NULL,
  `otp_sent_at` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `retry_count` int(11) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `IDX7516795akd6qg7e0i8e5rv58s` (`name`,`email`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

------------------------------------------------

create table if not exists oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

create table if not exists oauth_client_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table if not exists oauth_access_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(255)
);

create table if not exists oauth_refresh_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);

create table if not exists oauth_code (
  code VARCHAR(255), authentication LONG VARBINARY
);
--
--create table if not exists oauth_approvals (
--	userId VARCHAR(255),
--	clientId VARCHAR(255),
--	scope VARCHAR(255),
--	status VARCHAR(10),
--	expiresAt TIMESTAMP,
--	lastModifiedAt TIMESTAMP
--);

create table if not exists ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);