<!--- This component acts as a page for tutor, including add/fire tutor --->
<template>
  <div id="tutor" class="card" v-bind:style="{ backgroundColor : bgColor}">
    <span id="title" v-bind:style="{color : textColor}"></span>
    <div>
      <span id="title1"></span>
    </div>
    <!-- <b-container fluid>
      <b-col id="tutorlist">
        <p>View tutor list</p>
        <p>TODO get the list from backend and implement the two buttons</p>
        <select class="tutorField"></select>
      </b-col>
      <b-row id="myButton">
        <b-col>
          <button
            type="button"
            @click="addTutor()"
            class="btn btn-primary btn-lg tutorField button"
            v-b-tooltip.hove
            title="Add a tutor!"
          >Add Tutor</button>
        </b-col>
        <b-col>
          <button
            type="button"
            @click="removeTutor()"
            class="btn btn-primary btn-lg tutorField button"
            v-b-tooltip.hove
            title="Remove a tutor!"
          >Remove Tutor</button>
        </b-col>
      </b-row>
    </b-container>-->
    <b-container fluid>
      <b-row>
        <b-col id="tutorTable">
          <p>View all the tutors</p>
          <b-table striped hover :items="items"></b-table>
          <!--          <table id="tutorTable">-->
          <!--            <tr>-->
          <!--              <th>Tutor Id</th>-->
          <!--              <th>First Name</th>-->
          <!--              <th>Last Name</th>-->
          <!--              <th>Email</th>-->
          <!--            </tr>-->
          <!--            <tr>-->
          <!--              <td>{{tutorID}}</td>-->
          <!--              <td>{{ firstName}}</tutortd>-->
          <!--              <t@gamil.comd>{{lastName}}</Lasttd>-->
          <!--              <td>{{email}}</td>-->
          <!--            </tr>-->
          <!--          </table>-->
          
        </b-col>
        <b-col id="detailOfTutor">
          <form>
            Enter tutor ID:
            <input
              class="tutorField"
              text="number"
              id="tutorID"
              v-model="tutorID"
              placeholder="Enter tutor ID"
            />
          </form>
          <button
            type="button"
            @click="getTutor(tutorID)"
            class="btn btn-primary btn-lg viewTutor button"
            v-b-tooltip.hover
            title="Dispaly selected tutor"
          >View detail</button>
          <p>Here is the detail of the tutor you select</p>
          <button
            type="button"
            @click="deleteTutor(tutorID)"
            class="btn btn-primary btn-lg viewTutor button"
            v-b-tooltip.hover
            title="Remove"
          >Remove</button>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import axios from "axios";
import Router from "../router";
var config = require("../../config");
var frontendUrl = "http://" + config.build.host + ":" + config.build.port;
var backendUrl =
  "http://" + config.build.backendHost + ":" + config.build.backendPort;
// axios config
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});
var tutorTable = {
  // GET YOUR TABLE FROM THE BACKEND- all tutor applications
};
var detailOfTutor = {
  // GET YOUR SELECTED TUTOR APPLICATION OBJECT IN THE BACKEND AND PUT IT HERE
};
export default {
  data() {
    return {
      tutor: {
        type: Object
      },
      bgColor: "",
      textColor: "",
      tutorID: "",
      items: [
        {
          tutorID: 1001,
          firstName: "tutor1",
          lastName: "Last",
          email: "1001@gamil.com"
        },
        {
          tutorID: 1002,
          firstName: "tutor2",
          lastName: "Last",
          email: "1002@gamil.com"
        },
        {
          tutorID: 1003,
          firstName: "tutor3",
          lastName: "Last",
          email: "1003@gamil.com"
        },
        {
          tutorID: 1004,
          firstName: "tutor4",
          lastName: "Last",
          email: "1004@gamil.com"
        },
        {
          tutorID: 1005,
          firstName: "tutor5",
          lastName: "Last",
          email: "1005@gamil.com"
        },
        {
          tutorID: 1006,
          firstName: "tutor6",
          lastName: "Last",
          email: "1006@gamil.com"
        },
        {
          tutorID: 1007,
          firstName: "tutor7",
          lastName: "Last",
          email: "1007@gamil.com"
        },
        {
          tutorID: 1008,
          firstName: "tutor8",
          lastName: "Last",
          email: "1008@gamil.com"
        },
        {
          tutorID: 1009,
          firstName: "tutor9",
          lastName: "Last",
          email: "1009@gamil.com"
        },
        {
          tutorID: 1010,
          firstName: "tutor10",
          lastName: "Last",
          email: "1010@gamil.com"
        },
        {
          tutorID: 1011,
          firstName: "tutor11",
          lastName: "Last",
          email: "1011@gamil.com"
        },
        {
          tutorID: 1012,
          firstName: "tutor12",
          lastName: "Last",
          email: "1012@gamil.com"
        },
        {
          tutorID: 1013,
          firstName: "tutor13",
          lastName: "Last",
          email: "1013@gamil.com"
        },
        {
          tutorID: 1014,
          firstName: "tutor14",
          lastName: "Last",
          email: "1014@gamil.com"
        },
        {
          tutorID: 1015,
          firstName: "tutor15",
          lastName: "Last",
          email: "1015@gamil.com"
        },
        {
          tutorID: 1016,
          firstName: "tutor16",
          lastName: "Last",
          email: "1016@gamil.com"
        },
        {
          tutorID: 1017,
          firstName: "tutor17",
          lastName: "Last",
          email: "1017@gamil.com"
        }
      ]
    };
  },
  created: function() {
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
    },
    getTutor: function(tutorID) {
      AXIOS.get("/tutor/=" + tutorID).then(response => {
        this.getTutor = response.data;
      });
    },
    deleteTutor: function(tutorID) {
      AXIOS.delete("/tutor/delete/=" + tutorID);
    }
  },
  mounted() {
    // Listens to the setDarkModeState event emitted from the LogoBar component
    this.$root.$on("setDarkModeState", this.setDarkMode);
  }
};
</script>
<style>
p {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
}
#myButton {
  color: royalblue;
  border: 0px;
  border-radius: 4px;
  padding: 2px;
  margin: auto;
  margin-top: 5px;
}
b-container {
  height: auto;
}
#tutorTable {
  margin-left: auto;
  margin-right: auto;
}
table {
  font-family: "Open Sans", sans-serif;
  width: 300px;
  border-collapse: collapse;
  border: 3px solid #44475c;
  margin: 5px 5px 0 5px;
}
table th {
  text-transform: uppercase;
  text-align: left;
  background: #44475c;
  color: #fff;
  padding: 8px;
  min-width: 10px;
}
table td {
  text-align: left;
  padding: 8px;
  border-right: 2px solid #7d82a8;
}
table td:last-child {
  border-right: none;
}
table tbody tr:nth-child(2n) td {
  background: #d4d8f9;
}
.tutorField{
  margin-top: 20px;
}
</style>