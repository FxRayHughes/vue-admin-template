<template>
  <div v-if="open" class="f-dialog">
    <dialog :id="id">
      <slot />
    </dialog>
  </div>
</template>

<script>
export default {
  name: 'FDialog',
  props: {
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
    }
  },
  data() {
    return {
      open: true
    }
  },
  mounted() {
    const styleString = Object.keys(this.styleObject)
      .map(key => `${key}: ${this.styleObject[key]}`)
      .join('; ')
    const doc = document.getElementById(this.id)
    doc.setAttribute('style', styleString)
    doc.showModal()
  },
  methods: {
    closeDialog() {
      console.log('关闭了')
      const doc = document.getElementById(this.id)
      doc.close()
      this.open = false
    }
  }
}

</script>

<style>

</style>
