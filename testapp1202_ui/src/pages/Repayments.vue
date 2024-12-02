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
            <repayment-table
            v-if="repayments && repayments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:repayments="repayments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-repayments="getAllRepayments"
             >

            </repayment-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import RepaymentTable from "@/components/RepaymentTable";
import RepaymentService from "../services/RepaymentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    RepaymentTable,
  },
  data() {
    return {
      repayments: [],
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
    async getAllRepayments(sortBy='repaymentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await RepaymentService.getAllRepayments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.repayments.length) {
					this.repayments = response.data.repayments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching repayments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching repayment details:", error);
      }
    },
  },
  mounted() {
    this.getAllRepayments();
  },
  created() {
    this.$root.$on('searchQueryForRepaymentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllRepayments();
    })
  }
};
</script>
<style></style>
