<template>
  <div id="room" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <b-container fluid v-bind:style="{color: textColor}"> 
      <b-col id="roomList" v-bind:style="{color: textColor}">
        <h6>
          <strong>VIEW ROOM SCHEDULE</strong>
        </h6>

        <div id="table-wrapper" class="container" v-bind:style="{color: textColor}">
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
            :style="{color: textColor}"
          >
            <template slot="actions" slot-scope="props">
              <div class="table-button-container">
                <button
                  class="btn btn-success btn-sm"
                  title="Book a review session!"
                  @click="createReviewSession(props.rowData)"
                >
                  <i class="fa fa-plus"></i>
                </button>
              </div>
            </template>
          </vuetable>
          <div v-bind:style="{color: textColor}">
            <vuetable-pagination-info ref="paginationInfo" info-class="pull-left"></vuetable-pagination-info>

            <vuetable-pagination ref="pagination" @vuetable-pagination:change-page="onChangePage"></vuetable-pagination>
          </div>
        </div>
      </b-col>
    </b-container>
    <b-container v-bind:style="{color: textColor}">
      <b-col id="reviewSession">
        <div class="col-auto my-1">
          <select
            class="custom-select mr-sm-2"
            id="inlineFormCustomSelect"
            name="inlineFormCustomSelect"
          >
            <option selected>Choose Offering...</option>
          </select>
          <button class="btn btn-primary" title="Populate list!" @click="populateOfferingList">List</button>
        </div>
        <form>
          Confirm Your Manager ID:
          <input
            class="reviewField"
            text="text"
            id="managerID"
            v-model="managerID"
            placeholder="Enter manager ID"
          />
        </form>
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
  name: "rooms",
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
          field: "roomCode",
          sortField: "roomCode",
          direction: "asc"
        }
      ],
      fields: [
        {
          name: "roomCode",
          title: "Room ID",
          sortField: "roomCode"
        },
        {
          name: "isBooked",
          title: "Booked",
          sortField: "isBooked"
        },
        {
          name: "isBigRoom",
          title: "Big Room",
          sortField: "isBigRoom"
        },
        {
          name: "actions",
          title: "Actions"
        }
      ],
      //  filterText: "",
      rooms: [],
      errorRoom: "",
      response: [],
      bgColor: "",
      textColor: "",
      offerings: [],
      offeringID: "",
      managerID: "",
      errorOffering: "",
      errorRoom: ""
    };
  },

  watch: {
    rooms(newVal, oldVal) {
      this.$refs.vuetable.setData(this.rooms);
      this.$refs.vuetable.refresh();
    }
  },

  created: function() {
    this.updateRooms()
    this.populateOfferingList()

    var darkModeOn = localStorage.getItem("DarkModeOn");
    if (darkModeOn === "true") {
      this.bgColor = "rgb(53,58.62)";
      this.textColor = "white";
      this.css.tableClass = `table table-bordered table-hover white`;
    } else {
      this.bgColor = "rgb(250,250,250)";
      this.textColor = "black";
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
    updateRooms() {
      // Initializing rooms from backend
      AXIOS.get(`http://localhost:8080/classroom/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.rooms = response.data;
        })
        .catch(e => {
          this.errorRoom = e;
        });
    },
    dataManager(sortOrder, pagination) {
      if (this.rooms.length < 1) return;

      let local = this.rooms;

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
        data: _.slice(local, from, to)
      };
    },
    onFilterSet(filterText) {
      let room = this.rooms[0];
      let data = this.rooms.filter(room => {
        return (
          room.roomCode.toLowerCase().includes(filterText.toLowerCase())
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
        this.buttonClass = "btn btn-dark btn-lg signupField";
        this.buttonClass = "btn btn-dark btn-lg signupField";

      } else {
        this.bgColor = "rgb(250,250,250)";
        this.textColor = "black";
        this.buttonClass = "btn btn-white btn-lg signupField";
      }
    },
    createReviewSession(rowData) {
      var offeringList = document.getElementById("inlineFormCustomSelect")
      if((offeringList.selectedIndex > 0) && (offeringList.options[offeringList.selectedIndex].text)){
        this.offeringID = offeringList.options[offeringList.selectedIndex].text
      }
      if(this.offeringID == ''){
        alert("ERROR: Enter offering ID to add a review session!")
        return -1
      }
      if(this.managerID == ''){
        alert("ERROR: Enter manager ID to add a review session!")
        return -1
      }
      AXIOS.post(
        "/classroom/review/create/" +
          this.offeringID +
          "?managerID=" +
          this.managerID +
          "&roomCode=" +
          rowData.roomCode +
          "&tutoringSystemID=1"
      ).then(response => {
        this.errorRoom = ''
        this.rooms = response.data;
      })
      .catch(e => {
        var errorMsg = e.message
        console.log(errorMsg)
        alert(errorMsg)
        this.errorRoom = errorMsg;
      });
      alert("You clicked add on: " + JSON.stringify(rowData))
      this.offeringID = ''
      this.managerID = ''
    },
    populateOfferingList() {
      AXIOS.get(`http://localhost:8080/offering/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.offerings = response.data;
        })
        .catch(e => {
          this.errorOffering = e;
        });
      var inlineFormCustomSelect = document.getElementById(
        "inlineFormCustomSelect"
      );
      inlineFormCustomSelect.options.length = 1;
      for (var i = 0; i < this.offerings.length; i++) {
        var option = document.createElement("OPTION");
        option.innerHTML = this.offerings[i].offeringID;
        option.value = this.offerings[i].offeringID;
        inlineFormCustomSelect.options.add(option);
      }
    }
  },
  mounted() {
    // Listens to the setDarkModeState event emitted from the LogoBar component
    this.$root.$on("setDarkModeState", this.setDarkMode);
    this.$events.$on("filter-set", eventData => this.onFilterSet(eventData));
    this.$events.$on("filter-reset", e => this.onFilterReset());
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

#roomList {
  /*margin-bottom: 20px;*/
  border-width: 5px;
  border-style: groove;
}
#reviewSession {
  width: auto;
  height: auto;
  margin-top: 20px;
  margin-bottom: 20px;
  border-style: groove;
}
#myButton {
  margin-top: 10px;
}
form {
  margin-top: 10px;
  margin-bottom: 10 px;
}
</style>
