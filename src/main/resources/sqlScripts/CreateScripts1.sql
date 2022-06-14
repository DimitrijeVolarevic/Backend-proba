DROP TABLE IF EXISTS smer CASCADE;
DROP TABLE IF EXISTS projekat CASCADE;
DROP TABLE IF EXISTS grupa CASCADE;
DROP TABLE IF EXISTS student CASCADE;

DROP SEQUENCE IF EXISTS smer_seq;
DROP SEQUENCE IF EXISTS grupa_seq;
DROP SEQUENCE IF EXISTS projekat_seq;
DROP SEQUENCE IF EXISTS student_seq;


CREATE TABLE smer (
	id integer not null,
    naziv varchar(100),
    oznaka varchar(50)
);
CREATE TABLE projekat (
	id integer not null,
    naziv varchar(100),
    oznaka varchar(10),
    opis varchar(500)
);
CREATE TABLE grupa (
	id integer not null,
    oznaka varchar(10),
    smer integer not null
);
CREATE TABLE student (
	id integer not null,
    ime varchar(50),
    prezime varchar(50),
    broj_indeksa varchar(20),
    grupa integer not null,
    projekat integer not null
);

ALTER TABLE smer 
	ADD CONSTRAINT pk_smer PRIMARY KEY (id);
ALTER TABLE projekat 
	ADD CONSTRAINT pk_projekat PRIMARY KEY (id);
ALTER TABLE grupa 
	ADD CONSTRAINT pk_grupa PRIMARY KEY (id);
ALTER TABLE student 
	ADD CONSTRAINT pk_student PRIMARY KEY (id);
    
ALTER TABLE grupa 
	ADD CONSTRAINT  fk_grupa_smer FOREIGN KEY (smer) REFERENCES smer(id);
ALTER TABLE student 
	ADD CONSTRAINT  fk_student_grupa FOREIGN KEY (grupa) REFERENCES grupa(id);
ALTER TABLE student 
	ADD CONSTRAINT  fk_student_projekat FOREIGN KEY (projekat) REFERENCES projekat(id);
    
CREATE INDEX idx_pk_smer ON smer(id);
CREATE INDEX idx_pk_grupa ON grupa(id);
CREATE INDEX idx_pk_projekat ON projekat(id);
CREATE INDEX idx_pk_student ON student(id);

CREATE INDEX idx_fk_grupa_smer ON grupa(smer);
CREATE INDEX idx_fk_student_grupa ON student(grupa);
CREATE INDEX idx_fk_student_projekat ON student(projekat);

CREATE SEQUENCE IF NOT EXISTS smer_seq INCREMENT 1 start 1;
CREATE SEQUENCE IF NOT EXISTS grupa_seq INCREMENT 1 start 1;
CREATE SEQUENCE IF NOT EXISTS projekat_seq INCREMENT 1 start 1;
CREATE SEQUENCE IF NOT EXISTS student_seq INCREMENT 1 start 1;

AlTER TABLE smer ALTER COLUMN id SET DEFAULT nextval('smer_seq');

AlTER TABLE projekat ALTER COLUMN id SET DEFAULT nextval('projekat_seq');

AlTER TABLE grupa ALTER COLUMN id SET DEFAULT nextval('grupa_seq');

AlTER TABLE student ALTER COLUMN id SET DEFAULT nextval('student_seq');