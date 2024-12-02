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
            <deal-table
            v-if="deals && deals.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:deals="deals"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-deals="getAllDeals"
             >

            </deal-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import DealTable from "@/components/DealTable";
import DealService from "../services/DealService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    DealTable,
  },
  data() {
    return {
      deals: [],
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
    async getAllDeals(sortBy='dealId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await DealService.getAllDeals(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.deals.length) {
					this.deals = response.data.deals;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching deals:", error);
        }
        
      } catch (error) {
        console.error("Error fetching deal details:", error);
      }
    },
  },
  mounted() {
    this.getAllDeals();
  },
  created() {
    this.$root.$on('searchQueryForDealsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllDeals();
    })
  }
};
</script>
<style></style>
