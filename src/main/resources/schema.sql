DROP TABLE IF EXISTS style_feature;
DROP TABLE IF EXISTS image;
DROP TABLE IF EXISTS feature;
DROP TABLE IF EXISTS style;

CREATE TABLE style (
	style_id bigint NOT NULL AUTO_INCREMENT,
	style_name varchar (32) NOT NULL,
	country_of_origin varchar(32),
	oldest_recorded_date int ,
	typical_number_of_bobbins int,
	pillow_style varchar(32),
	PRIMARY KEY (style_id)
);

CREATE TABLE feature (
	feature_id bigint NOT NULL AUTO_INCREMENT,
	feature_name varchar(64),
	PRIMARY KEY(feature_id)
);

CREATE TABLE image (
	image_id bigint NOT NULL AUTO_INCREMENT,
	style_id bigint NULL,
	image_name varchar(64) NOT NULL,
	image_location varchar(128),
	PRIMARY KEY (image_id),
	FOREIGN KEY (style_id) REFERENCES style (style_id) ON DELETE CASCADE
);

CREATE TABLE style_feature (
	style_id bigint NOT NULL,
	feature_id bigint NOT NULL,
	FOREIGN KEY (style_id) REFERENCES style (style_id) ON DELETE CASCADE,
	FOREIGN KEY (feature_id) REFERENCES feature (feature_id) ON DELETE CASCADE
);