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
          <option value="Legs">Total Body</option>
        </select>


          <!-- DashBoard and tabs /-->
          <template v-slot:extension>
            <v-tabs v-model="tabs" align-with-title>
              <v-tab> Summary </v-tab>
              <v-tab href="#two"> Workouts </v-tab>
              <v-tab v-on:click="toggleViewExercises()"> Exercises </v-tab>
              <v-tab v-on:click="toggleAddExercise()"> Add Exercise </v-tab>
              <v-tab v-on:click="toggleViewSubmittedExercises()"> Approve Exercise </v-tab>
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
      <add-exercise v-if="addExercise === true"/>
      <view-exercises v-if="viewExercises === true"/>
      <view-submitted-Exercises v-if="viewSubmittedExercises === true"/>

      </div>
    </v-app>
  </div>
</template>

<script>
import addExercise from "../components/AddExercise.vue";
import viewExercises from "../components/ViewExercises.vue";
import viewSubmittedExercises from "../components/ViewSubmittedExercises.vue";

export default {
  name: "trainer-page",
  components: {
    addExercise,
    viewExercises,
    viewSubmittedExercises


  },

  data() {
    return {
      addExercise: false,
      viewExercises:false,
      viewWorkouts:false,
      viewSummary:false,
      viewSubmittedExercises:false,
      fab: false,
      hidden: false,
      tabs: null,
      filter:""
      
    };
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
    toggleAddExercise(){
      this.addExercise = true;
      this.viewExercises = false;
      this.viewSubmittedExercises = false;
    },
    toggleViewExercises(){
      this.viewExercises = true;
      this.addExercise = false;
      this.viewSubmittedExercises = false;
    },
    toggleViewSubmittedExercises(){
      this.viewExercises = false;
      this.addExercise = false;
      this.viewSubmittedExercises = true;
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