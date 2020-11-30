insert into student (student_id, first_name, last_name, email, gender) values ('0709185d-18fd-4a30-b473-25c901a9b2f6', 'Bald', 'Credland', 'bcredland0@virginia.edu', 'MALE');
insert into student (student_id, first_name, last_name, email, gender) values ('0b6275e5-e797-4bb8-aa65-8cd3111c4e4f', 'Even', 'Alvares', 'ealvares1@tumblr.com', 'MALE');
insert into student (student_id, first_name, last_name, email, gender) values ('92aa94bb-b8d2-464b-9699-9c35e1752212', 'Chlo', 'Landal', 'clandal2@icq.com', 'FEMALE');
insert into student (student_id, first_name, last_name, email, gender) values ('aac186fc-64ce-486d-b490-c59704cbe925', 'Balduin', 'Sefton', 'bsefton3@sciencedaily.com', 'MALE');
insert into student (student_id, first_name, last_name, email, gender) values ('4752b8d4-0b30-4093-a6a8-003898d5a72a', 'Edin', 'Wiggins', 'ewiggins4@slashdot.org', 'FEMALE');
insert into student (student_id, first_name, last_name, email, gender) values ('561ea289-9388-4848-9000-e4b9a79912a0', 'Marlowe', 'Rambadt', 'mrambadt5@instagram.com', 'MALE');
insert into student (student_id, first_name, last_name, email, gender) values ('7154d043-ffaf-42c9-a011-22ba030db1d6', 'Isadore', 'Anglish', 'ianglish6@rambler.ru', 'MALE');


insert into course (course_id, name, description, department, teacher_name) values ('f7fe8a83-a7ac-4b78-9d7f-739517c9b7da', 'Data Structure', 'HashMap, TreeSet ...', 'Computer Science', 'HERBERT PIMLEY');
insert into course (course_id, name, description, department, teacher_name) values ('2a51938c-8ac4-44d9-b2e4-72683e6180b4', 'Spring Boot', 'Spring Boot', 'WEB Development', 'DER KANE');
insert into course (course_id, name, description, department, teacher_name) values ('65366768-a69b-404d-ac98-f6a8c407bdeb', 'Python', 'Machine Learning', 'Computer Science', 'IRENE POLDING');


insert into student_course (student_id, course_id, start_date, end_date, grade) values ('0709185d-18fd-4a30-b473-25c901a9b2f6', 'f7fe8a83-a7ac-4b78-9d7f-739517c9b7da', '2019-11-27', '2020-11-04', 70);
insert into student_course (student_id, course_id, start_date, end_date, grade) values ('0709185d-18fd-4a30-b473-25c901a9b2f6', '2a51938c-8ac4-44d9-b2e4-72683e6180b4', '2019-11-02', '2020-06-07', 71);
insert into student_course (student_id, course_id, start_date, end_date, grade) values ('0b6275e5-e797-4bb8-aa65-8cd3111c4e4f', 'f7fe8a83-a7ac-4b78-9d7f-739517c9b7da', '2019-11-11', '2020-03-08', 82);
insert into student_course (student_id, course_id, start_date, end_date, grade) values ('0b6275e5-e797-4bb8-aa65-8cd3111c4e4f', '2a51938c-8ac4-44d9-b2e4-72683e6180b4', '2019-11-01', '2020-08-23', 74);
insert into student_course (student_id, course_id, start_date, end_date, grade) values ('92aa94bb-b8d2-464b-9699-9c35e1752212', 'f7fe8a83-a7ac-4b78-9d7f-739517c9b7da', '2019-11-22', '2020-10-25', 86);
insert into student_course (student_id, course_id, start_date, end_date, grade) values ('aac186fc-64ce-486d-b490-c59704cbe925', '65366768-a69b-404d-ac98-f6a8c407bdeb', '2019-11-23', '2020-06-07', 81);
insert into student_course (student_id, course_id, start_date, end_date, grade) values ('aac186fc-64ce-486d-b490-c59704cbe925', '2a51938c-8ac4-44d9-b2e4-72683e6180b4', '2019-11-01', '2020-02-03', 89);
insert into student_course (student_id, course_id, start_date, end_date, grade) values ('aac186fc-64ce-486d-b490-c59704cbe925', 'f7fe8a83-a7ac-4b78-9d7f-739517c9b7da', '2019-11-14', '2020-04-04', 77);
insert into student_course (student_id, course_id, start_date, end_date, grade) values ('4752b8d4-0b30-4093-a6a8-003898d5a72a', '2a51938c-8ac4-44d9-b2e4-72683e6180b4', '2019-11-06', '2020-02-09', 64);
insert into student_course (student_id, course_id, start_date, end_date, grade) values ('561ea289-9388-4848-9000-e4b9a79912a0', '65366768-a69b-404d-ac98-f6a8c407bdeb', '2019-11-20', '2020-03-08', 72);
