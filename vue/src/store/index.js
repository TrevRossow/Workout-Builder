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

    showAddWorkout:false,
    
    selectedExercise:{},

    selectedWorkout:{},

    recentWorkout:{},

    workouts:[],

    summaryWorkout: {
      workoutId: 1,
      name: "",
      exercises: [],
      focus: [],
      trainer: "",
      userId: "",
      completed: false,
    },

    filter:"",

    exercises: [],
    
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
      state.exercises.push(exercise);
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

    SELECT_WORKOUT(state, workout){
      state.selectedWorkout = workout;
    },

    SET_RECENT(state, workout){
      state.recentWorkout = workout;
    },
    
    ADD_WORKOUT(state, workout){
      
      let foundAt = state.workouts.findIndex( w => w.workoutId === workout.workoutId);
      if(foundAt === -1){
      state.workouts.unshift(workout);
      }

    },

    UPDATE_WORKOUT(state, updatedWorkout){
      state.workouts.forEach((workout) => {
        if (workout.id === updatedWorkout.workoutId) {
          workout = updatedWorkout;
        }
      })

    },
    DELETE_WORKOUT(state, id) {
      let workoutsToKeep = [];
      state.workouts.forEach(workout => {
        if (workout.workoutId !== id) {
          workoutsToKeep.push(workout);
        }
      });
      state.workouts = workoutsToKeep;
    },
  }
})
