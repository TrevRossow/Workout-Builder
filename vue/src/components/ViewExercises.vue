<template>
  <div class="main">
    <div
      class="exerciseDiv"
      v-for="exercise in filteredExercises"
      v-bind:exercise="exercise"
      :key="exercise.id"
    >
      <h2>{{ exercise.name }}</h2>
      <h3>{{ exercise.muscleGroup }}</h3>
      <h4>{{ exercise.repRange }}</h4>
      <h5>{{ exercise.type }}</h5>
      <p>{{ exercise.description }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "view-exercises",
  data() {
    return {
        
      }
    },
     computed: {
        filteredExercises() {
          const exerciseFilter = this.$store.state.filter;
          const exercises = this.$store.state.exercises;
          return exercises.filter((exercise) => {
            return exerciseFilter == ""
              ? true
              : exerciseFilter == exercise.muscleGroup;
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
}
.exerciseDiv {
  margin: 20px;
  padding: 10px;
  border-radius: 8px/7px;
  background-color: #ebebeb;
  box-shadow: 1px 2px 5px black;
  border: solid 1px #cbc9c9;
  width: 200px;
}
</style>