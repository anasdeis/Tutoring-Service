<template>
  <div class="filter-bar pull-left">
    <div class="form-inline">
      <div class="form-group">
        <label><b>Search for:</b>&nbsp;&nbsp;</label>

        <input
          type="text"
          v-model="filterText"
          class="form-control"
          @keyup.enter="doFilter"
          name="search"
          placeholder=""
        />
        &nbsp;
        <button class="btn btn-primary" @click="doFilter" title="Filter Table!">Go</button>
        &nbsp;
        <button class="btn btn-default" @click="resetFilter" title="Reset Table!">Reset</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      filterText: ""
    };
  },

  methods: {
    doFilter() {
      this.$events.fire("filter-set", this.filterText);
    },

    resetFilter() {
      this.filterText = ""; // clear the text in text input
      this.$events.fire("filter-reset");
    }
  }
};
</script>

<style>
.filter-bar {
  margin-bottom: 8px;
}
</style>