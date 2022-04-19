import axios from 'axios';



export default {

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
    }

}
