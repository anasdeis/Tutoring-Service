<!--- This component acts as a page for tutor application, including accept/decline tutor --->
<template>
  <div id="tutorApplication" class="card" v-bind:style="{ backgroundColor : bgColor}">
    <span id="title" v-bind:style="{color : textColor}"></span>
    <div>
      <span id="title1"></span>
    </div>

    <!-- <b-container fluid>
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
    </b-container>-->
    <b-container fluid>
      <b-row>
        <b-col id="tutprApplicationTable">
          <p>View all the tutor applications</p>
          <table id="tAppTable">
            <tr>
              <th>Tutor Id</th>
              <th>Tutor Application ID</th>
            </tr>
            <tr>
              <td>{{tutorID}}</td>
              <td>{{tutorApplicationID}}</td>
            </tr>
          </table>
          <form>
            Enter tutor application ID:
            <input
              class="tAppField"
              text="number"
              id="tAppID"
              v-model="tAppID"
              placeholder="Enter tutor application ID"
            />
          </form>
          <button
            type="button"
            @click="getTutorApplication(tAppID)"
            class="btn btn-primary btn-lg viewTApp button"
            v-b-tooltip.hover
            title="Dispaly selected tutor application"
          >View detail</button>
        </b-col>

        <b-col id="detailofTApp">
          <p>Here is the detail of the tutor application you select</p>
          <!-- <table id="tAppDetail">
            <tr>
              <th>Tutor Id</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email address</th>
              <th>Phone number</th>
              <th>Tutor Application ID</th>
            </tr>
            <tr>
              <td>{{firstName}}</td>
              <td>{{lastName}}</td>
              <td>{{email}}</td>
              <td>{{phone}}</td>
              <td>{{tutorApplicationID}}</td>
            </tr>
          </table>-->
          <b-col id="detailofTApp">{{detailofTApp}}</b-col>
          <button
            type="button"
            @click="updateTutorApplicationisAccepted(tAppID,isAccept)"
            class="btn btn-primary btn-lg viewTApp button"
            v-b-tooltip.hover
            title="Approve"
          >Approve</button>
          <button
            type="button"
            @click="updateTutorApplicationisAccepted(tAppID,isAccept)"
            class="btn btn-primary btn-lg viewTApp button"
            v-b-tooltip.hover
            title="Decline"
          >Decline</button>
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

var tAppTable = {
  // GET YOUR TABLE FROM THE BACKEND- all tutor applications
};

var detailofTApp = {
  // GET YOUR SELECTED TUTOR APPLICATION OBJECT IN THE BACKEND AND PUT IT HERE
};
// detailofTApp.displayObject("detailofTApp", detailofTApp); // we might need this line to display
export default {
  data() {
    return {
      tutor: {
        type: Object
      },
      bgColor: "",
      textColor: "",
      tAppID: "",
      isAccept: ""
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
    getTutorApplication: function(tAppID) {
      // TODO: AXIOS METHODS GOES HERE TO GET A TUTOR APPLICATION RELATED WITH THE ENTERED TUTPR APPLICATION ID
    },
    updateTutorApplicationisAccepted: function(tAppID, isAccept) {
      // TODO: AXIOS METHODS GOES HERE TO ACCEPT/DELINE A TUTOR APPLICATION
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
  width: auto;
}
#tutorApplication {
  align-content: center;
  height: auto;
}
#tAppTable {
  margin-left: auto;
  margin-right: auto;
  border: 4px;
  border: 1px solid black;
}
#tAppDetail {
  margin-left: auto;
  margin-right: auto;
  border: 4px;
  border: 1px solid black;
}
</style>