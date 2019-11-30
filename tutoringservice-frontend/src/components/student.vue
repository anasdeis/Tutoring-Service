<template>
  <div id="student" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <b-container fluid :style="{color: textColor}">
      <b-col id="studentList">
        <h6>
          <strong>VIEW STUDENTS</strong>
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
                  class="btn btn-danger btn-sm icon"
                  title="Remove student!"
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
  name: "student",
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
          field: "personId",
          sortField: "personId",
          direction: "asc"
        }
      ],
      fields: [
        {
          name: "personId",
          title: "ID",
          sortField: "personId"
        },
        {
          name: "firstName",
          title: `<span class="icon orange"><i class="fa fa-user"></i></span> First Name`,
          sortField: "firstName"
        },
        {
          name: "lastName",
          title: `<span class="icon orange"><i class="fa fa-user"></i></span> Last Name`,
          sortField: "lastName"
        },
        {
          name: "dateOfBirth",
          title:
            '<span class="icon orange"><i class="fa fa-birthday-cake"></i></span> Birthdate',
          sortField: "dateOfBirth"
        },
        {
          name: "email",
          title:
            '<span class="icon orange"><i class="fa fa-envelope"></i></span> Email',
          sortField: "email"
        },
        {
          name: "phoneNumber",
          title:
            '<span class="icon orange"><i class="fa fa-phone"></i></span> Phone',
          sortField: "phoneNumber"
        },
        {
          name: "numCoursesEnrolled",
          title: "No. of Courses",
          sortField: "numCoursesEnrolled"
        },
        {
          name: "actions",
          title: "Actions"
        }
      ],
      students: [],
      errorStudent: "",
      response: [],
      bgColor: "",
      textColor: ""
    };
  },

  watch: {
    students(newVal, oldVal) {
      this.$refs.vuetable.refresh();
    }
  },

  created: function() {
    this.updateStudents();
    this.setDarkMode()
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
    updateStudents() {
      // Initializing students from backend
      AXIOS.get(`student/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.students = response.data;
        })
        .catch(e => {
          this.errorStudent = e.message;
          console.log(this.errorStudent)
        });
    },
    deleteRow(rowData) {
      AXIOS.delete(`student/delete/${rowData.personId}`)
        .then(response => {
          this.errorStudent = "";
        })
        .catch(e => {
          var errorMsg =
            e.response.status +
            " " +
            e.response.data.error +
            ": " +
            e.response.data.message;
          console.log(errorMsg);
          this.errorStudent = errorMsg;
        });
      alert("You clicked delete on: " + JSON.stringify(rowData));
      this.updateStudents();
      if (this.errorStudent != "") {
        alert(this.errorStudent);
      }
    },
    dataManager(sortOrder, pagination) {
      let local = this.students;

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
      let data = this.students.filter(student => {
        return (
          student.firstName.toLowerCase().includes(filterText.toLowerCase()) ||
          student.lastName.toLowerCase().includes(filterText.toLowerCase())
        );
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
    document.getElementsByName("search")[0].placeholder =
      "Search first/last name..";
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
.icon{
  width: 30px;
}
#studentList {
  border-width: 5px;
  border-style: groove;
}
</style>