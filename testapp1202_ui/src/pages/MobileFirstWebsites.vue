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
            <mobileFirstWebsite-table
            v-if="mobileFirstWebsites && mobileFirstWebsites.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:mobileFirstWebsites="mobileFirstWebsites"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-mobile-first-websites="getAllMobileFirstWebsites"
             >

            </mobileFirstWebsite-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import MobileFirstWebsiteTable from "@/components/MobileFirstWebsiteTable";
import MobileFirstWebsiteService from "../services/MobileFirstWebsiteService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MobileFirstWebsiteTable,
  },
  data() {
    return {
      mobileFirstWebsites: [],
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
    async getAllMobileFirstWebsites(sortBy='mobileFirstWebsiteId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MobileFirstWebsiteService.getAllMobileFirstWebsites(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.mobileFirstWebsites.length) {
					this.mobileFirstWebsites = response.data.mobileFirstWebsites;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching mobileFirstWebsites:", error);
        }
        
      } catch (error) {
        console.error("Error fetching mobileFirstWebsite details:", error);
      }
    },
  },
  mounted() {
    this.getAllMobileFirstWebsites();
  },
  created() {
    this.$root.$on('searchQueryForMobileFirstWebsitesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMobileFirstWebsites();
    })
  }
};
</script>
<style></style>
