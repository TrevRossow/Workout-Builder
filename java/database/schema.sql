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
CREATE TABLE exercises (
	exercise_id SERIAL PRIMARY KEY NOT NULL,
	exercise_name VARCHAR (50) NOT NULL UNIQUE,
	exercise_description VARCHAR (50) NOT NULL,
	muscle_group VARCHAR (50),
	rep_range int,
	exercise_type VARCHAR (50) NOT NULL
		--CONSTRAINT fk_workouts FOREIGN KEY (workout_id) REFERENCES workouts (workout_id)

);
CREATE TABLE workouts (
	workout_id SERIAL NOT NULL PRIMARY KEY,
	exercise_id int REFERENCES exercises (exercise_id),
	trainer_id int REFERENCES trainers (trainer_id),
	user_id int,
	completed bool
	--CONSTRAINT pk_workouts REFERENCES workouts(workout_id)

);
CREATE TABLE exercise_status(
	exercise_status_id serial NOT NULL,
	exercise_status_description VARCHAR (20) NOT NULL
	--CONSTRAINT pk_exercise_status PRIMARY KEY(exercise_status_id)
);

INSERT INTO exercise_status (exercise_status_description) VALUES ('Pending');
INSERT INTO exercise_status (exercise_status_description) VALUES ('Approved');
INSERT INTO exercise_status (exercise_status_description) VALUES ('Rejected');
INSERT INTO exercises (exercise_name,exercise_description,muscle_group,rep_range,exercise_type)
VALUES
    ( 'Stair Climbing', 'Go up steps.', 'Cardio', '10', 'Cardio'),
    ( 'Elliptical Trainer', 'Fake skiing.', 'Cardio', '10', 'Cardio'),
    ( 'Rowing Machine', 'Fake rowing.', 'Cardio', '10', 'Cardio'),
    ( 'Stairmaster', 'Escalator', 'Cardio', '10', 'Cardio'),
    ( 'Stationary Bicycle', 'Fake biking', 'Cardio', '10', 'Cardio'),
    ( 'Front Squat', 'Squat.', 'Legs', '10', 'Strength'),
    ( 'Leg Press', 'Press with legs.', 'Legs', '10', 'Strength'),
    ( 'Lunge', 'Take a big low step.', 'Legs', '10', 'Strength'),
    ('Calf Raise', 'Tippy-Toes.', 'Legs', '10', 'Strength'),
    ( 'Walking Lunges', 'Take a lot of big low steps.', 'Legs', '10', 'Strength'),
    ( 'Bench Press', 'Press a bench.', 'Chest', '10', 'Strength'),
    ( 'Fly', 'Fly like bird with weight.', 'Chest', '10', 'Strength'),
    ( 'Push Up', 'Push up from gound.', 'Chest', '10', 'Strength'),
    ( 'Bent-over Row', 'Bend over and row.', 'Back', '10', 'Strength'),
    ( 'Back Extension', 'Extend your back.', 'Back', '10', 'Strength'),
    ( 'Deadlift', 'Workout at morgue.', 'Back', '10', 'Strength'),
    ( 'Pullup', 'Pull yourself up', 'Back', '10', 'Strength'),
    ( 'Lat Pulldown', 'Pull down a lat', 'Back', '10', 'Strength'),
    ( 'Cable Crossover', 'Cross a cable', 'Chest', '10', 'Strength'),
    ( 'Chest Dip', 'Dip your chest.', 'Chest', '10', 'Strength'),
    ( 'Biceps Curl', 'Curl your  biceps.', 'Arms', '10', 'Strength'),
    ( 'Dip', 'When I dip you dip we dip', 'Arms', '10', 'Strength'),
    ( 'Pushdown', 'Push it down.', 'Arms', '10', 'Strength'),
    ( 'Triceps Extension', 'Extend your triceps.', 'Arms', '10', 'Strength'),
    ( 'Tricep Curl', 'Curl your tricpes', 'Arms', '10', 'Strength');
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('trainer','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_TRAINER');
COMMIT TRANSACTION;