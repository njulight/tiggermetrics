<template>
	<div>
		<van-nav-bar title="账户详情" left-text="返回" right-text="编辑" left-arrow @click-left="onClickLeft" @click-right="onClickRight" />
		<van-nav-bar title="基本信息" />
		<van-cell title="账户名" :value="account.name" />
		<van-cell title="备注" :value="account.note" />
		<van-nav-bar title="收入" @click-right="onIncomeClickRight">
			<van-icon name="add-o" slot="right" />
		</van-nav-bar>
		<van-cell-group v-for="item in incomeList" :key="item" :title="item.title" :icon="item.icon">
			<van-cell title="金额" :value="item.amount+ ' ' +item.currency" />
			<van-cell title="时间" :value="item.itemTime" />
		</van-cell-group>

		<van-nav-bar title="支出" @click-right="onExpenseClickRight">
			<van-icon name="add-o" slot="right" />
		</van-nav-bar>
		<van-cell-group v-for="item in expenseList" :key="item" :title="item.title" :icon="item.icon">
			<van-cell title="金额" :value="item.amount+ ' ' +item.currency" />
			<van-cell title="时间" :value="item.itemTime" />
		</van-cell-group>
		<van-nav-bar title="结余" />
		<van-cell-group v-for="item in savingList" :key="item" :title="item.title">
			<van-cell title="金额" :value="item.amount+ ' ' +item.currency" />
		</van-cell-group>
		<van-cell title="总计" :value="account.amount+ ' ' +account.currency" />
	</div>
</template>

<script>
	import Vue from 'vue';
	import {
		NavBar,
		Cell,
		CellGroup
	} from 'vant';

	Vue.use(NavBar).use(Cell).use(CellGroup);
	export default {
		data() {
			return {
				account: {},
				incomeList: [],
				expenseList: [],
				savingList: []
			};
		},
		mounted: function() {
			if (this.$route.params.id == null) {
				this.$router.push({
					name: 'Account'
				})
				return;
			}
			var uuid = this.$route.params.id;
			this.queryAccountDetail(uuid);
			this.queryAccountStat(uuid);
		},
		methods: {
			onClickLeft() {
				this.$router.push({
					name: 'Account'
				})
			},
			onClickRight() {
				this.$router.push({
					name: 'EditAccount',
					params: {
						id: this.$route.params.id,
						name: this.account.name,
						note: this.account.note
					}
				})
			},
			onIncomeClickRight() {
				this.$router.push({
					name: 'AddIncome',
					params: {
						id: this.$route.params.id,
						name: this.account.name,
						note: this.account.note
					}
				})
			},
			onExpenseClickRight() {
				this.$router.push({
					name: 'AddExpense',
					params: {
						id: this.$route.params.id,
						name: this.account.name,
						note: this.account.note
					}
				})
			},
			queryAccountDetail: function(uuid) {
				this.$axios.get('/accout-service/' + uuid)
					.then(response => {
						Vue.set(this.account, 'name', response.data.name);
						Vue.set(this.account, 'note', response.data.note);
						if (response.data.incomes != null) {
							response.data.incomes.forEach(item => {
								this.incomeList.push(item);
							});
						}
						if (response.data.expenses != null) {
							response.data.expenses.forEach(item => {
								this.expenseList.push(item);
							});
						}
						if (response.data.saving != null) {
							response.data.saving.forEach(item => {
								this.savingList.push(item);
							});
						}
					}).catch(error => {
						alert(error);
					});
			},
			queryAccountStat: function(uuid) {
				this.$axios.get('/statistics-service/' + uuid)
					.then(response => {
						if (response.data.saving != null) {
							Vue.set(this.account, 'amount', response.data.saving.amount);
							Vue.set(this.account, 'currency', response.data.saving.currency);
						}
					}).catch(error => {
						alert(error);
					});
			}
		}
	}
</script>

<style>
</style>
