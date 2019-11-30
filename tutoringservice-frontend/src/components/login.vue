<!--- this component acts as a page to log in --->
<template>
  <div id="login" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <span id="title" v-bind:style="{ color: textColor}">Manager Login</span>
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
        v-on:click="goToSignupPage()"
        class="btn btn-primary btn-lg loginField button"
        v-b-tooltip.hover
        title="Create an account"
      >Sign up</button>
    </b-container>
    <p
      v-bind:style="{ color: textColor}"
    >Do not have an account yet? Sign Up Now!</p>
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
      errorLogin: "",
      username: "",
      password: "",
      loggedIn: true,
      login: [],
      manager: [],
      student: [],
      tutor: []
    };
  },
  created: function() {
    this.updateLogin()
    this.updateManager()
    this.updateStudent()
    this.updateTutor()
    this.setDarkMode()
  },
  methods: {
    updateLogin(){
      AXIOS.get("login/list/")
        .then(response => {
          this.login = response.data;
        })
        .catch(e => {
          this.errorLogin = e.message
          console.log(this.errorLogin);
        });
    },
    updateManager(){
      AXIOS.get("manager/list/")
        .then(response => {
          this.manager = response.data;
        })
        .catch(e => {
          this.errorLogin = e.message
          console.log(this.errorLogin);
        });
    },
    updateStudent(){
      AXIOS.get("student/list/")
        .then(response => {
          this.student = response.data;
        })
        .catch(e => {
          this.errorLogin = e.message
          console.log(this.errorLogin);
        });
    },
    updateTutor(){
      AXIOS.get("tutor/list/")
        .then(response => {
          this.tutor = response.data;
        })
        .catch(e => {
          this.errorLogin = e.message
          console.log(this.errorLogin);
        });
    },
    Login: function() {
      if(this.username != "" && this.password != "") { 
        var isValid = false
        for(var i=0; i < this.manager.length; i++){
          if(this.manager[i].loginInfo.userName == this.username && this.manager[i].loginInfo.password == this.password){
            isValid = true
            break;
          } 
        }
        if(isValid == false){
          for(var j=0; j < this.tutor.length; j++){
            if(this.tutor[j].loginInfo.userName == this.username && this.tutor[j].loginInfo.password == this.password){
              alert("ERROR: username \"" + this.username + "\" is a tutor account, only a manager account can login!")
              return -1
            } 
          }
          for(var k=0; k < this.student.length; k++){
            if(this.student[k].loginInfo.userName == this.username && this.student[k].loginInfo.password == this.password){
              alert("ERROR: username \"" + this.username + "\" is a student account, only a manager account can login!")
              return -1
            } 
          }
        }
        if(isValid == false){
          alert("ERROR: The username and/or password is incorrect!")
        } else{
          this.$events.fire("loggedIn-set", this.username);
          this.goToHomePage();
        }
      }
      else{
        alert("ERROR: A username and password must be present to login!")
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