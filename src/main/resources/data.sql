INSERT INTO USERS VALUES (1,'a','',1,'$2a$10$RogPwRa0/JrnYbwLxLmSIOYkt1T7LRHyykPCSuEljwSnQQvF728eq');
INSERT INTO USERS VALUES (2,'b','',1,'$2a$10$ZsK6gN4rIdBQqCLdC9YTLewLXm3rVScrfnkpq/gzK6Z4/WyiZ8CIi');

INSERT INTO ROLE VALUES (1,'ADMIN');
INSERT INTO ROLE VALUES (2,'USER');
--
INSERT INTO USERS_ROLE VALUES (1,1);
INSERT INTO USERS_ROLE VALUES (2,2);

INSERT INTO LOCATOR VALUES (1,'adbec52c-ce60-4a29-85fc-d6cc4f8a0501','opis 1','2020-06-14 12:18:06.82','Nazwa 1',1);
INSERT INTO LOCATOR VALUES (2,'e44a6f70-9bf6-4e17-913e-686910c3d6af','opis 2','2020-08-23 12:18:06.82','Nazwa 2',1);

INSERT INTO LOCATION VALUES (1,0,'2020-06-14 12:18:06.82',52.162523,130, 21.044935,1);
INSERT INTO LOCATION VALUES (2,0,'2020-06-14 12:19:06.82',52.161813,131, 21.043848,1);
INSERT INTO LOCATION VALUES (3,0,'2020-06-14 12:20:06.82',52.159648,129, 21.045994,1);

INSERT INTO LOCATION VALUES (4,0,'2020-06-23 12:18:06.82',52.182523,130, 21.034935,2);
INSERT INTO LOCATION VALUES (5,0,'2020-06-23 12:19:06.82',52.181813,131, 21.033848,2);
INSERT INTO LOCATION VALUES (6,0,'2020-06-23 12:20:06.82',52.169648,129, 21.035994,2);

;