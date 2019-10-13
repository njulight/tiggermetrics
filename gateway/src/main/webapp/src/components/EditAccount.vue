<template>
	<div>
		<van-nav-bar title="编辑账户" left-text="返回" right-text="确定" left-arrow @click-left="onClickLeft" @click-right="onClickRight" />
		<van-cell-group title="基本信息">
			<van-field v-model="name" label="账户名" placeholder="请输入账户名" maxlength="255" required="true" />
			<van-field v-model="note" label="备注" placeholder="请输入备注" maxlength="255" />
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
				name: "",
				note: ""
			};
		},
		mounted: function() {
			if (this.$route.params.id == null || this.$route.params.name == null) {
				this.$router.push({
					name: 'Account'
				})
			}
			this.name = this.$route.params.name;
			this.note = this.$route.params.note;
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
				if (this.name === "") {
					Toast('请输入账户名');
					return;
				}
				let data = {
					"uuid": this.$route.params.id,
					"name": this.name,
					"note": this.note,
					"lastSeen": new Date()
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
						Toast("编辑失败");
					});
			}
		},
	}
</script>

<style>
</style>
