create table song_resource (
  ID int not null AUTO_INCREMENT,
  RESOURCE MEDIUMBLOB not null,
  FILE_NAME varchar(256) not null,
  PRIMARY KEY ( ID )
);