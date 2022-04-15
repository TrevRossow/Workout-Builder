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
    exercise_name VARCHAR  NOT NULL UNIQUE,
    exercise_description TEXT NOT NULL,
    muscle_group VARCHAR ,
    rep_range VARCHAR,
    exercise_type VARCHAR  NOT NULL,
    exercise_status_id int DEFAULT '1',
        CONSTRAINT fk_exercise_status_exercise_status FOREIGN KEY (exercise_status_id)
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
    ('Stair Climbing', 'Find some stairs and climb up and down. Great for inside or outside!', 'Cardio', '10 min', 'Cardio', '2'),
    ('Elliptical Trainer', 'An elliptical trainer or cross-trainer is a stationary exercise machine used to stair climb, walk, or run without causing excessive pressure to the joints, hence decreasing the risk of impact injuries.', 'Cardio', '5 min', 'Cardio', '2'),
    ('Rowing Machine', 'An indoor rower, or rowing machine, is a machine used to simulate the action of watercraft rowing for the purpose of exercise or training for rowing. ', 'Cardio', '1 min', 'Cardio', '2'),
    ('Stairmaster', 'In simple terms, a StairMaster is a stationary fitness machine that rotates steps, similar to a treadmill, allowing the user to climb upward at the speed and duration he or she sets. It can provide an above-average cardio workout, while also toning lower-body muscles.', 'Cardio', '5 min', 'Cardio', '2'),
    ('Stationary Bicycle', 'A stationary exercise bike is a familiar piece of cardiovascular equipment that is generally easy to use. Bikes provide a no-impact aerobic workout that can strengthen and tone the muscles of the legs and buttocks.', 'Cardio', '10 min', 'Cardio', '2'),

    ('Squat', 'Place your feet so that they are a little wider than shoulder-width apart. Sit back into hips and keep the back straight and the chest up, squatting down so the hips are below the knees. From the bottom of the squat, press feet into the ground and push hips forward to return to the top of the standing position.', 'Legs', '5 - 8', 'Strength', '2'),
    ('Leg Press', 'The leg press is a weight training exercise in which the individual pushes a weight or resistance away from them using their legs.', 'Legs', '8 - 12', 'Strength', '2'),
    ('Barbell Lunge', 'Put barbell on the back of your shoulders. Stand upright, then take the first step forward. Step should bring you forward so that your supporting legs knee can touch the floor. Then stand back up and repeat with the other leg.', 'Legs', '8 - 12', 'Strength', '2'),
    ('Calf Raise', 'Calf raises are a method of exercising the gastrocnemius, tibialis posterior and soleus muscles of the lower leg. The movement performed is plantar flexion, a.k.a. ankle extension.', 'Legs', '12+', 'Strength', '2'),
    ('Dumbbell Lunges', 'Take two dumbbells in your hands, stand straight, feet about shoulder wide. Take one long step so that the front knee is approximately forming a right angle. The back leg is stretched, the knee is low but Does not touch the ground. Complete the step by standing up and repeat the movement with the other leg.', 'Legs', '8 - 12', 'Strength', '2'),

    ('Cable Crossover', 'Begin with cables at about shoulder height, one in each hand. Take a step forward so that one foot is in front of the other, for stability, and so that there is tension on the cables. Bring hands together in front of you. Try to make your hands overlap (so that the cables cross) a few inches.', 'Chest', '8 - 12', 'Strength', '2'),
    ('Bench Press', 'Lay down on a bench, the bar should be directly above your eyes, the knees are somewhat angled and the feet are firmly on the floor. Concentrate, breath deeply and grab the bar more than shoulder wide. Bring it slowly down till it briefly touches your chest at the height of your nipples. Push the bar up.', 'Chest', '5 - 8', 'Strength', '2'),
    ('Fly With Dumbbells', 'Take two dumbbells and lay on a bench, make sure the feet are firmly on the ground and your back is not arched, but has good contact with the bench. The arms are stretched in front of you, about shoulder wide. Bend now the arms a bit and let them down with a half-circle movement to the side. Without changing the angle of the elbow bring them in a fluid movement back up.', 'Chest', '12+', 'Strength', '2'),
    ('Push Up', 'Start with your body stretched, your hands are shoulder-wide apart on the ground. Push yourself off the ground till you strech your arms. The back is always straight and as well as the neck (always look to the ground). Lower yourself to the initial position and repeat.', 'Chest', '12+', 'Strength', '2'),

    ('Bent Over Rowing','Grab the barbell with a wide grip (slightly more than shoulder wide) and lean forward. Your upper body is not quite parallel to the floor, but forms a slight angle. The chest is out during the whole exercise. Pull now the barbell with a fast movement towards your belly button, not further up. Go slowly down to the initial position.', 'Back', '5 - 8', 'Strength', '2'),
    ('Back Extension', 'Slowly extend your back while keeping tension in your core. Slowly return to the starting position', 'Back', '8 - 12', 'Strength', '2'),
    ('Deadlift','Stand firmly, with your feet slightly more than shoulder wide apart. Stand directly behind the bar where it should barely touch your shin, your feet pointing a bit out. Bend down with a straight back, the knees also pointing somewhat out. Grab the bar with a shoulder wide grip, one underhand, one reverse grip.Pull the weight up. At the highest point make a slight hollow back and pull the bar back.', 'Back', '5 - 8', 'Strength', '2'),
    ('Pullup', 'Grab the pull up bar with a wide grip, the body is hanging freely. Keep your chest out and pull yourself up till your chin reaches the bar or it touches your neck, if you want to pull behind you. Go with a slow and controlled movement down, always keeping the chest out.', 'Back', '8 - 12', 'Strength', '2'),
    ('Lying Hip Raise', 'Lying down on your back, with your feet flat on the floor. Raise your hips up evenly as high as you can and hold for as long as you can.', 'Back', '2 min', 'Strength', '2'),

    ('Biceps Curls With Dumbbell','Hold two barbells, the arms are stretched, the hands are on your side, the palms face inwards. Bend the arms and bring the weight with a fast movement up. At the same time, rotate your arms by 90 degrees at the very beginning of the movement. At the highest point, rotate a little the weights further outwards. Without a pause, bring them down, ', 'Biceps', '8 - 12', 'Strength', '2'),
    ('Biceps Curls With Barbell','Hold the Barbell shoulder-wide, the back is straight, the shoulders slightly back, the arms are stretched. Bend the arms, bringing the weight up, with a fast movement. Without pausing, let down the bar with a slow and controlled movement.Do not allow your body to swing during the exercise, all work is done by the biceps, which are the only muscles that should move (pay attention to the elbows).', 'Biceps', '8 - 12', 'Strength', '2'),
    ('Chin-up', 'Grab the bar with hands about shoulder-width apart. While focusing on your lats, pull your chest up to the bar.', 'Biceps', '8 - 12', 'Strength', '2'),
    ('Hammer Curls','Hold two dumbbells and sit on a bench with a straight back, the shoulders are slightly rolled backwards. Your pals point to your body. Bend the arms and bring the weight up with a fast movement. Do not rotate your hands, as with the curls. Without any pause bring the dumbbell down with a slow, controlled movement. Do not swing your body during the exercise, the biceps should do all the work here.', 'Biceps', '8 - 12', 'Strength', '2'),
    ('Preacher Curl', 'Place the EZ curl bar on the rest handles in front of the preacher bench. Lean over the bench and grab the EZ curl bar with palms up. Sit down on the preacher bench seat so your upper arms rest on top of the pad and your chest is pressed against the pad. Lower the weight until your elbows are extended and arms are straight. Bring the weights back up to the starting point by contracting biceps. Repeat', 'Biceps', '8 - 12', 'Strength', '2'),

    ('Dumbbell Triceps Extension', 'Position one dumbbell over head with both hands under inner plate (heart shaped grip). With elbows over head, lower forearm behind upper arm by flexing elbows. Flex wrists at bottom to avoid hitting dumbbell on back of neck. Raise dumbbell over head by extending elbows while hyperextending wrists. Return and repeat.', 'Triceps', '8 - 12', 'Strength', '2'),
    ('Seated Triceps Press', 'Sit down on a back (better with back support). Take a dumbbell firmly with both hands and hold it with extended arms over your head. With your palms facing upward and holding the weight of the dumbbell, slowly lower the weight behind your head.', 'Triceps', '8 - 12', 'Strength', '2'),
    ('Dips Between Two Benches', 'Put two benches so far apart, that you can hold onto one with your hands and are just able to reach the other with your feet. The legs stay during the exercise completely stretched. With your elbows facing back, bend them as much as you can. Push yourself up, but Do not stretch out the arms.', 'Triceps', '12+', 'Strength', '2'),
    ('Kickback ', 'Prop the right foot on a step or platform, resting the right forearm on the thigh to support the back, or allowing the arm to drop directly below the shoulder. Hold a weight in the left hand and pull the elbow up to torso level. Keeping the elbow in that position, extend the arm behind you, focusing on contracting the triceps muscle. Lower the forearm down to about 90 degrees and repeat', 'Triceps', '12+', 'Strength', '2'),
    ('Close Grip Bench Press', 'Lie on a bench or step holding a barbell with hands about shoulder-width apart. Begin the exercise with the elbows bent and the barbell hovering just over the ribcage. Press the weight straight up over the ribcage, focusing on contracting the triceps. Lower and repeat.', 'Triceps', '5 - 8', 'Strength', '2'),

    ('Crunches', 'Lay down on your back a soft surface, the feet are on the floor. Ask a partner or use some other help (barbell, etc.) to keep them fixed, your hands are behind your head. From this position move your upper body up till your head or elbows touch your knees. Do this movement by rolling up your back.', 'Abs', '12+', 'Strength', '2'),
    ('Flutter Kicks', 'Laying on the back, lift your straightened legs from the ground at a 45 degree angle. As your Left foot travels downward and nearly touches the floor, your Right foot should seek to reach a 90 degree angle, or as close to one as possible. Bring your R foot down until it nearly touches the floor, and bring your L foot upwards.  Maintain leg rigidity throughout the exercise', 'Abs', '12+', 'Strength', '2'),
    ('Cable Woodchoppers', 'Set cable pulley slightly lower than chest height. Keep body facing forward with hips stable. Grab the pulley handle, fully extend your arms and bring your arms forward and across your body. Hold for 1 second at the end of the movement and slowly return to starting position.', 'Abs', '12+', 'Strength', '2'),
    ('Russian Twist', ' Get in a a position if you were to do a crunch, Ideally, the torso is kept straight with the back kept off the ground at a 45 degree angle with arms held together away from the body in a straight fashion. Next, the arms should be swung from one side to another in a twisting motion, with each swing to a side counting as one repetition.', 'Abs', '12+', 'Strength', '2'),
    ('Hollow Hold', 'Get on a mat and lie on your back. Contract your abs, stretch your raise and legs and raise them (your head and shoulders are also be raised). Make sure your lower back remains in contact with the mat.', 'Abs', '2 min', 'Strength', '2');
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('trainer','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_TRAINER');
COMMIT TRANSACTION;