<!--- This component acts as a page to view tutor list and add/remove tutor --->
<template>
  <div id="tutor" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <b-container fluid :style="{color: textColor}">
      <b-col id="tutorList">
        <h6>
          <strong>VIEW REGISTERED TUTORS</strong>
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
                  title="Remove a tutor!"
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
  name: "tutors",
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
        tableClass: `table table-bordered table-hover`,
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
          title: "Tutor ID",
          sortField: "personId"
        },
        {
          name: "firstName",
          title: `<span class="icon orange"><i class="fa fa-user"></i></span> First Name`,
          sortField: "firstName"
        },
        {
          name: "lastName",
          title: "Last Name",
          sortField: "lastName"
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
          name: "actions",
          title: "Actions"
        }
      ],
      //  filterText: "",
      tutors: [],
      errorTutor: "",
      response: [],
      bgColor: "",
      textColor: ""
    };
  },

  watch: {
    tutors(newVal, oldVal) {
      this.$refs.vuetable.refresh();
    }
  },

  created: function() {
    // Initializing tutors from backend
    AXIOS.get(`http://localhost:8080/tutor/list`)
      .then(response => {
        // JSON responses are automatically parsed.
        this.tutors = response.data;
      })
      .catch(e => {
        this.errorTutor = e;
      });

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
    updateTutors() {
      // Initializing tutors from backend
      AXIOS.get(`http://localhost:8080/tutor/list`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.tutors = response.data;
        })
        .catch(e => {
          this.errorTutor = e;
        });
    },
    deleteRow(rowData) {
      AXIOS.delete(`http://localhost:8080/tutor/delete/${rowData.personId}`)
        .then(response => {
          this.errorTutor = "";
        })
        .catch(e => {
          var errorMsg = e.message;
          console.log(errorMsg);
          this.errorTutor = errorMsg;
        });
      alert("You clicked delete on: " + JSON.stringify(rowData));
      this.updateTutors();
    },
    dataManager(sortOrder, pagination) {
      if (this.tutors.length < 1) return;

      let local = this.tutors;

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
      let tutor = this.tutors[0];
      let data = this.tutors.filter(tutor => {
        return (
          tutor.firstName.toLowerCase().includes(filterText.toLowerCase()) ||
          tutor.lastName.toLowerCase().includes(filterText.toLowerCase())
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

#tutorList {
  /*margin-bottom: 20px;*/
  border-width: 5px;
  border-style: groove;
}
</style>



<!--- This component acts as a page for tutor, including add/fire tutor --->
<!--- <template>
  <div id="tutor" class="card" v-bind:style="{ backgroundColor : bgColor}">
    <span id="title" v-bind:style="{color : textColor}"></span>
    <div>
      <span id="title1"></span>
    </div>
    <b-container fluid>
      <b-row>
        <b-col id="tutor">
          <p>View all the tutors</p>
          <b-table striped hover :items="items"></b-table>
        </b-col>
        <b-col id="detailOfTutor">
          <form>
            Enter tutor ID:
            <input
              class="tutorField"
              text="number"
              id="tutorID"
              v-model="tutorID"
              placeholder="Enter tutor ID"
            />
          </form>
          <button
            type="button"
            @click="getTutor(tutorID)"
            class="btn btn-primary btn-lg viewTutor button"
            v-b-tooltip.hover
            title="Display selected tutor"
          >View detail</button>
          <p>Here is the detail of the tutor you selected</p>
          <button
            type="button"
            @click="deleteTutor(tutorID)"
            class="btn btn-primary btn-lg viewTutor button"
            v-b-tooltip.hover
            title="Remove"
          >Remove</button>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
--->


<!---  <script> 
// import axios from "axios";
// import Router from "../router";

// var config = require("../../config");
// var frontendUrl = "http://" + config.build.host + ":" + config.build.port;
// var backendUrl =
//   "http://" + config.build.backendHost + ":" + config.build.backendPort;


// // axios config
// var AXIOS = axios.create({
//   baseURL: backendUrl,
//   headers: { "Access-Control-Allow-Origin": frontendUrl }
// });


// var tutorTable = {
//   // GET YOUR TABLE FROM THE BACKEND- all tutor applications
// };
// var detailOfTutor = {
//   // GET YOUR SELECTED TUTOR APPLICATION OBJECT IN THE BACKEND AND PUT IT HERE
// };
// export default {
//   data() {
//     return {
//       tutor: {
//         type: Object
//       },
//       bgColor: "",
//       textColor: "",
//       tutorID: "",
//       items: [
//         {
//           tutorID: 1001,
//           firstName: "tutor1",
//           lastName: "Last",
//           email: "1001@gamil.com"
//         }
//       ]
//     };
//   },
//   created: function() {
//     var darkModeOn = localStorage.getItem("DarkModeOn");
//     if (darkModeOn === "true") {
//       this.bgColor = "rgb(53,58,62)";
//       this.textColor = "white";
//       this.buttonClass = "btn btn-dark btn-lg signupField";
//     } else {
//       this.bgColor = "rgb(250,250,250)";
//       this.textColor = "black";
//       // this.bgColor = "rgb(248, 249, 251)";
//       this.buttonClass = "btn btn-white btn-lg signupField";
//     }
//   },
//   methods: {


//     setDarkMode: function() {
//       var darkModeOn = localStorage.getItem("DarkModeOn");
//       if (darkModeOn === "true") {
//         this.bgColor = "rgb(53, 58, 62)";
//         this.textColor = "white";
//         this.buttonClass = "btn btn-dark btn-lg signupField";
//       } else {
//         this.bgColor = "rgb(250,250,250)";
//         this.textColor = "black";
//         this.buttonClass = "btn btn-white btn-lg signupField";
//       }
//     },
//     getTutor: function(tutorID) {
//       AXIOS.get("/tutor/=" + tutorID).then(response => {
//         this.getTutor = response.data;
//       });
//     },
//     deleteTutor: function(tutorID) {
//       AXIOS.delete("/tutor/delete/=" + tutorID);
//     }
//   },
//   mounted() {
//     // Listens to the setDarkModeState event emitted from the LogoBar component
//     this.$root.$on("setDarkModeState", this.setDarkMode);
//   }
// };
// </script>



/*
<style>
p {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
}
#myButton {
  color: royalblue;
  border: 0px;
  border-radius: 4px;
  padding: 2px;
  margin: auto;
  margin-top: 5px;
}
b-container {
  height: auto;
}
#tutorTable {
  margin-left: auto;
  margin-right: auto;
}
table {
  font-family: "Open Sans", sans-serif;
  width: 300px;
  border-collapse: collapse;
  border: 3px solid #44475c;
  margin: 5px 5px 0 5px;
}
table th {
  text-transform: uppercase;
  text-align: left;
  background: #44475c;
  color: #fff;
  padding: 8px;
  min-width: 10px;
}
table td {
  text-align: left;
  padding: 8px;
  border-right: 2px solid #7d82a8;
}
table td:last-child {
  border-right: none;
}
table tbody tr:nth-child(2n) td {
  background: #d4d8f9;
}
.tutorField{
  margin-top: 20px;
}
</style>
*/