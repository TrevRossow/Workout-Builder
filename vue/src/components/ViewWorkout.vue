<template>
  <div class="main">
    <add-workout v-if="$store.state.showWorkout === true" />
    <div
      class="exerciseDiv"
      v-for="workouts in newArr"
      :key="workouts.workoutId" 
    ><div id="head">
      <h2>{{ workouts.workoutName }}</h2>
      </div>
      <div id="imgDiv">
        <div id="maininfo" >
          <h4 class="group">Trainer: {{ workouts.trainerId }}</h4>
          <h5 class="type">User Id: {{ workouts.userId }}</h5>
          <br>
          <v-carousel class="carousel" height="200px" 
          hide-delimiter-background  hide-delimiters show-arrows-on-hover >
          <v-carousel-item id="carocards"
           v-for="exercise in newArr" :key="exercise">
            <h4 class="reps">{{ exercise }}</h4>
            <div vr id="imgDiv">
              <div id="info">
                <h4 class="group">{{ exercise.muscleGroup }}</h4>
                <h5 class="type" v-show="exercise.type != 'Cardio'">
                  {{ exercise.type }}
                </h5>
                <h6>Reps:</h6>
                <h4 class="reps">{{ exercise.repRange }}</h4>
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
        <div class="btnDiv">
         <button type="" v-on:click="log(newArr)">Complete</button>
        <button class="delete" v-on:click="deleteWorkout()">Delete</button>
        </div>
      </div>
      
    </div>

</template>

<script>
import exerciseService from "../services/ExerciseService";
import addWorkout from "../components/AddWorkout.vue";
import workoutService from '../services/WorkoutService';


export default {
  name: "view-workout",

  data() {
    return {
      showEdit: false,

      targetedExercise: {},

      exercises: [],

      newArr:[],

      workouts:[],

      showWorkout: false,
    };
  },
  components: {
    addWorkout,
  },

  created() {
    this.getWorkouts();
  },

  computed: {
    
    filteredExercises() {
      const exerciseFilter = this.$store.state.filter;
      const exercises = this.exercises;
      return exercises.filter((exercise) => {
        return exercise.statusId === 2 && exerciseFilter == ""
          ? true
          : exerciseFilter == exercise.muscleGroup;
      })
    },

    exercisesInfo(arr){

      return arr.exercises

    },
        
    isAuthorized() {
      if (this.$store.state.user.authorities[0].name === "ROLE_TRAINER") {
        return true;
      } else {
        return false;
      }
    },
  },

  methods: {
    getExercisesByWorkout(id) {
      exerciseService.getExercisesByWorkouts(id).then((response) => {
        let exercises = response.data;
        return exercises
         
      });  
     
    },

    log(exercise){
      console.log(exercise)
    },


      getWorkouts(){
        workoutService.getWorkouts().then((response) => {
          this.workouts = response.data;
          this.workouts.forEach((workout) => {

             let workoutObj = {
               workoutId:workout.id,
                name: workout.workoutName,
                trainerId: workout.trainerId,
                userId: workout.userId,
                completed: workout.completed,
                dateCompleted: workout.dateCompleted,
                exercises:[]
            }
            exerciseService.getExercisesByWorkouts(workout.id).then((response) => {
            let exercises = response.data;
             workoutObj.exercises.push(exercises[0])
            console.log(workoutObj)
            this.newArr.push(workoutObj)
            });  
           
            
           
          })

          
        })
      }

  

     /* deleteWorkout(workout) {
      workoutService
        .deleteExercise(workout)
        .then((response) => {
          console.log(response);
          if (response.status == 200) {
            this.$store.commit("DELETE_WORKOUT", workout.workoutId);
            this.getExercises();
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.createError = true;
          }
        });
    }, */
  },
};
</script>

<style scoped>
.main {
  display: flex;
  max-width: 100%;
  flex-wrap: wrap;
  min-width: 320px;
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
  margin: 20px;
  padding: 15px;
  border-radius: 8px/7px;
  background-color: #ebebeb;
  box-shadow: 1px 2px 5px black;
  border: solid 1px black;
  max-width: 275px;
  min-width: 275px;
  height: max-content;
}

.btnDiv {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.edit {
  background-color: rgb(161, 161, 38);
}

.delete {
  background-color: red;
}
button {
  font-size: 14px;
  font-weight: 600;
  color: white;
  text-decoration: none;
  width: 120px;
  height: 30px;
  border-radius: 5px;
  background-color: #00afef;
  box-shadow: 0 3px rgba(58, 87, 175, 0.75);
  padding: 5px;
  margin: 15px;
}

.add:hover {
  top: 3px;
  border: #4c4c4c;
}
</style>