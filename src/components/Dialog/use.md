## DiaLog 弹窗使用方式

### 调用

#### 默认开启状态

```vue

<f-dialog id="dialogger" ref="child" :style-object="styleObject">
<button @click="closeThis">关闭</button>
</f-dialog>
```

#### 默认关闭状态

```vue
<f-dialog id="dialogger" ref="child" :style-object="styleObject" :open="false">
<button @click="closeThis">关闭</button>
</f-dialog>
```

如果想开启 你需要操作子组件的 open 参数

```vue
export default {
  methods: {
    openThis() {
      this.$refs.child.openDialog()
    }
  }
}
```

#### 设置样式

```vue
export default {
  data() {
    return {
      styleObject: {
        width: '50%',
        height: '100%'
      }
    }
  },
}
```
绑定给 styleObject 参数即可

#### 关闭弹窗

```vue
  methods: {
    closeThis() {
      this.$refs.child.closeDialog()
    }
  }
```
需要绑定一个 ref 然后执行子组件里面的 closeDialog() 函数

#### 自定义关闭回调

比如我们需要某些情况下无法关闭弹窗

可以使用这种方式进行回调 
回调会在子组件运行所以父组件元素需要斟酌

需要返回一个 布尔值 true是允许关闭 false是不允许
```vue
<f-dialog id="dialogger" :closeFun="closeBefore">
<button @click="closeThis">关闭</button>
</f-dialog>

export default {
  methods: {
    closeBefore() {
      console.log('关闭前')
      return 1 > 50
    }
  }
}
```

