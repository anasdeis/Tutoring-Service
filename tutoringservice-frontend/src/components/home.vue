<!--- this component is the home page --->
<template>
    <div>
        <div id = "home-container" class="card" v-bind:style="{ backgroundColor: bgColor}">
            <span id = "title" v-bind:style="{color: textColor}"> Home Page </span>
            <div><span id = "title1"></span></div>
        </div>
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
    created: function() {
        var isLoggedIn = localStorage.getItem("isLoggedIn");
        if(isLoggedIn === "false") {
            Router.push({
                path:"/login",
                name: "LoginPage"
            });
        }

        var darkModeOn = localStorage.getItem("DarkModeOn");
        if(darkModeOn == "true") {
            this.bgColor="rgb(53,58,62)";
            this.textColor="white";
        } else {
            this.bgColor="rgb(250,250,250)";
            this.textColor="black";
            // this.bgColor = "rgb(248, 249, 251)";
        }

        // since we will only have one manager, I don't think the following 4 blocks of code are necessary
        // get all managers from backend
        AXIOS.get('/managers').then(response => {
            this.managers = response.data;
            this.managersLoaded = true;
        })
        .catch(e => {
            this.error = e;
        });

        // get all managers from manager database
        AXIOS_Manager.get("/getAllManagers").then(response => {
            this.externalManagers = response.data;
            this.externalManagersLoaded = true;
        })
        .catch(e => {
            this.error = e;
        });

        // get all logins from backend
        AXIOS.get('/logins').then(response => {
            this.logins = response.data;
            this.loginsLoaded = true;
        })
        .catch(e => {
            this.error = e;
        });

        // get all logins from database
        AXIOS_Logins.get("getAllLogins").then(response => {
            this.externalLogins = response.data;
            this.externalLoginsLoaded = true;
        })
        .catch(e => {
            this.error = e;
        });
    },
    methods: { setDarkMode: function(darkModeOn) {
            if (darkModeOn) {
                this.bgColor = "rgb(53, 58, 62)";
                this.textColor = "white";
            } else {
                this.bgColor = "rgb(250,250,250)";
                this.textColor = "black";
            }
        }
    },
   
    
    mounted() {
        // Listens to the setDarkModeState event emitted from the LogoBar component
        this.$root.$on("setDarkModeState", this.setDarkMode);
    }
};
</script>