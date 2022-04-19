<template>
  <div class="main">
      <h1>Your workout summary</h1>
      <div class="timeExercising">
          This should display info
      </div>
      <div class="focusMostCompleted">

      </div>
      <div class="repsCompleted">

      </div>
  </div>
</template>

<script>
export default {
    name: "view-summary",
    data() {
        return {
            workout:{}
        }
    },

    methods: {
        getWorkout(){
            this.workout = this.$store.state.summaryWorkout;
        }
    },

    created() {
        this.getWorkout();
    }

}
</script>

<style>

</style>