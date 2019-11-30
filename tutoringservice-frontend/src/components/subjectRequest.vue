<!--- This component acts as a page to view subject request and add subject from request --->
<template>
  <div id="subjectRequest" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <b-container fluid :style="{color: textColor}">
      <b-col id="subjectRequestsList" :style="{color: textColor}">
        <h6>
          <strong>VIEW SUBJECT REQUESTS</strong>
        </h6>

        <div id="table-wrapper" class="container">
          <div class="form-inline">
            <table>
              <tbody>
                <tr>
                  <td>
                    <label style="padding-bottom:10px;">
                      <b>Add university:</b>&nbsp;&nbsp;
                    </label>
                  </td>
                  <input
                    type="text"
                    v-model="universityName"
                    class="form-control"
                    placeholder="Enter university name.."
                  />
                  <td>
                    &nbsp;
                    <button
                      class="btn btn-primary"
                      title="Create university!"
                      style="width:75px;"
                      @click="createUniversity()"
                    >Create</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="form-inline">
            <table>
              <tbody>
                <tr>
                  <td>
                    <label style="padding-bottom:10px;">
                      <b>Add subject:</b>&nbsp;&nbsp;
                    </label>
                  </td>
                  <input
                    type="text"
                    v-model="courseID"
                    class="form-control"
                    placeholder="Enter new course ID.."
                    style="margin-top:20px;"
                  />
                  <small style="margin-bottom:10px;" id="subjectHelp" class="form-text text-muted">
                    Click
                    <i class="fa fa-plus"></i> in Actions.
                  </small>
                  <td>
                    &nbsp;
                    <select
                      class="custom-select"
                      id="inlineFormCustomSelect"
                      name="inlineFormCustomSelect"
                      style="margin-bottom:11px;"
                    >
                      <option selected>Choose university...</option>
                    </select>
                  </td>&nbsp;
                  <td>
                    <button
                      class="btn btn-primary"
                      title="Populate list!"
                      style="width:51px;margin-bottom:10px;"
                      @click="populateUniversityList()"
                    >List</button>
                  </td>
                </tr>
              </tbody>
            </table>
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
                  class="btn btn-success btn-sm icon"
                  title="Add subject!"
                  @click="addRow(props.rowData)"
                >
                  <i class="fa fa-plus"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm icon"
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
  name: "subjectRequest",
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
          title:
            '<span class="icon orange"><i class="fas fa-school"></i></span> School Type',
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
      universityName: "",
      errorSubjectRequest: "",
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
    this.updateUniversity();
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
    updateUniversity() {
      AXIOS.get(`university/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.universities = response.data;
        })
        .catch(e => {
          this.errorSubjectRequest = e.message;
          console.log(this.errorSubjectRequest);
        });
    },
    createUniversity() {
      AXIOS.post(`university/create/${this.universityName}?tutoringSystemID=1`)
        .then(response => {
          this.errorSubjectRequest = "";
        })
        .catch(e => {
          var errorMsg =
            e.response.status +
            " " +
            e.response.data.error +
            ": " +
            e.response.data.message;
          console.log(errorMsg);
          this.errorSubjectRequest = errorMsg;
        });
      alert("You clicked on create university!");
      this.universityName = "";
      this.updateUniversity();
      this.populateUniversityList();
      if (this.errorSubjectRequest != "") {
        alert(this.errorSubjectRequest);
      }
    },
    populateUniversityList() {
      this.updateUniversity();

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
      AXIOS.get(`subjectRequest/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.subjectRequests = response.data;
        })
        .catch(e => {
          this.errorSubjectRequest = e.message;
          console.log(this.errorSubjectRequest);
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

      AXIOS.post(
        `subject/create/${rowData.name}?courseID=${this.courseID}&description=${rowData.description}&subjectType=${rowData.subjectType}&tutoringSystemID=${rowData.tutoringSystem}&university=${this.university}`
      )
        .then(response => {
          this.errorSubjectRequest = "";
          this.university = "";
        })
        .catch(e => {
          var errorMsg =
            e.response.status +
            " " +
            e.response.data.error +
            ": " +
            e.response.data.message;
          console.log(errorMsg);
          this.errorSubjectRequest = errorMsg;
        });
      alert("You clicked add on: " + JSON.stringify(rowData));
      this.university = "";
      this.courseID = "";
      universityList.selectedIndex = 0;
      if (this.errorSubjectRequest != "") {
        alert(this.errorSubjectRequest);
      }
    },
    deleteRow(rowData) {
      AXIOS.delete(`subjectRequest/delete/${rowData.requestID}`)
        .then(response => {
          this.errorSubjectRequest = "";
        })
        .catch(e => {
          var errorMsg =
            e.response.status +
            " " +
            e.response.data.error +
            ": " +
            e.response.data.message;
          console.log(errorMsg);
          this.errorSubjectRequest = errorMsg;
        });
      alert("You clicked delete on: " + JSON.stringify(rowData));
      this.updateSubjectRequests();
      if (this.errorSubjectRequest != "") {
        alert(this.errorSubjectRequest);
      }
    },
    dataManager(sortOrder, pagination) {
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
.icon {
  width: 30px;
}
</style>