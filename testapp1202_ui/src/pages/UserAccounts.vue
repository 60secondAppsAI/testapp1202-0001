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
            <userAccount-table
            v-if="userAccounts && userAccounts.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:userAccounts="userAccounts"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-user-accounts="getAllUserAccounts"
             >

            </userAccount-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import UserAccountTable from "@/components/UserAccountTable";
import UserAccountService from "../services/UserAccountService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    UserAccountTable,
  },
  data() {
    return {
      userAccounts: [],
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
    async getAllUserAccounts(sortBy='userAccountId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await UserAccountService.getAllUserAccounts(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.userAccounts.length) {
					this.userAccounts = response.data.userAccounts;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching userAccounts:", error);
        }
        
      } catch (error) {
        console.error("Error fetching userAccount details:", error);
      }
    },
  },
  mounted() {
    this.getAllUserAccounts();
  },
  created() {
    this.$root.$on('searchQueryForUserAccountsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllUserAccounts();
    })
  }
};
</script>
<style></style>
