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
            <dealRepresentative-table
            v-if="dealRepresentatives && dealRepresentatives.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:dealRepresentatives="dealRepresentatives"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-deal-representatives="getAllDealRepresentatives"
             >

            </dealRepresentative-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import DealRepresentativeTable from "@/components/DealRepresentativeTable";
import DealRepresentativeService from "../services/DealRepresentativeService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    DealRepresentativeTable,
  },
  data() {
    return {
      dealRepresentatives: [],
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
    async getAllDealRepresentatives(sortBy='dealRepresentativeId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await DealRepresentativeService.getAllDealRepresentatives(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.dealRepresentatives.length) {
					this.dealRepresentatives = response.data.dealRepresentatives;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching dealRepresentatives:", error);
        }
        
      } catch (error) {
        console.error("Error fetching dealRepresentative details:", error);
      }
    },
  },
  mounted() {
    this.getAllDealRepresentatives();
  },
  created() {
    this.$root.$on('searchQueryForDealRepresentativesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllDealRepresentatives();
    })
  }
};
</script>
<style></style>
