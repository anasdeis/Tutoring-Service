<!--- This component acts as a page for tutor application, including accept/decline tutor --->
<template>
  <div id="tutorApplication" class="card" v-bind:style="{ backgroundColor : bgColor}">
    <span id="title" v-bind:style="{color : textColor}"></span>
    <div>
      <span id="title1"></span>
    </div>

    <b-container fluid>
      <b-col id="tutorApplicationlist">
        <p>View tutor application list</p>
        <p>TODO get the list from backend and implement the corresponding buttons</p>
        <select class="tutorApplicationField"></select>
      </b-col>

      <b-row id="tutorApplication">
        <b-col>
          <button
            type="button"
            @click="acceptTutor()"
            class="btn btn-primary btn-lg tutorApplicationField button"
            v-b-tooltip.hove
            title="Accept a tutor!"
          >Accept Tutor</button>
        </b-col>

        <b-col>
          <button
            type="button"
            @click="declineTutor()"
            class="btn btn-primary btn-lg tutorApplicationField button"
            v-b-tooltip.hove
            title="Decline a tutor!"
          >Decline Tutor</button>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";
import Router from "../router";

var config = require("../../config");

var frontendUrl = "http://" + config.build.host + ":" + config.build.port;
var backendUrl =
  "http://" + config.build.backendHost + ":" + config.build.backendPort;

// axios config
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  data() {
    return {
      tutor: {
        type: Object
      },
      bgColor: "",
      textColor: ""
    };
  },
  created: function() {
    var darkModeOn = localStorage.getItem("DarkModeOn");
    if (darkModeOn == "true") {
      this.bgColor = "rgb(53,58,62)";
      this.textColor = "white";
      this.buttonClass = "btn btn-dark btn-lg signupField";
    } else {
      this.bgColor = "rgb(250,250,250)";
      this.textColor = "black";
      // this.bgColor = "rgb(248, 249, 251)";
      this.buttonClass = "btn btn-white btn-lg signupField";
    }
  },
  methods: {
    setDarkMode: function() {
      var darkModeOn = localStorage.getItem("DarkModeOn");
      if (darkModeOn === "true") {
        this.bgColor = "rgb(53, 58, 62)";
        this.textColor = "white";
        this.buttonClass = "btn btn-dark btn-lg signupField";
      } else {
        this.bgColor = "rgb(250,250,250)";
        this.textColor = "black";
        this.buttonClass = "btn btn-white btn-lg signupField";
      }
    }
  },
  mounted() {
    // Listens to the setDarkModeState event emitted from the LogoBar component
    this.$root.$on("setDarkModeState", this.setDarkMode);
  }
};
</script>

<style>
#myButton {
  color: royalblue;
  border: 0px;
  border-radius: 4px;
  padding: 2px;
  margin: auto;
}
b-container {
  height: auto;
}
#tutorApplication {
  align-content: center;
  height: auto;
}
</style>