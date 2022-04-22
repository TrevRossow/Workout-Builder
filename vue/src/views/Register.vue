<template>
<div class="testbox">
  <h1>Registration</h1>
  <form @submit.prevent="register">
    <div class="alert" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>  
  
  <label id="icon" for="name">
  <i><font-awesome-icon icon="fa-user"/></i>
  </label>
  <input type="text" 
  name="name" 
  id="name" 
  autocomplete="off"
  placeholder="Username"
  v-model="user.username"
  required
  autofocus/>

  <h2>Password must be 8 - 16 characters, and contain at least one special character, and one number</h2>
  <label id="icon" for="name">
  <i> <font-awesome-icon icon="fa fa-key"/></i>
  </label>
  <input type="password" 
  name="name" id="name" 
  placeholder="Password"   
  v-model="user.password"
  required/>

  <label id="icon" for="name">
    <i><font-awesome-icon icon="fa fa-key"/></i>
  </label>
  <input type="password" 
  name="name" id="ConfirmPassword," 
  placeholder="Confirm Password"   
  v-model="user.confirmPassword"
  required/>

<div class="send">
<button class=" btn-lg btn-primary btn-block" type="submit"> Register</button>
<router-link :to="{ name: 'login' }">Have an account?</router-link>
</div>
  </form>
</div>
</template>

<script>
import authService from '../services/AuthService';


export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
      passwordComparison: /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$/
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Passwords do not match.';
      } else if (this.user.password.length <= 8 ) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password must be at least 8 characters'
      } else if (this.user.password.length >= 16) {
        this.registrationErrorMsg = true;
        this.registrationErrorMsg = 'Password must be less than 16 characters'
      } else if ( !this.user.password.match(this.passwordComparison)){
        this.registrationErrorMsg = true;
        this.registrationErrorMsg = 'Password must contain at least one number, and one special character.'
      }
       else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'This username is already in use!';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
h1 {
  font-size: 32px;
  font-weight: 300;
  color: #4c4c4c;
  text-align: center;
  padding-top: 10px;
  margin-bottom: 10px;
}
h2 {
  font-size: 12px;
  font-weight: 400;
  color: #4c4c4c;
  text-align: center;
  padding-top: 10px;

}

.testbox {
  margin: 20px auto;
  width: 310px; 
  height: 465px; 
  border-radius: 8px/7px; 
  background-color: #ebebeb; 
  box-shadow: 1px 2px 5px black; 
  border: solid 1px #cbc9c9;
}

input[type=radio] {
  visibility: hidden;
}

form{
  margin: 0 30px;
}

.alert{
  box-shadow: 1px 2px 5px black; 
  text-align: center;
  font-size: 14px;
  font-weight: 600;
  background-color:rgb(240, 52, 52);
  color: #ebebeb;
  border-radius: 5px;
  animation: shake;
  animation: shake 0.82s cubic-bezier(.36,.07,.19,.97) both;
  transform: translate3d(0, 0, 0);
  backface-visibility: hidden;
  perspective: 1000px;
}


hr{
  color: #a9a9a9;
  opacity: 0.3;
}

input[type=text],input[type=password]{
  width: 180px; 
  height: 39px; 
  border-radius: 0px 4px 4px 0px/5px 5px 4px 4px; 
  background-color: #fff;  
  box-shadow: 1px 2px 5px black; 
  border: solid 1px #cbc9c9;
  margin-left: -5px;
  margin-top: 13px; 
  padding-left: 10px;
}

input{
  margin-bottom: 15px;
}


#icon {
  display: inline-block;
  width: 40px;
  background-color: #3a57af;
  padding: 8px 8px 8px 12px;
  margin-left: 15px;
  margin-right: 10px;
  color: white;
  box-shadow: 1px 2px 5px rgba(0,0,0,.09); 
  border: solid 0px #cbc9c9;
}


.gender {
  margin-left: 30px;
  margin-bottom: 10px;
}

.send{
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
}

button {
  font-size: 14px;
  font-weight: 600;
  color: white;
  text-decoration: none;
  width: 100px; height: 30px; 
  border-radius: 5px; 
  background-color: #3a57af; 
  box-shadow: 0 3px rgba(58,87,175,.75);
  align-self: center;
  margin-bottom: 10px;
}

button :hover {
  top: 3px;
  background-color: black;
  border: #4c4c4c;
}
</style>
