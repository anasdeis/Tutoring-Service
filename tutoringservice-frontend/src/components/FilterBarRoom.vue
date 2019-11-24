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
          placeholder="Input Room ID"
        />
        &nbsp;
        <button class="btn btn-primary" @click="doFilter">Find</button>
        &nbsp;
        <button class="btn btn-primary" @click="resetFilter">Reset Table</button>
        &nbsp;
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