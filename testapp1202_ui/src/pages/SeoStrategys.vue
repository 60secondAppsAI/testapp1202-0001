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
            <seoStrategy-table
            v-if="seoStrategys && seoStrategys.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:seoStrategys="seoStrategys"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-seo-strategys="getAllSeoStrategys"
             >

            </seoStrategy-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SeoStrategyTable from "@/components/SeoStrategyTable";
import SeoStrategyService from "../services/SeoStrategyService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SeoStrategyTable,
  },
  data() {
    return {
      seoStrategys: [],
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
    async getAllSeoStrategys(sortBy='seoStrategyId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SeoStrategyService.getAllSeoStrategys(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.seoStrategys.length) {
					this.seoStrategys = response.data.seoStrategys;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching seoStrategys:", error);
        }
        
      } catch (error) {
        console.error("Error fetching seoStrategy details:", error);
      }
    },
  },
  mounted() {
    this.getAllSeoStrategys();
  },
  created() {
    this.$root.$on('searchQueryForSeoStrategysChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSeoStrategys();
    })
  }
};
</script>
<style></style>
