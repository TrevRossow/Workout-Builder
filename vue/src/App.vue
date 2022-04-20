<template>
  <v-app id="app">
    <v-main>
      <div id="head">
        <header v-on:click="$router.push({name: 'home'})">
          <img src="../public/EFLogo.png" alt="" />
          <h1>Elevated Fitness</h1>
        </header>
        <nav>
          <div class="navDiv">
            <button id="loginHome" class="btn" v-on:click="$router.push({name:'login'})"
            v-if="($store.state.token === '' )">Login
            <font-awesome-icon icon="fa-solid fa-arrow-right-to-bracket" />
            </button>

            <button id="dashboardBtn" class="btn" v-on:click="getUser()"
            v-if="($store.state.token != '')"> Dashboard
            <font-awesome-icon icon="fa-dashboard"/>
            </button>

          <button id="loginHome" class="btn" v-on:click="$router.push({name:'logout'})"
            v-bind:to="{ name: 'logout' }"
            v-if="($store.state.token != '')">Logout
            <font-awesome-icon icon="fa-solid fa-arrow-right-from-bracket" />
            </button>
          </div>
        </nav>
      </div>
      <router-view />
    </v-main>
    <footer class="footer-whole">
      <div class="footer-right">
        <a href="#"
          ><i><font-awesome-icon icon="fa-brands fa-facebook-square" /></i
        ></a>
        <a href="#"
          ><i><font-awesome-icon icon=" fa-brands fa-twitter" /></i
        ></a>
        <a href="#"
          ><i><font-awesome-icon icon=" fa-brands fa-linkedin" /></i
        ></a>
        <a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ"
          ><i><font-awesome-icon icon="fa-brands fa-youtube-square" /></i
        ></a>
      </div>
      <div class="footer-left">
        <p class="footer-links">
          <router-link :to="{ name: 'home' }">Home</router-link>
          <a href="#">Blog</a>
          <a href="#">About</a>
          <a href="#">Faq</a>
          <a href="#">Contact</a>
        </p>
        <p>Elevated Fitness &copy; 2022</p>
      </div>
    </footer>
  </v-app>
</template>


<script>
export default {
  name: "App",

  data: () => ({
    
    //
  }),
  methods: {
    getUser(){
      const id = this.$store.state.user.id
       if (this.$store.state.user.authorities[0].name === "ROLE_TRAINER") {
              this.$router.replace({ name: "trainer" ,params:{id: id} });
            } else if (this.$store.state.user.authorities[0].name === "ROLE_USER") {
              this.$router.replace({ name: "user", params:{id: id} });
            } else {
              this.$router.push({ name: "login" });
            }
          }
      
    
  }
};
</script>
<style>

@import url('https://fonts.googleapis.com/css2?family=Josefin+Sans:ital,wght@1,700&display=swap');

#app{
}
img {
  height: 100px;
  padding-left: 10px;
}

h1 {
  padding-left: 10px;
}
header {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  grid-area: "header";
  width: auto;
}
h1, img:hover {
  cursor: pointer;
}

nav {
  grid-area: "nav";
  display: flex;
  align-items: center;
  justify-content: flex-end;
  
  
}

#head {
  display: grid;
  grid-template-areas: "header nav";
  grid-row: 1fr 1fr;
}

.btn {
  font-size: 14px;
  font-weight: 600;
  text-decoration: none;
  width: 100px; height: 30px; 
  border-radius: 5px; 
  background-color: #00AFEF; 
  box-shadow: 0 3px rgba(58,87,175,.75);
  margin: 15px
}

.btn:hover {
  top: 3px;
  border: #4c4c4c;
 
}

.footer-whole {
  background-color: #292c2f;
  box-shadow: 0 1px 1px 0 rgba(0, 0, 0, 0.12);
  box-sizing: border-box;
  width: 100%;
  text-align: left;
  font: normal 16px sans-serif;
  padding: 45px 50px;
}

.footer-whole .footer-left p {
  color: #8f9296;
  font-size: 14px;
  margin: 0;
}
/* Footer links */

.footer-whole p.footer-links {
  font-size: 18px;
  font-weight: bold;
  color: #ffffff;
  margin: 0 0 10px;
  padding: 0;
}

.footer-whole p.footer-links a {
  display: inline-block;
  line-height: 1.8;
  text-decoration: none;
  color: inherit;
}

.footer-whole .footer-links a:before {
  content: "·";
  font-size: 20px;
  left: 0;
  color: #fff;
  display: inline-block;
  padding-right: 5px;
}

.footer-whole .footer-links .link-1:before {
  content: none;
}

.footer-whole .footer-right {
  float: right;
  margin-top: 6px;
  max-width: 180px;
}

.footer-whole .footer-right a {
  display: inline-block;
  width: 35px;
  height: 35px;
  background-color: #33383b;
  border-radius: 2px;
  font-size: 20px;
  color: #ffffff;
  text-align: center;
  line-height: 35px;
  margin-left: 5px;
}

/* Media Queries */

@media (max-width: 600px) {

  
  .footer-distributed .footer-left,
  .footer-distributed .footer-right {
    text-align: center;
  }
  .footer-distributed .footer-right {
    float: none;
    margin: 0 auto 20px;
  }
  .footer-distributed .footer-left p.footer-links {
    line-height: 1.8;
  }
}
</style>
