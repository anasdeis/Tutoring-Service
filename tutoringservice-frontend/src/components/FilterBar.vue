<template>
  <div class="filter-bar pull-left">
    <div class="form-inline">
      <div class="form-group">
        <table>
          <tbody>
            <tr>
              <td>
                <label style="padding-bottom:5px">
                  <b>Search for:</b>&nbsp;&nbsp;
                </label>
              </td>
              <input
                type="text"
                v-model="filterText"
                class="form-control"
                @keyup.enter="doFilter"
                name="search"
                placeholder
              />
              <td>
                &nbsp;
                <button class="btn btn-primary go" @click="doFilter" title="Filter Table!">Go</button>
              </td>&nbsp;
              <td>
                <button class="btn btn-light reset" @click="resetFilter" title="Reset Table!">Reset</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FilterBar",
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
  margin-bottom: 4px;
}

.go {
  display: inline-block;
  width: 48px;
}

.reset {
  display: inline-block;
  width: 68px;
}
</style>