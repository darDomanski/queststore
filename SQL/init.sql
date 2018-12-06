CREATE TABLE active_sessions (
userLogin text PRIMARY KEY,
sessionId text UNIQUE,
userType text);

CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    firstName text,
    nickname text,
    phone text,
    email text,
    quests integer[3],
    artifacts integer[3],
    classroom_artifacts integer[3],
    classroom text,
    cool_coins integer,
    experience integer);

CREATE TABLE mentors (
    id SERIAL PRIMARY KEY,
    firstName text,
    nickname text,
    phone text,
    email text,
    classroom text
);

CREATE TABLE quests (
    id SERIAL PRIMARY KEY,
    firstName text,
    category text,
    price integer
);

CREATE TABLE artifacts (
    id SERIAL PRIMARY KEY,
    firstName text,
    category text,
    price integer
);

CREATE TABLE classroom_artifacts (
    id SERIAL PRIMARY KEY,
    firstName text,
    category text,
    price integer,
    members text[4],
    payStatus boolean,
    activeStatus boolean
);

CREATE TABLE login (
    nickname text,
    haslo text,
    typ text 
);


INSERT INTO students VALUES(
	DEFAULT,
    'Krzys',
    'Puchatek',
    '444-333-222',
    'mis@oe.pl',
    '{1,44,3,6,7,54}',
    '{61,3,4,6,5,44}',
    '{51,24,3,6,7,43}',
    'B1',
    '100',
    '280'
);

INSERT INTO students VALUES (
    DEFAULT,
    'John',
    'Terry',
    '243-234-111',
    'chelsea@mes.uk',
    '{1,4,3,6,7,5}',
    '{6,3,4}',
    '{1,4,3}',
    'C1',
    '10',
    '20'
);

INSERT INTO students VALUES (
    DEFAULT,
    'Bill',
    'Cosby',
    '999-113-777',
    'bill@oe.pl',
    '{1}',
    '{61,4}',
    '{6,7}',
    'G1',
    '2',
    '28'
);

INSERT INTO mentors VALUES (
    DEFAULT,
    'Marek Grzybek',
    'Cain',
    '545-654-322',
    'grzybekn@codecool.pl',
    'A1'
);

INSERT INTO mentors VALUES (
    DEFAULT,
    'Piotr Tomaszewski',
    'Tomasz',
    '235-254-122',
    'ptomasz@codecool.pl',
    'B1'
);

INSERT INTO mentors VALUES (
    DEFAULT,
    'Frank Lampard',
    'Lampi',
    '945-884-722',
    'flampard@codecool.pl',
    'C1'
);

INSERT INTO quests VALUES (
    DEFAULT,
    'Master of morning',
    'Basic',
    '10'
);

INSERT INTO quests VALUES (
    DEFAULT,
    'Killl dragon',
    'Hard',
    '100'
);

INSERT INTO quests VALUES (
    DEFAULT,
    'Give feedback',
    'Basic',
    '5'
);

INSERT INTO artifacts VALUES (
    DEFAULT,
    'Sword',
    'Basic',
    '20'
);

INSERT INTO artifacts VALUES (
    DEFAULT,
    'Axe',
    'Basic',
    '30'
);

INSERT INTO artifacts VALUES (
    DEFAULT,
    'Magic Helmet',
    'Magic',
    '90'
);

INSERT INTO classroom_artifacts VALUES (
    DEFAULT,
    'mEETUP',
    'OutSchool',
    '50',
    '{john,bart,bill}',
    'FALSE',
    'FALSE'
    );

INSERT INTO login VALUES(
    'Puchatek',
    'Honey',
    'student'
);

INSERT INTO login VALUES(
    'Terry',
    'chelsea',
    'student'
);

INSERT INTO login VALUES(
    'Cosby',
    'usa',
    'student'
);

INSERT INTO login VALUES(
    'Cain',
    'diablo',
    'mentors'
);

INSERT INTO login VALUES(
    'Tomasz',
    'java',
    'mentors'
);

INSERT INTO login VALUES(
    'Lampi',
    'chelsea',
    'mentors'
);

 