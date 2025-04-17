
INSERT INTO style (style_name, country_of_origin, oldest_recorded_date, typical_number_of_bobbins, pillow_style)
	VALUES ('Idrija', 'Slovenia', 1750, 10,'bolster');
INSERT INTO style (style_name, country_of_origin, oldest_recorded_date, typical_number_of_bobbins, pillow_style)
	VALUES ('Honiton', 'England', 1750, 20,'cookie');
INSERT INTO style (style_name, country_of_origin, oldest_recorded_date, typical_number_of_bobbins, pillow_style)
	VALUES ('Flanders', 'Belgium', 1750, 100,'cookie');

INSERT INTO image (image_name, image_location,style_id) VALUES ('Idrija sun', 'My Computer - Pictures dir',1);
INSERT INTO image (image_name, image_location,style_id) VALUES ('Idrija moon', 'My Computer - Pictures dir',1);
INSERT INTO image (image_name, image_location,style_id) VALUES ('Flanders rose', 'My Computer - Pictures dir',3);


insert into style_feature (style_id, feature_id) values (1,4);
insert into style_feature (style_id, feature_id) values (1,10);
insert into style_feature (style_id, feature_id) values (1,14);
insert into style_feature (style_id, feature_id) values (2,3);
insert into style_feature (style_id, feature_id) values (2,7);
insert into style_feature (style_id, feature_id) values (3,2);
insert into style_feature (style_id, feature_id) values (3.9);
insert into style_feature (style_id, feature_id) values (3,12);