<!--- this component acts as a page to log in --->
<template>
  <div id="login" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <span id="title" v-bind:style="{ color: textColor}">Manager Login</span>
    <div>
      <span id="title1"></span>
    </div>
    <b-container fluid v-bind:style="{ color: textColor}">
      <input
        class="loginField"
        type="text"
        id="username"
        v-model="username"
        placeholder="Enter username"
        @keyup.enter="Login()"
      />
      <input
        class="loginField"
        type="password"
        id="password"
        v-model="password"
        placeholder="Enter password"
        @keyup.enter="Login()"
      />
      <button
        type="button"
        v-on:click="Login()"
        class="btn btn-primary btn-lg loginField button"
        v-b-tooltip.hover
        title="Login"
      >Login</button>
      <button
        type="button"
        v-on:click="SignUp()"
        class="btn btn-primary btn-lg loginField button"
        v-b-tooltip.hover
        title="Create an account"
      >Sign up</button>
    </b-container>
    <p
      v-bind:style="{ color: textColor}"
    >Do not have an account yet? Fill in the information and Sign Up Now!</p>
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
      bgColor: "",
      textColor: "",
      error: "",
      username: "",
      password: "",
      loggedIn: true,
      login: []
    };
  },
  created: function() {
    this.updateLogin()
    var darkModeOn = localStorage.getItem("DarkModeOn");
    if (darkModeOn === "true") {
      this.bgColor = "rgb(53,58,62)";
      this.textColor = "white";
    } else {
      this.bgColor = "rgb(250,250,250)";
      this.textColor = "black";
    }
  },
  methods: {
    updateLogin(){
      AXIOS.get("login/list/")
        .then(response => {
          this.login = response.data;
        })
        .catch(e => {
          console.log(e.message);
        });
    },
    Login: function() {
      if(this.username != "" && this.password != "") { 
        this.updateLogin()
        var isValid = false
        for(var i=0; i < this.login.length; i++){
          if(this.login[i].userName == this.username && this.login[i].password == this.password){
            isValid = true
            break;
          }
        }
        if(isValid == false){
          alert("The username and/or password is incorrect!")
        } else{
          this.$events.fire("loggedIn-set", this.username);
          this.goToHomePage();
        }
      }
      else{
        alert("ERROR: A username and password must be present to login!")
      }
    },
    SignUp: function() {
      AXIOS.post("login/" + this.username + "?password=" + this.password).then(
        response => {
          this.login.push(response.data);
          this.error = ""
        })
          .catch(e => {
          var errorMsg =
            e.response.status +
            " " +
            e.response.data.error +
            ": " +
            e.response.data.message;
          console.log(errorMsg);
          this.error = errorMsg;
        });
        if (this.error != "") {
          alert(this.error);
      }
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
      } else {
        this.bgColor = "rgb(250,250,250)";
        this.textColor = "black";
      }
    }
  },
  mounted() {
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