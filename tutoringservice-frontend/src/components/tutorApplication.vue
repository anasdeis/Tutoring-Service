<!--- This component acts as a page for tutor application, including accept/decline tutor --->
<template>
  <div id="tutorApplication" class="card" v-bind:style="{ backgroundColor : bgColor}">
    <span id="title" v-bind:style="{color : textColor}"></span>
    <div>
      <span id="title1" style="color:red"></span>
    </div>
    <b-container fluid>
      <b-row>
        <b-col id="tutprApplicationTable">
          <p>View all the tutor applications</p>

          <b-table striped hover :items="items"></b-table>
        </b-col>

        <b-col id="detailofTApp">
          <form>
            Enter tutor application ID:
            <input
              class="tAppField"
              text="number"
              id="tAppID"
              v-model="tAppID"
              placeholder="Enter tutor application ID"
            />
          </form>
          <button
            type="button"
            @click="getTutorApplication(tAppID)"
            class="btn btn-primary btn-lg viewTApp button"
            v-b-tooltip.hover
            title="Dispaly selected tutor application"
          >View detail</button>

          <p>Here is the detail of the tutor application you select</p>
          <div class="detailedApplication">
            <form action="#">
              <div>
                <label>Tutor ID:</label>
                <a id="tutorID"></a>
              </div>
              <div>
                <label>First Name:</label>
                <a id="firstName"></a>
              </div>
              <div>
                <label>Last Name:</label>
                <a id="lastName"></a>
              </div>
              <div>
                <label>Is Tutor Accepted?</label>
                <a id="isAccepted"></a>
              </div>
              <div>
                <br />
                <label>Subjects</label>
                <ul>
                  <li>
                    <label>Course Name:</label>
                    <a id="Name1"></a>
                    <label>Course ID:</label>
                    <a id="courseID1"></a>
                  </li>
                  <li>
                    <label>Course Name:</label>
                    <a id="Name2"></a>
                    <label>Course ID:</label>
                    <a id="courseID2"></a>
                  </li>
                </ul>
              </div>
            </form>
          </div>
          <!-- <table id="tAppDetail">
            <tr>
              <th>Tutor Id</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email address</th>
              <th>Phone number</th>
              <th>Tutor Application ID</th>
            </tr>
            <tr>
              <td>{{firstName}}</td>
              <td>{{isAccepted: false, lastName}}</td>
              <td>{{email}}</td>
              <td>{{phone}}</td>
              <td>{{firstName: "Tutor1", isAccepted: false, lastName: "Last", subjects: [{Name: "Software Engineering: Sleeping is the Key,, courseID: "ECSE 321 "tu{Name: "DPM: Just Kill Met, courseID: "ECSE 211orApplicationID}}</td>
            </tr>
          </table>-->
          <b-col id="detailofTApp2">{{detailofTApp}}</b-col>
          <button
            type="button"
            @click="updateTutorApplicationisAccepted(tAppID,isAccept)"
            class="btn btn-primary btn-lg viewTApp button"
            v-b-tooltip.hover
            title="Approve"
          >Approve</button>
          <button
            type="button"
            @click="updateTutorApplicationisDecline(tAppID,isAccept)"
            class="btn btn-primary btn-lg viewTApp button"
            v-b-tooltip.hover
            title="Decline"
          >Decline</button>
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
var backendUrl = "http://localhost:8080/";
  // "http://" + config.build.backendHost + ":" + config.build.backendPort;

// axios config
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

var tAppTable = {
  // GET YOUR TABLE FROM THE BACKEND- all tutor applications
};

var detailofTApp = {
  // GET YOUR SELECTED TUTOR APPLICATION OBJECT IN THE BACKEND AND PUT IT HERE
};
// detailofTApp.displayObject("detailofTApp", detailofTApp); // we might need this line to display
export default {
  data() {
    return {
      tutor: {
        type: Object
      },
      bgColor: "",
      textColor: "",
      tAppID: "",
      isAccept: "",
      detailedTutorApplications: [
        {
          tutorID: 1001,
          firstName: "Tutor1",
          isAccepted: false,
          lastName: "Last",
          subjects: [
            {
              Name: "Software Engineering: Sleeping is the Key",
              courseID: "ECSE 321"
            },
            { Name: "DPM: Just Kill Me", courseID: "ECSE 211" }
          ],
          tutorApplicationID: 1
        },
        {
          tutorID: 1002,
          firstName: "Tutor2",
          isAccepted: false,
          lastName: "Last",
          subjects: [
            {
              Name: "Software Engineering: Sleeping is the Key",
              courseID: "ECSE 321"
            },
            { Name: "DPM: Just Kill Me", courseID: "ECSE 211" }
          ],
          tutorApplicationID: 2
        },
        {
          tutorID: 1003,
          firstName: "Tutor3",
          isAccepted: false,
          lastName: "Last",
          subjects: [
            {
              Name: "Software Engineering: Sleeping is the Key",
              courseID: "ECSE 321"
            },
            { Name: "DPM: Just Kill Me", courseID: "ECSE 211" }
          ],
          tutorApplicationID: 3
        },
        {
          tutorID: 1004,
          firstName: "Tutor4",
          isAccepted: false,
          lastName: "Last",
          subjects: [
            {
              Name: "Software Engineering: Sleeping is the Key",
              courseID: "ECSE 321"
            },
            { Name: "DPM: Just Kill Me", courseID: "ECSE 211" }
          ],
          tutorApplicationID: 4
        },
        {
          tutorID: 1005,
          firstName: "Tutor5",
          isAccepted: false,
          lastName: "Last",
          subjects: [
            {
              Name: "Software Engineering: Sleeping is the Key",
              courseID: "ECSE 321"
            },
            { Name: "DPM: Just Kill Me", courseID: "ECSE 211" }
          ],
          tutorApplicationID: 5
        },
        {
          tutorID: 1006,
          firstName: "Tutor6",
          isAccepted: false,
          lastName: "Last",
          subjects: [
            {
              Name: "Software Engineering: Sleeping is the Key",
              courseID: "ECSE 321"
            },
            { Name: "DPM: Just Kill Me", courseID: "ECSE 211" }
          ],
          tutorApplicationID: 6
        },
        {
          tutorID: 1007,
          firstName: "Tutor7",
          isAccepted: false,
          lastName: "Last",
          subjects: [
            {
              Name: "Software Engineering: Sleeping is the Key",
              courseID: "ECSE 321"
            },
            { Name: "DPM: Just Kill Me", courseID: "ECSE 211" }
          ],
          tutorApplicationID: 7
        },
        {
          tutorID: 1008,
          firstName: "Tutor8",
          isAccepted: false,
          lastName: "Last",
          subjects: [
            {
              Name: "Software Engineering: Sleeping is the Key",
              courseID: "ECSE 321"
            },
            { Name: "DPM: Just Kill Me", courseID: "ECSE 211" }
          ],
          tutorApplicationID: 8
        },
        {
          tutorID: 1009,
          firstName: "Tutor9",
          isAccepted: false,
          lastName: "Last",
          subjects: [
            {
              Name: "Software Engineering: Sleeping is the Key",
              courseID: "ECSE 321"
            },
            { Name: "DPM: Just Kill Me", courseID: "ECSE 211" }
          ],
          tutorApplicationID: 9
        },
        {
          tutorID: 1010,
          firstName: "Tutor10",
          isAccepted: false,
          lastName: "Last",
          subjects: [
            {
              Name: "Software Engineering: Sleeping is the Key",
              courseID: "ECSE 321"
            },
            { Name: "DPM: Just Kill Me", courseID: "ECSE 211" }
          ],
          tutorApplicationID: 10
        },
        {
          tutorID: 1011,
          firstName: "Tutor11",
          isAccepted: false,
          lastName: "Last",
          subjects: [
            {
              Name: "Software Engineering: Sleeping is the Key",
              courseID: "ECSE 321"
            },
            { Name: "DPM: Just Kill Me", courseID: "ECSE 211" }
          ],
          tutorApplicationID: 11
        },
        {
          tutorID: 1012,
          firstName: "Tutor12",
          isAccepted: false,
          lastName: "Last",
          subjects: [
            {
              Name: "Software Engineering: Sleeping is the Key",
              courseID: "ECSE 321"
            },
            { Name: "DPM: Just Kill Me", courseID: "ECSE 211" }
          ],
          tutorApplicationID: 12
        },
        {
          tutorID: 1013,
          firstName: "Tutor13",
          isAccepted: false,
          lastName: "Last",
          subjects: [
            {
              Name: "Software Engineering: Sleeping is the Key",
              courseID: "ECSE 321"
            },
            { Name: "DPM: Just Kill Me", courseID: "ECSE 211" }
          ],
          tutorApplicationID: 13
        },
        {
          tutorID: 1014,
          firstName: "Tutor14",
          isAccepted: false,
          lastName: "Last",
          subjects: [
            {
              Name: "Software Engineering: Sleeping is the Key",
              courseID: "ECSE 321"
            },
            { Name: "DPM: Just Kill Me", courseID: "ECSE 211" }
          ],
          tutorApplicationID: 14
        },
        {
          tutorID: 1015,
          firstName: "Tutor15",
          isAccepted: false,
          lastName: "Last",
          subjects: [
            {
              Name: "Software Engineering: Sleeping is the Key",
              courseID: "ECSE 321"
            },
            { Name: "DPM: Just Kill Me", courseID: "ECSE 211" }
          ],
          tutorApplicationID: 15
        }
      ],

      items: [
        { tutorID: 1001, tutorApplicationID: 1 },
        { tutorID: 1002, tutorApplicationID: 2 },
        { tutorID: 1003, tutorApplicationID: 3 },
        { tutorID: 1004, tutorApplicationID: 4 },
        { tutorID: 1005, tutorApplicationID: 5 },
        { tutorID: 1006, tutorApplicationID: 6 },
        { tutorID: 1007, tutorApplicationID: 7 },
        { tutorID: 1008, tutorApplicationID: 8 },
        { tutorID: 1009, tutorApplicationID: 9 },
        { tutorID: 1010, tutorApplicationID: 10 },
        { tutorID: 1011, tutorApplicationID: 11 },
        { tutorID: 1012, tutorApplicationID: 12 },
        { tutorID: 1013, tutorApplicationID: 13 },
        { tutorID: 1014, tutorApplicationID: 14 },
        { tutorID: 1015, tutorApplicationID: 15 }
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
    getTutorApplication: function(tAppID) {
      var message,
        x,
        found = false;
      message = document.getElementById("title1");
      message.innerHTML = "";
      x = document.getElementById("tAppID").value;

      if (x == "") {
        message.innerHTML = "Error :  " + "Input fields cannot be empty";
      }
      // try {
      //     if (x == "") throw "Input fields cannot be empty";
      // }
      // catch(err) {
      //     message.innerHTML = "Error :  " + err;
      // }
      else {
        let arrayLength = this.detailedTutorApplications.length;
        for (let i = 0; i < arrayLength; i++) {
          console.log(this.detailedTutorApplications[i]);
          if (this.detailedTutorApplications[i].tutorApplicationID == tAppID) {
            found = true;
            document.getElementById(
              "tutorID"
            ).innerHTML = this.detailedTutorApplications[i].tutorID;
            document.getElementById(
              "firstName"
            ).innerHTML = this.detailedTutorApplications[i].firstName;
            document.getElementById(
              "lastName"
            ).innerHTML = this.detailedTutorApplications[i].lastName;
            document.getElementById(
              "isAccepted"
            ).innerHTML = this.detailedTutorApplications[i].isAccepted;
            document.getElementById(
              "Name1"
            ).innerHTML = this.detailedTutorApplications[i].subjects[0].Name;
            document.getElementById(
              "courseID1"
            ).innerHTML = this.detailedTutorApplications[
              i
            ].subjects[0].courseID;
            document.getElementById(
              "Name2"
            ).innerHTML = this.detailedTutorApplications[i].subjects[1].Name;
            document.getElementById(
              "courseID2"
            ).innerHTML = this.detailedTutorApplications[
              i
            ].subjects[1].courseID;
            break;
          }
        }
        if (found == false) {
          message.innerHTML =
            "Error :  " + "Cannot find this Tutor Application";
        }
      }
    },
    updateTutorApplicationisAccepted: function(tAppID, isAccept) {
      AXIOS.patch(
        '/tutorApplication/update/' + tAppID + '?isAccepted' + isAccept
      )
        this.tutorApplucation = response.data;
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
  margin-top: 10px;
}
li {
  font-size: 20px;
}
#myButton {
  color: royalblue;
  border: 0px;
  border-radius: 4px;
  padding: 2px;
  margin: auto;
}
b-container {
  height: auto;
  width: auto;
}
#tutorApplication {
  align-content: center;
  height: auto;
}
#tAppTable {
  margin-left: auto;
  margin-right: auto;
  border: 4px;
  border: 1px solid black;
}
#tAppDetail {
  margin-left: auto;
  margin-right: auto;
  border: 4px;
  border: 1px solid black;
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
  text-align: center;
  background: #44475c;
  color: #fff;
  /* padding: 8px; */
  /* min-width: 5px; */
  table-layout: auto;
  height: auto;
  width: 50%;
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
.tAppField {
  margin-top: 40px;
}
</style>
