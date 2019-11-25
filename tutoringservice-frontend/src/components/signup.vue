<!--- This component acts as a page to createManager --->
<template>
  <div id="createManager" class="card" v-bind:style="{ backgroundColor : bgColor }">
    <span id="title" v-bind:style="{color : textColor}">
      <center>Create an account</center>
    </span>
    <div>
      <span id="title1"></span>
    </div>

    <b-container fluid v-bind:style="{ color: titleColor}">
      <form>
        First name:
        <input
          class="createManagerField"
          type="text"
          id="first"
          v-model="first"
          placeholder="Enter first name"
        />
      </form>
      <form>
        Last name:
        <input
          class="createManagerField"
          type="text"
          id="last"
          v-model="last"
          placeholder="Enter last name"
        />
      </form>
      <form>
        Date of Birth:
        <input
          class="createManagerField"
          type="date"
          id="dob"
          v-model="dob"
          placeholder="YYYY-MM-DD"
        />
      </form>
      <form>
        Email:
        <input
          class="createManagerField"
          type="text"
          id="email"
          v-model="email"
          placeholder="Enter email"
        />
      </form>
      <form>
        Phone number:
        <input
          class="createManagerField"
          type="text"
          id="phone"
          v-model="phone"
          placeholder="Enter phone number"
        />
      </form>
      <form>
        Manager ID (all numbers):
        <input
          class="createManagerField"
          type="text"
          id="managerID"
          v-model="managerID"
          placeholder="Enter manager ID"
        />
      </form>
      <form>
        Username:
        <input
          class="createManagerField"
          type="text"
          id="userName"
          v-model="userName"
          placeholder="Enter username"
        />
      </form>
      <!-- <form>
        Password:
        <input
          class="createManagerField"
          type="password"
          id="password"
          v-model="pw"
          placeholder="Enter password"
        />
      </form> -->
      <center>
        <button
          type="button"
          id="myButton"
          @click="createManager(first,last,dob,email,phone,managerID,userName,tutoringSystemID)"
          class="btn btn-primary btn-lg createManagerField button"
          
          v-b-tooltip.hover
          title="Click to create an account!"
        >Create</button>
      </center>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";
import Router from "../router";
import Axios from "axios";

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
      manager: {
        type: Object
      },
      bgColor: "",
      textColor: "",
      buttonClass: "",
      error: "",
      first: "",
      last: "",
      dob: "",
      email: "",
      phone: "",
      managerID: "",
      // username: "",
      // password: "",
      userName: "",
      // request: null,
      // review: null,
      // commission: null,
      // classroom: null,
      tutoringSystemID: "1"
    };
  },
  created: function() {
    var darkModeOn = localStorage.getItem("DarkModeOn");
    if (darkModeOn === "true") {
      this.bgColor = "rgb(53,58,62)";
      this.textColor = "white";
      this.buttonClass = "btn btn-dark btn-lg createManagerField";
    } else {
      this.bgColor = "rgb(250,250,250)";
      this.textColor = "black";
      // this.bgColor = "rgb(248, 249, 251)";
      this.buttonClass = "btn btn-white btn-lg createManagerField";
    }
  },
  methods: {
    // send get request to fetch manager
    // getuserName: function(username, password) {
    //   AXIOS.get('/userName/list/'+username)
    //     .then(response => {
    //       // this.userName = response.data;
    //       var userName = ;
    //     })
    //     // .catch(e => {
    //     //   console.log(e.message);
    //     //   document.getElementById("title1").innerText =
    //     //     "Account does not exist, please try again";
    //     // });
    // },
    // myuserName: function (username, password) {
    //   var username=document.getElementById("username");
    //   var password=document.getElementById("password");
    // },
    createManager: function(
      first,
      last,
      dob,
      email,
      phone,
      managerID,
      userName,
      tutoringSystemID
    ) {
      AXIOS.post("");
      AXIOS.post(
        "/manager/create/" +
          managerID +
          "?first=" +
          first +
          "&last=" +
          last +
          "&dob=" +
          dob +
          "&email=" +
          email +
          "&phone=" +
          phone +
          "&userName=" +
          userName +
          "&tutoringSystemID=" +
          tutoringSystemID
      )
        .then(response => {
          this.manager = response.data;
          this.goToHomePage();
        })
        .catch(e => {
          console.log(e.message);
          // this.error = error
          document.getElementById("title1").innerText =
            "Please enter missing information!";
        });
    },
    goToHomePage: function() {
      Router.push({
        path: "/home",
        name: "home"
      });
    },
    // goTocreateManagerPage: function() {
    //     Router.push({
    //         path: "/createManager",
    //         name: "createManager"
    //     });
    // },
    setDarkMode: function() {
      var darkModeOn = localStorage.getItem("DarkModeOn");
      if (darkModeOn === "true") {
        this.bgColor = "rgb(53, 58, 62)";
        this.textColor = "white";
        this.buttonClass = "btn btn-dark btn-lg createManagerField";
      } else {
        this.bgColor = "rgb(250,250,250)";
        this.textColor = "black";
        this.buttonClass = "btn btn-white btn-lg createManagerField";
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
#title {
  text-align: center;
  color: white;
  font-size: 26px;
  padding-left: 15px;
}
#title1 {
  text-align: left;
  color: red;
  font-size: 15px;
  padding-left: 15px;
}
#send {
  align-content: right;
}
#name {
  text-align: left;
  color: white;
  font-size: 26px;
  padding-left: 15px;
}
#createManager {
  width: 30%;
  max-height: auto;
  min-width: 550px;
  margin: auto;
  margin-top: auto;
  padding: 15px;
  text-align: center;
  margin-bottom: auto;
}
b-container {
  height: 100%;
}
.createManagerField {
  width: auto;
  height: auto;
  border-radius: 4px;
  border: 0px;
  padding: 2%;
  margin: auto;
  margin-top: 5px;
}
.button {
  color: white;
}
#btn1 {
  align-self: center;
}
</style>
