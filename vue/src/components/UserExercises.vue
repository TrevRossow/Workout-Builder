<template>
  <div class="main">
    <div
      class="exerciseDiv"
      v-for="exercise in filteredExercises"
      v-bind:exercise="exercise"
      :key="exercise.id"
    >
      <h2>{{ exercise.name }}</h2>
      <div vr id="imgDiv">
        <div>
          <h6>{{exercise.userId}}</h6>
          <h4 class="group">{{ exercise.muscleGroup }}</h4>
          <h5 class="type" v-show="exercise.type != 'Cardio'">
            {{ exercise.type }}
          </h5>
          <h4 class="reps">{{ exercise.repRange }}</h4>
        </div>
        <img
          class="img"
          :src="`../WorkoutImages/${exercise.muscleGroup}.png`"
        />
      </div>
      <p id="desc">{{ exercise.description }}</p>
      <p id="status" v-if="status.length > 0">
        {{ status(exercise.statusId) }}
      </p>
      <div id="btn">
        <button class="delete" v-on:click="deleteExercise(exercise)">
          Delete
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import exerciseService from "../services/ExerciseService";
export default {
  name: "user_exercise",

  data() {
    return {
      showEdit: false,

      targetedExercise: {},

      exercises: [],
    };
  },
  components: {},

  created() {
    this.getExercises();
  },

  computed: {
    filteredExercises() {
      const exercises = this.exercises;
      return exercises.filter((exercise) => {
        return exercise.statusId == 1 || exercise.statusId == 3;
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
    getExercises() {
      exerciseService.getExercisesByUser(this.$store.state.user.id).then((response) => {
        this.exercises = response.data;
      });
    },

    getStatus(exercise) {
      exerciseService.getStatus(exercise.statusId).then((response) => {
        this.status = response.data;
      });
    },

    status(status) {
      let desc = "";
      if (status == 1) {
        desc = "Pending";
      } else if (status == 3) {
        desc = "Rejected";
      }
      return desc;
    },
    deleteExercise(exercise) {
      exerciseService
        .deleteExercise(exercise)
        .then((response) => {
          console.log(response);
          if (response.status == 200) {
            this.$store.commit("DELETE_EXERCISE", exercise.id);
            this.getExercises();
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.createError = true;
          }
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
  height: 100px;
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

#btn {
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