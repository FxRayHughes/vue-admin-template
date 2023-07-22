<template>
  <div id="assembly">
    <el-container>
      <el-header>
        <el-input
          placeholder="请输入内容"
          v-model="searchText"
          clearable
          @clear="handleClear"
          @keyup.enter.native="handleSearch"
          style="width: 300px;"
        ></el-input>
        <div style="transform: translateY(10px)">
          <el-button type="primary" @click="handleAdd" size="mini">新增</el-button>
        </div>
      </el-header>
      <el-main>
        <el-table
          :data="showData"
          height="100%"
          border
          style="width: 100%"
        >
          <el-table-column
            type="selection"
            width="55"
          />
          <el-table-column
            prop="id"
            label="编号"
            width="55"
          />
          <el-table-column
            prop="name"
            label="作业名称"
          />
          <el-table-column
            prop="pmc"
            label="生产计划"
            width="150"
          />
          <el-table-column
            prop="pps"
            label="计划排产"
          />
          <el-table-column
            prop="product"
            label="产品"
          />
          <el-table-column
            prop="model"
            label="型号"
          />
          <el-table-column
            prop="specifications"
            label="规格"
          />
          <el-table-column
            prop="unit"
            label="单位"
          />
          <el-table-column
            prop="start_time"
            label="开始时间"
          />
          <el-table-column
            prop="end_time"
            label="结束时间"
          />
          <el-table-column label="操作" fixed="right" width="150">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)"
              >编辑
              </el-button>
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)"
              >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>

    <el-dialog
      :title="formTitle"
      :visible.sync="dialogVisible"
      :before-close="handleClose"
    >
      <span slot="title">{{ formTitle }}</span>
      <el-form :inline="true">
        <el-form-item label="作业名称" required>
          <el-input v-model="form.name"/>
        </el-form-item>
        <el-form-item label="生产计划" required>
          <el-input v-model="form.pmc"/>
        </el-form-item>
        <el-form-item label="计划排产" required>
          <el-input v-model="form.pps"/>
        </el-form-item>
        <el-form-item label="产品" required>
          <el-input v-model="form.product"/>
        </el-form-item>
        <el-form-item label="型号" required>
          <el-input v-model="form.model"/>
        </el-form-item>
        <el-form-item label="规格" required>
          <el-input v-model="form.specifications"/>
        </el-form-item>
        <el-form-item label="单位" required>
          <el-input v-model="form.unit"/>
        </el-form-item>
        <el-form-item label="时间" required>
          <el-col :span="11">
            <el-form-item prop="date1">
              <el-date-picker
                type="date"
                placeholder="开始日期"
                v-model="ruleForm.date1"
                style="width: 100%;"
                format="yyyy 年 MM 月 dd 日"
                value-format="timestamp"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col class="line" :span="2">-</el-col>
          <el-col :span="11">
            <el-form-item prop="date2">
              <el-date-picker
                type="date"
                placeholder="结束日期"
                v-model="ruleForm.date2"
                style="width: 100%;"
                format="yyyy 年 MM 月 dd 日"
                value-format="timestamp"
              ></el-date-picker>
            </el-form-item>
          </el-col>
        </el-form-item>
        <!--        <el-form-item label="开始时间">-->
        <!--          <el-input v-model="form.start_time"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="结束时间">-->
        <!--          <el-input v-model="form.end_time"/>-->
        <!--        </el-form-item>-->
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateTable">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getAssemblyList, updateAssembly, deleteAssembly, addAssembly } from '@/api/mes/assembly'

export default {
  name: 'Assembly',
  data() {
    return {
      formTitle: '',
      tableData: [],
      showData: [],
      ruleForm: {},
      form: {},
      dialogVisible: false
    }
  },
  mounted() {
    this.getlist()
  },
  methods: {
    getlist() {
      new Promise((resolve, reject) => {
        getAssemblyList().then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      }).then(res => {
        this.tableData = res.data
        this.showData = this.formatTime(res.data)
        console.log(this.showData)
      }).catch(err => {
        console.log(err)
      })
    },
    formatTime(list) {
      const formattedList = JSON.parse(JSON.stringify(list))

      formattedList.forEach(item => {
        const startDate = new Date(item.start_time)
        item.start_time = startDate.getFullYear() + '年' + (startDate.getMonth() + 1) + '月' + startDate.getDate() + '日'

        const endDate = new Date(item.end_time)
        item.end_time = endDate.getFullYear() + '年' + (endDate.getMonth() + 1) + '月' + endDate.getDate() + '日'
      })

      return formattedList
    },
    handleEdit(index, row) {
      this.form = {}
      this.ruleForm = {}
      this.form = row
      this.formTitle = `编辑 ${row.id}`
      this.dialogVisible = true
    },
    handleDelete(index, row) {
      this.$confirm('确认删除？')
        .then(_ => {
          new Promise((resolve, reject) => {
            deleteAssembly(row.id).then(res => {
              resolve(res)
            }).catch(err => {
              reject(err)
            })
          }).then(res => {
            this.getlist()
          }).catch(err => {
            console.log(err)
          })
        })
        .catch(_ => {
        })
    },
    handleAdd() {
      this.form = {}
      this.ruleForm = {}
      this.formTitle = '新增'
      this.dialogVisible = true
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {
        })
    },
    updateTable() {
      this.dialogVisible = false
      new Promise((resolve, reject) => {
        const form = this.form
        if (this.ruleForm.date1 != null) {
          form.start_time = this.ruleForm.date1
        }
        if (this.ruleForm.date2 != null) {
          form.end_time = this.ruleForm.date2
        }
        if (form.id != null) {
          updateAssembly(form).then(res => {
            resolve(res)
          }).catch(err => {
            reject(err)
          })
        } else {
          addAssembly(form).then(res => {
            resolve(res)
          }).catch(err => {
            reject(err)
          })
        }
      }).then(res => {
        this.getlist()
      }).catch(err => {
        console.log(err)
      })
    }
  }
}

</script>

<style>
.el-header, .el-footer {
  color: #333;
  height: 10vh !important;
  transform: translateY(10px);
}

.el-main {
  color: #333;
  height: 80vh;
}

.cell {
  text-align: center;
}
</style>
