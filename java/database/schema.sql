BEGIN TRANSACTION;
DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id CASCADE;
DROP TABLE IF EXISTS trainers CASCADE;
DROP TABLE IF EXISTS trainer_user CASCADE;
DROP TABLE IF EXISTS exercises CASCADE;
DROP TABLE IF EXISTS workouts CASCADE;
DROP TABLE IF EXISTS exercise_status CASCADE;
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) UNIQUE NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE trainers (
	trainer_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) UNIQUE NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_trainer PRIMARY KEY (trainer_id)
);
CREATE TABLE trainer_user (
	trainer_user_id SERIAL NOT NULL,
	trainer_id int NOT NULL CONSTRAINT fk_trainers REFERENCES trainers(trainer_id),
	user_id int NOT NULL CONSTRAINT fk_users REFERENCES users(user_id)
);
CREATE TABLE exercise_status(
	exercise_status_id serial NOT NULL,
	exercise_status_description VARCHAR (20) NOT NULL,
	CONSTRAINT pk_exercise_status PRIMARY KEY(exercise_status_id)
);
CREATE TABLE exercises (
	exercise_id SERIAL PRIMARY KEY NOT NULL,
	exercise_name VARCHAR (50) NOT NULL UNIQUE,
	exercise_description VARCHAR (500) NOT NULL,
	muscle_group VARCHAR (50),
	rep_range int,
	exercise_type VARCHAR (50) NOT NULL,
	exercise_status_id int DEFAULT '1',
		CONSTRAINT fk_exercise_status FOREIGN KEY (exercise_status_id) 
		REFERENCES exercise_status (exercise_status_id)
	

);
CREATE TABLE workouts (
	workout_id SERIAL NOT NULL PRIMARY KEY,
	exercise_id int REFERENCES exercises (exercise_id),
	trainer_id int REFERENCES trainers (trainer_id),
	user_id int,
	completed bool

);


INSERT INTO exercise_status (exercise_status_description) VALUES ('Pending');
INSERT INTO exercise_status (exercise_status_description) VALUES ('Approved');
INSERT INTO exercise_status (exercise_status_description) VALUES ('Rejected');
INSERT INTO exercises (exercise_name,exercise_description,muscle_group,rep_range,exercise_type,exercise_status_id)
VALUES
    ('Stair Climbing', 'Go up steps.', 'Cardio', '10', 'Cardio', '2'),
    ('Elliptical Trainer', 'Fake skiing.', 'Cardio', '10', 'Cardio', '2'),
    ('Rowing Machine', 'Fake rowing.', 'Cardio', '10', 'Cardio', '2'),
    ('Stairmaster', 'Escalator', 'Cardio', '10', 'Cardio', '2'),
    ('Stationary Bicycle', 'Fake biking', 'Cardio', '10', 'Cardio', '2'),
    ('Front Squat', 'Squat.', 'Legs', '10', 'Strength', '2'),
    ('Leg Press', 'Press with legs.', 'Legs', '10', 'Strength', '2'),
    ('Lunge', 'Take a big low step.', 'Legs', '10', 'Strength', '2'),
    ('Calf Raise', 'Tippy-Toes.', 'Legs', '10', 'Strength', '2'),
    ('Walking Lunges', 'Take a lot of big low steps.', 'Legs', '10', 'Strength', '2'),
    ('Cable Crossover', 'Cross a cable', 'Chest', '10', 'Strength', '2'),
    ('Chest Dip', 'Dip your chest.', 'Chest', '10', 'Strength', '2'),
    ('Fly', 'Fly like bird with weight.', 'Chest', '10', 'Strength', '2'),
    ('Push Up', 'Push up from gound.', 'Chest', '10', 'Strength', '2'),
    ('Bent-over Row', 'Bend over and row.', 'Back', '10', 'Strength', '2'),
    ('Back Extension', 'Extend your back.', 'Back', '10', 'Strength', '2'),
    ('Deadlift', 'Workout at morgue.', 'Back', '10', 'Strength', '2'),
    ('Pullup', 'Pull yourself up', 'Back', '10', 'Strength', '2'),
    ('Lat Pulldown', 'Pull down a lat', 'Back', '10', 'Strength', '2'),
    ('Biceps Curl', 'Curl your  biceps.', 'Biceps', '10', 'Strength', '2'),
    ('Barbell Curl', 'Curl a barbell.', 'Arms', '10', 'Strength', '2'),
    ('Pushdown', 'Push it down.', 'Arms', '10', 'Strength', '2'),
    ('Chin-up', 'Get your chin up.', 'Arms', '10', 'Strength', '2'),
    ('Hammer Curl', 'Get your toolbox out.', 'Arms', '10', 'Strength', '2'),
    ('Preacher Curl', 'Curl a preacher.', 'Arms', '10', 'Strength', '2'),
    ('Triceps Extension', 'Extend your triceps.', 'Triceps', '10', 'Strength', '2'),
    ('Tricep Curl', 'Curl your triceps.', 'Triceps', '10', 'Strength', '2'),
    ('Kickbacks', 'Kick it back.', 'Triceps', '10', 'Strength', '2'),
    ('Rope Pushdown', 'Push down a rope.', 'Triceps', '10', 'Strength', '2'),
    ('Close Grip Bench Press', 'Close grip.', 'Triceps', '10', 'Strength', '2'),
    ('Crunches', '', 'Abs', '10', 'Strength', '2'),
    ('Flutter Kicks', '', 'Abs', '10', 'Strength', '2'),
    ('Toe Reach', '', 'Abs', '10', 'Strength', '2'),
    ('Russian Twist', '', 'Abs', '10', 'Strength', '2'),
    ('Bicycle Crunch', '', 'Abs', '10', 'Strength', '2');
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('trainer','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_TRAINER');
COMMIT TRANSACTION;

