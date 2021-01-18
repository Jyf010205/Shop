<style lang="less">
@import './login.less';
</style>

<template>
  <div class="login">
    <div class="content">
      <header class="logo center">
        <img alt style="width:20%"
             src="../../assets/images/sdjyf-logo.png" />
      </header>
      <div class="form-con">
        <login-form @on-success-valid="handleSubmit"></login-form>
      </div>
      <footer class="center footerDesc">shop 由菅楞工厂店大力支持</footer>
    </div>
  </div>
</template>
<script>
import LoginForm from './components/login-form';
import { mapActions } from 'vuex';
import $ from 'jquery';
import { lonWave, canvasParticle } from './canvas';
export default {
  name: 'login',
  props: {},
  components: {
    LoginForm
  },
  data () {
    return {}
  },
  computed: {},
  watch: {},
  filters: {},
  created () {},
  mounted () {
    this.$Spin.hide();
  },
  methods: {
    ...mapActions(['handleLogin']),
    // 提交
    handleSubmit (params) {
      this.handleLogin(params).then(res => {
        this.$router.push({
          name: this.$config.homeName
        });
      });
    }
  }
};
</script>
