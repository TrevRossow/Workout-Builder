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
  
  getExercisesByWorkouts(id) {
    return axios.get(`/exercise/${id}`)
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

  getStatus(statusID){
    return axios.get(`/exercise/status/${statusID}`)
  },

  getExercisesByStatus(id){
    return axios.get(`/exercise/view/${id}/status`)
  },

  getExercisesByUser(id){
    return axios.get(`/exercise/user/${id}`)
  }


  
}
