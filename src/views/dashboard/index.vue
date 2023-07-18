<template>
  <div class="dashboard-container">
    <div class="dashboard-text">name: {{ name }}</div>
    <button @click="openThis">开启</button>
    <!--    这里是新增的内容-->
    <f-dialog id="dialogger" ref="child" :style-object="styleObject" :close-fun="closeFun" :open="defOpen">
      <button @click="closeThis">关闭</button>
    </f-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import FDialog from '@/components/Dialog/index.vue'

export default {
  name: 'Dashboard',
  components: { FDialog },
  data() {
    return {
      styleObject: {
        width: '50%',
        height: '100%'
      },
      defOpen: false
    }
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  methods: {
    closeThis() {
      this.$refs.child.closeDialog()
    },
    closeFun() {
      return true
    },
    openThis() {
      this.$refs.child.openDialog()
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }

  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
