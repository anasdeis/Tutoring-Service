<template>
  <div id="offering" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <b-container fluid :style="{color: textColor}">
      <b-col id="offeringList">
        <h6>
          <strong>VIEW OFFERINGS</strong>
        </h6>

        <div id="table-wrapper" class="container">
          <filter-bar></filter-bar>
          <vuetable
            ref="vuetable"
            :fields="fields"
            :api-mode="false"
            pagination-path="pagination"
            :per-page="perPage"
            :sort-order="sortOrder"
            :multi-sort="true"
            :css="css"
            :data-manager="dataManager"
            :render-icon="renderIcon"
            @vuetable:pagination-data="onPaginationData"
          >
            <template slot="actions" slot-scope="props">
              <div class="table-button-container">
                <button
                  class="btn btn-success btn-sm icon"
                  title="Add student!"
                  @click="addRow(props.rowData)"
                >
                  <i class="fa fa-plus"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm icon"
                  title="Remove offering!"
                  @click="deleteRow(props.rowData)"
                >
                  <i class="fa fa-trash"></i>
                </button>
              </div>
            </template>
          </vuetable>
          <div>
            <vuetable-pagination-info ref="paginationInfo" info-class="pull-left"></vuetable-pagination-info>
            <vuetable-pagination ref="pagination" @vuetable-pagination:change-page="onChangePage"></vuetable-pagination>
          </div>
        </div>
      </b-col>
      <hr />
      <center>
        <table>
          <tbody>
            <tr>
              <td>
                <label style="margin-bottom:25px;" for="students-title">
                  <b>Add student:</b>&nbsp;
                </label>
              </td>
              <td>
                <select class="custom-select" id="student" name="student">
                  <option selected>Choose student...</option>
                </select>
                <small id="studentHelp" class="form-text text-muted">
                  Click
                  <i class="fa fa-plus"></i> in Actions.
                </small>
              </td>
            </tr>
            <tr>
              <td>
                <label for="offering-title">Offering:&nbsp;</label>
              </td>
              <td>
                <input
                  class="offeringField form-control"
                  type="text"
                  id="offering"
                  v-model="offeringID"
                  placeholder="Enter offering ID"
                />
              </td>
            </tr>
            <tr>
              <td>
                <label for="term-title">Term:&nbsp;</label>
              </td>
              <td>
                <input
                  class="offeringField form-control"
                  type="text"
                  id="term"
                  v-model="term"
                  placeholder="Enter term"
                />
              </td>
            </tr>
            <tr>
              <td>
                <label for="price-title">Price:&nbsp;</label>
              </td>
              <td>
                <input
                  class="offeringField form-control"
                  type="number"
                  id="price"
                  v-model="price"
                  placeholder="Enter price per hour"
                />
              </td>
            </tr>
            <tr>
              <td>
                <label for="subject-title">Subject:&nbsp;</label>
              </td>
              <td>
                <select
                  class="custom-select"
                  id="subject"
                  name="subject"
                  style="margin-bottom:5px;"
                >
                  <option selected>Choose subject...</option>
                </select>
              </td>
            </tr>
            <tr>
              <td>
                <label for="tutor-title">Tutor ID:&nbsp;</label>
              </td>
              <td>
                <select class="custom-select" id="tutor" name="tutor" style="margin-bottom:5px;">
                  <option selected>Choose tutor...</option>
                </select>
              </td>
            </tr>
            <tr>
              <td>
                <label for="commission-title">Commission ID:&nbsp;</label>
              </td>
              <td>
                <select
                  class="custom-select"
                  id="commission"
                  name="commission"
                  style="margin-bottom:5px;"
                >
                  <option selected>Choose commission...</option>
                </select>
              </td>
            </tr>
            <tr>
              <td>
                <label for="classtime-title">Classtime ID:&nbsp;</label>
              </td>
              <td>
                <select
                  class="custom-select"
                  id="classtime"
                  name="classtime"
                  style="margin-bottom:5px;"
                >
                  <option selected>Choose classtime...</option>
                </select>
              </td>
            </tr>
            <tr>
              <td>
                <label for="classroom-title">Classroom:&nbsp;</label>
              </td>
              <td>
                <select
                  class="custom-select"
                  id="classroom"
                  name="classroom"
                  style="margin-bottom:5px;"
                >
                  <option selected>Choose classrom...</option>
                </select>
              </td>
            </tr>
            <tr>
              <td></td>
              <td>
                <button
                  type="button"
                  id="myButton"
                  @click="List()"
                  class="btn btn-primary btn-lg offeringField button"
                  :class="buttonClass"
                  title="Populate lists!"
                >List</button>
              </td>
            </tr>
            <tr>
              <td></td>
              <td>
                <button
                  type="button"
                  id="myButton"
                  @click="createOffering()"
                  class="btn btn-primary btn-lg offeringField button"
                  :class="buttonClass"
                  title="Add existing offering"
                >Create offering</button>
              </td>
            </tr>
          </tbody>
        </table>
      </center>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";
import Router from "../router";
import Vuetable from "vuetable-2/src/components/Vuetable";
import VuetablePagination from "vuetable-2/src/components/VuetablePaginationDropdown";
import VuetablePaginationInfo from "vuetable-2/src/components/VuetablePaginationInfo";
import _ from "lodash";
import Vue from "vue";
import FilterBar from "./FilterBar";
import VueEvents from "vue-events";
Vue.use(VueEvents);

var config = require("../../config");

var frontendUrl = "http://" + config.build.host + ":" + config.build.port;
var backendUrl = "http://localhost:8080/";
// "http://" + config.build.backendHost + ":" + config.build.backendPort;

// axios config
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  name: "offering",
  components: {
    Vuetable,
    VuetablePagination,
    VuetablePaginationInfo,
    FilterBar
  },
  data() {
    return {
      perPage: 10,
      css: {
        tableClass: "table table-bordered table-hover",
        ascendingIcon: "fa fa-chevron-up",
        descendingIcon: "fa fa-chevron-down",
        loadingClass: "loading",
        ascendingClass: "sorted-asc",
        descendingClass: "sorted-desc"
      },
      sortOrder: [
        {
          field: "offeringID",
          sortField: "offeringID",
          direction: "asc"
        }
      ],
      fields: [
        {
          name: "offeringID",
          title: "ID",
          sortField: "offeringID"
        },
        {
          name: "subject",
          title: `<span class="icon orange"><i class="fas fa-book-open"></i></span> Subject`,
          sortField: "subject"
        },
        {
          name: "term",
          title: `<span class="icon orange"><i class="fas fa-calendar-alt"></i></span> Term`,
          sortField: "term"
        },
        {
          name: "commission",
          title: `<span class="icon orange"><i class="fas fa-percent"></i></span> Commission ID`,
          sortField: "commission"
        },
        {
          name: "classroom",
          title:
            '<span class="icon orange"><i class="fas fa-chalkboard"></i></span> Classroom',
          sortField: "classroom"
        },
        {
          name: "classTime",
          title:
            '<span class="icon orange"><i class="fas fa-clock"></i></span> Classtime ID',
          sortField: "classTime"
        },
        {
          name: "review",
          title:
            '<span class="icon orange"><i class="fas fa-comment"></i></span> Review IDs',
          sortField: "review"
        },
        {
          name: "tutor",
          title:
            '<span class="icon orange"><i class="fas fa-chalkboard-teacher"></i></span> Tutor ID',
          sortField: "tutor"
        },
        {
          name: "students",
          title:
            '<span class="icon orange"><i class="fas fa-user-graduate"></i></span> Student IDs',
          sortField: "students"
        },
        {
          name: "actions",
          title: "Actions"
        }
      ],
      offerings: [],
      errorOffering: "",
      response: [],
      offeringID: "",
      term: "",
      price: "",
      classtime: [],
      commission: [],
      tutor: [],
      subject: [],
      classroom: [],
      student: [],
      students: "",
      bgColor: "",
      textColor: ""
    };
  },

  watch: {
    offerings(newVal, oldVal) {
      this.$refs.vuetable.refresh();
    }
  },

  created: function() {
    this.updateOfferings();
    this.updateSubjects();
    this.updateTutors();
    this.updateAvailableSessions();
    this.updateCommissions();
    this.updateClassrooms();
    this.updateStudents();
    this.setDarkMode();
  },
  methods: {
    renderIcon(classes, options) {
      return `<span class="${classes.join(" ")}"></span>`;
    },
    onPaginationData(paginationData) {
      this.$refs.pagination.setPaginationData(paginationData);
      this.$refs.paginationInfo.setPaginationData(paginationData);
    },
    onChangePage(page) {
      this.$refs.vuetable.changePage(page);
    },
    updateOfferings() {
      // Initializing offerings from backend
      AXIOS.get(`offering/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.offerings = response.data;
        })
        .catch(e => {
          this.errorOffering = e.message;
          console.log(this.errorOffering);
        });
    },
    updateSubjects() {
      // Initializing offerings from backend
      AXIOS.get(`subject/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.subject = response.data;
        })
        .catch(e => {
          this.errorOffering = e.message;
          console.log(this.errorOffering);
        });
    },
    updateStudents() {
      // Initializing offerings from backend
      AXIOS.get(`student/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.student = response.data;
        })
        .catch(e => {
          this.errorOffering = e.message;
          console.log(this.errorOffering);
        });
    },
    updateClassrooms() {
      // Initializing offerings from backend
      AXIOS.get(`classroom/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.classroom = response.data;
        })
        .catch(e => {
          this.errorOffering = e.message;
          console.log(this.errorOffering);
        });
    },
    updateTutors() {
      // Initializing offerings from backend
      AXIOS.get(`tutor/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.tutor = response.data;
        })
        .catch(e => {
          this.errorOffering = e.message;
          console.log(this.errorOffering);
        });
    },
    updateCommissions() {
      // Initializing offerings from backend
      AXIOS.get(`commission/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.commission = response.data;
        })
        .catch(e => {
          this.errorOffering = e.message;
          console.log(this.errorOffering);
        });
    },
    updateAvailableSessions() {
      // Initializing offerings from backend
      AXIOS.get(`availableSession/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.classtime = response.data;
        })
        .catch(e => {
          this.errorOffering = e.message;
          console.log(this.errorOffering);
        });
    },
    populateStudents() {
      this.updateSubjects();

      var inlineFormCustomSelect = document.getElementById("student");
      inlineFormCustomSelect.options.length = 1;
      for (var i = 0; i < this.student.length; i++) {
        var option = document.createElement("OPTION");
        option.innerHTML = this.student[i].personId;
        option.value = this.student[i].personId;
        inlineFormCustomSelect.options.add(option);
      }
    },
    populateSubjects() {
      this.updateSubjects();

      var inlineFormCustomSelect = document.getElementById("subject");
      inlineFormCustomSelect.options.length = 1;
      for (var i = 0; i < this.subject.length; i++) {
        var option = document.createElement("OPTION");
        option.innerHTML = this.subject[i].courseID;
        option.value = this.subject[i].courseID;
        inlineFormCustomSelect.options.add(option);
      }
    },
    populateClassrooms() {
      this.updateClassrooms();

      var inlineFormCustomSelect = document.getElementById("classroom");
      inlineFormCustomSelect.options.length = 1;
      for (var i = 0; i < this.classroom.length; i++) {
        var option = document.createElement("OPTION");
        option.innerHTML = this.classroom[i].roomCode;
        option.value = this.classroom[i].roomCode;
        inlineFormCustomSelect.options.add(option);
      }
    },
    populateTutors() {
      this.updateTutors();

      var inlineFormCustomSelect = document.getElementById("tutor");
      inlineFormCustomSelect.options.length = 1;
      for (var i = 0; i < this.tutor.length; i++) {
        var option = document.createElement("OPTION");
        option.innerHTML = this.tutor[i].personId;
        option.value = this.tutor[i].personId;
        inlineFormCustomSelect.options.add(option);
      }
    },
    populateCommissions() {
      this.updateCommissions();

      var inlineFormCustomSelect = document.getElementById("commission");
      inlineFormCustomSelect.options.length = 1;
      for (var i = 0; i < this.commission.length; i++) {
        var option = document.createElement("OPTION");
        option.innerHTML = this.commission[i].commissionID;
        option.value = this.commission[i].commissionID;
        inlineFormCustomSelect.options.add(option);
      }
    },
    addRow(rowData) {
      var studentList = document.getElementById("student");
      if (
        studentList.selectedIndex > 0 &&
        studentList.options[studentList.selectedIndex].text
      ) {
        var student = studentList.options[studentList.selectedIndex].text;
      } else {
        alert("ERROR: Please select a student before adding an offering!");
        return -1;
      }

      AXIOS.patch(
        `offering/addstudent/${rowData.offeringID}?studentID=${student}`
      )
        .then(response => {
          this.errorOffering = "";
        })
        .catch(e => {
          var errorMsg =
            e.response.status +
            " " +
            e.response.data.error +
            ": " +
            e.response.data.message;
          console.log(errorMsg);
          this.errorOffering = errorMsg;
        });
      alert("You clicked add student on: " + JSON.stringify(rowData));
      studentList.selectedIndex = 0;
      this.updateOfferings();
      if (this.errorOffering != "") {
        alert(this.errorOffering);
      }
    },
    populateClasstimes() {
      this.updateAvailableSessions();

      var inlineFormCustomSelect = document.getElementById("classtime");
      inlineFormCustomSelect.options.length = 1;
      for (var i = 0; i < this.classtime.length; i++) {
        var option = document.createElement("OPTION");
        option.innerHTML = this.classtime[i].availableSessionID;
        option.value = this.classtime[i].availableSessionID;
        inlineFormCustomSelect.options.add(option);
      }
    },
    List() {
      this.populateSubjects();
      this.populateTutors();
      this.populateCommissions();
      this.populateClasstimes();
      this.populateClassrooms();
      this.populateStudents();
    },
    createOffering() {
      if (this.offeringID == "" || this.term == "" || this.price == "") {
        alert("ERROR: Please fill in all empty fields!");
        return -1;
      }

      var subjectList = document.getElementById("subject");
      if (
        subjectList.selectedIndex > 0 &&
        subjectList.options[subjectList.selectedIndex].text
      ) {
        var subject = subjectList.options[subjectList.selectedIndex].text;
      } else {
        alert("ERROR: Please select a subject before adding an offering!");
        return -1;
      }

      var tutorList = document.getElementById("tutor");
      if (
        tutorList.selectedIndex > 0 &&
        tutorList.options[tutorList.selectedIndex].text
      ) {
        var tutor = tutorList.options[tutorList.selectedIndex].text;
      } else {
        alert("ERROR: Please select a tutor before adding an offering!");
        return -1;
      }

      var commissionList = document.getElementById("commission");
      if (
        commissionList.selectedIndex > 0 &&
        commissionList.options[commissionList.selectedIndex].text
      ) {
        var commission =
          commissionList.options[commissionList.selectedIndex].text;
      } else {
        alert("ERROR: Please select a commission before adding an offering!");
        return -1;
      }

      var classtimeList = document.getElementById("classtime");
      if (
        classtimeList.selectedIndex > 0 &&
        classtimeList.options[classtimeList.selectedIndex].text
      ) {
        var classtime = classtimeList.options[classtimeList.selectedIndex].text;
      } else {
        alert("ERROR: Please select a classtime before adding an offering!");
        return -1;
      }

      var classroomList = document.getElementById("classroom");
      if (
        classroomList.selectedIndex > 0 &&
        classroomList.options[classroomList.selectedIndex].text
      ) {
        var classroom = classroomList.options[classroomList.selectedIndex].text;
      } else {
        alert("ERROR: Please select a classroom before adding an offering!");
        return -1;
      }

      AXIOS.post(
        `offering/create/${this.offeringID}?term=${this.term}&price=${this.price}&classTimes=${classtime}&courseID=${subject}&tutorID=${tutor}&commissionID=${commission}&roomCode=${classroom}&tutoringSystemID=1`
      )
        .then(response => {
          this.errorOffering = "";
        })
        .catch(e => {
          var errorMsg =
            e.response.status +
            " " +
            e.response.data.error +
            ": " +
            e.response.data.message;
          console.log(errorMsg);
          this.errorOffering = errorMsg;
        });
      alert("You clicked on create offering!");
      this.offeringID = "";
      this.term = "";
      this.price = "";
      this.students = [];
      subjectList.selectedIndex = 0;
      tutorList.selectedIndex = 0;
      commissionList.selectedIndex = 0;
      classtimeList.selectedIndex = 0;
      classroomList.selectedIndex = 0;
      this.updateOfferings();
      if (this.errorOffering != "") {
        alert(this.errorOffering);
      }
    },
    deleteRow(rowData) {
      AXIOS.delete(`offering/delete/${rowData.offeringID}`)
        .then(response => {
          this.errorOffering = "";
        })
        .catch(e => {
          var errorMsg =
            e.response.status +
            " " +
            e.response.data.error +
            ": " +
            e.response.data.message;
          console.log(errorMsg);
          this.errorOffering = errorMsg;
        });
      alert("You clicked delete on: " + JSON.stringify(rowData));
      this.updateOfferings();
      if (this.errorOffering != "") {
        alert(this.errorOffering);
      }
    },
    dataManager(sortOrder, pagination) {
      let local = this.offerings;

      // sortOrder can be empty, so we have to check for that as well
      if (sortOrder.length > 0) {
        console.log("orderBy:", sortOrder[0].sortField, sortOrder[0].direction);
        local = _.orderBy(
          local,
          sortOrder[0].sortField,
          sortOrder[0].direction
        );
      }

      pagination = this.$refs.vuetable.makePagination(
        local.length,
        this.perPage
      );
      console.log("pagination:", pagination);
      let from = pagination.from - 1;
      let to = from + this.perPage;

      return {
        pagination: pagination,
        data: local.slice(from, to)
      };
    },
    onFilterSet(filterText) {
      let data = this.offerings.filter(offering => {
        return offering.offeringID
          .toLowerCase()
          .includes(filterText.toLowerCase());
      });

      this.$refs.vuetable.setData(data);
    },
    onFilterReset() {
      this.$refs.vuetable.refresh();
    },
    setDarkMode: function() {
      var darkModeOn = localStorage.getItem("DarkModeOn");
      if (darkModeOn === "true") {
        this.bgColor = "rgb(53, 58, 62)";
        this.textColor = "white";
        this.buttonClass = "btn btn-dark btn-lg container";
        this.css.tableClass = `table table-bordered table-hover white`;
      } else {
        this.bgColor = "rgb(250,250,250)";
        this.textColor = "black";
        this.buttonClass = "btn btn-white btn-lg container";
      }
    }
  },
  mounted() {
    // Listens to the setDarkModeState event emitted from the LogoBar component
    this.$root.$on("setDarkModeState", this.setDarkMode);
    this.$events.$on("filter-set", eventData => this.onFilterSet(eventData));
    this.$events.$on("filter-reset", e => this.onFilterReset());
    document.getElementsByName("search")[0].placeholder = "Search ID..";
  }
};
</script>

<style>
b-container {
  height: auto;
}
.orange {
  color: orange;
}
.white {
  color: white;
}
.pagination {
  margin-bottom: 10px;
}
#offeringList {
  margin-bottom: 20px;
  border-width: 5px;
  border-style: groove;
}
.offeringField {
  margin-top: 5px;
  margin-bottom: 5px;
}
.icon {
  width: 30px;
}
#myButton {
  width: 200px;
}
</style>