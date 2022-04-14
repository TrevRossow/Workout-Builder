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

    exercises: [
    {
        exercise_id: 1, 
        exercise_name: "Bench Press",
        exercise_description: "Lower the weight with control to lower portion of the chest and then press",
        muscle_group: "Chest",
        rep_range: 8,
        exercise_type: "Strength"
    },
    {
      exercise_id: 2, 
      exercise_name: "Bench Press",
      exercise_description: "Lower the weight with control to lower portion of the chest and then press",
      muscle_group: "Chest",
      rep_range: 8,
      exercise_type: "Strength"
  },
  {
    exercise_id: 3, 
    exercise_name: "Bench Press",
    exercise_description: "Lower the weight with control to lower portion of the chest and then press",
    muscle_group: "Chest",
    rep_range: 8,
    exercise_type: "Strength"
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

    ADD_EXERCISE(state, exercise){
      state.exercises.push(exercise);
    }
  }
})
