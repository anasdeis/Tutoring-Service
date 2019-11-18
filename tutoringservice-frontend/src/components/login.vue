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
        id="userName"
        v-model="userName"
        placeholder="Enter userName"
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
        v-on:click="logIntoSystem(userName,password);"
        class="btn btn-primary btn-lg loginField button"
        v-b-tooltip.hover
        title="Login"
      >Login</button>
      <button
        type="button"
        v-on:click="createLogin(userName,password)"
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
  "http://" + config.build.backendHost + ":" + config.build.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

// export default {
//   data() {
//     return {
//       // login: {
//       //   type: Object
//       // },
//       bgColor: "",
//       textColor: "",
//       error: "",
//       // userName: this.$cookie.get("userName") || ' ',
//       // password: this.$cookie.get("password") || ' ',
//       userName:"",
//       password:""
//     };
//   },
//   created: function() {
//     var darkModeOn = localStorage.getItem("DarkModeOn");
//     if (darkModeOn === "true") {
//       this.bgColor = "rgb(53,58,62)";
//       this.textColor = "white";
//       // this.buttonClass="btn btn-dark btn-lg loginField";
//     } else {
//       this.bgColor = "rgb(250,250,250)";
//       this.textColor = "black";
//       // this.buttonClass="btn btn-white btn-lg loginField";
//     }
//   },
//   methods: {
//     login: function(userName, password) {
//       AXIOS.get('/login/' + userName)
//         .then(response => {
//           this.login = response.data;
//           if (this.login.password === password) {
//             this.goToHomePage();
//             localStorage.setItem("isLoggedIn", "true");
//             this.$loggedInEvent.$emit("setLoggedInState", true);
//           } else {
//             document.getElementById("title1").innerText =
//               "Password is not correct, please try again";
//           }
//         })
//         .catch(e => {
//           console.log(e.message);
//           document.getElementById("title1").innerText =
//             "Account does not exist, please try again";
//         });
//     },
//     createLogin: function(userName,password) {
//        AXIOS.post('/login/=' + userName + '?password=' + password).then(response => {
//         this.login = response.data;
//         // this.$cookie.set('userName', userName);
//         // this.$cookie.set("passwor", password);
//       })
//     },
//     goToHomePage: function() {
//       Router.push({
//         path: "/home",
//         name: "home"
//       });
//     },
//     goToSignupPage: function() {
//       Router.push({
//         path: "/signup",
//         name: "signup"
//       });
//     },
//     setDarkMode: function() {
//       var darkModeOn = localStorage.getItem("DarkModeOn");
//       if (darkModeOn === "true") {
//         this.bgColor = "rgb(53, 58, 62)";
//         this.textColor = "white";
//         // this.buttonClass = "btn btn-dark btn-lg loginField";
//       } else {
//         this.bgColor = "rgb(250,250,250)";
//         this.textColor = "black";
//         // this.buttonClass = "btn btn-white btn-lg loginField";
//       }
//      }
//     },
//     mounted() {
//       // Listens to the setDarkModeState event emitted from the LogoBar component
//       this.$root.$on("setDarkModeState", this.setDarkMode);
//   }
// };

//----------------------------------------------------------------------------

function LoginDto(username, password) {
  // var user = {"username": username, "password": password}
  // return user;
  this.username = username;
  this.password = password;
}

export default {
  name: "login",
  data() {
    return {
      login: [],
      newUsername: "",
      newPassword: "",
      response: []
    };
  },
  //...


  created: function() {
    // Test data
    const p1 = new LoginDto("Omar", "abc");
    // const p2 = new LoginDto('Noor','cba')

    // Sample initial content

    this.login = [p1];
    //   this.login.push(p1);
    //   this.login.push(p2);
  }, //end of created

  methods: {
    createLogin: function(username, password) {
      // Create a new login and add it to the list of logins
      var message, x, y;

      message = document.getElementById("title1");
      message.innerHTML = "";
      x = document.getElementById("userName").value;
      y = document.getElementById("password").value;
      try {
        if (x == "") throw "Username or password empty";
      } catch (err) {
        message.innerHTML = "Error :  " + err;
      }

      try {
        if (y == "") throw "Username or password empty";
      } catch (err) {
        message.innerHTML = "Error :  " + err;
      }

      var p = new LoginDto(username, password);
      this.login.push(p);
      if (x != "" && y != "") {
        //if username is not empty
        this.goToSignupPage();
      }
    }, //end of createLogin

    //------------------------------------------------------------------

    logIntoSystem: function(username, password) {
      var message, x;

      message = document.getElementById("title1");
      message.innerHTML = "";
      x = document.getElementById("userName").value;
      try {
        if (x == "") throw "Username or password empty";
      } catch (err) {
        message.innerHTML = "Error :  " + err;
      }

 
        logIntoSystem: function (username,password) {
        var message, x, y;
    
        message = document.getElementById("title1");
        message.innerHTML = "";
        x = document.getElementById("userName").value;
        y = document.getElementById("password").value;
        try {
          if (x == "") throw "Username or password empty";
        }
        catch(err) {
          message.innerHTML = "Error :  " + err;
        }

         try {
          if (y == "") throw "Username or password empty";
        }
        catch(err) {
          message.innerHTML = "Error :  " + err;
        }
        
        var p = new LoginDto(username,password);
      // const p1 = new LoginDto('Omar', 'abc')
     //  this.login = [p1]
     // const p2 = new LoginDto('Noor','cba')
     // this.login = [p2] 
        
      
        if (this.login.includes(p) && x != "" && y != "") {
        this.goToSignupPage();
       
          }  
      
      }, //end of login
//----------------------------------------------------------------------- 
        goToSignupPage: function() {
        Router.push({
         path: "/signup",
          name: "signup"
       });
    
        },  //end of goToSignupPage

        goToHomePage: function() {
         Router.push({
           path: "/home1",
            name: "home1"
       });
       }, //end of gotoHomePage
    


    } //end of methods


  }   //end of export default






      if (this.login.includes(p1) && x != "") {
        this.goToSignupPage();
      }
    }, //end of login
    //-----------------------------------------------------------------------
    goToSignupPage: function() {
      Router.push({
        path: "/signup",
        name: "signup"
      });
    }, //end of goToSignupPage

    goToHomePage: function() {
      Router.push({
        path: "/home1",
        name: "home1"
      });
    } //end of gotoHomePage
  } //end of methods
}; //end of export default

//------------------------------------------------------------------------
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

