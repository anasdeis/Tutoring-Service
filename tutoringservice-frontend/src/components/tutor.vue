<!--- This component acts as a page for tutor, including add/fire tutor --->
<template>
  <div id="tutor" class="card" v-bind:style="{ backgroundColor : bgColor}">
    <span id="title" v-bind:style="{color : textColor}"></span>
    <div>
      <span id="title1"></span>
    </div>

    <!-- <b-container fluid>
      <b-col id="tutorlist">
        <p>View tutor list</p>
        <p>TODO get the list from backend and implement the two buttons</p>
        <select class="tutorField"></select>
      </b-col>

      <b-row id="myButton">
        <b-col>
          <button
            type="button"
            @click="addTutor()"
            class="btn btn-primary btn-lg tutorField button"
            v-b-tooltip.hove
            title="Add a tutor!"
          >Add Tutor</button>
        </b-col>

        <b-col>
          <button
            type="button"
            @click="removeTutor()"
            class="btn btn-primary btn-lg tutorField button"
            v-b-tooltip.hove
            title="Remove a tutor!"
          >Remove Tutor</button>
        </b-col>
      </b-row>
    </b-container>-->

    <b-container fluid>
      <b-row>
        <b-col id="tutor">
          <p>View all the tutors</p>
          <table id="tutorTable">
            <tr>
              <th>Tutor Id</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
            </tr>
            <tr>
              <td>{{tutorID}}</td>
              <td>{{firstName}}</td>
              <td>{{lastName}}</td>
              <td>{{email}}</td>
            </tr>
          </table>
          <form>
            Enter tutor ID:
            <input
              class="tutorField"
              text="number"
              id="tutorID"
              v-model="tutorID"
              placeholder="Enter tutor ID"
            />
          </form>
          <button
            type="button"
            @click="getTutor(tutorID)"
            class="btn btn-primary btn-lg viewTutor button"
            v-b-tooltip.hover
            title="Dispaly selected tutor"
          >View detail</button>
        </b-col>

        <b-col id="detailOfTutor">
          <p>Here is the detail of the tutor you select</p>
          <button
            type="button"
            @click="deleteTutor(tutorID)"
            class="btn btn-primary btn-lg viewTutor button"
            v-b-tooltip.hover
            title="Remove"
          >Remove</button>
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

var tutorTable = {
  // GET YOUR TABLE FROM THE BACKEND- all tutor applications
};

var detailOfTutor = {
  // GET YOUR SELECTED TUTOR APPLICATION OBJECT IN THE BACKEND AND PUT IT HERE
};

export default {
  data() {
    return {
      tutor: {
        type: Object
      },
      bgColor: "",
      textColor: "",
      tutorID: ""
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
    getTutor: function(tutorID) {
      AXIOS.get("/tutor/=" + tutorID).then(response => {
        this.getTutor = response.data;
      });
    },
    deleteTutor: function(tutorID) {
      AXIOS.delete("/tutor/delete/=" + tutorID);
    }
  },
  mounted() {
    // Listens to the setDarkModeState event emitted from the LogoBar component
    this.$root.$on("setDarkModeState", this.setDarkMode);
  }
};
</script>

<style>
p {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
}
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
#tutorTable {
  margin-left: auto;
  margin-right: auto;
}
</style>