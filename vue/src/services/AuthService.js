import axios from 'axios';


export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  getTrainers(role){
    return axios.get(`/users/role/${role}`)
  },

  getUsers(){
    return axios.get(`/user`)
  },

  getUserNameById(id){
    return axios.get(`/user/username/${id}`)
  }

}
