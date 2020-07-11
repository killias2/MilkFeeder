CREATE TABLE users (
        userid  serial  CONSTRAINT pk_userid    PRIMARY KEY,
        username        varchar(16)     NOT NULL
);

CREATE TABLE babies (
        babyid  serial  CONSTRAINT pk_babyid    PRIMARY KEY,
        babyfirstname   varchar(20)     NOT NULL,
        babymiddleinitial       char,
        babylastname    varchar(30)     NOT NULL
);

CREATE TABLE user_baby (
        userid  int,
        babyid  int,
        CONSTRAINT fk_userid    FOREIGN KEY (userid) REFERENCES users(userid),
        CONSTRAINT fk_babyid    FOREIGN KEY (babyid) REFERENCES babies(babyid)
        );
        
CREATE TABLE feedings (
        feedingid       serial  CONSTRAINT pk_feedingid PRIMARY KEY,
        babyid int,
        userid int,
        isbreastfeeding   boolean,
        bottleouncesamount    double precision,
        feedingdate     date    NOT NULL,
        feedingtime     time    NOT NULL,
        CONSTRAINT fk_babyid    FOREIGN KEY (babyid) REFERENCES babies(babyid),
        CONSTRAINT fk_userid    FOREIGN KEY (userid) REFERENCES users(userid)
);

ALTER TABLE users ADD CONSTRAINT un_username UNIQUE (username);

INSERT INTO users (username) VALUES('killias2');
INSERT INTO babies (babyfirstname, babymiddleinitial, babylastname) VALUES('Zeke', 'T', 'Killian');
INSERT INTO user_baby (userid, babyid) VALUES(1,1);
INSERT INTO feedings (babyid, userid, isbreastfeeding, bottleouncesamount, feedingdate, feedingtime) VALUES(1, 1, false, 8.0, '2020-07-07', '07:00 AM');

SELECT b.babyid, babyfirstname, babymiddleinitial, babylastname FROM babies b JOIN user_baby ub on b.babyid = ub.babyid JOIN users u ON ub.userid = u.userid WHERE u.userid = 1;

SELECT feedingid, babyid, userid, isbreastfeeding, bottleouncesamount, feedingdate, feedingtime FROM feedings WHERE userid = 1 ORDER BY feedingdate, feedingtime;