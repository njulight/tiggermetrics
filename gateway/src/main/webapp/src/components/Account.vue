<template>
	<div id="app">
		<van-nav-bar title="账户" @click-right="onClickRight">
			<van-icon name="add-o" slot="right" />
		</van-nav-bar>
		<img alt="Vue logo" src="../assets/logo.png">
		<van-list v-model="loading" :finished="finished" finished-text="没有更多了" @load="onLoad">
			<van-swipe-cell :on-close="onClose" v-for="item in list" :key="item">
				<van-cell :border="false" is-link :title="item.name" v-on:click="showDetail(item.uuid)" />
				<template slot="right">
					<van-button square type="danger" text="删除" />
				</template>
			</van-swipe-cell>
		</van-list>
	</div>
</template>

<script>
	import Axios from 'axios';
	import Vue from 'vue';
	import {
		NavBar,
		Icon,
		List,
		SwipeCell,
		Cell,
		Dialog,
		Button
	} from 'vant';
	Vue.use(NavBar).use(Icon).use(List).use(SwipeCell).use(Cell).use(Dialog).use(Button);
	Vue.prototype.$axios = Axios

	export default {
		name: 'Account',
		data() {
			return {
				list: [],
				loading: false,
				finished: false
			};
		},

		methods: {
			onClose(clickPosition, instance) {
				switch (clickPosition) {
					case 'left':
					case 'cell':
					case 'outside':
						instance.close();
						break;
					case 'right':
						Dialog.confirm({
							message: '确定删除吗？'
						}).then(() => {
							instance.close();
						});
						break;
				}
			},
			onLoad() {
				this.$axios.get('/accout-service')
					.then(response => {
						response.data.forEach(item => {
							this.list.push(item);
						});
						this.loading = false;
						this.finished = true;
					}).catch(error => {
						alert(error);
					});
			},
			onClickRight() {
				this.$router.push({
					name: 'AddAccount'
				})
			},
			showDetail: function(message) {
				this.$router.push({
					name: 'AccountDetail',
					params: {
						id: message
					}
				})
			}
		}
	}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	h3 {
		margin: 40px 0 0;
	}

	ul {
		list-style-type: none;
		padding: 0;
	}

	li {
		display: inline-block;
		margin: 0 10px;
	}

	a {
		color: #42b983;
	}

	#app {
		font-family: 'Avenir', Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		text-align: center;
		color: #2c3e50;
	}
</style>
