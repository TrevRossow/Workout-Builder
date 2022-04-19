<template>
  <div class="main">
      <h1>Your workout summary</h1>
      <div class="timeExercising">
          This should display info
      </div>
      <div class="currentDate" >
          <i><font-awesome-icon icon="fa-calendar"/></i>
          {{ date }}
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
            showDateTime: null,
            date: null,
            workout:{}
        }
    },

    methods: {
        getWorkout(){
            this.workout = this.$store.state.summaryWorkout;
        },
        
        getCurrentDatetime(){
            let dateTime = new Date();
            this.showDateTime = dateTime;
            this.date = dateTime.toJSON().slice(0,10).replace(/-/g,'/');
            console.log(dateTime);
        }
    },

    created() {
        this.getWorkout();
        this.getCurrentDatetime();
    },
        

}

</script>

<style>

.currentDate{
  width: 140px;
  background-color: #3a57af;
  border-radius: 5px;
  padding: 8px 8px 8px 12px;
  margin: 1rem;
  color: white;
  box-shadow: 2px 3px 3px rgb(58 87 175 / 75%);
  border: solid 1px #cbc9c9;
  position: relative;
}
</style>