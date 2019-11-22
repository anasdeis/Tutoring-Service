<!--- This component acts as a page to view rooms--->
<template>
  <div id="room" class="card" v-bind:style="{ backgroundColor : bgColor }">
    <span id="title" v-bind:style="{color : textColor}">
      <center>View Rooms</center>
    </span>
    <div>
      <span id="title1"></span>
    </div>
    <!-- <b-container>
      <b-row>
        <b-col id="smallroom">
          <p>View Small Rooms 1-10</p>
          <select>
            <option value="RM01">RM01</option>
          </select>
          <p>
            The room you select is: <span class="output"></span></p>
            <button onClick="getOpinion()">Select Room</button>
          
          <p>
            The romm you select is: <span class="status"></span></p>
          <button
            type="button"
            @click="viewRoomStatus()"
            class="btn btn-primary btn-lg room button"
            v-b-tooltip.hover
            title="View status"
          >View Status</button>
          
        </b-col>
        <b-col id="bigroom">
          <p>View Big Rooms 11-13 for group review</p>
          <select>
            <option value="RM11">RM11</option>
          </select>
          <button
            type="button"
            @click="viewRoomStatus()"
            class="btn btn-primary brn-lg room button"
            v-b-tooltip.hover
            title="View status"
          >View Status</button>
        </b-col>
      </b-row>
    </b-container>-->
    <b-container fluid>
      <b-row>
        <b-col id="room">
          <p>View all the rooms, room 1-10 are small room and 11-13 are big room</p>
          <center><table id="roomTable">
            <tr>
              <th>Room Code</th>
              <th>Big Room</th>
              <th>Room Avaliability</th>
            </tr>
            <tr>
              <td>{{roomCode}}</td>
              <td>{{isBigRoom}}</td>
              <td>{{isAvaliable}}</td>
            </tr>
          </table></center>
          <button id="myButton"
            type="button"
            @click="getAllRoomSchedules()"
            class="btn btn-primary btn-lg room button"
            v-b-tooltip.hover
            title="View status"
          >View Status</button>
        </b-col>

        <b-col id="reviewSession">
          <form>
            Enter Big Room Code (RM11-RM13) to schedule a group review:
            <input
              class="reviewField"
              text="text"
              id="roomCode"
              v-model="roomCode"
              placeholder="Enter Room Code"
            />
          </form>
          <form>
            Enter Offering ID:
            <input
              class="reviewField"
              text="text"
              id="offeringID"
              v-model="offeringID"
              placeholder="Enter offering ID"
            />
          </form>
          <button id="myButton"
            type="button"
            @click="createReviewSession(offeringID, managerID, roomCode, tutoringSystemID)"
            class="btn btn-primary btn-lg createReview button"
            v-b-tooltip.hover
            title="Create Review Session"
          >Create Review Session</button>
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

var roomTable = {
  // GET YOUR ROOM SCHEDULES FROM THE BACKEND THEN REFLECT TO THE TABLE
};
export default {
  data() {
    return {
      room: {
        type: Object
      },
      bgColor: "",
      textColor: "",
      error: "",
      output: "",
      offeringID:"",
      managerID:"", 
      roomCode:"", 
      tutoringSystemID:"1"
    };
  },
  created: function() {
    var darkModeOn = localStorage.getItem("DarkModeOn");
    if (darkModeOn === "true") {
      this.bgColor = "rgb(53,58.62)";
      this.textColor = "white";
    } else {
      this.bgColor = "rgb(250,250,250)";
      this.textColor = "black";
    }
  },
  methods: {
    setDarkMode: function() {
      var darkModeOn = localStorage.getItem("DarkModeOn");
      if (darkModeOn === "true") {
        this.bgColor = "rgb(53, 58, 62)";
        this.textColor = "white";
        // this.buttonClass = "btn btn-dark btn-lg loginField";
      } else {
        this.bgColor = "rgb(250,250,250)";
        this.textColor = "black";
        // this.buttonClass = "btn btn-white btn-lg loginField";
      }
    },
    getOpinion: function() {
      selectElement = document.querySelector("#select1");
      output = selectElement.value;
      document.querySelector(".output").textContent = output;
    },
    getAllRoomSchedules: function() {
      AXIOS.get("/classroom/list");
    },
    createReviewSession: function(offeringID, managerID, roomCode, tutoringSystemID) {
      AXIOS.post(
        "/classroom/review/create/" +
          offeringID +
          "?managerID=" +
          managerID +
          "?roomCode=" +
          roomCode +
          "?tutoringSystemID=" +
          tutoringSystemID
      ).then(response => {
        this.createReviewSession = response.data;
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
  margin-top: 5px;
  margin-bottom: 5px;
}
#reviewSession {
  width: auto;
  height: auto;
  margin-top: 5px;
  margin-bottom: 5px;
}
#roomTable {
  margin: auto;
  border: 2px;
  border: 1px solid black;
}
#myButton {
  border: 0px;
  border-radius: 4px;
  padding: 2px;
  margin-top: 5px;
}
</style>