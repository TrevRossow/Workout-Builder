<template>
  <div id="exercise" class="testbox">
    <form class="form-signin" @submit.prevent="addExercise()">
      <h1 class="h3 mb-3 font-weight-normal">Create Exercise</h1>
      <div class="alert" role="alert" v-if="createError">
        Unable to create exercise.
      </div>

      <div
        class="success"
        v-if="createSuccess"
      >
        Exercise Submitted.
      </div>

      <label id="icon" for="name">
        <i><font-awesome-icon icon="fa-pen" /></i>
      </label>
      <input
        type="text"
        name="name"
        id="name"
        placeholder="-- Exercise Name --"
        v-model="exercise.name"
        required
        autofocus />
      <div>
        <label id="icon" for="name">
          <i><font-awesome-icon icon="fa-user" /></i>
        </label>
        <select name="drop-down" id="muscle-group" v-model="exercise.muscleGroup" required>
          <option value="" selected="selected" disabled="disabled">-- Muscle Group --</option>
          <option value="Chest">Chest</option>
          <option value="Back">Back</option>
          <option value="Biceps">Biceps</option>
          <option value="Triceps">Triceps</option>
          <option value="Shoulders">Shoulders</option>
          <option value="Legs">Legs</option>
          <option value="Legs">Total Body</option>
        </select>
      </div>
      <div>
        <label id="icon" for="name">
          <i><font-awesome-icon icon="fa-list-numeric" /></i>
        </label>
         <select 
          name="drop-down"
          v-model="exercise.repRange"
          placeholder="Exercise Type"
          required>
          <option value="" selected="selected" >-- Rep Ranges --</option>
          <option value="1 - 5">1 - 5</option>
          <option value="5 - 8">5 - 8</option>
          <option value="8 - 12">8 - 12</option>
          <option value="12+">12+</option>
        </select>
      </div>
      <div>
        <label id="icon" for="name">
          <i><font-awesome-icon icon="fa-dumbbell" /></i>
        </label>
        <select 
          name="drop-down"
          v-model="exercise.type"
          placeholder="Exercise Type"
          required>
          <option value="" selected="selected" >-- Exercise Type --</option>
          <option value="Strength">Strength</option>
          <option value="Strength">Cardio</option>
        </select>
      </div>
      <div>
        <textarea
          name="name"
          id="name"
          placeholder="Description"
          v-model="exercise.description"
          required
          autofocus
          rows="4"
          cols="50"
        />
      </div>
      <div class="send">
        <button type="submit">Create</button>
      </div>
    </form>
  </div>
</template>

<script>
import exerciseService from "../services/ExerciseService.js";
export default {
  name: "add-exercise",
  components: {},

  data() {
    return {
      exercise: {
        id: "",
        name: "",
        description: "",
        muscleGroup: "",
        repRange: "",
        type: "",
        statusId: 1,
      },

      createError: false,
      createSuccess:false,
    };
  },
  methods: {
    addExercise() {
      if (this.$store.state.user.authorities[0].name === "ROLE_TRAINER") {
        this.exercise.statusId = 2;
      } else {this.exercise.statusId = 1;
      }
      exerciseService
        .addExercise(this.exercise)
        .then((response) => {
          if (response.status == 200) {
            this.createSuccess = true;
            this.$store.commit("ADD_EXERCISE", this.exercise);
            this.exercise = {};
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status != 200) {
            this.createError = true;
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

}

.success{
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

input[type="text"] {
  width: 70%;
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

#muscle-group{
  margin-top: 10px;
}

select {
  width: 70%;
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
  display: inline-block;
  width: 40px;
  background-color: #3a57af;
  padding: 8px 8px 8px 12px;
  margin-left: 1rem;
  margin-right: 10px;
  color: white;
  box-shadow: 1px 2px 5px rgba(0, 0, 0, 0.09);
  border: solid 0px #cbc9c9;
  position: relative;
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

@media (max-width: 340px) {
  .testbox {
  max-width: 70vw;
}
  
  
}


</style>
