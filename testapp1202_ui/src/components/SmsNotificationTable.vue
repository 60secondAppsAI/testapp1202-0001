
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3 justify-content-end">
      <div class="col-8"></div>
       <div class="col-auto">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Sms Notifications</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalSmsNotifications = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalSmsNotifications">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add SmsNotification</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="SmsNotificationId" type="text" placeholder="Enter SmsNotificationId" v-model="smsNotificationToAdd.smsNotificationId"></base-input>
  <base-input label="Message" type="text" placeholder="Enter Message" v-model="smsNotificationToAdd.message"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="smsNotifications" :row-key="record => record.SmsNotificationId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <SmsNotificationPictureView :smsNotifications="smsNotifications" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/Card";
import SmsNotificationService from "../services/SmsNotificationService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import SmsNotificationPictureView from './SmsNotificationPictureView.vue';


const smsNotificationsColumns = [
  "smsNotificationId",
  "year",
  "date",
  "competitionId",
  "smsNotificationId"
]

export default {
  props: {
    smsNotifications: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    SmsNotificationPictureView
  },

  data() {
    return {
      modalSmsNotifications: false,
        isTableView: true,

      columns: [
        {
          title: 'Sms Notification Id',
		dataIndex: 'smsNotificationId',
          visible: true,
          scopedSlots: { customRender: 'smsNotificationId' },
          sorter: true
          //sorter: (a, b) => a.smsNotificationId - b.smsNotificationId,
          //sorter: (a, b) => a.smsNotificationId.localeCompare(b.smsNotificationId),
        },
        {
          title: 'Message',
		dataIndex: 'message',
          visible: true,
          scopedSlots: { customRender: 'message' },
          sorter: true
          //sorter: (a, b) => a.message - b.message,
          //sorter: (a, b) => a.message.localeCompare(b.message),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} smsNotifications`,
      },

      smsNotifications: [],
      smsNotificationToAdd: {},

      smsNotificationsTable: {
        columns: [...smsNotificationsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'smsNotificationId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderSmsNotificationsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let smsNotificationsTableData = [];
      for (let i = 0; i < this.smsNotifications.length; i++) {
        smsNotificationsTableData.push({
          id: i,
          smsNotificationId: this.smsNotifications[i].smsNotificationId,
          year: this.smsNotifications[i].year,
          date: this.smsNotifications[i].date,
          competitionId: this.smsNotifications[i].competitionId,
          smsNotificationId: this.smsNotifications[i].smsNotificationId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-sms-notifications',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToCustomerDetail(id) {
      this.$router.push({ name: 'CustomerDetail', params: { customerId: id.toString() }})
    },
    routingToUserAccountDetail(id) {
      this.$router.push({ name: 'UserAccountDetail', params: { userAccountId: id.toString() }})
    },
    routingToDealDetail(id) {
      this.$router.push({ name: 'DealDetail', params: { dealId: id.toString() }})
    },
    routingToDealDashboardDetail(id) {
      this.$router.push({ name: 'DealDashboardDetail', params: { dealDashboardId: id.toString() }})
    },
    routingToDealRepresentativeDetail(id) {
      this.$router.push({ name: 'DealRepresentativeDetail', params: { dealRepresentativeId: id.toString() }})
    },
    routingToNotificationDetail(id) {
      this.$router.push({ name: 'NotificationDetail', params: { notificationId: id.toString() }})
    },
    routingToPreliminaryDiscussionDetail(id) {
      this.$router.push({ name: 'PreliminaryDiscussionDetail', params: { preliminaryDiscussionId: id.toString() }})
    },
    routingToShippingDetailsDetail(id) {
      this.$router.push({ name: 'ShippingDetailsDetail', params: { shippingDetailsId: id.toString() }})
    },
    routingToItemHandlingDetail(id) {
      this.$router.push({ name: 'ItemHandlingDetail', params: { itemHandlingId: id.toString() }})
    },
    routingToValuationDetail(id) {
      this.$router.push({ name: 'ValuationDetail', params: { valuationId: id.toString() }})
    },
    routingToOfferDetail(id) {
      this.$router.push({ name: 'OfferDetail', params: { offerId: id.toString() }})
    },
    routingToAgreementDetail(id) {
      this.$router.push({ name: 'AgreementDetail', params: { agreementId: id.toString() }})
    },
    routingToPaymentDetail(id) {
      this.$router.push({ name: 'PaymentDetail', params: { paymentId: id.toString() }})
    },
    routingToStorageDetail(id) {
      this.$router.push({ name: 'StorageDetail', params: { storageId: id.toString() }})
    },
    routingToRepaymentDetail(id) {
      this.$router.push({ name: 'RepaymentDetail', params: { repaymentId: id.toString() }})
    },
    routingToEmailNotificationDetail(id) {
      this.$router.push({ name: 'EmailNotificationDetail', params: { emailNotificationId: id.toString() }})
    },
    routingToSmsNotificationDetail(id) {
      this.$router.push({ name: 'SmsNotificationDetail', params: { smsNotificationId: id.toString() }})
    },
    routingToDocumentStoreDetail(id) {
      this.$router.push({ name: 'DocumentStoreDetail', params: { documentStoreId: id.toString() }})
    },
    routingToSeoStrategyDetail(id) {
      this.$router.push({ name: 'SeoStrategyDetail', params: { seoStrategyId: id.toString() }})
    },
    routingToMobileFirstWebsiteDetail(id) {
      this.$router.push({ name: 'MobileFirstWebsiteDetail', params: { mobileFirstWebsiteId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForSmsNotificationsChanged', this.searchQuery);
		//this.renderSmsNotificationsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalSmsNotifications = false;

      const currentDate = new Date().getTime();
      this.smsNotificationToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.smsNotificationToAdd);
      console.log(jsonData);
      
      const res = await SmsNotificationService.addSmsNotification(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderSmsNotificationsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}
.header-search {
  width: 300px !important;
  margin-left: auto !important;
}
</style>
