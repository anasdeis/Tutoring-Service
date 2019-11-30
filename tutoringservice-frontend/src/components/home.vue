<!--- this component is the home page --->
<template>
  <div id="home" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <span class="text-center" style="font-size:35px;" id="title" v-bind:style="{color: textColor}"><strong>Home Page</strong></span>
    <p style="padding:15px;font-size:22px;" v-bind:style="{color: textColor}">Check out the following features!</p>
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
            @click="goToSubjectPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="View Subjects"
          >Subjects</button>
        </b-col>
         <b-col>
          <button
            type="button"
            @click="goToOfferingPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="View Offerings"
          >Offerings</button>
        </b-col>
        <b-col>
          <button
            type="button"
            @click="goToTutorPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="View Tutors"
          >Tutors</button>
        </b-col>
        <b-col>
          <button
            type="button"
            @click="goToStudentPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="View Studnet"
          >Students</button>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <button
            type="button"
            @click="goToCommissionPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="Setup Commission"
          >Create Commission</button>
        </b-col>
        <b-col>
          <button
            type="button"
            @click="goToTutorApplicationPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="View Tutor Application"
          >Tutor Applications</button>
        </b-col>
        <b-col>
          <button
            type="button"
            @click="goToRequestPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="View Subject Request"
          >Subject Requests</button>
        </b-col>

        <b-col>
          <button
            type="button"
            @click="goToReviewPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="View Student Review"
          >Student Reviews</button>
        </b-col>

        <b-col>
          <button
            type="button"
            @click="goToAvailableSessionPage()"
            class="btn btn-primary btn-lg home button"
            v-b-tooltip.hover
            title="View Available Sessions"
          >Available Sessions</button>
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
var backendUrl = "http://localhost:8080/";
// "http://" + config.build.backendHost + ":" + config.build.backendPort;

// axios config
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  name: "home",
  data() {
    return {
      bgColor: "",
      textColor: ""
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
    goToOfferingPage: function() {
      Router.push({
        path: "offering",
        name: "offering"
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
    goToSubjectPage: function() {
      Router.push({
        path: "/subject",
        name: "subject"
      });
    },
    goToReviewPage: function() {
      Router.push({
        path: "/review",
        name: "review"
      });
    },
    goToAvailableSessionPage: function() {
      Router.push({
        path: "/availableSession",
        name: "availableSession"
      });
    }
  },
  mounted() {
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
button {
  width: 150px;
  height: auto;
  margin-bottom: 10px;
  margin-left: auto;
}
#functionality {
  color: black;
  font-size: 25px;
}
</style>