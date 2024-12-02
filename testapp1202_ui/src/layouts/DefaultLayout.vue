<template>
  <a-layout style="min-height: 100vh">
    <!-- Header -->
    <a-layout-header style="background: #fff; padding: 0">
      <app-header />
    </a-layout-header>

    <!-- Main layout with sider and content -->
    <a-layout>
      <!-- Collapsible Sider with Controlled Open Keys for Nested Menu -->
      <a-layout-sider width="200px" collapsible style="background: #fff">
        <a-menu
          mode="inline"
          :openKeys="openKeys"
          @openChange="onOpenChange"
          style="height: 100%; border-right: 0"
        >
<!--          <a-sub-menu key="sub1" title="Menu 1">
            <a-menu-item key="1">Option 1</a-menu-item>
            <a-menu-item key="2">Option 2</a-menu-item>
            <a-sub-menu key="sub1-1" title="Nested Menu">
              <a-menu-item key="3">Option 3</a-menu-item>
              <a-menu-item key="4">Option 4</a-menu-item>
            </a-sub-menu>
          </a-sub-menu>
          <a-sub-menu key="sub2" title="Menu 2">
            <a-menu-item key="5">Option 5</a-menu-item>
            <a-menu-item key="6">Option 6</a-menu-item>
          </a-sub-menu>
-->>

	 
			
				<a-menu-item>
					<router-link to="/customers">
						<span class="label">Customers</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/userAccounts">
						<span class="label">User Accounts</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/deals">
						<span class="label">Deals</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/dealDashboards">
						<span class="label">Deal Dashboards</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/dealRepresentatives">
						<span class="label">Deal Representatives</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/notifications">
						<span class="label">Notifications</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/preliminaryDiscussions">
						<span class="label">Preliminary Discussions</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/shippingDetailss">
						<span class="label">Shipping Detailss</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/itemHandlings">
						<span class="label">Item Handlings</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/valuations">
						<span class="label">Valuations</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/offers">
						<span class="label">Offers</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/agreements">
						<span class="label">Agreements</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/payments">
						<span class="label">Payments</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/storages">
						<span class="label">Storages</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/repayments">
						<span class="label">Repayments</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/emailNotifications">
						<span class="label">Email Notifications</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/smsNotifications">
						<span class="label">Sms Notifications</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/documentStores">
						<span class="label">Document Stores</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/seoStrategys">
						<span class="label">Seo Strategys</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/mobileFirstWebsites">
						<span class="label">Mobile First Websites</span>
					</router-link>
				</a-menu-item>
		
	
        </a-menu>
      </a-layout-sider>
      
      <!-- Content area -->
      <a-layout-content style="padding: 16px">
        <router-view />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
import { ref } from "vue";
import AppHeader from "@/components/AppHeader.vue";

export default {
  name: "DefaultLayout",
  components: {
    AppHeader,
  },
  setup() {
    const openKeys = ref(["sub1"]); // default open submenu
    const onOpenChange = (keys) => {
      // Open only the last clicked submenu, for single open behavior
      openKeys.value = keys.length ? [keys[keys.length - 1]] : [];
    };

    return {
      openKeys,
      onOpenChange,
    };
  },
};
</script>
<style>
.ant-layout {
    margin-top: 20px;
}
</style>

