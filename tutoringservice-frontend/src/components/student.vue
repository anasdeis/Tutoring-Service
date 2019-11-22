<template>
  <div id="student" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <span id="title" v-bind:style="{color : textColor}"></span>
    <div>
      <span id="title1"></span>
    </div>
    <b-container fluid>
      <b-col id="studentlist">
        <p>View student list</p>

        <input name="input-student-id" type="text" v-model="studentId" placeholder="Student ID" />
        <input name="input-student-id" type="text" v-model="name" placeholder="Student name" />
        <button name="search-for-students" @click="createStudent(studentId, name)">Add student</button>
        <span v-if="errorStudent" style="color:red">Error: {{errorStudent}}</span>
        <hr />
        <select class="studentField" id="studentList" name="studentList">
          <option value="all">--Select Student--</option>
        </select>
        <input
          type="button"
          id="btnGenerate"
          value="Populate drop-down list"
          @click="PopulateDropDownList()"
        />

        <!-- </b-col> -->

        <b-row id="myButton">
          <b-col>
            <button
              type="button"
              @click="removeStudent()"
              class="btn btn-primary btn-lg studentField button"
              v-b-tooltip.hove
              title="Remove a student!"
            >Remove Student</button>
          </b-col>
        </b-row>
      </b-col>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";
import Router from "../router";
//<option v-for="student in students"  v-bind:key="student.personId">{{student}}</option>
var config = require("../../config");

var frontendUrl = "http://" + config.build.host + ":" + config.build.port;
var backendUrl = "http://localhost:8080/";
// "http://" + config.build.backendHost + ":" + config.build.backendPort;

// axios config
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

function StudentDto(studentId, name) {
  this.studentId = studentId;
  this.name = name;
  /*
  this.dateOfBirth = dateOfBirth
  this.email = email
  this.phoneNumber = phoneNumber
  this.numCoursesEnrolled = numCoursesEnrolled
  this.loginInfo = loginInfo
  this.tutoringSystem = tutoringSystem*/
}

export default {
  name: "students",
  data() {
    return {
      students: [],
      newStudent: '',
      studentId: '',
      /*firstName:'',
      lastName:'',
      dateOfBirth:'',
      email: '',
      phoneNumber: '', 
      numCoursesEnrolled: '',
      loginInfo: [{userName:'', password:''}],
      tutoringSystem: '',*/
      errorStudent: "",
      response: [],
      bgColor: "",
      textColor: ""
    };
  },

  created: function() {
    /* // Initializing students from backend
    AXIOS.get(`/student/list`).then(response => {
      // JSON responses are automatically parsed.
      this.students = response.data
    })
    .catch(e => {
      this.errorStudent = e;
    });*/

    // Test data
    const s1 = new StudentDto("5", "Anas Deis");
    const s2 = new StudentDto("6", "Justin Trudeau");
    // Sample initial content
    this.students = [s1, s2];

    var darkModeOn = localStorage.getItem("DarkModeOn");
    if (darkModeOn === "true") {
      this.bgColor = "rgb(53,58,62)";
      this.textColor = "white";
      this.buttonClass = "btn btn-dark btn-lg studentField";
    } else {
      this.bgColor = "rgb(250,250,250)";
      this.textColor = "black";
      // this.bgColor = "rgb(248, 249, 251)";
      this.buttonClass = "btn btn-white btn-lg studentField";
    }
  },
  methods: {
    createStudent: function(studentId, name) {
      /* AXIOS.post(`student/create/${studentId}?firstName=anas&lastName=deis&dob=1996-03-19&email=anas.deis@mail.mcgill.ca&phone=911&numCoursesEnrolled=0&userName=anasdeis&tutoringSystemID=1`)
      .then(response => {
        // JSON responses are automatically parsed.
        his.students.push(response.data)
        this.newStudent = ''
        this.errorPerson = ''
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
        this.errorPerson = errorMsg
      });*/

      // Create a new person and add it to the list of people
      if (studentId == undefined || studentId == ``) {
        errorStudent = "Plese enter a valid studentId!";
      }
      if (name == undefined || name == ``) {
        errorStudent = "Plese enter a valid name";
      } else {
        var s = new StudentDto(studentId, name);
        this.students.push(s);
        // Reset the name field for new people
        this.newStudent = "";
      }
    },

    removeStudent: function() {
      /*AXIOS.delete(`/student/delete/${studentID}`).then(response => {
        this.errorStudent = ''
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
        this.errorStudent = errorMsg
      });*/
      if (studentList.options.length > 1 && studentList.selectedIndex > 0) {
        window.alert(
          "Student name: " +
            studentList.options[studentList.selectedIndex].text +
            " Student ID: " +
            studentList.options[studentList.selectedIndex].value +
            " is removed!"
        );
        this.students.splice(studentList.selectedIndex - 1, 1);
      } else {
        window.alert("Select a student to remove!");
      }
    },
    getAllStudents: function() {
      /*
     AXIOS.get(`/student/list`).then(response => {
      // JSON responses are automatically parsed.
      this.students = response.data
    })
    .catch(e => {
      this.errorStudent = e;
    });*/
    },
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

    PopulateDropDownList: function() {
      //this.getAllStudents()
      var studentList = document.getElementById("studentList");
      studentList.options.length = 1;
      for (var i = 0; i < this.students.length; i++) {
        var option = document.createElement("OPTION");
        option.innerHTML = this.students[i].name;
        option.value = this.students[i].studentId;
        studentList.options.add(option);
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
#myButton {
  color: royalblue;
  border: 0px;
  border-radius: 4px;
  padding: 2px;
  margin: auto;
}
b-container {
  height: auto;
}
#studentlist {
  margin-bottom: 20px;
  border-width: 5px;
  border-style: groove;
}
</style>