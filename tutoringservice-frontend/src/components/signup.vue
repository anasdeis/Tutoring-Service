<!--- This component acts as a page to createManager --->
<template>
  <div id="createManager" class="card" v-bind:style="{ backgroundColor : bgColor }">
    <span id="title" v-bind:style="{color : textColor}">
      <center>Create an account</center>
    </span>
    <b-container fluid :style="{ color: textColor}">
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
        Birthdate:
        <input
          class="createManagerField"
          type="date"
          id="dob"
          v-model="dob"
          placeholder="MM-DD-YYYY"
          style="margin-right:13px;"
        />
      </form>
      <form>
        <label style="margin-left:77px;">Email:</label>
        <input
          class="createManagerField"
          type="text"
          id="email"
          v-model="email"
          placeholder="Enter email"
          style="margin-right:45px;"
        />
      </form>
      <form>
        <label style="margin-left:77px;">Phone:</label>
        <input
          class="createManagerField"
          type="text"
          id="phone"
          v-model="phone"
          placeholder="Enter phone number"
          style="margin-right:52px;"
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
          style="margin-right:3px;"
        />
      </form>
      <form>
        Password:
        <input
          class="createManagerField"
          type="password"
          id="password"
          v-model="password"
          placeholder="Enter password"
        />
      </form>
      <center>
        <button
          type="button"
          id="myButton"
          @click="createManager()"
          class="btn btn-primary btn-lg createManagerField button"
          v-b-tooltip.hover
        >Create</button>
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
      buttonClass: "",
      errorSignup: "",
      first: "",
      last: "",
      dob: "",
      email: "",
      phone: "",
      manager: [],
      userName: "",
      password: "",
      managerID: "",
      login: [],
      tutoringSystem: []
    };
  },
  created: function() {
    this.updateTutoringSystem();
    this.updateLogin();
    this.setDarkMode()
  },
  methods: {
    updateLogin() {
      AXIOS.get("login/list/")
        .then(response => {
          this.login = response.data;
        })
        .catch(e => {
          this.errorSignup = e.message
          console.log(this.errorSignup);
        });
    },
    updateTutoringSystem() {
      AXIOS.get("tutoringSystem/list/")
        .then(response => {
          this.tutoringSystem = response.data;
        })
        .catch(e => {
          this.errorSignup = e.message
          console.log(this.errorSignup);
        });
    },
    generateManagerID() {
      if (this.tutoringSystem != "") {
        this.managerID = 0;
        for (var i = 0; i < this.tutoringSystem.length; i++) {
          if (this.tutoringSystem[i].tutoringSystemID == 1) {
            for (var j = 0; j < this.tutoringSystem[i].person.length; j++) {
              if (this.tutoringSystem[i].person[j] > this.managerID) {
                this.managerID = this.tutoringSystem[i].person[j];
              }
            }
          } else {
            alert("No tutoring system with ID=1 is found!");
            return -1;
          }
        }
      } else {
        alert("ERROR: No tutoring system is found!");
        return -1;
      }
      this.managerID += 1;
    },
    ValidateSignup: function() {
      if(this.userName != "" && this.password != "" && this.first != "" && this.last != "" && this.dob != "" && this.phone != "" && this.email != "") { 
        var isValid = true
        for(var i=0; i < this.login.length; i++){
          if(this.login[i].userName == this.userName){
            isValid = false
            break;
          }
        }
        if(isValid == false){
          alert("ERROR: The username " +  this.userName + " already exists! Choose another one")
          return -1
        } 
      }
      else{
        alert("ERROR: All entries must be present to signup!")
        return -1
      }
    },
    setLogin() {
      AXIOS.post("login/" + this.userName + "?password=" + this.password)
        .then(response => {
          this.login.push(response.data);
          this.errorSignup = "";
        })
        .catch(e => {
          var errorMsg =
            e.response.status +
            " " +
            e.response.data.error +
            ": " +
            e.response.data.message;
          console.log(errorMsg);
          this.errorSignup = errorMsg;
        });
      alert("Your assigned manager ID: " + this.managerID);
      if (this.errorSignup != "") {
        alert("ERROR: " + this.errorSignup);
        return -1;
      }
    },
    createManager: function() {
      if (this.generateManagerID() == -1) {
        return -1;
      }
      if(this.ValidateSignup() == -1){
        return -1
      }
      if (this.setLogin() == -1) {
        return -1;
      }
      AXIOS.post(
        "/manager/create/" +
          this.managerID +
          "?first=" +
          this.first +
          "&last=" +
          this.last +
          "&dob=" +
          this.dob +
          "&email=" +
          this.email +
          "&phone=" +
          this.phone +
          "&userName=" +
          this.userName +
          "&tutoringSystemID=1"
      )
        .then(response => {
          this.manager.push(response.data);
          this.errorSignup = "";
        })
        .catch(e => {
          var errorMsg =
            e.response.status +
            " " +
            e.response.data.error +
            ": " +
            e.response.data.message;
          console.log(errorMsg);
          this.errorSignup = errorMsg;
        });
      this.first = "";
      this.last = "";
      this.dob = "";
      this.email = "";
      this.phone = "";
      this.userName = "";
      this.password = "";
      alert(
        "You clicked on create an account! Validating before redirecting.."
      );
      if (this.errorSignup != "") {
        alert("ERROR: " + this.errorSignup);
        return -1;
      } else {
        this.$events.fire("loggedIn-set", this.userName);
        this.goToHomePage();
      }
    },
    goToHomePage: function() {
      Router.push({
        path: "/home",
        name: "home"
      });
    },
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
  margin-left: 5px;
}
.button {
  color: white;
}
#myButton{
  align-self: center;
}
</style>