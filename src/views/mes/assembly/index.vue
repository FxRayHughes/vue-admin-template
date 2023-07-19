<template>
  <div id="assembly">
    <el-container>
      <el-header>搜索与增加</el-header>
      <el-main>
        <el-table
          :data="tableData"
          height="100%"
          border
          style="width: 100%"
          header-row-style="text-align: center"
        >
          <el-table-column
            type="selection"
            width="55"
          />
          <el-table-column
            prop="id"
            label="编号"
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
  </div>
</template>

<script>
export default {
  name: 'Assembly',
  data() {
    return {
      tableData: [{
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }
      ]
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
