<template>
  <div id="student" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <span id="title" v-bind:style="{color : textColor}"></span>
    <div>
      <span id="title1"></span>
    </div>

    <b-container fluid>
      <center>
        <b-row id="findTutor">
          <form>
            Tutor ID:
            <input
              class="commissionField"
              type="number"
              id="tutprID"
              v-model="tutprID"
              placeholder="Enter tutor ID"
            />
          </form>
          <button
            type="button"
            id="myButton"
            @click="findTutor()"
            class="btn btn-primary btn-lg commissionField button"
            :class="buttonClass"
            title="Find the tutor"
          >Find Tutor</button>
        </b-row>
        <p>Tutor information</p>
        <b-row id="findTutor">
          <form>
            Commission percentage:
            <input
              class="commissionField"
              type="number"
              step="0.01"
              id="percentage"
              v-model="percentage"
              placeholder="Enter percentage"
            />
          </form>
          <button
            type="button"
            id="myButton"
            @click="setupCommission()"
            class="btn btn-primary btn-lg commissionField button"
            :class="buttonClass"
            title="Setup commission"
          >Setup commission</button>
        </b-row>
      </center>
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
    if (darkModeOn === "true") {
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
.button {
  color: white;
}

#findTutor {
  align-self: auto;
}
#b-container {
  vertical-align: center;
}
</style>