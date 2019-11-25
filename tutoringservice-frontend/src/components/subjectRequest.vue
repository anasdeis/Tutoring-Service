<!--- This component acts as a page to view subject request and add subject from request --->
<template>
  <div id="subjectRequest" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <b-container fluid :style="{color: textColor}">
      <b-col id="subjectRequestsList" :style="{color: textColor}">
        <h6>
          <strong>VIEW SUBJECT REQUESTS</strong>
        </h6>

        <div id="table-wrapper" class="container">
          <div class="filter-bar pull-left">
            <div class="form-inline">
              <div class="form-group">
                <label>
                  <b>Add subject:</b>&nbsp;&nbsp;
                </label>

                <input
                  type="text"
                  v-model="courseID"
                  class="form-control"
                  placeholder="Enter new course ID.."
                />
                &nbsp;
                <div class="col-auto my-1">
                  <select
                    class="custom-select mr-sm-2"
                    id="inlineFormCustomSelect"
                    name="inlineFormCustomSelect"
                  >
                    <option selected>Choose university...</option>
                  </select>
                  <button
                    class="btn btn-primary"
                    title="Populate list!"
                    @click="populateUniversityList"
                  >List</button>
                </div>
              </div>
            </div>
          </div>
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
                  class="btn btn-success btn-sm"
                  title="Add subject!"
                  @click="addRow(props.rowData)"
                >
                  <i class="fa fa-plus"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm"
                  title="Remove subject request!"
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
  name: "subjectRequests",
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
          field: "requestID",
          sortField: "requestID",
          direction: "asc"
        }
      ],
      fields: [
        {
          name: "requestID",
          title: "ID",
          sortField: "requestID"
        },
        {
          name: "name",
          title: "Name",
          sortField: "name"
        },
        {
          name: "description",
          title: `<span class="icon orange"><i class="fa fa-file-alt"></i></span> Description`,
          sortField: "description"
        },
        {
          name: "subjectType",
          title: '<i class="fas fa-school"></i> School Type',
          sortField: "subjectType"
        },
        {
          name: "actions",
          title: "Actions"
        }
      ],
      subjectRequests: [],
      universities: [],
      courseID: "",
      university: "",
      errorSubjectRequest: "",
      errorUniversity: "",
      response: [],
      bgColor: "",
      textColor: ""
    };
  },

  watch: {
    subjectRequests(newVal, oldVal) {
      this.$refs.vuetable.refresh();
    }
  },

  created: function() {
    this.updateSubjectRequests();
    this.populateUniversityList();

    var darkModeOn = localStorage.getItem("DarkModeOn");
    if (darkModeOn === "true") {
      this.bgColor = "rgb(53,58,62)";
      this.textColor = "white";
      this.buttonClass = "btn btn-dark btn-lg container";
      this.css.tableClass = `table table-bordered table-hover white`;
    } else {
      this.bgColor = "rgb(250,250,250)";
      this.textColor = "black";
      // this.bgColor = "rgb(248, 249, 251)";
      this.buttonClass = "btn btn-white btn-lg container";
    }
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
    populateUniversityList() {
      AXIOS.get(`http://localhost:8080/university/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.universities = response.data;
        })
        .catch(e => {
          this.errorUniversity = e;
        });

      var inlineFormCustomSelect = document.getElementById(
        "inlineFormCustomSelect"
      );
      inlineFormCustomSelect.options.length = 1;
      for (var i = 0; i < this.universities.length; i++) {
        var option = document.createElement("OPTION");
        option.innerHTML = this.universities[i].name;
        option.value = this.universities[i].name;
        inlineFormCustomSelect.options.add(option);
      }
    },
    updateSubjectRequests() {
      // Initializing reviews from backend
      AXIOS.get(`http://localhost:8080/subjectRequest/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.subjectRequests = response.data;
        })
        .catch(e => {
          this.errorSubjectRequest = e;
        });
    },
    addRow(rowData) {
      var universityList = document.getElementById("inlineFormCustomSelect");
      if (
        universityList.selectedIndex > 0 &&
        universityList.options[universityList.selectedIndex].text
      ) {
        this.university =
          universityList.options[universityList.selectedIndex].text;
      }

<<<<<<< HEAD
      /*
      let link = ""
      if(rowData.subjectType === "University"){
        link = `http://localhost:8080/subject/create/${rowData.name}?courseID=${this.courseID}&description=${rowData.description}&subjectType=${rowData.subjectType}&tutoringSystemID=${rowData.tutoringSystem}&university=${this.university}`
      }
      else{
        link = `http://localhost:8080/subject/create/${rowData.name}?courseID=${this.courseID}&description=${rowData.description}&subjectType=${rowData.subjectType}&tutoringSystemID=${rowData.tutoringSystem}`
      }*/
      AXIOS.post(`http://localhost:8080/subject/create/${rowData.name}?courseID=${this.courseID}&description=${rowData.description}&subjectType=${rowData.subjectType}&tutoringSystemID=${rowData.tutoringSystem}&university=${this.university}`)
=======
      if (this.courseID == "") {
        alert(
          "ERROR: You must enter a new course ID to the subject you want to add to the system!"
        );
        return -1;
      }

      let link = "";
      if (rowData.subjectType === "University") {
        if (this.university == "") {
          alert(
            "ERROR: You must select a university for the the subject you want to add to the system!"
          );
          return -1;
        }
        link = `http://localhost:8080/subject/create/${rowData.name}?courseID=${this.courseID}&description=${rowData.description}&subjectType=${rowData.subjectType}&tutoringSystemID=${rowData.tutoringSystem}&university=${this.university}`;
      } else {
        link = `http://localhost:8080/subject/create/${rowData.name}?courseID=${this.courseID}&description=${rowData.description}&subjectType=${rowData.subjectType}&tutoringSystemID=${rowData.tutoringSystem}`;
      }
      AXIOS.post(link)
>>>>>>> master
        .then(response => {
          this.errorSubjectRequest = "";
          this.university = "";
        })
        .catch(e => {
          var errorMsg = e.response.status + " " + e.response.data.error + ": " + e.response.data.message;
          console.log(errorMsg);
<<<<<<< HEAD
          this.errorSubjectRequest = errorMsg;
        });
      alert("You clicked add on: "+ JSON.stringify(rowData));
      this.university = ''
      this.courseID = ''
      if(this.errorSubjectRequest != ''){
        alert(this.errorSubjectRequest)
      }
=======
          alert(errorMsg);
          this.errorSubjectRequest = errorMsg;
        });
      alert("You clicked add on: " + JSON.stringify(rowData));
      this.university = "";
      this.courseID = "";
>>>>>>> master
    },
    deleteRow(rowData) {
      AXIOS.delete(
        `http://localhost:8080/subjectRequest/delete/${rowData.requestID}`
      )
        .then(response => {
          this.errorSubjectRequest = "";
        })
        .catch(e => {
          var errorMsg = e.response.status + " " + e.response.data.error + ": " + e.response.data.message;
          console.log(errorMsg);
<<<<<<< HEAD
=======
          alert(errorMsg);
>>>>>>> master
          this.errorSubjectRequest = errorMsg;
        });
      alert("You clicked delete on: " + JSON.stringify(rowData));
      this.updateSubjectRequests();
      if(this.errorSubjectRequest != ''){
        alert(this.errorSubjectRequest)
      }
    },
    dataManager(sortOrder, pagination) {
      //if (this.subjectRequests.length < 1) return;

      let local = this.subjectRequests;

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
      let subjectRequest = this.subjectRequests[0];
      let data = this.subjectRequests.filter(subjectRequest => {
        return subjectRequest.name
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
    document.getElementsByName("search")[0].placeholder = "Search name..";
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

#subjectRequestsList {
  border-width: 5px;
  border-style: groove;
}
</style>