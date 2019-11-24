<template>
  <div id="room" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <b-container fluid>
      <b-col id="roomList">
        <h6>
          <strong>VIEW ROOM SCHEDULE</strong>
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
                  title="Remove a room!"
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
import FilterBar from "./FilterBarRoom";
import VueEvents from "vue-events";
Vue.use(VueEvents);

var config = require("../../config");

var frontendUrl = "http://" + config.build.host + ":" + config.build.port;
var backendUrl =
  "http://" + config.build.backendHost + ":" + config.build.backendPort;

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
          field: "roomID",
          sortField: "roomId",
          direction: "asc"
        }
      ],
      fields: [
        {
          name: "roomCode",
          title: "Room ID",
          sortField: "roomId"
        },
        {
          name: "isBooked",
          title: "isBooked",
          sortField: "isBooked"
        },
        {
          name: "isBigRoom",
          title: "isBigRoom",
          sortField: "isBigRoom"
        }
      ],
    //  filterText: "",
      rooms: [],
      errorRoom: "",
      response: [],
      bgColor: "",
      textColor: ""
    };
  },

  watch: {
    rooms(newVal, oldVal) {
      this.$refs.vuetable.refresh();
  
    }
  },

  created: function() {
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
    deleteRow(rowData) {
      AXIOS.delete(`http://localhost:8080/classroom/delete/${rowData.personId}`)
        .then(response => {
          this.errorRoom = "";
        })
        .catch(e => {
          var errorMsg = e.message;
          console.log(errorMsg);
          this.errorRoom = errorMsg;
        });
      alert("You clicked delete on: " + JSON.stringify(rowData));
      this.updateRooms()
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
    //  this.moreParams = {
      // 'filter': filterText
     // };
     // Vue.nextTick(() => this.$refs.vuetable.refresh());
      let room = this.rooms[0];
      let data = this.rooms.filter(room => {
        return (
          room.firstName.toLowerCase().includes(filterText.toLowerCase()) ||
          room.lastName.toLowerCase().includes(filterText.toLowerCase())
        );
      });
      this.$refs.vuetable.setData(data);
    },
    onFilterReset() {
    //  this.moreParams = {};
      this.$refs.vuetable.refresh();
     // Vue.nextTick(() => this.$refs.vuetable.refresh());
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

.pagination {
  margin-bottom: 10px;
}

#roomList {
  /*margin-bottom: 20px;*/
  border-width: 5px;
  border-style: groove;
}
</style>
