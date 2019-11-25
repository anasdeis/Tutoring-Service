<template>
  <div id="commission" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <span id="title" v-bind:style="{color : textColor}"></span>
    <div :style="{color : textColor}">
      <span id="title1"></span>
    </div>

    <b-container fluid :style="{color : textColor}">
      <b-col id="commissionList">
        <h6>
          <strong>VIEW COMMISSIONS</strong>
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
                  class="btn btn-danger btn-sm"
                  title="Remove commission!"
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
      <center>
        <!-- <b-row id="findTutor">
          <form>
            Tutor ID:
            <input
              class="commissionField"
              type="number"
              id="tutorID"
              v-model="tutorID"
              placeholder="Enter tutor ID"
            />
          </form>
          <button
            type="button"
            id="myButton"
            @click="getTutor(tutorID)"
            class="btn btn-primary btn-lg commissionField button"
            :class="buttonClass"
            title="Find the tutor"
          >Find tutor</button>
        </b-row>-->
        <!-- <p>Tutor information</p>
        <b-row id="findTutor">
        </b-row>-->
        <form>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Commission ID:
          <input
            class="commissionField"
            type="number"
            id="commissionID"
            v-model="commissionID"
            placeholder="Enter Commission ID"
          />
        </form>
        <form>
          Commission percentage:
          <input
            class="commissionField"
            type="number"
            step="0.01"
            id="percentage"
            v-model="percentage"
            placeholder="Enter percentage"
          />
        </form>
        <form>
          Confirm your manager ID:
          <input
            class="commissionField"
            type="number"
            id="managerID"
            v-model="managerID"
            placeholder="Enter managerID"
          />
        </form>
        <!-- <form>
          Offering ID:
          <input
            class="commissionField"
            type="number"
            id="offeringID"
            v-model="offeringID"
            placeholder="Enter Offering ID"
          />
        </form>-->

        <button
          type="button"
          id="myButton"
          @click="createCommission(percentage,commissionID, managerID)"
          class="btn btn-primary btn-lg commissionField button"
          :class="buttonClass"
          title="Setup commission"
        >Create commission</button>
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
    name: "commissions",
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
          field: "commissionID",
          sortField: "commissionID",
          direction: "asc"
        }
      ],
      fields: [
        {
          name: "commissionID",
          title: "ID",
          sortField: "commissionID"
        },
        {
          name: "percentage",
          title: `<span class="icon orange"><i class="fas fa-percent"></i></span> Percentage`,
          sortField: "percentage"
        },
        {
          name: "offering",
          title: `<i class="fas fa-book-open"></i></span> Offering`,
          sortField: "offering"
        },
        {
          name: "actions",
          title: "Actions"
        }
      ],
      bgColor: "",
      textColor: "",
      commissionID: "",
      percentage: "",
      managerID: "",
      offeringID: "",
      error: "",
      response: [],
      commissions: []
    };
  },

  watch: {
    commissions(newVal, oldVal) {
      this.$refs.vuetable.setData(this.commissions);
      this.$refs.vuetable.refresh();
    }
  },
  created: function() {
    this.updateCommissions()

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
    updateCommissions() {
      // Initializing students from backend
      AXIOS.get(`commission/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.commissions = response.data;
        })
        .catch(e => {
          this.error = e;
        });
    },
    deleteRow(rowData) {
      AXIOS.delete(`commission/delete/${rowData.commissionID}`)
        .then(response => {
          this.error = "";
        })
        .catch(e => {
          var errorMsg = e.response.status + " " + e.response.data.error + ": " + e.response.data.message;
          console.log(errorMsg);
          this.error = errorMsg;
        });
      alert("You clicked delete on: " + JSON.stringify(rowData));
      this.updateCommissions();
      if(this.error != ''){
        alert(this.error)
      }
    },
    dataManager(sortOrder, pagination) {
      if (this.commissions.length < 1) return;

      let local = this.commissions;

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
      let student = this.commissions[0];

      let data = this.commissions.filter(commission => {
        return (
          commission.percentage.toString().includes(filterText.toString())
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
      } else {
        this.bgColor = "rgb(250,250,250)";
        this.textColor = "black";
        this.buttonClass = "btn btn-white btn-lg container";
      }
    },
    createCommission: function(
      percentage,
      commissionID,
      managerID,
    ) {
      AXIOS.post(
        "/commission/create/" +
          commissionID +
          "?percentage=" +
          percentage +
          "&managerID=" +
          managerID +
          "&tutoringSystemID=1"
      )
        .then(response => {
          this.commissions.push(response.data);
          error = "";
        })
        .catch(e => {
          var errorMsg = e.response.status + " " + e.response.data.error + ": " + e.response.data.message;
          console.log(errorMsg);
          this.error = errorMsg;
        });
        alert("You clicked setup commission for: " + this.response.data.commissionID);
        this.updateCommissions()
        this.commissionID = ''
        this.percentage = ''
        this.managerID = ''
        if(this.error != ''){
          alert(this.error)
      }
    }
  },
  mounted() {
    this.$root.$on("setDarkModeState", this.setDarkMode);
    this.$events.$on("filter-set", eventData => this.onFilterSet(eventData));
    this.$events.$on("filter-reset", e => this.onFilterReset());
    document.getElementsByName("search")[0].placeholder =
      "Search percentage..";
  }
};
</script>

<style>
#myButton {
  color: white;
  margin-top: 20px;
}

#b-container {
  vertical-align: center;
  margin-top: auto;
  margin-bottom: auto;
  height: auto;
}
#commissionList {
  border-width: 5px;
  border-style: groove;
}
.commissionField {
  margin-top: 5px;
  margin-bottom: 5px;
}
</style>