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
            <documentStore-table
            v-if="documentStores && documentStores.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:documentStores="documentStores"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-document-stores="getAllDocumentStores"
             >

            </documentStore-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import DocumentStoreTable from "@/components/DocumentStoreTable";
import DocumentStoreService from "../services/DocumentStoreService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    DocumentStoreTable,
  },
  data() {
    return {
      documentStores: [],
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
    async getAllDocumentStores(sortBy='documentStoreId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await DocumentStoreService.getAllDocumentStores(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.documentStores.length) {
					this.documentStores = response.data.documentStores;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching documentStores:", error);
        }
        
      } catch (error) {
        console.error("Error fetching documentStore details:", error);
      }
    },
  },
  mounted() {
    this.getAllDocumentStores();
  },
  created() {
    this.$root.$on('searchQueryForDocumentStoresChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllDocumentStores();
    })
  }
};
</script>
<style></style>
