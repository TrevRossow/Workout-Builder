import axios from 'axios';



export default {

  getExercises() {
    return axios.get('/exercise/all')
  },

  getExerciseById(ID) {
    return axios.get(`/exercise/view/${ID}`)
  },
  getExercisesByMuscleGroup(muscleGroup) {
    return axios.get(`/exercise/group/${muscleGroup}`)
  },
  
  getExercisesBytype(type) {
    return axios.get(`/exercise/${type}`)
  },

  addExercise(exercise) {
    return axios.post('/exercise', exercise)
  },

  updateExercise(exercise){
    return axios.put(`/exercise/${exercise.id}`, exercise)
  },

  deleteExercise(exercise){
    return axios.delete(`/exercise/${exercise.id}`)
  },

  getStatus(exercise){
    return axios.get(`/exercise/status/${exercise.id}`)
  }


  
}
