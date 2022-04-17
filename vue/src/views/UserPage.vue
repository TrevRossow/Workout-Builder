<template>
  <div id="app">
    <v-app id="inspire">
      <v-card id="lateral">
        <v-toolbar id="toolbar" dark tabs flat color="blue">
          <v-app-bar-nav-icon></v-app-bar-nav-icon>
          <v-toolbar-title>{{this.$store.state.user.username}}</v-toolbar-title>
          <v-spacer id="vspace"></v-spacer>
     <v-btn icon>
            <v-icon v-on:click="updateFilter()"> mdi-magnify</v-icon>
          </v-btn>
           <select name="drop-down" id="filter" v-model="filter" required>
          <option value="" selected="selected" disabled="disabled">-- Filter --</option>
          <option value="Chest">Chest</option>
          <option value="Back">Back</option>
          <option value="Biceps">Biceps</option>
          <option value="Triceps">Triceps</option>
          <option value="Shoulders">Shoulders</option>
          <option value="Legs">Legs</option>
        </select>


          <!-- DashBoard and tabs /-->
          <template v-slot:extension>
            <v-tabs v-model="tabs" align-with-title>
              <v-tab> Summary </v-tab>
              <v-tab v-on:click="toggleCreateWorkout()"> Create Workout</v-tab>
              <v-tab v-on:click="toggleViewWorkout()"> Workouts </v-tab>
              <v-tab v-on:click="toggleViewExercises()"> Exercises </v-tab>
              <v-tab v-on:click="toggleAddExercise()"> Add Exercise </v-tab>
              <v-tabs-slider color="pink"></v-tabs-slider>
            </v-tabs>
          </template>

          <!-- Main content area below tabs -->
        </v-toolbar>

        <!-- Might not need card text. Ideally on the selection of a tab we display 
      the corresponding component -->
        <v-card-text id="text">
          <v-tabs-items v-model="tabs">
            <v-tab-item
              v-for="content in ['one', 'two', 'three', 'four']"
              :key="content"
              :value="content"
            >
              <v-card>  </v-card>
            </v-tab-item>
          </v-tabs-items>
        </v-card-text>
      </v-card>
      <div>
      <create-workout v-if="createWorkout === true"/>
      <view-workout v-if="viewWorkout === true"/>
      <add-exercise v-if="addExercise === true"/>
      <viewExercises v-if="viewExercises === true"/>
      </div>
    </v-app>
  </div>
</template>

<script>
import addExercise from "../components/AddExercise.vue";
import viewExercises from "../components/ViewExercises.vue";
import viewWorkout from "../components/ViewWorkout.vue";
import createWorkout from "../components/CreateWorkout.vue";
export default {
  components: {
    addExercise,
    viewExercises,
    viewWorkout,
    createWorkout

  },

  data() {
    return {
      addExercise: false,
      viewExercises:false,
      viewWorkout:false,
      createWorkout:false,
      viewSummary:false,
      fab: false,
      hidden: false,
      tabs: null,
      filter:""
      
    };
  },
  created(){
    
  },

  computed: {
    activeFab() {
      switch (this.tabs) {
        case "one":
          return true;
        case "two":
          return {};
        case "three":
          return {};
        default:
          return {};
      }
    },
  },
  methods:{

      toggleCreateWorkout(){
        this.createWorkout = true;
      this.viewWorkout = false;
      this.viewExercises = false;
      this.addExercise = false;
      this.viewSubmittedExercises = false;
    },
        toggleViewWorkout(){
                this.createWorkout = false;
      this.viewWorkout = true;
      this.viewExercises = false;
      this.addExercise = false;
      this.viewSubmittedExercises = false;
    },
    toggleAddExercise(){
      this.createWorkout = false;
      this.addExercise = true;
      this.viewExercises = false;
      this.viewWorkout = false;
    },
    toggleViewExercises(){
      this.createWorkout = false;
      this.viewExercises = true;
      this.addExercise = false;
      this.viewWorkout = false;
    },
    updateFilter(){
      this.$store.commit("UPDATE_FILTER", this.filter)
      this.filter = "";
    }
  }
};
</script>

<style scoped>
#main {
  height: 90%;
}

#filter{
  background-color: white;
  min-width: 80px;
  width: max-content;
}

option{
  min-width: 80px;
  width: 15%;

}

#vspace{
  
}

#inspire {
  height: min-content;
  
}
#text {
  padding: 0;
}
</style>