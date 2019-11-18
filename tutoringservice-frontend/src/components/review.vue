<template>
  <div id="studentReview" class="card" v-bind:style="{ backgroundColor : bgColor}">
    <span id="title" v-bind:style="{color : textColor}"></span>
    <div>
      <span id="title1"></span>
    </div>

    <b-container fluid>
      <b-col id="reviewList">
        <p>View student reviews</p>
        <table id="reviewTable">
          <tr>
            <th>Review ID</th>
            <th>Comment</th>
            <th>Offering</th>
            <th>Approved</th>
          </tr>
          <tr w3-repeat="reviews">
            <td>{{reviewID}}</td>
            <td>{{comment}}</td>
            <td>{{offeringID}}</td>
            <td>{{isApproved}}</td>
          </tr>
        </table>

        <b-row id="myButton"><center>
            <form>
              Enter review ID:
              <input
                class="reviewField"
                type="text"
                id="reviewID"
                v-model="reviewID"
                placeholder="Enter review ID"
              />
            </form>
            <button id="approve"
              type="button"
              @click="updateReviewIsApproved()"
              class="btn btn-primary btn-lg tutorField button"
              v-b-tooltip.hove
              title="Approve this review!"
            >Approve</button>
            <button id="decline"
              type="button"
              @click="declineReview()"
              class="btn btn-primary btn-lg tutorField button"
              v-b-tooltip.hove
              title="Decline this review!"
            >Decline</button>
        </center></b-row>
      </b-col>
    </b-container>
  </div>
</template>


<script src="https://www.w3schools.com/lib/w3.js"></script>

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

var reviewTable = {
  reviews: [
    {
      reviewID: "1000",
      comment: "Amazing Course",
      offeringID: "ECSE321F",
      isApproved: true
    },
    {
      reviewID: "1001",
      comment: "So hard",
      offeringID: "ECSE321F",
      isApproved: true
    },
    {
      reviewID: "1002",
      comment: "I don't like this",
      offeringID: "ECSE321F",
      isApproved: false
    }
  ]
};

// studentReview.displayObject("reviewTable", reviewTable);

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
#reviewTable {
  margin-top: 20px;
  border-width: 5px;
  border-style: groove;
  margin-left: auto;
  margin-right: auto;
}
table, th, td {
  border: 1px solid black;
}
form{
  color:black;
}
#myButton {
  margin-left: auto;
  margin-right: auto;
  margin-top: 5px;
}
#approve{
  margin-right: 5px;
  margin-left: 5px;
}
#decline{
  margin-right: 5px;
  margin-left: 5px;
}
</style>