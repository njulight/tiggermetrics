import Vue from 'vue';
import Router from 'vue-router';
import Account from './components/Account.vue'
import AccountDetail from './components/AccountDetail.vue'
import AddAccount from './components/AddAccount.vue'
import EditAccount from './components/EditAccount.vue'
import AddIncome from './components/AddIncome.vue'
import AddExpense from './components/AddExpense.vue'

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
	path: '/add-account',
	name: 'AddAccount',
	component: AddAccount,
	meta: {
		title: '添加账户'
	}
}, {
	path: '/add-income',
	name: 'AddIncome',
	component: AddIncome,
	meta: {
		title: '添加收入'
	}
}, {
	path: '/add-expense',
	name: 'AddExpense',
	component: AddExpense,
	meta: {
		title: '添加支出'
	}
}, {
	path: '/edit-account',
	name: 'EditAccount',
	component: EditAccount,
	meta: {
		title: '编辑账户'
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
