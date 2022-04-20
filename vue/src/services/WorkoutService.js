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
    editWorkout(workout) {
        return axios.put(`/workout/${workout.id}`, workout)
    },

    deleteWorkout(id) {
        return axios.delete(`/workout/${id}`)
    },

    sendExercises(workoutID, exerciseObj){
        return axios.post(`/workout/${workoutID}/exercise`, exerciseObj)
    }

}
