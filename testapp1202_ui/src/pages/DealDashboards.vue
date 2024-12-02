<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <dealDashboard-table
            v-if="dealDashboards && dealDashboards.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:dealDashboards="dealDashboards"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-deal-dashboards="getAllDealDashboards"
             >

            </dealDashboard-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import DealDashboardTable from "@/components/DealDashboardTable";
import DealDashboardService from "../services/DealDashboardService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    DealDashboardTable,
  },
  data() {
    return {
      dealDashboards: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllDealDashboards(sortBy='dealDashboardId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await DealDashboardService.getAllDealDashboards(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.dealDashboards.length) {
					this.dealDashboards = response.data.dealDashboards;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching dealDashboards:", error);
        }
        
      } catch (error) {
        console.error("Error fetching dealDashboard details:", error);
      }
    },
  },
  mounted() {
    this.getAllDealDashboards();
  },
  created() {
    this.$root.$on('searchQueryForDealDashboardsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllDealDashboards();
    })
  }
};
</script>
<style></style>
