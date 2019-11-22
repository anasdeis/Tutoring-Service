<template>
  <div id="studentReview" class="card" v-bind:style="{ backgroundColor : bgColor}">
    <span id="title" v-bind:style="{color : textColor}"></span>
    <div>
      <span id="title1"></span>
    </div>

    <b-container fluid>
      <b-col id="reviewlist">
        <p>View student review</p>
        <select
          class="reviewField"
          id="selectList"
          name="selectList"
          @click="PopulateDropDownList()"
        >
          <option value="select">--Select Review Status--</option>
          <option value="Approved">Approved</option>
          <option value="Declined">Declined</option>
          <option value="Pending">Pending</option>
          <option value="All">All</option>
        </select>
        <select class="reviewField" id="reviewList" name="reviewList">
          <option value="all">--Select Review--</option>
        </select>

        <!-- 
        <table id="reviewTable">
          <tr>
            <th>Review ID</th>
            <th>Comment</th>
            <th>Offering</th>
            <th>Approved</th>
          </tr>
          <tr w3-repeat="reviews">
            <td>{{reviewID}}</td>
            <td>{{comment}}</td>
            <td>{{offeringID}}</td>
            <td>{{isApproved}}</td>
          </tr>
        </table>
        -->

        <b-row id="myButton">
          <center>
            <button
              id="approve"
              type="button"
              @click="updateReviewIsApproved()"
              class="btn btn-primary btn-lg subjectField button"
              v-b-tooltip.hove
              title="Approve this review!"
            >Approve</button>
            <button
              id="decline"
              type="button"
              @click="declineReview()"
              class="btn btn-primary btn-lg tutorField button"
              v-b-tooltip.hove
              title="Decline this review!"
            >Decline</button>
          </center>
        </b-row>
      </b-col>
    </b-container>
  </div>
</template>


<script>
import axios from "axios";
import Router from "../router";

var config = require("../../config");

var frontendUrl = "http://" + config.build.host + ":" + config.build.port;
var backendUrl = "http://localhost:8080/";
  // "http://" + config.build.backendHost + ":" + config.build.backendPort;

// axios config
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

function ReviewDto(reviewId, comment, isApproved) {
  this.reviewId = reviewId;
  this.comment = comment;
  this.isApproved = isApproved;
}
/*
script src="https://www.w3schools.com/lib/w3.js"
var reviewTable = {
  reviews: [
    {
      reviewID: "1000",
      comment: "Amazing Course",
      offeringID: "ECSE321F",
      isApproved: true
    },
    {
      reviewID: "1001",
      comment: "So hard",
      offeringID: "ECSE321F",
      isApproved: true
    },
    {
      reviewID: "1002",
      comment: "I don't like this",
      offeringID: "ECSE321F",
      isApproved: false
    }
  ]
};
*/
// studentReview.displayObject("reviewTable", reviewTable);

export default {
  name: "reviews",
  data() {
    return {
      reviews: [],
      reviewId: "",
      comment: "",
      isApproved: null,
      errorReview: "",
      response: [],
      bgColor: "",
      textColor: ""
    };
  },

  created: function() {
    // Test data
    const r1 = new ReviewDto("1", "I loved the tutor!", false);
    const r2 = new ReviewDto("2", "Great!", false);
    const r3 = new ReviewDto("3", "Amazing course!", null);
    const r4 = new ReviewDto("4", "Easy tutorial!", true);
    // Sample initial content
    this.reviews = [r1, r2, r3, r4];

    var darkModeOn = localStorage.getItem("DarkModeOn");
    if (darkModeOn === "true") {
      this.bgColor = "rgb(53,58,62)";
      this.textColor = "white";
      this.buttonClass = "btn btn-dark btn-lg signupField";
    } else {
      this.bgColor = "rgb(250,250,250)";
      this.textColor = "black";
      // this.bgColor = "rgb(248, 249, 251)";
      this.buttonClass = "btn btn-white btn-lg signupField";
    }
  },
  methods: {
    getAllReviews: function() {
      /*
     AXIOS.get(`/review/list`).then(response => {
      // JSON responses are automatically parsed.
      this.reviews = response.data
    })
    .catch(e => {
      this.errorReview = e;
    });*/
    },
    updateReviewIsApproved: function() {
      /*  AXIOS.patch(`/review/update/approved/${reviewId}?$isApproved=true`).then(response => {
      // JSON responses are automatically parsed.
      this.reviews = response.data
    })
    .catch(e => {
      this.errorReview = e;
    });*/
      if (reviewList.options.length > 1 && reviewList.selectedIndex > 0) {
        var index = -1;
        for (var i = 0; i < this.reviews.length; i++) {
          if (
            this.reviews[i].reviewId ===
            reviewList.options[reviewList.selectedIndex].text
          ) {
            index = i;
            break;
          }
        }
        window.alert(
          "ReviewID: " +
            reviewList.options[reviewList.selectedIndex].text +
            " Approval status: " +
            reviewList.options[reviewList.selectedIndex].value +
            " is approved!"
        );
        this.reviews[index].isApproved = true;
      } else {
        window.alert("Select a review to approve!");
      }
    },
    declineReview: function() {
      /*  AXIOS.patch(`/review/update/approved/${reviewId}?$isApproved=true`).then(response => {
      // JSON responses are automatically parsed.
      this.reviews = response.data
    })
    .catch(e => {
      this.errorReview = e;
    });*/
      if (reviewList.options.length > 1 && reviewList.selectedIndex > 0) {
        var index = -1;
        for (var i = 0; i < this.reviews.length; i++) {
          if (
            this.reviews[i].reviewId ===
            reviewList.options[reviewList.selectedIndex].text
          ) {
            index = i;
            break;
          }
        }
        window.alert(
          "ReviewID: " +
            reviewList.options[reviewList.selectedIndex].text +
            " Approval status: " +
            reviewList.options[reviewList.selectedIndex].value +
            " is declined!" +
            index
        );
        this.reviews[index].isApproved = false;
      } else {
        window.alert("Select a review to decline!");
      }
    },
    PopulateDropDownList: function() {
      //this.getAllReviews()

      var selectList = document.getElementById("selectList");
      var reviewList = document.getElementById("reviewList");
      var list1SelectedValue =
        selectList.options[selectList.selectedIndex].value;
      if (list1SelectedValue === "select") {
        window.alert("Select a review status to populate review list!");
      } else {
        reviewList.options.length = 1;
        for (var i = 0; i < this.reviews.length; i++) {
          if (
            (list1SelectedValue === "Approved" &&
              this.reviews[i].isApproved === true) ||
            (list1SelectedValue == "Declined" &&
              this.reviews[i].isApproved === false) ||
            (list1SelectedValue === "Pending" &&
              this.reviews[i].isApproved == null) ||
            list1SelectedValue === "All"
          ) {
            var option = document.createElement("OPTION");
            option.innerHTML = this.reviews[i].reviewId;
            option.value = this.reviews[i].isApproved;
            reviewList.options.add(option);
          }
        }
      }
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
  }
};
</script>

<style>
#reviewTable {
  margin-top: 20px;
  border-width: 5px;
  border-style: groove;
  margin-left: auto;
  margin-right: auto;
}
table,
th,
td {
  border: 1px solid black;
}
form {
  color: black;
}
#myButton {
  margin-left: auto;
  margin-right: auto;
  margin-top: 5px;
}
#approve {
  margin-right: 5px;
  margin-left: 5px;
}
#decline {
  margin-right: 5px;
  margin-left: 5px;
}
</style>