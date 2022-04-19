<template>
    <div id="exercise" class="testbox" >
    <form class="form-signin" @submit.prevent="addToWorkout()">
      <h1 class="h3 mb-3 font-weight-normal">Update Workout</h1>
      <div class="alert" role="alert" v-if="createError === true">
        Unable to edit exercise.
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.exercise"
      >
        Exercise edited.
      </div>

      <label id="icon" for="name">
        <i><font-awesome-icon icon="fa-pen" /></i>
      </label>
      <select 
        v-model="name"
          v-on:click="selectWorkout(name)"
          name="drop-down"
          placeholder="Exercise Type"
          required>
          <option value="" selected="selected" >-- Workout Names --</option>
           <option
            v-for="workout in this.$store.state.workouts"
            :key="workout.id"
          >
            {{ workout.name }}
          </option>
         
        </select>

      <div class="send">
        <button type="submit">Save</button>
        <button v-on:click="hideForm()">Cancel</button>
      </div>
    </form>
  </div>
</template>

<script>


export default {
  name: "add-workout",
  createError:false,
  data() {
    return {
      createError:false,

        name:{},

      workout: {
        workoutId: 1,
        name: "",
        exercises: [],
        focus: [],
        trainer: "",
        userId: this.$store.state.user.id,
        completed: false,
      },

      exercise:{}

    }
  },
  
  created(){
    this.exercise = this.$store.state.selectedExercise
  },


  methods:{
    hideForm(){
       this.$store.state.showAddWorkout = false;
    },

    addToWorkout(){
      let updateWorkout = this.$store.state.selectedWorkout[0].exercises.unshift(this.exercise)
        this.$store.commit('UPDATE_WORKOUT' , updateWorkout)
        this.hideForm();
    },

    selectWorkout(name){
    
      let workoutToAppend = this.$store.state.workouts.filter((workout) => {
        return workout.name === name
      }); 
       
       this.$store.commit('SELECT_WORKOUT', workoutToAppend)
       
    }
   
    }
  
  
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