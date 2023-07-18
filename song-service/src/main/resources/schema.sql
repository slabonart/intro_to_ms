create table song_meta_data (
  ID int not null AUTO_INCREMENT,
  RESOURCE_ID int not null,
  NAME varchar(256) not null,
  ARTIST varchar(256),
  ALBUM varchar(256),
  LENGTH varchar(128),
  YEAR varchar(4),
  PRIMARY KEY ( ID )
);