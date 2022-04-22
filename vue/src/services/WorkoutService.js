import axios from 'axios';



export default {

    getWorkouts(){
        return axios.get(`/workout`)

    },

    getWorkoutsByUserID(id) {
        return axios.get(`/workout/user/${id}`)
    },

    addWorkout(workout) {
        return axios.post('/workout', workout)
    },
    editWorkout(id, workout) {
        return axios.put(`/workout/${id}`, workout)
    },

    deleteWorkout(id) {
        return axios.delete(`/workout/${id}`)
    },

    deleteWorkoutExercise(id) {
        return axios.delete(`/workout/${id}/exercise`)
    },

    deleteExerciseWorkout(id) {
    return axios.delete(`/workout/exercise/${id}`)
    },

    sendExercises(workoutID, exerciseObj){
        return axios.post(`/workout/${workoutID}/exercise`, exerciseObj)
    },

    updateWorkoutExercises(workoutID, exerciseObj){
        return axios.put(`/workout/${workoutID}/exercise/update`, exerciseObj)
    },

    getLatestWorkout(userID){
        return axios.get(`/workout/${userID}/recent`)
    }



}
