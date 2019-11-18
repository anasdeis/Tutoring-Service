<!--- This component acts as a page to view rooms--->
<template>
  <div id="room" class="card" v-bind:style="{ backgroundColor : bgColor }">
    <span id="title" v-bind:style="{color : textColor}">
      <center>View Rooms</center>
    </span>
    <div>
      <span id="title1"></span>
    </div>
    <b-container>
      <b-row>
        <b-col id="smallroom">
          <p>View Small Rooms 1-10</p>
          <select>
            <option value="RM01">RM01</option>
            <option value="RM02">RM02</option>
            <option value="RM03">RM03</option>
            <option value="RM04">RM04</option>
            <option value="RM05">RM05</option>
            <option value="RM06">RM06</option>
            <option value="RM07">RM07</option>
            <option value="RM08">RM08</option>
            <option value="RM09">RM09</option>
            <option value="RM10">RM10</option>
            <!-- <option selected="selected">Select</option> -->
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
            <option value="RM12">RM12</option>
            <option value="RM13">RM13</option>
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
      room: {
        type: Object
      },
      bgColor: "",
      textColor: "",
      error: "",
      output:""
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
      selectElement=document.querySelector('#select1');
      output = selectElement.value;
      document.querySelector('.output').textContent = output;
    },
    viewRoomStatus: function(roomID) {
      AXIOS.get('')
    }
  },
  mounted() {
    this.$root.$on("setDarkModeState", this.setDarkMode);
  }
};
</script>

<style>
</style>