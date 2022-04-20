<template>
  <div class="main">
      <h1>Your workout summary</h1>
      <div class="exerciseDiv">
          <v-card>
              <v-container>
                  <v-row dense>
                      <v-col cols="15">
                          <v-card>
                              <v-card-title class="firstCardTitle">
                                  Average Time Spent exercising<br/><br/>
                              </v-card-title>
                              <v-card-subtitle>
                                  *time exercising average*
                              </v-card-subtitle>
                          </v-card>
                          <v-card>
                              <v-card-title class="secondCardTitle">
                                  Your focus most completed<br/><br/>
                              </v-card-title>
                              <v-card-subtitle>
                                  *never legs - who does legs*
                              </v-card-subtitle>
                          </v-card>
                          <v-card>
                              <v-card-title class="thirdCardTitle">
                                  Reps completed this workout<br/><br/>
                              </v-card-title>
                              <v-card-subtitle>
                                  *100 - you big beef cake*
                              </v-card-subtitle>
                          </v-card>
                      </v-col>
                  </v-row>
              </v-container>
          </v-card>
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
    },

    computed: {
        isAuthorized() {
            if (this.$store.state.user.authorities[0].name === "ROLE_USER") {
                return true;
            }
            else {
                return false;
            }
        },
    }

}
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
  height: max-content;
}
</style>