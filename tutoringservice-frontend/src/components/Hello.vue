<template>
<div class="hello">
  <b-container fluid class="home-row">
    <b-row>
      <b-col>
        <button
          type="button"
          v-on:click="goToLoginPage()"
          class="btn btn-primary btn-lg home-row button"
          v-b-tooltip.hove
          title="Go to Manager portal"
        >Manager</button>
      </b-col>
      <b-col>
        <button
          type="button"
          v-on:click="goToTutorLoginPage()"
          class="btn btn-primary btn-lg home-row button"
          v-b-tooltip.hove
          title="Go to Tutor portal"
        >Tutor</button>
      </b-col>
      <b-col>
        <button
          type="button"
          v-on:click="goToStudentLoginPage()"
          class="btn btn-primary btn-lg home-row button"
          v-b-tooltip.hove
          title="Go to Student portal"
        >Student</button>
      </b-col>
    </b-row>
  </b-container>

  <div id="description" v-bind:style="{color: textColor}">
    <body>
      <hr />
      <!-- <p>Welcome to our company, <b>Tutor4All</b>. We open from 9-9, 7 days a week.<br/></p> -->

      <p>What we can provide</p>
      <ul>
        <li>High Shcool Courses: Math, Sciences, History, Religion Studies</li>
        <li>Cegep Courses: Math, Physics, Chemistry, English, French, Biolgy, Businese, etc.</li>
        <li>
          University Courses:
          <a
            href="https://mcgill.ca/study/2019-2020/courses/search"
          >McGill University</a>,
          <a
            href="https://www.concordia.ca/artsci/academics/undergraduate.html"
          >Concordia University</a>,
          <a
            href="https://admission.umontreal.ca/repertoire-des-cours/filtres/matiere_kin/"
          >University of Montreal</a>
        </li>
        <li>and more!</li>
      </ul>
    </body>
  </div>
</div>
</template>

<script>
import Router from "../router";
var config = require("../../config");

// axios config
var frontendUrl = "http://" + config.build.host + ":" + config.build.port;
var backendUrl =
  "http://" + config.build.backendHost + ":" + config.build.backendPort;

// var AXIOS = axios.create({
//     baseURL: backendUrl,
//     headers: {"Access-Control-Allow-Origin": frontendUrl}
// });
export default {
  name: "Hello",
  data() {
    return {
      msg: "Welcome to Your Vue.js App"
    };
  },
  methods: {
    goToLoginPage: function() {
      Router.push({
        path: "/login",
        name: "login"
      });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
p {
  font-family: fantasy;
  text-align: center;
  font-size: 30px;
}
ul {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  list-style-type: none;
  text-align: center;
  font-size: 20px;
}
#description {
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
