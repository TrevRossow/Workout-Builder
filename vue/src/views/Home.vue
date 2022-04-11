<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <v-app>
    <v-carousel>
      <v-carousel-item
      v-for="img in caroImages"
      :key="img.id"
      :src="img.src"
      >
      </v-carousel-item>
    </v-carousel>
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