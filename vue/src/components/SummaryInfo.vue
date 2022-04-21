<template>
  <div class="main">
    <div class="exerciseDiv">
      <h2>Welcome Back!</h2>
      <div vr id="imgDiv">
        <div>
          <h2 class="name">{{ getName }}</h2>
          <h5 class="user_id"></h5>
          <div class="currentDate">
            <i><font-awesome-icon icon="fa-calendar" /></i>
            {{ date }}
          </div>
          <h6>Reps:</h6>
          <h4 class="reps">****</h4>
          <h6>Time:</h6>
          <h4 class="time">****</h4>
        </div>
      </div>
      <p id="desc"></p>
    </div>
  </div>
</template>

<script>

export default {
  name: "summary-info",

    data() {
    return {
      showDateTime: null,
      date: null,
      workout: {},
    }
    
    },
created(){
  this.getCurrentDatetime()
},


    computed:{
       getName(){
        return this.$store.state.user.username
      }
      

    },


  methods: {
    getWorkout() {
      this.workout = this.$store.state.summaryWorkout;
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
p {
  margin-top: 10px;
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