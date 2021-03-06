CREATE TABLE doctor (
    docid   SERIAL,
    docname VARCHAR(128) NOT NULL,
    PRIMARY KEY (docid)
);
CREATE TABLE pharmacy (
    phid   SERIAL,
    phname VARCHAR(128) NOT NULL,
    city VARCHAR(128) NOT NULL,
    PRIMARY KEY (phid)
);
CREATE TABLE drug (
    did   SERIAL,
    dname VARCHAR(128) NOT NULL,
    price INT NOT NULL,
    PRIMARY KEY (did)
);
CREATE TABLE PharmacyDrug (
    pdid   SERIAL,
    did INT NOT NULL,
    phid INT NOT NULL,
    PRIMARY KEY (pdid)
);
CREATE TABLE Insurance (
    inid   SERIAL,
    plan VARCHAR,
    PRIMARY KEY (inid)
);
CREATE TABLE InsuranceDrug(
    idid   SERIAL,
    did INT NOT NULL,
    inid INT NOT NULL,
    reducedprice INT NOT NULL,
    PRIMARY KEY (idid)
)
CREATE TABLE MyUser(
    uid   SERIAL,
    username VARCHAR,
    password VARCHAR,
    role VARCHAR,
    PRIMARY KEY (uid)
);
CREATE TABLE patient (
    pid   SERIAL,
    name VARCHAR(128) NOT NULL,
    city VARCHAR(128) NOT NULL,
    inid INT,
    PRIMARY KEY (pid)
);

