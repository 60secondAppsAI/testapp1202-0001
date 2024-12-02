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
            <agreement-table
            v-if="agreements && agreements.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:agreements="agreements"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-agreements="getAllAgreements"
             >

            </agreement-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import AgreementTable from "@/components/AgreementTable";
import AgreementService from "../services/AgreementService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AgreementTable,
  },
  data() {
    return {
      agreements: [],
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
    async getAllAgreements(sortBy='agreementId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AgreementService.getAllAgreements(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.agreements.length) {
					this.agreements = response.data.agreements;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching agreements:", error);
        }
        
      } catch (error) {
        console.error("Error fetching agreement details:", error);
      }
    },
  },
  mounted() {
    this.getAllAgreements();
  },
  created() {
    this.$root.$on('searchQueryForAgreementsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllAgreements();
    })
  }
};
</script>
<style></style>
