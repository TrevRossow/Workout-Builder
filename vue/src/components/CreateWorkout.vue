<template>
  <div id="exercise" class="testbox">
    <form class="form-signin" @submit.prevent="pushWorkout()">
      <h1 class="h3 mb-3 font-weight-normal">Create Workout</h1>
      <div class="alert" role="alert" v-if="createError">
        Unable to Generate Workout. <span>{{errorMsg}}</span>
      </div>

      <div class="success" v-if="createSuccess">Workout Submitted.</div>

      <label id="icon" for="name">
        <i><font-awesome-icon icon="fa-pen" /></i>
      </label>
      <input
        type="text"
        name="name"
        id="name"
        placeholder="-- Workout Name --"
        autocomplete="off"
        v-model="workout.workoutName"
        required
        autofocus
      />

      <div class="focusDiv">
        <label id="icon" for="chest">
          <input
            id="chest"
            type="checkbox"
            value="Chest"
            v-if="checkRandom === false"
            v-model="checkBoxes"
          />
          Chest
        </label>

        <label id="icon" for="back">
          <input
            id="back"
            type="checkbox"
            v-if="checkRandom === false"
            value="Back"
            v-model="checkBoxes"
          />
          Back
        </label>

        <label id="icon" for="shoulders">
          <input
            id="shoulders"
            type="checkbox"
            value="Shoulders"
            v-model="checkBoxes"
            v-if="checkRandom === false"
          />
          Shoulders
        </label>

        <label id="icon" for="biceps">
          <input
            id="biceps"
            type="checkbox"
            value="Biceps"
            v-model="checkBoxes"
            v-if="checkRandom === false"
          />
          Biceps
        </label>

        <label id="icon" for="triceps">
          <input
            id="triceps"
            type="checkbox"
            value="Triceps"
            v-model="checkBoxes"
            v-if="checkRandom === false"
          />
          Triceps
        </label>
        <label id="icon" for="legs">
          <input
            id="legs"
            type="checkbox"
            v-if="checkRandom === false"
            value="Legs"
            v-model="checkBoxes"
          />
          Legs
        </label>

        <label id="icon" for="abs">
          <input
            id="abs"
            type="checkbox"
            v-if="checkRandom === false"
            value="Abs"
            v-model="checkBoxes"
          />
          Abs
        </label>

        <label id="icon" for="cardio">
          <input
            id="cardio"
            type="checkbox"
            value="Cardio"
            v-model="checkBoxes"
            v-if="checkRandom === false"
          />
          Cardio
        </label>
        
        <label id="icon" for="totalbody">
          <input
            id="toatalbody"
            type="checkbox"
            value="TotalBody"
            v-model="checkBoxes"
             v-if="checkRandom === false"
          />
          Total Body
        </label>
        <label id="icon" for="random" >
          <input
            id="random"
            type="checkbox"
            value="Random"
            v-model="checkBoxes"
          />
          Random
        </label>
      </div>
      <div>
        <label id="icon" for="name">
          <i><font-awesome-icon icon="fa-user" id="user-icon" /></i>
        </label>
        <select
          name="drop-down"
          placeholder="Exercise Type"
          required
          v-model="workout.trainerId"
          v-on:click="generateWorkout(checkBoxes)"
        >
          <option value="" disabled selected>-- Trainer --</option>
          <option
           :value= "trainer.id"
            v-for="trainer in this.trainers"
            :key="trainer.id"
          >
            {{ trainer.username }}
          </option>
        </select>
      </div>
      <div class="send">
        <button type="submit">Create</button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';
import ExerciseService from "../services/ExerciseService";
import workoutService from "../services/WorkoutService";

export default {
  name: "create-workout",
  components: {},

  data() {
    return {

      errorMsg:"",

      trainers: [],

      trainer: {},

      checkBoxes: [],

      tempArr: [],

      workout: {
        workoutName: "",
        trainerId: null,
        userId: this.$store.state.user.id,
        completed: false,
        dateCompleted: null,
      },

      createError: false,
      createSuccess: false,
    };
  },

  created() {
    this.getTrainers();
  },
  computed: {
    checkRandom() {
      if (this.checkBoxes.includes("Random")) {
        return true;
      } else return false;
    },
  },

  methods: {
    getTrainers(){
      authService.getUsers().then((response)  => {
        if (response.status == 200) {
          let users = response.data;
          this.trainers = users.filter((u) => {
            return u.authorities[0].name === "ROLE_TRAINER"
          })
        }
        })
    },
    generateWorkout(focusArr) {
      let ids = [];

      if (focusArr.includes("Random")) {
        focusArr = [
          "Chest",
          "Back",
          "Biceps",
          "Triceps",
          "Abs",
          "Legs",
          "Cardio",
          "TotalBody"
        ];
      } else if (focusArr.length === 0) {
        this.createError = true;
      }

      for (let i = 0; i < focusArr.length * 3; i++) {
        let exerciseFocus =
          focusArr[Math.floor(Math.random() * focusArr.length)];

        ExerciseService.getExercisesByMuscleGroup(exerciseFocus).then(
          (response) => {
          if(response.status == 200){
            let exercises = response.data;

            let exercise =
              exercises[[Math.floor(Math.random() * exercises.length)]];

            ids.push(exercise);

            this.tempArr = ids;
          }
          })
          .catch((error) => {
            const response = error.response;
            this.createError = true;
            if (response.status != 400) {
              this.errorMsg = 'No Exercises Available!';
            }
          });
      }
    },

    pushWorkout() {
      const map = {};
      const newArray = [];
      this.tempArr.forEach((element) => {
        if (!map[JSON.stringify(element)]) {
          map[JSON.stringify(element)] = true;
          newArray.push(element);
        }
      });
      this.tempArr = newArray;
   

      workoutService.addWorkout(this.workout).then((response)  => {
        console.log(this.workout)
        if (response.status == 200) {
          this.createSuccess = true;

          let newWorkoutId = response.data.id;
          
           this.tempArr.forEach((exercise) => {
             let temp = {
               workoutId:newWorkoutId,
               exerciseId:exercise.id 
             }
            workoutService
              .sendExercises(newWorkoutId, temp)
          });  
        }
      });
    },
  },
};
</script>
<style scoped>
h1 {
  font-size: 32px;
  font-weight: 300;
  color: #4c4c4c;
  text-align: center;
  padding-top: 10px;
  margin-bottom: 10px;
  max-width: 100%;
}
.focusDiv {
  display: inline-flex;
  flex-wrap: wrap;
  width: 100%;
}
.testbox {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin: 20px auto;
  border-radius: 8px/7px;
  background-color: #ebebeb;
  box-shadow: 1px 2px 5px black;
  border: solid 1px #cbc9c9;
  max-width: 60vw;
  min-width: 300px;
}

.success {
  box-shadow: 1px 2px 5px black;
  text-align: center;
  font-size: 14px;
  font-weight: 600;
  background-color: #3a57af;
  color: #ebebeb;
  border-radius: 5px;
  backface-visibility: hidden;
  perspective: 1000px;
}

.alert {
  box-shadow: 1px 2px 5px black;
  text-align: center;
  font-size: 14px;
  font-weight: 600;
  background-color: rgb(240, 52, 52);
  color: #ebebeb;
  border-radius: 5px;
  animation: shake;
  animation: shake 0.82s cubic-bezier(0.36, 0.07, 0.19, 0.97) both;
  transform: translate3d(0, 0, 0);
  backface-visibility: hidden;
  perspective: 1000px;
}

@keyframes shake {
  10%,
  90% {
    transform: translate3d(-1px, 0, 0);
  }

  20%,
  80% {
    transform: translate3d(2px, 0, 0);
  }

  30%,
  50%,
  70% {
    transform: translate3d(-4px, 0, 0);
  }

  40%,
  60% {
    transform: translate3d(4px, 0, 0);
  }
}

hr {
  color: #f86c6c;
  opacity: 0.3;
}

form {
  margin: 0 30px;
  max-width: 100%;
}

input[type="checkbox"] {
  margin-left: 5px;
}

input[type="text"] {
  width: 60%;
  height: 39px;
  border-radius: 0px 4px 4px 0px/5px 5px 4px 4px;
  background-color: #fff;
  box-shadow: 1px 2px 5px black;
  border: solid 1px #cbc9c9;
  margin-left: -5px;
  margin-top: 20px;
  padding-left: 0px;
  max-width: 100%;
  text-align: center;
}

#muscle-group {
  margin-top: 10px;
}

select {
  width: 50%;
  height: 39px;
  border-radius: 0px 4px 4px 0px/5px 5px 4px 4px;
  background-color: #fff;
  box-shadow: 1px 2px 5px black;
  border: solid 1px #cbc9c9;
  margin-left: -5px;
  margin-top: 20px;
  padding-left: 10px;
  max-width: 100%;
}

textarea {
  border-radius: 0px 4px 4px 0px/5px 5px 4px 4px;
  background-color: #fff;
  box-shadow: 1px 2px 5px black;
  border: solid 1px #cbc9c9;
  margin-top: 13px;
  margin-left: 10px;
  padding-left: 10px;
  max-height: 200px;
  max-width: 90%;
}

input {
  margin-bottom: 15px;
}

#icon {
  width: 120px;
  background-color: #3a57af;
  border-radius: 5px;
  padding: 8px 8px 8px 12px;
  margin: 1rem;
  color: white;
  box-shadow: 2px 3px 3px rgb(58 87 175 / 75%);
  border: solid 1px #cbc9c9;
  position: relative;
}
#user-icon {
  margin-right: 4px;
}

.send {
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
  margin: 90px;
}

button {
  font-size: 14px;
  font-weight: 600;
  color: white;
  text-decoration: none;
  width: 100px;
  height: 30px;
  border-radius: 5px;
  background-color: #3a57af;
  box-shadow: 0 3px rgba(58, 87, 175, 0.75);
  align-self: center;
  margin-bottom: 10px;
}

button :hover {
  top: 3px;
  background-color: black;
  border: #4c4c4c;
}

@media only screen and (max-width: 600px) {
  .testbox {
    max-width: 70vw;
  }

  #icon {
    width: 200%;
  }
}
</style>