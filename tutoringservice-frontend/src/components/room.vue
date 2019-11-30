<template>
  <div id="room" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <b-container fluid :style="{color: textColor}">
      <b-col id="roomList">
        <h6>
          <strong>VIEW CLASSROOMS</strong>
        </h6>

        <div id="table-wrapper" class="container">
          <div class="form-inline">
            <table>
              <tbody>
                <tr>
                  <td>
                    <label style="margin-bottom:5px;">
                      <b>Book review session:</b>
                    </label>
                  </td>

                  <td>
                    &nbsp;
                    <select
                      class="custom-select mr-sm-2"
                      id="inlineFormCustomSelect"
                      name="inlineFormCustomSelect"
                      style="margin-bottom:10px;"
                    >
                      <option selected>Choose Offering...</option>
                    </select>
                  </td>
                  <td>
                    <button
                      class="btn btn-primary"
                      title="Populate list!"
                      @click="populateOfferingList"
                      style="width:51px;margin-bottom:10px;"
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
                  title="Book review session!"
                  @click="createReviewSession(props.rowData)"
                >
                  <i class="fa fa-plus"></i>
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
  name: "room",
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
          title: "Room Code",
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
          name: "offering",
          title: `<span class="icon orange"><i class="fas fa-book-open"></i></span> Offerings`,
          sortField: "offerings"
        },
        {
          name: "actions",
          title: "Actions"
        }
      ],
      rooms: [],
      response: [],
      bgColor: "",
      textColor: "",
      offerings: [],
      offeringID: "",
      errorRoom: ""
    };
  },

  watch: {
    rooms(newVal, oldVal) {
      this.$refs.vuetable.refresh();
    }
  },

  created: function() {
    this.updateRooms();
    this.populateOfferingList();
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
    updateRooms() {
      // Initializing rooms from backend
      AXIOS.get(`classroom/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.rooms = response.data;
        })
        .catch(e => {
          this.errorRoom = e.message;
          console.log(this.errorRoom)
        });
    },
    dataManager(sortOrder, pagination) {
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
        data: local.slice(from, to)
      };
    },
    onFilterSet(filterText) {
      let data = this.rooms.filter(room => {
        return room.roomCode.toLowerCase().includes(filterText.toLowerCase());
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
    createReviewSession(rowData) {
      var offeringList = document.getElementById("inlineFormCustomSelect");
      if (
        offeringList.selectedIndex > 0 &&
        offeringList.options[offeringList.selectedIndex].text
      ) {
        this.offeringID = offeringList.options[offeringList.selectedIndex].text;
      }

      AXIOS.patch(
        "/classroom/review/" +
          rowData.roomCode +
          "?offeringID=" +
          this.offeringID
      )
        .then(response => {
          this.errorRoom = "";
        })
        .catch(e => {
          var errorMsg =
            e.response.status +
            " " +
            e.response.data.error +
            ": " +
            e.response.data.message;
          console.log(errorMsg);
          this.errorRoom = errorMsg;
        });
      alert("You clicked add on: " + JSON.stringify(rowData));
      this.updateRooms();
      this.offeringID = "";
      if (this.errorRoom != "") {
        alert(this.errorRoom);
      }
    },
    populateOfferingList() {
      AXIOS.get(`offering/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.offerings = response.data;
        })
        .catch(e => {
          this.errorRoom = e.message;
          console.log(this.errorRoom)
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
    document.getElementsByName("search")[0].placeholder = "Search room code..";
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
.icon {
  width: 30px;
}
</style>
