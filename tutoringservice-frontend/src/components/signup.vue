<!--- This component acts as a page to signup --->
<template>
  <div id="signup" class="card" v-bind:style="{ backgroundColor : bgColor }">
    <span id="title" v-bind:style="{color : textColor}">
      <center>Create an account</center>
    </span>
    <div>
      <span id="title1"></span>
    </div>

    <b-container fluid>
      <form>
        First name:
        <input
          class="signupField"
          type="text"
          id="first"
          v-model="first"
          placeholder="Enter first name"
        />
      </form>
      <form>
        Last name:
        <input
          class="signupField"
          type="text"
          id="last"
          v-model="last"
          placeholder="Enter last name"
        />
      </form>
      <form>
        Date of Birth:
        <input
          class="signupField"
          type="date"
          id="dob"
          v-model="dob"
          placeholder="YYYY-MM-DD"
        />
      </form>
      <form>
        Email:
        <input
          class="signupField"
          type="text"
          id="email"
          v-model="email"
          placeholder="Enter email"
        />
      </form>
      <form>
        Phone number:
        <input
          class="signupField"
          type="text"
          id="phone"
          v-model="phone"
          placeholder="Enter phone number"
        />
      </form>
      <form>
        Manager ID (all numbers):
        <input
          class="signupField"
          type="text"
          id="managerID"
          v-model="managerID"
          placeholder="Enter manager ID"
        />
      </form>
      <form>
        Username:
        <input
          class="signupField"
          type="text"
          id="username"
          v-model="username"
          placeholder="Enter username"
        />
      </form>
      <!-- <form>
        Password:
        <input
          class="signupField"
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
          @click="signup(first,last,dob,email,phone,managerID,login,request,review,commission,classroom,system)"
          class="btn btn-primary btn-lg signupField button"
          v-bind:class="buttonClass"
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

var config = require("../../config");

var frontendUrl = "http://" + config.build.host + ":" + config.build.port;
var backendUrl ="http://localhost:8080/";
  // "http://" + config.build.backendHost + ":" + config.build.backendPort;

// axios config
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

// function LoginDto (username, password) {
//   this.username = username
//   this.password = password
// }

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
      username:"",
      login: "",
      request: null,
      review: null,
      commission: null,
      classroom: null,
      system: "1"

      // first,last,dob,email,phone,managerID,login,request,review,commission,classroom,system
    };
  },
  created: function() {
    // fetch the user's selected UI mode from brower local storage
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
    // login: function(username,pw) {
    //     this.username = username;
    //     this.pw = pw;
    // },
    // var : login = {
    //     "username" : "username",
    //     "password" : "pw",
    // },
    // send get request to fetch manager
    getLogin: function(username, password) {
      AXIOS.get('/login/list/'+username)
        .then(response => {
          this.login = response.data;
        })
        .catch(e => {
          console.log(e.message);
          document.getElementById("title1").innerText =
            "Account does not exist, please try again";
        });
    },
    signup: function(
      first,
      last,
      dob,
      email,
      phone,
      managerID,
      login = this.login,
      request,
      review,
      commission,
      classroom,
      system
    ) {
      
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
          "&login=" +
          username +
          "&request=" +
          request +
          "&review=" +
          review +
          "&commission=" +
          commission +
          "&classroom=" +
          classroom +
          "&system=" +
          system
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
    // goToSignupPage: function() {
    //     Router.push({
    //         path: "/signup",
    //         name: "signup"
    //     });
    // },
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
#signup {
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
/* #myButton{
    justify-content: center;
    align-self: center;
} */
.signupField {
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
  /* align-self: auto; */
}
#btn1 {
  align-self: center;
}
</style>
