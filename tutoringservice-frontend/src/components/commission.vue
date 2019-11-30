<template>
  <div id="commission" class="card" v-bind:style="{ backgroundColor: bgColor}">

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
                  class="btn btn-danger btn-sm icon"
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
        <table>
          <tbody>
            <tr>
              <td>
                <label for="email">Percentage:&nbsp;</label>
              </td>
              <td>
                <input
                  class="commissionField form-control"
                  type="number"
                  step="0.01"
                  id="percentage"
                  v-model="percentage"
                  placeholder="Enter percentage"
                />
              </td>
            </tr>
          </tbody>
        </table>
        <button
          type="button"
          id="myButton"
          @click="createCommission()"
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
  name: "commission",
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
          title: `<span class="icon orange"><i class="fas fa-book-open"></i></span> Offerings`,
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
      offeringID: "",
      errorCommission: "",
      response: [],
      commissions: [],
      tutoringSystem: [],
      manager: [],
    };
  },

  watch: {
    commissions(newVal, oldVal) {
      this.$refs.vuetable.refresh();
    }
  },
  created: function() {
    this.updateCommissions();
    this.updateTutoringSystem()
    this.updateManager()
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
    updateManager(){
      AXIOS.get("manager/list/")
        .then(response => {
          this.manager = response.data;
        })
        .catch(e => {
          this.errorCommission = e.message
          console.log(this.errorLogin);
        });
    },
    updateCommissions() {
      // Initializing commissions from backend
      AXIOS.get(`commission/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.commissions = response.data;
        })
        .catch(e => {
          this.errorCommission = e.message;
          console.log(this.errorCommission)
        });
    },
    deleteRow(rowData) {
      AXIOS.delete(`commission/delete/${rowData.commissionID}`)
        .then(response => {
          this.errorCommission = "";
        })
        .catch(e => {
          var errorMsg =
            e.response.status +
            " " +
            e.response.data.error +
            ": " +
            e.response.data.message;
          console.log(errorMsg);
          this.errorCommission = errorMsg;
        });
      alert("You clicked delete on: " + JSON.stringify(rowData));
      this.updateCommissions();
      if (this.errorCommission != "") {
        alert(this.errorCommission);
      }
    },
    dataManager(sortOrder, pagination) {
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
      let data = this.commissions.filter(commission => {
        return commission.percentage.toString().includes(filterText.toString());
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
    },
    updateTutoringSystem() {
      AXIOS.get("tutoringSystem/list/")
        .then(response => {
          this.tutoringSystem = response.data;
        })
        .catch(e => {
          this.errorCommission = e.message
          console.log(this.errorCommission);
        });
    },
    generateCommissionID() {
      if (this.tutoringSystem != "") {
        this.commissionID = 0;
        for (var i = 0; i < this.tutoringSystem.length; i++) {
          if (this.tutoringSystem[i].tutoringSystemID == 1) {
            for (var j = 0; j < this.tutoringSystem[i].commission.length; j++) {
              if (this.tutoringSystem[i].commission[j] > this.commissionID) {
                this.commissionID = this.tutoringSystem[i].commission[j];
              }
            }
          } else {
            alert("No tutoring system with ID=1 is found!");
            return -1;
          }
        }
      } else {
        alert("ERROR: No tutoring system is found!");
        return -1;
      }
      this.commissionID += 1;
    },
    createCommission: function() {
      this.generateCommissionID()
      AXIOS.post(
        "/commission/create/" +
          this.commissionID +
          "?percentage=" +
          this.percentage +
          "&managerID=" +
          this.manager[0].personId +
          "&tutoringSystemID=1"
      )
        .then(response => {
          this.commissions.push(response.data);
          this.errorCommission = "";
        })
        .catch(e => {
          var errorMsg =
            e.response.status +
            " " +
            e.response.data.error +
            ": " +
            e.response.data.message;
          console.log(errorMsg);
          this.errorCommission = errorMsg;
        });
      alert(
        "You clicked setup commission for: " + this.response.data.commissionID
      );
      this.updateCommissions();
      this.commissionID = "";
      this.percentage = "";
      if (this.errorCommission != "") {
        alert(this.errorCommission);
      }
    }
  },
  mounted() {
    this.$root.$on("setDarkModeState", this.setDarkMode);
    this.$events.$on("filter-set", eventData => this.onFilterSet(eventData));
    this.$events.$on("filter-reset", e => this.onFilterReset());
    document.getElementsByName("search")[0].placeholder = "Search percentage..";
  }
};
</script>

<style>
#myButton {
  color: white;
  margin-top: 20px;
  width: 275px;
}
#b-container {
  vertical-align: center;
  margin-top: auto;
  margin-bottom: auto;
  height: auto;
}
.pagination {
  margin-bottom: 10px;
}
#commissionList {
  margin-bottom: 20px;
  border-width: 5px;
  border-style: groove;
}
.commissionField {
  margin-top: 0px;
  margin-bottom: 5px;
}
.icon{
  width: 30px;
}
</style>