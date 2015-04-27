CREATE TABLE USER (
  id   BIGINT  NOT NULL,
  name VARCHAR(255) NOT NULL,
  contact_details_id BIGINT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_user_contact_details_id
    FOREIGN KEY (contact_details_id)
    REFERENCES USER (id)
);

CREATE TABLE USER_DETAILS (
  id   bigint  NOT NULL,
  user_id BIGINT NOT NULL,
  nationality VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_user_details_user_id
    FOREIGN KEY (user_id)
    REFERENCES USER (id)
);

CREATE TABLE CONTACT_DETAILS (
  id   bigint  NOT NULL,
  mobile VARCHAR(20) NOT NULL,
  email VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);
