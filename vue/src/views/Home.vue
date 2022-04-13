<template>
  <div class="home">
    <v-app class="vapp">
      <div class= "main">
    <v-carousel class="carousel" height="600" width= 75%>
      <v-carousel-item id="caroimg"
      v-for="img in caroImages"
      :key="img.id"
      :src="img.src"
      >
      </v-carousel-item>
    </v-carousel>
    </div>
    <div class="articleDiv">
      <article>
        <h3>Our Mission</h3><br>
        <p>
          Here at Elevated Fitness® we are passionate about sharing our love of fitness with the world.
          We work hard hand picking every trainer to best suit our client's needs.
          Whether you're looking for a random work out to spice up your routine, or a customized session with a world-class trainer, 
          Elevated Fitness® has you covered. <br><br>
          Don't take our word for it though,
          Sign up today for a 457,00 day free trial and see for yourself!
        </p>
      </article>
      <article>
        <h3>Program Overview</h3><br>
        <p>
          Here at Elevated Fitness®, we want you to work out whatever way you see fit. Bored with your daily routine? 
          try our random work out generator. Need a little more structure in your life? Work one-on-one with our hand picked trainers,
          and find what's best for you!<br><br>
         Here at Elevated Fitness® You're The Boss!
        </p>
      </article>
      <article>
        <h3>Reviews:</h3><br>
        <div id="reviewDiv">
        <h3>Life Changing!
        </h3>
        <i id=reviewStar>
          <font-awesome-icon 
         icon="fa-solid fa-star" v-for="review in reviews" v-bind:key="review"/>
         </i></div>
        <q>
          Since starting with The Elevated Fitness® program I have experienced greatly improved strength,
           postural stability, and flexibility. The sleek design of the app and simple to use the features 
           make selecting and creating a workout a breeze!
           </q><br><br>
           
      </article>
  
    </div>
    </v-app>
</div>
</template>
<script>
export default {
  name: "home",
  data() {
    return {
      reviews:["","","","",""],
    
      caroImages: [
        {
          id: 1,
          src: "/Asset/Photos/pexels-julia-larson-6455813.jpg",
          title: 'lady split squats'
        },
        {
          id: 2,
          src: "/Asset/Photos/pexels-andrea-piacquadio-3776144.jpg",
          title: 'ladies dumbells'
        },
        {
          id: 3,
          src: "/Asset/Photos/pexels-andres-ayrton-6551098.jpg",
          title: 'lady on machine'
        },
        {
          id: 4,
          src: "/Asset/Photos/bench.jpg",
          title: 'dude front squat'
        },
        {
          id: 5,
          src: "/Asset/Carousel/CaroAssets/photo-1627483298235-f3bac2567c1c.jpg",
          title: 'ladies stretching'
        },
        {
          id: 6,
          src: "/Asset/Carousel/CaroAssets/workout-limitless-endurance-gym-colorful-smoke-1920x1080-929.jpg",
          title: 'limitless'
        },
      ],
    };
  },
};
</script>
<style scoped>

#reviewDiv{
display: flex;

}

#reviewStar {
  color: gold;
  justify-content: right;
  padding-left: 6rem;
}

.main{
  padding: 20px;
}

.articleDiv{
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  flex-direction: row;
  padding: 1rem;
  height: 100%;
}

article{
  box-shadow: 3px 3px 10px 5px gray;
  margin: 1rem;
  padding: 10px;
  max-width: 80%;
  min-width: 300px;
  
}

@media (min-width: 1020px) {
  article{
 
      max-width: 30%;
      min-width: 300px;
  
  }
}

</style>