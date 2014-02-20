
-- Insert Query
INSERT INTO 'teams' VALUES(6,'Poland', 5);

/*
 New Table
 */
 CREATE TABLE coaches (
    "_id" INTEGER NOT NULL,
    "name" TEXT,
    "years" INTEGER NOT NULL DEFAULT (0)
);