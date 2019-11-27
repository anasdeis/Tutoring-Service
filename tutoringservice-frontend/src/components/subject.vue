<!--- This component acts as a page to view subject and add subject from request --->
<template>
  <div id="subject" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <b-container fluid :style="{color: textColor}">
      <b-col id="subjectList">
        <h6>
          <strong>VIEW SUBJECTS</strong>
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
                  title="Remove subject!"
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
  name: "subject",
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
          field: "courseID",
          sortField: "courseID",
          direction: "asc"
        }
      ],
      fields: [
        {
          name: "courseID",
          title: "ID",
          sortField: "courseID"
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
          name: "university",
          title:
            '<span class="icon orange"><i class="fas fa-university"></i></span> University',
          sortField: "university"
        },
        {
          name: "actions",
          title: "Actions"
        }
      ],
      subjects: [],
      errorSubject: "",
      response: [],
      bgColor: "",
      textColor: ""
    };
  },

  watch: {
    subjects(newVal, oldVal) {
      this.$refs.vuetable.refresh();
    }
  },

  created: function() {
    this.updateSubjects();
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
    updateSubjects() {
      // Initializing reviews from backend
      AXIOS.get(`subject/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.subjects = response.data;
        })
        .catch(e => {
          this.errorSubject = e.message;
          console.log(this.errorSubject)
        });
    },
    deleteRow(rowData) {
      AXIOS.delete(`subject/delete/${rowData.courseID}`)
        .then(response => {
          this.errorSubject = "";
        })
        .catch(e => {
          var errorMsg =
            e.response.status +
            " " +
            e.response.data.error +
            ": " +
            e.response.data.message;
          console.log(errorMsg);
          this.errorSubject = errorMsg;
        });
      alert("You clicked delete on: " + JSON.stringify(rowData));
      this.updateSubjects();
      if (this.errorSubject != "") {
        alert(this.errorSubject);
      }
    },
    dataManager(sortOrder, pagination) {
      let local = this.subjects;

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
      let data = this.subjects.filter(subject => {
        return subject.name.toLowerCase().includes(filterText.toLowerCase());
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
#subjectList {
  border-width: 5px;
  border-style: groove;
}
.icon{
  width: 30px;
}
</style>