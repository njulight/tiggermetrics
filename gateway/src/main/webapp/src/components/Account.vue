<template>
	<div id="app">
		<img alt="Vue logo" src="../assets/logo.png">
		<van-list v-model="loading" :finished="finished" finished-text="没有更多了" @load="onLoad">
			<van-cell is-link v-for="item in list" :key="item" :title="item.name" v-on:click="showDetail(item.uuid)" />
		</van-list>
	</div>
</template>

<script>
	import Axios from 'axios';
	import Vue from 'vue';
	import {
		List
	} from 'vant';
	import {
		Cell
	} from 'vant';
	Vue.use(List);
	Vue.use(Cell);
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
		margin-top: 60px;
	}
</style>
