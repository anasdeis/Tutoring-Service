<template>
    <nav v-bind:class="navBar" id="container">
        <a class="navbar-brand mb-0 h1" v-b-tooltip.hover title="Get back to home page" v-bind:style="{Color: titleColor}"
        @click="goToHomePage()">Welcome to our company, <b>Tutor4All</b>. We open from 9-9, 7 days a week.</a>
        <span style="float:left;">
            <button type = "button" v-bind:class="buttonClass" @click="logOut" v-show="isLoggedIn">Log Out</button>
            <button type = "button" v-bind:class="buttonClass" @click="toggleDarkMode">{{ buttonText}}</button>
        </span>
    </nav>
</template>

<script>
import Router from "../router";

export default {
    data() {
        return {
            navBar:"",
            buttonClass:"",
            buttonText:"",
            titleColor:"",
            isLoggedIn: false
        };
    },
    created() {
        var darkModeOn = localStorage.getItem("DarkModeOn");
        if(darkModeOn === "true") {
            this.navBar = "navbar navvar-dark bg-dark";
            this.buttonClass = "btn btn-dark";
            this.buttonText = "Night";
            this.titleColor = "white";
        } else {
            this.navBar = "navbar navbar-dark bg-light";
            this.buttonClass = "btn btn-light";
            this.buttonText = "Day";
            this.titleColor = "black";
        }
        var isLoggedIn = localStorage.getItem("isLoggedIn");
        if (isLoggedIn === "true")
            this.isLoggedIn = true;
        else
            this.isLoggedIn = false;
    },
    methods:{
        goToHomePage: function() {
            var isLoggedIn = localStorage.getItem("isLoggedIn");
            if(isLoggedIn === "true") {
                Router.push({
                    path: "/home",
                    name: "Home"
                });
            } else {
                Router.push({
                    path:"/login",
                    name:"LoginPage"
                });
            }
        },
        logOut: function() {
            localStorage.setItem("isLoggedIn", "false");
            this.isLoggedIn = false;
            Router.push({
                path:"/login",
                name: "LoginPage"
            });
        },
        toggleDarkMode: function() {
            var darkModeOn = localStorage.getItem("DarkModeOn");

            if (darkModeOn === "true") {
                localStorage.setItem("DarkModeOn", "false");
                darkModeOn = "false";
            } else {
                localStorage.setItem("DarkModeOn", "true");
                darkModeOn = "true";
            }

            var darModeOnBool = false;
            if(darkModeOn === "true")
                darkModeOn = true;
            this.$emit("setDarkModeState", darkModeOnBool);

            if (darkModeOnBool == true) {
                this.navBar = "navbar navbar-dark bg-dark";
                this.buttonClass = "btn btn-dark";
                this.buttonText = "Night";
                this.titleColor = "white";
            } else {
                this.navBar = "navbar navbar-dark bg-light";
                this.buttonClass = "btn btn-light";
                this.buttonText = "Day";
                this.titleColor = "black";
            }
        },
        updateLoggedInState: function(state) {
            this.isLoggedIn = state;
        }
    },
    mounted() {
        this.$loggedInEvent.$on("setLoggedInState", this.updateLoggedInState);
    }    
};
</script>

<style scoped>
#container {
    margin-bottom: 50px;
}
</style>>

</style>