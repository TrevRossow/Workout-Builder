<template>
  <div class="main">
      <div class="exerciseDiv"  >
      <div id="head">
         <h2  v-show="workout.completed == true"> Recently Completed </h2>
          <h2  v-show="workout.userId && workout.completed == false"> Recently Added </h2>
         <h2 v-show="!workout.userId"> Get Started</h2>
      </div>
      <div id="imgDiv">
        <div id="maininfo">
          <h2 >{{ workout.workoutName }}</h2>
          <h6 v-if="workout.completed"> Completed On : {{workout.dateCompleted}} </h6>
          <h4 class="group" v-show="workout.userId"> Trainer: {{ workout.trainer}} </h4>
          <h5 class="type" v-show="workout.userId">User Id: {{ workout.userId }}</h5>
          <br />
          <v-carousel
            class="carousel"
            height="200px"
            hide-delimiter-background
            hide-delimiters
            show-arrows-on-hover
          >
            <v-carousel-item
              id="carocards"
              v-for="exercise in workout.exercises"
              :key="exercise.id"
            >
              <h4 class="reps">{{ exercise.name }}</h4>
              <div vr id="imgDiv">
                <div id="info">
                  <h4 class="group">{{ exercise.muscleGroup }}</h4>
                  <h5 class="type" v-show="exercise.type != 'Cardio'">
                    {{ exercise.type }}
                  </h5>
                  <h6  v-show="exercise.type != 'Cardio'">Reps:</h6>
                  <h4 class="reps" v-show="exercise.type != 'Cardio'">
                    {{ exercise.repRange }}
                  </h4>
                  <h6>Time:</h6>
                  <h4 class="time">{{ exercise.timeRange }} Mins</h4>
                </div>
                <img
                  class="img"
                  :src="`../WorkoutImages/${exercise.muscleGroup}.png`"
                />
              </div>
            </v-carousel-item>
          </v-carousel>
        </div>
      </div>
      </div>
    </div>
</template>

<script>


import workoutService from "../services/WorkoutService";
import exerciseService from "../services/ExerciseService";
import authService from "../services/AuthService";

export default {
  name: "summary-info",

    data() {
    return {
      showDateTime: null,

      noRecent:false,

      date: null,

      workout: {},
    }
    
    
    },
created(){

  this.checkStore()
  this.getCurrentDatetime();

  this.getLastWorkout();
},

    computed:{
       getName(){
        return this.$store.state.user.username
      },

      getId(){
         return this.$store.state.user.id
      },

    },


  methods: {

    checkStore(){

        if(!this.$store.state.recentWorkout){
          this.noRecent = false;
        }else{
          this.noRecent = true;
        }
      },
    getLastWorkout() { 
        if(this.noRecent){
       workoutService.getLatestWorkout(this.$store.state.user.id).then((response)  => {
         let workout = response.data;
          exerciseService
            .getExercisesByWorkouts(workout.id)
            .then((response) => {
            let exercises = response.data
            workout.exercises = exercises;
            authService.getUserNameById(workout.trainerId).then((response) => {
                workout.trainer = response.data.username
          this.$store.commit('SET_RECENT', workout)
          this.workout = this.$store.state.recentWorkout;
          this.createSuccess = true;
            })
        })
       })
      }
    },
    
    getCurrentDatetime() {
      let dateTime = new Date();
      this.date = dateTime.toJSON().slice(0, 10).replace(/-/g, "/");
    },
  }
};
</script>

<style scoped>
.main {
  display: flex;
  justify-content: center;
  width: max-content;
  max-height: max-content;
}

#imgDiv {
  display: flex;
  justify-content: space-between;
  justify-content: center;
  align-content: center;
}

#head {
  text-align: center;
}

#info {
  margin-bottom: 30px;
}

img {
  height: 100px;
  margin-bottom: 10px;
  border-radius: 5px;
  margin-right: 40px;
}

 .exerciseDiv {
  display: inline-block;
  justify-content: center;
  margin: 15px;
  padding: 15px;
  border-radius: 8px/7px;
  background-color: #ebebeb;
  box-shadow: 1px 2px 5px black;
  border: solid 1px black;
  min-width: max-content;
  height: max-content;
} 






</style>