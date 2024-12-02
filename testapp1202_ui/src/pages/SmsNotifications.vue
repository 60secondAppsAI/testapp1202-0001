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
            <smsNotification-table
            v-if="smsNotifications && smsNotifications.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:smsNotifications="smsNotifications"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-sms-notifications="getAllSmsNotifications"
             >

            </smsNotification-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SmsNotificationTable from "@/components/SmsNotificationTable";
import SmsNotificationService from "../services/SmsNotificationService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SmsNotificationTable,
  },
  data() {
    return {
      smsNotifications: [],
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
    async getAllSmsNotifications(sortBy='smsNotificationId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SmsNotificationService.getAllSmsNotifications(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.smsNotifications.length) {
					this.smsNotifications = response.data.smsNotifications;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching smsNotifications:", error);
        }
        
      } catch (error) {
        console.error("Error fetching smsNotification details:", error);
      }
    },
  },
  mounted() {
    this.getAllSmsNotifications();
  },
  created() {
    this.$root.$on('searchQueryForSmsNotificationsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSmsNotifications();
    })
  }
};
</script>
<style></style>
