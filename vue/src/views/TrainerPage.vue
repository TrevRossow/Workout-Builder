<template>
   <div id="app">
  <v-app id="inspire">
    <v-card id="lateral">
      <v-toolbar id="toolbar"
        dark
        tabs
        flat
        color="blue"
      >
        <v-app-bar-nav-icon></v-app-bar-nav-icon>
        <v-toolbar-title>Trainer Dashboard</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn icon>
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
        <v-btn icon>
          <v-icon>mdi-dots-vertical</v-icon>
        </v-btn>
        
        <!-- DashBoard and tabs /-->
        <template v-slot:extension>
          <v-tabs
            v-model="tabs"
            align-with-title
          >
            <v-tab v href="#two">
              Summary 
            </v-tab>
            <v-tab href="#two">
              Workouts
            </v-tab>
            <v-tab href="#three">
              Exercises
            </v-tab>
            <v-tab href="#four">
              Exercises
            </v-tab>
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
            v-for="content in ['one', 'two', 'three','four']"
            :key="content"
            :value="content"
          >
            <v-card id="main"
            flat
            
           
            >
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-card-text>
    </v-card>
  </v-app>
</div>
</template>

<script>
export default {
  data: () => ({
    fab: false,
    hidden: false,
    tabs: null,
  }),


  computed: {
    activeFab () {
      switch (this.tabs) {
        case 'one': return {}
        case 'two': return {}
        case 'three': return {}
        default: return {}
      }
    },
  },
}
</script>

<style scoped>

#main{
  height: 90%;
}

#inspire{
  height: 80vw;

}
#text{
  padding: 0;
}

</style>