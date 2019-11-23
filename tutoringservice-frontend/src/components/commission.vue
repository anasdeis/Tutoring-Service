<template>
  <div id="student" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <span id="title" v-bind:style="{color : textColor}"></span>
    <div>
      <span id="title1"></span>
    </div>

    <b-container fluid>
      <center>
        <!-- <b-row id="findTutor">
          <form>
            Tutor ID:
            <input
              class="commissionField"
              type="number"
              id="tutorID"
              v-model="tutorID"
              placeholder="Enter tutor ID"
            />
          </form>
          <button
            type="button"
            id="myButton"
            @click="getTutor(tutorID)"
            class="btn btn-primary btn-lg commissionField button"
            :class="buttonClass"
            title="Find the tutor"
          >Find tutor</button>
        </b-row>-->
        <!-- <p>Tutor information</p>
        <b-row id="findTutor">
        </b-row>-->
        <form>
          Commission ID:
          <input
            class="commissionField"
            type="number"
            id="commissionID"
            v-model="commissionID"
            placeholder="Enter Commission ID"
          />
        </form>
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
        <form>
          Offering ID:
          <input
            class="commissionField"
            type="number"
            id="offeringID"
            v-model="offeringID"
            placeholder="Enter Offering ID"
          />
        </form>
        <form>
          Confirm your manager ID:
          <input
            class="commissionField"
            type="number"
            id="managerID"
            v-model="managerID"
            placeholder="Enter managerID"
          />
        </form>
        <button
          type="button"
          id="myButton"
          @click="createCommission(percentage,commissionID, manager, offeringID, system)"
          class="btn btn-primary btn-lg commissionField button"
          :class="buttonClass"
          title="Setup commission"
        >Setup commission</button>
      </center>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";
import Router from "../router";

var config = require("../../config");

var frontendUrl = "http://" + config.build.host + ":" + config.build.port;
var backendUrl = "http://localhost:8080/";
// "http://" + config.build.backendHost + ":" + config.build.backendPort;

// axios config
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  data() {
    return {
      bgColor: "",
      textColor: "",
      commissionID: "",
      percentage: "",
      manager: "",
      offeringID: "",
      error: "",
      system: "1"
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
    },
    createCommission: function(
      percentage,
      commissionID,
      manager,
      offeringID,
      system
    ) {
      AXIOS.post(
        "/commission/create/" +
          commissionID +
          "?percentage=" +
          percentage +
          "&manager=" +
          manager +
          "&offeringID=" +
          offeringID +
          "&system=" +
          system
      ).then(response => {
        this.commission = response.data;
      });
    }
  },
  mounted() {
    this.$root.$on("setDarkModeState", this.setDarkMode);
  }
};
</script>

<style>
#myButton {
  color: white;
  margin-top: 20px;
}

#b-container {
  vertical-align: center;
  margin-top: auto;
  margin-bottom: auto;
  height: auto;
}

.commissionField {
  margin-top: 5px;
  margin-bottom: 5px;
}
</style>