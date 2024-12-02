
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

              <h5 class="card-title">Storages</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalStorages = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalStorages">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add Storage</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="StorageId" type="text" placeholder="Enter StorageId" v-model="storageToAdd.storageId"></base-input>
  <base-input label="StorageLocation" type="text" placeholder="Enter StorageLocation" v-model="storageToAdd.storageLocation"></base-input>
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
            <a-table :columns="columns" :data-source="storages" :row-key="record => record.StorageId" :pagination="pagination"
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
            <StoragePictureView :storages="storages" />
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
import StorageService from "../services/StorageService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import StoragePictureView from './StoragePictureView.vue';


const storagesColumns = [
  "storageId",
  "year",
  "date",
  "competitionId",
  "storageId"
]

export default {
  props: {
    storages: {
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
    StoragePictureView
  },

  data() {
    return {
      modalStorages: false,
        isTableView: true,

      columns: [
        {
          title: 'Storage Id',
		dataIndex: 'storageId',
          visible: true,
          scopedSlots: { customRender: 'storageId' },
          sorter: true
          //sorter: (a, b) => a.storageId - b.storageId,
          //sorter: (a, b) => a.storageId.localeCompare(b.storageId),
        },
        {
          title: 'Storage Location',
		dataIndex: 'storageLocation',
          visible: true,
          scopedSlots: { customRender: 'storageLocation' },
          sorter: true
          //sorter: (a, b) => a.storageLocation - b.storageLocation,
          //sorter: (a, b) => a.storageLocation.localeCompare(b.storageLocation),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} storages`,
      },

      storages: [],
      storageToAdd: {},

      storagesTable: {
        columns: [...storagesColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'storageId',           // Column to sort by
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

    async renderStoragesTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let storagesTableData = [];
      for (let i = 0; i < this.storages.length; i++) {
        storagesTableData.push({
          id: i,
          storageId: this.storages[i].storageId,
          year: this.storages[i].year,
          date: this.storages[i].date,
          competitionId: this.storages[i].competitionId,
          storageId: this.storages[i].storageId,
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

	  this.$emit('get-all-storages',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
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
    	this.$root.$emit('searchQueryForStoragesChanged', this.searchQuery);
		//this.renderStoragesTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalStorages = false;

      const currentDate = new Date().getTime();
      this.storageToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.storageToAdd);
      console.log(jsonData);
      
      const res = await StorageService.addStorage(jsonData);

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
    this.renderStoragesTable();
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
