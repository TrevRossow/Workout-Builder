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

    sendExercises(workoutID, exerciseObj){
        return axios.post(`/workout/${workoutID}/exercise`, exerciseObj)
    },

    updateWorkoutExercises(workoutID, exerciseObj){
        return axios.put(`/workout/${workoutID}/exercise/update`, exerciseObj)
    }

}
