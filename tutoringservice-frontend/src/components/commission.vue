<template>
  <div>
    <b-container fluid>
      <b-row id="functionality">
        <b-col>
         test 
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";
import Router from "../router";

var config = require("../../config");

var frontendUrl = "http://" + config.build.host + ":" + config.build.port;
var backendUrl =
  "http://" + config.build.backendHost + ":" + config.build.backendPort;

// axios config
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {};
</script>

<style>
#functionlity {
  margin-top: 40px;

  padding-bottom: 40px;
}
</style>