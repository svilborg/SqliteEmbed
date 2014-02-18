#!/bin/bash

sqlite3 teams.db "BEGIN TRANSACTION;
CREATE TABLE 'android_metadata' ('locale' TEXT DEFAULT 'en_US');
INSERT INTO 'android_metadata' VALUES('en_US');
CREATE TABLE teams (
    '_id' INTEGER NOT NULL,
    'name' TEXT,
    'points' INTEGER NOT NULL DEFAULT (0)
);
INSERT INTO 'teams' VALUES(1,'Sweden',3);
INSERT INTO 'teams' VALUES(2,'Finland',8);
INSERT INTO 'teams' VALUES(3,'Iceland',2);
INSERT INTO 'teams' VALUES(4,'Norway',7);
INSERT INTO 'teams' VALUES(5,'Denmark',7);
COMMIT;"