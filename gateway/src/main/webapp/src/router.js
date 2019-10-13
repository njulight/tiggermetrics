import Vue from 'vue';
import Router from 'vue-router';
import Account from './components/Account.vue'
import AccountDetail from './components/AccountDetail.vue'

Vue.use(Router);

const routes = [{
	path: '*',
	redirect: '/account'
}, {
	path: '/account',
	name: 'Account',
	component: Account,
	meta: {
		title: '账户'
	}
}, {
	path: '/account-detail',
	name: 'AccountDetail',
	component: AccountDetail,
	meta: {
		title: '账户详情'
	}
}];

const router = new Router({
	routes
});

router.beforeEach((to, from, next) => {
	const title = to.meta && to.meta.title;
	if (title) {
		document.title = title;
	}
	next();
});

export {
	router
};
