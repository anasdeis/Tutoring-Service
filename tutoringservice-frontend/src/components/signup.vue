<!--- This component acts as a page to signup --->
<template>
   <div id="signup" class="card" v-bind:style="{ backgroundColor : bgColor }">
        <span id = "title" v-bind:style="{color : textColor}">Create an account</span>
        <div>
            <span id = "title1"></span>
        </div>

        <b-container fluid>
            <input class="signupField" type="text" id="first" v-model="first" placeholder="Enter first name">
            <input class="signupField" type="text" id="last" v-model="last" placeholder="Enter last name">
            <input class="signupField" type="text" id="email" v-model="email" placeholder="Enter email">
            <input class="signupField" type="text" id="phone" v-model="phone" placeholder="Enter phone number">
            <input class="signupField" type="text" id="managerID" v-model="managerID" placeholder="Enter manager ID (all number)">

            <input class="signupField" type="date" id="dob" v-model="PersonDob" placeholder="YYYY-MM-DD">

            <button type="button" v-on:click="Create(first,last,email,phone,managerID)" v-bind:class="buttonClass" v-b-tooltip.hover title="Click to create an account!">Create</button>
        </b-container>
    </div>    
</template>

<script>
import axios from "axios";
import Router from "../router";

var config = require("../../config");

var frontendUrl = "http://" + config.build.host + ":" + config.build.port;
var backendUrl = "http://" + config.build.backendHost + ":" + config.build.backendPort;

// axios config
var AXIOS = axios.create({
    baseURL: backendUrl, 
    headers: {"Access-Control-Allow-Origin": frontendUrl}
});

export default {
    data() {
        return {
            manager: {
                type: Object
            },
            bgColor:"", 
            textColor:"",
            buttonClass:"",
            error:"",
            first:"",
            last:"",
            email:"",
            phone:"",
            managerID:""
        };
    },
    created: function() {
        // fetch the user's selected UI mode from brower local storage
        var darkModeOn = localStorage.getItem("DarkModeOn");
        if(darkModeOn == "true") {
            this.bgColor="rgb(53,58,62)";
            this.textColor="white";
            this.buttonClass="btn btn-dark btn-lg signupField";
        } else {
            this.bgColor="rgb(250,250,250)";
            this.textColor="black";
            // this.bgColor = "rgb(248, 249, 251)";
            this.buttonClass="btn btn-white btn-lg signupField";
        }
    },
    methods: {
        // send get request to fetch manager
        create: function(first,last,email,phone,managerID) {
            AXIOS.post(
               // '/manager/create?email=' + 
            )
            .then(response => {
                this.manager=response.data;
                this.goToHomePage();
            })
            .catch(e=> {
                console.log(e.message);
                document.getElementById("title1").innerText = "Please enter missing information!";
            });
        },
        goToHomePage: function() {
            Router.push({
                path: "/home",
                name: "Home"
            });
        },
        goToSignupPage: function() {
            Router.push({
                path: "/signup",
                name: "SignupPage"
            });
        },
        setDarkMode: function() {
            var darkModeOn = localStorage.getItem("DarkModeOn");
            if (darkModeOn === "true") {
                this.bgColor = "rgb(53, 58, 62)";
                this.textColor = "white";
                this.buttonClass = "btn btn-dark btn-lg loginField";
            } else {
                this.bgColor = "rgb(250,250,250)";
                this.textColor = "black";
                this.buttonClass = "btn btn-white btn-lg loginField";
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
  max-height: 480px;
  min-width: 550px;
  margin: auto;
  margin-top: 15px;
  padding: 15px;
  text-align: left;
}
.loginField {
  width: 98%;
  border-radius: 4px;
  border: 0px;
  padding: 2%;
  margin: auto;
  margin-top: 15px;
}
</style>
