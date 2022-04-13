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
        <h3>Life Changing!</h3>
        <q>Since starting with The Elevated Fitness® program I have experienced greatly improved strength,
           postural stability, and flexibility. The sleek design of the app and simple to use the features 
           make selecting and creating a workout a breeze!</q><br>
           <font-awesome-icon icon="fa-solid fa-star" v-for="review in reviews" v-bind:key="review"/>
      </article>
      <article>
        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.
           Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
            when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
            It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. 
            It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker 
            including versions of Lorem Ipsum.</p>
      </article>
      <article>
        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.
           Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
            when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
            It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. 
            It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker 
            including versions of Lorem Ipsum.</p>
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