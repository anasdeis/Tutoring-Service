<!--- this component acts as a page to log in --->
<template>
  <div id="login" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <span id="title" v-bind:style="{ color: textColor}">Manager Login</span>
    <div>
      <span id="title1"></span>
    </div>
    <b-container fluid>
      <input
        class="loginField"
        type="text"
        id="username"
        v-model="username"
        placeholder="Enter username"
      />
      <input
        class="loginField"
        type="password"
        id="password"
        v-model="password"
        placeholder="Enter password"
      />
      <button
        type="button"
        v-on:click="getLogin(username,password);"
        class="btn btn-primary btn-lg loginField button"
        v-b-tooltip.hover
        title="Login"
      >Login</button>
      <button
        type="button"
        @:click="createLogin(username,password)" v-on:click="goToSignupPage()"
        class="btn btn-primary btn-lg loginField button"
        v-b-tooltip.hover
        title="Create an account"
      >Sign up</button>
    </b-container>
    <p>Do not have an account yet? Fill in the information and Sign Up Now!</p>
  </div>
</template>

<script>
import axios from "axios";
import Router from "../router";

var config = require("../../config");

// axios config
var frontendUrl = "http://" + config.build.host + ":" + config.build.port;
var backendUrl =
  // "http://" + config.build.backendHost + ":" + config.build.backendPort;
  "http://localhost:8080/";

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  data() {
    return {
      // login: {
      //   type: Object
      // },
      bgColor: "",
      textColor: "",
      error: "",
      username:"",
      password:""
    };
  },
  created: function() {
    var darkModeOn = localStorage.getItem("DarkModeOn");
    if (darkModeOn === "true") {
      this.bgColor = "rgb(53,58,62)";
      this.textColor = "white";
      // this.buttonClass="btn btn-dark btn-lg loginField";
    } else {
      this.bgColor = "rgb(250,250,250)";
      this.textColor = "black";
      // this.buttonClass="btn btn-white btn-lg loginField";
    }
  },
  methods: {
    getLogin: function(username, password) {
      AXIOS.get('/login/list/'+username)
        .then(response => {
          this.login = response.data;
          this.goToHomePage();
          // if (this.login.password == password) {
          //   this.goToHomePage();
          //   localStorage.setItem("isLoggedIn", "true");
          //   this.$loggedInEvent.$emit("setLoggedInState", true);
          // } else {
          //   document.getElementById("title1").innerText =
          //     "Password is not correct, please try again";
          // }
        })
        .catch(e => {
          console.log(e.message);
          document.getElementById("title1").innerText =
            "Account does not exist, please try again";
        });
    },
    createLogin: function(username,password) {
       AXIOS.post('/login/' + username + '?password=' + password).then(response => {
        this.login = response.data;
      })
    },
    goToHomePage: function() {
      Router.push({
        path: "/home",
        name: "home"
      });
    },
    goToSignupPage: function() {
      Router.push({
        path: "/signup",
        name: "signup"
      });
    },
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
  text-align: left;
  color: white;
  font-size: 30px;
  padding-left: 15px;
}
#title1 {
  text-align: left;
  color: red;
  font-size: 15px;
  padding-left: 15px;
}
p {
  text-align: center;
}
#send {
  align-content: right;
}
#name {
  text-align: left;
  color: white;
  font-size: 25px;
  padding-left: 15px;
}
#login {
  width: 30%;
  max-height: 480px;
  min-width: 550px;
  margin: auto;
  margin-top: 15px;
  padding: 15px;
  text-align: left;
}
.loginField {
  width: 98%;
  border-radius: 5px;
  border: 1px;
  padding: 2%;
  margin: auto;
  margin-top: 15px;
}
.button {
  color: white;
}
</style>