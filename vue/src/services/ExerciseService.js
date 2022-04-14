import axios from 'axios';

export default {

  getExercises() {
    return axios.get('/exercise')
  },

  addExercise(exercise) {
    return axios.post('/exercise', exercise)
  },

  
}
