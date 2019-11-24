<template>
  <div id="tutorApplication" class="card" v-bind:style="{ backgroundColor: bgColor}">
    <b-container fluid>
      <b-col id="tutorApplicationList">
        <h6>
          <strong>VIEW ALL TUTOR APPLICATIONS</strong>
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
                  title="Remove a tutor application!"
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



var frontendUrl = "http://" + config.build.host + ":" + config.build.port;
var backendUrl = "http://localhost:8080/";
  // "http://" + config.build.backendHost + ":" + config.build.backendPort;



<script>
    import axios from "axios";
    import Router from "../router";
    import Vuetable from "vuetable-2/src/components/Vuetable";
    import VuetablePagination from "vuetable-2/src/components/VuetablePaginationDropdown";
    import VuetablePaginationInfo from "vuetable-2/src/components/VuetablePaginationInfo";
    import _ from "lodash";
    import Vue from "vue";
    import FilterBar from "./FilterBar";  /*let's use ./FilterBar for now, add if necessary*/
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
        name: "tutorApplications",
        components: {
            Vuetable,
            VuetablePagination,
            VuetablePaginationInfo,
            FilterBar     /*needs to change that as well*/
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
                        field: "tutorApplicationId",
                        sortField: "tutorApplicationId",
                        direction: "asc"
                    }
                ],
                fields: [
                    {
                        name: "tutorApplicationId",
                        title: "Tutor Application ID",
                        sortField: "tutorApplicationId"
                    },
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
                        title: `<span class="icon black"><i class="fa fa-user"></i></span> Last Name`,
                        sortField: "lastName"
                    },
                    {
                        name: "isAccepted",
                        title: '<i class="fa fa-thumbs-up"></i> Acceptance',
                        sortField: "isAccepted"
                    },
                    {
                        name: "subject",
                        title: "Subjects",
                        sortField: "subject"
                    },
                    {
                        name: "actions",
                        title: "Actions"
                    }
                ],
                tutorApplications: [],
                errorTutorApplication: "",
                response: [],
                bgColor: "",
                textColor: ""
            };
        },

        watch: {
            tutorApplications(newVal, oldVal) {
                this.$refs.vuetable.refresh();
            }
        },

        created: function() {
            this.updateTutorApplications();
        },
        methods: {
            renderIcon(classes, options) {
                return `<span class="${classes.join(" ")}"></span>`;
            },
            onPaginationData(paginationData) {
                this.$refs.pagination.setPaginationData(paginationData);
                this.$refs.paginationInfo.setPaginationData(paginationData);
            },
            updateTutorApplications() {
                AXIOS.get(`http://localhost:8080/tutorApplication/list`)
                    .then(response => {
                        // JSON responses are automatically parsed.
                        this.tutorApplications = response.data;
                    })
                    .catch(e => {
                        this.errorTutorApplication = e;
                    });
            },
            onChangePage(page) {
                this.$refs.vuetable.changePage(page);
            },
            approveRow(rowData) {
                AXIOS.patch(`http://localhost:8080/tutorApplication/update/${rowData.tutorApplicationID}?isAccepted=true`)
                    .then(response => {
                        this.errorTutorApplication = "";
                    })
                    .catch(e => {
                        var errorMsg = e.message;
                        console.log(errorMsg);
                        this.errorTutorApplication = errorMsg;
                    });
                alert("You clicked approve on: " + JSON.stringify(rowData));
                this.updateReviews();
            },
            declineRow(rowData) {
                AXIOS.patch(`http://localhost:8080/tutorApplication/update/${rowData.tutorApplicationID}?isAccepted=false`)
                    .then(response => {
                        this.errorTutorApplication = "";
                    })
                    .catch(e => {
                        var errorMsg = e.message;
                        console.log(errorMsg);
                        this.errorTutorApplication = errorMsg;
                    });
                alert("You clicked decline on: " + JSON.stringify(rowData));
                this.updateReviews();
            },
            dataManager(sortOrder, pagination) {
                if (this.tutorApplications.length < 1) return;

                let local = this.tutorApplications;

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
                let tutorApplication = this.tutorApplications[0];

                let data = this.tutorApplications.filter(tutorApplication => {
                    return (
                        tutorApplication.subject.toLowerCase().includes(filterText.toLowerCase())
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
            document.getElementsByName("search")[0].placeholder = "Search tutor application..."
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

  #tutorApplicationList {
    border-width: 5px;
    border-style: groove;
  }
</style>
