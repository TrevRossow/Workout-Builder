<template>
  <div class="main">
    <div
      class="exerciseDiv"
      v-for="workout in $store.state.workouts"
      :key="workout.id"
    >
      <h2>{{ workout.name }}</h2>
      <div vr id="imgDiv">
        <div>
          <h4 class="group">Trainer: {{ workout.trainer }}</h4>
          <h5 class="type">User Id: {{ workout.userId }}</h5>
          <div v-for="exercise in workout.exercises" :key="exercise.id">
            <h4 class="reps">{{ exercise.name }}</h4>
            <div vr id="imgDiv">
              <div>
                <h4 class="group">{{ exercise.muscleGroup }}</h4>
                <h5 class="type" v-show="exercise.type != 'Cardio'">
                  {{ exercise.type }}
                </h5>
                <h4 class="reps">{{ exercise.repRange }}</h4>
              </div>
              <img
                class="img"
                :src="`../WorkoutImages/${exercise.muscleGroup}.jpg`"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import exerciseService from "../services/ExerciseService";
export default {
  name: "view-workout",

  data() {
    return {
      showEdit: false,

      targetedExercise: {},

      exercises: [],
    };
  },
  components: {},

  created() {
    this.getExercisesById();
  },

  computed: {
    filteredExercises() {
      const exerciseFilter = this.$store.state.filter;
      const exercises = this.exercises;
      return exercises.filter((exercise) => {
        return exercise.statusId === 2 && exerciseFilter == ""
          ? true
          : exerciseFilter == exercise.muscleGroup;
      });
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
    getExercisesById() {
      exerciseService.getExercises().then((response) => {
        this.exercises = response.data;
      });
    },
  },
};
</script>

<style scoped>
.main {
  display: flex;
  max-width: 100%;
  flex-wrap: wrap;
  min-width: 320px;
}

#imgDiv {
  display: flex;
  justify-content: space-between;
  align-content: center;
}

img {
  height: 80px;
  margin-bottom: 10px;
  border-radius: 5px;
  margin-right: 40px;
}

.exerciseDiv {
  display: inline-block;
  margin: 20px;
  padding: 15px;
  border-radius: 8px/7px;
  background-color: #ebebeb;
  box-shadow: 1px 2px 5px black;
  border: solid 1px #cbc9c9;
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