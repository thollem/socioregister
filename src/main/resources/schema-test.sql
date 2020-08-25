

CREATE TABLE IF NOT EXISTS socio ( 
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    first_name VARCHAR(250) NULL,
    last_name VARCHAR(250) NULL,
    email VARCHAR(250) NOT NULL,
    register_date DATETIME NOT NULL,
    last_checkin_date DATETIME NOT NULL,
    active TINYINT(1) NOT NULL
);

CREATE TABLE IF NOT EXISTS socio_associated_socio (
   associated_socio_state VARCHAR(100) NULL,
   associated_socio_date DATETIME NULL,
   associated_socio_id INT(10) NULL,
   socio_id INT(10) NULL,
   FOREIGN KEY (associated_socio_id) REFERENCES socio(id),
   FOREIGN KEY (socio_id) REFERENCES socio(id)
);

CREATE TABLE IF NOT EXISTS country (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS address (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    province VARCHAR(100) NULL,
    postalcode VARCHAR(100) NULL,
    country_id INT(10) NOT NULL,
    address_type VARCHAR(100) NOT NULL,
    description VARCHAR(500) NULL,
    socio_id INT(10) NULL,
    FOREIGN KEY (socio_id) REFERENCES socio(id)
    FOREIGN KEY (country_id) REFERENCES country(id)
);

CREATE TABLE IF NOT EXISTS role (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS socio_role (
    socio_id INT(10) NOT NULL,
    role_id INT(10) NOT NULL,
    FOREIGN KEY (socio_id) REFERENCES socio(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE IF NOT EXISTS language (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(100) NOT NULL
);


CREATE TABLE IF NOT EXISTS socio_language (
    socio_id INT(10) NOT NULL,
    language_id INT(10) NOT NULL,
    FOREIGN KEY (socio_id) REFERENCES socio(id),
    FOREIGN KEY (language_id) REFERENCES language(id)
);

