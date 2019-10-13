<template>
	<div>
		<van-nav-bar title="添加收入" left-text="返回" right-text="确定" left-arrow @click-left="onClickLeft" @click-right="onClickRight" />
		<van-cell-group title="收入">
			<van-field v-model="amount" label="金额" placeholder="请输入收入金额" maxlength="255" required="true" />
			<van-field v-model="currency" label="币种" placeholder="请选择币种" maxlength="255" required="true" />
			<van-field v-model="itemTime" label="时间" placeholder="请选择时间" maxlength="255" required="true" />
			<van-field v-model="title" label="描述" placeholder="请输入描述" maxlength="255" required="true" />
		</van-cell-group>
	</div>
</template>

<script>
	import Axios from 'axios';
	import Vue from 'vue';
	import {
		NavBar,
		CellGroup,
		Field,
		Toast
	} from 'vant';
	Vue.use(NavBar).use(CellGroup).use(Field).use(Toast);
	Vue.prototype.$axios = Axios

	export default {
		data() {
			return {
				amount: "",
				currency: "",
				itemTime: "",
				title: ""
			};
		},
		mounted: function() {
			if (this.$route.params.id == null || this.$route.params.name == null) {
				this.$router.push({
					name: 'Account'
				})
			}
		},
		methods: {
			onClickLeft() {
				this.$router.push({
					name: 'AccountDetail',
					params: {
						id: this.$route.params.id
					}
				})
			},
			onClickRight() {
				if (this.amount === "" || this.currency === "" || this.itemTime === "" || this.title === "") {
					Toast('请输入必填信息');
					return;
				}
				let data = {
					"uuid": this.$route.params.id,
					"name": this.$route.params.name,
					"note": this.$route.params.note,
					"lastSeen": new Date(),
					"incomes": [{
						"title": this.title,
						"amount": this.amount,
						"currency": this.currency,
						"itemTime": this.itemTime,
						"icon": "icon",
						"itemType": "INCOMES"
					}]
				};
				this.$axios.put('/accout-service', data)
					.then(response => {
						if (response.status === 200) {
							this.$router.push({
								name: 'AccountDetail',
								params: {
									id: this.$route.params.id
								}
							})
						}
					}).catch(() => {
						Toast("添加失败");
					});
			}
		},
	}
</script>

<style>
</style>
