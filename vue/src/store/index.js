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

    workouts:[],

    filter:"",

    exercises: [
    {
        id: 1, 
        name: "Incline Bench Press",
        description: "Lower the weight with control to lower portion of the chest and then press",
        muscleGroup: "Chest",
        repRange: "8-12",
        type: "Strength",
        statusId: 2
    },
    {
      id: 2, 
      name: "Barbell Row",
      description: "Lower the weight with control to and pull toward the navel",
      muscleGroup: "Back",
      repRange: "8-12",
      type: "Strength",
      statusId: 2
  },
  {
    id: 3, 
    name: "Lat Pull Down",
    description: "Lower the weight with control pulling elbows to the side",
    muscleGroup: "Back",
    repRange: "8-12",
    type: "Strength",
    statusId: 2
},
{
  id: 4, 
  name: "Lat Pull Down",
  description: "Lower the weight with control pulling elbows to the side",
  muscleGroup: "Back",
  repRange: "8-12",
  type: "Strength",
  statusId: 2
},
{
  id: 5, 
  name: "Lat Pull Down",
  description: "Lower the weight with control pulling elbows to the side",
  muscleGroup: "Back",
  repRange: "8-12",
  type: "Strength",
  statusId: 2
},
{
  id: 6, 
  name: "Bench Press",
  description: "Lower the weight with control to lower portion of the chest and then press",
  muscleGroup: "Chest",
  repRange: "8-12",
  type: "Strength",
  statusId: 2
},
{
id: 7, 
name: "Barbell Row",
description: "Lower the weight with control to and pull toward the navel",
muscleGroup: "Back",
repRange: "8-12",
type: "Strength",
statusId: 2
},
{
id: 8, 
name: "Lat Pull Down",
description: "Lower the weight with control pulling elbows to the side",
muscleGroup: "Back",
repRange: "8-12",
type: "Strength",
statusId: 2
},
{
id: 9, 
name: "Lat Pull Down",
description: "Lower the weight with control pulling elbows to the side",
muscleGroup: "Back",
repRange: "8-12",
type: "Strength",
statusId: 2
},
{
id: 10, 
name: "Lat Pull Down",
description: "Lower the weight with control pulling elbows to the side",
muscleGroup: "Back",
repRange: "8-12",
type: "Strength",
statusId: 2
}
    ],
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
    }
  }
})
