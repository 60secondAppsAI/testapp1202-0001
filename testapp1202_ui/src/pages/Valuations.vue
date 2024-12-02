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
            <valuation-table
            v-if="valuations && valuations.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:valuations="valuations"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-valuations="getAllValuations"
             >

            </valuation-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ValuationTable from "@/components/ValuationTable";
import ValuationService from "../services/ValuationService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ValuationTable,
  },
  data() {
    return {
      valuations: [],
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
    async getAllValuations(sortBy='valuationId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ValuationService.getAllValuations(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.valuations.length) {
					this.valuations = response.data.valuations;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching valuations:", error);
        }
        
      } catch (error) {
        console.error("Error fetching valuation details:", error);
      }
    },
  },
  mounted() {
    this.getAllValuations();
  },
  created() {
    this.$root.$on('searchQueryForValuationsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllValuations();
    })
  }
};
</script>
<style></style>
