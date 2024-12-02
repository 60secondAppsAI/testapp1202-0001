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
            <shippingDetails-table
            v-if="shippingDetailss && shippingDetailss.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:shippingDetailss="shippingDetailss"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-shipping-detailss="getAllShippingDetailss"
             >

            </shippingDetails-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ShippingDetailsTable from "@/components/ShippingDetailsTable";
import ShippingDetailsService from "../services/ShippingDetailsService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ShippingDetailsTable,
  },
  data() {
    return {
      shippingDetailss: [],
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
    async getAllShippingDetailss(sortBy='shippingDetailsId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ShippingDetailsService.getAllShippingDetailss(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.shippingDetailss.length) {
					this.shippingDetailss = response.data.shippingDetailss;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching shippingDetailss:", error);
        }
        
      } catch (error) {
        console.error("Error fetching shippingDetails details:", error);
      }
    },
  },
  mounted() {
    this.getAllShippingDetailss();
  },
  created() {
    this.$root.$on('searchQueryForShippingDetailssChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllShippingDetailss();
    })
  }
};
</script>
<style></style>
