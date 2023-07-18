<template>
  <div class="f-dialog">
    <dialog :id="id" class="f-dialog-main">
      <h2 class="f-dialog-title">{{ title }}</h2>
      <slot/>
    </dialog>
  </div>
</template>

<script>
export default {
  name: 'FDialog',
  props: {
    title: {
      type: String,
      default: '信息弹窗'
    },
    id: {
      type: String,
      default: 'dialog'
    },
    styleObject: {
      type: Object,
      default: () => {
        return {
          width: '100%',
          height: '100%'
        }
      }
    },
    closeFun: {
      type: Function,
      default: () => false
    },
    open: {
      type: Boolean,
      default: true
    }
  },
  mounted() {
    const styleString = Object.keys(this.styleObject)
      .map(key => `${key}: ${this.styleObject[key]}`)
      .join('; ')
    const doc = document.getElementById(this.id)
    doc.setAttribute('style', styleString)
    if (this.open) {
      doc.showModal()
    }
  },
  methods: {
    closeDialog() {
      if (this.closeFun() === true) {
        const doc = document.getElementById(this.id)
        doc.close()
        this.open = false
      }
    },
    openDialog() {
      this.open = true
      const doc = document.getElementById(this.id)
      doc.showModal()
    }
  }
}

</script>

<style>

.f-dialog-title {
  text-align: center;
}

.f-dialog-main {
  background-color: white;
  border: 1px solid #ebebeb;
//box-shadow: 1 1 0 0 #97a8be;
}

dialog::backdrop {
  background: rgba(0, 0, 0, 0.3);
}
</style>
