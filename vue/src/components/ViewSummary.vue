<template>
  <div class="main">
    <div class="body">
      <div class="currentDate">
      <i><font-awesome-icon icon="fa-calendar" /></i>
      {{ date }}
    </div>
     <summary-info/>
      <div class="timeExercising">
        <img class="img" src="../../public/graph.png" alt="exercise graph" />
        <p>Here's your workout time this week!</p>
      </div>
      <div class="focusMostCompleted">
        <img class="img" src="../../public/focus.png" alt="exercise focuses" />
        <p>Great Work! This is the breakdown <br />on your focuses this week</p>
      </div>
      <div class="repsCompleted">
        <img class="img" src="../../public/reps.png" alt="reps completed" />
        <p>You have completed: <b>153 Reps this week!</b></p>
      </div>
    </div>
    <div class="subBody">
      <p>Excellent job this week! Based on your summary, we recommend focusing more
        on <a href="">shoulders</a>
      </p>
    </div>
  </div>
</template>
<script>
import summaryInfo from "../components/SummaryInfo.vue";

export default {
  name: "view-summary",
  components:{
    summaryInfo
  
  },
  data() {
    return {
      showDateTime: null,
      date: null,
      workout: {},
    };
    
  },
  methods: {
    getWorkout() {
      this.workout = this.$store.state.summaryWorkout;
    },
    getCurrentDatetime() {
      let dateTime = new Date();
      this.showDateTime = dateTime;
      this.date = dateTime.toJSON().slice(0, 10).replace(/-/g, "/");
    },
  },
  created() {
    this.getWorkout();
    this.getCurrentDatetime();
  },
};
</script>
<style scoped>
.currentDate {
  display: flex;
  justify-content: center;
  justify-content: space-around;
  width: 140px;
  height: 40px;
  background-color: #3a57af;
  border-radius: 5px;
  padding: 8px 8px 8px 12px;
  margin: 1rem;
  color: white;
  box-shadow: 2px 3px 3px rgb(58 87 175 / 75%);
  border: solid 1px #cbc9c9;
}

img {
  margin-top: 40px;
  margin-bottom: 10px;
  height: 15rem;
  
}

p {
  font-style: italic;
  font-family: "Josefin Sans", sans-serif;
}

.body {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}

.timeExercising {
  max-width: 100%;
  padding-left: 30px;
  padding-right: 30px;
  margin-top: 10px;
  margin-right: 15px;
  background-color: rgb(240, 20, 12);
  border-radius: 15px;
  box-shadow: 4px 4px 3px rgb(58 87 175 / 75%);
}

.focusMostCompleted {
    max-width: 100%;
  padding-left: 30px;
  padding-right: 30px;
  margin-top: 10px;
  background-color: rgb(17, 226, 51);
  border-radius: 15px;
  box-shadow: 2px 3px 3px rgb(58 87 175 / 75%);
}

.repsCompleted {
    max-width: 100%;
  padding-left: 30px;
  padding-right: 30px;
  margin-top: 10px;
  background-color: rgb(185, 5, 155);
  border-radius: 15px;
  box-shadow: 2px 3px 3px rgb(58 87 175 / 75%);
}

.subBody {
  display: flex;
  justify-content: center;
  margin-top: 5rem;
  margin-left: 10px;
}

@media (max-width:465px) {

  img{
    width: 100%;
  }

  .timeExercising{
    margin-right: 0px;
  }
  
}
</style>