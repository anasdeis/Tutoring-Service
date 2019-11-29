<template>
  <nav v-bind:class="navBar" id="container">
    <a
      class="navbar-brand mb-0 h1"
      v-b-tooltip.hover
      title="Get back to home page"
      v-bind:style="{color: titleColor}"
      @click="goToHomePage()"
    >
      Hi  <b>{{this.user}}</b>! Welcome to our company,
      <b>Tutor4All</b>. We are open 9-9, 7 days a week.
    </a>
    <span style="float:left;">
      <button type="button"  v-bind:class="buttonClass" @click="logOut" v-show="isLoggedIn">Logout</button>
      <button type="button"  v-bind:class="buttonClass" @click="toggleDarkMode">{{ buttonText }}</button>
      <button
        type="button"
        @click="goToHomePage()"
        class="btn btn-primary btn-lg loginField button"
        v-b-tooltip.hover
        title="Go to home page"
      >Home</button>
    </span>
  </nav>
</template>

<script>
import Router from "../router";
import Vue from "vue";
import VueEvents from "vue-events";
Vue.use(VueEvents);

export default {
  name: "LogoBar",
  data() {
    return {
      navBar: "",
      buttonClass: "",
      buttonText: "",
      titleColor: "",
      user: "",
      isLoggedIn: false
    };
  },
  created() {
    var darkModeOn = localStorage.getItem("DarkModeOn");
    if (darkModeOn === "true") {
      this.navBar = "navbar navvar-dark bg-dark";
      this.buttonClass = "btn btn-lg btn-dark loginField";
      this.buttonText = "Night";
      this.titleColor = "white";
    } else {
      this.navBar = "navbar navbar-dark bg-light";
      this.buttonClass = "btn btn-lg btn-light loginField";
      this.buttonText = "Day";
      this.titleColor = "black";
    }
  },
  methods: {
    goToHomePage: function() {
      if (this.isLoggedIn === true) {
        Router.push({
          path: "/home",
          name: "home"
        });
      } else {
        Router.push({
          path: "/login",
          name: "login"
        });
      }
    },
    logOut: function() {
      this.isLoggedIn = false;
      this.user = ""
      Router.push({
        path: "/login",
        name: "login"
      });
      alert("You logged out!")
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
      if (darkModeOn === "true") darkModeOn = true;
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
    updateLoggedInState: function(user) {
      this.isLoggedIn = true;
      this.user = user;
    }
  },
  mounted() {
    this.$events.$on("loggedIn-set", eventData => this.updateLoggedInState(eventData));
    
  }
};
</script>

<style scoped>
#container {
  margin-bottom: 50px;
}
.loginField{
  width: 100px;
  margin-bottom: 25px !important;
}
</style>>

