<template>
  <div class="home">
    <v-app class="vapp">
      <div class= "main">
    <v-carousel class="carousel">
      <v-carousel-item 
      v-for="img in caroImages"
      :key="img.id"
      :src="img.src"
      >
      </v-carousel-item>
    </v-carousel>
    </div>
    <div class="articleDiv">
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
      caroImages: [
        {
          id: 1,
          src: "/Asset/Carousel/CaroAssets/istockphoto-991036038-612x612.jpg",
          title: 'smiling guy'
        },
        {
          id: 2,
          src: "/Asset/Carousel/CaroAssets/istockphoto-1141393089-612x612.jpg",
          title: 'ladies running'
        },
        {
          id: 3,
          src: "/Asset/Carousel/CaroAssets/istockphoto-1225596371-612x612.jpg",
          title: 'ladies posing'
        },
        {
          id: 4,
          src: "/Asset/Carousel/CaroAssets/pexels-pixabay-38630.jpg",
          title: 'muscly dude'
        },
        {
          id: 5,
          src: "/Asset/Carousel/CaroAssets/photo-1627483298235-f3bac2567c1c.jpg",
          title: 'ladies stretching'
        },
      ],
    };
  },
};
</script>
<style scoped>




.main{
  display: flex;
  justify-content: center;
  padding: 20px;
  height: 100%;
}


.articleDiv{
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  padding: 1rem;
}

article{
   width:400px;
   padding: 2rem;
  box-shadow: 3px 3px 10px 5px gray;

}
</style>