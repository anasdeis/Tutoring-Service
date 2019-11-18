<template>
  <div id="app" v-bind:style=" {backgroundColor : bgColor}">
    <LogoBar @setDarkModeState="setBackgroundColor"/>
    <img src="./assets/logo.png">
     <!-- the router uses this tag as a container for rendering the different components
     tied to the different routes, think of it as a placeholder -->
    <router-view></router-view>   

    <div id = "description">
      <body>
        <hr />
        <center><p> What we can provide</p></center>
        <ul>
          <li><b> High School Courses</b>: Math, Sciences, History, Religion Studies</li>
          <li> <b> Cegep Courses</b>: Math, Physics, Chemistry, English, French, Biolgy, Businese, etc.</li>
          <li><b> University Course</b>: <a href="https://mcgill.ca/study/2019-2020/courses/search">McGill University,</a>
          <a href="https://www.concordia.ca/artsci/academics/undergraduate.html">Concordia University,</a> 
          <a href="https://admission.umontreal.ca/repertoire-des-cours/filtres/matiere_kin/">University of Montreal,</a></li> 
          <li>and more!</li>
        </ul>
      </body>
    </div>

  </div>
</template>

<script>
import LogoBar from "./components/LogoBar.vue";
export default {
  name: "app",
  components: {
    LogoBar
  },
  data() {
    return {
      // rgb(88, 96, 102)
      bgColor: ""
    };
  },
  created: function() {
    if (!localStorage.getItem("DarkModeOn")) {
      // Set local storage value if none exists
      localStorage.setItem("DarkModeOn", "false");
    } else {
      // Local storage value exists
      var darkModeOn = localStorage.getItem("DarkModeOn");
      if (darkModeOn === "true") this.bgColor = "rgb(88, 96, 102)";
      else this.bgColor = "white";
    }
    if (!localStorage.getItem("isLoggedIn")) {
      // Set local storage value if none exists
      localStorage.setItem("isLoggedIn", "false");
    }
  },
  methods: {
    setBackgroundColor: function(darkModeOn) {
      if (darkModeOn == true) this.bgColor = "rgb(88, 96, 102)";
      else this.bgColor = "white";
      this.$root.$emit("setDarkModeState", darkModeOn); // Emit to all children
    }
  }
};
</script>

<style>
body,
html {
  margin: 0;
  height: 100%;
  font-family: Arial, Helvetica, sans-serif;
  /* font-size: 55px; */
}
p{
  font-size: 15px;
  font-family: fantasy;

}
ul{
  font-family: fantasy;
  font-size: 12px;
  text-align: center;
  list-style-type: none;
}
/* #description {
  font-family: Arial, Helvetica, sans-serif;
  font-size: 45px;
} */
#app {
  /* background-color: rgb(88, 96, 102) !important; */
  height: 100%;
  overflow: auto;
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>