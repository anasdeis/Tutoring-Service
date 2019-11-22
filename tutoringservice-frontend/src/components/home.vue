<!--- this component is the home page --->
<template>
  <div id="home-container" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <span id="title" v-bind:style="{color: textColor}">Home Page</span>
    <div>
      <span id="title1"></span>
    </div>
    <p>Check out our functionality for you!</p>
    <b-container fluid>
      <b-row id="functionality">
        <b-col>
          <button
            type="button"
            @click="goToRoomPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="View Rooms"
          >Rooms</button>
        </b-col>
        <b-col>
          <button
            type="button"
            @click="goToTutorApplicationPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="View Tutor Application"
          >View Tutor Application</button>
        </b-col>
        <b-col>
          <button
            type="button"
            @click="goToTutorPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="View Tutors"
          >View Tutors</button>
        </b-col>
        <b-col>
          <button
            type="button"
            @click="goToStudentPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="View Studnet"
          >View Student</button>
        </b-col>

        <b-col>
          <button
            type="button"
            @click="goToCommissionPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="Setup Commission"
          >Setup Commission</button>
        </b-col>
        <b-col>
          <button
            type="button"
            @click="goToRequestPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="View Subject Request"
          >Subject Request</button>
        </b-col>
        <b-col>
          <button
            type="button"
            @click="goToReviewPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="View Student Review"
          >View Student Reviews</button>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";
import Router from "../router";
// import func from '../../vue-temp/vue-editor-bridge';

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
      home: {
        type: Object
      },
      bgColor: "",
      textColor: "",
      // error: ""
    };
  },
  created: function() {
    var isLoggedIn = localStorage.getItem("isLoggedIn");
    if (isLoggedIn === "false") {
      Router.push({
        path: "/login",
        name: "LoginPage"
      });
    }

    var darkModeOn = localStorage.getItem("DarkModeOn");
    if (darkModeOn === "true") {
      this.bgColor = "rgb(53,58,62)";
      this.textColor = "white";
    } else {
      this.bgColor = "rgb(250,250,250)";
      this.textColor = "black";
      // this.bgColor = "rgb(248, 249, 251)";
    }

    // since we will only have one manager, I don't think the following 4 blocks of code are necessary
    // get all managers from backend
    // AXIOS.get('/managers').then(response => {
    //     this.managers = response.data;
    //     this.managersLoaded = true;
    // })
    // .catch(e => {
    //     this.error = e;
    // });

    // // get all managers from manager database
    // AXIOS_Manager.get("/getAllManagers").then(response => {
    //     this.externalManagers = response.data;
    //     this.externalManagersLoaded = true;
    // })
    // .catch(e => {
    //     this.error = e;
    // });

    // // get all logins from backend
    // AXIOS.get('/logins').then(response => {
    //     this.logins = response.data;
    //     this.loginsLoaded = true;
    // })
    // .catch(e => {
    //     this.error = e;
    // });

    // // get all logins from database
    // AXIOS_Logins.get("getAllLogins").then(response => {
    //     this.externalLogins = response.data;
    //     this.externalLoginsLoaded = true;
    // })
    // .catch(e => {
    //     this.error = e;
    // });
  },
  methods: {
    setDarkMode: function(darkModeOn) {
      if (darkModeOn) {
        this.bgColor = "rgb(53, 58, 62)";
        this.textColor = "white";
      } else {
        this.bgColor = "rgb(250,250,250)";
        this.textColor = "black";
      }
    },
    goToRoomPage: function() {
      Router.push({
        path: "/room",
        name: "room"
      });
    },
    goToTutorPage: function() {
      Router.push({
        path: "/tutor",
        name: "tutor"
      });
    },
    goToTutorApplicationPage: function() {
      Router.push({
        path: "/tutorApplication",
        name: "tutorApplication"
      });
    },
    goToStudentPage: function() {
      Router.push({
        path: "/student",
        name: "student"
      });
    },
    goToCommissionPage: function() {
      Router.push({
        path: "/commission",
        name: "commission"
      });
    },
    goToRequestPage: function() {
      Router.push({
        path: "/subjectRequest",
        name: "subjectRequest"
      });
    },
    goToReviewPage: function() {
      Router.push({
        path: "/review",
        name: "review"
      });
    }
  },
  mounted() {
    // Listens to the setDarkModeState event emitted from the LogoBar component
    this.$root.$on("setDarkModeState", this.setDarkMode);
  }
};
</script>

<style>
b-container {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  height: auto;
  width: auto;
}
#functionality {
  color: black;
  font-size: 25px;
  padding-left: 15px;
}
</style>