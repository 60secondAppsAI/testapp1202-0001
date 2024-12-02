import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Customers from  '@/pages/Customers.vue';
import CustomerDetail from  '@/pages/CustomerDetail.vue';
import UserAccounts from  '@/pages/UserAccounts.vue';
import UserAccountDetail from  '@/pages/UserAccountDetail.vue';
import Deals from  '@/pages/Deals.vue';
import DealDetail from  '@/pages/DealDetail.vue';
import DealDashboards from  '@/pages/DealDashboards.vue';
import DealDashboardDetail from  '@/pages/DealDashboardDetail.vue';
import DealRepresentatives from  '@/pages/DealRepresentatives.vue';
import DealRepresentativeDetail from  '@/pages/DealRepresentativeDetail.vue';
import Notifications from  '@/pages/Notifications.vue';
import NotificationDetail from  '@/pages/NotificationDetail.vue';
import PreliminaryDiscussions from  '@/pages/PreliminaryDiscussions.vue';
import PreliminaryDiscussionDetail from  '@/pages/PreliminaryDiscussionDetail.vue';
import ShippingDetailss from  '@/pages/ShippingDetailss.vue';
import ShippingDetailsDetail from  '@/pages/ShippingDetailsDetail.vue';
import ItemHandlings from  '@/pages/ItemHandlings.vue';
import ItemHandlingDetail from  '@/pages/ItemHandlingDetail.vue';
import Valuations from  '@/pages/Valuations.vue';
import ValuationDetail from  '@/pages/ValuationDetail.vue';
import Offers from  '@/pages/Offers.vue';
import OfferDetail from  '@/pages/OfferDetail.vue';
import Agreements from  '@/pages/Agreements.vue';
import AgreementDetail from  '@/pages/AgreementDetail.vue';
import Payments from  '@/pages/Payments.vue';
import PaymentDetail from  '@/pages/PaymentDetail.vue';
import Storages from  '@/pages/Storages.vue';
import StorageDetail from  '@/pages/StorageDetail.vue';
import Repayments from  '@/pages/Repayments.vue';
import RepaymentDetail from  '@/pages/RepaymentDetail.vue';
import EmailNotifications from  '@/pages/EmailNotifications.vue';
import EmailNotificationDetail from  '@/pages/EmailNotificationDetail.vue';
import SmsNotifications from  '@/pages/SmsNotifications.vue';
import SmsNotificationDetail from  '@/pages/SmsNotificationDetail.vue';
import DocumentStores from  '@/pages/DocumentStores.vue';
import DocumentStoreDetail from  '@/pages/DocumentStoreDetail.vue';
import SeoStrategys from  '@/pages/SeoStrategys.vue';
import SeoStrategyDetail from  '@/pages/SeoStrategyDetail.vue';
import MobileFirstWebsites from  '@/pages/MobileFirstWebsites.vue';
import MobileFirstWebsiteDetail from  '@/pages/MobileFirstWebsiteDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/customers',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/customers',
		name: 'Customers',
		layout: DefaultLayout,
		component: Customers,
	},
	{
	    path: '/customer/:customerId', 
	    name: 'CustomerDetail',
		layout: DefaultLayout,
	    component: CustomerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/userAccounts',
		name: 'UserAccounts',
		layout: DefaultLayout,
		component: UserAccounts,
	},
	{
	    path: '/userAccount/:userAccountId', 
	    name: 'UserAccountDetail',
		layout: DefaultLayout,
	    component: UserAccountDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/deals',
		name: 'Deals',
		layout: DefaultLayout,
		component: Deals,
	},
	{
	    path: '/deal/:dealId', 
	    name: 'DealDetail',
		layout: DefaultLayout,
	    component: DealDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/dealDashboards',
		name: 'DealDashboards',
		layout: DefaultLayout,
		component: DealDashboards,
	},
	{
	    path: '/dealDashboard/:dealDashboardId', 
	    name: 'DealDashboardDetail',
		layout: DefaultLayout,
	    component: DealDashboardDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/dealRepresentatives',
		name: 'DealRepresentatives',
		layout: DefaultLayout,
		component: DealRepresentatives,
	},
	{
	    path: '/dealRepresentative/:dealRepresentativeId', 
	    name: 'DealRepresentativeDetail',
		layout: DefaultLayout,
	    component: DealRepresentativeDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/notifications',
		name: 'Notifications',
		layout: DefaultLayout,
		component: Notifications,
	},
	{
	    path: '/notification/:notificationId', 
	    name: 'NotificationDetail',
		layout: DefaultLayout,
	    component: NotificationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/preliminaryDiscussions',
		name: 'PreliminaryDiscussions',
		layout: DefaultLayout,
		component: PreliminaryDiscussions,
	},
	{
	    path: '/preliminaryDiscussion/:preliminaryDiscussionId', 
	    name: 'PreliminaryDiscussionDetail',
		layout: DefaultLayout,
	    component: PreliminaryDiscussionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/shippingDetailss',
		name: 'ShippingDetailss',
		layout: DefaultLayout,
		component: ShippingDetailss,
	},
	{
	    path: '/shippingDetails/:shippingDetailsId', 
	    name: 'ShippingDetailsDetail',
		layout: DefaultLayout,
	    component: ShippingDetailsDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/itemHandlings',
		name: 'ItemHandlings',
		layout: DefaultLayout,
		component: ItemHandlings,
	},
	{
	    path: '/itemHandling/:itemHandlingId', 
	    name: 'ItemHandlingDetail',
		layout: DefaultLayout,
	    component: ItemHandlingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/valuations',
		name: 'Valuations',
		layout: DefaultLayout,
		component: Valuations,
	},
	{
	    path: '/valuation/:valuationId', 
	    name: 'ValuationDetail',
		layout: DefaultLayout,
	    component: ValuationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/offers',
		name: 'Offers',
		layout: DefaultLayout,
		component: Offers,
	},
	{
	    path: '/offer/:offerId', 
	    name: 'OfferDetail',
		layout: DefaultLayout,
	    component: OfferDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/agreements',
		name: 'Agreements',
		layout: DefaultLayout,
		component: Agreements,
	},
	{
	    path: '/agreement/:agreementId', 
	    name: 'AgreementDetail',
		layout: DefaultLayout,
	    component: AgreementDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/payments',
		name: 'Payments',
		layout: DefaultLayout,
		component: Payments,
	},
	{
	    path: '/payment/:paymentId', 
	    name: 'PaymentDetail',
		layout: DefaultLayout,
	    component: PaymentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/storages',
		name: 'Storages',
		layout: DefaultLayout,
		component: Storages,
	},
	{
	    path: '/storage/:storageId', 
	    name: 'StorageDetail',
		layout: DefaultLayout,
	    component: StorageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/repayments',
		name: 'Repayments',
		layout: DefaultLayout,
		component: Repayments,
	},
	{
	    path: '/repayment/:repaymentId', 
	    name: 'RepaymentDetail',
		layout: DefaultLayout,
	    component: RepaymentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/emailNotifications',
		name: 'EmailNotifications',
		layout: DefaultLayout,
		component: EmailNotifications,
	},
	{
	    path: '/emailNotification/:emailNotificationId', 
	    name: 'EmailNotificationDetail',
		layout: DefaultLayout,
	    component: EmailNotificationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/smsNotifications',
		name: 'SmsNotifications',
		layout: DefaultLayout,
		component: SmsNotifications,
	},
	{
	    path: '/smsNotification/:smsNotificationId', 
	    name: 'SmsNotificationDetail',
		layout: DefaultLayout,
	    component: SmsNotificationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/documentStores',
		name: 'DocumentStores',
		layout: DefaultLayout,
		component: DocumentStores,
	},
	{
	    path: '/documentStore/:documentStoreId', 
	    name: 'DocumentStoreDetail',
		layout: DefaultLayout,
	    component: DocumentStoreDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/seoStrategys',
		name: 'SeoStrategys',
		layout: DefaultLayout,
		component: SeoStrategys,
	},
	{
	    path: '/seoStrategy/:seoStrategyId', 
	    name: 'SeoStrategyDetail',
		layout: DefaultLayout,
	    component: SeoStrategyDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/mobileFirstWebsites',
		name: 'MobileFirstWebsites',
		layout: DefaultLayout,
		component: MobileFirstWebsites,
	},
	{
	    path: '/mobileFirstWebsite/:mobileFirstWebsiteId', 
	    name: 'MobileFirstWebsiteDetail',
		layout: DefaultLayout,
	    component: MobileFirstWebsiteDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
