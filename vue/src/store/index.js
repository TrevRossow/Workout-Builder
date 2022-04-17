import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'


Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},

    showEdit:false,
    
    selectedExercise:{},

 

    workouts:[

      {
        workoutId: 1,
        name: "Test1",
        exercises: [1,2,3,4],
        focus: [],
        trainer: "Jim",
        userId: "1",
        completed: false,
      },
      {
        workoutId: 2,
        name: "Test2",
        exercises: [2,2,3,5],
        focus: [],
        trainer: "Jim",
        userId: "1",
        completed: false,
      },
      {
        workoutId: 3,
        name: "Test3",
        exercises: [2,3,5,7],
        focus: [],
        trainer: "Jeff",
        userId: "1",
        completed: false,
      }
    


    ],

    filter:"",

    exercises: [],
  
    exerciseImages: [
      {
        id:"001",
        name:"Back",
        src: "../WorkoutImages/Back.jpg"
      },
      {
        id:"002",
        name: "Biceps",
        src:"../WorkoutImages/Biceps.jpg"
      },
      {
        id:"003",
        name:"Chest",
        src: "../WorkoutImages/Chest.jpg"
      },
      {
        id: "004",
        name: "Legs",
        src: "../WorkoutImages/Legs.jpg"
      },
      {
        id: "005",
        name: "Shoulders",
        src: "../WorkoutImages/Shoulders.jpg"
      },
      {
        id: "006",
        name: "Tricep",
        src: "../WorkoutImages/Tricep.jpg"
      }

    ], 
  
    
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },

    UPDATE_FILTER(state, filter){
      state.filter = filter

    },

    ADD_EXERCISE(state, exercise){
      state.exercises.unshift(exercise);
    },

    UPDATE_EXERCISE(state, updatedExercise){
      state.exercises.forEach(exercise => {
        if (exercise.id === updatedExercise.id) {
          exercise = updatedExercise
        }
      });
      
    },

    DELETE_EXERCISE(state, id) {
      let exercisesToKeep = [];
      state.exercises.forEach(exercise => {
        if (exercise.id !== id) {
          exercisesToKeep.push(exercise);
        }
      });
      state.exercises = exercisesToKeep;
    },

    SELECT_EXERCISE(state, exercise){
      state.selectedExercise = exercise;
    },

    ADD_WORKOUT(state, workout){
      state.workouts.unshift(workout);
    },
  }
})
