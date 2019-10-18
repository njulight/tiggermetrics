<template>
	<div>
		<van-nav-bar title="添加支出" left-text="返回" right-text="确定" left-arrow @click-left="onClickLeft" @click-right="onClickRight" />
		<van-cell-group title="支出">
			<van-field :value="amount" label="金额" placeholder="请输入支出金额" maxlength="255" required readonly clickable
			 @touchstart.native.stop="showAmountKeyBoard = true" />
			<van-field v-model="currency" label="币种" placeholder="请选择币种" maxlength="255" required readonly clickable @click="currencyPicker = true" />
			<van-field v-model="itemTime" label="时间" placeholder="请选择时间" maxlength="255" required readonly clickable @click="itemTimePicker = true" />
			<van-field v-model="title" label="描述" placeholder="请输入描述" maxlength="255" required />
		</van-cell-group>
		<van-popup v-model="currencyPicker" position="bottom">
			<van-picker show-toolbar :columns="columns" @cancel="currencyPicker = false" @confirm="onCurrencyConfirm" />
		</van-popup>
		<van-popup v-model="itemTimePicker" position="bottom">
			<van-datetime-picker show-toolbar v-model="currentDate" type="date" :min-date="minDate" :max-date="maxDate" @cancel="itemTimePicker = false"
			 @confirm="onItemTimeConfirm" />
		</van-popup>
		<van-number-keyboard v-model="amount" :show="showAmountKeyBoard" :maxlength="10" @blur="showAmountKeyBoard = false" />
	</div>
</template>

<script>
	import Axios from 'axios';
	import Vue from 'vue';
	import {
		NavBar,
		CellGroup,
		Picker,
		DatetimePicker,
		NumberKeyboard,
		Popup,
		Field,
		Toast
	} from 'vant';
	Vue.use(NavBar).use(CellGroup).use(Picker).use(DatetimePicker).use(NumberKeyboard).use(Popup).use(Field).use(Toast);
	Vue.prototype.$axios = Axios

	export default {
		data() {
			return {
				amount: "",
				showAmountKeyBoard: false,
				currency: "",
				itemTime: "",
				title: "",
				currencyPicker: false,
				itemTimePicker: false,
				columns: ['人民币', '美元', '欧元'],
				minDate: new Date(2009, 12, 31),
				maxDate: new Date(2029, 12, 31),
				currentDate: new Date()
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
			onCurrencyConfirm(value) {
				this.currency = value;
				this.currencyPicker = false;
			},
			onItemTimeConfirm(value) {
				this.itemTime = value;
				this.itemTimePicker = false;
			},
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
				let currenyMap = {
					"人民币": "RMB",
					"美元": "USD",
					"欧元": "EUR"
				}
				let data = {
					"uuid": this.$route.params.id,
					"name": this.$route.params.name,
					"note": this.$route.params.note,
					"lastSeen": new Date(),
					"expenses": [{
						"title": this.title,
						"amount": this.amount,
						"currency": currenyMap[this.currency],
						"itemTime": new Date(this.itemTime),
						"icon": "icon",
						"itemType": "EXPENSES"
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
